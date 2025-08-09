package net.tuffetspider.spectral.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.tuffetspider.spectral.Spectral;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static final RegistryEntry<ArmorMaterial> SPECTRAL = registerArmorMaterials("spectral",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.HELMET,0);
                map.put(ArmorItem.Type.CHESTPLATE,0);
                map.put(ArmorItem.Type.LEGGINGS,0);
                map.put(ArmorItem.Type.BOOTS,0);

    }), 20, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, () -> Ingredient.ofItems(Items.PHANTOM_MEMBRANE), List.of(new ArmorMaterial.Layer(Identifier.of(Spectral.MOD_ID,"spectral"))),0,0));


    public static RegistryEntry<ArmorMaterial> registerArmorMaterials(String name, Supplier<ArmorMaterial> material){
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(Spectral.MOD_ID,name),material.get());



    }
}
