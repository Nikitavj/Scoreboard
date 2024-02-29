package com.play.scoreboard.servise;

import com.play.scoreboard.models.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class ScoreGame {
    private Player player1;
    private Player player2;
    private Map<Player, ScorePlayer> score = new HashMap<>();
    private int setCurrent = 1;
    private boolean deuce;
    private boolean tiebreak;

    public ScoreGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        score.put(player1, new ScorePlayer());
        score.put(player2, new ScorePlayer());
    }

    public int getPoints(Player player) {
        return score.get(player).getPoints();
    }

    public String getScore(Player player) {

        if (!deuce && !tiebreak) {
            switch (getPoints(player)) {
                case 1:
                    return "15";
                case 2:
                    return "30";
                case 3:
                    return "40";
            }
        }

        if (deuce) {

            if (getPoints(player1) == getPoints(player2)){
                return "РОВНО";
            }

            if (player.equals(player1)
                    && getPoints(player1) > getPoints(player2)) {
                return "БОЛЬШЕ";
            }

            if (player.equals(player2)
                    && getPoints(player2) > getPoints(player1)) {
                return "БОЛЬШЕ";
            } else {
                return "МЕНЬШЕ";
            }
        }

        return Integer.toString(getPoints(player));
    }

    public void addPoint(Player player) {
        score.get(player).addPoint();
    }

    public void clearPoints() {
        score.values().stream().forEach(s -> s.clearPoints());
    }

    public int getGames(Player player) {
        return score.get(player).getGames();
    }

    public void addGame(Player player) {
        score.get(player).addGame();
        clearPoints();
    }

    public int getSets(Player player) {
        return score.get(player).getSets();
    }

    public void clearGames() {
        score.values().stream().forEach(s -> s.clearGames());
    }

    public void addSet(Player player) {
        score.get(player).addSet();
        addPrevSet();
        clearGames();
        setCurrent++;
    }

    public int getPrevSets(Player player, long number) {
        return score.get(player).getPreviousSets().get((int) number);
    }

    private void addPrevSet() {
        score.values().stream().forEach(s -> s.addPrevSet(setCurrent));
    }
}


