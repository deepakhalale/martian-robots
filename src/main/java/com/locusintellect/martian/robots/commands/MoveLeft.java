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

public class MoveLeft implements Movement {

    private final EnumMap<Orientation, Orientation> leftOrientations;

    public MoveLeft() {
        leftOrientations = new EnumMap<>(Orientation.class);
        leftOrientations.put(EAST, NORTH);
        leftOrientations.put(WEST, SOUTH);
        leftOrientations.put(NORTH, WEST);
        leftOrientations.put(SOUTH, EAST);
    }

    @Override
    public FinalPosition move(final Position currentPosition) {
        return newFinalPosition(currentPosition.getCoordinates(), leftOrientations.get(currentPosition.getOrientation()));
    }
}
