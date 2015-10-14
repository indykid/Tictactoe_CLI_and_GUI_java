package kg.jarkyn.Core;

import kg.jarkyn.Core.Board;
import kg.jarkyn.Core.HumanPlayer;
import kg.jarkyn.Core.Mark;
import kg.jarkyn.Core.Ui;
import kg.jarkyn.doubles.UiDouble;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {
    @Test
    public void picksPosition() {
        Ui ui = new UiDouble(new int[]{1});
        HumanPlayer playerX = new HumanPlayer(Mark.X, ui);
        int position = playerX.pickPosition(new Board());

        assertEquals(0, position);
    }
}
