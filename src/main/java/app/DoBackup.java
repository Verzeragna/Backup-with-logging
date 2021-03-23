package app;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DoBackup implements IDoBackup{

    @Autowired
    private ReadSettings readSettings;

    @Autowired
    private Mail mail;

    private Settings settings;

    private File vPathDest;

    private static final Logger log = LogManager.getLogger(DoBackup.class);

    private final String SETTINGS = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "\\settings.properties";

    @Override
    public void readSettingsFromFile() {

        try {
            settings = readSettings.readSettingsFile(SETTINGS);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteOldBackup(){
        if (settings.getDelOldBackup().equals("1")) {
            log.info("Removing an old backup.");
            List<File> dirList = new ArrayList<>();
            File pathSource = new File(settings.getToDirectory());
            File[] arrayFiles = pathSource.listFiles();
            if (arrayFiles != null) {
                if (arrayFiles.length > Integer.parseInt(settings.getBackupCount())) {
                    for (File myFile : arrayFiles) {
                        if (myFile.isDirectory()) {
                            dirList.add(myFile);
                        }
                    }
                    Collections.sort(dirList);
                    try {
                        deleteOldBackupDirectory(dirList.get(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    log.info("Backup " + dirList.get(0).getName() + " deleted.");
                }
            } else {
                log.info("No backup files found!");
            }
        }else{
            log.info("Deleting old backups is disabled!");
        }
    }

    private void deleteOldBackupDirectory(File path) throws IOException {
        FileUtils.deleteDirectory(path);
    }

    @Override
    public void copyFiles(){
        log.info("Copying files.");
        File pathSource = new File(settings.getFromDirectory());
        File pathDest = new File(settings.getToDirectory());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        StringBuilder newPath = new StringBuilder();
        newPath.append(pathDest).append("\\");
        String fileName = simpleDateFormat.format(new Date());
        newPath.append(fileName);
        File newDir = new File(newPath.toString());
        boolean isCreated = newDir.mkdir();
        if (!isCreated) {
            log.error("Can not create directory: " + newPath.toString());
        }
        try {
            doCopyFiles(pathSource, newDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doCopyFiles(File pathSource, File pathDest) throws IOException{
        int i;
        File[] arrayFiles = pathSource.listFiles();
        if (arrayFiles != null) {
            for (i = 0; i < arrayFiles.length; i++) {
                File fileDest = new File(pathDest.getAbsolutePath() + "\\" + arrayFiles[i].getName());
                log.info(arrayFiles[i].getName() + " processing....");
                FileUtils.copyFile(arrayFiles[i], fileDest);
                log.info(arrayFiles[i].getName() + " copied!");
            }
            log.info("Copied " + pathDest.listFiles().length + " files from " + pathSource.listFiles().length + " files");
            vPathDest = pathDest;
        } else {
            log.error("No files found in " + pathDest.getName());
        }
    }

    @Override
    public void deleteFiles(){
        if (settings.getDelFromDirectory().equals("1")) {
            log.info("Removing files from the server!");
            File pathSource = new File(settings.getFromDirectory());
            File[] arrayFiles = pathSource.listFiles();
            if (arrayFiles != null) {
                for (File myFile : arrayFiles) {
                    if (myFile.isDirectory()) {
                        try {
                            FileUtils.deleteDirectory(myFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        boolean isDeleted = myFile.delete();
                        if (!isDeleted) {
                            log.error("File can not be deleted: " + myFile.getName());
                        }
                    }
                }
                log.info("Files from the server have been deleted!");
            } else {
                log.error("No files found in " + pathSource.getName());
            }
        }else{
            log.info("Deleting files from the server is disabled!");
        }
    }

    @Override
    public void sendInfoMail() {
        File pathSource = new File(settings.getFromDirectory());
        Properties prop = new Properties();
        String jarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String PATH_TO_PROPERTIES = new File(jarPath).getParent() + "\\mail.properties";
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
        mail.seteMails(prop.getProperty("mails").split(","));
        mail.setMail(prop.getProperty("mail"));
        mail.setSmtpHost(prop.getProperty("smtpHost"));
        mail.setPort(prop.getProperty("port"));
        mail.setPassword(prop.getProperty("password"));
        StringBuilder textMessage = new StringBuilder();
        if (vPathDest.listFiles().length == 0) {
            textMessage.append("Copy error (see server logs) !");
        } else {
            textMessage.append("Copied ").append(vPathDest.listFiles().length).append(" files from ").append(pathSource.listFiles().length).append(".");
        }
        try {
            new SendEmail().sendMessage(mail, textMessage.toString());
        }catch (MessagingException me){
            me.printStackTrace();
        }
    }

    private void finishScript(){
        log.info("****************Script finish****************");
    }

    public void setReadSettings(ReadSettings readSettings) {
        this.readSettings = readSettings;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
