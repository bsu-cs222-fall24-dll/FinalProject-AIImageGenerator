package bsu.edu.cs222;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
        Path testPath = Path.of(Objects.requireNonNull(getClass().getResource("/redXbox.jpg")).getPath());
        System.out.println(testPath);
        imageLoader = new ImageLoader();
        sampleImage = new ImageWithCharacteristics(Files.readAllBytes(testPath), CharacteristicsTest.getCharacteristics());
    }

    public Path getSafePath(Path path) {
        String os = System.getProperty("os.name").toLowerCase();
        String pathString = path.toString();

        if (os.contains("win")) {
            pathString = pathString.replaceFirst("/", "");
        }

        return Paths.get(pathString);
    }

    public ImageWithCharacteristics getTestImage() throws IOException, ClassNotFoundException {
        Path tempFile = getSafePath(tempDir.resolve("image1.data"));
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile.toFile()))) {
            oos.writeObject(sampleImage);
        }

        return imageLoader.loadImageAndCharacteristics(tempFile.toString());
    }

    public HashMap<String, ImageWithCharacteristics> getImageItems() throws IOException {
        Path tempFile1 = getSafePath(tempDir.resolve("image1.data"));
        Path tempFile2 = getSafePath(tempDir.resolve("image2.data"));

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

        assert Arrays.equals(sampleImage.imageData(), loadedImage.imageData());
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
        assert imageItems.containsKey(tempDir.toAbsolutePath() + "/image1.data") && imageItems.containsKey(tempDir.toAbsolutePath() + "/image2.data");
    }
}