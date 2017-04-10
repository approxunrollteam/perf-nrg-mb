package fr.inria.approxloop;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by elmarce on 29/03/17.
 */
public class EmailSender {

    public static void main(String[] args) {
        send("marcelino.rguez.cancio@gmail.com", "1qaz2wsx3edc*-", "Something happen", "Yeah is me!");
    }

    public static void send( final String username, final String password, String subject, String text) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("marcelino.rguez.cancio@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("marcelino.rguez.cancio@gmail.com"));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}