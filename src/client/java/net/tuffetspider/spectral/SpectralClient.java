package net.tuffetspider.spectral;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.tuffetspider.spectral.attribute.ModAttributes;
import net.tuffetspider.spectral.mixin.client.WorldRendererAccessor;
import net.tuffetspider.spectral.model.SpectralModelLoadingPlugin;

import java.io.IOException;

public class SpectralClient implements ClientModInitializer {
	public static void updateChunks(BlockPos pos) {

			MinecraftClient minecraft = MinecraftClient.getInstance();
			ClientWorld world = minecraft.world;
			WorldRenderer WorldRenderer = minecraft.worldRenderer;
			if (world != null) {
				 minecraft.worldRenderer.reload();
				ChunkSectionPos.forEachChunkSectionAround(pos, chunk -> ((WorldRendererAccessor) WorldRenderer).spectral$scheduleChunkRender(
								ChunkSectionPos.unpackX(chunk),
								ChunkSectionPos.unpackY(chunk),
								ChunkSectionPos.unpackZ(chunk),
								true
						));
			}
	}


	private double previousSpectral = 0;
	@Override
	public void onInitializeClient() {
		ModelLoadingPlugin.register(new SpectralModelLoadingPlugin());
		ClientTickEvents.START_CLIENT_TICK.register(minecraft -> {
			if (minecraft.player == null) return;
			if (minecraft.player.getAttributeValue(ModAttributes.SPECTRAL)!=previousSpectral){
                for(ChunkBuilder.BuiltChunk chunk : minecraft.worldRenderer.chunks.chunks){
					chunk.scheduleRebuild(true);
				}
			}

			previousSpectral = minecraft.player.getAttributeValue(ModAttributes.SPECTRAL);
		});
	}
}

