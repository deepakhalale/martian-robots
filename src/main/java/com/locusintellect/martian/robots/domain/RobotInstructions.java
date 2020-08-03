package com.locusintellect.martian.robots.domain;

import static com.google.common.collect.ImmutableList.copyOf;

import java.util.List;

import com.locusintellect.martian.robots.commands.Command;

public class RobotInstructions {

    private final Position position;
    private final List<Command> commands;

    public RobotInstructions(final Position position, final List<Command> commands) {
        this.position = position;
        this.commands = commands;
    }

    public Position getPosition() {
        return position;
    }

    public List<Command> getCommands() {
        return copyOf(commands);
    }
}
