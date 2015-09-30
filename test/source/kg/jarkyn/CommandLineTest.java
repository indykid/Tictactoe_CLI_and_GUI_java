package kg.jarkyn;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class CommandLineTest {

    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
    }

    private CommandLine setupCommandLine(String inputString) {
        return new CommandLine(inputStream(inputString), output);
    }

    private ByteArrayInputStream inputStream(String inputString) {
        return new ByteArrayInputStream(inputString.getBytes());
    }

    @Test
    public void printsToTheOutput() {
        CommandLine commandLine = setupCommandLine("irrelevant");

        commandLine.show("output");

        assertEquals("output", output.toString());
    }

    @Test
    public void getsInput() {
        CommandLine commandLine = setupCommandLine("input");

        String userInput = commandLine.getInput();

        assertEquals("input", userInput);
    }
}
