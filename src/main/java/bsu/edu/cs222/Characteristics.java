package bsu.edu.cs222;

import java.io.Serializable;
import java.util.HashMap;

public record Characteristics(
        String sex,
        String race,
        String age,
        String hairColor,
        String eyeColor,
        String bodyStyle,
        String hairLength,
        String eyeShape,
        String eyebrowShape,
        String faceShape,
        String cheekbones,
        boolean isGameCharacter,
        String artStyle,
        String characterType,
        String gameType,
        String species,
        String skinColor

) implements Serializable {
    public Characteristics(HashMap<String, String> arguments, boolean isGameCharacter) {
        this(arguments.get("sex"), arguments.get("race"), arguments.get("age"), arguments.get("hairColor"),
                arguments.get("eyeColor"), arguments.get("bodyStyle"), arguments.get("hairLength"), arguments.get("eyeShape"), arguments.get("eyebrowShape"), arguments.get("faceShape"), arguments.get("cheekbones"), isGameCharacter, arguments.get("artStyle"),
                arguments.get("characterType"), arguments.get("gameType"), arguments.get("species"), arguments.get("skinColor"));
    }
}