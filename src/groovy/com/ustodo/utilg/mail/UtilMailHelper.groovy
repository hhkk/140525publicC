package com.ustodo.utilg.mail

import java.util.Set;
import java.util.Vector
import com.google.code.com.sun.mail.imap.IMAPFolder;
import com.google.code.com.sun.mail.imap.IMAPInputStream;
import com.google.code.com.sun.mail.imap.IMAPMessage;
import com.google.code.com.sun.mail.imap.IMAPSSLStore
import com.google.code.javax.mail.FetchProfile;
import com.google.code.javax.mail.Flags;
import com.google.code.javax.mail.Folder
import com.google.code.javax.mail.Header;
import com.google.code.javax.mail.Message;
import com.google.code.javax.mail.internet.MimeMessage;
import com.google.code.javax.mail.internet.MimeMessage.RecipientType
import com.google.code.javax.mail.search.BodyTerm
import com.google.code.javax.mail.search.ComparisonTerm
import com.google.code.javax.mail.search.MessageNumberTerm;
import com.google.code.javax.mail.search.OrTerm
import com.google.code.javax.mail.search.ReceivedDateTerm
import com.google.code.javax.mail.search.SearchTerm
import com.google.code.javax.mail.search.SubjectTerm
import com.mongodb.BasicDBObject;
import com.ustodo.utilg.Cfg;
import com.ustodo.utilg.FileLine
import com.ustodo.utilg.O;
import com.ustodo.utilg.UtilDate;
import com.ustodo.utilg.UtilFile
import com.ustodo.utilg.UtilMongo;
import com.ustodo.utilg.UtilStr

class UtilMailHelper {

	public static 	com.google.code.com.sun.mail.imap.IMAPSSLStore getStore()
	{
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.imap.socketFactory.fallback", "false");
		com.google.code.javax.mail.Session session = com.google.code.javax.mail.Session.getDefaultInstance(props, null); // [com.google.code.javax.mail.Session:
		// obs 110924 com.google.code.javax.mail.Store store = session.getStore("imaps");
		com.google.code.com.sun.mail.imap.IMAPSSLStore store = session.getStore("imaps");  // [com.google.code.com.sun.mail.imap.IMAPSSLStore]
		//com.google.code.javax.mail.Store store = session.getStore();
		//O.oc "session", session // oc:varname  <<com.google.code.javax.mail.Session>>
	}

	static def pass = null;
	public static String getPassword ()
	{
		def s = UtilFile.fileAsList("/Users/hkon/configprivate/tpass.txt")[0]
		if (pass != null)
			pass
		else
			pass = "hi"+ (s[0..0]+s[0..0]+s[0..0]) + "py\$" + s[1..3]

	}

	//	public static Vector<SearchTerm> buildQueryVector_keysAndAge(String searchstr, int ageMax, Integer ageunit )
	//	{
	//		Vector v1 = buildQueryVector_keysAndAge(searchstr, ageMax, ageunit, true )
	//		//Vector v2 = buildQueryVector_keysAndAge(searchstr, ageMax, ageunit, false )
	//
	//	}
	//

	public static Vector<SearchTerm> buildQueryVector_keysAndAge(String searchstrSubjOrBody_wholeWord_caseInsens, int ageMax, Integer ageunit, String substringSrchFromOrNullIfAll, boolean unseenOnly)
	{
		O.o "EMAILSRCH: SUMMARY: BY KEY AND DATE: " + searchstrSubjOrBody_wholeWord_caseInsens + " within " +  ageMax + " " + UtilMailHelper.decodeAgeUnits(ageunit)

		// see also http://javamail.kenai.com/nonav/javadocs/javax/mail/search/SearchTerm.html

		def vstAndDateRest = new Vector<SearchTerm>();
		def vstOrSubjBodyFrom = new Vector<SearchTerm>();


		// 	SUBJ *******************************
		//vConstraintsAdd("orSubj", vstOrSubjBodyFrom, new SubjectTerm(searchstr)); // -----------------------------------------1

		// 	BODY *******************************
		//vConstraintsAdd "orbody", vstOrSubjBodyFrom, new BodyTerm(searchstr); // -----------------------------------------1

		// 	FROM *******************************
		//vConstraintsAdd "orfrom", vstOrSubjBodyFrom, new FromStringTerm(searchstr);// -----------------------------------------3

		// 	RCVDATE *******************************
		Calendar cal = new GregorianCalendar(); // now
		//cal.add(Calendar.DATE, -600);
		//cal.add(Calendar.DATE, -(10*365));
		//cal.add(Calendar.DATE, ageMaxDays);
		//O.ofmt("agemaxyears", ageMaxYears)
		//cal.add(Calendar.MONTH, -ageMaxMonths);
		cal.add(ageunit, -ageMax);
		//cal.add(Calendar.YEAR, -ageMaxYears);
		//calendar.add(Calendar.DAY_OF_MONTH,-1);
		//cal.add(Calendar.HOUR,-24);
		//cal.add(Calendar.MINUTE,-120);
		//cal.add(Calendar.SECOND,-ageMaxSeconds);
		O.o("searching email for [" + searchstrSubjOrBody_wholeWord_caseInsens +  "]")

		// 1 EMAILSRCH: FLAGS - NOT SEEN
		if (unseenOnly)
		{
			Flags f = new Flags();
			f.add(Flags.Flag.SEEN);
			vConstraintsAdd "EMAILSRCH: FLAGTERM UNSEEN ", vstAndDateRest,
					new com.google.code.javax.mail.search.NotTerm( new com.google.code.javax.mail.search.FlagTerm(f, true));
		}

		// 2 EMAILSRCH: RECEIVED DATE
		vConstraintsAdd "EMAILSRCH: ReceivedDateTerm GE " + cal.getTime().toString(), vstAndDateRest, new ReceivedDateTerm(ComparisonTerm.GE, cal.getTime());// -----------------------------------------4

		// 3 EMAILSRCH: OR (SUBJECT, BODY)
		if (searchstrSubjOrBody_wholeWord_caseInsens != null)
			vConstraintsAdd "EMAILSRCH: BodyTerm match $searchstrSubjOrBody_wholeWord_caseInsens", vstAndDateRest, new OrTerm(
					new SubjectTerm(searchstrSubjOrBody_wholeWord_caseInsens), new BodyTerm(searchstrSubjOrBody_wholeWord_caseInsens))

		return vstAndDateRest;


	}
	//					String usersrch = UtilCons.gets("enter sesarch subject ... eventually to: fr: su: or regular:");
	//com.google.code.javax.mail.search.SubjectTerm subjectTerm = new com.google.code.javax.mail.search.SubjectTerm("testyy")
	//O.oc "subjectTerm", subjectTerm
	//javax.mail.search.AndTerm andterm = new

