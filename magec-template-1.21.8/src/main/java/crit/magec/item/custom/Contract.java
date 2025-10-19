package crit.magec.item.custom;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.CommandContextBuilder;
import crit.magec.Magec;
import crit.magec.components.ModComponents;
import me.emafire003.dev.structureplacerapi.StructurePlacerAPI;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


public class Contract extends Item {
    public Contract(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        int Tick = 0;
        ItemStack stack = user.getStackInHand(hand);
        String boundTo = stack.get(ModComponents.BOUND_TO_NAME);
        if ("Unbound".equals(boundTo) && user.isSneaking()) {
            user.playSound(SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 10, 1);
        }
        if (!world.isClient) {
            if (user.isSneaking()) {
                String uuid = user.getUuidAsString();
                String name = user.getNameForScoreboard();
                if ("Unbound".equals(boundTo)) {
                    stack.set(ModComponents.BOUND_TO_UUID, uuid);
                    stack.set(ModComponents.BOUND_TO_NAME, name);
                    return ActionResult.SUCCESS;
                }else if (user.getPitch() == -90) {
                    RegistryKey<World> contractedKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of("magec", "insidethecontract"));
                    ServerWorld contracted = world.getServer().getWorld(contractedKey);
                    stack.decrement(stack.getCount());
                    user.getInventory().dropAll();
                    user.setHealth(20);
                    user.incrementStat(Stats.DEATHS);
                    user.resetStat(Stats.CUSTOM.getOrCreateStat(Stats.TIME_SINCE_DEATH));
                    user.resetStat(Stats.CUSTOM.getOrCreateStat(Stats.TIME_SINCE_REST));
                    user.setLastDeathPos(Optional.of(GlobalPos.create(user.getWorld().getRegistryKey(), user.getBlockPos())));
                    user.extinguish();
                    user.setOnFire(false);
                    user.teleport(contracted, Random.create().nextBetween(-1000, 1000), 1, Random.create().nextBetween(-1000, 1000), Set.of(PositionFlag.X), user.getYaw(), user.getPitch(), true);
                    world.getServer().execute(() -> {
                        world.getServer().execute(() -> {
                            ServerCommandSource source = world.getServer().getCommandSource().withWorld(contracted);
                            String command = "execute in magec:insidethecontract as @a[name=\"" + name + "\"] at @s run place structure magec:dungeon ~ ~ ~";
                            world.getServer().getCommandManager().executeWithPrefix(source, command);
                        });
                    });

                }
            }

        }
        // i know this is the wrong place to call this but this is just a test
        //MinecraftClient.getInstance().setScreen(new CustomScreen(Text.empty()));
        return ActionResult.FAIL;
    }

   @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
       String boundTo = context.getStack().get(ModComponents.BOUND_TO_NAME);
       World world = context.getWorld();
       PlayerEntity player = context.getPlayer();
       BlockPos pos = context.getBlockPos();
            if (player.isSneaking() && !("Unbound".equals(boundTo))){
                player.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1, 1);
                world.addParticleClient(ParticleTypes.FLAME, pos.getX(), pos.getY()+1, pos.getZ(), 0, 0, 0);
                if (!world.isClient) player.getMainHandStack().decrement(player.getMainHandStack().getCount());


                return ActionResult.SUCCESS;
            }
        return ActionResult.FAIL;
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (attacker.isSneaking() && !attacker.getWorld().isClient){
            String targetUuid = target.getUuidAsString();
            String attackerContractUuid = attacker.getMainHandStack().get(ModComponents.BOUND_TO_UUID);
            if (targetUuid.equals(attackerContractUuid)) {
                if (target.hasStatusEffect(Magec.TETHERED_EFFECT)){
                    target.removeStatusEffect(Magec.TETHERED_EFFECT);
                }else {
                    target.addStatusEffect(new StatusEffectInstance(Magec.TETHERED_EFFECT, 72000, 0));
                }
            }
        }
        super.postHit(stack, target, attacker);
    }

    @Override
    public void onItemEntityDestroyed(ItemEntity entity) {
        World world = entity.getWorld();
        if (!world.isClient){
            ItemStack stack = entity.getStack();
            String uuid = stack.get(ModComponents.BOUND_TO_UUID);
            LivingEntity target = (LivingEntity) world.getEntity(UUID.fromString(uuid));
            assert target != null;
            target.setOnFireFor(100);
            target.teleport((ServerWorld) world, entity.getX(), entity.getY(), entity.getZ(), Set.of(PositionFlag.X), entity.getYaw(), entity.getPitch(), false);
        }

        super.onItemEntityDestroyed(entity);
    }
}
