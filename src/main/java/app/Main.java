package app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        IDoBackup doBackup = ctx.getBean("doBackup",IDoBackup.class);
        doBackup.readSettingsFromFile();
        doBackup.deleteOldBackup();
        doBackup.copyFiles();
        doBackup.deleteFiles();
        ctx.close();
    }
}
