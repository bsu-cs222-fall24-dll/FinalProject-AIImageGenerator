package bsu.edu.cs222;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ImageLoader {
    public ImageWithCharacteristics loadImageAndCharacteristics(String filePath) throws IOException, ClassNotFoundException {
        // Deserialize the ImageWithCharacteristics object from the file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (ImageWithCharacteristics) ois.readObject();
        }
    }

    public HashMap<String, ImageWithCharacteristics> loadImageItems(String saveDirectoryPath) throws IOException {
        FileFetcher fileFetcher = new FileFetcher(saveDirectoryPath);
        ArrayList<String> filePaths = fileFetcher.getAllFilePaths();

        HashMap<String, ImageWithCharacteristics> imageItems = new HashMap<>();
        for (String filePath : filePaths) {
            try {
                imageItems.put(filePath, loadImageAndCharacteristics(filePath));
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Failed to load file: " + filePath + " due to " + e.getMessage());
            }
        }

        return imageItems;
    }
}
