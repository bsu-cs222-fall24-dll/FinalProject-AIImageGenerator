package bsu.edu.cs222;

import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    SceneController sceneController = new SceneController();

    @Override
    public void start(Stage stage) {
        sceneController.setStage(stage);
        sceneController.switchSceneToManagerView();
    }

    public static void main(String[] args) {
        launch();
    }
}