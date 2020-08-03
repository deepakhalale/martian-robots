package com.locusintellect.martian.robots;

import org.junit.Test;

public class MartianRobotMainTest {

    @Test
    public void shouldReadASampleTestScenarioWithoutAnyError() {
        MartianRobotMain.main("/test-scenario-1.txt");
    }

    @Test
    public void shouldSkipAnyNewLinesAndReadFileContentsWithoutAnyError() {
        MartianRobotMain.main("/test-scenario-2.txt");
    }

}