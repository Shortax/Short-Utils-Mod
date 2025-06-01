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
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class ModPayloads {

    static class Packets{
        public static final Identifier PROJECTOR_SCREEN_PACKET_ID = Identifier.of(ShortUtils.MOD_ID, "projector_variables_packet");
    }

    public record Projector_Variables_Payload(int radius,int thickness, int transparency) implements CustomPayload {
        public static final CustomPayload.Id<Projector_Variables_Payload> ID = new CustomPayload.Id<>(Packets.PROJECTOR_SCREEN_PACKET_ID);
        public static final PacketCodec<RegistryByteBuf, Projector_Variables_Payload> CODEC = PacketCodec.tuple(
                PacketCodecs.INTEGER, Projector_Variables_Payload::radius,
                PacketCodecs.INTEGER, Projector_Variables_Payload::thickness,
                PacketCodecs.INTEGER,Projector_Variables_Payload::transparency,
                Projector_Variables_Payload::new
        );

        @Override
        public CustomPayload.Id<? extends CustomPayload> getId() {
            return ID;
        }
    }

    public static void init()
    {
        PayloadTypeRegistry.playC2S().register(Projector_Variables_Payload.ID,Projector_Variables_Payload.CODEC);
    }
}
