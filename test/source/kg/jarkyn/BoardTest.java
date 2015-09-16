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
        ArrayList<Integer> expectedAvailable = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));

        assertEquals(expectedAvailable, board.available);
    }

    @Test
    public void knowsAvailablePositionsWhenMovesWereMade() {
        ArrayList<Integer> expectedAvailable = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
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
    public void knowsVacantMoveIsValid() {
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

    @Test
    public void knowsWhenNotFull() {
        Board board = new Board();

        assertFalse(board.isFull());
    }

    @Test
    public void knowsWhenFull() {
        String[] moves = {"x", "o", "x",
                          "x", "x", "o",
                          "o", "o", "x"};
        Board board = new Board(moves);

        assertTrue(board.isFull());
    }

    @Test
    public void returnsWinningRow() {
        String[] moves = {"x", "x", "x",
                          null, "o", "o",
                          null, null, null};
        Board board = new Board(moves);
        int[] expectedWinLine = {0, 1, 2};

        assertArrayEquals(expectedWinLine, board.winLine());
    }

    @Test
    public void returnsWinningColumn() {
        String[] moves = {"x", null, null,
                          "x", "o", "o",
                          "x", null, null};
        Board board = new Board(moves);
        int[] expectedWinLine = {0, 3, 6};

        assertArrayEquals(expectedWinLine, board.winLine());
    }

    @Test
    public void returnsWinningDiagonal() {
        String[] moves = {"x", "o", "o",
                          null, "x", null,
                          null, null, "x"};
        Board board = new Board(moves);
        int[] expectedWinLine = {0, 4, 8};

        assertArrayEquals(expectedWinLine, board.winLine());
    }

    @Test
    public void returnsNoWinningLine() {
        String[] moves = {"x", "o", "o",
                          "o", "x", "x",
                          "o", "x", "o"};
        Board board = new Board(moves);
        int[] expectedWinLine = new int[0];

        assertArrayEquals(expectedWinLine, board.winLine());
    }

    @Test
    public void returnsWinnerMark() {
        String[] moves = {"x", "x", "x",
                          null, "o", "o",
                          null, null, null};
        Board board = new Board(moves);

        assertEquals("x", board.winnerMark());
    }

    @Test
    public void knowsIfItIsWon() {
        String[] moves = {"x", "x", "x",
                null, "o", "o",
                null, null, null};
        Board board = new Board(moves);

        assertTrue(board.isWon());
    }

    @Test
    public void knowsIfItIsNotWon() {
        Board board = new Board();

        assertFalse(board.isWon());
    }
}
