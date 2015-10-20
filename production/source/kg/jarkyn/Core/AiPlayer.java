package kg.jarkyn.Core;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class AiPlayer extends Player {

    public AiPlayer(Mark mark) {
        super(mark);
    }

    @Override
    public int pickPosition(Board board) {
        HashMap<Integer, Integer> scoredPositions = new HashMap<>();
        for (int position : availablePositions(board)) {
            Board possibleBoard = board.addMove(position, getMark());
            int score = score(possibleBoard, getMark().opponent());

            scoredPositions.put(position, score);
        }

        int bestScore = Integer.MIN_VALUE;
        int bestPosition = Integer.MIN_VALUE;
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

        return currentMark == getMark() ? Collections.max(scores) : Collections.min(scores);
    }

    private int scoreFinalBoard(Board board) {
        Mark winnerMark = board.winnerMark();
        int score = 0;
        if (winnerMark == getMark()) {
            score = 10;
        } else if (winnerMark == getMark().opponent()) {
            score = -10;
        }
        return score;
    }

    private ArrayList<Integer> availablePositions(Board board) {
        return board.getAvailable();
    }
}
