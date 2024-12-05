package bsu.edu.cs222;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public class ImageManager {
    private final ImageFetcher imageFetcher;
    private final CharacteristicsManager characteristicsManager;
    private final ImageSaver imageSaver;
    private byte[] imageData;

    public ImageManager() {
        characteristicsManager = new CharacteristicsManager();
        imageFetcher = new ImageFetcher();
        imageSaver = new ImageSaver();
    }

    public byte[] generateImage(boolean isGameCharacter, HashMap<String, String> characteristics) throws IOException, URISyntaxException {
        Characteristics characterModel = characteristicsManager.createCharacteristics(isGameCharacter, characteristics);

        PromptBuilder promptBuilder = new PromptBuilder(characterModel);
        imageData = imageFetcher.fetchImage(promptBuilder.buildPrompt());
        return imageData;
    }

    public void saveImage(String filename, HashMap<String, String> characteristics, boolean isGameCharacter) throws IOException {
        if (imageData == null) throw new IllegalStateException("An image must be generated before saving.");
        Characteristics characterModel = characteristicsManager.createCharacteristics(isGameCharacter, characteristics);
        String saveDirectoryPath = Config.getSaveDirectory();
        imageSaver.saveImageAndCharacteristics(saveDirectoryPath, imageData, characterModel, filename);
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}