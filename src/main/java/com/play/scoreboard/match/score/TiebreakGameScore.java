package com.play.scoreboard.match.score;

public class TiebreakGameScore extends Score<Integer>{
    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public State pointWon(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);

        int playerScore = getPlayerScore(playerNumber);
        if (playerScore >= 7 &&
                (playerScore - getOppositePlayerScore(playerNumber)) >= 2) {
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }

        return State.ONGOING;
    }
}
