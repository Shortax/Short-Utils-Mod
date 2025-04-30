package com.shortax.short_utils.blocks.stairs;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeafStair extends StairsBlock {

    public final String ID;
    public final Block baseBlock;
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

    public static List<LeafStair> get_leaves_stairs() {
        List<LeafStair> list = new ArrayList<>();
        for(String key : allLeaves.keySet())
        {
            Block b = allLeaves.get(key);
            list.add(new LeafStair(b,copy(b).registryKey(getBlockReg(Identifier.of(key))),key));
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

    public LeafStair(Block baseBlock, Settings settings, String id) {
        super(baseBlock.getDefaultState(), settings);
        this.ID = id;
        this.baseBlock = baseBlock;
    }
}
