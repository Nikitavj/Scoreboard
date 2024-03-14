package com.play.scoreboard.match.score;

public enum RegularGamePoints {
    ZERO,
    FIFTEEN,
    THIRTY,
    FORTY,
    ADVANTAGE;

    public RegularGamePoints next() {
        if (this == ADVANTAGE) {
            throw new IllegalStateException("Cannot call next() on ADVANTAGE");

        } else {
            return RegularGamePoints.values()[this.ordinal() + 1];
        }
    }
}
