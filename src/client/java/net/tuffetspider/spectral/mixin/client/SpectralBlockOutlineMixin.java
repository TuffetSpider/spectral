package net.tuffetspider.spectral.mixin.client;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.BlockBreakingInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.tuffetspider.spectral.attribute.ModAttributes;
import net.tuffetspider.spectral.block.ModBlocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class SpectralBlockOutlineMixin {


    @Shadow @Final private Int2ObjectMap<BlockBreakingInfo> blockBreakingInfos;

    @Inject(at = @At("HEAD"),method = "drawBlockOutline", cancellable = true)
    private void disableRenderOfSpectralEntities(MatrixStack matrices, VertexConsumer vertexConsumer, Entity entity, double cameraX, double cameraY, double cameraZ, BlockPos pos, BlockState state, CallbackInfo ci) {
        if(state.isOf(ModBlocks.SPECTRAL_BLOCK)&&entity instanceof PlayerEntity player && !(player.getAttributeValue(ModAttributes.SPECTRAL) ==1)) {
ci.cancel();
        }
        }
}