	public static String decodeAgeUnits(int ageUnits)
	{
		switch (ageUnits) {
			case 1: return "YEAR";
			case 2: return "MONTH";
			case 5: return "DATE";
			case 10: return "HOUR";
			default:
				throw new RuntimeException("not found ageunits:" + ageUnits);
		}
	}




	public static Vector<SearchTerm> buildQueryVector_messageNumber(int minMessageNumber )
	{
		// see also http://javamail.kenai.com/nonav/javadocs/javax/mail/search/SearchTerm.html

		def vstAndDateRest = new Vector<SearchTerm>();
		vConstraintsAdd ("to and add orterm", vstAndDateRest, new MessageNumberTerm(ComparisonTerm.GE, minMessageNumber));

		return vstAndDateRest;


	}


	// list of terms: SentDateTerm, MessageNumberTerm



	public static String[] getAllFolderNamesTest (com.google.code.com.sun.mail.imap.IMAPSSLStore store , def emit) {
		// list of all folders
		String[] foldernames = UtilMailHelper.getAllFoldersUnderRootNamespace(store, true);
		def folder = UtilMailHelper.getFolder(store, "atemp", Folder.READ_ONLY); // 				def smailbox = "aUsToDo"; //smailbox = "atemp";

		// O.oc ("folder", folder) // // <<com.google.code.com.sun.mail.imap.IMAPFolder
		folder.each {
			println "asdasd" + it;
		}
	}


	public static String[] getAllFoldersUnderRootNamespace(com.google.code.com.sun.mail.imap.IMAPSSLStore store , def emit) {

		def s = []
		// DEFAULT FOLDER

		store.defaultFolder.list().eachWithIndex { deffolder, i ->
			if (i == 0 && emit) {
				O.os ("\r\nfolderlistitem as object tostring", deffolder)
				//UtilCons.gets("observe folder class name, hit a key")
			}
			if (emit) {
				try {
					O.o "folderlistitem deffolder.name [" + deffolder.name +
							"] getNewMessageCount ["  + deffolder.getNewMessageCount() +
							"] getUnreadMessageCount:" + deffolder.getUnreadMessageCount() +
							"]";
					//O.oc "deffolder", deffolder // com.google.code.com.sun.mail.imap.IMAPFolder
				} catch ( Exception e) {
					O.os "\r\nfolderlistitem deffolder.getNewMessageCount()","unable to retrieve";

				}
			}
			s << deffolder.name
		}
		return s

	}
	public static Folder getFolder(com.google.code.com.sun.mail.imap.IMAPSSLStore store, String mailbox, int folderOpenMode)
	{
		// eg folderOpenMode = Folder.READ_ONLY

		//mailbox = "INBOX";
		com.google.code.javax.mail.Folder folder = store.getFolder(mailbox)
		//def folder = store.getFolder("[Gmail]/All Mail")
		folder.open(folderOpenMode)
		return folder

	}
	public static String[] testFolderFindAll(		com.google.code.javax.mail.Folder folder, String mailbox, boolean emit)
	{

		//O.oc "folder", folder
		int i = 0;
		def x = folder.findAll
		{ it ->
			//assert f.contains("test")
			if (i == 0)
				O.oc "itx", it
			i++;
			//true
			//it.contains("test")
		}
		O.o ("x.size()", x.size())
		O.o ("x", x)
	}


	public static void vConstraintsAdd(String desc, Vector v, Object o)
	{
		v.add o
		O.o("EMAILSRCH ADD [" + desc + "] " + o.getClass().getName())
		//qry = qry + ", desc [" + desc + "] " + o.getClass().getName();

	}





	public static void emitMsg(String desc, com.google.code.com.sun.mail.imap.IMAPMessage imapMsg, boolean details) {
		// oc:varname [*****************msg] o.getClass().getName() [com.sun.mail.imap.IMAPMessage] o.getClass().toString() [class com.sun.mail.imap.IMAPMessage]
		//O.os ("\r\nmsg.sender", imapMsg.sender);
		if (details)
			O.o("EMITMSGDETAIL:" + UtilMailHelper.cvtImapMsgToStrAllVars(imapMsg, true));
		else
			O.o ("EMITMSGNODETAIL: rcv date [" + imapMsg.getReceivedDate().toString() + "] sender [" + imapMsg.getSender() + "] to [" + imapMsg.getRecipients(RecipientType.TO) + "] subj [" + imapMsg.subject) ; //
		// + "; " + imapMsg.getMessageID());
		//O.os ("  NEXT MSG: NEXT MSG: A SENDER:" + imapMsg.getSender()); //  + ", " + imapMsg.from + ", " + imapMsg.from.size() + ", " + imapMsg.from[0].getPersonal() + ", " + imapMsg.from[0].getAddress());
		//O.os ("\r\n: B RECIPIENTS:" + imapMsg.getRecipients(RecipientType.TO)); //  + ", " + imapMsg.from + ", " + imapMsg.from.size() + ", " + imapMsg.from[0].getPersonal() + ", " + imapMsg.from[0].getAddress());
		// http://javamail.kenai.com/nonav/javadocs/javax/mail/internet/InternetAddress.html
		// http://javamail.kenai.com/nonav/javadocs/javax/mail/internet/InternetAddress.html
		//O.os (" C RECDATE <<<<<<" + imapMsg.getReceivedDate() + ">>>>>, Lns:"+imapMsg.getLineCount().toString() + ", " +  imapMsg.subject);// + ", " + imapMsg.getMessageID() );


		//O.oc ("\r\nD CONTENTSTREAM()" ,imapMsg.getContentStream()); // com.sun.mail.imap.IMAPInputStream
		//O.os ("\r\nD CONTENTSTREAM()" ,imapMsg.getContentStream().toString()); // com.sun.mail.imap.IMAPInputStream
		//O.os ("body", UtilMail.cvtContentStreamToString(imapMsg.getContentStream()));




		//O.oc ("\r\nE imapMsg.getContentMD5()" ,imapMsg.getContentMD5());

		//msg.setFlag(Flags.Flag.SEEN, true)

		/**
		 1msg: 1 of 153.
		 2msg.getReceivedDate()[Thu Mar 24 02:10:41 EST 2005]
		 3from["Henry B. Kon, PhD" <hkon@alum.mit.edu>]
		 4imapMsg.from[[Ljavax.mail.internet.InternetAddress;@5738ec91]oc:varname [4.5imapMsg.from] o.toString() [[Ljavax.mail.internet.InternetAddress;@5a335053] o.getClass().getName() [[Ljavax.mail.internet.InternetAddress;] o.getClass().toString() [class [Ljavax.mail.internet.InternetAddress;]
		 5imapMsg.from.size()[1]
		 6from[0].getPersonal()[Henry B. Kon, PhD]
		 6.5from[0].getAddress()[hkon@alum.mit.edu]
		 7msg.subject[Fw: eFax from "6179267817" - 7 page(s)]
		 8msg.getMessageID()Å[<013401c53040$938f9d30$6601a8c0@hbk4150>]
		 9msg.getLineCount()[-1]
		 10msg.getReceivedDate()[Thu Mar 24 02:10:41 EST 2005]
		 *
		 */
		//UtilCons.gets("hit enter for next msg")



	}


