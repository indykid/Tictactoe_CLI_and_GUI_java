package kg.ui.GUI.SwingViewComponents;

import kg.jarkyn.GameOption;
import kg.ui.GUI.GameOptionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGameSelectionWidget extends JPanel {
    public SwingGameSelectionWidget(GameOptionListener listener) {
        super(new GridLayout(0, GameOption.values().length));
        setupButtons(listener);
        style();
    }

    private void style() {
        setMinimumSize(new Dimension(600, 200));
    }

    private void setupButtons(GameOptionListener listener) {
        for (GameOption gameOption : GameOption.values()) {
            SwingGameOptionButton button = new SwingGameOptionButton(gameOption);
            addListener(listener, button);
            add(button);
        }
    }

    private void addListener(final GameOptionListener listener, SwingGameOptionButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.gameOptionSelected(button.getGameOption());
            }
        });
    }
}
