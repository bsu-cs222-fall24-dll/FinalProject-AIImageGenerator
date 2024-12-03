package bsu.edu.cs222;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

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
    private TextArea txtPromptBox;

    @FXML
    private ImageView imgAiImage;

    @FXML
    private CheckBox cbGameCharacter;

    @FXML
    private FlowPane fpGeneratorViewContainer, fpGameCharacterCharacteristics;

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
        setDisableInteraction(true);
        HashMap<String, String> characteristics = getCharacteristicsHashMap();
        HashMap<String, String> additionalChar = getAdditionalCharHashMap();

        // Fetches image in a thread, preventing application from freezing.
        // Utilizes "Platform.runLater(<lambda function>)" to run non-thread safe logic like JavaFX code, see README.
        new Thread(() -> {
            try {
                boolean isGameCharacter = cbGameCharacter.isSelected();
                byte[] image = imageManager.generateImage(isGameCharacter, characteristics, additionalChar);

                Platform.runLater(() -> displayImage(image));
            } catch (Exception e) {
                Platform.runLater(() -> viewUtilities.showErrorDialogBox("Error whilst getting and fetching image!", e.toString()));
            } finally {
                Platform.runLater(() -> {
                    invalidateSaveStatus();
                    setDisableInteraction(false);
                });
            }
        }).start();
    }

    @FXML
    private void clearFields() {
        invalidateSaveStatus();
        clearCharacteristicsFields(
                txtSex, txtRace, txtAge, txtHairColor, txtEyeColor, txtBodyStyle,
                txtHairLength, txtEyeShape, txtEyebrowShape, txtFaceShape, txtCheekbones,
                txtArtStyle, txtCharacterType, txtGameType, txtSpecies, txtSkinColor
        );
        imgAiImage.setImage(null);
    }

    @FXML
    private void clearArea(){
        invalidateSaveStatus();
        clearPromptBoxArea(txtPromptBox);
        imgAiImage.setImage(null);
    }

    @FXML
    public void saveToFile() throws IOException {
        setDisableInteraction(true);

        HashMap<String, String> characteristicsHashMap = getCharacteristicsHashMap();
        HashMap<String, String> additionalCharHashMap = getAdditionalCharHashMap();
        imageManager.saveImage(saveStatusManager.getFilename(), characteristicsHashMap, additionalCharHashMap, cbGameCharacter.isSelected());
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
        updateWindowTitle();
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
        fpGeneratorViewContainer.setDisable(isDisabled);
    }

    public void displayImage(byte[] imageData) {
        imgAiImage.setImage(new Image(new ByteArrayInputStream(imageData)));
    }

    public void setFilename(String filename) {
        saveStatusManager.setFilename(filename);
        saveStatusManager.markAsSaved();
        updateWindowTitle();
    }

    private HashMap<String, String> getCharacteristicsHashMap() {
        return getCharacteristicsFromFields(
                txtSex, txtRace, txtAge, txtHairColor, txtEyeColor, txtBodyStyle,
                txtHairLength, txtEyeShape, txtEyebrowShape, txtFaceShape, txtCheekbones,
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
        characteristics.put("hairLength", fields[6].getText());
        characteristics.put("eyeShape", fields[7].getText());
        characteristics.put("eyebrowShape", fields[8].getText());
        characteristics.put("faceShape", fields[9].getText());
        characteristics.put("cheekbones", fields[10].getText());
        characteristics.put("artStyle", fields[11].getText());
        characteristics.put("characterType", fields[12].getText());
        characteristics.put("gameType", fields[13].getText());
        characteristics.put("species", fields[14].getText());
        characteristics.put("skinColor", fields[15].getText());
        characteristics.put("promptBox", fields[16].getText());
        return characteristics;
    }

    private HashMap<String, String> getAdditionalCharHashMap() {
        return getPromptFromArea(
                txtPromptBox
        );
    }

    public HashMap<String, String> getPromptFromArea(TextArea... areas) {
        HashMap<String, String> additionalChar = new HashMap<>();
        additionalChar.put("promptBox", areas[0].getText());
        return additionalChar;
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

    void  setAdditionalChar(Characteristics additionalChar){
        txtPromptBox.setText(additionalChar.promptBox());
    }

    public void clearCharacteristicsFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }
    public void clearPromptBoxArea(TextArea... areas){
        for(TextArea area : areas){
            area.clear();
        }
    }
}