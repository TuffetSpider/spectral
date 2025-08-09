package net.tuffetspider.spectral.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.network.packet.s2c.play.TeamS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.tuffetspider.spectral.Spectral;
import net.tuffetspider.spectral.attribute.ModAttributes;

public class ModEffects {

    public static RegistryEntry<StatusEffect> SPECTRAL = register("spectral",new SpectralEffect().addAttributeModifier(ModAttributes.SPECTRAL,Identifier.of(Spectral.MOD_ID,"spectral_effect_attribute_add"),1, EntityAttributeModifier.Operation.ADD_VALUE));

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Spectral.MOD_ID, id), statusEffect);

    }


    public static void registerModEffects(){
        SPECTRAL = register("spectral",new SpectralEffect().addAttributeModifier(ModAttributes.SPECTRAL,Identifier.of(Spectral.MOD_ID,"spectral_effect_attribute_add"),1, EntityAttributeModifier.Operation.ADD_VALUE));

    }


}
