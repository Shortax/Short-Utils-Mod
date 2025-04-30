package com.shortax.short_utils.dataGen;

import com.shortax.short_utils.Initializers.ModBlocks;
import com.shortax.short_utils.Initializers.Utils;
import com.shortax.short_utils.blocks.FakeRedstoneBlocks.FakeTrapdoor;
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
        TextureMap obsidianTexture = TextureMap.all(Identifier.ofVanilla("block/obsidian"));
        //TextureMap DEBUG_TEXTURE = TextureMap.all(Identifier.ofVanilla("block/black_concrete"));


        //Obsidian Pressure Plate
        register_plate(ModBlocks.OBSID_PRESSURE_PLATE,blockStateModelGenerator,obsidianTexture);

        //colored Lamps
        Utils.applyToEach(ModBlocks.COLORED_REDSTONE_LAMPS.values(), block -> registerCustomLamp(block,blockStateModelGenerator));

        //combined block entity
        //register_cube_test(ModBlockEntities.COMBINED_BLOCK,blockStateModelGenerator,DEBUG_TEXTURE);

        registerFake_Trapdoor(ModBlocks.FAKE_OAK_TRAPDOOR,blockStateModelGenerator,TextureMap.all(ModBlocks.FAKE_OAK_TRAPDOOR.ORIGINAL));
        registerFake_Trapdoor(ModBlocks.FAKE_SPRUCE_TRAPDOOR,blockStateModelGenerator,TextureMap.all(ModBlocks.FAKE_SPRUCE_TRAPDOOR.ORIGINAL));
        registerFake_Trapdoor(ModBlocks.FAKE_IRON_TRAPDOOR,blockStateModelGenerator,TextureMap.all(ModBlocks.FAKE_IRON_TRAPDOOR.ORIGINAL));

        Utils.applyToEach(ModBlocks.LEAF_STAIRS,leafStair -> create_custom_stair(leafStair,blockStateModelGenerator,TextureMap.all(leafStair.baseBlock)));
    }

    private void create_custom_stair(Block stair, BlockStateModelGenerator blockStateModelGenerator, TextureMap parentTexture)
    {
        Identifier innerIdent = Models.INNER_STAIRS.upload(stair,parentTexture,blockStateModelGenerator.modelCollector);
        Identifier straightIdent = Models.STAIRS.upload(stair,parentTexture,blockStateModelGenerator.modelCollector);
        Identifier outerIdent = Models.OUTER_STAIRS.upload(stair,parentTexture,blockStateModelGenerator.modelCollector);

        WeightedVariant innerVariant = BlockStateModelGenerator.createWeightedVariant(innerIdent);
        WeightedVariant straightVariant = BlockStateModelGenerator.createWeightedVariant(straightIdent);
        WeightedVariant outerVariant = BlockStateModelGenerator.createWeightedVariant(outerIdent);

        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stair,innerVariant,straightVariant,outerVariant));
        blockStateModelGenerator.registerParentedItemModel(stair,straightIdent);
    }

    private void registerFake_Trapdoor(FakeTrapdoor block, BlockStateModelGenerator blockStateModelGenerator, TextureMap parentTexture)
    {
        WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(Models.TEMPLATE_TRAPDOOR_TOP.upload(block, parentTexture, blockStateModelGenerator.modelCollector));

        Identifier identifier = Models.TEMPLATE_TRAPDOOR_BOTTOM.upload(block, parentTexture, blockStateModelGenerator.modelCollector);
        WeightedVariant middleVariant = BlockStateModelGenerator.createWeightedVariant(identifier);

        WeightedVariant weightedVariant2 = BlockStateModelGenerator.createWeightedVariant(Models.TEMPLATE_TRAPDOOR_OPEN.upload(block, parentTexture, blockStateModelGenerator.modelCollector));


        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createTrapdoorBlockState(block, weightedVariant, middleVariant, weightedVariant2));
        blockStateModelGenerator.registerParentedItemModel(block, identifier);
    }

    @SuppressWarnings("unused")
    private void register_cube_test(Block block, BlockStateModelGenerator blockStateModelGenerator, TextureMap parentTexture)
    {
        Identifier parent = Models.CUBE_ALL.upload(block,parentTexture,blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(block,parent);
    }

    private void register_plate(Block block, BlockStateModelGenerator blockStateModelGenerator, TextureMap parentTexture )
    {
        Identifier PlateDownID = Models.PRESSURE_PLATE_DOWN.upload(block,parentTexture,blockStateModelGenerator.modelCollector);
        Identifier PlateUpID = Models.PRESSURE_PLATE_UP.upload(block,parentTexture,blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(
                BlockStateModelGenerator.createPressurePlateBlockState(block,
                        BlockStateModelGenerator.createWeightedVariant(PlateUpID),
                        BlockStateModelGenerator.createWeightedVariant(PlateDownID
                        )));

        blockStateModelGenerator.registerParentedItemModel(block, PlateUpID);
    }

    private void registerCustomLamp(Block block, BlockStateModelGenerator blockStateModelGenerator) {
        WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(TexturedModel.CUBE_ALL.upload(block, blockStateModelGenerator.modelCollector));
        WeightedVariant weightedVariant2 = BlockStateModelGenerator.createWeightedVariant(blockStateModelGenerator.createSubModel(block, "_on", Models.CUBE_ALL, TextureMap::all));

        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockModelDefinitionCreator.of(block).with(BlockStateModelGenerator.createBooleanModelMap(Properties.LIT,
                        weightedVariant2,
                        weightedVariant
                )));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
