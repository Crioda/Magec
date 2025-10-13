package crit.magec.block.custom;

import crit.magec.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
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
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

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
        if (!world.isClient) {
           /* if (player.isSneaking() && !state.get(LIT) && player.getMainHandStack().getItem() == Items.FLINT_AND_STEEL) {
                world.setBlockState(pos, state.cycle(LIT));
                player.stopUsingItem();
                return ActionResult.SUCCESS;
            } else if (player.isSneaking() && state.get(LIT)) {
                world.setBlockState(pos, state.cycle(LIT));
                return ActionResult.SUCCESS;
            }*/
            if (player.getMainHandStack().getItem() == Items.FLINT_AND_STEEL) return ActionResult.FAIL;
            for (int i = -6; i < 12; i++) {
                if (world.getBlockState(pos.east(i).north(-5)).isOf(ModBlocks.CANDLE)) {
                    candles++;
                }
                if (world.getBlockState(pos.east(i).north(-4)).isOf(ModBlocks.CANDLE)) {
                    candles++;
                }
                if (world.getBlockState(pos.east(i).north(-3)).isOf(ModBlocks.CANDLE)) {
                    candles++;
                }
                if (world.getBlockState(pos.east(i).north(-2)).isOf(ModBlocks.CANDLE)) {
                    candles++;
                }
                if (world.getBlockState(pos.east(i).north(-1)).isOf(ModBlocks.CANDLE)) {
                    candles++;
                }
                if (world.getBlockState(pos.east(i)).isOf(ModBlocks.CANDLE)) {
                    candles++;
                }
                if (world.getBlockState(pos.east(i).north(1)).isOf(ModBlocks.CANDLE)) {
                    candles++;
                }
                if (world.getBlockState(pos.east(i).north(2)).isOf(ModBlocks.CANDLE)) {
                    candles++;
                }
                if (world.getBlockState(pos.east(i).north(3)).isOf(ModBlocks.CANDLE)) {
                    candles++;
                }
                if (world.getBlockState(pos.east(i).north(4)).isOf(ModBlocks.CANDLE)) {
                    candles++;
                }
                if (world.getBlockState(pos.east(i).north(5)).isOf(ModBlocks.CANDLE)) {
                    candles++;
                }
                if (candles >= 4) {
                    player.sendMessage(Text.literal("Works"), false);
                    break;
                }
            }
        }
            return super.onUse(state, world, pos, player, hit);

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

