package kg.jarkyn;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static kg.jarkyn.Mark.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UiTest {

    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
    }

    private ByteArrayInputStream inputStream(String userInput) {
        return new ByteArrayInputStream(userInput.getBytes());
    }

    private Ui setupUi(String userInput) {
        return new Ui(new Cli(inputStream(userInput), output));
    }

    @Test
    public void greets() {
        Ui ui = setupUi("irrelevant");

        ui.greet();

        assertEquals(Ui.GREETING + "\n", output.toString());
    }

    @Test
    public void promptsGameSelection() {
        Ui ui = setupUi("1");

        ui.selectGame();

        assertEquals(Ui.GAME_OPTIONS + "\n", output.toString());
    }

    @Test
    public void receivesGameOption() {
        Ui ui = setupUi("1");

        int option = ui.selectGame();

        assertEquals(1, option);
    }

    @Test
    public void notifiesOfInvalidGameOption() {
        Ui ui = setupUi("invalid\n1");
        ui.selectGame();
        assertTrue(output.toString().contains(Ui.INVALID_OPTION + "\n"));
    }

    @Test
    public void getsInputUntilValid() {
        Ui ui = setupUi("invalid\n1");
        assertEquals(1, ui.selectGame());
    }

    @Test
    public void displaysBoard() {
        Ui ui = setupUi("irrelevant");
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
        Ui ui = setupUi("1");
        ui.getMove(X);
        assertEquals("Player X, please select your move\n", output.toString());
    }

    @Test
    public void getsMove() {
        Ui ui = setupUi("1");
        assertEquals(0, ui.getMove(X));
    }

    @Test
    public void doesNotAcceptNonNumericMove() {
        Ui ui = setupUi("a\n1");
        assertEquals(0, ui.getMove(X));
    }

    @Test
    public void doesNotAcceptBlankInputAsMove() {
        Ui ui = setupUi("\n1");
        assertEquals(0, ui.getMove(X));
    }

    @Test
    public void notifiesOnInvalidMove() {
        Ui ui = setupUi("a\n1");

        ui.getMove(X);

        assertTrue(output.toString().contains("Invalid input, please try again"));
    }
}
