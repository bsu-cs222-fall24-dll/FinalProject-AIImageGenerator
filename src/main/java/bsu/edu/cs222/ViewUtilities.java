package bsu.edu.cs222;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ViewUtilities {
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    void showErrorDialogBox(String title, String message) {
        showAlert(title, message);
    }
}
