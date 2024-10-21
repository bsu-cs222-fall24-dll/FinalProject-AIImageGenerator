package bsu.cs222.finalprojectaiimagegenerator;

public abstract class Characteristics {
    protected String hair;
    protected String eyes;
    protected String gender;

    public Characteristics(String hair, String eyes, String gender) {
        this.hair = hair;
        this.eyes = eyes;
        this.gender = gender;
    }

    @Override
    public abstract String toString();
}
