package bsu.edu.cs222;

import java.util.HashMap;

public record Characteristics(
        String sex,
        String race,
        String age,
        String hairColor,
        String eyeColor,
        String bodyStyle,
        boolean isGameCharacter,
        String artStyle,
        String characterType,
        String gameType,
        String species,
        String skinColor
) {

    public Characteristics(HashMap<String, String> arguments, boolean isGameCharacter) {
        this(arguments.get("sex"), arguments.get("race"), arguments.get("age"), arguments.get("hairColor"),
                arguments.get("eyeColor"), arguments.get("bodyStyle"), isGameCharacter, arguments.get("artStyle"),
                arguments.get("characterType"), arguments.get("gameType"), arguments.get("species"), arguments.get("skinColor"));
    }
}