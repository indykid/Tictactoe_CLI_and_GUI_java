package kg.jarkyn.GUI;

import kg.jarkyn.Core.GameOption;
import kg.jarkyn.GUI.ViewComponents.GameSelectionButton;

import java.util.ArrayList;
import java.util.List;

public class ViewMaker {
    public static List<GameSelectionButton> makeGameSelectionButtons() {
        List<GameSelectionButton> buttons = new ArrayList<>();
        for (GameOption gameOption : GameOption.values()) {
            buttons.add(new GameSelectionButton(gameOption));
        }
        return buttons;
    }
}
