package bsu.edu.cs222;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private final Stage stage;

    final private String GENERATOR_VIEW = "generator-view.fxml";

    public SceneController(Stage stage) {
        this.stage = stage;
    }

    void setWindowTitle(String title) {
        stage.setTitle(title);
    }

    FXMLLoader getLoader(String filename) {
        return new FXMLLoader(getClass().getResource("/" + filename));
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

    public void switchToGeneratorView(byte[] imageData, Characteristics characteristics, String filename) {
        try {
            FXMLLoader loader = getLoader(GENERATOR_VIEW);
            Parent root = loader.load();

            GeneratorViewController controller = loader.getController();
            controller.setSceneController(this);
            controller.setCharacteristics(characteristics);
            controller.setFilename(filename);
            controller.getImageManager().setImageData(imageData);
            controller.displayImage(imageData);

            setAndShowSceneFromParent(root);
        } catch (IOException e) {
            throw new RuntimeException("There was an error whilst trying to switch" +
                    " the scene to the generator view with initial data!", e);
        }
    }
}