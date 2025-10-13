package crit.magec.item.custom;

import crit.magec.block.ModBlocks;
import crit.magec.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Map;

public class Chisel extends Item {
    public Chisel(Settings settings) {
        super(settings);
    }
    public static final Map<Block, Block> CHISEL_MAP =
            Map.of(Blocks.BEDROCK, ModBlocks.CRACKED_BEDROCK);

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();
        if (CHISEL_MAP.containsKey(clickedBlock) && !world.isClient) {
            world.setBlockState(context.getBlockPos(), ModBlocks.CRACKED_BEDROCK.getDefaultState());
            player.giveItemStack(ModItems.BEDROCK_SHARD.getDefaultStack());

        }
        return super.useOnBlock(context);
    }
}
