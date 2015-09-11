package kg.jarkyn;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private static final int DEFAULT_SIZE = 3;
    public int size;
    public String[] moves;
    public ArrayList<Integer> available;

    public Board() {
        this(new String[9], DEFAULT_SIZE);
    }

    public Board(String[] moves) {
        this(moves, DEFAULT_SIZE);
    }

    public Board(String[] moves, int size) {
        this.size = size;
        this.moves = moves;
        this.available = setAvailable();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return Arrays.equals(moves, board.moves);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(moves);
    }

    public Board addMove(int position, String mark) {
        String[] newMoves = moves.clone();
        newMoves[position] = mark;
        return new Board(newMoves);
    }

    public String markAt(int position) {
        return moves[position];
    }

    public boolean isValidMove(int position) {
        return available.contains(position);
    }

    private ArrayList<Integer> setAvailable() {
        ArrayList<Integer> available = new ArrayList<Integer>();
        for (int position = 0; position < moves.length; position++) {
            if (isEmptyPosition(position)) {
                available.add(position);
            }
        }
        return available;
    }

    private boolean isEmptyPosition(int position) {
        return markAt(position) == null;
    }
}
