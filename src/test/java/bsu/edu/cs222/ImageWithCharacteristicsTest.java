package bsu.edu.cs222;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class ImageWithCharacteristicsTest {
    ImageWithCharacteristics imageWithCharacteristics;

    @BeforeEach
    public void setUp() throws IOException {
        Characteristics characteristics = CharacteristicsTest.getCharacteristics();
        try (InputStream imageStream = getClass().getResourceAsStream("/redXbox.jpg")) {
            assert imageStream != null;
            imageWithCharacteristics = new ImageWithCharacteristics(imageStream.readAllBytes(), characteristics);
        }
    }

    @Test
    public void testGetImageInputStream() {
        assertNotNull(imageWithCharacteristics.getImageStream());
    }

    @Test
    public void testGetImageBytes() {
        assertNotNull(imageWithCharacteristics.imageData());
    }

    @Test
    public void testGetCharacteristics() {
        assertNotNull(imageWithCharacteristics.characteristics());
    }
}
