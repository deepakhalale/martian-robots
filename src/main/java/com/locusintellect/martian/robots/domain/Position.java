package com.locusintellect.martian.robots.domain;

import static com.locusintellect.martian.robots.domain.Coordinates.newCoordinates;
import static java.lang.Integer.parseInt;

import java.util.Objects;

public class Position {

    private static final String DELIMITER = " ";

    private final Coordinates coordinates;
    private final Orientation orientation;

    public static Position newPosition(final String input) {
        final String[] positionValues = input.trim().split(DELIMITER);
        return new Position(newCoordinates(parseInt(positionValues[0]), parseInt(positionValues[1])), Orientation.from(positionValues[2]));
    }

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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return Objects.equals(getCoordinates(), position.getCoordinates()) &&
                getOrientation() == position.getOrientation();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoordinates(), getOrientation());
    }

    @Override
    public String toString() {
        return "Position{" +
                "coordinates=" + coordinates +
                ", orientation=" + orientation +
                '}';
    }
}
