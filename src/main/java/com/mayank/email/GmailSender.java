package com.mayank.email;

import java.util.Properties;
import java.util.Random;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

public class GmailSender {

	public static boolean sendGmail(String to, String subject, String text) {
		boolean flag = false;

		final String username = "kinnerapavankalyan7";
		final String password = "sfxfjtkzckbtobvx";

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.host", "smtp.gmail.com");

		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);

			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(username.concat("@gmail.com")));
			message.setText(text);
			message.setSubject(subject);

			Transport.send(message);

			flag = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	public static int generateRandomNumber() {
		Random random = new Random();
		int min = 100000;
		int max = 999999;
		return random.nextInt(max - min + 1) + min;
	}

}
