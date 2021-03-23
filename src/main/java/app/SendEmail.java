package app;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public void sendMessage(Mail mail, String textMessage) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", mail.getSmtpHost());
        properties.put("mail.smtp.auth", "false");
        properties.put("mail.smtp.port", mail.getPort());

        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mail.getMail(), mail.getPassword());
                    }
                });
        for (String email : mail.geteMails()) {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.getMail()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Server backup report MSK-CBU01SQL");
            message.setText(textMessage);
            Transport.send(message);
        }

    }
}
