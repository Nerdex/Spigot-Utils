package nerdex.io.debug;/*
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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Debugger {

    private static final String IN_GAME_DEBUG = ChatColor.GREEN + ChatColor.BOLD.toString() + "[SPU-G-DEBUG] ";
    private static final String CONSOLE_DEBUG = "[SPU-C-DEBUG] ";
    private static final String EXCEPTION_CAUGHT_PREFIX = "[SPU-EXCEPTION-DEBUG] ";

    public static void sendInGameDebug(Player player, String igMessage){
        player.sendMessage(IN_GAME_DEBUG + igMessage);
    }
    public static void sendCosnoleMessage(String cMessage){
        Bukkit.getLogger().info(CONSOLE_DEBUG + cMessage);
    }
    public static void sendExceptionDebug(String eMessage){
        System.out.println(EXCEPTION_CAUGHT_PREFIX + eMessage);
    }

    public static void writeDebugFile(String content, String location){
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        DateFormat format = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        try {
            String fileMessage = content;

            fileWriter = new FileWriter(location + format.format(calendar));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
