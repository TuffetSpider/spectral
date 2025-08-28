package net.tuffetspider.spectral.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tuffetspider.spectral.Spectral;
import net.tuffetspider.spectral.attribute.ModAttributes;
import net.tuffetspider.spectral.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class SpectralPlayerEntityMixin extends LivingEntity {


    protected SpectralPlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(at=@At("RETURN"),method = "canInteractWithBlockAt")
    private boolean disableSpectralInteraction(boolean original, BlockPos pos){
        if(!Spectral.isSpectral(this)&&this.getWorld().getBlockState(pos).isOf(ModBlocks.SPECTRAL_BLOCK)){
            Spectral.LOGGER.info("test");
            return false;
        }

        return original;
    }
    @ModifyReturnValue(at=@At("RETURN"),method="shouldRenderName")
    private boolean disableSpectralNames(boolean original) {
        if (this.getAttributeValue(ModAttributes.SPECTRAL) == 1) {
            return false;
        } else return original;
    }
    }
