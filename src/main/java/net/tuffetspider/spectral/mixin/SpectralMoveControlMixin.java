package net.tuffetspider.spectral.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.control.Control;
import net.minecraft.entity.ai.control.JumpControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.tuffetspider.spectral.Spectral;
import net.tuffetspider.spectral.attribute.ModAttributes;
import net.tuffetspider.spectral.block.ModBlocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MoveControl.class)
public abstract class SpectralMoveControlMixin implements Control {


    @Shadow @Final protected MobEntity entity;


    @ModifyExpressionValue(at= @At(value = "INVOKE", target = "Lnet/minecraft/util/shape/VoxelShape;isEmpty()Z"),method = "tick")
    private boolean makeVoxelShapesWork(boolean original){
        BlockPos blockPos = this.entity.getBlockPos();
        BlockState blockState = this.entity.getWorld().getBlockState(blockPos);

        return blockState.isOf(ModBlocks.SPECTRAL_BLOCK) && this.entity.getAttributeValue(ModAttributes.SPECTRAL) == 0;


    }
    @WrapWithCondition(at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/control/JumpControl;setActive()V"),method = "tick")
    private boolean stopJumping(JumpControl instance){
        BlockPos blockPos = this.entity.getBlockPos();
        BlockState blockState = this.entity.getWorld().getBlockState(blockPos);

        if(blockState.isOf(ModBlocks.SPECTRAL_BLOCK) && !Spectral.isSpectral(this.entity)){
            return false;
        }
        if(blockState.isOf(ModBlocks.COLD_IRON_BLOCK) && Spectral.isSpectral(this.entity)){
            return false;
        }
        else return true;



    }
}
