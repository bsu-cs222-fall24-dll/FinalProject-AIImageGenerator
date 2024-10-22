package bsu.edu.cs222;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.io.*;

public class ImageParserTest {
    InputStream inputStream;
    ImageParser imageParser;

    @BeforeEach
    public void setUp() {
        imageParser = new ImageParser();
        inputStream = getClass().getResourceAsStream("/sofa.jpeg");
    }

    @Test
    public void testConvertInputStreamToImage() {
        assertNotNull(inputStream, "InputStream should not be null");
    }

    @Test
    public void testGetImageObject() throws IOException {
        Image image = imageParser.parseImage(inputStream);
        assertNotNull(image, "Image should not be null");
    }
}

