package bsu.edu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.*;

public class ImageGeneratorController {
    ImageFetcher imageFetcher = new ImageFetcher();
    InputStream inputStream;

    void checkForEmptyFields(Map<String, String> argsMap) {
        argsMap.values().removeIf(value -> value.trim().isEmpty());

        if (argsMap.isEmpty()) {
            throw new IllegalArgumentException("Need at least one argument.");
        }
    }

    Characteristics getCharacteristicsFromArgs(boolean isGameCharacter, HashMap<String, String> arguments) {
        checkForEmptyFields(arguments);
        return new Characteristics(arguments, isGameCharacter);
    }

    public InputStream getImage(boolean isGameCharacter, HashMap<String, String> arguments) throws IOException, URISyntaxException {
        Characteristics characteristics = getCharacteristicsFromArgs(isGameCharacter, arguments);

        PromptBuilder promptBuilder = new PromptBuilder(characteristics);
        String prompt = promptBuilder.buildPrompt();

        try {
            inputStream = imageFetcher.fetchImage(prompt);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unable to reach the internet", e);
        }

        return inputStream;
    }
}
