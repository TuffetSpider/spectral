package net.tuffetspider.spectral.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SpectralModelLoadingPlugin implements ModelLoadingPlugin {
    public static final ModelIdentifier SPECTRAL_BLOCK_MODEL = new ModelIdentifier(Identifier.of("spectral", "spectral_block"), "");
    public static final ModelIdentifier COLD_IRON_BLOCK_MODEL = new ModelIdentifier(Identifier.of("spectral","cold_iron_block"),"");
    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.modifyModelOnLoad().register((original, context) -> {
            final ModelIdentifier id = context.topLevelId();
            if(id != null) {
                if(id.equals(SPECTRAL_BLOCK_MODEL)) {
                    return new SpectralBlockModel();
                }
                if(id.equals(COLD_IRON_BLOCK_MODEL)){
                    return new ColdIronBlockModel();
                }

            }
            return original;
            })
    ;}
}
