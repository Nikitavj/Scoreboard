package com.play.scoreboard.match.service;

import com.play.scoreboard.match.models.MatchScoreModel;
import com.play.scoreboard.match.score.State;

public class MatchScoreCalculationServise {

    public boolean isFinished(MatchScoreModel match, int playerNumber) {
        State matchState = match.getScore().pointWon(playerNumber);

        if (matchState == State.PLAYER_ONE_WON) {
            match.setWinner(match.getPlayer1());
            return true;
        } else if (matchState == State.PLAYER_TWO_WON) {
            match.setWinner(match.getPlayer2());
            return true;
        }

        return false;
    }
}
