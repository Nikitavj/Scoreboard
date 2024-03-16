package com.play.scoreboard.match.models;

import com.play.scoreboard.player.models.Player;
import com.play.scoreboard.match.score.RegularGamePoints;
import com.play.scoreboard.match.score.RegularMatchScore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class MatchScoreModel {
    private String uuid;
    private Player winner;
    private Player player1;
    private Player player2;
    private RegularMatchScore score;

    public MatchScoreModel(Player player1, Player player2, String uuid) {
        this.uuid = uuid;
        this.player1 = player1;
        this.player2 = player2;
        this.score = new RegularMatchScore();
    }

    public String getPointsPlayer(int playerNumber) {
        if (isTiebreak()) {
            return String.valueOf(score.getCurrentSet().
                    getTiebreakGame().
                    getPlayerScore(playerNumber));
        } else {
            RegularGamePoints point = score.getCurrentSet().
                    getCurrentGame().
                    getPlayerScore(playerNumber);

            switch (point) {
                case RegularGamePoints.ZERO:
                    return "0";
                case RegularGamePoints.FIFTEEN:
                    return "15";
                case RegularGamePoints.THIRTY:
                    return "30";
                case RegularGamePoints.FORTY:
                    return "40";
                case RegularGamePoints.ADVANTAGE:
                    return "AD";
                default:
                    return "0";
            }
        }
    }

    public int getGamesPlayer(int playerNumber) {
        return score.getCurrentSet().getPlayerScore(playerNumber);
    }

    public int getSetsPlayer(int playerNumber) {
        return score.getPlayerScore(playerNumber);
    }

    public boolean isTiebreak() {
        return score.getCurrentSet().isTiebreak();
    }

    public List<Integer> getPreviousSetPlayer(int playerNumber) {
        return score.getPrevSetForPlayer(playerNumber);
    }
}
