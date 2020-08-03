package com.locusintellect.martian.robots;

import static com.google.common.collect.Lists.newArrayList;
import static com.locusintellect.martian.robots.domain.FinalPosition.newFinalPosition;

import java.util.List;

import com.locusintellect.martian.robots.commands.Command;
import com.locusintellect.martian.robots.domain.FinalPosition;
import com.locusintellect.martian.robots.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MartianRobotCommandExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(MartianRobotCommandExecutor.class);
    private final List<Position> lostRobots = newArrayList();

    public FinalPosition executeCommands(final Position initialPosition, final List<Command> commands) {
        FinalPosition currentPosition = newFinalPosition(initialPosition);
        for (final Command command : commands) {
            currentPosition = command.move(currentPosition);

            if (currentPosition.isLost()) {
                if (lostRobots.contains(currentPosition)) {
                    currentPosition = newFinalPosition(currentPosition);
                } else {
                    lostRobots.add(currentPosition);
                    LOG.info("Martian Robot at initial position {} fell off the edge of the grid at {}", initialPosition, currentPosition);
                    break;
                }
            }
        }

        return currentPosition;
    }
}
