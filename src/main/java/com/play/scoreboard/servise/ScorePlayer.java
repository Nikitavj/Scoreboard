package com.play.scoreboard.servise;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class ScorePlayer {

    private int points = 0;
    private int games = 0;
    private int sets = 0;
    private Map<Integer, Integer> previousSets = new HashMap<>();

    public ScorePlayer() {
        previousSets.put(1, 0);
        previousSets.put(2, 0);
        previousSets.put(3, 0);
    }

    public void addPoint() {
        points++;
    }

    public void addGame() {
        games++;
    }

    public void addSet() {
        sets++;
    }

    public void clearPoints() {
        points = 0;
    }

    public void clearGames() {
        games = 0;
    }

    public void addPrevSet(int numberSet) {
        previousSets.put(numberSet, games);
    }
}
