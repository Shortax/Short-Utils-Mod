package com.shortax.short_utils.dataGen;

import com.shortax.short_utils.blocks.ModBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.Properties;
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
                        BlockStateModelGenerator.createWeightedVariant(ObsidPlateDownID
                )));

        blockStateModelGenerator.registerParentedItemModel(ModBlocks.OBSID_PRESSURE_PLATE, ObsidPlateUpID);

        ModBlocks.applyToEach(ModBlocks.COLORED_REDSTONE_LAMPS.values(),block -> registerCustomLamp(block,blockStateModelGenerator));

    }

    private void registerCustomLamp(Block b, BlockStateModelGenerator blockStateModelGenerator) {
        WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(TexturedModel.CUBE_ALL.upload(b, blockStateModelGenerator.modelCollector));
        WeightedVariant weightedVariant2 = BlockStateModelGenerator.createWeightedVariant(blockStateModelGenerator.createSubModel(b, "_on", Models.CUBE_ALL, TextureMap::all));

        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockModelDefinitionCreator.of(b).with(BlockStateModelGenerator.createBooleanModelMap(Properties.LIT,
                        weightedVariant2,
                        weightedVariant
                )));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
