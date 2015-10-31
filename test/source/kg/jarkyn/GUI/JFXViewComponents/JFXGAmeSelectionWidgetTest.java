package kg.jarkyn.GUI.JFXViewComponents;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.Pane;
import kg.jarkyn.Core.GameOption;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JFXGameSelectionWidgetTest {

    @Test
    public void makesGameSelectorWidget() {
        setupJFXEnvironment();
        int amountOfGameOptions = GameOption.values().length;

        Pane pane = new JFXGameSelectionWidget();

        assertEquals(amountOfGameOptions, pane.getChildren().size());
    }

    private void setupJFXEnvironment() {
        new JFXPanel();
    }
}
