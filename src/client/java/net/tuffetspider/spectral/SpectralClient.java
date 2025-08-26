package net.tuffetspider.spectral;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.tuffetspider.spectral.attribute.ModAttributes;
import net.tuffetspider.spectral.mixin.client.WorldRendererAccessor;
import net.tuffetspider.spectral.model.SpectralModelLoadingPlugin;

public class SpectralClient implements ClientModInitializer {

	private double previousSpectral = 0;
	@Override
	public void onInitializeClient() {
		ModelLoadingPlugin.register(new SpectralModelLoadingPlugin());
		ClientTickEvents.START_CLIENT_TICK.register(minecraft -> {
			if (minecraft.player == null) return;
			//if(minecraft.player.getAttributeValue(ModAttributes.SPECTRAL)==1) {
				//if (minecraft.gameRenderer.getPostProcessor() == null) {
				//	minecraft.gameRenderer.loadPostProcessor(Identifier.ofVanilla("shaders/post/invert.json"));
				//}
			//}
			if (minecraft.player.getAttributeValue(ModAttributes.SPECTRAL)!=previousSpectral){
			//	minecraft.gameRenderer.disablePostProcessor();
                for(ChunkBuilder.BuiltChunk chunk : minecraft.worldRenderer.chunks.chunks){
					chunk.scheduleRebuild(true);
				}
			}

			previousSpectral = minecraft.player.getAttributeValue(ModAttributes.SPECTRAL);
		});
	}
}

