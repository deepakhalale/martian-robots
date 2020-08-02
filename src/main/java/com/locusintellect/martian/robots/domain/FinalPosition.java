package com.locusintellect.martian.robots.domain;

public class FinalPosition extends Position {

    private final boolean isLost;

    public static FinalPosition newFinalPosition(final Position position) {
        return new FinalPosition(position.getCoordinates(), position.getOrientation(), false);
    }

    public static FinalPosition newFinalPosition(final Coordinates coordinates, final Orientation orientation) {
        return new FinalPosition(coordinates, orientation, false);
    }

    public static FinalPosition lost(final Position position) {
        return new FinalPosition(position.getCoordinates(), position.getOrientation(), true);
    }

    private FinalPosition(final Coordinates coordinates, final Orientation orientation, final boolean isLost) {
        super(coordinates, orientation);
        this.isLost = isLost;
    }

    public boolean isLost() {
        return isLost;
    }
}
