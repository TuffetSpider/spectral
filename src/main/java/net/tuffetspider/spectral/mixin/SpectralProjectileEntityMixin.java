package net.tuffetspider.spectral.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.tuffetspider.spectral.attribute.ModAttributes;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProjectileEntity.class)
public abstract class SpectralProjectileEntityMixin {


    @Shadow @Nullable private Entity owner;

    @Inject(at = @At("RETURN"),method = "canHit(Lnet/minecraft/entity/Entity;)Z", cancellable = true)
    private void init(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if(entity instanceof LivingEntity livingEntity
                && owner instanceof LivingEntity livingOwner){
            if(livingEntity.getAttributeValue(ModAttributes.SPECTRAL)!=
            livingOwner.getAttributeValue(ModAttributes.SPECTRAL)){
                cir.setReturnValue(false);
            }
        }

        }

    }


