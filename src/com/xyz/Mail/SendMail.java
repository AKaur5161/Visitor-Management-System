
package com.infodart.Mail;

import java.security.Security;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public static void mail(String fromEmail, final String UserName, final String pswd, String toEmail, String sub,
			String text) {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(true);

		Message mailMessage = new MimeMessage(session);
		try {
			/* mailMessage.setFrom(new InternetAddress(fromEmail)); */
			mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mailMessage.setContent(text, "text/html");
			mailMessage.setSubject(sub);

			Transport transport = session.getTransport("smtp");
		
			mailMessage.setFrom(new InternetAddress(fromEmail));
			transport.connect("smtp.gmail.com", UserName, pswd);
			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
			System.out.println("Email sent successfully.");

		} catch (AddressException e) {

			e.printStackTrace();
		} catch (MessagingException e) {

			e.printStackTrace();
		}

	}

}
