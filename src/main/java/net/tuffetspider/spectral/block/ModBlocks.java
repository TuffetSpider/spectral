package net.tuffetspider.spectral.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.tuffetspider.spectral.Spectral;

public class ModBlocks {
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Spectral.MOD_ID, name), block);
    }
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Spectral.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }
    public static Block SPECTRAL_BLOCK;
    public static Block COLD_IRON_BLOCK;

    public static void registerModBlocks(){
        COLD_IRON_BLOCK=registerBlock("cold_iron_block", new ColdIronBlock(AbstractBlock.Settings.create().nonOpaque().blockVision(Blocks::never).suffocates(Blocks::never).solid().sounds(BlockSoundGroup.METAL).strength(5f).requiresTool()));

        SPECTRAL_BLOCK=registerBlock("spectral_block", new SpectralBlock(AbstractBlock.Settings.create().nonOpaque().blockVision(Blocks::never).suffocates(Blocks::never).solid().sounds(BlockSoundGroup.POWDER_SNOW).hardness(0.2f)));
    }
}
