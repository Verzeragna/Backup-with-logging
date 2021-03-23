package app;

import org.springframework.stereotype.Component;

@Component
public class Mail {

    private String[] eMails;
    private String mail;
    private String password;
    private String smtpHost;
    private String port;

    public String[] geteMails() {
        return eMails;
    }

    public void seteMails(String[] eMails) {
        this.eMails = eMails;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
