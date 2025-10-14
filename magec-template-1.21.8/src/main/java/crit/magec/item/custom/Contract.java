package crit.magec.item.custom;

import crit.magec.Magec;
import crit.magec.components.ModComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class Contract extends Item {
    public static final BooleanProperty SIGNED = BooleanProperty.of("signed");
    public Contract(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        String boundTo = stack.get(ModComponents.BOUND_TO_NAME);
        if ("Unbound".equals(boundTo)) {
            user.playSound(SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 10, 1);
        }
        if (!world.isClient) {
            String uuid = user.getUuidAsString();
            String name = user.getNameForScoreboard();
            if ("Unbound".equals(boundTo)) {
                stack.set(ModComponents.BOUND_TO_UUID, uuid);
                stack.set(ModComponents.BOUND_TO_NAME, name);
                return ActionResult.SUCCESS;
            }
        }

        //MinecraftClient.getInstance().setScreen(new CustomScreen(Text.empty()));
        return ActionResult.FAIL;
    }

   @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
       BlockPos pos = context.getBlockPos();
            if (player.isSneaking()){
                player.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1, 1);
                world.addParticleClient(ParticleTypes.FLAME, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
                if (!world.isClient) player.getMainHandStack().decrement(1);

                return ActionResult.SUCCESS;
            }
        return ActionResult.FAIL;
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (attacker.isSneaking() && !attacker.getWorld().isClient){
            String targetuuid = target.getUuidAsString();
            String attackercontractuuid = attacker.getMainHandStack().get(ModComponents.BOUND_TO_UUID);
            if (targetuuid.equals(attackercontractuuid)) {
                if (target.hasStatusEffect(Magec.TETHERED_EFFECT)){
                    target.removeStatusEffect(Magec.TETHERED_EFFECT);
                }else {
                    target.addStatusEffect(new StatusEffectInstance(Magec.TETHERED_EFFECT, 300, 1));
                }
            }
        }
        super.postHit(stack, target, attacker);
    }


}
