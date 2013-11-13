package br.unifesp.profetas.business;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailProfetas {

	public void sendEmail(String to, String title, String body) throws MessagingException {
        Message message = new MimeMessage(getSession());

        message.addRecipient(RecipientType.TO, new InternetAddress(to));
        message.addFrom(new InternetAddress[] { new InternetAddress("EMAIL") });

        message.setSubject(title);
        message.setContent(body, "text/html; charset=ISO-8859-1");

        Transport.send(message);
    }
	
	private Session getSession() {
        Authenticator authenticator = new Authenticator();

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
        properties.setProperty("mail.smtp.auth", "true");

        properties.setProperty("mail.smtp.host", "SMTP...");
        properties.setProperty("mail.smtp.port", "25");

        return Session.getInstance(properties, authenticator);
    }

    private class Authenticator extends javax.mail.Authenticator {
        private PasswordAuthentication authentication;

        public Authenticator() {
            String username = "USER...";
            String password = "PASS...";
            authentication = new PasswordAuthentication(username, password);
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
}