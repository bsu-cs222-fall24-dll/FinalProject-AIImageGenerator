package bsu.edu.cs222;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ViewUtilities {
    void showDialogBox(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText(message);

        alert.showAndWait();
    }
}
