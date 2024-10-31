package bsu.edu.cs222;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class CharacteristicsTest {
    Characteristics characteristics;

    public static Characteristics getCharacteristics() {
        HashMap<String, String> characteristicsArgs = new HashMap<>();
        characteristicsArgs.put("sex", "male");
        characteristicsArgs.put("race", "Caucasian");
        characteristicsArgs.put("age", "25");
        characteristicsArgs.put("hairColor", "brown");
        characteristicsArgs.put("eyeColor", "blue");
        characteristicsArgs.put("bodyStyle", "athletic");
        characteristicsArgs.put("artStyle", "black and white");
        characteristicsArgs.put("characterType", "enemy");
        characteristicsArgs.put("gameType", "FPS");
        characteristicsArgs.put("species", "Human");
        characteristicsArgs.put("skinColor", "white");

        return new Characteristics(characteristicsArgs, true);
    }

    @BeforeEach
    public void setUp() {
        characteristics = getCharacteristics();
    }

    @Test
    public void testInitialization() {
        assertNotNull(characteristics);
    }

    @Test
    public void testGetSex() {
        assertEquals("male", characteristics.sex());
    }
    @Test
    public void testGetRace() {
        assertEquals("Caucasian", characteristics.race());
    }
    @Test
    public void testGetAge() {
        assertEquals("25", characteristics.age());
    }
    @Test
    public void testGetHairColor() {
        assertEquals("brown", characteristics.hairColor());
    }
    @Test
    public void testGetEyeColor() {
        assertEquals("blue", characteristics.eyeColor());
    }
    @Test
    public void testGetBodyStyle() {
        assertEquals("athletic", characteristics.bodyStyle());
    }
    @Test
    public void testGetArtStyle() {
        assertEquals("black and white", characteristics.artStyle());
    }
    @Test
    public void testGetCharacterType() {
        assertEquals("enemy", characteristics.characterType());
    }
    @Test
    public void testGetGameType() {
        assertEquals("FPS", characteristics.gameType());
    }
}
