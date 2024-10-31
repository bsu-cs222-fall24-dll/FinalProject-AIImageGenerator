package bsu.edu.cs222;

//import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class AIImageControllerTest {
    HashMap<String, String> args;
    HashMap<String, String> emptyArgs;
    AIImageController controller;

    void setupArguments() {
        args = new HashMap<>();
        args.put("sex", "male");
        args.put("race", "Caucasian");
        args.put("age", "25");
        args.put("hairColor", "brown");
        args.put("eyeColor", "blue");
        args.put("bodyStyle", "athletic");
        args.put("artStyle", "black and white");
        args.put("characterType", "enemy");
        args.put("gameType", "FPS");
        args.put("species", "Human");
        args.put("skinColor", "white");

        emptyArgs = new HashMap<>();
        emptyArgs.put("sex", "");
        emptyArgs.put("race", "");
        emptyArgs.put("age", "");
        emptyArgs.put("hairColor", "");
        emptyArgs.put("eyeColor", "");
        emptyArgs.put("bodyStyle", "");
        emptyArgs.put("artStyle", "");
        emptyArgs.put("characterType", "");
        emptyArgs.put("gameType", "");
        emptyArgs.put("species", "");
        emptyArgs.put("skinColor", "");
    }
    
    @BeforeEach
    void setUp() {
        controller = new AIImageController();
        setupArguments();
    }

    void fieldsTest(boolean isEmpty, HashMap<String, String> testArgs) {
        boolean hasEmptyFields = false;

        try {
            controller.checkForEmptyFields(testArgs);
        } catch (IllegalArgumentException e) {
            hasEmptyFields = true;
        }

        assertEquals(isEmpty, hasEmptyFields);
    }

    @Test
    void testFilledFields() {
        fieldsTest(false, args);
    }

    @Test
    void testEmptyFields() {
        fieldsTest(true, emptyArgs);
    }

    @Test
    void testGetCharacteristics() {
        assertNotNull(controller.getCharacteristicsFromArgs(false, args));
    }
}
