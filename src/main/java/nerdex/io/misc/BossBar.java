package nerdex.io.misc;/*
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
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BossBar {

    public static org.bukkit.boss.BossBar bossBar;

    public BossBar(String message, String color, String style, double progress){

        bossBar = Bukkit.createBossBar(message, BarColor.valueOf(color), BarStyle.valueOf(style), BarFlag.DARKEN_SKY);
        bossBar.setProgress(progress);
    }

    public static void hide(Player player){
        bossBar.setVisible(false);
        bossBar.removeAll();
    }

    public static void send(Player player, int delay){
        bossBar.addPlayer(player);
        bossBar.setVisible(true);
        Bukkit.getScheduler().runTaskLater((Plugin) Bukkit.getServer(), new Runnable() {
            @Override
            public void run() {
                hide(player);
            }
        }, delay);
    }
}
