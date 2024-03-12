package com.play.scoreboard.exception;

import java.util.concurrent.TransferQueue;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
