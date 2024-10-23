package bsu.edu.cs222;

import java.awt.*;
import java.io.InputStream;

public class AIImageController {
    public InputStream getImage(boolean isGameCharacter, String sex, String race, String age, String hairColor, String eyeColor, String bodyStyle, String artStyle, String characterType, String gameType, String species, String skinColor) {
        Characteristics characteristics;
        Image image = null;
        InputStream inputStream = null;

        if (isGameCharacter) characteristics = new Characteristics(sex, race, age, hairColor, eyeColor, bodyStyle, artStyle, characterType, gameType, species, skinColor);
        else characteristics = new Characteristics(sex, race, age, hairColor, eyeColor, bodyStyle);

        GUIUtilities utilities = new GUIUtilities();
        ImageParser imageParser = new ImageParser();
        ImageFetcher imageFetcher = new ImageFetcher();
        PromptBuilder promptBuilder = new PromptBuilder(characteristics);

        String prompt = promptBuilder.buildPrompt();

        try {
            inputStream = imageFetcher.fetchImage(prompt);
//            image = imageParser.parseImage(inputStream);
        } catch (Exception e) {
            utilities.showDialogBox(e.getMessage());
        }

        return inputStream;
    }
}
