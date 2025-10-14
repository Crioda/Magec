package crit.magec.item.custom;

import crit.magec.block.ModBlocks;
import crit.magec.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class Chisel extends Item {
    public Chisel(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();
        if (clickedBlock == Blocks.BEDROCK && !world.isClient) {
            world.setBlockState(context.getBlockPos(), ModBlocks.CRACKED_BEDROCK.getDefaultState());
            player.giveItemStack(ModItems.BEDROCK_SHARD.getDefaultStack());
        }
        return super.useOnBlock(context);
    }
}