	public static class EmailSrchResult {

		int numFound;
		int numEmitted;
		int numToDB;
		long numMsInLoop;
		java.util.Date dateSearchStarted;

		public String toString() {
			return	"[" +
			"numFound:" + numFound +
			", dateSearchStarted:" + dateSearchStarted +
			", numMsInLoop:" + numMsInLoop +
			", numEmitted:" + numEmitted +
			", numToDB:" + numToDB +
			"]"
		}



	} // end class EmailSrchResult

	static int callcnt202=0
	// see also util file_email_db for routine from imap
	public static String cvtImapMsgToStrAllVars(IMAPMessage im , boolean emitcontent)
	{

		// test class MimeMessage yi = null  http://code.google.com/p/java-gmail-imap/source/browse/trunk/src/main/java/com/google/code/javax/mail/internet/MimeMessage.java?r=23
		// Message yi = null  http://code.google.com/p/java-gmail-imap/source/browse/trunk/src/main/java/com/google/code/javax/mail/Message.java?r=23

		def sb = new StringBuffer();
		//sb.append ((callcnt202++).toString());
		sb.append ("\r\n1xxx getGoogleMessageId:" + im.getGoogleMessageId());
		sb.append ("\r\n2xxx, getFolder:" + im.getFolder());
		sb.append ("\r\n3xxx, getReceivedDate:" + im.getReceivedDate());
		sb.append ("\r\n4xxx, getSubject:" + im.getSubject());
		sb.append ("\r\n5xxx, getContentMD5:" + im.getContentMD5());
		sb.append ("\r\n6xxx, getFlags:" + im.getFlags());
		sb.append ("\r\n7xxx, getContentID:" + im.getContentID());
		sb.append ("\r\n8xxx, getMessageID:" + im.getMessageID());
		sb.append ("\r\n9xxx, getSequenceNumber:" + im.getSequenceNumber());
		sb.append ("\r\n10xxx, getUID:" + im.getUID());
		sb.append ("\r\n11xxx, getProperties:" + im.getProperties());
		sb.append ("\r\n12xxx, getMessageNumber:" + im.getMessageNumber());
		sb.append ("\r\n13xxx, getGoogleMessageId:" + im.getGoogleMessageId());
		sb.append ("\r\n14xxx, getGoogleMessageThreadId:" + im.getGoogleMessageThreadId());
		sb.append ("\r\n15xxx, getContentID:" + im.getContentID());
		sb.append ("\r\n16xxx, getGoogleMessageLabels:" + im.getGoogleMessageLabels());
		sb.append ("\r\n17xxx, getSize:" + im.getSize());
		if (emitcontent)
			sb.append ("\r\n18xxx, content <<" + cvtImapMsgToItsBodyContent(im) + ">>");





		sb.append (im.getGoogleMessageId());
		// Hex version - useful for linking to Gmail
		sb.append ("\r\nxxx19, im.getGoogleMessageId():" + im.getGoogleMessageId());
		sb.append ("\r\nxxx20, im.getGoogleMessageThreadId():" + im.getGoogleMessageThreadId());
		String[] labels = im.getGoogleMessageLabels();
		if(labels!=null){
			for(String label: labels){
				sb.append ("\r\n21xxx,    im.getGoogleMessageLabels() each Label: " + label);
			}
		}


		sb.append ("\r\nEND\r\n\r\n");

		return sb.toString();
	}


	public static String cvtImapMsgToStrShort(IMAPMessage msg , boolean emitcontent)
	{
		def sb = new StringBuffer();
		//sb.append ((callcnt202++).toString());
		//sb.append (" msgId:" + msg.getGoogleMessageId());
		//sb.append (" folder:" + msg.getFolder());
		if (msg == null )
		{
			O.o "null msg why? "
			return "null msg"
		}
		sb.append (msg.getSender().toString() + ": " );
		//sb.append (msg.getReceivedDate().toString() + ": " );
		//sb.append ();

		//O.oc "msg:", msg // com.google.code.com.sun.mail.imap.IMAPMessage>
		//O.oc "msg.getSubject():", msg.getSubject() // ocdesc <msg.getSubject():> clsnm <java.lang.String> non AbstractCollection toStr <Fwd: Welcome to UsToDo>
		String subjOrBodyIfNull = msg.getSubject();;
		if (msg.getSubject() == null)
			subjOrBodyIfNull = cvtImapMsgToItsBodyContent(msg);

		subjOrBodyIfNull = subjOrBodyIfNull.trim()
		//O.o "########## subjOrBodyIfNull:"+ subjOrBodyIfNull
		sb.append(" <a href=\"https://mail.google.com/mail/#search/subject%3A%22" +
				subjOrBodyIfNull.replaceAll(" ", "+") + "+%22\" target=\"_blank\">" +
				subjOrBodyIfNull + "</a>");


		//sb.append ("&nbsp;&nbsp;" + UtilDate.getDateLikeFileFormatFromUtilDateNoTime(msg.getReceivedDate()));
		//sb.append ("\r\n, getContentMD5:" + msg.getContentMD5());
		if (msg.getGoogleMessageLabels() != null)
			sb.append (" labels:" + msg.getGoogleMessageLabels());
		if (emitcontent)
			sb.append ("\r\n, content <<" + cvtImapMsgToItsBodyContent(msg) + ">>");

		return sb.toString();
	}




