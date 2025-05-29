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

package com.shortax.short_utils.ServerHandling;

import com.shortax.short_utils.Initializers.ModPayLoads;
import com.shortax.short_utils.blocks.blockEntities.Projector.ProjectorScreen.ProjectorScreenHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ServerReceivers {

    public static void init()
    {
        ServerPlayNetworking.registerGlobalReceiver(ModPayLoads.Projector_Variables_Payload.ID,((payload, context) -> {
            context.server().execute( () -> {
                System.out.println(payload.radius());
                System.out.println(payload.thickness());
                System.out.println(payload.transparency());

                ((ProjectorScreenHandler)context.player().currentScreenHandler).setProperty(0,payload.radius());
                ((ProjectorScreenHandler)context.player().currentScreenHandler).setProperty(1,payload.thickness());
                ((ProjectorScreenHandler)context.player().currentScreenHandler).setProperty(2,payload.transparency());
            });
        }));
    }
}
