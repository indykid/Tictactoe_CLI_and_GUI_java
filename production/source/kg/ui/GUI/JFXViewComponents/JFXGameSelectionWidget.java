package kg.ui.GUI.JFXViewComponents;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import kg.jarkyn.GameOption;
import kg.ui.GUI.GameOptionListener;

public class JFXGameSelectionWidget extends JFXGrid {
    public JFXGameSelectionWidget(GameOptionListener listener) {

        setPrefSize(600, 600);

        setAlignment(Pos.CENTER_LEFT);

        int column = 0;
        int row = 0;
        for (GameOption gameOption : GameOption.values()) {
            JFXGameOptionButton button = new JFXGameOptionButton(gameOption);
            addGameOptionListener(listener, button);
            add(button, column, row);
            column++;
        }
    }

    private void addGameOptionListener(final GameOptionListener listener, JFXGameOptionButton button) {
        button.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                listener.gameOptionSelected(button.getGameOption());
            }
        });
    }
}
