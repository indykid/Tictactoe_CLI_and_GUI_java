package kg.jarkyn;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class CliTest {

    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
    }

    private Cli setupCli(String inputString) {
        return new Cli(inputStream(inputString), output);
    }

    private ByteArrayInputStream inputStream(String inputString) {
        return new ByteArrayInputStream(inputString.getBytes());
    }

    @Test
    public void printsToTheOutput() {
        Cli cli = setupCli("irrelevant");

        cli.show("output");

        assertEquals("output\n", output.toString());
    }

    @Test
    public void getsInput() {
        Cli cli = setupCli("input");

        String userInput = cli.getInput();

        assertEquals("input", userInput);
    }
}
