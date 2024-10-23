package bsu.edu.cs222;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ViewUtilities {
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    void showErrorDialogBox(String title, String message) {
        showAlert(Alert.AlertType.ERROR, title, message);
    }

    void showInfoDialogBox(String message) {
        showAlert(Alert.AlertType.INFORMATION, "Information", message);
    }
}
