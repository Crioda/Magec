package crit.magec.block.custom;

import com.mojang.brigadier.ParseResults;
import crit.magec.block.ModBlocks;
import crit.magec.entity.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SummoningCandle extends Block {
    public static final BooleanProperty LIT = BooleanProperty.of("lit");
    public SummoningCandle(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
        super.appendProperties(builder);
    }


    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        int candles = 0;
        List<BlockPos> candlepos = new ArrayList<>();
        if (!world.isClient) {
           if (player.isSneaking() && !state.get(LIT) && player.getMainHandStack().getItem() == Items.FLINT_AND_STEEL) {
                world.setBlockState(pos, state.cycle(LIT));
                player.stopUsingItem();
                return ActionResult.SUCCESS;
            } else if (player.isSneaking() && state.get(LIT)) {
                world.setBlockState(pos, state.cycle(LIT));
                return ActionResult.SUCCESS;
            }
            if (player.getMainHandStack().getItem() == Items.FLINT_AND_STEEL) return ActionResult.FAIL;
            for (int i = -6; i < 12; i++) {
                for (int j = -6; j < 12; j++) {

                    if (world.getBlockState(pos.east(i).north(j)).isOf(ModBlocks.CANDLE) &&
                            world.getBlockState(pos.east(i).north(j)).get(LIT)) {
                        candles++;
                        BlockPos candle = pos.east(i).north(j);
                        candlepos.add(candle);
                    }
                    if (candles >= 8) {
                        Vec3i candlespawn = getVec3i(pos, candlepos);
                        world.spawnEntity(ModEntities.DIVINITYTEST.spawn((ServerWorld) world, new BlockPos(candlespawn), SpawnReason.TRIGGERED));
                        break;

                    } else if (candles >= 4) {
                    }
                }
            }
        }
            return super.onUse(state, world, pos, player, hit);

    }

    private static @NotNull Vec3i getVec3i(BlockPos pos, List<BlockPos> candlepos) {
        BlockPos candle1 = candlepos.get(0);
        BlockPos candle2 = candlepos.get(1);
        BlockPos candle3 = candlepos.get(2);
        BlockPos candle4 = candlepos.get(3);
        BlockPos candle5 = candlepos.get(4);
        BlockPos candle6 = candlepos.get(5);
        BlockPos candle7 = candlepos.get(6);
        BlockPos candle8 = candlepos.get(7);

        int candlex = (candle1.getX() + candle2.getX() + candle3.getX() + candle4.getX() + candle5.getX() + candle6.getX() + candle7.getX() + candle8.getX()) / 8;
        int candlez = (candle1.getZ() + candle2.getZ() + candle3.getZ() + candle4.getZ() + candle5.getZ() + candle6.getZ() + candle7.getZ() + candle8.getZ()) / 8;

        Vec3i candlespawn = new Vec3i(candlex, pos.getY(), candlez);
        return candlespawn;
    }

    @Override
    protected boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return true;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return  VoxelShapes.cuboid(0.3f, 0.0f, 0.3f, .7f, .8f, .7f);
    }
}

