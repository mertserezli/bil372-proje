package otherSources;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


import models.UserBean;

public class SendMail {

	   public static boolean sendMail(UserBean user) {    
	      // Recipient's email ID needs to be mentioned.
		   	final String username = "cemsozens96@gmail.com";
			final String password = "55numara";

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
				message.setFrom(new InternetAddress("cemsozens96@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("cemsozens96@gmail.com"));
				message.setSubject("Testing Subject");
				message.setText("Dear Mail Crawler,"
					+ "\n\n No spam to my email, please!");

				Transport.send(message);

				return true;

			} catch (MessagingException e) {
				e.printStackTrace();
				return false;
			}
	}
}
