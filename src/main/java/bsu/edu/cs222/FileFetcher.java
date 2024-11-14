package bsu.edu.cs222;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileFetcher {
    private final Path directoryPath;

    public FileFetcher(String directoryPath) {
        this.directoryPath = Paths.get(directoryPath);
    }

    public ArrayList<String> getAllFilePaths() throws IOException {
        ArrayList<String> filePaths = new ArrayList<>();
        if (Files.isDirectory(directoryPath)) {
            try (Stream<Path> paths = Files.walk(directoryPath, 1)) {
                paths.filter(Files::isRegularFile)
                        .forEach(path -> filePaths.add(path.toString()));
            }
        } else {
            throw new IllegalArgumentException("Provided path is not a directory: " + directoryPath);
        }
        return filePaths;
    }
}
