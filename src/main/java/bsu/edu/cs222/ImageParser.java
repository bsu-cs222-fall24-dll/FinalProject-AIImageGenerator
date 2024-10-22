package bsu.edu.cs222;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class ImageParser {
    public Image parseImage(InputStream inputStream) throws IOException {
        return ImageIO.read(inputStream);
    }
}
