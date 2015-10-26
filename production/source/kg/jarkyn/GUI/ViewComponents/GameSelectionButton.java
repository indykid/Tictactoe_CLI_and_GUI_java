package kg.jarkyn.GUI.ViewComponents;

import javafx.scene.control.Button;
import kg.jarkyn.Core.GameOption;

public class GameSelectionButton extends Button {
    private GameOption gameOption;

    public GameSelectionButton(GameOption gameOption) {
        this.gameOption = gameOption;
    }

    public GameOption getGameOption() {
        return gameOption;
    }
}
