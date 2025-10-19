package crit.magec.item.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class Wand extends Item {
    public Wand(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        return ActionResult.CONSUME;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 40;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (remainingUseTicks >= 0 && user instanceof PlayerEntity playerEntity) {
            int tick = this.getMaxUseTime(stack, user) - remainingUseTicks + 1;

            while (tick >= 0) {
                tick = this.getMaxUseTime(stack, user) - remainingUseTicks + 1;
                String string = String.valueOf(tick);
                ((PlayerEntity) user).sendMessage(Text.literal(string), false);
            }

        }

        super.usageTick(world, user, stack, remainingUseTicks);
    }


    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {

        if (remainingUseTicks <= 0) {
            world.spawnEntity(EntityType.FIREBALL.spawn((ServerWorld) world, user.getBlockPos().up(1), SpawnReason.SPAWN_ITEM_USE));
        }

        return super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public boolean isUsedOnRelease(ItemStack stack) {
        return super.isUsedOnRelease(stack);
    }

}
