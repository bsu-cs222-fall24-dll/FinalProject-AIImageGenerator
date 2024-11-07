package bsu.edu.cs222;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ImageLoaderTest {
    public static ImageWithCharacteristics loadRedXboxImage() throws IOException, ClassNotFoundException  {
        ImageLoader imageLoader = new ImageLoader();
        return imageLoader.loadImageAndCharacteristics("./saved-images/redXbox.data");
    }

    @BeforeEach
    void setUp() throws IOException {
        ImageSaverTest.deleteRedXboxImage();
        ImageSaverTest.saveRedXboxImage();
    }

    @Test
    public void testLoadRedXboxImage() throws IOException, ClassNotFoundException {
        ImageWithCharacteristics imageWithCharacteristics = loadRedXboxImage();
        assertNotNull(imageWithCharacteristics);
    }
}
