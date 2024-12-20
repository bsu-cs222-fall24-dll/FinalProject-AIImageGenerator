package bsu.edu.cs222;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();

    static {
        loadProperties(CONFIG_FILE);
    }

    public static void loadProperties(String fileName) {
        try (FileInputStream input = new FileInputStream(fileName)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error whilst trying to load properties file! Please check for the " +
                    fileName + " file.", e);
        }
    }

    public static void createDirectoryIfMissing(String saveDirectoryPath) throws IOException {
        File directory = new File(saveDirectoryPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new IOException("Failed to create directory: " + saveDirectoryPath);
            }
        }
    }

    public static String getSaveDirectory() throws IOException {
        String saveDirectoryPath = properties.getProperty("save.directory", "./out/saved-images");
        createDirectoryIfMissing(saveDirectoryPath);

        return saveDirectoryPath;
    }

    public static void resetProperties() {
        properties.clear();
    }
}
