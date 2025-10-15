package net.tuffetspider.spectral.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Ownable;
import net.tuffetspider.spectral.Spectral;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityRenderDispatcher.class)
public abstract class SpectralEntityRenderDispatcherMixin {

    @WrapWithCondition(at= @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderDispatcher;renderFire(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/entity/Entity;Lorg/joml/Quaternionf;)V"),method = "render")
   private boolean disableSpectralFire(EntityRenderDispatcher instance, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity, Quaternionf rotation){
      if(entity instanceof LivingEntity livingEntity && MinecraftClient.getInstance().player != null){
          return Spectral.isSpectral(livingEntity) == Spectral.isSpectral(MinecraftClient.getInstance().player);
        }

        return true;
           }

    @ModifyReturnValue(at=@At("RETURN"),method = "shouldRender")
    private boolean disableRendering(boolean original, Entity entity){
        if(MinecraftClient.getInstance().player!= null && entity instanceof Ownable ownable&& ownable.getOwner() instanceof LivingEntity owner){
            if(Spectral.isSpectral(owner)!=Spectral.isSpectral(MinecraftClient.getInstance().player)){
                return false;
            }

        }

        return original;
    }
}

