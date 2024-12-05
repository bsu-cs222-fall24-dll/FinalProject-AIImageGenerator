package bsu.edu.cs222;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ImageLoaderTest {
    @TempDir
    Path tempDir;

    private ImageLoader imageLoader;
    private ImageWithCharacteristics sampleImage;

    @BeforeEach
    void setUp() throws IOException {
        Path testImagePath = Paths.get(getSafePath(Objects.requireNonNull(getClass().getResource("/redXbox.jpg")).getPath()));
        imageLoader = new ImageLoader();
        sampleImage = new ImageWithCharacteristics(Files.readAllBytes(testImagePath), CharacteristicsTest.getCharacteristics());
    }

    public String getSafePath(String pathString) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            pathString = pathString.replaceFirst("/", "");
        }

        // Required for path names that have spaces in them as there are formated incorrectly,
        // therefore "redundancy" warning of "\\ " is obsolete. See README.
        pathString = pathString.replaceAll("%20", "\\ ");

        return pathString;
    }

    public ImageWithCharacteristics getTestImage() throws IOException, ClassNotFoundException {
        Path tempFile = tempDir.resolve("image1.data");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile.toFile()))) {
            oos.writeObject(sampleImage);
        }

        return imageLoader.loadImageAndCharacteristics(tempFile.toString());
    }

    public HashMap<String, ImageWithCharacteristics> getImageItems() throws IOException {
        Path tempFile1 = tempDir.resolve("image1.data");
        Path tempFile2 = tempDir.resolve("image2.data");

        try (ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(tempFile1.toFile()));
             ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(tempFile2.toFile()))) {
            oos1.writeObject(sampleImage);
            oos2.writeObject(sampleImage);
        }

        return imageLoader.loadImageItems(tempDir.toString());
    }

    @Test
    void testLoadImageData() throws IOException, ClassNotFoundException {
        ImageWithCharacteristics loadedImage = getTestImage();

        assertArrayEquals(sampleImage.imageData(), loadedImage.imageData());
    }

    @Test
    void testLoadCharacteristics() throws IOException, ClassNotFoundException {
        ImageWithCharacteristics loadedImage = getTestImage();

        assertEquals(sampleImage.characteristics(), loadedImage.characteristics());
    }

    @Test
    void testLoadAmountOfItems() throws IOException {
        HashMap<String, ImageWithCharacteristics> imageItems = getImageItems();
        assert imageItems.size() == 2;
    }

    @Test
    void testLoadFilePaths() throws IOException {
        HashMap<String, ImageWithCharacteristics> imageItems = getImageItems();
        String slashChar = System.getProperty("os.name").toLowerCase().contains("win") ? "\\" : "/";

        assertTrue(imageItems.containsKey(tempDir.toAbsolutePath() + slashChar + "image1.data")
                && imageItems.containsKey(tempDir.toAbsolutePath() + slashChar + "image2.data"));
    }
}