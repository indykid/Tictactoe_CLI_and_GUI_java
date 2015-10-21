package kg.jarkyn.Core;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    public int size;
    public ArrayList<Integer> available;
    private Mark[] moves;
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

    public Board() {
        this(setMoves());
    }

    public Board(Mark[] moves) {
        this.size = DEFAULT_SIZE;
        this.moves = moves;
        this.available = setAvailable();
    }

    public Mark markAt(int position) {
        return moves[position];
    }

    public Board addMove(int position, Mark mark) {
        Mark[] newMoves = moves.clone();
        newMoves[position] = mark;
        return new Board(newMoves);
    }

    public boolean isValidMove(int position) {
        return available.contains(position);
    }

    public boolean isFull() {
        return available.size() == 0;
    }

    public boolean isFinalState() {
        return isWon() || isDrawn();
    }

    public Mark[] getMoves() {
        return moves;
    }

    public ArrayList<Integer> getAvailable() {
        return available;
    }

    public int[] winLine() {
        for (int[] line : WINNING_POSITIONS) {
            if (isOccupied(line[0]) && isSameMark(line)) {
                return line;
            }
        }
        int[]line = new int[size];
        Arrays.fill(line, -1);
        return line;
    }

    public Mark winnerMark() {
        if (winLine()[0] == -1) {
            return Mark.NONE;
        } else {
            return moves[winLine()[0]];
        }
    }

    public int getSize() {
        return size;
    }

    private boolean isWon() {
        return winnerMark() != Mark.NONE;
    }

    private boolean isDrawn() {
        return isFull() && !isWon();
    }

    private boolean isEmptyPosition(int position) {
        return markAt(position) == Mark.NONE;
    }

    private boolean isOccupied(int position) {
        return markAt(position) != Mark.NONE;
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

    private static Mark[] setMoves() {
        int length = (int)Math.pow(DEFAULT_SIZE, 2);
        Mark[] moves  = new Mark[length];
        Arrays.fill(moves, Mark.NONE);
        return moves;
    }

    private boolean isSameMark(int[] line) {
        for (int index = 0; index < line.length - 1; index++) {
            if (!moves[line[index]].equals(moves[line[index + 1]])) {
                return false;
            }
        }
        return true;
    }
}