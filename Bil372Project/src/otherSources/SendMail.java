package otherSources;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import models.UserBean;
public class SendMail {
	
	public static boolean sendMail(UserBean user){
		// Get system properties
	    Properties properties = System.getProperties();

	    // Setup mail server
	    properties.setProperty("mail.smtp.host", "localhost");

	    // Get the default Session object.
	    Session session = Session.getDefaultInstance(properties);
	    
	    try {
	    	MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("cemsozens@hotmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setSubject("Password Reminder.");
			message.setText(user.getUsername()+". Your password is "+user.getPassword());
			Transport.send(message);
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}

		return true;
	}

}