	//	public static String cvtImapMsgToItsBodyContent(IMAPMessage imapMsg)
	//	{
	//		// hhh
	//
	//		IMAPInputStream iMAPInputStream = imapMsg.getContentStream()
	//		StringBuffer sb = new StringBuffer();
	//		byte[] bytearrbuf =new byte[1024];
	//		int len;
	//		while((len=iMAPInputStream.read(bytearrbuf))>0)
	//			sb.append(new String(bytearrbuf));
	//		String sx = sb.toString()
	//
	//		int indexOfContentTypeTextPlain =	 sx.indexOf("Content-Type: text/plain")
	//
	//		O.oc ("\r\nE <<<<<<<<<<<<<HKBODY " ,sx[0..Math.min(300, sx.length()-1)] + "/HKBODY>>>>>>>>>>>>>>"); // com.sun.mail.imap.IMAPInputStream
	//
	//		if (indexOfContentTypeTextPlain < 0)
	//		{
	//			O.o("WARNING - no content type plain from sender:" + imapMsg.getSender().toString())
	//		} else {
	//			O.o("COOL - yes, content type plain from sender:" + imapMsg.getSender().toString())
	//		}
	//		return sx;
	//	}

	public static final String CONTENT_TYPE_PLAIN_TEXT = "Content-Type: text/plain";
	public static String cvtImapMsgToItsBodyContent(IMAPMessage imapMsg, int bodyCharsMax)
	{
		// hhh
		String sBodyTextToRtn = null;
		IMAPInputStream iMAPInputStream = imapMsg.getContentStream()
		StringBuffer sb = new StringBuffer();
		byte[] bytearrbuf =new byte[1024];
		int len;
		while((len=iMAPInputStream.read(bytearrbuf))>0)
		{
			sb.append(new String(bytearrbuf));
			if (bodyCharsMax > 0 && sb.toString().size() > bodyCharsMax)
			{
				O.o("capped message at chars :" + bodyCharsMax)
				break;
			}
		}
		String sBody = sb.toString();
		if (bodyCharsMax > 0 && sb.toString().size() > bodyCharsMax)
		{
			int prelen = sBody.size();
			sBody = UtilStr.keepUpToZeroBased(sBody, bodyCharsMax)
			O.o "whacked email body to [" + sBody.size() + "] from supposedly already capped [" + prelen + "]"
		}


		int indexOfContentTypeTextPlain = sBody.indexOf(CONTENT_TYPE_PLAIN_TEXT)

		//O.oc ("\r\nE <<<<<<<<<<<<<HKBODY " ,sBody[0..Math.min(300, sBody.length()-1)] + "/HKBODY>>>>>>>>>>>>>>"); // com.sun.mail.imap.IMAPInputStream

		if (indexOfContentTypeTextPlain < 0)
		{
			sBodyTextToRtn = sBody.trim();
			//O.o("WARNING - no content type plain from sender:" + imapMsg.getSender().toString())
			//O.ofmt("1 sBodyTextToRtn", sBodyTextToRtn)
		} else {
			sBodyTextToRtn = 	UtilStr.keepAllAfterLastOfThis(sBody, CONTENT_TYPE_PLAIN_TEXT)
			sBodyTextToRtn = 	UtilStr.keepAllAfterNextOfThis(sBodyTextToRtn, "\n")
			//O.ofmt "2 sBodypostContentTypePlain", sBody_postContentTypePlain
			sBodyTextToRtn = 	UtilStr.keepAllBeforeFirstOfThis(sBodyTextToRtn, "\r\n--")
			//O.ofmt "3 sBodypostContentTypePlain", sBody_postContentTypePlain
			sBodyTextToRtn = 	sBodyTextToRtn.trim()
			//O.ofmt "4 sBodypostContentTypePlain", sBody_postContentTypePlain

			//sx.substring(indexOfContentTypeTextPlain+(CONTENT_TYPE_PLAIN_TEXT.length()));
			//String sBody_postContentTypePlain = 	sx.substring(indexOfContentTypeTextPlain+(CONTENT_TYPE_PLAIN_TEXT.length()));
			//int i =
			//O.o("1:" + sBody_postContentTypePlain.indexOf("\r"))
			//O.o("2:" + sBody_postContentTypePlain.indexOf("\n"))
			//O.o("3:" + sBody_postContentTypePlain.indexOf("\r\n"))

			//O.o("COOL - yes, content type plain from sender:" + imapMsg.getSender().toString())
		}
		O.o("user [" + imapMsg.getSender().toString() + "] subject [" + imapMsg.getSubject() + "] body [" + sBodyTextToRtn + "]")
		return sBodyTextToRtn;
	}


	public static FileLine cvtImapMsgToFileLine (IMAPMessage im)
	{
		def fl = new FileLine(-999, im);

	}

	private static int callcnt36 = 0;
	private static BasicDBObject cvtImapMessageToDbo (com.google.code.com.sun.mail.imap.IMAPMessage imapMsg, int icnt, 	int bodyCharsMax, boolean forTextMsg_MovedyToSubj )
	// do the switch from body to subject for text messages)

