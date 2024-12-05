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

    private void addBasicDescription(List<String> promptParts) {
        if(!characteristics.age().isEmpty())
            promptParts.add(characteristics.age() + " year old" );
        if(!characteristics.sex().isEmpty())
            promptParts.add(characteristics.sex());
        if(!characteristics.race().isEmpty())
            promptParts.add(characteristics.race());
        if(!characteristics.hairColor().isEmpty())
            promptParts.add("with " + characteristics.hairColor() + " hair");
        if (!characteristics.eyeColor().isEmpty())
            promptParts.add("and " + characteristics.eyeColor() + " eyes");
    }

    private void addBodyFeatures(List<String> promptParts) {
        if (characteristics.bodyStyle() != null && !characteristics.bodyStyle().isEmpty())
            promptParts.add("having a " + characteristics.bodyStyle() + " body style");
        if (characteristics.hairLength() != null && !characteristics.hairLength().isEmpty())
            promptParts.add("with " + characteristics.hairLength() + " hair");
        if (characteristics.eyeShape() != null && !characteristics.eyeShape().isEmpty())
            promptParts.add(characteristics.eyeShape() + " shaped eyes");
        if (characteristics.eyebrowShape() != null && !characteristics.eyebrowShape().isEmpty())
            promptParts.add(characteristics.eyebrowShape() + " eyebrows");
        if (characteristics.faceShape() != null && !characteristics.faceShape().isEmpty())
            promptParts.add("a " + characteristics.faceShape() + " face");
        if (characteristics.cheekbones() != null && !characteristics.cheekbones().isEmpty())
            promptParts.add(characteristics.cheekbones() + " cheekbones");
    }

    private void addGameRelatedInfo(List<String> promptParts) {
        if (characteristics.isGameCharacter()) {
            if (characteristics.artStyle() != null && !characteristics.artStyle().isEmpty())
                promptParts.add("The character is depicted in a " + characteristics.artStyle() + " art style");
            if (characteristics.characterType() != null && !characteristics.characterType().isEmpty())
                promptParts.add("and is a " + characteristics.characterType());
            if (characteristics.gameType() != null && !characteristics.gameType().isEmpty())
                promptParts.add("in a " + characteristics.gameType() + " game.");
            if (characteristics.species() != null && !characteristics.species().isEmpty())
                promptParts.add(characteristics.species() + " is the species.");
            if (characteristics.skinColor() != null && !characteristics.skinColor().isEmpty())
                promptParts.add(characteristics.skinColor() + " is the color of their skin.");
        }
    }

    private void addAdditionalPrompt(List<String> promptParts) {
        if (characteristics.prompt() != null && !characteristics.prompt().isEmpty())
            promptParts.add(characteristics.prompt());
    }
}
