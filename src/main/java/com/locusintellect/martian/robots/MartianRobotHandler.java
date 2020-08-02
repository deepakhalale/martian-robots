package com.locusintellect.martian.robots;

import static com.locusintellect.martian.robots.domain.FinalPosition.newFinalPosition;

import java.util.List;

import com.locusintellect.martian.robots.commands.Instruction;
import com.locusintellect.martian.robots.commands.MoveForward;
import com.locusintellect.martian.robots.commands.MoveLeft;
import com.locusintellect.martian.robots.commands.MoveRight;
import com.locusintellect.martian.robots.domain.Coordinates;
import com.locusintellect.martian.robots.domain.FinalPosition;
import com.locusintellect.martian.robots.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MartianRobotHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MartianRobotHandler.class);

    private final Coordinates lowerLeft;
    private final Coordinates upperRight;

    public MartianRobotHandler(final Coordinates lowerLeft, final Coordinates upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public FinalPosition sendInstructions(final Position initialPosition, final List<Instruction> instructions) {
        FinalPosition currentPosition = newFinalPosition(initialPosition);
        for (final Instruction instruction : instructions) {
            switch (instruction) {
                case LEFT:
                    currentPosition = new MoveLeft().move(currentPosition);
                    break;
                case RIGHT:
                    currentPosition = new MoveRight().move(currentPosition);
                    break;
                case FORWARD:
                    currentPosition = new MoveForward(lowerLeft, upperRight).move(currentPosition);
                    break;
            }
            if (currentPosition.isLost()) {
                LOG.warn("Martian Robot at initial position {} fell off the edge of the grid at {}", initialPosition, currentPosition);
                break;
            }
        }

        return currentPosition;
    }
}
