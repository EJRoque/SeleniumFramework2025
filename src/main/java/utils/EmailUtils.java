package utils;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils {

	public static void sendTestReport(String reportPath) {
		final String senderEmail = "automationtesting261@gmail.com";
		final String appPassword = "wezkeghjndpdaxkn";
		final String receipientEmail = "roquejernest770@gmail.com";

		// SMTP server properties
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.port", "587");

		// Create session with Authentication
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, appPassword);
			}
		});
		session.setDebug(true);

		try {
			// Create message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipientEmail));
			message.setSubject("Test Email from QA Automation");
			// message.setText("Hello \n This is a test email from Java \n Regards, \nQA
			// Team");

			// Email body part
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("\"Hello \n\nThis is a test email from Java \n\nRegards, \nQA Team");

			// Attachment part
			MimeBodyPart attachmentPart = new MimeBodyPart();
			//String filePath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
			System.out.println("Attachment path is - " + reportPath);
			attachmentPart.attachFile(new File(reportPath));

			// Combine email body and attachment part
			MimeMultipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(textPart);
			multiPart.addBodyPart(attachmentPart);
			message.setContent(multiPart);

			// Send Email
			Transport.send(message);
			System.out.println("Email sent successfully ***");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
