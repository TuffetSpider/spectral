package net.tuffetspider.spectral.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.tuffetspider.spectral.block.ModBlocks;
import net.tuffetspider.spectral.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        offerShapelessRecipe(recipeExporter, ModItems.SPECTRAL_DUST,Items.PHANTOM_MEMBRANE, "spectral",2);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPECTRAL_BLOCK.asItem(),4)
                .pattern("DGD")
                .pattern("G G")
                .pattern("DGD")
                .input('D',ModItems.SPECTRAL_DUST)
                .input('G',Items.GLASS)
                .criterion(hasItem(ModItems.SPECTRAL_DUST),conditionsFromItem(ModItems.SPECTRAL_DUST))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.SPECTRAL_GOGGLES)
                .pattern("sSs")
                .pattern("IMI")
                .pattern("   ")
                .input('s',ModBlocks.SPECTRAL_BLOCK.asItem())
                .input('S',Items.STRING)
                .input('I',Items.IRON_INGOT)
                .input('M',Items.PHANTOM_MEMBRANE)
                .criterion(hasItem(Items.PHANTOM_MEMBRANE),conditionsFromItem(Items.PHANTOM_MEMBRANE))
                .offerTo(recipeExporter);

    }
}
