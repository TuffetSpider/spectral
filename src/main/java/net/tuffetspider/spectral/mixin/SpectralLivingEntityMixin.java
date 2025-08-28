package net.tuffetspider.spectral.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.entry.RegistryEntry;
import net.tuffetspider.spectral.attribute.ModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class SpectralLivingEntityMixin {


    @Shadow public abstract double getAttributeValue(RegistryEntry<EntityAttribute> attribute);

    @ModifyReturnValue(at = @At("RETURN"),method = "createLivingAttributes")
    private static DefaultAttributeContainer.Builder init(DefaultAttributeContainer.Builder original) {
        return original.add(ModAttributes.SPECTRAL, 0);
    }
    @ModifyReturnValue(at = @At("RETURN"),method ="canTarget(Lnet/minecraft/entity/LivingEntity;)Z")
    private boolean init(boolean original,LivingEntity target) {
        if(target.getAttributeValue(ModAttributes.SPECTRAL)!=
                this.getAttributeValue(ModAttributes.SPECTRAL)){
            return false;
        }
        return original;
    }
    @WrapWithCondition(at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;pushAwayFrom(Lnet/minecraft/entity/Entity;)V"),method = "pushAwayFrom")
    private boolean stopPushingSpectralEntities(Entity instance, Entity entity){
        return !(entity instanceof LivingEntity livingEntity) || livingEntity.getAttributeValue(ModAttributes.SPECTRAL) == this.getAttributeValue(ModAttributes.SPECTRAL);

    }
    @ModifyReturnValue(at=@At("RETURN"),method="shouldRenderName")
    private boolean disableSpectralNames(boolean original){
        if(this.getAttributeValue(ModAttributes.SPECTRAL)==1){
            return false;
        }

        return original;
    }

}