	{
		//UtilMongo.putCollection(UtilMongo.getOrCreateDBAndColl(dbn, cln), alfile);
		//O.os (

		// JUST TO SEE WHAT HEADERS ARE POSSIBLE
		if (false)
			UtilMailHelper.outputMimeHeaders_asNameVal ((com.google.code.com.sun.mail.imap.IMAPMessage) imapMsg)
		//		22.  -- 0. mimeheaderline nameMIME-Version -> val:1.0
		//		23.  -- 1. mimeheaderline nameReceived -> val:by 10.224.54.137; Tue, 25 Oct 2011 22:08:47 -0700 (PDT)
		//		24.  -- 2. mimeheaderline nameDate -> val:Tue, 25 Oct 2011 22:08:47 -0700
		//		25.  -- 3. mimeheaderline nameMessage-ID -> val:<CAFMsGwHC2bvxE=VcH01pjgRG+KPnho7GBqN=Z0ixCgY23AhO0g@mail.gmail.com>
		//		26.  -- 4. mimeheaderline nameSubject -> val:Get Gmail on your mobile phone
		//		27.  -- 5. mimeheaderline nameFrom -> val:Gmail Team <mail-noreply@google.com>
		//		28.  -- 6. mimeheaderline nameTo -> val:ustodo ustodo <ustodo.com@gmail.com>
		//		29.  -- 7. mimeheaderline nameContent-Type -> val:multipart/alternative; boundary=bcaec51a8c7e9b419b04b02ca652



		BasicDBObject dboOuter = new BasicDBObject();
		//dboOuter.put("filelineraw", filelineraw);
		dboOuter.put("InsertedToDbDate", UtilDate.getDateForFile());
		dboOuter.put("MovedToUserFavs", "false");
		dboOuter.put("ReceivedDate", imapMsg.getReceivedDate().toString());
		dboOuter.put("Size", imapMsg.getSize().toString());
		dboOuter.put("Sender", imapMsg.getSender().toString())
		dboOuter.put("RecipientsTO", imapMsg.getRecipients(RecipientType.TO).toString())
		dboOuter.put("GoogleMessageThreadId", imapMsg.getGoogleMessageThreadId())
		dboOuter.put("GoogleMessageId", imapMsg.getGoogleMessageId())
		dboOuter.put("GoogleMessageLabels", imapMsg.getGoogleMessageLabels())
		dboOuter.put("Idx", (callcnt36++).toString())
		//O.o(icnt+ ". dbouter:" + dboOuter)

		//		BasicDBObject dboInner = new BasicDBObject();
		//		O.o "in utilCreateDBODoc icnt:" + icnt
		//		dboInner.put("icnt", icnt);
		//		dboOuter.put("from", );
		//	if (icnt % 1000 == 0)
		//	Profiler.check("done cvtImapMessageToDbo icnt:" + icnt + " inserted to dboOuter.toString() [" + dboOuter.toString() + "]", true);
		if (forTextMsg_MovedyToSubj)
		{
			if (UtilStr.isNotNullBlank(imapMsg.subject))
			{
				dboOuter.put("Subject", imapMsg.subject)
				dboOuter.put("Body", UtilMailHelper.cvtImapMsgToItsBodyContent(imapMsg, bodyCharsMax))
			}
			else // move body to subject for text msgs
			{
				//O.o "for sender [" + dboOuter.get("Sender") + "] take bodytodubject"
				dboOuter.put("Subject", UtilMailHelper.cvtImapMsgToItsBodyContent(imapMsg, bodyCharsMax))
				dboOuter.put("Body", "")
			}
			
			// convert . to / not b->s 
			dboOuter.put ("Subject", ((String)dboOuter.get("Subject")).replaceAll(" . ", " / "));
		}
		else
		{
			dboOuter.put("Subject", imapMsg.subject)
			dboOuter.put("Body", UtilMailHelper.cvtImapMsgToItsBodyContent(imapMsg, bodyCharsMax))
		}

		O.o("done cvtImapMessageToDbo icnt:" + icnt + ". dboOuter.toString() [" + dboOuter.toString() + "]", true);

		return dboOuter
		//		dbc.insert(dboOuter);
	}

	//	public String toString ()
	//	{
	//		"emitted [" + cntEmit +
	//				"] cntFound [" + cntFound+
	//				"]  ageMax [" + ageMax + " " +UtilDate.decodeAgeUnits(MAX_NESTED_EXCEPTIONS) +
	//				"] smailbox [" + smailbox +
	//				"] searchstr:" + searchstr  // hhh
	//	}





