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

public class Stopwatch {

    private static long startTime = 0;
    private static long endTime = 0;
    private static boolean running = false;

    public static void start(){
        startTime = System.currentTimeMillis();
        running = true;
    }

    public static void stop(){
        endTime = System.currentTimeMillis();
        running = false;
    }

    public static long getElapsedTime(){
        long elapsed;
        if(running){
            elapsed = (System.currentTimeMillis() - startTime);
        } else {
            elapsed = (endTime = startTime);
        }
        return elapsed;
    }

    public static long getElapedTimeInSeconds(){
        long elapsed;
        if(running){
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        } else {
            elapsed = ((endTime - startTime) / 1000);
        }
        return elapsed;
    }
}
