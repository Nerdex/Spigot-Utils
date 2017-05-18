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

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.List;
import java.util.Map;

public class ScoreboardBuilder {

    private static Scoreboard scoreboard;

    private static String title;
    private static Map<String, Integer> scores;
    private List<Team> teams;

    public ScoreboardBuilder(String title){
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.title = title;
        this.scores = Maps.newLinkedHashMap();
        this.teams = Lists.newArrayList();
    }

    public static void blankeLine(){

    }

    public static void add(String text, Integer score){
        Preconditions.checkArgument(text.length() < 48, "Text can't be over 48 characters");
        text = fixDupes(text);
        scores.put(text, score);
    }

    private static String fixDupes(String text){
        while(scores.containsKey(text)){
            text += "&r";
            if(text.length() > 48)
                text = text.substring(0, 47);
        }
        return text;
    }
}
