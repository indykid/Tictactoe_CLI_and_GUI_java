package kg.jarkyn;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static kg.jarkyn.Mark.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandLineUiTest {

    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
    }

    private ByteArrayInputStream inputStream(String userInput) {
        return new ByteArrayInputStream(userInput.getBytes());
    }

    private CommandLineUi setupUi(String userInput) {
        return new CommandLineUi(new CommandLine(inputStream(userInput), output));
    }

    @Test
    public void greets() {
        CommandLineUi ui = setupUi("irrelevant");

        ui.greet();

        assertEquals(CommandLineUi.GREETING + "\n", output.toString());
    }

    @Test
    public void promptsGameSelection() {
        CommandLineUi ui = setupUi("1");

        ui.selectGame();

        assertEquals(CommandLineUi.GAME_OPTIONS + "\n", output.toString());
    }

    @Test
    public void receivesGameOption() {
        CommandLineUi ui = setupUi("1");

        int option = ui.selectGame();

        assertEquals(1, option);
    }

    @Test
    public void notifiesOfInvalidGameOption() {
        CommandLineUi ui = setupUi("invalid\n1");
        ui.selectGame();
        assertTrue(output.toString().contains(CommandLineUi.INVALID_OPTION + "\n"));
    }

    @Test
    public void getsInputUntilValid() {
        CommandLineUi ui = setupUi("invalid\n1");
        assertEquals(1, ui.selectGame());
    }

    @Test
    public void displaysBoard() {
        CommandLineUi ui = setupUi("irrelevant");
        Board board = new Board().addMove(0, X);

        ui.displayBoard(board);

        assertEquals("  X |  2 |  3 \n" +
                     "--------------\n" +
                     "  4 |  5 |  6 \n" +
                     "--------------\n" +
                     "  7 |  8 |  9 \n" +
                                   "\n", output.toString());
    }

    @Test
    public void asksUserForMove() {
        CommandLineUi ui = setupUi("1");
        ui.getMove(X);
        assertEquals("Player X, please select your move\n", output.toString());
    }

    @Test
    public void getsMove() {
        CommandLineUi ui = setupUi("1");
        assertEquals(0, ui.getMove(X));
    }

    @Test
    public void doesNotAcceptNonNumericMove() {
        CommandLineUi ui = setupUi("a\n1");
        assertEquals(0, ui.getMove(X));
    }

    @Test
    public void doesNotAcceptBlankInputAsMove() {
        CommandLineUi ui = setupUi("\n1");
        assertEquals(0, ui.getMove(X));
    }

    @Test
    public void notifiesOnInvalidMove() {
        CommandLineUi ui = setupUi("a\n1");

        ui.getMove(X);

        assertTrue(output.toString().contains("Invalid input, please try again"));
    }
}
