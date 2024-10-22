package bsu.edu.cs222;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PromptBuilderTest {
    PromptBuilder promptBuilder;
    Characteristics characteristics;

    @BeforeEach
    public void setUp() {
        characteristics = new Characteristics("male", "Caucasian", "25", "brown", "blue", "athletic");
        promptBuilder = new PromptBuilder(characteristics);
    }

    @Test
    public void testBuildPrompt() {
        String prompt = promptBuilder.buildPrompt();
        assertEquals("A 25 year old male Caucasian, with brown hair and blue eyes, having a athletic body style.", prompt);
    }

    @Test
    public void testBuildPromptNoEyes() {
        characteristics.setEyeColor(null);

        String prompt = promptBuilder.buildPrompt();
        assertEquals("A 25 year old male Caucasian, with brown hair having a athletic body style.", prompt);
    }

    @Test
    public void testBuildPromptNoBodyStyle() {
        characteristics.setBodyStyle(null);

        String prompt = promptBuilder.buildPrompt();
        assertEquals("A 25 year old male Caucasian, with brown hair and blue eyes, with an undefined body style.", prompt);
    }

    @Test
    public void testBuildPromptGameCharacter() {
        characteristics.setGameCharacter(true);
        characteristics.setArtStyle("black and white");
        characteristics.setCharacterType("enemy");
        characteristics.setGameType("FPS");
        characteristics.setSpecies("Human");
        characteristics.setSkinColor("White");

        String prompt = promptBuilder.buildPrompt();
        assertEquals("A 25 year old male Caucasian, with brown hair and blue eyes, having a athletic body style. " +
                "The character is depicted in a black and white art style, and is a enemy in a FPS game. " +
                "Human is the species. White is the color of their skin.", prompt);
    }

    @Test
    public void testBuildPromptGameCharacterWithoutGameType() {
        characteristics.setGameCharacter(true);
        characteristics.setArtStyle("black and white");
        characteristics.setCharacterType("enemy");
        characteristics.setSpecies("Human");
        characteristics.setSkinColor("White");

        String prompt = promptBuilder.buildPrompt();
        assertEquals("A 25 year old male Caucasian, with brown hair and blue eyes, having a athletic body style. " +
                "The character is depicted in a black and white art style, and is a enemy in an unspecified type of game. " +
                "Human is the species. White is the color of their skin.", prompt);
    }
}
