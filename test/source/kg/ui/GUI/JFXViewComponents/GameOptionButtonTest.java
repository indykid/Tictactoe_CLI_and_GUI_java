package kg.ui.GUI.JFXViewComponents;

import javafx.embed.swing.JFXPanel;
import kg.jarkyn.GameOption;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameOptionButtonTest {

    @Test
    public void hasCorrectDescription() {
        setupJFXEnvironment();
        JFXGameOptionButton aiFirstButton = new JFXGameOptionButton(GameOption.AI_FIRST);

        assertEquals(GameOption.AI_FIRST.readableOption(), aiFirstButton.getText());
    }

    private void setupJFXEnvironment() {
        new JFXPanel();
    }
}
