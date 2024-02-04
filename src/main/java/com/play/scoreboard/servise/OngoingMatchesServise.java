package com.play.scoreboard.servise;

import com.play.scoreboard.models.Match;
import com.play.scoreboard.models.MatchScoreModel;

import java.util.HashMap;
import java.util.UUID;

public class OngoingMatchesServise {
    private static HashMap<String, MatchScoreModel> matches = new HashMap<>();

    public String startNewMatch(MatchScoreModel match) {
        String uuid = UUID.randomUUID().toString();
        matches.put(uuid, match);
        return uuid;
    }

    public MatchScoreModel get(String uuid) {
        return matches.get(uuid);
    }
}
