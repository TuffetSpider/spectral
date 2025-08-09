package net.tuffetspider.spectral.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.tuffetspider.spectral.effect.ModEffects;

public class ModFoodComponents {

    public static final FoodComponent SPECTRAL_DUST = new FoodComponent.Builder().alwaysEdible().nutrition(0).saturationModifier(0).statusEffect(new StatusEffectInstance(ModEffects.SPECTRAL,100,0,true,false),1).build();
}
