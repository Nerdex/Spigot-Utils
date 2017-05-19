package nerdex.io.command;/*
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

import org.bukkit.ChatColor;

import java.lang.reflect.Method;
import java.util.*;

public class Manager {

    private static final String wildCard = "*";
    private static final String wildCardNumber = "**";
    private static final String either = "|";
    private static final String error = ChatColor.RED + "It dun goofed";
    private static final String permError = ChatColor.RED + "No permission";
    private static final String playerError = ChatColor.RED + "You're not a player, this can't be used for you";
    private static final String argError = ChatColor.RED + "Incorrect errors";

    public static void register(Listener... listeners){
        for(Listener listener : listeners){
            Class<?> clazz = listener.getClass();
            Method[] clazzMethods = clazz.getMethods();

            Map<String, Set<Method>> map = new HashMap<>();

            for(Method method : clazzMethods){
                if(!method.isAnnotationPresent(Command.class)) continue;

                List<String> names = new ArrayList<>();
                if(!method.getAnnotation(Command.class).name().isEmpty()){
                    Command command = method.getAnnotation(Command.class);

                    if(command.name().trim().contains(" ")){
                        names.addAll(new ArrayList<String>(Arrays.asList(command.name().trim().split(" "))));
                    } else {
                        names.add(command.name());
                    }
                }
            }
        }
    }
}
