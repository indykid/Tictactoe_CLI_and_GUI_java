package kg.jarkyn.GUI.SwingViewComponents;

import kg.jarkyn.Core.GameOption;

import javax.swing.*;
import java.awt.*;

public class SwingGameOptionButton extends JButton {
    private final GameOption gameOption;

    public SwingGameOptionButton(GameOption gameOption) {
        this.gameOption = gameOption;
        style();
    }

    private void style() {
        setText(gameOption.readableOption());

//        setFont(new Font("Arial", Font.PLAIN, 20));// to set font size
        setMinimumSize(new Dimension(200, 200));
        // set background colour and font colour
    }

    public GameOption getGameOption() {
        return gameOption;
    }
}
