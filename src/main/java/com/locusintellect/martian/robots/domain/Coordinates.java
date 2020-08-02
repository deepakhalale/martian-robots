package com.locusintellect.martian.robots.domain;

import java.util.Objects;

public class Coordinates {

    private final int x;
    private final int y;

    public static Coordinates newCoordinates(final int x, final int y) {
        return new Coordinates(x, y);
    }

    public static Coordinates newCoordinates(final Coordinates that) {
        return new Coordinates(that.x, that.y);
    }

    Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        final Coordinates that = (Coordinates) other;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
