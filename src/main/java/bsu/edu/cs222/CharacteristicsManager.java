package bsu.edu.cs222;

import java.util.HashMap;

public class CharacteristicsManager {
    private void checkForEmptyFields(HashMap<String, String> map) {
        boolean noArguments = map.values().stream().allMatch(value -> value == null || value.trim().isEmpty());
        if (noArguments) {
            throw new IllegalArgumentException("At least one characteristic is required.");
        }
    }

    public Characteristics createCharacteristics(boolean isGameCharacter, HashMap<String, String> args) {
        checkForEmptyFields(args);
        return new Characteristics(args, isGameCharacter);
    }
}