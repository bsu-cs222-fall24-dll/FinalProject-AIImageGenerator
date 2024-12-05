package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ImageItemController {
    ViewUtilities viewUtilities = new ViewUtilities();
    SceneController sceneController;
    ImageWithCharacteristics imageWithCharacteristics;
    ImageItemContextMenu imageItemContextMenu;

    @FXML
    ImageView imgGeneratedImage;

    @FXML
    Label lblFilename;

    ContextMenu contextMenu;

    @FXML
    public void initialize() {
        contextMenu = new ContextMenu();

        MenuItem copyItem = new MenuItem("Copy");
        copyItem.setOnAction(event -> handleCopyImage());

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(event -> handleDeleteImage());

        MenuItem renameItem = new MenuItem("Rename");
        renameItem.setOnAction(event -> handleRenameImage());

        contextMenu.getItems().addAll(copyItem, deleteItem, renameItem);

        imgGeneratedImage.setOnContextMenuRequested(event ->
                contextMenu.show(imgGeneratedImage, event.getScreenX(), event.getScreenY()));
    }


    @FXML
    public void handleOnClick(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2) {
                try {
                    sceneController.switchToGeneratorView(imageWithCharacteristics.imageData(), imageWithCharacteristics.characteristics(), imageItemContextMenu.getFilename());
                } catch (Exception e) {
                    viewUtilities.showErrorDialogBox("Error", e.getMessage());
                }
            }
        }
    }

    public void setSceneController(SceneController controller) {
        sceneController = controller;
    }

    public void setFilepath(String filepath) {
        this.imageItemContextMenu = new ImageItemContextMenu(filepath);
        lblFilename.setText(imageItemContextMenu.getFilename());
    }

    public void setFilename(String filename) {
        if (imageItemContextMenu != null) {
            imageItemContextMenu.renameImageFile(filename);
            lblFilename.setText(imageItemContextMenu.getFilename());
        }
    }

    public void setImageWithCharacteristics(ImageWithCharacteristics imageWithCharacteristics) throws IOException {
        this.imageWithCharacteristics = imageWithCharacteristics;
        imgGeneratedImage.setImage(new Image(imageWithCharacteristics.getImageStream()));
    }

    private void handleRenameImage() {
        if (imageItemContextMenu == null) return;

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Rename Image");
        dialog.setHeaderText("Rename Image");
        dialog.setContentText("Enter a new name:");
        dialog.showAndWait()
                .filter(newName -> !newName.trim().isEmpty())
                .ifPresent(newName -> {
                    if (imageItemContextMenu.renameImageFile(newName)) {
                        lblFilename.setText(imageItemContextMenu.getFilename());
                    }
                });
    }

    private void handleDeleteImage() {
        if (imageItemContextMenu == null) return;

        if (imageItemContextMenu.deleteImageFile()) {
            removeImageFromView();
        }
    }

    private void handleCopyImage() {
        if (imageItemContextMenu == null) return;


        imageItemContextMenu.copyImageFile(imgGeneratedImage.getImage());
    }

    private void removeImageFromView() {
        GridPane gridPane = (GridPane) imgGeneratedImage.getParent().getParent();
        if (gridPane.getParent() instanceof FlowPane flowPane) {
            flowPane.getChildren().remove(gridPane);
        }
    }
}
