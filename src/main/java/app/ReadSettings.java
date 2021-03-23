package app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class ReadSettings {

    public Settings readSettingsFile(String path) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(path));
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Settings settings = ctx.getBean("settings", Settings.class);
        settings.setFromDirectory(prop.getProperty("fromDirectory"));
        settings.setToDirectory(prop.getProperty("toDirectory"));
        settings.setDelOldBackup(prop.getProperty("delOldBackup"));
        settings.setBackupCount(prop.getProperty("backupCount"));
        settings.setDelFromDirectory(prop.getProperty("delFromDirectory"));
        ctx.close();
        return settings;
    }
}
