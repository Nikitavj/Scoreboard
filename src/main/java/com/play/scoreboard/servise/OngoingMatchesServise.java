package com.play.scoreboard.servise;

import com.play.scoreboard.models.MatchScoreModel;
import com.play.scoreboard.models.Player;

import java.util.HashMap;
import java.util.UUID;

public class OngoingMatchesServise {
    private static HashMap<String, MatchScoreModel> matches = new HashMap<>();

    public MatchScoreModel startNewMatch(Player player1, Player player2) {
        String uuid = UUID.randomUUID().toString();
        var match = new MatchScoreModel(player1, player2, uuid);
        matches.put(uuid, match);
        return match;
    }

    public MatchScoreModel get(String uuid) {
        MatchScoreModel match = matches.get(uuid);
        if (match == null) {
            throw new RuntimeException("Матча с текущим uuid не существует");
        }

        return match;
    }
}
