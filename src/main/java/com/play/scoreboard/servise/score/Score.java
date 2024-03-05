package com.play.scoreboard.servise.score;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> {

    private final List<T> playerScores = new ArrayList<>();

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
        playerScores.set(playerNumber, score);
    }

    public void setOppositePlayerScore(int playerNumber, T score) {
        playerScores.set(playerNumber == 0 ? 1 : 0, score);
    }

    public abstract State pointWon(int playerNumber);
}
