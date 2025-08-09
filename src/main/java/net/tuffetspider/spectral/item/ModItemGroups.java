package net.tuffetspider.spectral.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tuffetspider.spectral.Spectral;
import net.tuffetspider.spectral.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup SPECTRAL_ITEMS_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(Spectral.MOD_ID,"spectral_item_group"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.SPECTRAL_DUST))
                    .displayName(Text.translatable("itemgroup.spectral.spectral_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.SPECTRAL_DUST);
                        entries.add(ModItems.SPECTRAL_GOGGLES);
                        entries.add(ModBlocks.SPECTRAL_BLOCK);
                    })






                    .build());
    public static void registerItemGroups(){


    }
}
