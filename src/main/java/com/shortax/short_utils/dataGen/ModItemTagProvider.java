package com.shortax.short_utils.dataGen;

import com.shortax.short_utils.Initializers.ModBlocks;
import com.shortax.short_utils.Initializers.ModTags;
import com.shortax.short_utils.Initializers.Utils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {



    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        Utils.applyToEach(
                Utils.applyToEachReturn(
                        ModBlocks.COLORED_REDSTONE_LAMPS.values(),
                        Block::asItem), //->
                getOrCreateTagBuilder(ModTags.Items.COL_RED_LAMP_ITEM)::add
        );
    }
}
