package kg.ui.CLI;

import kg.jarkyn.Board;
import kg.jarkyn.Game;
import kg.jarkyn.HumanInput;
import kg.jarkyn.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static kg.jarkyn.Mark.O;
import static kg.jarkyn.Mark.X;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandLineUITest {

    private List<Integer>         validMoves;
    private ByteArrayOutputStream output;

    private Game setupGame(HumanInput input) {
        return new Game(new Board(), new HumanPlayer(X, input), new HumanPlayer(O, input));
    }

    private ByteArrayInputStream inputStream(String userInput) {
        return new ByteArrayInputStream(userInput.getBytes());
    }

    private CommandLineUI setupUi(String userInput) {
        return new CommandLineUI(new CommandLine(inputStream(userInput), output));
    }

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        validMoves = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    @Test
    public void greets() {
        CommandLineUI ui = setupUi("1");

        ui.selectGame();

        assertTrue(output.toString().contains(CommandLineUI.GREETING));
    }

    @Test
    public void promptsGameSelection() {
        CommandLineUI ui = setupUi("1");

        ui.selectGame();

        assertTrue(output.toString().contains(CommandLineUI.GAME_SELECTION_MESSAGE));
    }

    @Test
    public void receivesGameOption() {
        CommandLineUI ui = setupUi("1");

        int option = ui.selectGame();

        assertEquals(1, option);
    }

    @Test
    public void notifiesOfInvalidGameSelectionOption() {
        CommandLineUI ui = setupUi("invalid\n1");
        ui.selectGame();

        assertTrue(output.toString().contains(CommandLineUI.INVALID_OPTION));
    }

    @Test
    public void getsInputUntilValid() {
        CommandLineUI ui = setupUi("invalid\n1");
        assertEquals(1, ui.selectGame());
    }

    @Test
    public void displaysBoardBeforeAskingForMove() {
        CommandLineUI ui = setupUi("1");
        ui.setGame(setupGame(ui));

        ui.getMove(validMoves);

        assertTrue(output.toString().contains("  1 |  2 |  3 \n" +
                "--------------\n" +
                "  4 |  5 |  6 \n" +
                "--------------\n" +
                "  7 |  8 |  9 \n"));
    }

    @Test
    public void asksUserForMove() {
        CommandLineUI ui = setupUi("1");
        ui.setGame(setupGame(ui));
        ui.getMove(validMoves);

        assertTrue(output.toString().contains("Player X, please select your move:"));
    }

    @Test
    public void getsMove() {
        CommandLineUI ui = setupUi("1");
        ui.setGame(setupGame(ui));

        assertEquals(0, ui.getMove(validMoves));
    }

    @Test
    public void doesNotAcceptNonNumericMove() {
        CommandLineUI ui = setupUi("a\n1");
        ui.setGame(setupGame(ui));

        assertEquals(0, ui.getMove(validMoves));
    }

    @Test
    public void doesNotAcceptBlankInputAsMove() {
        CommandLineUI ui = setupUi("\n1");
        ui.setGame(setupGame(ui));

        assertEquals(0, ui.getMove(validMoves));
    }

    @Test
    public void notifiesOnNonNumericInputForMove() {
        CommandLineUI ui = setupUi("a\n1");
        ui.setGame(setupGame(ui));

        ui.getMove(validMoves);

        assertTrue(output.toString().contains(CommandLineUI.INVALID_OPTION));
    }

    @Test
    public void notifiesOnInvalidMove() {
        CommandLineUI ui = setupUi("10\n1");
        ui.setGame(setupGame(ui));

        ui.getMove(validMoves);

        assertTrue(output.toString().contains(CommandLineUI.INVALID_OPTION));
    }

    @Test
    public void announcesWinner() {
        CommandLineUI ui = setupUi("1\n4\n2\n5\n3");
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertTrue(output.toString().contains("Player X has won this game"));
    }

    @Test
    public void announcesDraw() {
        CommandLineUI ui = setupUi("1\n2\n3\n5\n4\n6\n8\n7\n9");
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertTrue(output.toString().contains(CommandLineUI.DRAW_STATUS));
    }

    @Test
    public void announcesGameOver() {
        CommandLineUI ui = new CommandLineUI(new CommandLine(inputStream("1\n4\n2\n5\n3"), output));
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertTrue(output.toString().contains(CommandLineUI.GAME_OVER));
    }

    @Test
    public void showsBoardAtTheEnd() {
        CommandLineUI ui = new CommandLineUI(new CommandLine(inputStream("1\n4\n2\n5\n3"), output));
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertTrue(output.toString().contains(
                "  X |  X |  X \n" +
                "--------------\n" +
                "  O |  O |  6 \n" +
                "--------------\n" +
                "  7 |  8 |  9 \n"));
    }

    @Test
    public void doesNotAnnounceWinnerWhenDrawn() {
        CommandLineUI ui = new CommandLineUI(new CommandLine(inputStream("1\n2\n3\n5\n4\n6\n8\n7\n9"), output));
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertFalse(output.toString().contains("Player NONE has won this game"));
    }

    @Test
    public void doesNotAnnounceDrawWhenWon() {
        CommandLineUI ui = new CommandLineUI(new CommandLine(inputStream("1\n4\n2\n5\n3"), output));
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertFalse(output.toString().contains(CommandLineUI.DRAW_STATUS));
    }

    @Test
    public void alwaysHasHumanMove() {
        CommandLineUI ui = setupUi("irrelevant");

        assertTrue(ui.hasHumanMove());
    }
}