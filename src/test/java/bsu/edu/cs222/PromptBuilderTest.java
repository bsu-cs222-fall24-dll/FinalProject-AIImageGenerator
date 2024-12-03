package bsu.edu.cs222;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PromptBuilderTest {
    PromptBuilder promptBuilder;
    Characteristics characteristics;

    @BeforeEach
    public void setUp() {
        characteristics = CharacteristicsTest.getCharacteristics();
        promptBuilder = new PromptBuilder(characteristics);
    }

    @Test
    public void testBuildPrompt() {
        String prompt = promptBuilder.buildPrompt();
        assertEquals("25 year old male Caucasian with brown hair and blue eyes having a athletic body style. " +
                "The character is depicted in a black and white art style and is a enemy in a FPS game. " +
                "Human is the species. white is the color of their skin.", prompt);
    }
}
