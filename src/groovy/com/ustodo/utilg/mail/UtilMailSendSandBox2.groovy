package com.ustodo.utilg.mail
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import com.ustodo.utilg.O;




public class UtilMailSendSandBox2
{
	public static void test()
	{

		// from http://www.tutorialspoint.com/java/java_sending_email.htm

		// File Name SendEmail.java
		// Recipient's email ID needs to be mentioned.
		String to = "henry.kon@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "ustodo.com@gmail.com";
		String pass = "himmmpy\$123";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", "mail.google.com"); // from http://www.coderanch.com/t/274207/java/java/Sending-Email-Java-mail-API
		//properties.setProperty("smtp.gmail.com", host); // ori
		
		// not ori:
		properties.put("mail.smtps.auth", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties); // ori
		// from http://www.coderanch.com/t/274207/java/java/Sending-Email-Java-mail-API 
		//Session session = Session.getDefaultInstance(properties, new com.google.code.javax.mail. )
		

		try{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Now set the actual message
			message.setText("This is actual message");

			
			
			
			
			
			
////			// from http://www.coderanch.com/t/271336/java/java/NoSuchProviderException-smtp
//			Transport transport = session.getTransport("smtp");
////			//transport.connect(<SMTP_HOSTNAME>, <MAIL_USER>, <MAIL_PASSWORD>);
//			transport.connect("smtp.gmail.com", from, pass);
//			transport.send(message);
			
			
			
			
			
			
			
			
			
			
			
			
			// Send message
			Transport.send(message);
			O.o("Sent message successfully....");
		}catch (Exception mex) {
			mex.printStackTrace();
			throw mex;
		}
	}

	public static void main (String[] args)
	{
		try
		{
			test()
		}
		catch (Throwable t )
		{
			O.or("t", t);
			throw t;
			assert(false);
		} finally {
			O.o "done"
			assert (true)
		}


	} // main




}