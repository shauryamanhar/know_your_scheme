package com.enggcell.utilities;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender implements Runnable {

    private String from;
    private String to;
    private String[] arrto;
    private String subject;
    private String text;
    private String displayName;

    public EmailSender(String from, String to, String subject, String text, String displayName) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.displayName = displayName;
    }

    public void run() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constant.EMAIL_USER, Constant.EMAIL_PASSWORD);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from, displayName));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
            message.setContent(text, "text/html");
            Transport.send(message);

        } catch (MessagingException e) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, e);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
