package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.items.ItemSettings.ModItemSettings;
import com.shortax.short_utils.items.projected_remover;
import com.shortax.short_utils.items.useful_sword;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModItems {

    public static String PROJECTED_REMOVER_ID = "projected_remover";
    public static projected_remover PROJECTED_REMOVER;

    public static String USEFUL_SWORD_ID = "useful_sword";
    public static useful_sword USEFUL_SWORD;

    public static List<ItemConvertible> itemsListCustomTab;

    public static Map<Item, Utils.custom_tooltip_bundle> tooltip;

    public static void init()
    {
        List<ItemConvertible> itemsListRedstone = new ArrayList<>();
        List<ItemConvertible> itemsListFunctional = new ArrayList<>();
        List<ItemConvertible> itemsListTools = new ArrayList<>();
        List<ItemConvertible> itemsListCombat = new ArrayList<>();
        List<ItemConvertible> itemsListIngredients = new ArrayList<>();

        itemsListCustomTab = new ArrayList<>();

        tooltip = new HashMap<>();


        PROJECTED_REMOVER = (projected_remover) Utils.registerItem_C(PROJECTED_REMOVER_ID,projected_remover::new,ModItemSettings.PROJECTED_REMOVER);
        itemsListCustomTab.add(PROJECTED_REMOVER);
        tooltip.put(PROJECTED_REMOVER,projected_remover.TOOLTIP_BUNDLE);

        USEFUL_SWORD = (useful_sword) Utils.registerItem_C(USEFUL_SWORD_ID,useful_sword::new,ModItemSettings.USEFUL_SWORD);
        itemsListCustomTab.add(USEFUL_SWORD);
        tooltip.put(USEFUL_SWORD,useful_sword.TOOLTIP_BUNDLE);


        itemsListCustomTab.addAll(itemsListRedstone);
        itemsListCustomTab.addAll(itemsListFunctional);
        itemsListCustomTab.addAll(itemsListTools);
        itemsListCustomTab.addAll(itemsListCombat);
        itemsListCustomTab.addAll(itemsListIngredients);

        Utils.Log_registering("Items", ShortUtils.MOD_ID);
        Utils.registerItemGroupEntry(ItemGroups.REDSTONE,itemsListRedstone);
        Utils.registerItemGroupEntry(ItemGroups.FUNCTIONAL,itemsListFunctional);
        Utils.registerItemGroupEntry(ItemGroups.TOOLS,itemsListTools);
        Utils.registerItemGroupEntry(ItemGroups.COMBAT,itemsListCombat);
        Utils.registerItemGroupEntry(ItemGroups.INGREDIENTS,itemsListIngredients);

    }
}
