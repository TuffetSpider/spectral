package net.tuffetspider.spectral.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.tuffetspider.spectral.attribute.ModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(Entity.class)
public abstract class SpectralEntityMixin {

    @Shadow public abstract World getEntityWorld();

    @Shadow public abstract int getId();

    @Inject(at = @At("HEAD"),method = "handleAttack", cancellable = true)
    private void disableAttackingForSpectralEntities(Entity attacker, CallbackInfoReturnable<Boolean> cir){
        if(this.getEntityWorld().getEntityById(this.getId()) instanceof LivingEntity livingEntity&&
                this.getEntityWorld().getEntityById(attacker.getId()) instanceof LivingEntity Attacker) {
            if (livingEntity.getAttributeValue(ModAttributes.SPECTRAL) != Attacker.getAttributeValue(ModAttributes.SPECTRAL)) {
                cir.setReturnValue(true);

            }

        }
        }

}
