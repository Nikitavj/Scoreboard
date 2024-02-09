package com.play.scoreboard.controller;

import com.play.scoreboard.models.MatchScoreModel;

public class Validator {

    public static void validNames(String name1, String name2) {

        if (name1 == null | name2 == null) {
            throw new RuntimeException("Отсутсвует игрок");
        }
        if (name1.length() == 0 | name2.length() == 0) {
            throw new RuntimeException("Отсутсвует игрок");
        }
    }

    public static void validIdForMatch(Long idWin, MatchScoreModel match) {
        if(idWin != match.getPlayer1().getId() &&
                idWin != match.getPlayer2().getId()) {
            throw new RuntimeException("Неверный ID игрока для текущего матча");
        }
    }

    public static void validPage(int page) {
        if(page < 1) {
            throw new RuntimeException("Номер страницы должен быть больше 0");
        }
    }
}
