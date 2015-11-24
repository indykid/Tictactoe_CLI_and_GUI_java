package kg.ui.doubles;

import kg.jarkyn.GameOption;
import kg.ui.GUI.GameOptionListener;

public class GameOptionListenerSpy implements GameOptionListener {
    public GameOption gameOptionReceived;

    @Override
    public void gameOptionSelected(GameOption gameOption) {
        gameOptionReceived = gameOption;
    }
}
