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

    @Test
    public void printsToTheOutput() {
        ByteArrayInputStream input = new ByteArrayInputStream("irrelevant".getBytes());
        Cli cli = new Cli(output, input);
        cli.show("output");

        assertEquals("output\n", output.toString());
    }

    @Test
    public void getsInput() {
        ByteArrayInputStream input = new ByteArrayInputStream("input".getBytes());
        Cli cli = new Cli(output, input);
        String userInput = cli.getInput();

        assertEquals("input", userInput);
    }
}
