package com.locusintellect.martian.robots;

import static com.locusintellect.martian.robots.domain.Coordinates.newCoordinates;
import static com.locusintellect.martian.robots.domain.Orientation.EAST;
import static com.locusintellect.martian.robots.domain.Orientation.NORTH;
import static com.locusintellect.martian.robots.domain.Position.newPosition;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.locusintellect.martian.robots.commands.MoveForward;
import com.locusintellect.martian.robots.commands.MoveLeft;
import com.locusintellect.martian.robots.commands.MoveRight;
import com.locusintellect.martian.robots.domain.Coordinates;
import com.locusintellect.martian.robots.domain.FinalPosition;
import com.locusintellect.martian.robots.domain.Orientation;
import com.locusintellect.martian.robots.domain.Position;
import org.junit.Test;

public class MartianRobotCommandExecutorTest {

    private static final Coordinates LOWER_LEFT_COORDINATES = newCoordinates(0, 0);

    private final MartianRobotCommandExecutor underTest = new MartianRobotCommandExecutor();

    @Test
    public void shouldProvideFinalPositionOfTheRobotWhenInitialPositionAndInstructionsAreSupplied() {
        final Coordinates upperRightCoordinates = newCoordinates(5, 3);
        final MoveRight moveRight = new MoveRight();
        final MoveForward moveForward = new MoveForward(LOWER_LEFT_COORDINATES, upperRightCoordinates);

        final FinalPosition actualFinalPosition = underTest.executeCommands(
                newPosition(newCoordinates(1, 1), EAST),
                asList(moveRight, moveForward, moveRight, moveForward, moveRight, moveForward, moveRight, moveForward));

        verifyFinalPositionOfTheRobotAs(1, 1, EAST, actualFinalPosition);
    }

    @Test
    public void shouldProvideLastKnownPositionWhenRobotFallsOffTheEdgeOfTheGrid() {
        final Coordinates upperRightCoordinates = newCoordinates(5, 3);
        final MoveRight moveRight = new MoveRight();
        final MoveLeft moveLeft = new MoveLeft();
        final MoveForward moveForward = new MoveForward(LOWER_LEFT_COORDINATES, upperRightCoordinates);

        final Position initialPosition = newPosition(newCoordinates(3, 2), NORTH);
        final FinalPosition actualFinalPosition = underTest.executeCommands(initialPosition,
                asList(moveForward, moveRight, moveRight, moveForward, moveLeft, moveLeft, moveForward, moveForward, moveRight, moveRight, moveForward, moveLeft, moveLeft));

        verifyLastKnownPositionOfTheLostRobotAs(3, 3, NORTH, actualFinalPosition);
    }

    private void verifyLastKnownPositionOfTheLostRobotAs(final int expectedXCoordinate, final int expectedYCoordinate,
                                                         final Orientation expectedOrientation, final FinalPosition actualFinalPosition) {
        assertThat(actualFinalPosition.getCoordinates().getX(), is(expectedXCoordinate));
        assertThat(actualFinalPosition.getCoordinates().getY(), is(expectedYCoordinate));
        assertThat(actualFinalPosition.getOrientation(), is(expectedOrientation));
        assertThat(actualFinalPosition.isLost(), is(true));
    }

    private void verifyFinalPositionOfTheRobotAs(final int expectedXCoordinate, final int expectedYCoordinate,
                                                 final Orientation expectedOrientation, final FinalPosition actualFinalPosition) {
        assertThat(actualFinalPosition.getCoordinates().getX(), is(expectedXCoordinate));
        assertThat(actualFinalPosition.getCoordinates().getY(), is(expectedYCoordinate));
        assertThat(actualFinalPosition.getOrientation(), is(expectedOrientation));
        assertThat(actualFinalPosition.isLost(), is(false));
    }

}