package bsu.edu.cs222;

public class PromptBuilder {
    private final Characteristics characteristics;

    public PromptBuilder(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

    public String buildPrompt() {
        StringBuilder prompt = new StringBuilder("A ");

        if (characteristics.age() != null) prompt.append(characteristics.age()).append(" year old ");
        if (characteristics.sex() != null) prompt.append(characteristics.sex()).append(" ");
        if (characteristics.race() != null) prompt.append(characteristics.race()).append(", ");
        if (characteristics.hairColor() != null) prompt.append("with ").append(characteristics.hairColor()).append(" hair ");

        if (characteristics.eyeColor() != null) {
            if (characteristics.hairColor() != null) prompt.append("and ");
            else prompt.append("with ");

            prompt.append(characteristics.eyeColor()).append(" eyes, ");
        }

        if (characteristics.bodyStyle() != null) prompt.append("having a ").append(characteristics.bodyStyle()).append(" body style.");
        else prompt.append("with an undefined body style.");

        if (characteristics.hairLength() != null) prompt.append(" With ").append(characteristics.hairLength()).append(" hair,");
        if (characteristics.eyeShape() != null) prompt.append(characteristics.eyeShape()).append(" shaped eyes,");
        if (characteristics.eyebrowShape() != null) prompt.append(characteristics.eyebrowShape()).append(" eyebrows,");
        if (characteristics.faceShape() != null) prompt.append(" a ").append(characteristics.faceShape()).append(" face, and");
        if (characteristics.cheekbones() != null) prompt.append(characteristics.cheekbones()).append(" cheekbones.");

        if (characteristics.isGameCharacter()) {
            prompt.append(" The character is depicted in a ");

            if (characteristics.artStyle() != null) prompt.append(characteristics.artStyle()).append(" art style, ");
            if (characteristics.characterType() != null) prompt.append("and is a ").append(characteristics.characterType()).append(" ");
          
            if (characteristics.gameType() != null) prompt.append("in a ").append(characteristics.gameType()).append(" game. ");
            else prompt.append("in an unspecified type of game. ");
          
            if (characteristics.species() != null) prompt.append(characteristics.species()).append(" is the species. ");
            if (characteristics.skinColor() != null) prompt.append(characteristics.skinColor()).append(" is the color of their skin.");
        }

        if (characteristics.promptBox() != null) prompt.append(characteristics.promptBox());

        return prompt.toString();
    }
}