package net.tuffetspider.spectral.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNode;
import net.minecraft.entity.ai.pathing.PathNodeMaker;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tuffetspider.spectral.attribute.ModAttributes;
import net.tuffetspider.spectral.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(LandPathNodeMaker.class)
public abstract class SpectralLandNodeMakerMixin extends PathNodeMaker {


    @Inject(at=@At("RETURN"),method = "canPathThrough", cancellable = true)
    private void enableAndDisableSpectralPathing(BlockPos pos, CallbackInfoReturnable<Boolean> cir){
        if(this.entity.getWorld().getBlockState(pos).isOf(ModBlocks.SPECTRAL_BLOCK)){
            if(this.entity.getAttributeValue(ModAttributes.SPECTRAL)==1){
                cir.setReturnValue(true);
            } else cir.setReturnValue(false);
        }

    }



}
