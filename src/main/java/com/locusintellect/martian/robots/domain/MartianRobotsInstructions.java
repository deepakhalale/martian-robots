package com.locusintellect.martian.robots.domain;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class MartianRobotsInstructions {

    private final Coordinates upperRightCoordinates;
    private final List<RobotInstructions> robotInstructions = newArrayList();

    public MartianRobotsInstructions(final Coordinates upperRightCoordinates) {
        this.upperRightCoordinates = upperRightCoordinates;
    }

    public Coordinates getUpperRightCoordinates() {
        return upperRightCoordinates;
    }

    public List<RobotInstructions> getRobotInstructions() {
        return ImmutableList.copyOf(robotInstructions);
    }

    public MartianRobotsInstructions addRobotInstructions(final RobotInstructions robotInstructions) {
        this.robotInstructions.add(robotInstructions);
        return this;
    }
}
