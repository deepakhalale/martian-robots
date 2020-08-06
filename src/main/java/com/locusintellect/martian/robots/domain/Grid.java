package com.locusintellect.martian.robots.domain;

import static com.locusintellect.martian.robots.domain.Coordinates.newCoordinates;

public class Grid {

    private final Coordinates lowerLeftBound;
    private final Coordinates upperRightBound;

    public static Grid newGrid(final Coordinates upperRightBound) {
        return new Grid(newCoordinates(0, 0), upperRightBound);
    }

    private Grid(final Coordinates lowerLeftBound, final Coordinates upperRightBound) {
        this.lowerLeftBound = lowerLeftBound;
        this.upperRightBound = upperRightBound;
    }

    public Coordinates getLowerLeftBound() {
        return lowerLeftBound;
    }

    public Coordinates getUpperRightBound() {
        return upperRightBound;
    }

    public boolean hasMovedOffTheGrid(final int xCoordinate, final int yCoordinate) {
        return yCoordinate > getUpperRightBound().getY() || yCoordinate < getLowerLeftBound().getY() || xCoordinate > getUpperRightBound().getX() || xCoordinate < getLowerLeftBound().getX();
    }
}
