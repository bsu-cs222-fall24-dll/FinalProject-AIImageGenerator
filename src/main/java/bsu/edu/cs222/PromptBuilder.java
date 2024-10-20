package bsu.edu.cs222;

public class PromptBuilder {
    private final Characteristics characteristics;

    public PromptBuilder(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

    public String buildPrompt() {
        StringBuilder prompt = new StringBuilder("A ");

        if (characteristics.getAge() != null) prompt.append(characteristics.getAge()).append(" year old ");
        if (characteristics.getSex() != null) prompt.append(characteristics.getSex()).append(" ");
        if (characteristics.getRace() != null) prompt.append(characteristics.getRace()).append(", ");
        if (characteristics.getHairColor() != null) prompt.append("with ").append(characteristics.getHairColor()).append(" hair ");

        if (characteristics.getEyeColor() != null) {
            if (characteristics.getHairColor() != null) prompt.append("and ");
            else prompt.append("with ");

            prompt.append(characteristics.getEyeColor()).append(" eyes, ");
        }

        if (characteristics.getBodyStyle() != null) prompt.append("having a ").append(characteristics.getBodyStyle()).append(" body style.");
        else prompt.append("with an undefined body style.");

        if (characteristics.isGameCharacter()) {
            prompt.append(" The character is depicted in a ");

            if (characteristics.getArtStyle() != null) prompt.append(characteristics.getArtStyle()).append(" art style, ");
            if (characteristics.getCharacterType() != null) prompt.append("and is a ").append(characteristics.getCharacterType()).append(" ");

            if (characteristics.getGameType() != null) prompt.append("in a ").append(characteristics.getGameType()).append(" game.");
            else prompt.append("in an unspecified type of game.");
        }

        return prompt.toString();
    }
}