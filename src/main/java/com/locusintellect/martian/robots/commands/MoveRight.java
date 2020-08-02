package com.locusintellect.martian.robots.commands;

import static com.locusintellect.martian.robots.domain.FinalPosition.newFinalPosition;
import static com.locusintellect.martian.robots.domain.Orientation.EAST;
import static com.locusintellect.martian.robots.domain.Orientation.NORTH;
import static com.locusintellect.martian.robots.domain.Orientation.SOUTH;
import static com.locusintellect.martian.robots.domain.Orientation.WEST;

import java.util.EnumMap;

import com.locusintellect.martian.robots.domain.FinalPosition;
import com.locusintellect.martian.robots.domain.Orientation;
import com.locusintellect.martian.robots.domain.Position;

public class MoveRight implements Movement {

    private final EnumMap<Orientation, Orientation> rightOrientations;

    public MoveRight() {
        rightOrientations = new EnumMap<>(Orientation.class);
        rightOrientations.put(EAST, SOUTH);
        rightOrientations.put(WEST, NORTH);
        rightOrientations.put(NORTH, EAST);
        rightOrientations.put(SOUTH, WEST);
    }

    @Override
    public FinalPosition move(final Position currentPosition) {
        return newFinalPosition(currentPosition.getCoordinates(), rightOrientations.get(currentPosition.getOrientation()));
    }
}