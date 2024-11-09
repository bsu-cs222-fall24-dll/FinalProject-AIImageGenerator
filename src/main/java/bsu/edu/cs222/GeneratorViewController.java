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

    private void setDisableInteraction(boolean isDisabled) {
        gpGeneratorViewContainer.setDisable(isDisabled);
    }

    @FXML
    private void toggleGameCharacterCharacteristicsInteraction() {
        fpGameCharacterCharacteristics.setDisable(!cbGameCharacter.isSelected());
    }

    @FXML
    private void generateAndSetImage() {
        HashMap<String, String> characteristics = getCharacteristics();

        try {
            setDisableInteraction(true);

            InputStream imageInputStream = imageGenerator.getImage(cbGameCharacter.isSelected(), characteristics);
            imgAiImage.setImage(new Image(imageInputStream));
        } catch (Exception e) {
            viewUtilities.showErrorDialogBox(e.getClass().toString(), e.getMessage());
        } finally {
            setDisableInteraction(false);
        }
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    private HashMap<String, String> getCharacteristics() {
        HashMap<String, String> characteristics = new HashMap<>();

        characteristics.put("sex", txtSex.getText());
        characteristics.put("race", txtRace.getText());
        characteristics.put("age", txtAge.getText());
        characteristics.put("hairColor", txtHairColor.getText());
        characteristics.put("eyeColor", txtEyeColor.getText());
        characteristics.put("bodyStyle", txtBodyStyle.getText());
        characteristics.put("artStyle", txtArtStyle.getText());
        characteristics.put("characterType", txtCharacterType.getText());
        characteristics.put("gameType", txtGameType.getText());
        characteristics.put("species", txtSpecies.getText());
        characteristics.put("skinColor", txtSkinColor.getText());

        return characteristics;
    }

    @FXML
    private void clearCharacteristics(){
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
    }

    void setCharacteristics(Characteristics characteristics) {
        txtSex.setText(characteristics.sex());
        txtRace.setText(characteristics.race());
        txtAge.setText(characteristics.age());
        txtHairColor.setText(characteristics.hairColor());
        txtEyeColor.setText(characteristics.eyeColor());
        txtBodyStyle.setText(characteristics.bodyStyle());
        txtArtStyle.setText(characteristics.artStyle());
        txtCharacterType.setText(characteristics.characterType());
        txtGameType.setText(characteristics.gameType());
        txtSpecies.setText(characteristics.species());
        txtSkinColor.setText(characteristics.skinColor());
    }

    void setImage(InputStream imageStream) {
        imgAiImage.setImage(new Image(imageStream));
    }
}
