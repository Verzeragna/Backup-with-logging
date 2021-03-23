package app;

public interface IDoBackup {
    void readSettingsFromFile();
    void deleteOldBackup();
    void copyFiles();
    void deleteFiles();
    void sendInfoMail();
}
