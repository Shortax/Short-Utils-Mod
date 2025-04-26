package com.shortax.short_utils.blocks.cRedstoneLamp;


import com.shortax.short_utils.ShortUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class colRedstoneLamp {

    static String[] COLORS = {
            "white",
            "gray",
            "brown",
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "purple",
            "pink",
            "cyan",
            "lime"
    };


    public static Map<String, Block> init(String MOD_ID)
    {
        Map<String, Block> lamps = new HashMap<>();


        for(String col : COLORS)
        {
            Identifier ident = Identifier.of(ShortUtils.MOD_ID,getID(col));
            RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK,ident);
            lamps.put(col, new cRLamp(AbstractBlock.Settings.copy(Blocks.REDSTONE_BLOCK),col,key));
        }

        return lamps;
    }

    public static Identifier getIdent(String ModID, String color) { return Identifier.of(ModID, getID(color)); }
    public static String getID(String color) { return color+ "_redstone_lamp"; }

}

class cRLamp extends RedstoneLampBlock
{
    public String Color;
    public String ID;
    public cRLamp(Settings settings, String Color, RegistryKey<Block> key) {
        super(settings.registryKey(key));
        this.Color = Color;
        this.ID = this.Color + "_redstone_lamp";
    }
}
