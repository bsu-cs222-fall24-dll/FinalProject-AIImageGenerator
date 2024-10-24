package bsu.edu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AIImageController {
    ImageFetcher imageFetcher = new ImageFetcher();
    InputStream inputStream;

     void checkForEmptyFields(String[] args) {
        Set<String> argSet = new HashSet<>(Arrays.asList(args));
        argSet.removeIf(arg -> Objects.equals(arg, ""));

        if (argSet.isEmpty()) throw new IllegalArgumentException("Need at least one argument.");
    }

    Characteristics getCharacteristicsFromArgs(boolean isGameCharacter, String[] args) {
        checkForEmptyFields(args);

        if (isGameCharacter) return new Characteristics(args[0], args[1], args[2], args[3], args[4],
                args[5], args[6], args[7], args[8], args[9], args[10]);
        else return new Characteristics(args[0], args[1], args[2], args[3], args[4], args[5]);
    }

    public InputStream getImage(boolean isGameCharacter, String[] arguments) throws IOException, URISyntaxException {
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
