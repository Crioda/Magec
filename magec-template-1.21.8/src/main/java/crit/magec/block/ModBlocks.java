package crit.magec.block;

import crit.magec.Magec;
import crit.magec.block.custom.Bigdoor;
import crit.magec.block.custom.Decayingblock;
import crit.magec.block.custom.NeurotoxinEmitter;
import crit.magec.block.custom.SummoningCandle;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {

    public static final Block CRACKED_BEDROCK = register(
            "cracked_bedrock",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE),
            true
    );

    public static final Block DECAY = register(
            "decay",
            Decayingblock::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE).strength(-1).hardness(-1).ticksRandomly(),
            true
    );

    public static final Block CANDLE = register(
            "candle",
            SummoningCandle::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.CANDLE),
            true
    );

    public static final Block NEUROTOXIN_EMITTER = register(
            "neurotoxin_emitter",
            NeurotoxinEmitter::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.CANDLE),
            true
    );

    public static final Block BIG_DOOR = register(
            "big_door",
            Bigdoor::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE),
            true
    );

    public static void initialize() {
    }

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));
        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Magec.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Magec.MOD_ID, name));
    }
}
