package com.ustodo.utilg.mail

class UtilMailUpdate {








	public static void test1(message)
	{
		
		
		//folder.open(Folder.READ_WRITE);
		//message.setFlag(Flags.Flag.DELETED, true);
		//folder.close(true); // true says to expunge
		
		
		//see http://java.sun.com/developer/onlineTraining/JavaMail/contents.html#JavaMailDeleting
		//Deleting Messages and Flags
		//Deleting messages involves working with the Flags associated with the messages. There are different flags for different states, some system-defined and some user-defined. The predefined flags are defined in the inner class Flags.Flag and are listed below:
		//
		//Flags.Flag.ANSWERED
		//Flags.Flag.DELETED
		//Flags.Flag.DRAFT
		//Flags.Flag.FLAGGED
		//Flags.Flag.RECENT
		//Flags.Flag.SEEN
		//Flags.Flag.USER
		//Just because a flag exists doesn't mean the flag is supported by all mail servers/providers. For instance, besides deleting messages, the POP protocol supports none of them. Checking for new mail is not a POP task but one built into mail clients. To find out what flags are supported, ask the folder with getPermanentFlags().
		//
		//To delete messages, you set the message's DELETED flag:
		//
		//message.setFlag(Flags.Flag.DELETED, true);
		//Open up the folder in READ_WRITE mode first though:
		//
		//folder.open(Folder.READ_WRITE);
		//Then, when you are done processing all messages, close the folder, passing in a true value to expunge the deleted messages.
		//
		//folder.close(true);
		//There is an expunge() method of Folder that can be used to delete the messages. However, it doesn't work for Sun's POP3 provider. Other providers may or may not implement the capabilities. It will more than likely be implemented for IMAP providers. Because POP only supports single access to the mailbox, you have to close the folder to delete the messages with Sun's provider.
		//
		//To unset a flag, just pass false to the setFlag() method. To see if a flag is set, check with isSet().	}
	}
	public static void test2()
	{
		//https://mail.google.com/support/bin/answer.py?answer=77657
		//	Action on mobile device/client (e.g. iPhone/Outlook)	Result in Gmail on the web
		//	Open a message	Mark a message as read
		//	Flag a message	Apply a star to the message
		//	Move a message to a folder	Apply a label to the message
		//	Move a message to a folder within a folder*	Apply a label showing folder hierarchy ('MainFolder/SubFolder')*
		//	Create a folder	Create a label
		//	Move a message to [Gmail]/Spam	Report a message as spam
		//	Move a message to [Gmail]/Trash	Move a message to Trash
		//	Send a message	Store message in Sent Mail
		//	Delete a message in inbox**	Remove the message from inbox**
		//	Delete a message from a folder**	Remove that label from the message**
		//	Delete a message from [Gmail]/Spam or [Gmail]/Trash	Delete the message permanently
	}




}
