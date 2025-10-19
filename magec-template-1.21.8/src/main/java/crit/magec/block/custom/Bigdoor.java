package crit.magec.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Bigdoor extends Block {
    public Bigdoor(Settings settings) {
        super(settings);
    }
    public static final BooleanProperty OPEN = BooleanProperty.of("open");
    public static final BooleanProperty SECOND = BooleanProperty.of("second");
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(OPEN);
        super.appendProperties(builder);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient){
            if (state.get(OPEN) && !state.get(SECOND)){
                world.setBlockState(pos, state.with(OPEN, false));
                world.setBlockState(pos.up(2), state.with(OPEN, false).with(SECOND, false));
                world.setBlockState(pos.up(4), state.with(OPEN, false).with(SECOND, false));
            } else if (!state.get(SECOND)) {
                world.setBlockState(pos, state.with(OPEN, true));
                world.setBlockState(pos.up(2), state.with(OPEN, true).with(SECOND, false));
                world.setBlockState(pos.up(4), state.with(OPEN, true).with(SECOND, false));
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (true){
            return VoxelShapes.cuboid(0, 0, -1, 1, 2, 1);
        }
        return VoxelShapes.cuboid(0, 0, -1, 1, 2, 1);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0, 0, -1, 1, 2, 1);
    }

    @Override
    protected VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0, 0, -1, 1, 2, 1);
    }

    @Override
    protected VoxelShape getCullingShape(BlockState state) {
        return VoxelShapes.cuboid(0, 0, -1, 1, 2, 1);
    }

    @Override
    protected VoxelShape getInsideCollisionShape(BlockState state, BlockView world, BlockPos pos, Entity entity) {
        return VoxelShapes.cuboid(0, 0, -1, 1, 2, 1);
    }

    @Override
    protected VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.cuboid(0, 0, -1, 1, 2, 1);
    }

    @Override
    protected VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.cuboid(0, 0, -1, 1, 2, 1);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient){
            world.setBlockState(pos.up(2), state);
            world.setBlockState(pos.up(4), state);
            state.cycle(SECOND);
        }

        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
