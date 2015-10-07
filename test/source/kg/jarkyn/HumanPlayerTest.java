package kg.jarkyn;

import kg.jarkyn.doubles.UiDouble;
import org.junit.Test;
import static org.junit.Assert.*;

public class HumanPlayerTest {
    @Test
    public void picksPosition() {
        UiDouble ui = new UiDouble(new int[]{1});
        HumanPlayer playerX = new HumanPlayer(Mark.X, ui);
        int position = playerX.pickPosition(new Board());

        assertEquals(0, position);
    }
}