	//		//2222222222222222222
	//		try {
	//			O.o "start"
	//			def emailAddress = "henry.kon@gmail.com"
	//			//def s = UtilCons.gets("enter pass fillin");
	//			def s = UtilFile.fileAsList("/Users/hkon/configprivate/tpass.txt")[0]
	//			def password = "hi"+ (s[0..0]+s[0..0]+s[0..0]) + "py\$" + s[1..4]
	//			//O.ofmt("password", password)
	//			def server = "imap.gmail.com"
	//			def port = 993
	//			def props = new Properties()
	//			props.setProperty("mail.store.protocol", "imaps")
	//			props.setProperty("mail.imaps.host", server)
	//			props.setProperty("mail.imaps.port", port.toString())
	//			com.google.code.javax.mail.Session session = com.google.code.javax.mail.Session.getDefaultInstance(props,null)
	//			O.oc ("session", session)
	//			//com.google.code.javax.mail.Store store = session.getStore("imaps")
	//
	//			com.google.code.javax.mail.Store store = session.getStore("imaps")
	//			//IMAPSSLStore store = null;
	//			O.oc ("store", store)
	//			O.ofmt ("emailAddress", emailAddress)
	//			O.ofmt ("password", password)
	//			store.connect(emailAddress, password)
	//			O.oc ("store.defaultFolder", store.defaultFolder)
	//
	//			//oc:varname [session] getClass [javax.mail.Session] toString[javax.mail.Session@7adafa2c]
	//			//oc:varname [store] getClass [com.sun.mail.imap.IMAPSSLStore] toString[imaps:]
	//			//oc:varname [store.defaultFolder] getClass [com.sun.mail.imap.DefaultFolder] toString[]
	//			//oc:varname [store.defaultFolder.list()] getClass [[Lcom.sun.mail.imap.IMAPFolder;] toString[[Lcom.sun.mail.imap.IMAPFolder;@743bce70]
	//			//oc:varname [deffolder] getClass [com.sun.mail.imap.IMAPFolder] toString[AdwordsAPI]
	//
	//
	//
	//			//With the connection to the Store, the next thing you probably want to do is see what's there. As I mentioned, GMail treats all your labels as IMAP folders.
	//			// You can list the folders that are available, and then interact with the messages in whatever folder you're interested in:
	//			//?
	//			O.oc ("store.defaultFolder.list()", store.defaultFolder.list())
	//
	//			//println (["asd", "fff"].grep("").size());
	//			//.each { it -> println "asasd:" + it }
	//
	//			// FOLDERS
	//			store.defaultFolder.list().eachWithIndex { deffolder, i ->
	//				if (i == 0) {
	//					O.os ("\r\ndeffolder", deffolder)
	//					//UtilCons.gets("observe folder class name, hit a key")
	//				}
	//				O.os "\r\nphase 1 folder.name",deffolder.name
	//			}
	//
	//
	//
	//			// 3333333333333333333333
	//			if (false)
	//			{
	//				// TESTING
	//
	//				//Object f = ["testing", "timing"]
	//				Object f = "testing"
	//
	//				//assert (f.contains("test"))
	//
	//				def x = f.findAll
	//				{
	//					it ->
	//					//assert f.contains("test")
	//					O.oc "itx", it
	//					true
	//					//it.contains("test")
	//				}
	//				O.oc ("x.size()", x.size())
	//				O.oc ("x", x)
	//
	//
	//				// END TESTING
	//			}
	//
	//
	//
	//
	//			// GET FOLDER
	//			def smailbox = "aUsToDo";
	//			smailbox = "[Gmail]/All Mail";
	//			//mailbox = "atemp";
	//
	//
	//			mailbox = "INBOX";
	//			def folder = store.getFolder(mailbox)
	//			//def folder = store.getFolder("[Gmail]/All Mail")
	//			folder.open(Folder.READ_ONLY)
	//
	//
	//
	//			if (false)
	//			{
	//				O.oc "folder", folder
	//				def x = folder.findAll
	//				{
	//					it ->
	//					//assert f.contains("test")
	//					O.oc "itx", it
	//					//true
	//					//it.contains("test")
	//				}
	//				O.oc ("x.size()", x.size())
	//				O.oc ("x", x)
	//
	//			}
	//
	//
	//			while (true)
	//			{
	//				// QRY from etc
	//				qry = "||"
	//				String searchstr = "utd";
	//				String searchfrom = "mkon"; // mkon
	//				String searchbody = null;
	//				String searchsubj = "obama" // cedar
	//				def arrIMAPMsg = null;
	//				try
	//				{
	//					// see java mail tutorial http://java.sun.com/developer/onlineTraining/JavaMail/contents.html
	//SEE FOR DELETE: !!!!!!! http://java.sun.com/developer/onlineTraining/JavaMail/contents.html#JavaMailDeleting
	//					//						Flags.Flag.ANSWERED
	//					//						Flags.Flag.DELETED
	//					//						Flags.Flag.DRAFT
	//					//						Flags.Flag.FLAGGED
	//					//						Flags.Flag.RECENT
	//					//						Flags.Flag.SEEN
	//					//						Flags.Flag.USER
	//
	//					//						To delete messages, you set the message's DELETED flag:
	//					//
	//					//						message.setFlag(Flags.Flag.DELETED, true);
	//					//						Open up the folder in READ_WRITE mode first though:
	//					//
	//					//						folder.open(Folder.READ_WRITE);
	//					//						Then, when you are done processing all messages, close the folder, passing in a true value to expunge the deleted messages.
	//					//
	//					//						folder.close(true);
	//
	//					// attachment filtering
	//
	//					//O.oc "srchTrm", srchTrm
	//					// oc:varname [srchTrm] getClass [javax.mail.search.FlagTerm] toString[javax.mail.search.FlagTerm@fffffffd]
	//
	//					// see also http://javamail.kenai.com/nonav/javadocs/javax/mail/search/SearchTerm.html
	//					//SearchTerm srchTrm =
	//					//					String searchfrom = UtilCons.gets("enter search searchfrom:")
	//					//					String searchbody = null;
	//					//					String searchsubj = UtilCons.gets("enter search searchsubj:")
	//
	//					def vstAndDateRest = new Vector<SearchTerm>();
	//					def vstOrSubjBodyFrom = new Vector<SearchTerm>();
	//
	//
	//					// 	SUBJ *******************************
	//					searchsubj = searchstr
	//					vConstraintsAdd	 "orSubj", vstOrSubjBodyFrom, new SubjectTerm(searchsubj); // -----------------------------------------1
	//
	//					// 	BODY *******************************
	//					searchbody = searchstr
	//					vConstraintsAdd "orbody", vstOrSubjBodyFrom, new BodyTerm(searchbody); // -----------------------------------------1
	//
	//					// 	FROM *******************************
	//					searchfrom = searchstr
	//					//new FromStringTerm("lbaigell@hotmail.com")).;// -----------------------------------------2
	//					vConstraintsAdd "orfrom", vstOrSubjBodyFrom, new FromStringTerm(searchfrom);// -----------------------------------------3
	//
	//
	//					// date arithmetic : http://www.techrepublic.com/blog/howdoi/how-do-i-perform-datetime-arithmetic-with-javas-calendar-class/116
	//					//new jave.util.Date()
	//
	//					// 	RCVDATE *******************************
	//					Calendar cal = new GregorianCalendar(); // now
	//					//cal.add(Calendar.DAY_OF_YEAR,-1);
	//					//cal.add(Calendar.MONTH,-1);
	//					cal.add(Calendar.DATE, -60);
	//					//O.o "QRY:added time constraint"
	//					//calendar.add(Calendar.DAY_OF_MONTH,-1);
	//					//calendar.add(Calendar.HOUR,-4);
	//					//calendar.add(Calendar.MINUTE,-5);
	//					vConstraintsAdd "and add date", vstAndDateRest, new ReceivedDateTerm(ComparisonTerm.GE.intValue(), cal.getTime());// -----------------------------------------4
	//
	//					vConstraintsAdd "to and add orterm", vstAndDateRest, new OrTerm(((SearchTerm[])vstOrSubjBodyFrom.toArray()))
	//
	//
	//
	//					// TEST GMAIL EXTENSION
	//					com.google.code.javax.mail.Message[] ms = folder.getMessages();
	//
	//					//								FetchProfile fp = new FetchProfile();
	//					//								fp.add(IMAPFolder.FetchProfileItem.X_GM_MSGID);
	//					//								fp.add(IMAPFolder.FetchProfileItem.X_GM_THRID);
	//					//								fp.add(IMAPFolder.FetchProfileItem.X_GM_LABELS);
	//					//								folder.fetch(ms, fp);
	//
	//					for (com.google.code.com.sun.mail.imap.IMAPMessage m : ms) {
	//						com.google.code.com.sun.mail.imap.IMAPMessage im =  m;
	//						O.oc "im", im
	//						System.out.println(im.getGoogleMessageId());
	//						// Hex version - useful for linking to Gmail
	//						System.out.println(Long.toHexString(im.getGoogleMessageId()));
	//						System.out.println(im.getGoogleMessageThreadId());
	//						String[] labels = im.getGoogleMessageLabels();
	//						if(labels!=null){
	//							for(String label: labels){
	//								System.out.println("Label: " + label);
	//							}
	//						}
	//					}
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//					// SEARCH
	//					arrIMAPMsg = folder.search(new AndTerm(((SearchTerm[])vstAndDateRest.toArray())));
	//					O.oc("arrIMAPMsg", arrIMAPMsg)
	//					// oc:varname [arrIMAPMsg] getClass [[Lcom.sun.mail.imap.IMAPMessage;] toString[[Lcom.sun.mail.imap.IMAPMessage;@61ffbcb]
	//
	//					O.ofmt "arrIMAPMsg.size()", arrIMAPMsg.size()
	//
	//
	//
	//
	//
	//
	//
	//					// FOR EACH METHOD FOUND
	//
	//					arrIMAPMsg.eachWithIndex
	//					{
	//						imapMsg, i ->
	//						//com.sun.mail.imap.IMAPMessage
	//						if (i < 10)
	//						{
	//							// http://javamail.kenai.com/nonav/javadocs/com/sun/mail/imap/IMAPMessage.html
	//							O.os ("\r\nAmsg: " + i + " of " + arrIMAPMsg.size() + ". " )
	//							// oc:varname [*****************msg] o.getClass().getName() [com.sun.mail.imap.IMAPMessage] o.getClass().toString() [class com.sun.mail.imap.IMAPMessage]
	//							//O.os ("\r\nmsg.sender", imapMsg.sender);
	//							O.os ("\r\nB FROM:" + imapMsg.getSender()); //  + ", " + imapMsg.from + ", " + imapMsg.from.size() + ", " + imapMsg.from[0].getPersonal() + ", " + imapMsg.from[0].getAddress());
	//							// http://javamail.kenai.com/nonav/javadocs/javax/mail/internet/InternetAddress.html
	//							// http://javamail.kenai.com/nonav/javadocs/javax/mail/internet/InternetAddress.html
	//							O.os ("\r\nC <<<<<<" + imapMsg.getReceivedDate() + ">>>>>, Lns:"+imapMsg.getLineCount().toString() + ", " +  imapMsg.subject);// + ", " + imapMsg.getMessageID() );
	//
	//							O.oc ("\r\nD imapMsg.getContentStream()" ,imapMsg.getContentStream()); // com.sun.mail.imap.IMAPInputStream
	//
	//
	//
	//
	//							StringBuffer sb = new StringBuffer();
	//							def iMAPInputStream= imapMsg.getContentStream();
	//							byte[] bytearrbuf =new byte[1024];
	//
	//							int len;
	//							while((len=iMAPInputStream.read(bytearrbuf))>0)
	//								sb.append(new String(bytearrbuf));
	//
	//							O.oc ("\r\nE <<<<<<<<<<<<<HKBODY " ,sb.toString()[0..300] + "/HKBODY>>>>>>>>>>>>>>"); // com.sun.mail.imap.IMAPInputStream
	//
	//
	//
	//
	//
	//
	//
	//							//O.oc ("\r\nE imapMsg.getContentMD5()" ,imapMsg.getContentMD5());
	//
	//
	//							//msg.setFlag(Flags.Flag.SEEN, true)
	//
	//							/**
	//							 1msg: 1 of 153.
	//							 2msg.getReceivedDate()[Thu Mar 24 02:10:41 EST 2005]
	//							 3from["Henry B. Kon, PhD" <hkon@alum.mit.edu>]
	//							 4imapMsg.from[[Ljavax.mail.internet.InternetAddress;@5738ec91]oc:varname [4.5imapMsg.from] o.toString() [[Ljavax.mail.internet.InternetAddress;@5a335053] o.getClass().getName() [[Ljavax.mail.internet.InternetAddress;] o.getClass().toString() [class [Ljavax.mail.internet.InternetAddress;]
	//							 5imapMsg.from.size()[1]
	//							 6from[0].getPersonal()[Henry B. Kon, PhD]
	//							 6.5from[0].getAddress()[hkon@alum.mit.edu]
	//							 7msg.subject[Fw: eFax from "6179267817" - 7 page(s)]
	//							 8msg.getMessageID()Å[<013401c53040$938f9d30$6601a8c0@hbk4150>]
	//							 9msg.getLineCount()[-1]
	//							 10msg.getReceivedDate()[Thu Mar 24 02:10:41 EST 2005]
	//							 *
	//							 */
	//							//UtilCons.gets("hit enter for next msg")
	//
	//						}
	//						else
	//							throw new Exception("hbk")
	//					}
	//				}
	//				catch (Exception e) {
	//					O.o "e.getMessage().toString():" + e.getMessage().toString();
	//					O.oc "e.getMessage()", e.getMessage();
	//					O.oc "e.getMessage().toString().count('hbk')", e.getMessage().toString().count("hbk");
	//					//if (e.getMessage().toString().count("hbk").intValue() != 1)
	//					int d = e.getMessage().toString().count("hbk").intValue();
	//					O.oc("d", d)
	//					if (d != 1)
	//					{
	//						throw e;
	//					}
	//				}
	//
	//				O.os "\r\nDone. box [" + mailbox + "] Found [" + arrIMAPMsg.size() + "] on search for from ["+searchfrom+"]  subj ["+searchsubj+"] body ["+searchbody+"] qry [" + qry  + "]";
	//				if (true || UtilCons.gets("hit enter next srch").equals("q"))
	//				{
	//					O.o("exiting")
	//					System.exit(1);
	//				}
	//
	//			}
	//		} catch ( Exception e)
	//		{
	//			O.or( "e", e)
	//		} finally {
	//			if(folder) {
	//				//folder.close(true)
	//			}
	//			//store.close()
	//		}
	//		if(folder){
	//			folder.close(true)
	//		}
	//
	//		if(store) {
	//			store.close()
	//		}
	//		assert (true);
	//		O.o "SUCCESS: connected to Gmail"
	//		return;
	//
	//


