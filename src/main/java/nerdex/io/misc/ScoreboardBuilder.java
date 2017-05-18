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
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ScoreboardBuilder {

    private static Scoreboard scoreboard;

    private static String title;
    private static Map<String, Integer> scores;
    private static List<Team> teams;

    public ScoreboardBuilder(String title){
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.title = title;
        this.scores = Maps.newLinkedHashMap();
        this.teams = Lists.newArrayList();
    }

    public static void blankeLine(){
        add(" ");
    }

    public static void add(String text){
        add(text, null);
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

    private static Map.Entry<Team, String> createTeam(String text){
        String res = "";
        if(text.length() <= 16)
            return new AbstractMap.SimpleEntry<>(null, text);

        Team team = scoreboard.registerNewTeam("text-" +  scoreboard.getTeams().size());
        Iterator<String> iter = Splitter.fixedLength(16).split(text).iterator();
        team.setPrefix(iter.next());
        res = iter.next();

        if(text.length() > 32)
            team.setSuffix(iter.next());
        teams.add(team);
        return new AbstractMap.SimpleEntry<>(team, res);
    }

    public static void build(){
        Objective objective = scoreboard.registerNewObjective((title.length() > 16 ? title.substring(0, 15) : title), "dummy");
        objective.setDisplayName(title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        int index = scores.size();

        for(Map.Entry<String, Integer> text : scores.entrySet()){
            Map.Entry<Team, String> team = createTeam(text.getKey());
            Integer score = text.getValue() != null ? text.getValue() : index;
            OfflinePlayer player = Bukkit.getOfflinePlayer(team.getValue());
            if(team.getKey() != null)
                team.getKey().addPlayer(player); //deprecated but who gives a fuck?
            objective.getScore(player).setScore(score);
            index -= 1;
        }
    }

    public static void reset(){
        title = null;
        scores.clear();
        for(Team t : teams)
            t.unregister();
        teams.clear();
    }

    public static Scoreboard getScoreboard(){
        return scoreboard;
    }

    public static void send(Player... players){
        for(Player player : players){
            player.setScoreboard(scoreboard);
        }
    }
}
