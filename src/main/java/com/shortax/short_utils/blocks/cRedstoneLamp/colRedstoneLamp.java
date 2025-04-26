package com.shortax.short_utils.blocks.cRedstoneLamp;


import com.shortax.short_utils.ShortUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class colRedstoneLamp {

    public static String[] COLORS = {
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
            lamps.put(col, new cRLamp(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP),col,key));
        }

        return lamps;
    }

    public static Identifier getIdent(String ModID, String color) { return Identifier.of(ModID, getID(color)); }
    public static String getID(String color) { return color+ "_redstone_lamp"; }

    public static Item getDye(String color) {
        return switch (color) {
            case "white" -> Items.WHITE_DYE;
            case "orange" -> Items.ORANGE_DYE;
            case "magenta" -> Items.MAGENTA_DYE;
            case "light_blue" -> Items.LIGHT_BLUE_DYE;
            case "yellow" -> Items.YELLOW_DYE;
            case "lime" -> Items.LIME_DYE;
            case "pink" -> Items.PINK_DYE;
            case "gray" -> Items.GRAY_DYE;
            case "light_gray" -> Items.LIGHT_GRAY_DYE;
            case "cyan" -> Items.CYAN_DYE;
            case "purple" -> Items.PURPLE_DYE;
            case "blue" -> Items.BLUE_DYE;
            case "brown" -> Items.BROWN_DYE;
            case "green" -> Items.GREEN_DYE;
            case "red" -> Items.RED_DYE;
            case "black" -> Items.BLACK_DYE;
            default -> Items.WHITE_DYE; // or throw exception if needed
        };
    }
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
