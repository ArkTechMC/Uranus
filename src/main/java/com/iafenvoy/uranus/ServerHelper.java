package com.iafenvoy.uranus;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ServerHelper {
    @Nullable
    public static MinecraftServer server = null;

    public static void sendToAll(Identifier id, PacketByteBuf buf) {
        if (server != null)
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList())
                ServerPlayNetworking.send(player, id, buf);
    }
}
