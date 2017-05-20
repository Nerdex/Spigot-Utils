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

import nerdex.io.debug.Debugger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpectatorMode {

    private static boolean isSpec = false;

    public static void setPlayerInSpecMod(Player player){
        isSpec = true;
        hidePlayer(player);
        try {
            for(ItemStack is : player.getInventory().getContents()){
                if(is != null && is.getType() != Material.AIR){
                    Bukkit.getWorld(player.getWorld().getName()).dropItemNaturally(player.getLocation(), is);
                }
            }
        } catch (Exception e){
            Debugger.sendExceptionDebug("EXCEPTION CAUGHT");
            e.printStackTrace();
        }
        player.getInventory().clear();
        ItemStack stack = new ItemStack(Material.COMMAND);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "Teleport");
        stack.setItemMeta(meta);
        player.getInventory().setItem(0, stack);
        player.setAllowFlight(true);
        player.setFlying(true);
    }
    @EventHandler
    private static void cancelBlockBreak(BlockBreakEvent event){
        if(isSpec == true)
            event.setCancelled(true);
    }


    @EventHandler
    private static void cancelDamage(EntityDamageByEntityEvent event){
        if(isSpec == true && event.getEntity() instanceof Player)
            event.setCancelled(true);
    }


    @EventHandler
    private static void cancelBlockPlace(BlockPlaceEvent event){
        if(isSpec == true)
            event.setCancelled(true);
    }

    public static void hidePlayer(Player player){
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            p.hidePlayer(player);
        }
    }

    public static boolean isPlayerSpec() {
        return isSpec;
    }

    public static void setPlayerSpec(boolean isSpec) {
        SpectatorMode.isSpec = isSpec;
    }
}
