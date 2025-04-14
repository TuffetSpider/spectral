package net.tuffetspider.spectral.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.tuffetspider.spectral.attribute.ModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class SpectralVisionAttributeMixin {



    @ModifyReturnValue(at = @At("RETURN"),method = "createLivingAttributes")
    private static DefaultAttributeContainer.Builder init(DefaultAttributeContainer.Builder original) {
        return original.add(ModAttributes.SPECTRAL, 0);
    }
}
