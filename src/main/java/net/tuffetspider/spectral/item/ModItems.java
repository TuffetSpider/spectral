package net.tuffetspider.spectral.item;

import net.fabricmc.api.ModInitializer;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.tuffetspider.spectral.Spectral;
import net.tuffetspider.spectral.attribute.ModAttributes;
import net.tuffetspider.spectral.effect.ModEffects;
import net.tuffetspider.spectral.item.custom.SpectralDustItem;
import net.tuffetspider.spectral.item.custom.SpectralGogglesItem;

import java.util.Collections;

public class ModItems {
    public static Item SPECTRAL_DUST;
    public static Item SPECTRAL_GOGGLES;
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Spectral.MOD_ID, name), item);
    }



    public static void registerModItems() {
        SPECTRAL_DUST = registerItem("spectral_dust", new SpectralDustItem(new Item.Settings().rarity(Rarity.UNCOMMON).food(ModFoodComponents.SPECTRAL_DUST)));
        SPECTRAL_GOGGLES = registerItem("spectral_goggles", new SpectralGogglesItem(ModArmorMaterials.SPECTRAL, ArmorItem.Type.HELMET,new Item.Settings().maxCount(1).attributeModifiers(AttributeModifiersComponent.builder().add(ModAttributes.SPECTRAL, new EntityAttributeModifier(Identifier.of(Spectral.MOD_ID,"spectral_goggle_vision"),1.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD).build())));
    }
}
