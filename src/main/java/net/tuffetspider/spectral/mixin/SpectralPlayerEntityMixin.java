package net.tuffetspider.spectral.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.tuffetspider.spectral.attribute.ModAttributes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class SpectralPlayerEntityMixin extends LivingEntity {


    protected SpectralPlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(at=@At("RETURN"),method="shouldRenderName")
    private boolean disableSpectralNames(boolean original) {
        if (this.getAttributeValue(ModAttributes.SPECTRAL) == 1) {
            return false;
        } else return original;
    }
    }
