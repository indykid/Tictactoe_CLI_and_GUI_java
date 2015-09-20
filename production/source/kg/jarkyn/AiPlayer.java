package kg.jarkyn;

public class AiPlayer {

    private final Mark mark;

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
}
