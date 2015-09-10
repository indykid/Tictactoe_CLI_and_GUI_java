package kg.jarkyn;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void hasDefaultSize() {
        assertEquals(3, new Board().size);
    }

    @Test
    public void emptyAtTheStartByDefault() {
        assertTrue(new Board().isEmpty());
    }

    @Test
    public void canHaveMovesFromTheStart() {
        String[] moves = new String[9];
        moves[0] = "x";

        assertFalse(new Board(moves).isEmpty());

    }

    @Test
    public void knowsMarkForGivenPosition() {
        String[] moves = new String[9];
        moves[0] = "x";

        assertEquals("x", new Board(moves).markAt(0));
    }

    @Test
    public void twoBoardsAreEqualIfHaveSameMoves() {
        String[] moves = new String[9];
        moves[0] = "x";

        assertEquals(new Board(moves), new Board().addMove(0, "x"));
    }

    @Test
    public void addingMoveReturnsNewBoardWithTheMoveAdded() {
        Board newBoard = new Board().addMove(0, "x");
        String[] moves = new String[9];
        moves[0] = "x";

        assertEquals(new Board(moves), newBoard);
    }
}
