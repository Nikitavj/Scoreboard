package com.play.scoreboard.servise;

public class ScorePlayer {

    private int points = 0;
    private int games = 0;
    private int sets = 0;

    public void addPoint() {
        points++;
    }

    public void addGame() {
        games++;
    }

    public void addSet() {
        sets++;
    }

    public int getPoints() {
        return points;
    }

    public int getGames() {
        return games;
    }

    public int getSets() {
        return sets;
    }

    public void clearPoints() {
        points = 0;
    }

    public void clearGames() {
        games = 0;
    }

    public void clearSets() {
        sets = 0;
    }
}
