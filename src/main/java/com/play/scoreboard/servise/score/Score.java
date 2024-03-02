package com.play.scoreboard.servise.score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Score<T> {

    List<T> playerScores = new ArrayList<>();

    public Score() {
        playerScores.add(0, getZeroScore());
        playerScores.add(1, getZeroScore());
    }

    protected abstract T getZeroScore();

    public T getPlayerScore(int playerNumber) {
        return playerScores.get(playerNumber);
    }

    public T getOppositePlayerScore(int playerNumber) {
        return playerScores.get(playerNumber == 0 ? 1 : 0);
    }

    public void setPlayerScore(int playerNumber, T score) {
        playerScores.add(playerNumber, score);
    }

    public void setOppositePlayerScore(int playerNumber, T score) {
        playerScores.add(playerNumber == 0 ? 1 : 0, score);
    }

    abstract State pointWon(int playerNumber);
}
