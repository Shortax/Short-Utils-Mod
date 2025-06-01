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

package com.shortax.short_utils.items;

import com.shortax.short_utils.Initializers.Utils;
import net.minecraft.item.Item;
import net.minecraft.util.Formatting;

public class useful_sword extends Item {
    public static Utils.custom_tooltip_bundle TOOLTIP_BUNDLE = new Utils.custom_tooltip_bundle("What does it do?")
            .addFormatting(Formatting.ITALIC)
            .addFormatting(Formatting.YELLOW);
    public useful_sword(Settings settings) {
        super(settings);
    }
}
