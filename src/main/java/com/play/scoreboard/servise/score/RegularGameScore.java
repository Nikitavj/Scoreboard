package com.play.scoreboard.servise.score;

public class RegularGameScore extends GameScore<RegularGamePoints>{
    @Override
    protected RegularGamePoints getZeroScore() {
        return RegularGamePoints.ZERO;
    }

    @Override
    public State pointWon(int playerNumber) {
        RegularGamePoints playerScore = getPlayerScore(playerNumber);

        if (playerScore.ordinal() <= RegularGamePoints.THIRTY.ordinal()) {
            setPlayerScore(playerNumber, playerScore.next());
        } else if (playerScore == RegularGamePoints.FORTY) {
            RegularGamePoints oppositePlayerScore = getOppositePlayerScore(playerNumber);

            if (oppositePlayerScore == RegularGamePoints.FORTY) {
                setPlayerScore(playerNumber, playerScore.next());
            } else if (oppositePlayerScore == RegularGamePoints.ADVANTAGE) {
                setOppositePlayerScore(playerNumber, RegularGamePoints.FORTY);
            } else {
                return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
            }
        }else if (playerScore == RegularGamePoints.ADVANTAGE) {
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        } else {
            throw new IllegalStateException("Cannot call pointWon() on ADVANTAGE");
        }

        return State.ONGOING;
    }
}
