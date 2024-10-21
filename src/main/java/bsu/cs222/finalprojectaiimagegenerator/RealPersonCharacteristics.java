package bsu.cs222.finalprojectaiimagegenerator;

public class RealPersonCharacteristics extends Characteristics {
    private String race;
    private String bodyType;

    public RealPersonCharacteristics(String hair, String eyes, String race, String bodyType, String gender) {
        super(hair, eyes, gender);
        this.race = race;
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return "Real Person Characteristics:\n" +
                "Hair: " + hair + "\n" +
                "Eyes: " + eyes + "\n" +
                "Race: " + race + "\n" +
                "Body Type: " + bodyType + "\n" +
                "Gender: " + gender + "\n";
    }
}