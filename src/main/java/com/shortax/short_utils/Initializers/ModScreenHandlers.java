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

package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blocks.blockEntities.Projector.ProjectorScreen.ProjectorScreenHandler;
import com.shortax.short_utils.blocks.Crafters.Combiner.CombinerScreen.CombinerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {

    public static final ScreenHandlerType<CombinerScreenHandler> COMBINER_SCREEN_HANDLER = Registry
            .register(
                    Registries.SCREEN_HANDLER,
                    Identifier.of(
                            ShortUtils.MOD_ID,
                            "combiner_screen_handler"
                    ),
                    new ScreenHandlerType<>(CombinerScreenHandler::new,
                            FeatureSet.empty())
            );

    public static final ScreenHandlerType<ProjectorScreenHandler> PROJECTOR_SCREEN_HANDLER = Registry
            .register(
                    Registries.SCREEN_HANDLER,
                    Identifier.of(
                            ShortUtils.MOD_ID,
                            "projector_screen_handler"
                    ),
                    new ExtendedScreenHandlerType<>(
                            ProjectorScreenHandler::new,
                            BlockPos.PACKET_CODEC)
            );


    public static void init()
    {

    }

}

