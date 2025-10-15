package crit.magec.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
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

        if (!world.isClient) {
            Vec3d rotation = user.getRotationVec(1);

            world.spawnEntity(EntityType.FIREBALL.spawn((ServerWorld) world, user.getBlockPos().up(1), SpawnReason.SPAWN_ITEM_USE));

        }

        return super.use(world, user, hand);
    }
}
