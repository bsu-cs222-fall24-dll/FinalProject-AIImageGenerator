package bsu.edu.cs222;

import java.io.*;

public class ImageLoader {
    public ImageWithCharacteristics loadImageAndCharacteristics(String filePath) throws IOException, ClassNotFoundException {
        // Deserialize the ImageWithCharacteristics object from the file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (ImageWithCharacteristics) ois.readObject();
        }
    }
}
