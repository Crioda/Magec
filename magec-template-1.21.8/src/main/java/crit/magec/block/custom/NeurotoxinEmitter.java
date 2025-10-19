package crit.magec.block.custom;

import com.google.common.base.Predicates;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class NeurotoxinEmitter extends Block {
    public NeurotoxinEmitter(Settings settings) {
        super(settings);
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, 20);
        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isClient) return;
        Box box = new Box(pos);
        world.getEntitiesByClass(
                LivingEntity.class,
                box.expand(5),
                entity -> !entity.isDead()
        ).forEach(entity -> entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 20, 2))
        );
        //for (int i = 0; i < 30; i++) {
            world.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, pos.getX() + .5 ,pos.getY() + .5 ,pos.getZ() + .5, 50   , .1, .1, .1, .2);
        //}
        world.scheduleBlockTick(pos, this, 1);

        super.scheduledTick(state, world, pos, random);
    }
}
