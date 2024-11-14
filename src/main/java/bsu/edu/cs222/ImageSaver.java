package bsu.edu.cs222;

import java.io.*;

public class ImageSaver {
    public void saveImageAndCharacteristics(String filePath, byte[] imageData, Characteristics characteristics, String filename) throws IOException {
        if (!FileNameValidator.isValidFileName(filename)) {
            throw new IllegalArgumentException("Invalid file name: " + filename);
        }

        ImageWithCharacteristics imageWithChar = new ImageWithCharacteristics(imageData, characteristics);

        // Serialize ImageWithCharacteristics object to file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath + "/" + filename + ".data"))) {
            oos.writeObject(imageWithChar);
        }
    }
}

