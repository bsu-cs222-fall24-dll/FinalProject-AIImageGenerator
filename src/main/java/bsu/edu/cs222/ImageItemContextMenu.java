package bsu.edu.cs222;

import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.File;

public class ImageItemContextMenu {
    private static String filepath;
    private String filename;

    public ImageItemContextMenu(String filepath) {
        if (filepath == null || filepath.isEmpty()) {
            throw new IllegalArgumentException("Filepath cannot be null or empty");
        }
        ImageItemContextMenu.filepath = filepath;
        File file = new File(filepath);
        this.filename = file.getName();
    }

    public String getFilename() {
        return filename;
    }

    public boolean renameImageFile(String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("New name cannot be null or empty");
        }

        File oldFile = new File(filepath);
        File newFile = new File(oldFile.getParent(), newName);
        if (oldFile.renameTo(newFile)) {
            filepath = newFile.getAbsolutePath();
            this.filename = newFile.getName();
            return true;
        }
        return false;
    }

    public boolean deleteImageFile() {
        File file = new File(filepath);
        return file.delete();
    }

    public void copyImageFile(Image image) {
        if (image != null) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putImage(image);
            clipboard.setContent(content);
        }
    }
}
