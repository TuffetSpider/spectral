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

@Mixin(Entity.class)
public abstract class SpectralMixin {

    @Shadow public abstract World getEntityWorld();

    @Shadow public abstract int getId();
    @ModifyReturnValue(at = @At("TAIL"),method = "isInvisible")
    private boolean disableRenderOfSpectralEntities(boolean original) {
        if(this.getEntityWorld().getEntityById(this.getId()) instanceof LivingEntity livingEntity &&
                livingEntity.getAttributeValue(ModAttributes.SPECTRAL)==1){
            return true;
        }
        return original;
    }
    @Inject(at= @At("TAIL"),method = "isInvisibleTo", cancellable = true)
    private void renderForSpectralPlayers(PlayerEntity player, CallbackInfoReturnable<Boolean> cir){
    if(this.getEntityWorld().getEntityById(this.getId()) instanceof LivingEntity livingEntity &&
            livingEntity.getAttributeValue(ModAttributes.SPECTRAL)==1){
        cir.setReturnValue(false);
    }
    }
}
