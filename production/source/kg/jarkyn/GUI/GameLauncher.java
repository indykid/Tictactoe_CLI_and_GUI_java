package kg.jarkyn.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import kg.jarkyn.GUI.ViewComponents.MainGrid;

public class GameLauncher extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new MainGrid(), Color.BLACK));
        new GraphicalUI(primaryStage.getScene()).displayGameSelector();
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.sizeToScene();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
