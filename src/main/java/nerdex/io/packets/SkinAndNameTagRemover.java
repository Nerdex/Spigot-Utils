package nerdex.io.packets;/*
 * SpigotUtils
 * Copyright (C) 2017 RapidTheNerd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import io.netty.channel.*;
import net.minecraft.server.v1_11_R1.PacketPlayOutPlayerInfo;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class SkinAndNameTagRemover implements Listener {

    public static void removeSkinAndTag(Player player){
        Channel channel = ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel;
        channel.eventLoop().submit(() ->
        channel.pipeline().remove(player.getName()));
    }

    public static void createHandler(Player player) {
        ChannelDuplexHandler handler = new ChannelDuplexHandler() {

            @Override
            public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
                super.channelRead(context, packet);
            }
            @Override
            public void write(ChannelHandlerContext context, Object packet, ChannelPromise promise) throws Exception {
                if(packet instanceof PacketPlayOutPlayerInfo){
                    PacketPlayOutPlayerInfo info = (PacketPlayOutPlayerInfo) packet;
                    return;
                }
                super.write(context, packet, promise);
            }
        };

        ChannelPipeline pipeline = ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), handler);
    }
}
