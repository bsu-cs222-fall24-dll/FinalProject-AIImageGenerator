package bsu.edu.cs222;

import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    SceneController sceneController;

    @Override
    public void start(Stage stage) {
        sceneController = new SceneController(stage);
        sceneController.switchSceneToManagerView();
    }

    public static void main(String[] args) {
        launch();
    }
}