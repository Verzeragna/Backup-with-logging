package app.aspects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = LogManager.getLogger(LoggingAspect.class);

    @Before("app.aspects.PointsCut.readSettingsFromFile()")
    public void beforeReadSettingsAdvice() {
        log.info("****************Script start****************");
        log.info("Start read settings...........");
    }

    @AfterReturning("app.aspects.PointsCut.readSettingsFromFile()")
    public void afterReturningReadSettingsAdvice(){
        log.info("Settings read successfully!");
    }

    @AfterThrowing(pointcut = "app.aspects.PointsCut.readSettingsFromFile()", throwing = "exception")
    public void afterThrowingReadSettingsAdvice(Throwable exception){
        log.error("Settings read error: " + exception.getMessage());
    }

    @After("app.aspects.PointsCut.readSettingsFromFile()")
    public void afterReadSettingsAdvice() {
        log.info("Finish read settings...........");
    }

    @Before("app.aspects.PointsCut.deleteOldBackup()")
    public void beforeDeleteOldBackupAdvice() {
        log.info("Start delete old backup...........");
    }

    @AfterReturning("app.aspects.PointsCut.deleteOldBackup()")
    public void afterReturningDeleteOldBackupAdvice(){
        log.info("Removing old backups was successful!");
    }

    @AfterThrowing(pointcut = "app.aspects.PointsCut.deleteOldBackup()", throwing = "exception")
    public void afterThrowingDeleteOldBackupAdvice(Throwable exception){
        log.error("Old backup directory can not be deleted: " + exception.getMessage());
    }

    @After("app.aspects.PointsCut.deleteOldBackup()")
    public void afterDeleteOldBackupAdvice() {
        log.info("Finish delete old backup...........");
    }

    @Before("app.aspects.PointsCut.copyFiles()")
    public void beforeCopyFilesAdvice() {
        log.info("Start copy files...........");
    }

    @AfterReturning("app.aspects.PointsCut.copyFiles()")
    public void afterReturningCopyFilesAdvice() {
        log.info("File copy completed successfully!");
    }

    @AfterThrowing(pointcut = "app.aspects.PointsCut.copyFiles()", throwing = "exception")
    public void afterThrowingCopyFilesAdvice(Throwable exception) {
        log.error("File copy error: " + exception.getMessage());
    }

    @After("app.aspects.PointsCut.copyFiles()")
    public void afterCopyFilesAdvice() {
        log.info("Finish copy files...........");
    }

    @Before("app.aspects.PointsCut.sendInfoMail()")
    public void beforeSendInfoMailAdvice() {
        log.info("Sending letter.......");
    }

    @AfterReturning("app.aspects.PointsCut.sendInfoMail()")
    public void afterReturningSendInfoMailAdvice() {
        log.info("Sending the letter was successful!");
    }

    @AfterThrowing(pointcut = "app.aspects.PointsCut.sendInfoMail()", throwing = "exception")
    public void afterThrowingSendInfoMailAdvice(Throwable exception) {
        log.error("Email sending error : " + exception.getMessage());
    }

    @Before("app.aspects.PointsCut.deleteFiles()")
    public void beforeDeleteFilesAdvice() {
        log.info("Start removing files from the server...........");
    }

    @AfterReturning("app.aspects.PointsCut.deleteFiles()")
    public void afterReturningDeleteFilesAdvice() {
        log.info("Deleting files from the server was successful!");
    }

    @AfterThrowing(pointcut = "app.aspects.PointsCut.deleteFiles()", throwing = "exception")
    public void afterThrowingDeleteFilesAdvice(Throwable exception) {
        log.error("Directory can not be deleted: " + exception.getMessage());
    }

    @After("app.aspects.PointsCut.deleteFiles()")
    public void afterDeleteFilesAdvice() {
        log.info("Finish removing files from the server...........");
    }

}
