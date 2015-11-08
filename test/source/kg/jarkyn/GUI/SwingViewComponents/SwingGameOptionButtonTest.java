package kg.jarkyn.GUI.SwingViewComponents;

import kg.jarkyn.Core.GameOption;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SwingGameOptionButtonTest {
    @Test
    public void hasCorrectDescription() {
        SwingGameOptionButton aiFirstButton = new SwingGameOptionButton(GameOption.AI_FIRST);

        assertEquals(GameOption.AI_FIRST.readableOption(), aiFirstButton.getText());
    }
}
