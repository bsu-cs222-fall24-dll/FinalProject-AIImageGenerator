package bsu.edu.cs222;

public class Characteristics {
    // Default / suspect characteristics
    private String sex;
    private String race;
    private String age;
    private String hairColor;
    private String eyeColor;
    private String bodyStyle;

    // Game character characteristics
    private boolean isGameCharacter = false;
    private String artStyle;
    private String characterType;
    private String gameType;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public boolean isGameCharacter() {
        return isGameCharacter;
    }

    public void setGameCharacter(boolean gameCharacter) {
        isGameCharacter = gameCharacter;
    }

    public String getArtStyle() {
        return artStyle;
    }

    public void setArtStyle(String artStyle) {
        this.artStyle = artStyle;
    }

    public String getCharacterType() {
        return characterType;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public Characteristics(String sex, String race, String age, String hairColor, String eyeColor, String bodyStyle) {
        this.sex = sex;
        this.race = race;
        this.age = age;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.bodyStyle = bodyStyle;
    }

    public Characteristics(String sex, String race, String age, String hairColor, String eyeColor, String bodyStyle, String artStyle, String characterType, String gameType) {
        this(sex, race, age, hairColor, eyeColor, bodyStyle);

        this.isGameCharacter = true;
        this.artStyle = artStyle;
        this.characterType = characterType;
        this.gameType = gameType;
    }
}
