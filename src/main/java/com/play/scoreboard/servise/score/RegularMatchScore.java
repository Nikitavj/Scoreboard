package com.play.scoreboard.servise.score;

import lombok.Getter;

@Getter
public class RegularMatchScore extends Score<Integer>{
    RegularSetScore currentSet;

    public RegularMatchScore() {
        this.currentSet = new RegularSetScore();
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public State pointWon(int playerNumber) {
        State gameState = currentSet.pointWon(playerNumber);

        if (gameState == State.PLAYER_ONE_WON) {
            return setWon(0);
        } else if (gameState == State.PLAYER_TWO_WON) {
            return setWon(1);
        }

        return State.ONGOING;
    }

    public State setWon(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);
        currentSet = new RegularSetScore();

        if (getPlayerScore(playerNumber) >= 2) {
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }

        return State.ONGOING;
    }
}

