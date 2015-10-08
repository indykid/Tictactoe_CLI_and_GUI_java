package kg.jarkyn;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import static kg.jarkyn.Mark.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandLineUiTest {

    private ByteArrayOutputStream output;
    private List<Integer>         validMoves;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        validMoves = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    private ByteArrayInputStream inputStream(String userInput) {
        return new ByteArrayInputStream(userInput.getBytes());
    }

    private CommandLineUi setupUi(String userInput) {
        return new CommandLineUi(new CommandLine(inputStream(userInput), output));
    }

    @Test
    public void greets() {
        CommandLineUi ui = setupUi("1");

        ui.selectGame();

        assertTrue(output.toString().contains(CommandLineUi.GREETING));
    }

    @Test
    public void promptsGameSelection() {
        CommandLineUi ui = setupUi("1");

        ui.selectGame();

        assertTrue(output.toString().contains(CommandLineUi.GAME_SELECTION_MESSAGE));
    }

    @Test
    public void receivesGameOption() {
        CommandLineUi ui = setupUi("1");

        int option = ui.selectGame();

        assertEquals(1, option);
    }

    @Test
    public void notifiesOfInvalidGameSelectionOption() {
        CommandLineUi ui = setupUi("invalid\n1");
        ui.selectGame();

        assertTrue(output.toString().contains(CommandLineUi.INVALID_OPTION));
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
        ui.getMove(X, validMoves);

        assertTrue(output.toString().contains("Player X, please select your move:"));
    }

    @Test
    public void getsMove() {
        CommandLineUi ui = setupUi("1");

        assertEquals(0, ui.getMove(X, validMoves));
    }

    @Test
    public void doesNotAcceptNonNumericMove() {
        CommandLineUi ui = setupUi("a\n1");

        assertEquals(0, ui.getMove(X, validMoves));
    }

    @Test
    public void doesNotAcceptBlankInputAsMove() {
        CommandLineUi ui = setupUi("\n1");

        assertEquals(0, ui.getMove(X, validMoves));
    }

    @Test
    public void notifiesOnNonNumericInputForMove() {
        CommandLineUi ui = setupUi("a\n1");

        ui.getMove(X, validMoves);

        assertTrue(output.toString().contains(CommandLineUi.INVALID_OPTION));
    }

    @Test
    public void notifiesOnInvalidMove() {
        CommandLineUi ui = setupUi("10\n1");

        ui.getMove(X, validMoves);

        assertTrue(output.toString().contains(CommandLineUi.INVALID_OPTION));
    }

    @Test
    public void announcesGameOver() {
        CommandLineUi ui = setupUi("irrelevant");
        ui.announceGameOver();

        assertTrue(output.toString().contains(CommandLineUi.GAME_OVER));
    }

    @Test
    public void announcesGivenWinner() {
        CommandLineUi ui = setupUi("irrelevant");

        ui.announceWinner(X);

        assertTrue(output.toString().contains("Player X has won this game"));
    }

    @Test
    public void announcesDraw() {
        CommandLineUi ui = setupUi("irrelevant");

        ui.announceDraw();

        assertTrue(output.toString().contains(CommandLineUi.DRAW_STATUS));
    }
}