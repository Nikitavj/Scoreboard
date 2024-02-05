package com.play.scoreboard.models;

import com.play.scoreboard.servise.ScoreGame;

public class MatchScoreModel {
    private final String uuid;
    private Player winner;
    private final Player player1;
    private final Player player2;
    private ScoreGame score;

    public MatchScoreModel(Player player1, Player player2, String uuid) {
        this.uuid = uuid;
        this.player1 = player1;
        this.player2 = player2;
        this.score = new ScoreGame(player1, player2);
    }

    public String getUUID() {
        return uuid;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getPlayerById(long id) {
        return player1.getId() == id ? player1 : player2;
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

    public ScoreGame getScore() {
        return score;
    }
}
