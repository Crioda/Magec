package crit.magec.item;

import crit.magec.Magec;
import crit.magec.components.ModComponents;
import crit.magec.item.custom.Chisel;
import crit.magec.item.custom.Contract;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.util.CaveSurface;

import java.util.function.Function;

public class ModItems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Magec.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }
    public static final Item CONTRACT = register("contract", Contract::new, new Item.Settings()
            .component(ModComponents.BOUND_TO_NAME, "Unbound").component(ModComponents.BOUND_TO_UUID, "0").maxCount(1).fireproof());

    public static final Item CHISEL = register("chisel", Chisel::new, new Item.Settings());

    public static final Item BEDROCK_SHARD = register("bedrock_shard", Item::new, new Item.Settings().fireproof());

    public static void initialize() {}
}
