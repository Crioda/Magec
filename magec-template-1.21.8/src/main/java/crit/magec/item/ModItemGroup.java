package crit.magec.item;

import crit.magec.Magec;
import crit.magec.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup{
    public static final ItemGroup MAGEC_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Magec.MOD_ID, "magec"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.CONTRACT))
                    .displayName(Text.translatable("itemgroup.magec.items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.CONTRACT);
                        entries.add(ModItems.CHISEL);
                        entries.add(ModItems.BEDROCK_SHARD);




                    }).build());

    public static void registerItemGroups(){
        Magec.LOGGER.info(("Registering Item Groups for" + Magec.MOD_ID));
    }
}
