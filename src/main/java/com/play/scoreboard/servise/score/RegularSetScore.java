package com.play.scoreboard.servise.score;

public class RegularSetScore extends Score<Integer> {
    private RegularGameScore currentGame;
    private TiebreakGameScore tiebreakGame;
    private boolean tiebreak = false;

    public RegularSetScore() {
        this.currentGame = new RegularGameScore();
        this.tiebreakGame = new TiebreakGameScore();
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public State pointWon(int playerNumber) {
        State gameState;

        if (tiebreak) {
            gameState = tiebreakGame.pointWon(playerNumber);
        } else {
            gameState = currentGame.pointWon(playerNumber);
        }

        if (gameState == State.PLAYER_ONE_WON) {
            gameWon(0);
        } else if (gameState == State.PLAYER_TWO_WON) {
            gameWon(1);
        }

        return State.ONGOING;
    }

    private State gameWon(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);
        currentGame = new RegularGameScore();

        int playerScore = getPlayerScore(playerNumber);
        if (playerScore == 6) {
            if (getOppositePlayerScore(playerNumber) <= 4) {
                return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
            } else if (getOppositePlayerScore(playerNumber) == 6) {
                tiebreak = true;
                return State.ONGOING;
            }
        } else if (playerScore == 7 & getOppositePlayerScore(playerNumber) == 5) {
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }

        if (tiebreak) {
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }

        return State.ONGOING;
    }

}
