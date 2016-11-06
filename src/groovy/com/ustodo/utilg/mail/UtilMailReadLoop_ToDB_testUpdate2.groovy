package com.ustodo.utilg.mail

import com.google.code.com.sun.mail.imap.IMAPFolder
import com.google.code.javax.mail.FetchProfile
import com.google.code.javax.mail.Flags
import com.google.code.javax.mail.Folder
import com.google.code.javax.mail.Store
import com.google.code.javax.mail.search.AndTerm
import com.google.code.javax.mail.search.SearchTerm
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection;
import com.ustodo.utilg.Cfg
import com.ustodo.utilg.O
import com.ustodo.utilg.Profiler
import com.ustodo.utilg.Rte
import com.ustodo.utilg.UtilAssert

import com.ustodo.utilg.UtilDataUserGroupAcctImagesTaglines;
import com.ustodo.utilg.UtilMongo;


class UtilAllUserEmailToDB {

	public static enum SrchMode {KEYAGE, MSGID, ALLSINCELAST};


	private static void readEmailToDbAllUsers()
	{

		O.o("getting emails into coll:" + Cfg.collnameEmailsSuckedIn) // "EmailsSuckedIn"
		// PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I  PHASE I
		// put emails into the DB
		UtilAllUserEmailToDB.mailReadLoop_OptionalSave (

				new UtilMailDataSearchDisposeSpec (
				"[Gmail]/All Mail",    // mailbox/folder
				//"atemp",  // mailbox/folder
				//"*", // search str
				//"subjhk", // searchstr
				//"hbk120226", // searchstr
				null, //"ustodo", // searchstr
				60, //  <------------------- DATE
				Calendar.DATE, // <-------------------  days
				//Calendar.YEAR,
				//Calendar.DATE,
				-1, // NUM RECS  <-------------------
				UtilAllUserEmailToDB.SrchMode.KEYAGE.toString(),
				true, // PERSIST
				"ustodo.com@gmail.com",
				false, // emptyCollFirst
				true, // mark as read <-------------------
				true, // unseenonly <-------------------
				200, // body char's <-------------------
				true, // move subject on text messages <-------------------
				)

				); // if null will get created there

	}



