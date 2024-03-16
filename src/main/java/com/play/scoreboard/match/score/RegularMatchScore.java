package com.play.scoreboard.match.score;

import lombok.Getter;

import java.util.List;

@Getter
public class RegularMatchScore extends Score<Integer> {
    private RegularSetScore currentSet;
    private PreviousSetsScore previousSets;
    private int counterSets = 0;

    public RegularMatchScore() {
        this.currentSet = new RegularSetScore();
        this.previousSets = new PreviousSetsScore();
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
        previousSets.addSet(counterSets, currentSet);
        counterSets++;

        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);
        currentSet = new RegularSetScore();

        if (getPlayerScore(playerNumber) >= 2) {
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }

        return State.ONGOING;
    }

    public List<Integer> getPrevSetForPlayer(int playerNumber) {
        return previousSets.getSets(playerNumber);
    }


}

