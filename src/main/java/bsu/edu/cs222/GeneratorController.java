package bsu.edu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class GeneratorController {
    GUIUtilities guiUtilities = new GUIUtilities();

    @FXML
    private TextField txtSex, txtRace, txtAge, txtHairColor, txtEyeColor, txtBodyStyle, txtArtStyle, txtCharacterType, txtGameType, txtSpecies, txtSkinColor;

    @FXML
    private Button btnGenerate;

    @FXML
    private CheckBox cbGameCharacter;

    @FXML
    private FlowPane fpGameCharacterCharacteristics;

    @FXML
    private void cbGameCharacterOnActionHandler(ActionEvent actionEvent) {
        fpGameCharacterCharacteristics.setDisable(!cbGameCharacter.isSelected());
    }

    @FXML
    private void btnGenerateOnAction(ActionEvent actionEvent) {
        String[] arguments = {txtSex.getText(), txtRace.getText(), txtAge.getText(), txtHairColor.getText(), txtEyeColor.getText(), txtBodyStyle.getText()};
        String[] gameCharacterArguments = {txtArtStyle.getText(), txtCharacterType.getText(), txtGameType.getText()};
        guiUtilities.showDialogBox(arguments.toString());
    }
}
