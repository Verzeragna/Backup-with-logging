package app.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class PointsCut {

    @Pointcut("execution(* readSettingsFromFile())")
    public void readSettingsFromFile(){}

    @Pointcut("execution(* deleteOldBackup())")
    public void deleteOldBackup(){}

    @Pointcut("execution(* copyFiles())")
    public void copyFiles(){}

    @Pointcut("execution(* sendInfoMail())")
    public void sendInfoMail(){}

    @Pointcut("execution(* deleteFiles())")
    public void deleteFiles(){}

}
