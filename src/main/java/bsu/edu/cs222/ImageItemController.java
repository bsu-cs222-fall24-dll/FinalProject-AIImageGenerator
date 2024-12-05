package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ImageItemController {
    ViewUtilities viewUtilities = new ViewUtilities();
    SceneController sceneController;
    ImageWithCharacteristics imageWithCharacteristics;
    String filename;
    String filepath;

    @FXML
    ImageView imgGeneratedImage;

    @FXML
    Label lblFilename;

    @FXML
    public void handleOnClick(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2) {
                try {
                    sceneController.switchToGeneratorView(imageWithCharacteristics.imageData(), imageWithCharacteristics.characteristics(), filename);
                } catch (Exception e) {
                    viewUtilities.showErrorDialogBox("Error", e.getMessage());
                }
            }
        }
    }

    public void setSceneController(SceneController controller) {
        sceneController = controller;
    }

    public void setFilepath(String filePath) {
        this.filepath = filePath;
    }

    public void setFilename(String filename) {
        this.filename = filename;
        lblFilename.setText(filename);
    }

    public void setImageWithCharacteristics(ImageWithCharacteristics imageWithCharacteristics) throws IOException {
        this.imageWithCharacteristics = imageWithCharacteristics;
        imgGeneratedImage.setImage(new Image(imageWithCharacteristics.getImageStream()));
    }
}
