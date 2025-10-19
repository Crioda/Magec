package crit.magec.entity;

import crit.magec.Magec;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModEntities {
    private static final RegistryKey<EntityType<?>> DIVINITYTEST_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Magec.MOD_ID, "divinitytest"));

    public static final EntityType<DivinityTest> DIVINITYTEST = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Magec.MOD_ID, "divinitytest"),
            FabricEntityType.Builder.createLiving(DivinityTest::new, SpawnGroup.MISC, UnaryOperator.identity())
                    .dimensions(1f, 3f).build(DIVINITYTEST_KEY));


    public static void registerModEntities() {
        Magec.LOGGER.info("Registering Mod Entities for " + Magec.MOD_ID);
    }
}
