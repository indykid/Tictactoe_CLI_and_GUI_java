package kg.jarkyn;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private static final int DEFAULT_SIZE = 3;
    private static final int[][] WINNING_POSITIONS = new int[][]{
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8},
        {0, 3, 6},
        {1, 4, 7},
        {2, 5, 8},
        {0, 4, 8},
        {2, 4, 6}
    };

    public int size;
    public String[] moves;
    public ArrayList<Integer> available;

    public Board() {
        this(new String[9]);
    }

    public Board(String[] moves) {
        this.size = DEFAULT_SIZE;
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
        ArrayList<Integer> available = new ArrayList<>();
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

    public boolean isFull() {
        return available.size() == 0;
    }

    public int[] winLine() {
        for (int[] line : WINNING_POSITIONS) {
            if (isOccupied(line[0]) && isSameMark(line)) {
                return line;
            }
        }
        return new int[0];
    }

    private boolean isOccupied(int position) {
        return moves[position] != null;
    }

    private boolean isSameMark(int[] line) {
        for (int index = 0; index < line.length - 1; index++) {
            if (!moves[line[index]].equals(moves[line[index + 1]])) {
                return false;
            }
        }
        return true;
    }

    public String winnerMark() {
        return moves[winLine()[0]];
    }
}
