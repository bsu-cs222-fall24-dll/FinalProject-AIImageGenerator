package bsu.edu.cs222;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileFetcherTest {

    @TempDir
    Path tempDir;
    FileFetcher fileFetcher;

    @BeforeEach
    void setUp() {
        fileFetcher = new FileFetcher(tempDir.toString());
    }

    @Test
    void testGetAllFilePathsWithFiles() throws IOException {
        Path file1 = Files.createFile(tempDir.resolve("file1.txt"));
        Path file2 = Files.createFile(tempDir.resolve("file2.txt"));
        List<String> filePaths = fileFetcher.getAllFilePaths();

        assertEquals(List.of(file2.toString(), file1.toString()), filePaths);
    }

    @Test
    void testGetAllFilePathsWithNoFiles() throws IOException {
        List<String> filePaths = fileFetcher.getAllFilePaths();
        assertTrue(filePaths.isEmpty());
    }

    @Test
    void testGetAllFilePathsWithNonDirectoryPath() throws IOException {
        Path file = tempDir.resolve("notADirectory.txt");
        Files.createFile(file);
        FileFetcher invalidFileFetcher = new FileFetcher(file.toString());

        assertThrows(IllegalArgumentException.class, invalidFileFetcher::getAllFilePaths);
    }
}