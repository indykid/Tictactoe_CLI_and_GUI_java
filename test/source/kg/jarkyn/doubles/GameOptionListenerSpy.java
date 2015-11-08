package kg.jarkyn.doubles;

import kg.jarkyn.Core.GameOption;
import kg.jarkyn.GUI.GameOptionListener;

public class GameOptionListenerSpy implements GameOptionListener {
    public GameOption gameOptionReceived;

    @Override
    public void gameOptionSelected(GameOption gameOption) {
        gameOptionReceived = gameOption;
    }
}
