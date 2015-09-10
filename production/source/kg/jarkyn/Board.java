package kg.jarkyn;

import java.util.Arrays;

public class Board {
    private static final int DEFAULT_SIZE = 3;
    public int size = DEFAULT_SIZE;
    public String[] moves = new String[9];

    public Board() {
    }

    public Board(String[] moves) {
        this.moves = moves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(moves, board.moves);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(moves);
    }

    public boolean isEmpty() {
        return isAllEqualAndNull(moves);
    }

    public Board addMove(int position, String mark) {
        String[] newMoves = moves.clone();
        newMoves[position] = mark;
        return new Board(newMoves);
    }

    public String markAt(int position) {
        return moves[position];
    }

    private boolean isAllEqualAndNull(String[] moves) {
        return moves[0] == null && isAllEqual(moves);
    }

    private boolean isAllEqual(String[] moves) {
        for (int move = 1; move < moves.length; move++) {
            if (moves[0] != moves[move]) return false;
        }
        return true;
    }
}
