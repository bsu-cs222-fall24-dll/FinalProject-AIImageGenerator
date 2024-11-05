package bsu.edu.cs222;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FileNameValidatorTest {
    @Test
    public void testValidFileNameWithoutSpaces() {
        assertTrue(FileNameValidator.isValidFileName("redXbox"));
    }

    @Test
    public void testValidFileNameWithSpaces() {
        assertTrue(FileNameValidator.isValidFileName("red Xbox"));
    }

    @Test
    public void testValidFileNameWithUnderscores() {
        assertTrue(FileNameValidator.isValidFileName("red_Xbox"));
    }

    @Test
    public void testValidFileNameWithHyphens() {
        assertTrue(FileNameValidator.isValidFileName("red-Xbox"));
    }

    @Test
    public void testInvalidFileNameWithSpecialCharacters() {
        assertFalse(FileNameValidator.isValidFileName("red `-=\\Xbox"));
    }

    @Test
    public void testValidFileNameWithLeadingOrTrailingSpaces() {
        assertTrue(FileNameValidator.isValidFileName(" redXbox "));
    }

    @Test
    public void testInvalidFileNameWithEmptyString() {
        assertFalse(FileNameValidator.isValidFileName(""));
    }

    @Test
    public void testInvalidFileNameWithOnlySpecialCharacters() {
        assertFalse(FileNameValidator.isValidFileName("!!!@@@###"));
    }

    @Test
    public void testInvalidFileNameTooLong() {
        String longName = "a".repeat(256);
        assertFalse(FileNameValidator.isValidFileName(longName));
    }

    @Test
    public void testValidFileNameWithNumbers() {
        assertTrue(FileNameValidator.isValidFileName("redXbox123"));
    }

    @Test
    public void testValidFileNameWithMixedCase() {
        assertTrue(FileNameValidator.isValidFileName("RedXBox"));
    }

    @Test
    public void testInvalidFileNameWithPathCharacters() {
        assertFalse(FileNameValidator.isValidFileName("redXbox/invalid"));
    }
}
