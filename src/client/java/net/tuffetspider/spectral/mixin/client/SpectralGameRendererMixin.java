package net.tuffetspider.spectral.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.tuffetspider.spectral.attribute.ModAttributes;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class SpectralGameRendererMixin {

    @Shadow protected abstract void loadPostProcessor(Identifier id);

    @Shadow private @Nullable PostEffectProcessor postProcessor;

    @Inject(at=@At("TAIL"),method = "onCameraEntitySet")
    private void disableShaderDisabling(Entity entity, CallbackInfo ci){
        if(MinecraftClient.getInstance().player!=null){
        if(MinecraftClient.getInstance().player.getAttributeValue(ModAttributes.SPECTRAL)==1){
        this.loadPostProcessor(Identifier.ofVanilla("shaders/post/invert.json"));
            }
        }
    }
}
