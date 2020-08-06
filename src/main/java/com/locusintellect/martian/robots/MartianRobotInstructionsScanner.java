package com.locusintellect.martian.robots;

import static com.google.common.collect.Lists.newArrayList;
import static com.locusintellect.martian.robots.domain.Coordinates.newCoordinates;
import static com.locusintellect.martian.robots.domain.Position.newPosition;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.locusintellect.martian.robots.commands.Command;
import com.locusintellect.martian.robots.commands.CommandFactory;
import com.locusintellect.martian.robots.domain.Coordinates;
import com.locusintellect.martian.robots.domain.MartianRobotsInstructions;
import com.locusintellect.martian.robots.domain.Position;
import com.locusintellect.martian.robots.domain.RobotInstructions;
import com.locusintellect.martian.robots.exceptions.InstructionsFileNotFound;
import com.locusintellect.martian.robots.exceptions.InvalidFileContentsException;
import com.locusintellect.martian.robots.exceptions.MalformedFileContentsException;
import com.locusintellect.martian.robots.validators.InstructionsValidator;
import com.locusintellect.martian.robots.validators.PositionValidator;
import com.locusintellect.martian.robots.validators.UpperRightCoordinatesValidator;
import com.locusintellect.martian.robots.validators.ValidationResult;

public class MartianRobotInstructionsScanner {

    private final String filePath;
    private final UpperRightCoordinatesValidator upperRightCoordinatesValidator;
    private final PositionValidator positionValidator;
    private final InstructionsValidator instructionsValidator;

    public MartianRobotInstructionsScanner(final String filePath, final UpperRightCoordinatesValidator upperRightCoordinatesValidator,
                                           final PositionValidator positionValidator, final InstructionsValidator instructionsValidator) {
        this.filePath = filePath;
        this.upperRightCoordinatesValidator = upperRightCoordinatesValidator;
        this.positionValidator = positionValidator;
        this.instructionsValidator = instructionsValidator;
    }

    public MartianRobotsInstructions scan() {
        final MartianRobotsInstructions martianRobotsInstructions;

        final InputStreamReader streamReader = tryReadingTheFileFromClasspathOrFilesystem();
        try (final BufferedReader reader = new BufferedReader(streamReader)) {
            String line = reader.readLine();
            final Coordinates upperRight = validateAndGetUpperRightCoordinates(line);
            martianRobotsInstructions = new MartianRobotsInstructions(upperRight);
            while ((line = reader.readLine()) != null) {
                if (isNotEmpty(line)) {
                    final Position position = validateAndGetPosition(line);

                    line = reader.readLine();
                    final List<Command> commands = validateAndGetCommands(line, upperRight);
                    martianRobotsInstructions.addRobotInstructions(new RobotInstructions(position, commands));
                }
            }
        } catch (IOException e) {
            throw new MalformedFileContentsException(format("Contents of the file %s could not be read.", filePath), e);
        }

        if (martianRobotsInstructions.getRobotInstructions().isEmpty()) {
            throw new InvalidFileContentsException(format("Robot position and instructions missing in file %s", filePath));
        }

        return martianRobotsInstructions;
    }

    private InputStreamReader tryReadingTheFileFromClasspathOrFilesystem() {
        final InputStream resource = getClass().getResourceAsStream(filePath);
        final InputStreamReader streamReader;
        if (resource != null) {
            streamReader = new InputStreamReader(resource);
        } else {
            try {
                streamReader = new FileReader(filePath);
            } catch (FileNotFoundException e) {
                throw new InstructionsFileNotFound(format("File at path %s cannot be found", filePath), e);
            }
        }
        return streamReader;
    }

    private Coordinates validateAndGetUpperRightCoordinates(final String line) {
        final ValidationResult result = upperRightCoordinatesValidator.validate(line);
        if (!result.isSuccess()) {
            throw new InvalidFileContentsException(result.getErrorMessage());
        }
        return newCoordinates(line);
    }

    private Position validateAndGetPosition(final String line) {
        final ValidationResult result = positionValidator.validate(line);
        if (!result.isSuccess()) {
            throw new InvalidFileContentsException(result.getErrorMessage());
        }
        return newPosition(line);
    }

    private List<Command> validateAndGetCommands(final String line, final Coordinates upperRight) {
        final List<Command> commands = newArrayList();
        final ValidationResult result = instructionsValidator.validate(line);
        if (!result.isSuccess()) {
            throw new InvalidFileContentsException(result.getErrorMessage());
        }
        final CommandFactory factory = new CommandFactory(upperRight);
        for (final char instruction : line.toCharArray()) {
            commands.add(factory.getCommand(instruction));
        }

        return commands;
    }
}
