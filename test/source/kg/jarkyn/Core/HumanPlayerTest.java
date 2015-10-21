package kg.jarkyn.Core;

import kg.jarkyn.doubles.UiDouble;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HumanPlayerTest {
    @Test
    public void picksPosition() {
        Ui ui = new UiDouble(new int[]{1});
        HumanPlayer playerX = new HumanPlayer(Mark.X, ui);
        int position = playerX.pickPosition(new Board());

        assertEquals(0, position);
    }

    @Test
    public void hasNoMoveIfUiHasNotReceivedMove() {
        Ui ui = new UiDummyWithoutMove();
        HumanPlayer playerX = new HumanPlayer(Mark.X, ui);

        assertFalse(playerX.hasNextMove());
    }

    @Test
    public void hasMoveIfUiHasReceivedMove() {
        Ui ui = new UiDummyWithMove();
        HumanPlayer playerX = new HumanPlayer(Mark.X, ui);

        assertTrue(playerX.hasNextMove());
    }

    private class UiDummy implements Ui {
        @Override
        public int selectGame() {
            return 0;
        }

        @Override
        public void announceDraw() {}

        @Override
        public void playGame() {}

        @Override
        public void setGame(Game game) {

        }

        @Override
        public int getMove(List<Integer> available) {
            return 0;
        }

        @Override
        public void announceGameOver() {}

        @Override
        public boolean hasHumanMove() {
            return false;
        }
    }

    private class UiDummyWithoutMove extends UiDummy {

    }

    private class UiDummyWithMove extends UiDummy {
        @Override
        public boolean hasHumanMove() {
            return true;
        }
    }
}