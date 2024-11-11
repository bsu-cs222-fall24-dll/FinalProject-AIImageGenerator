package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.io.InputStream;
import java.util.HashMap;

public class GeneratorViewController {
    SceneController sceneController;
    ViewUtilities viewUtilities = new ViewUtilities();
    ImageGeneratorController imageGenerator = new ImageGeneratorController();

    String filename = "untitled";
    Boolean fileIsSaved = false;

    @FXML
    private TextField txtSex, txtRace, txtAge, txtHairColor, txtEyeColor, txtBodyStyle,
            txtArtStyle, txtCharacterType, txtGameType, txtSpecies, txtSkinColor;

    @FXML
    private ImageView imgAiImage;

    @FXML
    private CheckBox cbGameCharacter;

    @FXML
    private FlowPane fpGameCharacterCharacteristics;

    @FXML
    private GridPane gpGeneratorViewContainer;

    @FXML
    private void toggleGameCharacterCharacteristicsInteraction() {
        fpGameCharacterCharacteristics.setDisable(!cbGameCharacter.isSelected());
    }

    @FXML
    private void generateAndSetImage() {
        invalidateSaveStatus();
        HashMap<String, String> characteristics = getCharacteristicsHashMap();

        setDisableInteraction(true);
        try {
            InputStream imageStream = imageGenerator.getImageStream(cbGameCharacter.isSelected(), characteristics);
            imgAiImage.setImage(new Image(imageStream));
        } catch (Exception e) {
            viewUtilities.showErrorDialogBox("Exception: " + e.getClass(), e.getMessage());
        } finally {
            setDisableInteraction(false);
        }
    }

    @FXML
    private void clearFields() {
        invalidateSaveStatus();

        txtSex.setText("");
        txtRace.setText("");
        txtAge.setText("");
        txtHairColor.setText("");
        txtEyeColor.setText("");
        txtBodyStyle.setText("");
        txtArtStyle.setText("");
        txtCharacterType.setText("");
        txtGameType.setText("");
        txtSpecies.setText("");
        txtSkinColor.setText("");

        imgAiImage.setImage(null);
    }

    public void invalidateSaveStatus() {
        if (fileIsSaved) {
            fileIsSaved = false;
            updateWindowTitle();
        }
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
        updateWindowTitle();
    }

    public void updateWindowTitle() {
        String windowTitle = filename;
        if (!fileIsSaved) windowTitle += "*";

        sceneController.setWindowTitle(windowTitle);
    }

    private void setDisableInteraction(boolean isDisabled) {
        gpGeneratorViewContainer.setDisable(isDisabled);
    }

    public void saveToFile() {
        setDisableInteraction(true);
        try {
            HashMap<String, String> characteristicsHashMap = getCharacteristicsHashMap();
            boolean isGameCharacter = cbGameCharacter.isSelected();

            imageGenerator.saveImage(filename, characteristicsHashMap, isGameCharacter);

            fileIsSaved = true;
            updateWindowTitle();
        } catch (Exception e) {
            viewUtilities.showErrorDialogBox("Error whilst trying to save!", e.getMessage());
        } finally {
            setDisableInteraction(false);
        }
    }

    public void exitToManagerView() {
        if (!fileIsSaved) {
            boolean userWantsToExit = viewUtilities.getConfirmationAlertResult("Unsaved work", "Are you sure you want to exit?");
            if (!userWantsToExit) return;
        }

        sceneController.switchSceneToManagerView();
    }

    public void saveAndExit() {
            saveToFile();
            if (fileIsSaved) exitToManagerView();
    }

    public void changeFileNamePrompt() {
        String input = viewUtilities.getDialogInput("Change filename", "What would you like to set the filename to?");
        if (!(input == null || input.isEmpty())) {
            filename = input;
            updateWindowTitle();
        }
    }

    private HashMap<String, String> getCharacteristicsHashMap() {
        HashMap<String, String> characteristicsHashMap = new HashMap<>();

        characteristicsHashMap.put("sex", txtSex.getText());
        characteristicsHashMap.put("race", txtRace.getText());
        characteristicsHashMap.put("age", txtAge.getText());
        characteristicsHashMap.put("hairColor", txtHairColor.getText());
        characteristicsHashMap.put("eyeColor", txtEyeColor.getText());
        characteristicsHashMap.put("bodyStyle", txtBodyStyle.getText());
        characteristicsHashMap.put("artStyle", txtArtStyle.getText());
        characteristicsHashMap.put("characterType", txtCharacterType.getText());
        characteristicsHashMap.put("gameType", txtGameType.getText());
        characteristicsHashMap.put("species", txtSpecies.getText());
        characteristicsHashMap.put("skinColor", txtSkinColor.getText());

        return characteristicsHashMap;
    }

    void setCharacteristics(Characteristics characteristics) {
        txtSex.setText(characteristics.sex());
        txtRace.setText(characteristics.race());
        txtAge.setText(characteristics.age());
        txtHairColor.setText(characteristics.hairColor());
        txtEyeColor.setText(characteristics.eyeColor());
        txtBodyStyle.setText(characteristics.bodyStyle());

        cbGameCharacter.setSelected(characteristics.isGameCharacter());
        txtArtStyle.setText(characteristics.artStyle());
        txtCharacterType.setText(characteristics.characterType());
        txtGameType.setText(characteristics.gameType());
        txtSpecies.setText(characteristics.species());
        txtSkinColor.setText(characteristics.skinColor());

        fpGameCharacterCharacteristics.setDisable(!characteristics.isGameCharacter());
    }

    void setImage(InputStream imageStream) {
        imgAiImage.setImage(new Image(imageStream));
    }
}
