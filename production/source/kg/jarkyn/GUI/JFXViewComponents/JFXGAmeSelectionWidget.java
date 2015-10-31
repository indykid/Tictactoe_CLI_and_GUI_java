package kg.jarkyn.GUI.JFXViewComponents;

import javafx.geometry.Pos;
import kg.jarkyn.Core.GameOption;

public class JFXGameSelectionWidget extends JFXGrid {
    public JFXGameSelectionWidget() {

        setPrefSize(600, 600);

        setAlignment(Pos.CENTER_LEFT);
        int column = 0;
        int row = 0;
        for (GameOption gameOption : GameOption.values()) {
            JFXGameOptionButton button = new JFXGameOptionButton(gameOption);
            add(button, column, row);
            column++;
        }
    }
}
