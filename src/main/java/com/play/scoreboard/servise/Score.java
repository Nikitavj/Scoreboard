package com.play.scoreboard.servise;

import com.play.scoreboard.models.Player;

import java.util.HashMap;
import java.util.Map;

public class Score {
    private Map<Player, Integer> points = new HashMap<>();
    private Map<Player, Integer> games = new HashMap<>();
    private Map<Player, Integer> sets = new HashMap<>();
    private boolean equallPoints;
    private boolean tieBreak;
    private final Player player1;
    private final Player player2;

    public Score(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        points.put(player1, 0);
        points.put(player2, 0);
        games.put(player1, 0);
        games.put(player2, 0);
        sets.put(player1, 0);
        sets.put(player2, 0);
    }

    public int getPoints(Player player) {
        return points.get(player);
    }

    public void addPoint(Player player) {
        int point = points.get(player);
        points.put(player, ++point);
    }

    public void clearPoints() {
        points.put(player1, 0);
        points.put(player2, 0);
    }

    public int getGames(Player player) {
        return  games.get(player);
    }

    public void addGame(Player player) {
        int game = games.get(player);
        games.put(player, ++game);
    }

    public void clearGames() {
        games.put(player1, 0);
        games.put(player2, 0);
    }

    public int getSets(Player player) {
        return sets.get(player);
    }

    public void addSets(Player player) {
        int set = sets.get(player);
        sets.put(player, ++set);
    }

    public void equallyPoints() {
        equallPoints = true;
    }

    public void clearEquallyPoints() {
        equallPoints = false;
    }

    public boolean getEquallPoints() {
        return equallPoints;
    }

    public void startTieBreak() {
        tieBreak = true;
    }

    public void clearTieBreak() {
        tieBreak = false;
    }

    public boolean getTieBreak() {
        return tieBreak;
    }
}


