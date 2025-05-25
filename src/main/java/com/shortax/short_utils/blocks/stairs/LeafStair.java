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

package com.shortax.short_utils.blocks.stairs;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.FoliageColors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeafStair extends StairsBlock {

    public final String ID;
    public final Block baseBlock;
    public final int tint;
    private static final Settings DEFAULT_SETTINGS = AbstractBlock.Settings.create()
            .pistonBehavior(PistonBehavior.NORMAL)
            .burnable().sounds(BlockSoundGroup.GRASS)
            .suffocates(Blocks::never)
            .strength(0.2F)
            .mapColor(MapColor.DARK_GREEN)
            .allowsSpawning(Blocks::never)
            .nonOpaque()
            .blockVision(Blocks::never)
            .solidBlock(Blocks::never);
    private static final Map<String, Block> allLeaves = Map.of(
            "oak_leaves_stairs", Blocks.OAK_LEAVES,
            "spruce_leaves_stairs", Blocks.SPRUCE_LEAVES,
            "birch_leaves_stairs", Blocks.BIRCH_LEAVES,
            "jungle_leaves_stairs", Blocks.JUNGLE_LEAVES,
            "acacia_leaves_stairs", Blocks.ACACIA_LEAVES,
            "dark_oak_leaves_stairs", Blocks.DARK_OAK_LEAVES,
            "mangrove_leaves_stairs", Blocks.MANGROVE_LEAVES,
            "cherry_leaves_stairs", Blocks.CHERRY_LEAVES,
            "azalea_leaves_stairs", Blocks.AZALEA_LEAVES,
            "flowering_azalea_leaves_stairs", Blocks.FLOWERING_AZALEA_LEAVES
    );

    private static final Map<String, Integer> allTints = Map.of(
            "oak_leaves_stairs", FoliageColors.DEFAULT,
            "spruce_leaves_stairs", FoliageColors.SPRUCE,
            "birch_leaves_stairs", FoliageColors.BIRCH,
            "jungle_leaves_stairs", FoliageColors.DEFAULT,
            "acacia_leaves_stairs", FoliageColors.DEFAULT,
            "dark_oak_leaves_stairs", FoliageColors.DEFAULT,
            "mangrove_leaves_stairs", FoliageColors.MANGROVE,
            "cherry_leaves_stairs", -1,
            "azalea_leaves_stairs", -1,
            "flowering_azalea_leaves_stairs", -1
    );

    public static List<LeafStair> get_leaves_stairs() {
        List<LeafStair> list = new ArrayList<>();
        for(String key : allLeaves.keySet())
        {
            Block b = allLeaves.get(key);
            list.add(new LeafStair(b,DEFAULT_SETTINGS.registryKey(getBlockReg(Identifier.of(key))),key,allTints.get(key)));
        }
        return list;
    }

    private static RegistryKey<Block> getBlockReg(Identifier ident)
    {
        return RegistryKey.of(RegistryKeys.BLOCK,ident);
    }

    public static AbstractBlock.Settings copy(Block block) {
        return AbstractBlock.Settings.copy(block);
    }

    public LeafStair(Block baseBlock, Settings settings, String id, int tintColor) {
        super(baseBlock.getDefaultState(), settings);
        this.ID = id;
        this.baseBlock = baseBlock;
        this.tint = tintColor;
    }
}
