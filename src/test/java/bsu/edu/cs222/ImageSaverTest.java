package bsu.edu.cs222;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ImageSaverTest {
    private ImageSaver imageSaver;
    private byte[] sampleImageData;
    private Characteristics sampleCharacteristics;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        imageSaver = new ImageSaver();
        sampleImageData = new byte[]{1, 2, 3};
        sampleCharacteristics = CharacteristicsTest.getCharacteristics();
    }

    @Test
    void testFileIsCreatedAfterSave() throws IOException {
        String filename = "testImage";
        Path filePath = tempDir.resolve(filename + ".data");

        imageSaver.saveImageAndCharacteristics(tempDir.toString(), sampleImageData, sampleCharacteristics, filename);

        assertTrue(filePath.toFile().exists(), "The file should be created after save");
    }

    @Test
    void testSavedImageDataMatchesOriginal() throws IOException, ClassNotFoundException {
        String filename = "testImage";
        Path filePath = tempDir.resolve(filename + ".data");
        imageSaver.saveImageAndCharacteristics(tempDir.toString(), sampleImageData, sampleCharacteristics, filename);

        byte[] loadedImageData;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath.toFile()))) {
            ImageWithCharacteristics loadedImageWithChar = (ImageWithCharacteristics) ois.readObject();
            loadedImageData = loadedImageWithChar.imageData();
        }

        assertArrayEquals(sampleImageData, loadedImageData, "The saved image data should match the original data");
    }

    @Test
    void testSavedCharacteristicsMatchOriginal() throws IOException, ClassNotFoundException {
        String filename = "testImage";
        Path filePath = tempDir.resolve(filename + ".data");
        imageSaver.saveImageAndCharacteristics(tempDir.toString(), sampleImageData, sampleCharacteristics, filename);

        Characteristics loadedCharacteristics;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath.toFile()))) {
            ImageWithCharacteristics loadedImageWithChar = (ImageWithCharacteristics) ois.readObject();
            loadedCharacteristics = loadedImageWithChar.characteristics();
        }

        assertEquals(sampleCharacteristics, loadedCharacteristics, "The saved characteristics should match the original characteristics");
    }

    @Test
    void testSaveImageWithInvalidFileNameThrowsException() {
        String invalidFilename = "invalid/file:name";

        assertThrows(IllegalArgumentException.class, () ->
                        imageSaver.saveImageAndCharacteristics(tempDir.toString(), sampleImageData, sampleCharacteristics, invalidFilename),
                "An invalid filename should throw an IllegalArgumentException");
    }
}