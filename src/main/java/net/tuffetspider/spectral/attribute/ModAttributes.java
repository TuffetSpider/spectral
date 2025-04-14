package net.tuffetspider.spectral.attribute;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.tuffetspider.spectral.Spectral;

public class ModAttributes implements ModInitializer {
    public static RegistryEntry<EntityAttribute> SPECTRAL = register("spectral_vision",new ClampedEntityAttribute("attribute.spectral_vision",0,0,1)
                .setCategory(EntityAttribute.Category.NEUTRAL)
                .setTracked(true));
    private static RegistryEntry<EntityAttribute> register(String id, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(Spectral.MOD_ID,id), attribute);
    }


    @Override
    public void onInitialize() {

    }
}
