package kg.jarkyn.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kg.jarkyn.GUI.ViewComponents.MainPane;

public class GameLauncher extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new MainPane()));
        new GraphicalUI(primaryStage.getScene()).displayGameSelector();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
