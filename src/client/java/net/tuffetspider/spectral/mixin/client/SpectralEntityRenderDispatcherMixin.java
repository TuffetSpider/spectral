package net.tuffetspider.spectral.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.tuffetspider.spectral.attribute.ModAttributes;
import net.tuffetspider.spectral.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityRenderDispatcher.class)
public abstract class SpectralEntityRenderDispatcherMixin {



    @WrapWithCondition(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderDispatcher;renderHitbox(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/entity/Entity;FFFF)V"),method = "render")
    private boolean disableRenderOfSpectralEntities(MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta, float red, float green, float blue) {
        if(entity instanceof LivingEntity livingEntity && livingEntity.getAttributeValue(ModAttributes.SPECTRAL)!= MinecraftClient.getInstance().player.getAttributeValue(ModAttributes.SPECTRAL)){
        return false;
    } else return true;

    }
}

