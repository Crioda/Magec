package crit.magec.block.custom;

import crit.magec.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class Bedrock extends Block {
    public static final BooleanProperty NORTH = BooleanProperty.of("north");
    public static final BooleanProperty SOUTH = BooleanProperty.of("south");
    public static final BooleanProperty EAST = BooleanProperty.of("east");
    public static final BooleanProperty WEST = BooleanProperty.of("west");
    public static final BooleanProperty UP = BooleanProperty.of("up");
    public static final BooleanProperty DOWN = BooleanProperty.of("down");
    public Bedrock(Settings settings) {
        super(settings);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH);
        builder.add(SOUTH);
        builder.add(EAST);
        builder.add(WEST);
        builder.add(UP);
        builder.add(DOWN);
        super.appendProperties(builder);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Direction face = hit.getSide();
        if (!(stack.getItem() == ModItems.CHISEL)) return ActionResult.FAIL;
        if (face.equals(Direction.NORTH)){
            if (state.get(NORTH))return ActionResult.FAIL;
            state.cycle(NORTH);
            dropStack(world, pos, ModItems.BEDROCK_SHARD.getDefaultStack());
        } else if (face.equals(Direction.SOUTH)) {
            if (state.get(SOUTH))return ActionResult.FAIL;
            state.cycle(SOUTH);
            dropStack(world, pos, ModItems.BEDROCK_SHARD.getDefaultStack());
        } else if (face.equals(Direction.EAST)) {
            if (state.get(EAST))return ActionResult.FAIL;
            state.cycle(EAST);
            dropStack(world, pos, ModItems.BEDROCK_SHARD.getDefaultStack());
        } else if (face.equals(Direction.WEST)) {
            if (state.get(WEST))return ActionResult.FAIL;
            state.cycle(WEST);
            dropStack(world, pos, ModItems.BEDROCK_SHARD.getDefaultStack());
        } else if (face.equals(Direction.UP)) {
            if (state.get(UP))return ActionResult.FAIL;
            state.cycle(UP);
            dropStack(world, pos, ModItems.BEDROCK_SHARD.getDefaultStack());
        } else if (face.equals(Direction.DOWN)) {
            if (state.get(DOWN))return ActionResult.FAIL;
            state.cycle(DOWN);
            dropStack(world, pos, ModItems.BEDROCK_SHARD.getDefaultStack());
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }
}
