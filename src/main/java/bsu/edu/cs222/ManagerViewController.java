package bsu.edu.cs222;

import java.util.HashMap;

public class ManagerViewController {
    private SceneController sceneController;

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
        sceneController.setWindowTitle("AI Image Generator Manager");
    }

    public Characteristics getCharacteristics() {
        HashMap<String, String> characteristicsArgs = new HashMap<>();
        characteristicsArgs.put("sex", "male");
        characteristicsArgs.put("race", "Caucasian");
        characteristicsArgs.put("age", "25");
        characteristicsArgs.put("hairColor", "brown");
        characteristicsArgs.put("eyeColor", "blue");
        characteristicsArgs.put("bodyStyle", "athletic");
        characteristicsArgs.put("artStyle", "black and white");
        characteristicsArgs.put("characterType", "enemy");
        characteristicsArgs.put("gameType", "FPS");
        characteristicsArgs.put("species", "Human");
        characteristicsArgs.put("skinColor", "white");

        return new Characteristics(characteristicsArgs, true);
    }

    public void switchToGeneratorViewScene() {
        sceneController.switchToGeneratorView();
    }

    public void switchToGeneratorViewSceneWithData() {
        sceneController.switchToGeneratorView(getClass().getResourceAsStream("/redXbox.jpg"), getCharacteristics());
    }
}
