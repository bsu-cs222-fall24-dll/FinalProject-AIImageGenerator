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
        
        addBasicDescription(promptParts);
        addBodyFeatures(promptParts);
        addGameRelatedInfo(promptParts);
        addAdditionalPrompt(promptParts);

        return String.join(" ", promptParts).trim();
    }

    private void appendIfNotNull(List<String> promptParts, String value) {
        if (value != null && value != "") {
            promptParts.add(value);
        }
    }

    private void addBasicDescription(List<String> promptParts) {
        appendIfNotNull(promptParts, characteristics.age() != null ? characteristics.age() + " year old" : null);
        appendIfNotNull(promptParts, characteristics.sex());
        appendIfNotNull(promptParts, characteristics.race());
        appendIfNotNull(promptParts, characteristics.hairColor() != null ? "with " + characteristics.hairColor() + " hair" : null);
        if (characteristics.eyeColor() != null) {
            appendIfNotNull(promptParts, "and " + characteristics.eyeColor() + " eyes");
        }
    }

    private void addBodyFeatures(List<String> promptParts) {
        appendIfNotNull(promptParts, characteristics.bodyStyle() != null ? "having a " + characteristics.bodyStyle() + " body style" : null);
        appendIfNotNull(promptParts, characteristics.hairLength() != null ? "With " + characteristics.hairLength() + " hair" : null);
        appendIfNotNull(promptParts, characteristics.eyeShape() != null ? characteristics.eyeShape() + " shaped eyes" : null);
        appendIfNotNull(promptParts, characteristics.eyebrowShape() != null ? characteristics.eyebrowShape() + " eyebrows" : null);
        appendIfNotNull(promptParts, characteristics.faceShape() != null ? "a " + characteristics.faceShape() + " face" : null);
        appendIfNotNull(promptParts, characteristics.cheekbones() != null ? characteristics.cheekbones() + " cheekbones" : null);
    }

    private void addGameRelatedInfo(List<String> promptParts) {
        if (characteristics.isGameCharacter()) {
            appendIfNotNull(promptParts, "The character is depicted in a " + characteristics.artStyle() + " art style");
            appendIfNotNull(promptParts, characteristics.characterType() != null ? "and is a " + characteristics.characterType() : null);
            appendIfNotNull(promptParts, characteristics.gameType() != null ? "in a " + characteristics.gameType() + " game." : null);
            appendIfNotNull(promptParts, characteristics.species() != null ? characteristics.species() + " is the species." : null);
            appendIfNotNull(promptParts, characteristics.skinColor() != null ? characteristics.skinColor() + " is the color of their skin. " : null);
        }
    }

    private void addAdditionalPrompt(List<String> promptParts) {
        appendIfNotNull(promptParts, characteristics.prompt() != null ? characteristics.prompt() : null);
    }
}
