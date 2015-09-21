package kg.jarkyn;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class AiPlayer {

    public final Mark mark;

    public AiPlayer(Mark mark) {
        this.mark = mark;
    }

    public int pickPosition(Board board) {
        HashMap<Integer, Integer> scoredPositions = new HashMap<>();
        for (int position : availablePositions(board)) {
            Board possibleBoard = board.addMove(position, mark);
            int score = score(possibleBoard, mark.opponent());

            scoredPositions.put(position, score);
        }

        int bestScore = Integer.MIN_VALUE;
        int bestPosition = -1;
        for (Map.Entry<Integer, Integer> entry : scoredPositions.entrySet()) {
            if (entry.getValue().compareTo(bestScore) > 0) {
                bestScore = entry.getValue();
                bestPosition = entry.getKey();
            }
        }
        return bestPosition;
    }

    public int score(Board board, Mark currentMark) {
        if (board.isFinalState()) {
            return scoreFinalBoard(board);
        }

        List<Integer> scores =
                availablePositions(board)
                .stream()
                .map(position -> score(board.addMove(position, currentMark), currentMark.opponent()))
                .collect(toList());

        return currentMark == mark ? Collections.max(scores) : Collections.min(scores);
    }

    private int scoreFinalBoard(Board board) {
        Mark winnerMark = board.winnerMark();
        int score = 0;
        if (winnerMark == mark) {
            score = 10;
        } else if (winnerMark == mark.opponent()) {
            score = -10;
        }
        return score;
    }

    private ArrayList<Integer> availablePositions(Board board) {
        return board.available;
    }
}
