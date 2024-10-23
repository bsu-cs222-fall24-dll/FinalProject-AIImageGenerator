package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.io.InputStream;

public class GeneratorViewController {
    ViewUtilities viewUtilities = new ViewUtilities();
    AIImageController aiImageController = new AIImageController();

    @FXML
    private TextField txtSex, txtRace, txtAge, txtHairColor, txtEyeColor, txtBodyStyle, txtArtStyle, txtCharacterType, txtGameType, txtSpecies, txtSkinColor;

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
        String[] arguments = {txtSex.getText(), txtRace.getText(), txtAge.getText(), txtHairColor.getText(),
                txtEyeColor.getText(), txtBodyStyle.getText(), txtArtStyle.getText(), txtCharacterType.getText(),
                txtGameType.getText(), txtSpecies.getText(), txtSkinColor.getText()};

        // Deletes unneeded whitespace in the arguments
        for (int i = 0; i < arguments.length ; i++) arguments[i] = arguments[i].trim();

        try {
            setDisableInteraction(true);

            InputStream imageInputStream = aiImageController.getImage(cbGameCharacter.isSelected(), arguments);
            imgAiImage.setImage(new Image(imageInputStream));
        } catch (Exception e) {
            viewUtilities.showErrorDialogBox(e.getClass().toString(), e.getMessage());
        } finally {
            setDisableInteraction(false);
        }
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
}
