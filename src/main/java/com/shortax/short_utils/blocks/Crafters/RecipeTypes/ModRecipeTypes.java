package com.shortax.short_utils.blocks.Crafters.RecipeTypes;

import com.shortax.short_utils.ShortUtils;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeTypes {
    public static RecipeType<CombiningRecipe> COMBINING = register("combining");

    @SuppressWarnings("SameParameterValue")
    static <T extends Recipe<?>> RecipeType<T> register(String id) {
        return Registry.register(Registries.RECIPE_TYPE, Identifier.of(ShortUtils.MOD_ID,id), new RecipeType<T>() {
            public String toString() {
                return id;
            }
        });
    }
}