	//	O.oc("folder",  folder)
	//	O.oc("folder.toString()",  folder.toString())
	//	//				oc:varname [folder] getClass [com.sun.mail.imap.IMAPFolder] toString[INBOX]
	//	O.oc("folder.messages",  folder.messages)
	//	//				oc:varname [folder.messages] getClass [[Ljavax.mail.Message;] toString[[Ljavax.mail.Message;@513c952f]
	//
	//
	//
	//
	//	if (false)
	//	{
	//		def a3 = folder.findAll
	//		{
	//			it ->
	//			O.ofmt("it.toString()", it.toString())
	//
	//			it.toString().contains("a")
	//		}
	//		O.oc "a3", a3
	//
	//		def a1 = folder.findAll { it ->
	//			//assert 1 == 2 ;
	//			it.content.contains "john" ;
	//		}
	//		O.oc "a1", a1
	//	}
	//
	//	//def a1 = folder.messages
	//	// com.sun.mail.imap.IMAPFolder messages property
	//
	//	// MESSAGES WITHIN FOLDERS
	//	a2.eachWithIndex
	//	{ msg, i ->
	//		if (i == 0) {
	//			O.oc ("msg", msg)
	//
	//			//UtilCons.gets("observe msg class name, hit a key")
	//		}
	//
	//		if (i % 10 == 0)
	//		UtilCons.gets("press any key to continue iterating emails within phase 2")
	//
	//		O.oc ("msg", msg)
	//		//				oc:varname [msg] getClass [com.sun.mail.imap.IMAPMessage] toString[com.sun.mail.imap.IMAPMessage@44af17c7]
	//		O.o i + ". ============================ Phase 2 INBOX msg: Subject: ${msg.subject}"
	//		O.o "   Sent on: ${msg.sentDate}"
	//		O.o "   From: ${msg.from}"
	//		O.o "   Content: ${msg.content}"
	//		O.o "----------------------\n"
	//	}
	//


