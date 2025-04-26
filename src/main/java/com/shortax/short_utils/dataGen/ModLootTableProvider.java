package com.shortax.short_utils.dataGen;

import com.shortax.short_utils.Initializers.ModBlockEntities;
import com.shortax.short_utils.Initializers.ModBlocks;
import com.shortax.short_utils.Initializers.Utils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.OBSID_PRESSURE_PLATE);
        addDrop(ModBlockEntities.COMBINED_BLOCK);
        Utils.applyToEach(ModBlocks.COLORED_REDSTONE_LAMPS.values(),this::addDrop);
    }
}
