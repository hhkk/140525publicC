package com.ustodo.utilg.mail

import com.google.code.javax.mail.Address;
import com.google.code.javax.mail.Session;
import com.google.code.javax.mail.Transport;
import com.google.code.javax.mail.internet.MimeMessage;
import com.ustodo.utilg.O;

class UtilMailSend {
	public static void test ()
	{


		// http://www.oracle.com/technetwork/java/faq-135477.html#gmail

		//		Properties props = (Properties)System.getProperties().clone();
		//		props.put("mail.smtp.host", "smtp.gmail.com"); // was "whatever");
		// set as properties as needed
		//		Session session = Session.getInstance(props, null);



		String host = "smtp.gmail.com";
		String username = "ustodo.com@gmail.com";
		String password = "himmmpy\$123";
		Properties props = new Properties();
		props.put("mail.smtps.auth", "true");
		// ...
		Session session = Session.getInstance(props, null);
		MimeMessage msg = new MimeMessage(session);
		// msg.setFrom(new InternetAddress(fromAddress));
////		Address
////		RecipientType
//		msg.setRecipient(RecipientType, Address)
		msg.setSubject("hk subject send!!")
		msg.setContentMD5("hk content md5!!")
		
		// set the message content here
		Transport t;
		try {
			t = session.getTransport("smtps");
			t.connect(host, username, password);
			t.sendMessage(msg, msg.getAllRecipients());
		
		} finally {
			t.close();
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
