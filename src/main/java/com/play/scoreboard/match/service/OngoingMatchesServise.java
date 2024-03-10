package com.play.scoreboard.match.service;

import com.play.scoreboard.match.models.MatchScoreModel;
import com.play.scoreboard.player.models.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesServise {
    private static Map<String, MatchScoreModel> matches = new ConcurrentHashMap<>();

    public MatchScoreModel startNewMatch(Player player1, Player player2) {
        String uuid = UUID.randomUUID().toString();
        MatchScoreModel match = new MatchScoreModel(player1, player2, uuid);
        matches.put(uuid, match);
        return match;
    }

    public MatchScoreModel get(String uuid) {
        MatchScoreModel match = matches.get(uuid);
        if (match == null) {
            throw new RuntimeException("Матча с указанным uuid не существует");
        }

        return match;
    }

    public void remove(String key) {
        matches.remove(key);
    }
}
