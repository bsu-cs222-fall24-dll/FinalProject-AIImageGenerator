package bsu.edu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.image.Image;

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
    private void cbGameCharacterOnActionHandler() {
        fpGameCharacterCharacteristics.setDisable(!cbGameCharacter.isSelected());
    }

    @FXML
    private void btnGenerateOnAction() {
        InputStream imageInputStream;

        String[] arguments = {txtSex.getText(), txtRace.getText(), txtAge.getText(), txtHairColor.getText(),
                txtEyeColor.getText(), txtBodyStyle.getText(), txtArtStyle.getText(), txtCharacterType.getText(),
                txtGameType.getText(), txtSpecies.getText(), txtSkinColor.getText()};

        // Deletes unneeded whitespace in the arguments
        for (int i = 0; i < arguments.length ; i++) arguments[i] = arguments[i].trim();

        try {
            imageInputStream = aiImageController.getImage(cbGameCharacter.isSelected(), arguments);
            imgAiImage.setImage(new Image(imageInputStream));
        } catch (Exception e) {
            viewUtilities.showErrorDialogBox(e.getClass().toString(), e.getMessage());
        }
    }
}
