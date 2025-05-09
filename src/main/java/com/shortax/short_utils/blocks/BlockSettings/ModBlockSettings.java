package com.shortax.short_utils.blocks.BlockSettings;

import com.shortax.short_utils.sounds.blocks.ModBlockSoundGroups;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
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
            .requiresTool()
            .sounds(BlockSoundGroup.DEEPSLATE).mapColor(MapColor.BLACK);
}
