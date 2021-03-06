package nerdex.io.config;/*
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
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public class ObtainLocation {

    public static Location getLocationFromConfig(ConfigurationSection section, boolean pitchAndYaw){
        try {
            Location location = new Location(
                    Bukkit.getServer().getWorld(section.getString("world")),
                    section.getDouble("x"),
                    section.getDouble("y"),
                    section.getDouble("z")
            );

            if(pitchAndYaw){
                location.setPitch((float) section.getDouble("pich"));
                location.setYaw((float) section.getDouble("yaw"));
        }
        return location;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
