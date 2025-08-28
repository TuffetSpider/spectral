package net.tuffetspider.spectral.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.tuffetspider.spectral.Spectral;
import net.tuffetspider.spectral.attribute.ModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public abstract class SpectralEntityMixin {

    @Shadow public abstract World getEntityWorld();

    @Shadow public abstract int getId();


    @ModifyReturnValue(at = @At("RETURN"),method = "handleAttack")
    private boolean disableAttackingForSpectralEntities(boolean original, Entity attacker){
        if(this.getEntityWorld().getEntityById(this.getId()) instanceof LivingEntity livingEntity&&
                this.getEntityWorld().getEntityById(attacker.getId()) instanceof LivingEntity Attacker) {
            if (livingEntity.getAttributeValue(ModAttributes.SPECTRAL) != Attacker.getAttributeValue(ModAttributes.SPECTRAL)) {
                return true;

            }

        }
        return original;
    }
    @ModifyReturnValue(at=@At("RETURN"),method = "isInvisibleTo")
    private boolean disableNameTagRenderingAndEnsureInvisibility(boolean original, PlayerEntity player){
        if(this.getEntityWorld().getEntityById(this.getId()) instanceof LivingEntity livingEntity){
            if(Spectral.isSpectral(livingEntity)!= Spectral.isSpectral(player)){
                return true;
            }
        }
        return original;
    }

}
