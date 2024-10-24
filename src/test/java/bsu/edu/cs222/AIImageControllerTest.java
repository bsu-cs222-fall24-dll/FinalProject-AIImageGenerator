package bsu.edu.cs222;

//import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AIImageControllerTest {
    String[] args;
    String[] emptyArgs;
    AIImageController controller;

    @BeforeEach
    void setUp() {
        controller = new AIImageController();
        args = new String[] {"male", "Caucasian", "25", "brown", "blue", "athletic", "black and white", "enemy", "FPS","Human","white"};
        emptyArgs = new String[] {"", "", "", "", "", "", "", "", "","",""};
    }

    void fieldsTest(boolean isEmpty, String[] testArgs) {
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
