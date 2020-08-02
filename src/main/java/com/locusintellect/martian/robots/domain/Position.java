package com.locusintellect.martian.robots.domain;

import static com.locusintellect.martian.robots.domain.Coordinates.newCoordinates;

public class Position {

    private final Coordinates coordinates;
    private final Orientation orientation;

    public static Position newPosition(final Coordinates coordinates, final Orientation orientation) {
        return new Position(coordinates, orientation);
    }

    public static Position newPosition(final Position that) {
        return new Position(that.coordinates, that.orientation);
    }

    Position(final Coordinates coordinates, final Orientation orientation) {
        this.coordinates = newCoordinates(coordinates);
        this.orientation = orientation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "Position{" +
                "coordinates=" + coordinates +
                ", orientation=" + orientation +
                '}';
    }
}
