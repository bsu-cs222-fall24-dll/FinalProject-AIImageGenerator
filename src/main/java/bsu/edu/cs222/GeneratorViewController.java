package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

public class GeneratorViewController {
    final ImageManager imageManager;
    private final ViewUtilities viewUtilities;
    final SaveStatusManager saveStatusManager;
    private SceneController sceneController;

    @FXML
    private TextField txtSex, txtRace, txtAge, txtHairColor, txtEyeColor, txtBodyStyle,
            txtHairLength, txtEyeShape, txtEyebrowShape, txtFaceShape, txtCheekbones,
            txtArtStyle, txtCharacterType, txtGameType, txtSpecies, txtSkinColor;

    @FXML
    private ImageView imgAiImage;

    @FXML
    private CheckBox cbGameCharacter;

    @FXML
    private FlowPane fpGameCharacterCharacteristics;

    @FXML
    private GridPane gpGeneratorViewContainer;

    public GeneratorViewController() {
        this.imageManager = new ImageManager();
        this.saveStatusManager = new SaveStatusManager();
        this.viewUtilities = new ViewUtilities();
    }

    @FXML
    private void toggleGameCharacterCharacteristicsInteraction() {
        fpGameCharacterCharacteristics.setDisable(!cbGameCharacter.isSelected());
    }

    @FXML
    private void generateAndSetImage() {
        HashMap<String, String> characteristics = getCharacteristicsHashMap();

        try {
            boolean isGameCharacter = cbGameCharacter.isSelected();
            displayImage(imageManager.generateImage(isGameCharacter, characteristics));
        } catch (Exception e) {
            viewUtilities.showErrorDialogBox("Error whilst getting and fetching image!", e.getMessage());
        }
    }

    @FXML
    private void clearFields() {
        invalidateSaveStatus();
        clearCharacteristicsFields(
                txtSex, txtRace, txtAge, txtHairColor, txtEyeColor, txtBodyStyle,
                txtArtStyle, txtCharacterType, txtGameType, txtSpecies, txtSkinColor
        );
        imgAiImage.setImage(null);
    }

    @FXML
    public void saveToFile() throws IOException {
        setDisableInteraction(true);

        HashMap<String, String> characteristicsHashMap = getCharacteristicsHashMap();
        imageManager.saveImage(saveStatusManager.getFilename(), characteristicsHashMap, cbGameCharacter.isSelected());
        saveStatusManager.markAsSaved();
        updateWindowTitle();

        setDisableInteraction(false);
    }

    @FXML
    public void exitToManagerView() {
        if (!saveStatusManager.checkIfSaved()) {
            boolean userWantsToExit = saveStatusManager.confirmUnsavedChanges();
            if (!userWantsToExit) return;
        }
        sceneController.switchSceneToManagerView();
    }

    @FXML
    public void saveAndExit() throws IOException {
        saveToFile();
        if (saveStatusManager.checkIfSaved()) exitToManagerView();
    }

    @FXML
    public void changeFileNamePrompt() {
        String newFilename = saveStatusManager.promptFilenameChange();
        if (newFilename != null) {
            saveStatusManager.updateFilename(newFilename);
            updateWindowTitle();
        }
    }

    @FXML
    public void invalidateSaveStatus() {
        saveStatusManager.invalidateSaveStatus();
    }

    ImageManager getImageManager() {
        return imageManager;
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
        updateWindowTitle();
    }

    private void updateWindowTitle() {
        sceneController.setWindowTitle(saveStatusManager.getWindowTitle());
    }

    private void setDisableInteraction(boolean isDisabled) {
        gpGeneratorViewContainer.setDisable(isDisabled);
    }

    public void displayImage(byte[] imageData) {
        imgAiImage.setImage(new Image(new ByteArrayInputStream(imageData)));
    }

    public void setFilename(String filename) {
        saveStatusManager.setFilename(filename);
        saveStatusManager.markAsSaved();
        updateWindowTitle();
    }

    @FXML
    private void saveImage() {
        HashMap<String, String> characteristics = getCharacteristicsHashMap();
        String filename = saveStatusManager.getFilename();

        try {
            imageManager.saveImage(filename, characteristics, cbGameCharacter.isSelected());
        } catch (Exception e) {
            viewUtilities.showErrorDialogBox("Error saving image!", e.getMessage());
        }
    }

    private HashMap<String, String> getCharacteristicsHashMap() {
        return getCharacteristicsFromFields(
                txtSex, txtRace, txtAge, txtHairColor, txtEyeColor, txtBodyStyle,
                txtArtStyle, txtCharacterType, txtGameType, txtSpecies, txtSkinColor
        );
    }

    public HashMap<String, String> getCharacteristicsFromFields(TextField... fields) {
        HashMap<String, String> characteristics = new HashMap<>();
        characteristics.put("sex", fields[0].getText());
        characteristics.put("race", fields[1].getText());
        characteristics.put("age", fields[2].getText());
        characteristics.put("hairColor", fields[3].getText());
        characteristics.put("eyeColor", fields[4].getText());
        characteristics.put("bodyStyle", fields[5].getText());
        characteristics.put("artStyle", fields[6].getText());
        characteristics.put("characterType", fields[7].getText());
        characteristics.put("gameType", fields[8].getText());
        characteristics.put("species", fields[9].getText());
        characteristics.put("skinColor", fields[10].getText());
        return characteristics;
    }

    void setCharacteristics(Characteristics characteristics) {
        txtSex.setText(characteristics.sex());
        txtRace.setText(characteristics.race());
        txtAge.setText(characteristics.age());
        txtHairColor.setText(characteristics.hairColor());
        txtEyeColor.setText(characteristics.eyeColor());
        txtBodyStyle.setText(characteristics.bodyStyle());
        txtHairLength.setText(characteristics.hairLength());
        txtEyeShape.setText(characteristics.eyeShape());
        txtEyebrowShape.setText(characteristics.eyebrowShape());
        txtFaceShape.setText(characteristics.faceShape());
        txtCheekbones.setText(characteristics.cheekbones());

        cbGameCharacter.setSelected(characteristics.isGameCharacter());
        txtArtStyle.setText(characteristics.artStyle());
        txtCharacterType.setText(characteristics.characterType());
        txtGameType.setText(characteristics.gameType());
        txtSpecies.setText(characteristics.species());
        txtSkinColor.setText(characteristics.skinColor());

        fpGameCharacterCharacteristics.setDisable(!characteristics.isGameCharacter());
    }

    public void clearCharacteristicsFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }
}