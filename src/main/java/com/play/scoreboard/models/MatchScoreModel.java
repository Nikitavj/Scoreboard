package com.play.scoreboard.models;

import com.play.scoreboard.servise.ScoreGame;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MatchScoreModel {
    private String uuid;
    private Player winner;
    private Player player1;
    private Player player2;
    private ScoreGame score;

    public MatchScoreModel(Player player1, Player player2, String uuid) {
        this.uuid = uuid;
        this.player1 = player1;
        this.player2 = player2;
        this.score = new ScoreGame(player1, player2);
    }

    public Player getPlayerById(long id) {
        return player1.getId() == id ? player1 : player2;
    }
}
