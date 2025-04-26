package com.shortax.short_utils.dataGen;

import com.shortax.short_utils.blocks.ModBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        final TextureMap obsidianTexture = TextureMap.all(Identifier.ofVanilla("block/obsidian"));

        final Identifier ObsidPlateDownID = Models.PRESSURE_PLATE_DOWN.upload(ModBlocks.OBSID_PRESSURE_PLATE,obsidianTexture,blockStateModelGenerator.modelCollector);
        final Identifier ObsidPlateUpID = Models.PRESSURE_PLATE_UP.upload(ModBlocks.OBSID_PRESSURE_PLATE,obsidianTexture,blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(
                BlockStateModelGenerator.createPressurePlateBlockState(ModBlocks.OBSID_PRESSURE_PLATE,
                        BlockStateModelGenerator.createWeightedVariant(ObsidPlateUpID),
                        BlockStateModelGenerator.createWeightedVariant(ObsidPlateDownID)));

        blockStateModelGenerator.registerParentedItemModel(ModBlocks.OBSID_PRESSURE_PLATE, ObsidPlateUpID);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
