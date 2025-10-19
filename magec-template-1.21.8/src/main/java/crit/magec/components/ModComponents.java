package crit.magec.components;

import com.mojang.serialization.Codec;
import crit.magec.Magec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static final ComponentType<String> BOUND_TO_UUID = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Magec.MOD_ID, "bound_to_uuid"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    public static final ComponentType<String> BOUND_TO_NAME = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Magec.MOD_ID, "bound_to_name"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );
    public static final ComponentType<Boolean> HAS_SOUL = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Magec.MOD_ID, "has_soul"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
    );

    public static void initialize() {}
}