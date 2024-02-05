package com.play.scoreboard.servise;

import com.play.scoreboard.models.Player;

import java.util.HashMap;
import java.util.Map;

public class ScoreGame {
    private Player player1;
    private Player player2;
    private Map<Player, ScorePlayer> score = new HashMap<>();
    private boolean equally;
    private boolean tieBreak;

    public ScoreGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        score.put(player1, new ScorePlayer());
        score.put(player2, new ScorePlayer());
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getPoints(Player player) {
        return score.get(player).getPoints();
    }

    public void addPoint(Player player) {
       score.get(player).addPoint();
    }

    public void clearPoints() {
        score.values().stream().forEach(p -> p.clearPoints());
    }

    public int getGames(Player player) {
        return score.get(player).getGames();
    }

    public void addGame(Player player) {
        score.get(player).addGame();
    }

    public void clearGames() {
        score.values().stream().forEach(g -> g.clearGames());
    }

    public int getSets(Player player) {
        return score.get(player).getSets();
    }

    public void addSet(Player player) {
        score.get(player).addSet();
    }

    public void clearSets(Player player) {
        score.get(player).clearSets();
    }

    public void startEqually() {
        equally = true;
    }

    public void clearEqually() {
        equally = false;
    }

    public boolean getEqually() {
        return equally;
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


