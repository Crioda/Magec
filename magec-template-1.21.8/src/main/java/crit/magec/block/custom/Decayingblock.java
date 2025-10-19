package crit.magec.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class Decayingblock extends Block {
    public Decayingblock(Settings settings) {
        super(settings);
    }


    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        world.scheduleBlockTick(pos, this, Random.create().nextBetween(6000, 8000));
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.scheduleBlockTick(pos, this, Random.create().nextBetween(6000, 10000));
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient){
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            world.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, state),
                    pos.getX() + 0.5, pos.getY() + .5,
                    pos.getZ() + 0.5, 10, 0, 0, 0, 1);
        }
        super.scheduledTick(state, world, pos, random);
    }
}
