package com.play.scoreboard.models;

import com.play.scoreboard.servise.Score;

public class MatchScoreModel {
    private String uuid;
    private Player winner;
    private Player player1;
    private Player player2;
    private Score score;

    public MatchScoreModel(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = new Score(player1, player2);
    }

    public String getUuid() {
        return uuid;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Score getScore() {
        return score;
    }
}
