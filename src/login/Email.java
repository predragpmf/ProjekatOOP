package login;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;

public class Email {

    public static void posalji(String posiljaoc, String lozinka, String primaoc, String subjekat, String tekst) {

        String server = "smtp.gmail.com";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", server);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(posiljaoc, lozinka);
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(posiljaoc));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(primaoc));
            message.setSubject(subjekat);
            message.setText(tekst);
            System.out.println("Slanje...");
            Transport.send(message);
            System.out.println("Poruka uspjesno poslata....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}