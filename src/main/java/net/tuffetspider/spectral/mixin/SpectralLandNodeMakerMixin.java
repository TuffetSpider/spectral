package net.tuffetspider.spectral.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeMaker;
import net.minecraft.util.math.BlockPos;
import net.tuffetspider.spectral.attribute.ModAttributes;
import net.tuffetspider.spectral.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LandPathNodeMaker.class)
public abstract class SpectralLandNodeMakerMixin extends PathNodeMaker {


    @ModifyReturnValue(at=@At("RETURN"),method = "canPathThrough")
    private boolean enableAndDisableSpectralPathing(boolean original, BlockPos pos){
        if(this.entity.getWorld().getBlockState(pos).isOf(ModBlocks.SPECTRAL_BLOCK)){
            return this.entity.getAttributeValue(ModAttributes.SPECTRAL) != 1;
        }

        return original;
    }



}