	public static void  mailReadLoop_OptionalSave ( UtilMailDataSearchDisposeSpec esReq)
	{

		O.o("EMAIL SEARCH SPEC:" + esReq.toString());
		//cal.add(Calendar.DATE, -600);
		//cal.add(Calendar.DATE, -(10*365));
		//cal.add(Calendar.DATE, ageMaxDays);
		//O.ofmt("agemaxyears", ageMaxYears)
		//cal.add(Calendar.MONTH, -ageMaxMonths);
		//cal.add(Calendar.YEAR, -ageMaxYears);
		//calendar.add(Calendar.DAY_OF_MONTH,-1);
		//cal.add(Calendar.HOUR,-24);
		//cal.add(Calendar.MINUTE,-120);
		//cal.add(Calendar.SECOND,-ageMaxSeconds);

		//searchEmail("twisted");
		//searchEmail("NSTAR", "INBOX", 120); // 2
		//searchEmail("NSTAR", "[Gmail]/All Mail", 120); //  24
		//searchEmail("NSTAR", `"[Gmail]/Addll Mail", 2); //
		//searchEmail("Overlord");

		//searchEmail("*", "[Gmail]/All Mail", 1, Calendar.MONTH); //
		//		if (esReq == null) // default search: allow user to pass null and we fill in a default search
		//		{
		//			esReq = new UtilMailSearchRequestWithDispositionOptions (
		//				"[Gmail]/All Mail",    // mailbox/folder
		//				//"atemp",  // mailbox/folder				//"*", // search str    	//"subjhk", // searchstr
		//				"grails", // searchstr
		//				1, // DATE CONSTRAINT
		//				Calendar.MONTH, 	//Calendar.DATE, // DATE CONSTRAINT
		//				5,  // max num records to store
		//				SrchMode.KEYAGE.toString(),
		//				com.ustodo.utilg.Cfg.dbname,
		//				false, // db persist - false - this is production
		//				null,
		//				"ustodo.com@gmail.com",
		//				false // emptyCollFirst
		//				);
		//		}

		Profiler.start("searchEmail", true);
		def folderReal = null;

		try
		{
			// 1 GET GMAIL STORE AND CONNECT
			// from http://stackoverflow.com/questions/61176/getting-mail-from-gmail-into-java-application-using-imap
			Store store = UtilMailHelper.getStore();
			Profiler.check("post getstore" , true)
			store.connect("imap.gmail.com", esReq.emailAcct, UtilMailHelper.getPassword());
			Profiler.check("post store.connect" , true)
			//O.oc ("store.defaultFolder", store.defaultFolder) // oc:<<com.google.code.com.sun.mail.imap.DefaultFolder>>
			O.o "esReq.smailbox:" + esReq.smailbox
			// 2 GET GMAIL FOLDER PER ESX
			// list of all folders
			//String[] foldernames = UtilMail.getAllFoldersUnderRootNamespace(store, true);
			int folderMode =  Folder.READ_ONLY;
			if (esReq.bMarkEmailsAsReadIfMakeItToDb)
				folderMode =  Folder.READ_WRITE;

			folderReal = UtilMailHelper.getFolder(store, esReq.smailbox, folderMode); // "[Gmail]/All Mail"		or 		def smailbox = "aUsToDo"; //smailbox = "atemp";
			Profiler.check("post getfolder" , true)

			//O.oc ("folder", folder)  // // <<com.google.code.com.sun.mail.imap.IMAPFolder
			//folder.each {
			//	println "asdasd" + it;
			//}

			def arrIMAPMsg = null;

			long loopStart;
			DBCollection coll = null;

			// IF SAVING EMAILS TO THE DB
			if (esReq.persistToDb)
			{
				//					coll = UtilMongo.getOrCreateDBAndColl("desc:in searchemailOptionalSave", Cfg.dbname, esReq.getCollNameIfPersist());
				if (esReq.emptyCollFirst) // EMPTY ALL USER COLLS
				{
					UtilDataUserGroupAcctImagesTaglines.getVec_arr_User_pass().each {
						List l = ((List) it)
						String collname = Cfg.getCollNameThisUserEmail(it[0])
						UtilMongo.removeAll(collname, false) //  no confirm
					}
					O.o("completed empty colls")
				}
			}
			try
			{
				//def vstAndDateRest = UtilMail.buildQueryVector( searchstr, 3600*24*365*10 ); // one hour = 3600 seconds.
				//String srchMode = searchmode.KEYAGE.toString();

				if (esReq.srchMode.equals(SrchMode.KEYAGE.toString()))
				{
					O.o("SrchMode.KEYAGE.toString")
					def substringSrchFromOrNullIfAll = null
					def vstAndDateRest = UtilMailHelper.buildQueryVector_keysAndAge( esReq.searchstr, esReq.ageMax, esReq.ageUnit, substringSrchFromOrNullIfAll, esReq.unseenonly); // months






					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					// EMAIL SEARCH HERE
					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					arrIMAPMsg = folderReal.search(new AndTerm(((SearchTerm[])vstAndDateRest.toArray())));

					//UtilCons.gets "got [" + arrIMAPMsg.length + "] email messages.  hit a key to continue";
					O.o("got [" + arrIMAPMsg.length + "] email messages");


					// try to get message ID
					FetchProfile fp = new FetchProfile();
					fp.add(IMAPFolder.FetchProfileItem.X_GM_MSGID);
					fp.add(IMAPFolder.FetchProfileItem.X_GM_THRID);
					fp.add(IMAPFolder.FetchProfileItem.X_GM_LABELS);
					folderReal.fetch(arrIMAPMsg, fp);


				}
				else if (esReq.srchMode.equals(SrchMode.MSGID.toString()))
				{
					//					def vstAndDateRest = UtilMail.buildQueryVector_messageNumber (18306.intValue()); // months
					//					arrIMAPMsg = folder.search(new AndTerm(((SearchTerm[])vstAndDateRest.toArray())));

				}
				else if (esReq.srchMode.equals(SrchMode.ALLSINCELAST.toString()))
				{

					// should this be on the
					//					DBCollection dbc = UtilMongo.getOrCreateDBAndColl(esReq.getNameDB(), getFavsDBCollNameForUser());
					//					dbc.find().eachWithIndex { dbo, i ->
					//						String dbLineRaw = dbo.toString();
					//						O.oc( "dbrec.keys", dbo.keySet())
					//						String fileLineRaw = dbo.filelineraw.toString().trim(); // not found since dynamic
					//						//String fileLineRawLower = fileLineRaw;
					//					}
					//
					//					def vstAndDateRest = UtilMail.buildQueryVector_keysAndAge( esReq.searchstr, esReq.ageMax, esReq.ageUnit); // months
					//					arrIMAPMsg = folder.search(new AndTerm(((SearchTerm[])vstAndDateRest.toArray())));

				}
				else
					throw new Exception("not allowed")


				// GET IMapMsg Array

				//O.ofmt "arrIMAPMsg.size()", arrIMAPMsg.size()

				esReq.esRslt.dateSearchStarted = new java.util.Date();
				esReq.esRslt.numFound = arrIMAPMsg.size();
				//O.o("found $esReq.esRslt.numFound emails on search [$esReq.searchstr]");
				loopStart = System.currentTimeMillis()

				// TODO: find a counting hashmap ...
				HashMap<String, Integer> hsSubjToCount = new HashSet<String, Integer>();

				int maxwanted = Math.max(esReq.numEmitReq, esReq.numToDBReq )

				// EMAIL LOOP READ HERE





				// avoid repeat entry into the db
				//Set setMsgIdsPast = getMsgIdsPast()




				// EMAIL READ LOOP - SMALL CLOSURE
				// EMAIL READ LOOP - SMALL CLOSURE
				// EMAIL READ LOOP - SMALL CLOSURE
				// EMAIL READ LOOP - SMALL CLOSURE
				int emailRcvCount = 0;
				arrIMAPMsg.eachWithIndex {	it_imapMsg, i ->
					emailRcvCount++
					perImapMsgClosureForReadLoopAbove_OptionalSave	(	it_imapMsg, esReq, arrIMAPMsg, maxwanted, i,hsSubjToCount);
				} // each message withindex*


				// DONE SAVE FROM EMAIL SEARCH TO DB, NOW READ BACK FROM DB

				//def arrDboImapmsg = new com.mongodb.BasicDBObject()[];
				O.o  "======================= DONE email read loop got:" + emailRcvCount + " msgs ========================"
				//				coll.find().eachWithIndex { dbrec, i ->
				//					O.oc("dbrec", dbrec) // com.mongodb.BasicDBObject
				//					//O.oc("arrDboImapmsg", arrDboImapmsg) // com.mongodb.BasicDBObject
				//
				//					//arrDboImapmsg << dbrec
				//					//UtilMongo_File_Email_Db.emitbackOutADBMailObject(dbrec);
				//				}
			}
			catch (Exception e)
			{
				O.o "EXCEPTION!!  SWALLOWED? " + e.getMessage()
				O.or "EXCEPTION!!  SWALLOWED? ", e

				if (e.getMessage().toString().count("hbkloopexit").intValue() != 1) // if not just exiting the loop - a hack yes - what better way ?
				{
					O.o "Done email retrieval FAIL:" + esReq.toString();
					throw e
				}

				O.o "Done email retrieval SUCCESS:" + esReq.toString();

			}

			esReq.esRslt.numMsInLoop = System.currentTimeMillis()-loopStart;

		}
		catch (com.google.code.javax.mail.NoSuchProviderException e) {
			e.printStackTrace();
			throw e;
		} catch (com.google.code.javax.mail.MessagingException e) {
			e.printStackTrace();
			println "sd:" + e.getMessage();
			//O.or(qry, e)
            throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			println "sd:" + e.getMessage();
			//O.or(qry, e)
            throw e;
		}
		finally {
			if (folderReal != null)
			{
				if (esReq.bMarkEmailsAsReadIfMakeItToDb)
				// someone said: passing in a true value to expunge the deleted messages
				folderReal.close(true);
				else
					folderReal.close(false);
			}
			O.o("done")
		}
	}	 // end public static FileLine[] searchEmailOptionalSave ( UtilMail.EmailSrchReqToEmitAndDB esReq)

