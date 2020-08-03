package com.locusintellect.martian.robots;

import static com.locusintellect.martian.robots.utils.RobotPositionPrinter.robotPositionDisplayString;
import static java.lang.System.lineSeparator;

import com.locusintellect.martian.robots.domain.FinalPosition;
import com.locusintellect.martian.robots.domain.MartianRobotsInstructions;
import com.locusintellect.martian.robots.domain.RobotInstructions;
import com.locusintellect.martian.robots.validators.InstructionsValidator;
import com.locusintellect.martian.robots.validators.PositionValidator;
import com.locusintellect.martian.robots.validators.UpperRightCoordinatesValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MartianRobotMain {

    private static final Logger LOG = LoggerFactory.getLogger(MartianRobotMain.class);

    public static void main(final String... args) {
        if (args.length <= 0) {
            LOG.error("File path missing");
            LOG.info(getUsageInfo());
            return;
        }

        final MartianRobotInstructionsScanner scanner = new MartianRobotInstructionsScanner(args[0], new UpperRightCoordinatesValidator(),
                new PositionValidator(), new InstructionsValidator());

        final MartianRobotsInstructions martianRobotsInstructions = scanner.scan();

        final MartianRobotCommandExecutor commandExecutor = new MartianRobotCommandExecutor();
        for (final RobotInstructions robotInstructions : martianRobotsInstructions.getRobotInstructions()) {
            final FinalPosition finalPosition = commandExecutor.executeCommands(robotInstructions.getPosition(), robotInstructions.getCommands());
            System.out.println(robotPositionDisplayString(finalPosition));
        }
    }

    private static String getUsageInfo() {
        return "Usage:" + lineSeparator() +
                "java -jar martian-robots-jar-with-dependencies.jar <relative/absolute path to the file with instructions to Martian Robots>";
    }
}
