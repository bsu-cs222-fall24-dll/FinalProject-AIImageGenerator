package bsu.cs222.finalprojectaiimagegenerator;

public class FantasyCharacteristics extends Characteristics {
    private String skinColor;
    private String species;
    private String artStyle;

    public FantasyCharacteristics(String hair, String eyes, String skinColor, String species, String artStyle, String gender) {
        super(hair, eyes, gender);
        this.skinColor = skinColor;
        this.species = species;
        this.artStyle = artStyle;
    }

    @Override
    public String toString() {
        return "Fantasy Character Characteristics:\n" +
                "Hair: " + hair + "\n" +
                "Eyes: " + eyes + "\n" +
                "Skin Color: " + skinColor + "\n" +
                "Species: " + species + "\n" +
                "Art Style: " + artStyle + "\n" +
                "Gender: " + gender + "\n";
    }
}