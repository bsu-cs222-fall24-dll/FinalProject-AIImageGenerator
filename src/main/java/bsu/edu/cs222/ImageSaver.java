package bsu.edu.cs222;

import java.io.*;

public class ImageSaver {
    public void saveImageAndCharacteristics(InputStream imageStream, Characteristics characteristics, String fileName) throws IOException {
        if (!FileNameValidator.isValidFileName(fileName)) {
            throw new IllegalArgumentException("Invalid file name: " + fileName);
        }

        byte[] imageData = imageStream.readAllBytes();
        ImageWithCharacteristics imageWithChar = new ImageWithCharacteristics(imageData, characteristics);

        // Serialize ImageWithCharacteristics object to file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Config.getSaveDirectory() + "/" + fileName + ".data"))) {
            oos.writeObject(imageWithChar);
        }
    }
}

