package com.shortax.short_utils.blocks.Crafters.RecipeTypes;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.IngredientPlacement;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public class CombiningRecipe implements Recipe<CraftingRecipeInput> {

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        return false;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return null;
    }

    @Override
    public RecipeSerializer<? extends Recipe<CraftingRecipeInput>> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<? extends Recipe<CraftingRecipeInput>> getType() {
        return null;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return null;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return null;
    }
}

class Combining_Registry{

}
