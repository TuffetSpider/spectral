package net.tuffetspider.spectral.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.tuffetspider.spectral.attribute.ModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class SpectralGameRendererMixin {



    @Inject(at= @At(value = "HEAD"),method = "onCameraEntitySet", cancellable = true)
    private void disableShaderDisabling(Entity entity, CallbackInfo ci){
            if(MinecraftClient.getInstance().player!=null&& MinecraftClient.getInstance().player.getAttributeValue(ModAttributes.SPECTRAL)==1){
                ci.cancel();
            }
    }

}
