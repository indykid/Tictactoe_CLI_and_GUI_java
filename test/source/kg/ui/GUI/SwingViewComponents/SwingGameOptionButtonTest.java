package kg.ui.GUI.SwingViewComponents;

import kg.jarkyn.GameOption;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SwingGameOptionButtonTest {
    @Test
    public void hasCorrectDescription() {
        SwingGameOptionButton aiFirstButton = new SwingGameOptionButton(GameOption.AI_FIRST);

        assertEquals(GameOption.AI_FIRST.readableOption(), aiFirstButton.getText());
    }
}
