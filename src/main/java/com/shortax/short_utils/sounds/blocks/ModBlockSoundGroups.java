package com.shortax.short_utils.sounds.blocks;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class ModBlockSoundGroups {

    //volume, pitch, break, step, place, hit, fall
    public static final BlockSoundGroup PROJECTOR_BLOCK = new BlockSoundGroup(
            1.0F, 1.75F, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundEvents.BLOCK_STONE_STEP, SoundEvents.BLOCK_IRON_PLACE, SoundEvents.BLOCK_STONE_HIT, SoundEvents.BLOCK_STONE_FALL
    );


    public static final BlockSoundGroup COMBINER_BLOCK = new BlockSoundGroup(
            1.0F, 0.75F, SoundEvents.BLOCK_IRON_BREAK, SoundEvents.BLOCK_IRON_STEP, SoundEvents.BLOCK_IRON_PLACE, SoundEvents.BLOCK_IRON_HIT, SoundEvents.BLOCK_IRON_FALL
    );
}
