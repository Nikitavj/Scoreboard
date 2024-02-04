package com.play.scoreboard.controller;

public class Validator {

    public static void validNames(String name1, String name2) {

        if (name1 == null | name2 == null) {
            throw new RuntimeException("Отсутсвует игрок");
        }
        if (name1.length() == 0 | name2.length() == 0) {
            throw new RuntimeException("Отсутсвует игрок");
        }
    }
}
