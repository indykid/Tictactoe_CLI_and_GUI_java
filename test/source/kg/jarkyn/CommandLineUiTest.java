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
    private List<Integer>         gameOptions;
    private List<Integer>         validMoves;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        gameOptions = Arrays.asList(1, 2, 3);
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
        CommandLineUi ui = setupUi("irrelevant");

        ui.greet();

        assertEquals(CommandLineUi.GREETING + "\n", output.toString());
    }

    @Test
    public void promptsGameSelection() {
        CommandLineUi ui = setupUi("1");

        ui.selectGame(gameOptions);

        assertEquals(CommandLineUi.GAME_OPTIONS + "\n", output.toString());
    }

    @Test
    public void receivesGameOption() {
        CommandLineUi ui = setupUi("1");

        int option = ui.selectGame(gameOptions);

        assertEquals(1, option);
    }

    @Test
    public void notifiesOfInvalidGameOption() {
        CommandLineUi ui = setupUi("invalid\n1");
        ui.selectGame(gameOptions);

        assertTrue(output.toString().contains(CommandLineUi.INVALID_OPTION + "\n"));
    }

    @Test
    public void getsInputUntilValid() {
        CommandLineUi ui = setupUi("invalid\n1");
        assertEquals(1, ui.selectGame(gameOptions));
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

        assertEquals("Player X, please select your move\n", output.toString());
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
    public void notifiesOnInvalidMove() {
        CommandLineUi ui = setupUi("a\n1");

        ui.getMove(X, validMoves);

        assertTrue(output.toString().contains("Invalid input, please try again"));
    }

    @Test
    public void announcesGameOver() {
        CommandLineUi ui = setupUi("irrelevant");
        ui.announceGameOver();

    }

    @Test
    public void announcesGivenWinner() {
        CommandLineUi ui = setupUi("irrelevant");

        ui.announceWinner(X);

        assertEquals("Player X has won this game\n", output.toString());
    }

    @Test
    public void announcesDraw() {
        CommandLineUi ui = setupUi("irrelevant");

        ui.announceDraw();

        assertEquals("It's a draw\n", output.toString());
    }
}