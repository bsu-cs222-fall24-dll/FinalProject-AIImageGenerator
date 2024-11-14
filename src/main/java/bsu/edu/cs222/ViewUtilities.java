package bsu.edu.cs222;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

import java.util.Optional;

public class ViewUtilities {
    private Alert getAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        return alert;
    }

    private void showMessageAlert(String title, String message) {
        Alert alert = getAlert(title, message, Alert.AlertType.ERROR);
        alert.showAndWait();
    }

    public boolean getConfirmationAlertResult(String title, String message) {
        Alert alert = getAlert(title, message, Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }

    void showErrorDialogBox(String title, String message) {
        showMessageAlert(title, message);
    }

    /* Warnings about the parameters "always being" a certain value should be disregarded
       as this is a utility function and is planned to be used more in the future.        */
    String getDialogInput(String title, String message) {
        TextInputDialog textInputDialog = new TextInputDialog();

        textInputDialog.setTitle(title);
        textInputDialog.setHeaderText(title);
        textInputDialog.setContentText(message);

        textInputDialog.showAndWait();
        return textInputDialog.getEditor().getText();
    }
}
