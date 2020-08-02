package com.locusintellect.martian.robots;

import static com.locusintellect.martian.robots.commands.Instruction.FORWARD;
import static com.locusintellect.martian.robots.commands.Instruction.RIGHT;
import static com.locusintellect.martian.robots.domain.Coordinates.newCoordinates;
import static com.locusintellect.martian.robots.domain.Orientation.EAST;
import static com.locusintellect.martian.robots.domain.Position.newPosition;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.locusintellect.martian.robots.domain.Coordinates;
import com.locusintellect.martian.robots.domain.FinalPosition;
import com.locusintellect.martian.robots.domain.Orientation;
import org.junit.Test;

public class MartianRobotHandlerTest {

    private static final Coordinates LOWER_LEFT_COORDINATES = newCoordinates(0, 0);

    @Test
    public void shouldProvideFinalPositionOfTheRobotWhenInitialPositionAndInstructionsAreSupplied() {
        final Coordinates upperRightCoordinates = newCoordinates(5, 3);
        final MartianRobotHandler underTest = new MartianRobotHandler(LOWER_LEFT_COORDINATES, upperRightCoordinates);

        final FinalPosition actualFinalPosition = underTest.sendInstructions(
                newPosition(newCoordinates(1, 1), EAST),
                asList(RIGHT, FORWARD, RIGHT, FORWARD, RIGHT, FORWARD, RIGHT, FORWARD));

        verifyFinalPositionOfTheRobotAs(1, 1, EAST, actualFinalPosition);
    }

    private void verifyFinalPositionOfTheRobotAs(final int expectedXCoordinate, final int expectedYCoordinate,
                                                 final Orientation expectedOrientation, final FinalPosition actualFinalPosition) {
        assertThat(actualFinalPosition.getCoordinates().getX(), is(expectedXCoordinate));
        assertThat(actualFinalPosition.getCoordinates().getY(), is(expectedYCoordinate));
        assertThat(actualFinalPosition.getOrientation(), is(expectedOrientation));
        assertThat(actualFinalPosition.isLost(), is(false));
    }

}