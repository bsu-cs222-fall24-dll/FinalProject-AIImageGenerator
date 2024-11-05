package bsu.edu.cs222;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

public record ImageWithCharacteristics(byte[] imageData, Characteristics characteristics) implements Serializable {
    public InputStream getImageStream() {
        return new ByteArrayInputStream(imageData);
    }
}
