package bsu.edu.cs222;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CharacteristicsManagerTest {

    private CharacteristicsManager characteristicsManager;
    private HashMap<String, String> characteristicsMap;

    @BeforeEach
    void setUp() {
        characteristicsManager = new CharacteristicsManager();

        characteristicsMap = new HashMap<>();
        characteristicsMap.put("sex", "");
        characteristicsMap.put("race", "");
        characteristicsMap.put("age", "");
        characteristicsMap.put("hairColor", "");
        characteristicsMap.put("eyeColor", "");
        characteristicsMap.put("bodyStyle", "");
        characteristicsMap.put("artStyle", "");
        characteristicsMap.put("characterType", "");
        characteristicsMap.put("gameType", "");
        characteristicsMap.put("species", "");
        characteristicsMap.put("skinColor", "");
    }

    @Test
    void createCharacteristics_throwsExceptionWhenAllFieldsAreEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                        characteristicsManager.createCharacteristics(false, characteristicsMap),
                "Expected exception when all fields are empty."
        );
    }

    @Test
    void createCharacteristics_setsSexInCharacteristics() {
        characteristicsMap.put("sex", "Male");
        Characteristics characteristics = characteristicsManager.createCharacteristics(false, characteristicsMap);

        assertEquals("Male", characteristics.sex());
    }

    @Test
    void createCharacteristics_setsAgeInCharacteristics() {
        characteristicsMap.put("age", "25");
        Characteristics characteristics = characteristicsManager.createCharacteristics(false, characteristicsMap);

        assertEquals("25", characteristics.age());
    }

    @Test
    void createCharacteristics_setsIsGameCharacterFlag() {
        characteristicsMap.put("age", "25");
        Characteristics characteristics = characteristicsManager.createCharacteristics(true, characteristicsMap);

        assertTrue(characteristics.isGameCharacter());
    }
}