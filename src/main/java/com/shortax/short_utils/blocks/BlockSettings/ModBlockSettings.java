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

package com.shortax.short_utils.blocks.BlockSettings;

import com.shortax.short_utils.sounds.blocks.ModBlockSoundGroups;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlockSettings {

    public static final AbstractBlock.Settings PROJECTOR_BLOCK= AbstractBlock.Settings
            .create()
            .strength(1.75F, 6.0F)
            .pistonBehavior(PistonBehavior.BLOCK)
            .requiresTool()
            .mapColor(MapColor.PALE_PURPLE)
            .sounds(ModBlockSoundGroups.PROJECTOR_BLOCK);

    public static final AbstractBlock.Settings COMBINER_BLOCK = AbstractBlock.Settings.create()
            .strength(3.2F, 6.0F)
            .sounds(ModBlockSoundGroups.COMBINER_BLOCK)
            .mapColor(MapColor.YELLOW)
            .requiresTool();

    public static final AbstractBlock.Settings OBSID_PLATE = AbstractBlock.Settings
            .create()
            .strength(2F,1200F)
            .noCollision()
            .allowsSpawning(Blocks::never)
            .requiresTool()
            .sounds(BlockSoundGroup.DEEPSLATE).mapColor(MapColor.BLACK);

    public static final AbstractBlock.Settings GEN_PROJECTED_BLOCK = AbstractBlock.Settings
            .create()
            .noCollision()
            .dropsNothing()
            .noBlockBreakParticles()
            .nonOpaque()
            .replaceable()
            .sounds(ModBlockSoundGroups.GEN_PROJECTED_BLOCK)
            .allowsSpawning(Blocks::never)
            .solidBlock(Blocks::never)
            .suffocates(Blocks::never)
            .blockVision(Blocks::never)
            .air();

    public static final AbstractBlock.Settings STAR_BLOCK_LIGHT = AbstractBlock.Settings
            .create()
            .requiresTool()
            .instrument(NoteBlockInstrument.BELL)
            .strength(1.8F).mapColor(MapColor.GRAY).luminance(state -> 10);
}
