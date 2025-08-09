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
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class SpectralLivingEntityMixin {


    @Shadow public abstract double getAttributeValue(RegistryEntry<EntityAttribute> attribute);

    @ModifyReturnValue(at = @At("RETURN"),method = "createLivingAttributes")
    private static DefaultAttributeContainer.Builder init(DefaultAttributeContainer.Builder original) {
        return original.add(ModAttributes.SPECTRAL, 0);
    }
    @Inject(at = @At("RETURN"),method ="canTarget(Lnet/minecraft/entity/LivingEntity;)Z", cancellable = true)
    private void init(LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
        if(target.getAttributeValue(ModAttributes.SPECTRAL)!=
                this.getAttributeValue(ModAttributes.SPECTRAL)){
            cir.setReturnValue(false);
        }
    }
    @WrapWithCondition(at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;pushAwayFrom(Lnet/minecraft/entity/Entity;)V"),method = "pushAwayFrom")
    private boolean stopPushingSpectralEntities(Entity instance, Entity entity){
        return !(entity instanceof LivingEntity livingEntity) || livingEntity.getAttributeValue(ModAttributes.SPECTRAL) == this.getAttributeValue(ModAttributes.SPECTRAL);

    }
    @Inject(at=@At("RETURN"),method="shouldRenderName", cancellable = true)
    private void disableSpectralNames(CallbackInfoReturnable<Boolean> cir){
        if(this.getAttributeValue(ModAttributes.SPECTRAL)==1){
            cir.setReturnValue(false);
        }

    }

}
