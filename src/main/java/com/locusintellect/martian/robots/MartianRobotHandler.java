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

public class MartianRobotHandler {

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
        }

        return currentPosition;
    }
}
