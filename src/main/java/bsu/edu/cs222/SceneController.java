package bsu.edu.cs222;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class SceneController {
    private Stage stage;

    final private String GENERATOR_VIEW = "generator-view.fxml";

    void setStage(Stage stage) {
        this.stage = stage;
    }

    void setWindowTitle(String title) {
        stage.setTitle(title);
    }

    FXMLLoader getLoader(String sceneName) {
        return new FXMLLoader(getClass().getResource("/" + sceneName));
    }

    void setAndShowSceneFromParent(Parent root) throws IOException {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchSceneToManagerView() {
        try {
            String MANAGER_VIEW = "manager-view.fxml";
            FXMLLoader loader = getLoader(MANAGER_VIEW);
            Parent root = loader.load();

            ManagerViewController controller = loader.getController();
            controller.setSceneController(this);

            setAndShowSceneFromParent(root);
        } catch (IOException e) {
            throw new RuntimeException("There was an error whilst trying to switch" +
                    " the scene to the manager view!", e);
        }
    }

    public void switchToGeneratorView() {
        try {
            FXMLLoader loader = getLoader(GENERATOR_VIEW);
            Parent root = loader.load();

            GeneratorViewController controller = loader.getController();
            controller.setSceneController(this);

            setAndShowSceneFromParent(root);
        } catch (IOException e) {
            throw new RuntimeException("There was an error whilst trying to switch" +
                    " the scene to the generator view!", e);
        }
    }

    public void switchToGeneratorView(InputStream imageStream, Characteristics characteristics) {
        try {
            FXMLLoader loader = getLoader(GENERATOR_VIEW);
            Parent root = loader.load();

            GeneratorViewController controller = loader.getController();
            controller.setSceneController(this);
            controller.setCharacteristics(characteristics);
            controller.setImage(imageStream);

            setAndShowSceneFromParent(root);
        } catch (IOException e) {
            throw new RuntimeException("There was an error whilst trying to switch" +
                    " the scene to the generator view with initial data!", e);
        }
    }
}
