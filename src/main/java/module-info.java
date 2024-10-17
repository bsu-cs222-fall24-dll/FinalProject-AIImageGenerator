module bsu.cs222.finalprojectaiimagegenerator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens bsu.cs222.finalprojectaiimagegenerator to javafx.fxml;
    exports bsu.cs222.finalprojectaiimagegenerator;
}