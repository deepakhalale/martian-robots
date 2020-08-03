package com.locusintellect.martian.robots;

import static org.junit.rules.ExpectedException.none;

import com.locusintellect.martian.robots.exceptions.InvalidFileContentsException;
import com.locusintellect.martian.robots.validators.InstructionsValidator;
import com.locusintellect.martian.robots.validators.PositionValidator;
import com.locusintellect.martian.robots.validators.UpperRightCoordinatesValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MartianRobotInstructionsScannerTest {

    private final UpperRightCoordinatesValidator upperRightCoordinatesValidator = new UpperRightCoordinatesValidator();
    private final PositionValidator positionValidator = new PositionValidator();
    private final InstructionsValidator instructionsValidator = new InstructionsValidator();

    private MartianRobotInstructionsScanner underTest;

    @Rule
    public ExpectedException expectedException = none();

    @Test
    public void shouldThrowExceptionWhenFileContentsAreEmpty() {
        expectedException.expect(InvalidFileContentsException.class);
        expectedException.expectMessage("Upper right coordinates is null or empty. Should specify a pair of integers i.e. x-coordinate followed by y-coordinate");

        underTest = new MartianRobotInstructionsScanner("/error-scenarios/empty-file-scenario.txt",
                upperRightCoordinatesValidator, positionValidator, instructionsValidator);

        underTest.scan();
    }

    @Test
    public void shouldThrowExceptionWhenRobotPositionAndInstructionsAreMissingInFile() {
        expectedException.expect(InvalidFileContentsException.class);
        expectedException.expectMessage("Robot position and instructions missing in file /error-scenarios/missing-position-and-instructions-scenario.txt");

        underTest = new MartianRobotInstructionsScanner("/error-scenarios/missing-position-and-instructions-scenario.txt",
                upperRightCoordinatesValidator, positionValidator, instructionsValidator);

        underTest.scan();
    }

    @Test
    public void shouldThrowExceptionWhenRobotInstructionsAreMissingInFile() {
        expectedException.expect(InvalidFileContentsException.class);
        expectedException.expectMessage("Instruction is null or empty. A robot instruction is a string of the letters \"L\", \"R\" and \"F\" on one line.");

        underTest = new MartianRobotInstructionsScanner("/error-scenarios/missing-instructions-scenario.txt",
                upperRightCoordinatesValidator, positionValidator, instructionsValidator);

        underTest.scan();
    }

}