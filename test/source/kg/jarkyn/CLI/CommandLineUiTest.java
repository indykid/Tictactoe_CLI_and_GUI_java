package kg.jarkyn.CLI;

import kg.jarkyn.CLI.CommandLine;
import kg.jarkyn.CLI.CommandLineUi;
import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.Game;
import kg.jarkyn.Core.HumanPlayer;
import kg.jarkyn.Core.Ui;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static kg.jarkyn.Core.Mark.O;
import static kg.jarkyn.Core.Mark.X;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandLineUiTest {

    private List<Integer>         validMoves;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        validMoves = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    private Game setupGame(Ui ui) {
        return new Game(new Board(), new HumanPlayer(X, ui), new HumanPlayer(O, ui));
    }

    private ByteArrayInputStream inputStream(String userInput) {
        return new ByteArrayInputStream(userInput.getBytes());
    }

    private CommandLineUi setupUi(String userInput) {
        return new CommandLineUi(new CommandLine(inputStream(userInput), output));
    }

    @Test
    public void greets() {
        Ui ui = setupUi("1");

        ui.selectGame();

        assertTrue(output.toString().contains(CommandLineUi.GREETING));
    }

    @Test
    public void promptsGameSelection() {
        Ui ui = setupUi("1");

        ui.selectGame();

        assertTrue(output.toString().contains(CommandLineUi.GAME_SELECTION_MESSAGE));
    }

    @Test
    public void receivesGameOption() {
        Ui ui = setupUi("1");

        int option = ui.selectGame();

        assertEquals(1, option);
    }

    @Test
    public void notifiesOfInvalidGameSelectionOption() {
        Ui ui = setupUi("invalid\n1");
        ui.selectGame();

        assertTrue(output.toString().contains(CommandLineUi.INVALID_OPTION));
    }

    @Test
    public void getsInputUntilValid() {
        Ui ui = setupUi("invalid\n1");
        assertEquals(1, ui.selectGame());
    }

    @Test
    public void displaysBoardBeforeAskingForMove() {
        Ui ui = setupUi("1");
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
        Ui ui = setupUi("1");
        ui.setGame(setupGame(ui));
        ui.getMove(validMoves);

        assertTrue(output.toString().contains("Player X, please select your move:"));
    }

    @Test
    public void getsMove() {
        Ui ui = setupUi("1");
        ui.setGame(setupGame(ui));

        assertEquals(0, ui.getMove(validMoves));
    }

    @Test
    public void doesNotAcceptNonNumericMove() {
        Ui ui = setupUi("a\n1");
        ui.setGame(setupGame(ui));

        assertEquals(0, ui.getMove(validMoves));
    }

    @Test
    public void doesNotAcceptBlankInputAsMove() {
        Ui ui = setupUi("\n1");
        ui.setGame(setupGame(ui));

        assertEquals(0, ui.getMove(validMoves));
    }

    @Test
    public void notifiesOnNonNumericInputForMove() {
        Ui ui = setupUi("a\n1");
        ui.setGame(setupGame(ui));

        ui.getMove(validMoves);

        assertTrue(output.toString().contains(CommandLineUi.INVALID_OPTION));
    }

    @Test
    public void notifiesOnInvalidMove() {
        Ui ui = setupUi("10\n1");
        ui.setGame(setupGame(ui));

        ui.getMove(validMoves);

        assertTrue(output.toString().contains(CommandLineUi.INVALID_OPTION));
    }

    @Test
    public void playsTillGameIsWon() {
        CommandLineUi ui = new CommandLineUi(new CommandLine(inputStream("1\n4\n2\n5\n3"), output));
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertFalse(ui.gameIsActive());
    }

    @Test
    public void playsTillDrawn() {
        CommandLineUi ui = new CommandLineUi(new CommandLine(inputStream("1\n2\n3\n5\n4\n6\n8\n7\n9"), output));
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertFalse(ui.gameIsActive());
    }

    @Test
    public void announcesWinner() {
        Ui ui = setupUi("1\n4\n2\n5\n3");
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertTrue(output.toString().contains("Player X has won this game"));
    }

    @Test
    public void announcesDraw() {
        Ui ui = setupUi("1\n2\n3\n5\n4\n6\n8\n7\n9");
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertTrue(output.toString().contains(CommandLineUi.DRAW_STATUS));
    }

    @Test
    public void announcesGameOver() {
        Ui ui = new CommandLineUi(new CommandLine(inputStream("1\n4\n2\n5\n3"), output));
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertTrue(output.toString().contains(CommandLineUi.GAME_OVER));
    }

    @Test
    public void showsBoardAtTheEnd() {
        Ui ui = new CommandLineUi(new CommandLine(inputStream("1\n4\n2\n5\n3"), output));
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertTrue(output.toString().contains("  X |  X |  X \n" +
                "--------------\n" +
                "  O |  O |  6 \n" +
                "--------------\n" +
                "  7 |  8 |  9 \n"));
    }

    @Test
    public void doesNotAnnounceWinnerWhenDrawn() {
        Ui ui = new CommandLineUi(new CommandLine(inputStream("1\n2\n3\n5\n4\n6\n8\n7\n9"), output));
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertFalse(output.toString().contains("Player NONE has won this game"));
    }

    @Test
    public void doesNotAnnounceDrawWhenWon() {
        Ui ui = new CommandLineUi(new CommandLine(inputStream("1\n4\n2\n5\n3"), output));
        ui.setGame(setupGame(ui));

        ui.playGame();

        assertFalse(output.toString().contains(CommandLineUi.DRAW_STATUS));
    }
}