package crit.magec.datagen;


import crit.magec.block.ModBlocks;
import crit.magec.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;



public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerGeneric(ModBlocks.CRACKED_BEDROCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CONTRACT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEDROCK_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHISEL, Models.HANDHELD);
    }

}
