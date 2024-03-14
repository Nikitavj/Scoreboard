package com.play.scoreboard.match.score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreviousSetsScore extends Score<ArrayList<Integer>>{
    @Override
    protected ArrayList<Integer> getZeroScore() {
        return new ArrayList<Integer>(Arrays.asList(0, 0, 0));
    }

    public void addSet(int setNumber, RegularSetScore score) {
        getPlayerScore(0).set(setNumber, score.getPlayerScore(0));
        getPlayerScore(1).set(setNumber, score.getPlayerScore(1));
    }

    public List<Integer> getSets(int playerNumber) {
        return getPlayerScore(playerNumber);
    }

    @Override
    public State pointWon(int playerNumber) {
        return null;
    }
}
