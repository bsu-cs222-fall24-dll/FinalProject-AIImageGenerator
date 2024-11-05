package bsu.edu.cs222;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigTest {
    private File tempConfigFile;

    private void createTempConfigFile(boolean isInvalidFile) throws IOException {
        tempConfigFile = File.createTempFile("tempConfig", ".properties");

        try (FileWriter writer = new FileWriter(tempConfigFile)) {
            if (!isInvalidFile) writer.write("save.directory=test/directory");
            else writer.write("");
        }

        Config.loadProperties(tempConfigFile.getAbsolutePath());
    }

    @AfterEach
    public void tearDown() {
        // Delete the temporary properties file after each test, and clear loaded properties
        if (tempConfigFile != null && tempConfigFile.exists()) {
            if(!tempConfigFile.delete()) throw new AssertionError("Could not delete temp config file");
        }

        Config.resetProperties();
    }

    @AfterAll
    public static void cleanUp() {
        // Load default properties
        Config.loadProperties(Config.CONFIG_FILE);
    }

    @Test
    public void testGetSaveDirectory() throws IOException {
        createTempConfigFile(false);
        assertEquals("test/directory", Config.getSaveDirectory());
    }

    @Test
    public void testGetDefaultSaveDirectory() throws IOException {
        createTempConfigFile(true);
        assertEquals("./saved-images", Config.getSaveDirectory());
    }

    @Test
    public void testRuntimeExceptionForNonExistentProperties() {
        assertThrows(RuntimeException.class, () -> Config.loadProperties("./non_existent.properties"));
    }
}
