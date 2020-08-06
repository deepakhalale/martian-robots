package com.locusintellect.martian.robots.commands;

import static com.locusintellect.martian.robots.domain.Grid.newGrid;

import java.util.EnumMap;

import com.locusintellect.martian.robots.domain.CommandType;
import com.locusintellect.martian.robots.domain.Coordinates;

public class CommandFactory {

    private final EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    public CommandFactory(final Coordinates upperRight) {
        final MoveLeft moveLeft = new MoveLeft();
        final MoveRight moveRight = new MoveRight();
        final MoveForward moveForward = new MoveForward(newGrid(upperRight));
        commands.put(moveLeft.getType(), moveLeft);
        commands.put(moveRight.getType(), moveRight);
        commands.put(moveForward.getType(), moveForward);
    }

    public Command getCommand(final char commandCharacter) {
        return commands.get(CommandType.from(commandCharacter));
    }
}
