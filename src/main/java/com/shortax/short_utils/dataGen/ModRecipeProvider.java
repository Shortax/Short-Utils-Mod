package com.shortax.short_utils.dataGen;

import com.shortax.short_utils.Initializers.ModBlocks;
import com.shortax.short_utils.Initializers.ModTags;
import com.shortax.short_utils.Initializers.Utils;
import com.shortax.short_utils.ShortUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

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
                offerPressurePlateRecipe(ModBlocks.OBSID_PRESSURE_PLATE,Blocks.OBSIDIAN);


                Utils.applyToEach(ModBlocks.COLORED_REDSTONE_LAMPS.keySet(), color -> ShapelessRecipeJsonBuilder
                        .create(Registries.ITEM,RecipeCategory.REDSTONE,ModBlocks.COLORED_REDSTONE_LAMPS.get(color))
                        .input(Blocks.REDSTONE_LAMP,1)
                        .input(Utils.getDye(color),1)
                        .criterion(hasItem(Items.REDSTONE_LAMP),conditionsFromItem(Items.REDSTONE_LAMP))
                        .offerTo(exporter)
                );

                ShapelessRecipeJsonBuilder
                        .create(Registries.ITEM,RecipeCategory.REDSTONE,Blocks.REDSTONE_LAMP)
                        .input(ingredientFromTag(ModTags.Items.COL_RED_LAMP_ITEM))
                        .input(Items.REDSTONE)
                        .criterion("has_col_redstone_lamp",conditionsFromItem(Items.REDSTONE_LAMP))
                        .offerTo(exporter, String.valueOf(Identifier.of(ShortUtils.MOD_ID,"reverse_color_redstone_lamp")));

                create_reversible_recipe(RecipeCategory.REDSTONE,ModBlocks.FAKE_OAK_TRAPDOOR,ModBlocks.FAKE_OAK_TRAPDOOR.ORIGINAL,Items.PAPER,Items.REDSTONE);
                create_reversible_recipe(RecipeCategory.REDSTONE,ModBlocks.FAKE_SPRUCE_TRAPDOOR,ModBlocks.FAKE_SPRUCE_TRAPDOOR.ORIGINAL,Items.PAPER,Items.REDSTONE);
                create_reversible_recipe(RecipeCategory.REDSTONE,ModBlocks.FAKE_IRON_TRAPDOOR,ModBlocks.FAKE_IRON_TRAPDOOR.ORIGINAL,Items.PAPER,Items.REDSTONE);

                Utils.applyToEach(ModBlocks.LEAVES_STAIRS, leafStair ->
                        ShapedRecipeJsonBuilder
                                .create(Registries.ITEM,RecipeCategory.BUILDING_BLOCKS, leafStair , 4)
                                .input('#', leafStair.baseBlock)
                                .pattern("#  ")
                                .pattern("## ")
                                .pattern("###")
                                .criterion(hasItem(leafStair.baseBlock.asItem()),conditionsFromItem(leafStair.baseBlock))
                                .offerTo(exporter)
                );

                ShapedRecipeJsonBuilder
                        .create(Registries.ITEM,RecipeCategory.MISC,ModBlocks.COMBINER_BLOCK,1)
                        .input('#',Items.IRON_INGOT)
                        .input('S',Items.STICK)
                        .input('B', Items.BUCKET)
                        .input('D',Items.DIAMOND)
                        .input('L',Items.IRON_BLOCK)
                        .pattern("#S#")
                        .pattern("BDB")
                        .pattern("#L#")
                        .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                        .offerTo(exporter);
            }

            public void create_reversible_recipe(RecipeCategory category, ItemConvertible outputItem, ItemConvertible original, ItemConvertible converter, ItemConvertible reconverter){
                String[] p = ("reverse_" + outputItem.asItem().getName().getString().replace(" ", "_").toLowerCase()).split("\\.");
                String path = p[0].replace("block","") + p[2];

                ShapelessRecipeJsonBuilder
                        .create(Registries.ITEM,category,outputItem)
                        .input(original)
                        .input(converter)
                        .criterion(hasItem(original.asItem()),conditionsFromItem(original.asItem()))
                        .offerTo(exporter);

                ShapelessRecipeJsonBuilder
                        .create(Registries.ITEM,RecipeCategory.REDSTONE,original)
                        .input(outputItem)
                        .input(reconverter)
                        .criterion(hasItem(outputItem.asItem()),conditionsFromItem(outputItem))
                        .offerTo(exporter,path);
            }
        };
    }


    @Override
    public String getName() {
        return "Recipes CraftingRecips";
    }
}

