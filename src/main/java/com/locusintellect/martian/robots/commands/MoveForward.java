package com.locusintellect.martian.robots.commands;

import static com.locusintellect.martian.robots.domain.Coordinates.newCoordinates;
import static com.locusintellect.martian.robots.domain.FinalPosition.lost;
import static com.locusintellect.martian.robots.domain.FinalPosition.newFinalPosition;

import com.locusintellect.martian.robots.domain.Coordinates;
import com.locusintellect.martian.robots.domain.FinalPosition;
import com.locusintellect.martian.robots.domain.Position;

public class MoveForward implements Movement {

    private final Coordinates lowerLeft;
    private final Coordinates upperRight;

    public MoveForward(final Coordinates lowerLeft, final Coordinates upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    @Override
    public FinalPosition move(final Position position) {
        int newXCoordinate = position.getCoordinates().getX();
        int newYCoordinate = position.getCoordinates().getY();
        switch (position.getOrientation()) {
            case NORTH:
                newYCoordinate++;
                break;
            case SOUTH:
                newYCoordinate--;
                break;
            case EAST:
                newXCoordinate++;
                break;
            case WEST:
                newXCoordinate--;
                break;
        }

        if (hasMovedOffTheGrid(newXCoordinate, newYCoordinate)) {
            return lost(position);
        }
        return newFinalPosition(newCoordinates(newXCoordinate, newYCoordinate), position.getOrientation());
    }

    private boolean hasMovedOffTheGrid(final int newXCoordinate, final int newYCoordinate) {
        return newYCoordinate > upperRight.getY() || newYCoordinate < lowerLeft.getY() || newXCoordinate > upperRight.getX() || newXCoordinate < lowerLeft.getX();
    }
}
