package com.play.scoreboard.controller;

import com.play.scoreboard.exception.BadRequestException;

public class Validator {
private static final int MAX_SIZE_NAME = 20;
    public static void validName(String name) {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Отсутсвует имя игрока");
        }

        if (name.length() > MAX_SIZE_NAME) {
            throw new BadRequestException("Имя игрока может содержать не более 20 символов");
        }
    }

    public static void validNames(String name1, String name2) {
        validName(name1);
        validName(name2);
        if (name1.equals(name2)) {
            throw new BadRequestException("Введены два одинаковых имени");
        }
    }

    public static void validUuid(String uuid) {
        if (uuid == null || uuid.isBlank()) {
            throw new BadRequestException("Отсутствует значение параметра uuid");
        }
    }

    public static void validPage(String pageString) {
        if (pageString == null || pageString.isBlank()) {
            throw new BadRequestException("Отсутствует значение параметра page");
        }
    }
}
