package kg.jarkyn;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import static org.junit.Assert.assertEquals;

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
        String[] moves = {"x", null, null,
                          null, null, null,
                          null, null, null};
        Board board = new Board(moves);

        ui.displayBoard(board);

        assertEquals("  x |  2 |  3 \n" +
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

        ui.getMove("x");

        assertEquals("Player x, please select your move\n", output.toString());
    }

    @Test
    public void getsMove() {
        ByteArrayInputStream input = new ByteArrayInputStream("5".getBytes());
        Ui ui = new Ui(new Cli(output, input));

        int move = ui.getMove("x");

        assertEquals(4, move);
    }
}
