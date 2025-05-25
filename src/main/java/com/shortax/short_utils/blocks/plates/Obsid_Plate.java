package com.shortax.short_utils.blocks.plates;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Obsid_Plate extends PressurePlateBlock{
    private static final BlockSetType type = BlockSetType.STONE;


    public Obsid_Plate(Settings settings) {

        super(type, settings);
    }

    @Override
    protected int getRedstoneOutput(World world, BlockPos pos) {
        int i = getEntityCount(world, BOX.offset(pos), PlayerEntity.class);
        if (i > 0)
            return 15;
         else
            return 0;
    }

}
