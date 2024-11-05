package bsu.edu.cs222;

import java.util.regex.Pattern;

public class FileNameValidator {
    // Regex pattern for valid file names: allows letters, digits, spaces, underscores, and hyphens
    private static final Pattern FILE_NAME_PATTERN = Pattern.compile("^[\\w\\s-]+$");

    private static final int MAX_LENGTH = 255;

    public static boolean isValidFileName(String fileName) {
        // Trim leading and trailing spaces
        fileName = fileName.trim();

        return !fileName.isEmpty() && fileName.length() <= MAX_LENGTH && FILE_NAME_PATTERN.matcher(fileName).matches();
    }
}
