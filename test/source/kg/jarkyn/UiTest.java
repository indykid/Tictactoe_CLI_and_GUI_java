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

    @Test
    public void greets() {
        ByteArrayInputStream input = new ByteArrayInputStream("irrelevant".getBytes());
        Ui ui = new Ui(new Cli(output, input));

        ui.greet();

        assertEquals("Welcome to Ticatactoe!\n"             +
                     "This version requires two players.\n" +
                     "First player is assigned mark X,\n"   +
                     "second player is assigned mark O\n", output.toString());
    }

    @Test
    public void displaysBoard() {
        ByteArrayInputStream input = new ByteArrayInputStream("irrelevant".getBytes());
        Ui ui = new Ui(new Cli(output, input));
        Mark[] moves = {X, null, null,
                          null, null, null,
                          null, null, null};
        Board board = new Board(moves);

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
        ByteArrayInputStream input = new ByteArrayInputStream("0".getBytes());
        Ui ui = new Ui(new Cli(output, input));

        ui.getMove(X);

        assertEquals("Player X, please select your move\n", output.toString());
    }

    @Test
    public void getsMove() {
        ByteArrayInputStream input = new ByteArrayInputStream("5".getBytes());
        Ui ui = new Ui(new Cli(output, input));

        int move = ui.getMove(X);

        assertEquals(4, move);
    }

    @Test
    public void doesNotAcceptNonNumericMove() {
        ByteArrayInputStream input = new ByteArrayInputStream("a\n5".getBytes());
        Ui ui = new Ui(new Cli(output, input));

        int move = ui.getMove(X);

        assertEquals(4, move);
    }

    @Test
    public void doesNotAcceptBlankInputAsMove() {
        ByteArrayInputStream input = new ByteArrayInputStream("\n5".getBytes());
        Ui ui = new Ui(new Cli(output, input));

        int move = ui.getMove(X);

        assertEquals(4, move);
    }

    @Test
    public void notifiesOnInvalidMove() {
        ByteArrayInputStream input = new ByteArrayInputStream("a\n5".getBytes());
        Ui ui = new Ui(new Cli(output, input));

        int move = ui.getMove(X);

        assertTrue(output.toString().contains("Invalid input, please try again"));
    }
}
