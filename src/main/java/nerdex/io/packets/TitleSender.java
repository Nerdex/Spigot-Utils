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

import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TitleSender implements Listener {

    public static void sendTitle(Player player, String message){
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\": \""+message+"\"}"));
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(title);
    }
}
