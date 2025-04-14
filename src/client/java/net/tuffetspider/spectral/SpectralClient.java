package net.tuffetspider.spectral;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.tuffetspider.spectral.model.SpectralModelLoadingPlugin;

public class SpectralClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModelLoadingPlugin.register(new SpectralModelLoadingPlugin());
	}
}