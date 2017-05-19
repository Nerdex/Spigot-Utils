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

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class EzConfig {

    private File file;
    private FileConfiguration config;

    public EzConfig(String path, String fileName) {
        if (!fileName.contains(".yml")) {
            fileName = fileName + ".yml";
        }

        file = new File(path, fileName);
        config = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            config.options().copyDefaults(true);

            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
