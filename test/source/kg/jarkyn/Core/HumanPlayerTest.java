package kg.jarkyn.Core;

import kg.jarkyn.doubles.InputDouble;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HumanPlayerTest {
    @Test
    public void picksPosition() {
        HumanInput input = new InputDouble(new int[]{1});
        HumanPlayer playerX = new HumanPlayer(Mark.X, input);
        int position = playerX.pickPosition(new Board());

        assertEquals(0, position);
    }

    @Test
    public void hasNoMoveIfUiHasNotReceivedMove() {
        HumanInput input = new InputDummy();
        HumanPlayer playerX = new HumanPlayer(Mark.X, input);

        assertFalse(playerX.hasNextMove());
    }

    @Test
    public void hasMoveIfUiHasReceivedMove() {
        HumanInput input = new InputStubWithMove();
        HumanPlayer playerX = new HumanPlayer(Mark.X, input);

        assertTrue(playerX.hasNextMove());
    }

    private class InputDummy implements HumanInput {
        @Override
        public int getMove(List<Integer> available) {
            return 0;
        }

        @Override
        public boolean hasHumanMove() {
            return false;
        }
    }

    private class InputStubWithMove extends InputDummy {
        @Override
        public boolean hasHumanMove() {
            return true;
        }
    }
}