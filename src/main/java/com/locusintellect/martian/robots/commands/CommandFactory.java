package com.locusintellect.martian.robots.commands;

import static com.locusintellect.martian.robots.domain.Coordinates.newCoordinates;
import static java.lang.String.format;

import com.locusintellect.martian.robots.domain.Coordinates;
import com.locusintellect.martian.robots.exceptions.InvalidInstructionException;

public class CommandFactory {

    private final MoveLeft moveLeft;
    private final MoveRight moveRight;
    private final MoveForward moveForward;

    public CommandFactory(final Coordinates upperRight) {
        this.moveLeft = new MoveLeft();
        this.moveRight = new MoveRight();
        this.moveForward = new MoveForward(newCoordinates(0, 0), upperRight);
    }

    public Command getInstance(final char commandCharacter) {
        final Command command;
        switch (commandCharacter) {
            case 'L':
                command = moveLeft;
                break;
            case 'R':
                command = moveRight;
                break;
            case 'F':
                command = moveForward;
                break;
            default:
                throw new InvalidInstructionException(format("Unknown command %s", commandCharacter));
        }
        return command;
    }
}