	// NEED MORE MEAT MULTIPART ETC? 	// see http://docs.codehaus.org/display/GROOVY/StreamingMarkupBuilder+and+IMAP+client+example
	// http://stackoverflow.com/questions/6107334/reading-imap-message-content-string-with-groovy
	// Q&A http://stackoverflow.com/questions/tagged/javamail?page=5&sort=newest&pagesize=50
	// http://stackoverflow.com/questions/5366767/retrieve-unread-emails-from-gmail-javamail-api-imap
	// http://www.google.com/search?sourceid=chrome&ie=UTF-8&q=imap+folder+search+groovy+FlagTerm
	// http://www.google.com/search?num=100&hl=en&q=imap+tutorial+groovy+&oq=imap+tutorial+groovy+&aq=f&aqi=&aql=1&gs_sm=e&gs_upl=5697l6883l0l6932l8l7l0l0l0l0l175l760l2.4l6l0
	// http://ja.runcode.us/q/javamail-imap-search-by-subject-fails


	// see java mail tutorial http://java.sun.com/developer/onlineTraining/JavaMail/contents.html
	//						To delete messages, you set the message's DELETED flag:
	//						message.setFlag(Flags.Flag.DELETED, true);
	//						Open up the folder in READ_WRITE mode first though:
	//						folder.open(Folder.READ_WRITE);
	//						Then, when you are done processing all messages, close the folder, passing in a true value to expunge the deleted messages.
	//						folder.close(true);



	private static void investigateMsgIds()
	{
		IMAPSSLStore store = UtilMailHelper.getStore();

		Folder inbox = null;
		try {
			//inbox = store.getFolder("INBOX");
			O.oc ("store1:", store)
			store.connect("imap.gmail.com", "ustodo.com@gmail.com", UtilMailHelper.getPassword());
			O.oc ("store2:", store)

			com.google.code.javax.mail.Folder folder = store.getFolder("[Gmail]/All Mail")
			//def folder = store.getFolder("[Gmail]/All Mail")
			folder.open(Folder.READ_ONLY)

			inbox = folder;
			//inbox = store.getFolder("[Gmail]/All Mail");

			Message[] ms = inbox.getMessages();

			FetchProfile fp = new FetchProfile();
			fp.add(IMAPFolder.FetchProfileItem.X_GM_MSGID);
			fp.add(IMAPFolder.FetchProfileItem.X_GM_THRID);
			fp.add(IMAPFolder.FetchProfileItem.X_GM_LABELS);
			inbox.fetch(ms, fp);

			for (Message m : ms) {
				IMAPMessage im = (IMAPMessage) m;
				System.out.println(im.getGoogleMessageId());
				im.getSubject()
				// Hex version - useful for linking to Gmail
				System.out.println("im.getGoogleMessageId():" + im.getGoogleMessageId());
				System.out.println("im.getGoogleMessageThreadId():" + im.getGoogleMessageThreadId());
				String[] labels = im.getGoogleMessageLabels();
				if(labels!=null){
					for(String label: labels){
						System.out.println("im.getGoogleMessageLabels() each Label: " + label);
					}
				}
			}
		}
		catch (Throwable t )
		{
			O.or("t", t);
			throw t;
			assert(false);

		} finally {
			if (inbox.isOpen()) {
				inbox.close(true);
			}
			store.close();
		}
	}




	//see utilmailsearch for main


	// spin thread if needed to sweep email out
	public static void checkEmailSpinThreadUpdateUsToDoMessages(def usernameNewauth, int numMonthsOldMax)
	{
		//THREAD:
	}

	private static void outputMimeHeaders_asNameVal (MimeMessage message)
	{
		//		String content = ...
		//		Session s = Session.getDefaultInstance(new Properties());
		//		InputStream is = new ByteArrayInputStream(content.getBytes());
		//		MimeMessage message = new MimeMessage(s, is);

		//and parsing the headers could be done like this
		O.oc("message.getContent():", message.getContent())
		message.getAllHeaderLines();
		//UtilMail.cvtImapMsgToItsBodyContent(message);
		int j = 0;
		for (Enumeration<Header> e = message.getAllHeaders(); e.hasMoreElements();) {
			Header h = e.nextElement();
			O.o (" -- " + (j++).toString() + ". mimeheaderline name" + h.getName() + " -> val:" + h.getValue());
		}
		//22.  -- 0. mimeheaderline nameMIME-Version -> val:1.0
		//23.  -- 1. mimeheaderline nameReceived -> val:by 10.224.54.137; Tue, 25 Oct 2011 22:08:47 -0700 (PDT)
		//24.  -- 2. mimeheaderline nameDate -> val:Tue, 25 Oct 2011 22:08:47 -0700
		//25.  -- 3. mimeheaderline nameMessage-ID -> val:<CAFMsGwHC2bvxE=VcH01pjgRG+KPnho7GBqN=Z0ixCgY23AhO0g@mail.gmail.com>
		//26.  -- 4. mimeheaderline nameSubject -> val:Get Gmail on your mobile phone
		//27.  -- 5. mimeheaderline nameFrom -> val:Gmail Team <mail-noreply@google.com>
		//28.  -- 6. mimeheaderline nameTo -> val:ustodo ustodo <ustodo.com@gmail.com>
		//29.  -- 7. mimeheaderline nameContent-Type -> val:multipart/alternative; boundary=bcaec51a8c7e9b419b04b02ca652
	}

	private static int iCallcnt = 0;
	public static Set getMsgIdsPastThisUser(String username)
	{
		iCallcnt++;
		HashSet hs = new HashSet();

		def cur = UtilMongo.getColl (Cfg.getCollNameThisUserEmail(username)).find();
		O.o "start getting getMsgIdsPast seen"
		int icnt = 0;
		while (cur.hasNext())
		{
			icnt++;
			def dbo = cur.next();
			//O.oc("dbo", dbo)
			//O.o iCallcnt + "." + icnt + ". getMsgIdsPast user [" + username + "] seen:" + dbo.get("GoogleMessageId")
			hs.add(dbo.get("GoogleMessageId"))

		}
		//		O.o "done getting getMsgIdsPast seen"

		hs

	}
	
	public static String cvtSenderToJustRawEmailStr(String sender)
	{
		// "Steve Crotty" <secrotty@yahoo.com>
		if (!sender.contains("<"))
		 return sender;
		String r = UtilStr.keepAllBeforeFirstOfThisNew(sender, ">")
		r = UtilStr.keepAllAfterFirstOfThis(sender, "<")
		//r
		O.o("r:" + r)
		r
		
	}

	// main see UtilMailReadLoop ... 
} // end class outer UtilMail
//SEE FOR DELETE: !!!!!!! http://java.sun.com/developer/onlineTraining/JavaMail/contents.html#JavaMailDeleting

