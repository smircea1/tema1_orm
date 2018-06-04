package mdb;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GoogleMailSender {
	Properties props = new Properties();
	
	private String username = "tmiss20172019@gmail.com";
	private String localMailAddress = "tmiss20172019@gmail.com";
	private String password = "mateinfotmiss";

	public GoogleMailSender() { 
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587"); 
	}
	
	public void SendMessage(String destination, String stringMessage) {
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		
		try { 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(localMailAddress));  
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(destination));  
			message.setSubject("Wine App Subject");
			message.setText("Message received: " + stringMessage);

			Transport.send(message);

			System.out.println("Done sending mail."); 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
