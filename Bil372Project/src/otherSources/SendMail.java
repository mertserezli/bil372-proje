package otherSources;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


import models.UserBean;

public class SendMail {

	   public static boolean sendMail(UserBean user) {    
	      // Recipient's email ID needs to be mentioned.
	      String to = "c.ozen@etu.edu.tr";

	      // Sender's email ID needs to be mentioned
	      final String from = "cemsozens@hotmail.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getInstance(properties, new Authenticator() {                
              protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(from,"10numara");
              }
          });

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is actual message");

	         // Send message
	         Transport.send(message);
	         return true;
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	         return false;
	      }
	}
}
