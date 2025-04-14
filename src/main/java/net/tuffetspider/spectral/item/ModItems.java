package net.tuffetspider.spectral.item;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.tuffetspider.spectral.Spectral;
import net.tuffetspider.spectral.item.custom.SpectralDustItem;

public class ModItems {
    public static Item SPECTRAL_DUST;
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Spectral.MOD_ID, name), item);
    }



    public static void registerModItems() {
        SPECTRAL_DUST = registerItem("spectral_dust", new SpectralDustItem(new Item.Settings().rarity(Rarity.UNCOMMON)));
    }
}
