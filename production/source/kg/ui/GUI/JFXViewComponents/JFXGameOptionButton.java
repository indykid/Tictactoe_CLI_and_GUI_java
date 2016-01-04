package kg.ui.GUI.JFXViewComponents;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import kg.jarkyn.GameOption;

public class JFXGameOptionButton extends Button {
    private GameOption gameOption;

    public JFXGameOptionButton(GameOption gameOption) {
        this.gameOption = gameOption;
        setId(gameOption.toString());
        style();
    }

    private void style() {
        setText(gameOption.readableOption());
        wrapTextProperty().setValue(true);
        setTextFill(Color.GRAY);
        setTextAlignment(TextAlignment.CENTER);
        setFont(Font.font(20));
        setMinSize(200, 200);
        setStyle("-fx-border-color: gray");
    }

    public GameOption getGameOption() {
        return gameOption;
    }
}
