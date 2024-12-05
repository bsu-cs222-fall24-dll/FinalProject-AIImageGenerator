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

    String getFilenameDialogInput() {
        TextInputDialog textInputDialog = new TextInputDialog();

        textInputDialog.setTitle("Change filename");
        textInputDialog.setHeaderText("Change filename");
        textInputDialog.setContentText("Enter new filename:");

        textInputDialog.showAndWait();
        return textInputDialog.getEditor().getText();
    }
}
