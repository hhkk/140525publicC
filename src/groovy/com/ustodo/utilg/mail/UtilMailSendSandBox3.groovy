package com.ustodo.utilg.mail
import java.util.*;


import com.google.code.javax.mail.Message;
import com.google.code.javax.mail.MessagingException;
import com.google.code.javax.mail.Session;
import com.google.code.javax.mail.Transport;
import com.google.code.javax.mail.internet.InternetAddress;
import com.google.code.javax.mail.internet.MimeMessage;
import com.ustodo.utilg.O;




public class UtilMailSendSandBox3
{
	public static void test()
	{
		String host = "smtp.gmail.com";
		int port = 587;
		String username = "ustodo.com@gmail.com";
		String password = "himmmpy\$123";
		//O.o("pass:" + password )

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ustodo.com@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("henry.kon@gmail.com"));
			message.setSubject("Welcome to the UsToDo network");
			message.setText("As a gold member, ... ");

			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, password);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}	}

	public static void main (String[] args)
	{
		try
		{
			test()
		}
		catch (Throwable t )
		{
			O.o("t", t);
			throw t;
			assert(false);
		} finally {
			O.o "done"
			assert (true)
		}


	} // main

//
//	**********************************Fri Feb 24 04:22:09 EST 2012
//	0. tO.or.1 [com.google.code.javax.mail.AuthenticationFailedException: failed to connect, no password specified?]
//	1. O.or.2:t 2001 [ [@@@@@@@@@@@@@@@@@@@@@@ java.lang.RuntimeException: com.google.code.javax.mail.AuthenticationFailedException: failed to connect, no password specified?
//		at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
//		at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:39)
//		at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:27)
//		at java.lang.reflect.Constructor.newInstance(Constructor.java:513)
//		at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:77)
//		at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:102)
//		at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallConstructor(CallSiteArray.java:54)
//		at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:182)
//		at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:190)
//		at com.ustodo.utilg.mail.UtilMailSendSandBox3.test(UtilMailSendSandBox3.groovy:49)
//		at com.ustodo.utilg.mail.UtilMailSendSandBox3.main(UtilMailSendSandBox3.groovy:54)
//		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
//		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
//		at java.lang.reflect.Method.invoke(Method.java:597)
//		at org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:90)
//		at groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:233)
//		at groovy.lang.MetaClassImpl.invokeStaticMethod(MetaClassImpl.java:1295)
//		at org.codehaus.groovy.runtime.InvokerHelper.invokeMethod(InvokerHelper.java:767)
//		at groovy.lang.GroovyShell.runScriptOrMainOrTestOrRunnable(GroovyShell.java:273)
//		at groovy.lang.GroovyShell.run(GroovyShell.java:229)
//		at groovy.lang.GroovyShell.run(GroovyShell.java:159)
//		at groovy.ui.GroovyMain.processOnce(GroovyMain.java:548)
//		at g @@@@@@@@@@@@@@@@@@@@@]] 2001
//



}
