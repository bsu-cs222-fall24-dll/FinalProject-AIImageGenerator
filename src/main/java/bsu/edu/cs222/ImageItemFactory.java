package bsu.edu.cs222;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.nio.file.Paths;

public class ImageItemFactory {
    public GridPane createImageItem(String filePath, ImageWithCharacteristics imageWithCharacteristics, SceneController sceneController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/image-item.fxml"));
        GridPane imageItem;

        try {
            imageItem = loader.load();
            ImageItemController controller = loader.getController();
            controller.setSceneController(sceneController);
            controller.setFilepath(filePath);
            controller.setFilename(Paths.get(filePath).getFileName().toString().replace(".data", ""));
            controller.setImageWithCharacteristics(imageWithCharacteristics);
        } catch (IOException e) {
            throw new RuntimeException("Error getting image component", e);
        }

        return imageItem;
    }
}