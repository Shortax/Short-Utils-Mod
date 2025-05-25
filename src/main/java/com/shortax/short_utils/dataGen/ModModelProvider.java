/*
 *
 *  * This file is part of Short Utils mod
 *  *
 *  * Copyright (C) 2025 Shortax
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU Lesser General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  * GNU Lesser General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Lesser General Public License
 *  * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.shortax.short_utils.dataGen;

import com.shortax.short_utils.Initializers.ModBlocks;
import com.shortax.short_utils.Initializers.ModBlocksWithEntities;
import com.shortax.short_utils.Initializers.Utils;
import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blocks.FakeRedstoneBlocks.FakeTrapdoor;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.ModelVariantOperator;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

@SuppressWarnings("SameParameterValue")
public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        TextureMap obsidianTexture = TextureMap.all(Identifier.ofVanilla("block/obsidian"));


        //Obsidian Pressure Plate
        register_plate(ModBlocks.OBSID_PRESSURE_PLATE,blockStateModelGenerator,obsidianTexture);

        //colored Lamps
        Utils.applyToEach(ModBlocks.COLORED_REDSTONE_LAMPS.values(), block -> registerCustomLamp(block,blockStateModelGenerator));

        //combiner block
        register_multi_face_block_custom(ModBlocks.COMBINER_BLOCK,blockStateModelGenerator,false,true,true);

        //Fake Redstone Blocks
        registerFake_Trapdoor(ModBlocks.FAKE_OAK_TRAPDOOR,blockStateModelGenerator,TextureMap.all(ModBlocks.FAKE_OAK_TRAPDOOR.ORIGINAL));
        registerFake_Trapdoor(ModBlocks.FAKE_SPRUCE_TRAPDOOR,blockStateModelGenerator,TextureMap.all(ModBlocks.FAKE_SPRUCE_TRAPDOOR.ORIGINAL));
        registerFake_Trapdoor(ModBlocks.FAKE_IRON_TRAPDOOR,blockStateModelGenerator,TextureMap.all(ModBlocks.FAKE_IRON_TRAPDOOR.ORIGINAL));

        //Leaves Stairs
        String mod = "leaves_";
        Utils.applyToEach(ModBlocks.LEAVES_STAIRS, leafStair -> create_custom_stair(leafStair,blockStateModelGenerator,TextureMap.all(leafStair.baseBlock),mod,leafStair.tint));

        //Projector Block
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocksWithEntities.PROJECTOR_BLOCK);

    }

    private void create_custom_stair(Block stair, BlockStateModelGenerator blockStateModelGenerator, TextureMap parentTexture, String mod, int tintcolor)
    {

        Identifier innerIdent = INNER_STAIRS(mod).upload(stair,parentTexture,blockStateModelGenerator.modelCollector);
        Identifier straightIdent = STAIRS(mod).upload(stair,parentTexture,blockStateModelGenerator.modelCollector);
        Identifier outerIdent = OUTER_STAIRS(mod).upload(stair,parentTexture,blockStateModelGenerator.modelCollector);

        WeightedVariant innerVariant = BlockStateModelGenerator.createWeightedVariant(innerIdent);
        WeightedVariant straightVariant = BlockStateModelGenerator.createWeightedVariant(straightIdent);
        WeightedVariant outerVariant = BlockStateModelGenerator.createWeightedVariant(outerIdent);

        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stair,innerVariant,straightVariant,outerVariant));

        if(tintcolor == -1)
            blockStateModelGenerator.registerParentedItemModel(stair,straightIdent);
        else
            blockStateModelGenerator.registerTintedItemModel(stair,straightIdent,ItemModels.constantTintSource(tintcolor));
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

    @SuppressWarnings("unused")
    private static void register_multi_face_block_custom(Block block, BlockStateModelGenerator blockStateModelGenerator, boolean customParticle, boolean customFront, boolean customTop){
        String front = customFront ?        "_front" : "_side";
        String particle = customParticle ?  "_particle" : "_side";
        String top = customTop ?            "_top" :   "_side";
        String side =                       "_side";
        String bottom =                     "_bottom";

        register_multi_face_block(block, blockStateModelGenerator,particle,bottom,top,front,side);

    }

    /* KEY          ORIGINAL        INTERPRETATION
     * PARTICLE:    "_particle"
     * DOWN:        "_down"         //bottom
     * UP:          "_up"           //top
     * NORTH:       "_north"        //front
     * SOUTH:       "_south"        //south side
     * EAST:        "_east"         //east side
     * WEST:        "_west"         //west side
     */
    private static void register_multi_face_block(Block block, BlockStateModelGenerator blockStateModelGenerator, String... suffixes) {
        TextureMap texture = new TextureMap()
                .put(TextureKey.PARTICLE, TextureMap.getSubId(block, suffixes[0]))
                .put(TextureKey.BOTTOM, TextureMap.getSubId(block, suffixes[1]))
                .put(TextureKey.TOP, TextureMap.getSubId(block, suffixes[2]))
                .put(TextureKey.FRONT, TextureMap.getSubId(block, suffixes[3]))
                .put(TextureKey.SIDE,TextureMap.getSubId(block,suffixes[4]));

        WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(
                Models.ORIENTABLE.upload(block, texture, blockStateModelGenerator.modelCollector)
        );
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockModelDefinitionCreator.of(block, weightedVariant)
                        .coordinate(NORTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS())
        );
    }

    private static Model STAIRS(String mod)
    {
        return block(mod+"stairs", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    }

    private static Model INNER_STAIRS(String mod)
    {
        return block(mod+"inner_stairs", "_inner", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    }

    private static Model OUTER_STAIRS(String mod)
    {
        return block(mod+"outer_stairs", "_outer", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    }

    @SuppressWarnings("SameParameterValue")
    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.of(ShortUtils.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static Model block(String parent, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.of(ShortUtils.MOD_ID, "block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }

    private static BlockStateVariantMap<ModelVariantOperator> NORTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS()
    {
        return BlockStateVariantMap.operations(
                        Properties.HORIZONTAL_FACING
                )
                .register(Direction.EAST, BlockStateModelGenerator.ROTATE_Y_90)
                .register(Direction.SOUTH, BlockStateModelGenerator.ROTATE_Y_180)
                .register(Direction.WEST, BlockStateModelGenerator.ROTATE_Y_270)
                .register(Direction.NORTH, BlockStateModelGenerator.NO_OP);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
