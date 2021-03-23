package app;

import org.springframework.stereotype.Component;

@Component
public class Settings {

    private String fromDirectory;
    private String toDirectory;
    private String delOldBackup;
    private String backupCount;
    private String delFromDirectory;

    public String getFromDirectory() {
        return fromDirectory;
    }

    public void setFromDirectory(String fromDirectory) {
        this.fromDirectory = fromDirectory;
    }

    public String getToDirectory() {
        return toDirectory;
    }

    public void setToDirectory(String toDirectory) {
        this.toDirectory = toDirectory;
    }

    public String getDelOldBackup() {
        return delOldBackup;
    }

    public void setDelOldBackup(String delOldBackup) {
        this.delOldBackup = delOldBackup;
    }

    public String getBackupCount() {
        return backupCount;
    }

    public void setBackupCount(String backupCount) {
        this.backupCount = backupCount;
    }

    public String getDelFromDirectory() {
        return delFromDirectory;
    }

    public void setDelFromDirectory(String delFromDirectory) {
        this.delFromDirectory = delFromDirectory;
    }
}
