package crit.magec;

import crit.magec.block.ModBlocks;
import crit.magec.components.ModComponents;
import crit.magec.effect.TetheredEffect;
import crit.magec.entity.DivinityTest;
import crit.magec.entity.ModEntities;
import crit.magec.item.ModItemGroup;
import crit.magec.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.client.data.Models.CUBE;

public class Magec implements ModInitializer {
	public static final String MOD_ID = "magec";
	public static final RegistryEntry<StatusEffect> TETHERED_EFFECT =
			Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Magec.MOD_ID, "tethered_effect"), new TetheredEffect());

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModBlocks.initialize();
		ModComponents.initialize();
		ModItemGroup.registerItemGroups();
		ModEntities.registerModEntities();
		FabricDefaultAttributeRegistry.register(ModEntities.DIVINITYTEST, DivinityTest.createAttributes());

	}
}