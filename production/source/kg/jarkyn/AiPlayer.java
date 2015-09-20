package kg.jarkyn;

import java.util.ArrayList;
import java.util.Collections;

public class AiPlayer {

    public final Mark mark;

    public AiPlayer(Mark mark) {
        this.mark = mark;
    }

    public int scoreFinalBoard(Board board) {
        Mark winnerMark = board.winnerMark();
        int score = 0;
        if (winnerMark == mark) {
            score = 10;
        } else if (winnerMark == mark.opponent()) {
            score = -10;
        }
        return score;
    }

    public int score(Board board, Mark currentMark) {
        if (board.isFinalState()) {
            return scoreFinalBoard(board);
        }

        ArrayList<Integer> scores = new ArrayList<>();
        for (Board possibleBoard : possibleBoards(board, currentMark)) {
            scores.add(score(possibleBoard, nextMark(currentMark)));
        }

        int score;
        if (currentMark == mark) {
            score = Collections.max(scores);
        } else {
            score = Collections.min(scores);
        }

        return score;
    }

    private ArrayList<Board> possibleBoards(Board board, Mark mark) {
        ArrayList<Board> possibleBoards = new ArrayList<>();
        for (int position :  board.available) {
            possibleBoards.add(board.addMove(position, mark));
        }
        return possibleBoards;
    }

    private Mark nextMark(Mark currentMark) {
        return currentMark.opponent();
    }
}
