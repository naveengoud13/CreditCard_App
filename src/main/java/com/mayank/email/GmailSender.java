package com.mayank.email;

import java.security.SecureRandom;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

public class GmailSender {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    private static final String USERNAME =
            System.getenv("MAIL_USERNAME");

    private static final String PASSWORD =
            System.getenv("MAIL_PASSWORD");

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Sends an email using Gmail SMTP.
     *
     * @param to recipient email address
     * @param subject email subject
     * @param text email body
     * @return true if the email was sent successfully, otherwise false
     */
    public static boolean sendGmail(
            String to,
            String subject,
            String text
    ) {

        if (USERNAME == null || USERNAME.isBlank()
                || PASSWORD == null || PASSWORD.isBlank()) {

            System.err.println(
                    "Mail credentials are not configured."
            );

            return false;
        }

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(
                props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication
                    getPasswordAuthentication() {

                        return new PasswordAuthentication(
                                USERNAME,
                                PASSWORD
                        );
                    }
                }
        );

        try {
            Message message = new MimeMessage(session);

            message.setRecipient(
                    RecipientType.TO,
                    new InternetAddress(to)
            );

            message.setFrom(
                    new InternetAddress(USERNAME)
            );

            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            return true;

        } catch (MessagingException | IllegalArgumentException e) {

            System.err.println(
                    "Failed to send email: " + e.getMessage()
            );

            return false;
        }
    }

    /**
     * Generates a secure six-digit random number.
     *
     * @return six-digit verification number
     */
    public static int generateRandomNumber() {

        return 100000 + RANDOM.nextInt(900000);
    }
}
