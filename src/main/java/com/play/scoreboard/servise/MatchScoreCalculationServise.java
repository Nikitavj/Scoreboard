package com.play.scoreboard.servise;

import com.play.scoreboard.models.MatchScoreModel;
import com.play.scoreboard.models.Player;

public class MatchScoreCalculationServise {
    MatchScoreModel match;
    Player player1;
    Player player2;

    public MatchScoreCalculationServise(MatchScoreModel match) {
        this.match = match;
        this.player1 = match.getPlayer1();
        this.player2 = match.getPlayer2();
    }

    public boolean calculate(long idWin) {
        Score score = match.getScore();
        Player winner = getPlayerWinner(idWin);

        score.addPoint(winner);

        checkPoint(score, winner);
        checkGame(score, winner);
        return checkSet(score, winner);
    }

    private void checkPoint(Score score, Player winner) {
        int points1 = score.getPoints(player1);
        int points2 = score.getPoints(player2);

        if (score.getTieBreak()) {
            if ((points1 == 7 && points2 <= 5)
                    || (points2 == 7 && points1 <= 5)) {
                score.clearPoints();
                score.addGame(winner);
                return;
            }

            if ((points1 >= 6 && points2 >= 6)
                    && (Math.abs(points1 - points2) == 2)) {
                score.clearPoints();
                score.addGame(winner);
                return;
            }

            return;
        }

        if (!score.getEquallPoints()) {
            if ((points1 == 4 && points2 <= 2)
                    || (points2 == 4 && points1 <= 2)) {
                score.addGame(winner);
                score.clearPoints();
                score.clearEquallyPoints();
                return;
            }

            if (points1 == 3 && points2 == 3) {
                score.clearPoints();
                score.equallyPoints();
                return;
            }
        }

        if (score.getEquallPoints()) {
            if (Math.abs(points1 - points2) == 2) {
                score.addGame(winner);
                score.clearPoints();
                score.clearEquallyPoints();
            }
        }
    }

    public void checkGame(Score score, Player winner) {
        int games1 = score.getGames(player1);
        int games2 = score.getGames(player2);

        if (!score.getTieBreak()) {
            if ((games1 == 6 && games2 <= 4)
                    || (games2 == 6 && games1 <= 4)
                    || (games1 == 7 && games2 == 5)
                    || (games2 == 7 && games1 == 5)) {
                score.addSets(winner);
                score.clearGames();
                return;
            }

            if (games1 == 6 && games2 == 6) {
                score.startTieBreak();
                return;
            }
        }

        if (score.getTieBreak()) {
            if (games1 == 7 || games2 == 7) {
                score.addSets(winner);
                score.clearGames();
                score.clearTieBreak();
            }
        }
    }

    public boolean checkSet(Score score, Player winner) {
        int sets1 = score.getSets(player1);
        int sets2 = score.getSets(player2);
        Player player1 = match.getPlayer1();
        Player player2 = match.getPlayer2();

        if (sets1 == 2 || sets2 == 2) {
            if (player1 == winner) {
                match.setWinner(player1);
            } else {
                match.setWinner(player2);
            }
            return true;
        }

        return false;
    }

    private Player getPlayerWinner(Long idWin) {
        return player1.getId() == idWin ? player1: player2;
    }
}
