package com.play.scoreboard.servise;

import com.play.scoreboard.models.Player;

public class MatchScoreCalculationServise {

    public boolean calculate(ScoreGame score, Player winner) {
        checkPoints(score, winner);
        checkGame(score, winner);
        return checkSet(score, winner);
    }

    private void checkPoints(ScoreGame score, Player winner) {
        int points1 = score.getPoints(score.getPlayer1());
        int points2 = score.getPoints(score.getPlayer2());

        if (score.getTieBreak()) {
            if ((points1 == 7 && points2 <= 5)
                    || (points2 == 7 && points1 <= 5)) {
                score.addGame(winner);
                return;
            }

            if ((points1 >= 6 && points2 >= 6)
                    && (Math.abs(points1 - points2) == 2)) {
                score.addGame(winner);
                return;
            }

            return;
        }

        if (!score.getEqually()) {
            if ((points1 == 4 && points2 <= 2)
                    || (points2 == 4 && points1 <= 2)) {
                score.addGame(winner);
                score.clearEqually();
                return;
            }

            if (points1 == 3 && points2 == 3) {
                score.startEqually();
                return;
            }
        }

        if (score.getEqually()) {
            if (Math.abs(points1 - points2) == 2) {
                score.addGame(winner);
                score.clearEqually();
            }
        }
    }

    public void checkGame(ScoreGame score, Player winner) {
        int games1 = score.getGames(score.getPlayer1());
        int games2 = score.getGames(score.getPlayer2());

        if (!score.getTieBreak()) {
            if ((games1 == 6 && games2 <= 4)
                    || (games2 == 6 && games1 <= 4)
                    || (games1 == 7 && games2 == 5)
                    || (games2 == 7 && games1 == 5)) {
                score.addSet(winner);
                return;
            }

            if (games1 == 6 && games2 == 6) {
                score.startTieBreak();
                return;
            }
        }

        if (score.getTieBreak()) {
            if (games1 == 7 || games2 == 7) {
                score.addSet(winner);
                score.clearTieBreak();
            }
        }
    }

    public boolean checkSet(ScoreGame score, Player winner) {

        int sets1 = score.getSets(score.getPlayer1());
        int sets2 = score.getSets(score.getPlayer2());

        if (sets1 == 2 || sets2 == 2) {
            return true;
        }

        return false;
    }
}
