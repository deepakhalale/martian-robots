package com.locusintellect.martian.robots.commands;

import static com.locusintellect.martian.robots.domain.CommandType.FORWARD;
import static com.locusintellect.martian.robots.domain.Coordinates.newCoordinates;
import static com.locusintellect.martian.robots.domain.FinalPosition.lost;
import static com.locusintellect.martian.robots.domain.FinalPosition.newFinalPosition;

import com.locusintellect.martian.robots.domain.CommandType;
import com.locusintellect.martian.robots.domain.FinalPosition;
import com.locusintellect.martian.robots.domain.Grid;
import com.locusintellect.martian.robots.domain.Position;

public class MoveForward implements Command {

    private final Grid grid;

    public MoveForward(final Grid grid) {
        this.grid = grid;
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

        if (grid.hasMovedOffTheGrid(newXCoordinate, newYCoordinate)) {
            return lost(position);
        }
        return newFinalPosition(newCoordinates(newXCoordinate, newYCoordinate), position.getOrientation());
    }

    @Override
    public CommandType getType() {
        return FORWARD;
    }
}
