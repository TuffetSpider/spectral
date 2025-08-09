package net.tuffetspider.spectral.mixin;


import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import net.tuffetspider.spectral.attribute.ModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(MobEntity.class)
abstract public class SpectralMobEntityMixin extends LivingEntity {
    protected SpectralMobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(at = @At("RETURN"),method="isInAttackRange")
    private boolean disableAttackingSpectralEntities(boolean original,LivingEntity entity){
        if(entity instanceof LivingEntity livingEntity && this.getAttributeValue(ModAttributes.SPECTRAL)!=livingEntity.getAttributeValue(ModAttributes.SPECTRAL)){
            return false;
        } else return original;
    }


}