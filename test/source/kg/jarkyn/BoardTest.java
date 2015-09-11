package kg.jarkyn;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void hasDefaultSize() {
        assertEquals(3, new Board().size);
    }

    @Test
    public void knowsAvailablePositionsWhenNoMovesMade() {
        Board board = new Board();
        ArrayList<Integer> expectedAvailable = new ArrayList(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));

        assertEquals(expectedAvailable, board.available);
    }

    @Test
    public void knowsAvailablePositionsWhenMovesWereMade() {
        ArrayList<Integer> expectedAvailable = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        String[] moves = new String[9];
        moves[0] = "x";

        assertEquals(expectedAvailable, new Board(moves).available);
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

    @Test
    public void knowsIfMoveIsValid() {
        Board board = new Board();

        assertTrue(board.isValidMove(0));
    }

    @Test
    public void knowsOccupiedPositionIsNotValidMove() {
        String[] moves = {"x",  null, null,
                          null, null, null,
                          null, null, null};
        Board board = new Board(moves);

        assertFalse(board.isValidMove(0));
    }

    @Test
    public void knowsMoveOutsideTheBoardIsNotValid() {
        Board board = new Board();

        assertFalse(board.isValidMove(10));
    }
}
