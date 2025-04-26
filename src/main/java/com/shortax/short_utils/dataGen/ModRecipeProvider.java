package com.shortax.short_utils.dataGen;

import com.shortax.short_utils.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider{

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {

            @Override
            public void generate() {
                ShapedRecipeJsonBuilder.create(Registries.ITEM,RecipeCategory.REDSTONE,ModBlocks.OBSID_PRESSURE_PLATE)
                        .pattern("##").input('#',Blocks.OBSIDIAN)
                        .criterion(hasItem(Items.OBSIDIAN),conditionsFromItem(Items.OBSIDIAN))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Recipes";
    }
}

