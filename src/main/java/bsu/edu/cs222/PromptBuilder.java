package bsu.edu.cs222;

import java.util.ArrayList;
import java.util.List;

public class PromptBuilder {
    private final Characteristics characteristics;

    public PromptBuilder(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

    public String buildPrompt() {
        List<String> promptParts = new ArrayList<>();

        // Build basic description
        appendIfNotNull(promptParts, characteristics.age() != null, characteristics.age() + " year old");
        appendIfNotNull(promptParts, characteristics.sex() != null, characteristics.sex());
        appendIfNotNull(promptParts, characteristics.race() != null, characteristics.race());
        appendIfNotNull(promptParts, characteristics.hairColor() != null, "with " + characteristics.hairColor() + " hair");

        if (characteristics.eyeColor() != null) {
            appendIfNotNull(promptParts, true, "and " + characteristics.eyeColor() + " eyes");
        }

        // Handle body style and detailed features
        appendIfNotNull(promptParts, characteristics.bodyStyle() != null, "having a " + characteristics.bodyStyle() + " body style.");
        appendIfNotNull(promptParts, characteristics.hairLength() != null, "With " + characteristics.hairLength() + " hair");
        appendIfNotNull(promptParts, characteristics.eyeShape() != null, characteristics.eyeShape() + " shaped eyes");
        appendIfNotNull(promptParts, characteristics.eyebrowShape() != null, characteristics.eyebrowShape() + " eyebrows");
        appendIfNotNull(promptParts, characteristics.faceShape() != null, "a " + characteristics.faceShape() + " face");
        appendIfNotNull(promptParts, characteristics.cheekbones() != null, characteristics.cheekbones() + " cheekbones");

        // Add game-related info if it's a game character
        if (characteristics.isGameCharacter()) {
            appendIfNotNull(promptParts, true, "The character is depicted in a " + characteristics.artStyle() + " art style");
            appendIfNotNull(promptParts, characteristics.characterType() != null, "and is a " + characteristics.characterType());
            appendIfNotNull(promptParts, characteristics.gameType() != null, "in a " + characteristics.gameType() + " game.");
            appendIfNotNull(promptParts, characteristics.species() != null, characteristics.species() + " is the species.");
            appendIfNotNull(promptParts, characteristics.skinColor() != null, characteristics.skinColor() + " is the color of their skin.");
        }

        return String.join(" ", promptParts).trim();
    }

    private void appendIfNotNull(List<String> promptParts, boolean condition, String value) {
        if (condition && value != null) {
            promptParts.add(value);
        }
    }
}
