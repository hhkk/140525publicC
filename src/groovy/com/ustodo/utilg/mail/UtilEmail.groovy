package com.ustodo.utilg.mail
import java.util.*;

import com.google.code.javax.mail.Authenticator;
import com.google.code.javax.mail.PasswordAuthentication;
import com.google.code.javax.mail.Message;
import com.google.code.javax.mail.MessagingException;
import com.google.code.javax.mail.Session;
import com.google.code.javax.mail.Transport;
import com.google.code.javax.mail.internet.InternetAddress;
import com.google.code.javax.mail.internet.MimeMessage;
import com.ustodo.utilg.O;

import com.ustodo.utilg.O;
import com.ustodo.utilg.UtilStr
import com.ustodo.utilg.Profiler;





public class UtilEmail
{
    public static void sendMail(String recipient, String subj, String body, String username) // UtilEmail
    {
        Properties props = new Properties();
        Vector<String> vStrRecips = new Vector<String>()
        vStrRecips.add recipient

        sendMail(vStrRecips, subj, body, username)
    }


        public static void sendMail(Vector<String> vStrRecips, String subj, String body, String username) // UtilEmail
    {
        if (username.equals("ckckck"))
            username = "hk"
        Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		//		int[] anArray = {			100, 200, 300,			400, 500, 600,700, 800, 900, 1000
		//		};
		//		int[] arraddrTos = {1};

		//		vStrRecips.add("maxwellkon@gmail.com")
		//		vStrRecips.add("zach.kon@gmail.com")
		//		vStrRecips.add("sean.merritt@gmail.com")
		//		//vStrRecips.add("bkon@optonline.net")
		//		//vStrRecips.add("rbcook1@optonline.net")
		//		vStrRecips.add("mhpaperboy@gmail.com")
		//		vStrRecips.add("mkon@bu.edu")
		//		//vStrRecips.add("dave.t.chen@gmail.com")
		//			,
		//			,
		//			,
		//			,
		//			,
		//			,
		//			,
		//			,


		;


			// Session session = Session.getDefaultInstance(props,
				Session session = Session.getInstance(props,
				// orinew javax.mail.Authenticator() { // ori
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("ustodo.com@gmail.com","himmmpy\$123");
					}
				});

		try {

            O.o("pre email subj [" + subj + "]")
            O.o("pre email body [" + body + "]")
            for (int i = 0; i < vStrRecips.size() ;i++ )
			{
				String recip = vStrRecips.elementAt(i);
				O.o("pre reply to:" + recip)
				//recip = UtilMailHelper.cvtSenderToJustRawEmailStr(recip)

				//O.o("begin email send to:" + recip);
				Message message = new MimeMessage(session);
				//message.setFrom(new InternetAddress("UsToDo!!", "ustodo.com@gmail.com"));
				message.setFrom(new InternetAddress("ustodo.com@gmail.com", "UsToDo / " + username));
				//message.setFrom(new InternetAddress("ustodo.com@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(recip));
				message.setSubject(subj);
				message.setText(body);

                Profiler.check("pre email send " + subj + "(from " + username + ")")
                Transport.send(message);
                Profiler.check("post email send " + subj + "(from " + username + ")")
                O.o("post done send to:" + recip);
			}

			O.o("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main (String[] args)
	{
		try
		{
			Vector<String> vStrRecips = new Vector<String>()
			vStrRecips.add("henry.kon@gmail.com")
			vStrRecips.add("ustodo.com@gmail.com")

			String subj = "confsubj444";
			String body = "confbody444";

			sendMail(vStrRecips, subj, body, "unit test sender")
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
