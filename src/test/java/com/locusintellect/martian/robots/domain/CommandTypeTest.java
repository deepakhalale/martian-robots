package com.locusintellect.martian.robots.domain;

import static com.locusintellect.martian.robots.domain.CommandType.FORWARD;
import static com.locusintellect.martian.robots.domain.CommandType.LEFT;
import static com.locusintellect.martian.robots.domain.CommandType.RIGHT;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.rules.ExpectedException.none;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class CommandTypeTest {

    @DataProvider
    public static Object[][] validCommandTypes() {
        return new Object[][]{
                {'L', LEFT},
                {'R', RIGHT},
                {'F', FORWARD}
        };
    }

    @DataProvider
    public static Object[][] invalidCommandTypes() {
        return new Object[][]{
                {'A'},
                {'X'},
                {'Z'}
        };
    }

    @Rule
    public ExpectedException expectedException = none();

    @Test
    @UseDataProvider("validCommandTypes")
    public void shouldMapToValidCommandTypeFromTheCharacterValue(final char input, final CommandType expectedType) {
        assertThat(CommandType.from(input), is(expectedType));
    }

    @Test
    @UseDataProvider("invalidCommandTypes")
    public void shouldThrowExceptionForInvalidCharacter(final char input) {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(format("Invalid command type character %s", input));

        CommandType.from(input);
    }
}