	private static void perImapMsgClosureForReadLoopAbove_OptionalSave(
	com.google.code.com.sun.mail.imap.IMAPMessage it_imapMsg,
	UtilMailDataSearchDisposeSpec esReq,
	def arrIMAPMsg,
	int maxwanted,
	int i,
	HashMap<String, Integer> hsSubjToCount
	)
	{
		if (maxwanted > 0 && i >= maxwanted)
			O.o "skip msg :" + i + ", since maxwanted:" + maxwanted
		else
		{
			// if (it_imapMsg.getSequenceNumber().equals(getMessageNumber:36

			//O.os ("Amsg: " + (i+1) + " of " + arrIMAPMsg.size() + ".\r\n" )
			if (false && i <3)
			{
				//UtilGroovyMetaObject.listMethsProps(it_imapMsg)
				boolean emitcontent = false
				UtilMailHelper.emitMsg("i<3email:", it_imapMsg, emitcontent)
			}
			//if (esReq.persistToDb && (i < 0 || i < esReq.getNumToDBReq()))
			esReq.esRslt.numFound++;

			// ADD EMAIL TO FILE LINE ARR FOR RTN
			// Check if word is in HashMap
			def subj = it_imapMsg.getSubject()
			if (hsSubjToCount.containsKey(subj)) {
				// get number of occurrences for this word
				// increment it
				// and put back again
				hsSubjToCount.put(subj, hsSubjToCount.get(subj) + 1);
			} else {
				// this is first time we see this word, set value '1'
				hsSubjToCount.put(subj, 1);
			}
			// CONVERT IMAP TO FILELINE
			//FileLine fl = UtilMailHelper.cvtImapMsgToFileLine(it_imapMsg); // 1/2 CVT TO FL ########################################################
			//O.o("got email fileline:" + fl.toS�tring())
			//O.oc("it_imapMsg", it_imapMsg) // com.google.code.com.sun.mail.imap.IMAPMessage -- core class





			// bh
			BasicDBObject dboemail = UtilMailHelper.cvtImapMessageToDbo(it_imapMsg, i, esReq.bodyCharsMax, esReq.forTextMsg_MovedyToSubj) // 2/2 CVT TO DBO ########################################################

			String usernamesentfrom = UtilDataUserGroupAcctImagesTaglines.cvtSenderFullToUserName(dboemail.get("Sender"));
			DBCollection coll = UtilMongo.getColl(Cfg.getCollNameThisUserEmail(usernamesentfrom));


			//	dboOuter.put("ReceivedDate", imapMsg.getReceivedDate().toString());
			//	dboOuter.put("Size", imapMsg.getSize().toString());
			//	dboOuter.put("Sender", imapMsg.getSender().toString())
			//	dboOuter.put("RecipientsTO", imapMsg.getRecipients(RecipientType.TO).toString())
			//	dboOuter.put("Subject", imapMsg.subject)
			//	dboOuter.put("GoogleMessageThreadId", imapMsg.getGoogleMessageThreadId())
			//	dboOuter.put("GoogleMessageId", imapMsg.getGoogleMessageId())
			//	dboOuter.put("GoogleMessageLabels", imapMsg.getGoogleMessageLabels())
			//	dboOuter.put("Body", UtilMail.cvtImapMsgToItsBodyContent(imapMsg))
			//	dboOuter.put("Idx", (callcnt36++).toString())

			O.o("got loop emaiml dboemail.get('GoogleMessageId'):" + dboemail.get("GoogleMessageId"))

			if (esReq.persistToDb && (esReq.getNumToDBReq() < 0 || i < esReq.getNumToDBReq()))
			{
				if (!UtilMailHelper.getMsgIdsPastThisUser(usernamesentfrom).contains(dboemail.get("GoogleMessageId")))
				{
					int cntpre = coll.getCount()
					// DB INSERT
					UtilMongo.put(coll, dboemail);
					esReq.esRslt.numToDB++
					//O.o("db insert of imapMsg index0" + i + "dboemail.get(GoogleMessageId'):" + dboemail.get("GoogleMessageId"))

					if (true) // code block for validation of write
					{
						int cnt1 = UtilMongo.queryEquals(coll, dboemail).length // query DB to see if write succeeded
						UtilAssert.assertequalsx("confirm email write", cnt1, 1)
						int cntpost = coll.getCount()
						UtilAssert.assertequalsx("db  write pre+1 to post count compare", cntpre+1, cntpost)
						if (cntpost != (cntpre+1))
							throw new Rte ("write/read failed on email write:" + dboemail)
						else
							O.o("\r\n\r\n\r\nwrote email...write/read success on:" + dboemail)
					} // end confirm db write

					// send confirm to
					if (true)
					{
						// if sender has like sprintpcs...
						// if (UtilDataUserGroupAcctImagesTaglines.isSenderATextMsgDomain(dboemail.get("Sender")))
						//{
						try {
							// send ack:
							Vector<String> vStrRecips = new Vector<String>()
							vStrRecips.add(dboemail.get("Sender"))

							UtilEmail.sendMail(vStrRecips, "ack:" + dboemail.get("Subject"), "ustodo acknowedges receipt", "UsToDo.com") //
						} catch ( Exception e) {
							O.or("error email send", e)
							e.printStackTrace();
						}
						// "UsToDo acknowledges receipt of message:" + dboemail.get("Subject")

						//}
					}
					O.o("dboemail:", dboemail)


				}
				else
				{
					O.o("not persisting emails to db as we already have this message dboemail.get(GoogleMessageId'):" + dboemail.get("GoogleMessageId"))
				}



				// mark as seen?
				if (esReq.bMarkEmailsAsReadIfMakeItToDb)
				{
					if (true || dboemail.get("GoogleMessageId") == 1393075045649632083) //, subj:pending / sean /)
					{
						O.o("marking seen here:"+ dboemail.get("GoogleMessageId") + ", subj:" +
								dboemail.get("Subject"))
						it_imapMsg.setFlag(Flags.Flag.SEEN, true)

					}
					// Flags.Flag.: answered deleted draft flagged recent seen user
					//it_imapMsg.setFlag(Flags.Flag.SEEN, true); // remember to close folder different
					//
					//				message.setFlag(Flags.Flag.DELETED, true);
				}







			}

			//						Object o = dboemail.get("Subject")
			//						O.oc("o (Subject object):", o)
			//						Object o2 = dboemail.get("Body")
			//						O.oc("o2 (Body object):", o2)
			String subject = dboemail.get("Subject").toString().trim()
			String body = dboemail.get("Body").toString().trim()
			//O.oc("subject:", subject)
			//O.o("subject == null:", subject == null) // false
			// when there is no subject, then the subject line is the string "null"
			//O.o("subject[0..1]:", subject[0..1])
			//O.o("11111111 dboemail.get(GoogleMessageId):" + dboemail.get("GoogleMessageId"))
			//O.o("22222222 dboemail.get(Sender):" + dboemail.get("Sender"))
			//O.o("33333333 subject:", subject)
			//O.o("44444444 gbody:", "first 200:" + body [0..Math.min(200, body.size()-1)] + "................. more body still")

			//O.o "\r\n\r\n\r\n\r\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2 got new email from sender fl.toString():" + fl.toString()

			//fl.emit("searchEmailOptionalSave", true)
		}

	}






	private static void investigateMarkRead()
	{

	}

	public static void readEmailAndPropagate()
	{
		readEmailToDbAllUsers()
		// STEP 2: POST MAILS TO USTODO RECORDS
		UtilDataUserGroupAcctImagesTaglines.getVec_arr_User_pass().each {
			List l = ((List) it)
			String username = l[0];
			//O.o("Begin email propagate for user:" + username)
			UtilMailRecMoveFromDB_postToUsers.distribFromEmailTabletoFavsTableThisUser(username)
		}

	}

	public static void main (String[] args)
	{
		try
		{
			// ******************************************************************************************************************************************************************************************************************
			// STEP 1 READ FROM IMAP TO DB
			readEmailAndPropagate()


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

	// main hbk120226 see UtilHelper.java
}
