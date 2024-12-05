package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class ManagerViewController {
    private SceneController sceneController;
    private final ImageLoader imageLoader;
    private final ImageItemFactory imageItemFactory;
    private final ViewUtilities viewUtilities;

    @FXML
    private FlowPane fpImageItems;

    @FXML
    private Label lblPlaceholder;

    public ManagerViewController() {
        this.imageItemFactory = new ImageItemFactory();
        this.imageLoader = new ImageLoader();
        this.viewUtilities = new ViewUtilities();
    }

    public void switchToGeneratorViewScene() {
        sceneController.switchToGeneratorView();
    }

    @FXML
    void openNewFile() {
        switchToGeneratorViewScene();
    }

    public void setSceneController(SceneController controller) {
        this.sceneController = controller;
        this.sceneController.setWindowTitle("AI Image Generator Manager");
        loadFilesIntoView();
    }

    void loadFilesIntoView() {
        try {
            String saveDirectoryPath = Config.getSaveDirectory();
            imageLoader.loadImageItems(saveDirectoryPath).forEach((filePath, imageWithCharacteristics) ->
                    fpImageItems.getChildren().add(imageItemFactory.createImageItem(filePath, imageWithCharacteristics, sceneController)));

            removePlaceholderTextIfNecessary();
        } catch (Exception e) {
            viewUtilities.showErrorDialogBox("Error whilst loading files into view!", e.getMessage());
        }
    }

    private void removePlaceholderTextIfNecessary() {
        if (fpImageItems.getChildren().size() > 1) {
            fpImageItems.getChildren().remove(lblPlaceholder);
        }
    }
}