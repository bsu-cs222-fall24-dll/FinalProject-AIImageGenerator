package bsu.edu.cs222;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageSaverTest {
    public static void deleteRedXboxImage() {
        File file = new File("./saved-images/redXbox.data");
        if (file.exists()) {
            if (!file.delete()) {
                throw new AssertionError("Unable to delete redXbox file " + file);
            }
        }
    }

    public static void saveRedXboxImage() throws IOException {
        ImageSaver imageSaver = new ImageSaver();
        try (InputStream imageStream = ImageSaverTest.class.getResourceAsStream("/redXbox.jpg")) {
            Characteristics characteristics = CharacteristicsTest.getCharacteristics();
            imageSaver.saveImageAndCharacteristics(imageStream, characteristics, "redXbox");
        }
    }

    @BeforeEach
    void setUp() {
        deleteRedXboxImage();
    }

    @Test
    public void testSaveImageWithCharacteristics() throws IOException {
        saveRedXboxImage();
    }
}
