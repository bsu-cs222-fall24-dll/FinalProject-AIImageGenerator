package bsu.edu.cs222;

public class SaveStatusManager {
    private boolean fileIsSaved = false;
    private String filename = "untitled";
    private final ViewUtilities viewUtilities;

    public SaveStatusManager() {
        this.viewUtilities = new ViewUtilities();
    }

    public void invalidateSaveStatus() {
        fileIsSaved = false;
    }

    public void markAsSaved() {
        fileIsSaved = true;
    }

    public boolean checkIfSaved() {
        return fileIsSaved;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String newFilename) {
        this.filename = newFilename;
    }

    public void updateFilename(String newFilename) {
        setFilename(newFilename);
        invalidateSaveStatus();
    }

    public String getWindowTitle() {
        return fileIsSaved ? filename : filename + "*";
    }

    public String promptFilenameChange() {
        return viewUtilities.getFilenameDialogInput();
    }

    public boolean confirmUnsavedChanges() {
        return viewUtilities.getConfirmationAlertResult("Unsaved work", "Are you sure you want to exit?");
    }
}