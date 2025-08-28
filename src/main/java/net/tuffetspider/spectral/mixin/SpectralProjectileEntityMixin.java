package net.tuffetspider.spectral.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.tuffetspider.spectral.attribute.ModAttributes;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ProjectileEntity.class)
public abstract class SpectralProjectileEntityMixin {


    @Shadow @Nullable private Entity owner;

    @ModifyReturnValue(at = @At("RETURN"),method = "canHit(Lnet/minecraft/entity/Entity;)Z")
    private boolean init(boolean original, Entity entity) {
        if(entity instanceof LivingEntity livingEntity
                && owner instanceof LivingEntity livingOwner){
            if(livingEntity.getAttributeValue(ModAttributes.SPECTRAL)!=
            livingOwner.getAttributeValue(ModAttributes.SPECTRAL)){
                return false;
            }
        }

        return original;
    }

    }


