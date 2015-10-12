package kg.jarkyn;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
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
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);
        ui.getMove(validMoves);

        assertTrue(output.toString().contains("Player X, please select your move:"));
    }

    @Test
    public void getsMove() {
        CommandLineUi ui = setupUi("1");
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        assertEquals(0, ui.getMove(validMoves));
    }

    @Test
    public void doesNotAcceptNonNumericMove() {
        CommandLineUi ui = setupUi("a\n1");
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        assertEquals(0, ui.getMove(validMoves));
    }

    @Test
    public void doesNotAcceptBlankInputAsMove() {
        CommandLineUi ui = setupUi("\n1");
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        assertEquals(0, ui.getMove(validMoves));
    }

    @Test
    public void notifiesOnNonNumericInputForMove() {
        CommandLineUi ui = setupUi("a\n1");
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        ui.getMove(validMoves);

        assertTrue(output.toString().contains(CommandLineUi.INVALID_OPTION));
    }

    @Test
    public void notifiesOnInvalidMove() {
        CommandLineUi ui = setupUi("10\n1");
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        ui.getMove(validMoves);

        assertTrue(output.toString().contains(CommandLineUi.INVALID_OPTION));
    }

    @Test
    public void updatesBoardViewBeforeEachMove() {
        String userInput = "1\n2\n3\n5\n4\n6\n8\n7\n9";
        CommandLineUi ui = new CommandLineUi(new CommandLine(inputStream(userInput), output));
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        ui.playGame();

        assertEquals(9, ui.boardUpdateCount());
    }

    @Test
    public void playsTillGameIsWon() {
        String userInput = "1\n4\n2\n5\n3";
        CommandLineUi ui = new CommandLineUi(new CommandLine(inputStream(userInput), output));
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        ui.playGame();

        assertFalse(ui.gameIsActive());
    }

    @Test
    public void playsTillDrawn() {
        String userInput = "1\n2\n3\n5\n4\n6\n8\n7\n9";
        CommandLineUi ui = new CommandLineUi(new CommandLine(inputStream(userInput), output));
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        ui.playGame();

        assertFalse(ui.gameIsActive());
    }

    @Test
    public void announcesWinner() {
        String userInput = "1\n4\n2\n5\n3";
        CommandLineUi ui = new CommandLineUi(new CommandLine(inputStream(userInput), output));
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        ui.playGame();

        assertTrue(output.toString().contains("Player X has won this game"));
    }

    @Test
    public void announcesDraw() {
        String userInput = "1\n2\n3\n5\n4\n6\n8\n7\n9";
        CommandLineUi ui = new CommandLineUi(new CommandLine(inputStream(userInput), output));
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        ui.playGame();

        assertTrue(output.toString().contains(CommandLineUi.DRAW_STATUS));
    }

    @Test
    public void announcesGameOver() {
        String userInput = "1\n4\n2\n5\n3";
        CommandLineUi ui = new CommandLineUi(new CommandLine(inputStream(userInput), output));
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        ui.playGame();

        assertTrue(output.toString().contains(CommandLineUi.GAME_OVER));
    }

    @Test
    public void doesNotAnnounceWinnerWhenDrawn() {
        String userInput = "1\n2\n3\n5\n4\n6\n8\n7\n9";
        CommandLineUi ui = new CommandLineUi(new CommandLine(inputStream(userInput), output));
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        ui.playGame();

        assertFalse(output.toString().contains("Player NONE has won this game"));
    }

    @Test
    public void doesNotAnnounceDrawWhenWon() {
        String userInput = "1\n4\n2\n5\n3";
        CommandLineUi ui = new CommandLineUi(new CommandLine(inputStream(userInput), output));
        Game game = new HumanOnlyGame(new Board());
        ui.setGame(game);

        ui.playGame();

        assertFalse(output.toString().contains(CommandLineUi.DRAW_STATUS));
    }
}