package net.tuffetspider.spectral;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.LivingEntity;
import net.tuffetspider.spectral.attribute.ModAttributes;
import net.tuffetspider.spectral.block.ModBlocks;
import net.tuffetspider.spectral.effect.ModEffects;
import net.tuffetspider.spectral.item.ModItemGroups;
import net.tuffetspider.spectral.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Spectral implements ModInitializer {
	public static final String MOD_ID = "spectral";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static boolean isSpectral(LivingEntity living){
        return living.getAttributeValue(ModAttributes.SPECTRAL) == 1;
	}
	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModEffects.registerModEffects();

	}
}