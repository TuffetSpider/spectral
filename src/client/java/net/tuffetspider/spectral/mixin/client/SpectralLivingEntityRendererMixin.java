package net.tuffetspider.spectral.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.tuffetspider.spectral.attribute.ModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public abstract class SpectralLivingEntityRendererMixin {
    @Inject(at = @At("HEAD"),method = "getRenderLayer", cancellable = true)
    private void disableRenderOfSpectralEntities(LivingEntity entity, boolean showBody, boolean translucent, boolean showOutline, CallbackInfoReturnable<RenderLayer> cir){
        if( MinecraftClient.getInstance().player!=null&&
                MinecraftClient.getInstance().player.getAttributeValue(ModAttributes.SPECTRAL)
                !=entity.getAttributeValue(ModAttributes.SPECTRAL)){
            cir.setReturnValue(null);
        }
    }
    @ModifyReturnValue(at = @At("TAIL"),method="getShadowRadius(Lnet/minecraft/entity/LivingEntity;)F")
    private float disableShadowForSpectral(float original, LivingEntity livingEntity){
        if( MinecraftClient.getInstance().player!=null&&
                MinecraftClient.getInstance().player.getAttributeValue(ModAttributes.SPECTRAL)
                !=livingEntity.getAttributeValue(ModAttributes.SPECTRAL)){
            return 0.0F;
        }

        return original;
    }
    @WrapWithCondition(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",at= @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/feature/FeatureRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/Entity;FFFFFF)V"))
    private boolean disableFeaturesOnSpectralEntities(FeatureRenderer instance, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, Entity t, float p, float o, float g, float n, float k, float m){
        return !( MinecraftClient.getInstance().player!=null&&
                t instanceof LivingEntity livingEntity) || livingEntity.getAttributeValue(ModAttributes.SPECTRAL)
                == MinecraftClient.getInstance().player.getAttributeValue(ModAttributes.SPECTRAL);

    }

}

