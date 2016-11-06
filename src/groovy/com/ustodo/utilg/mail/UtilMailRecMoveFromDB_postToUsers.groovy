package com.ustodo.utilg.mail

import com.mongodb.BasicDBObject;
import com.ustodo.utilg.Cfg
import com.ustodo.utilg.Dbo;
import com.ustodo.utilg.O
import com.ustodo.utilg.UtilDataUserGroupAcctImagesTaglines;
import com.ustodo.utilg.UtilDate
import com.ustodo.utilg.UtilMongo;
import com.ustodo.utilg.UtilStr

class UtilMailRecMoveFromDB_postToUsers {

	private static Set fillSetWithSeenMSgIdsThisUser(String sUser)
	{
		Set setRtn = new HashSet<String>()
		List lmsgsalreadythisuser = UtilMongo.getFLRStrList_gotmsg("favs" + sUser)
		lmsgsalreadythisuser.each {
			if (!((String)it).contains("msg::"))
			{
				throw new RuntimeException("hit snag no msg::"+ it);
			}
			String[] arrMsgData = it.split "::"
			if (arrMsgData.length != 3 && arrMsgData.length != 4)
				throw new RuntimeException("hit snag split on :: should make 4 but is [" + arrMsgData.length + "] ["+ it + "]");

			String flrInsert = UtilDate.getDateForFile() + " " + arrMsgData[2]
			if (arrMsgData.length == 4)
			{
				flrInsert += arrMsgData[3]
			}

			flrInsert += "// "

			setRtn.add(flrInsert)

		}
	}

	private static void distribFromEmailTabletoFavsTableThisUser(String username)
	{
		// emit body only

		String collnameSrcEmail = Cfg.getCollNameThisUserEmail(username)
		String collnameTgtFavs = Cfg.getCollNameThisUserFavs(username)
		def curEmails = UtilMongo.getCur(collnameSrcEmail, new Dbo())
		int i = 1
		//HashMap<String, String> hmSenderToUser = UtilMongo.getUtdDatAsMap("senderToUserMap");
		int inserted = 0;
		while (curEmails.hasNext())
		{
			BasicDBObject dboEmail = curEmails.next();
			//if ( i == 1)
			//	O.o dbo.keySet().toString()
			String sBody = dboEmail.get ("Body");
			if (sBody.length() > 80)
				sBody = UtilStr.keepUpToZeroBased(sBody, 80)
			String sender = dboEmail.get ("Sender").toString()
			// find match check all keys
			//String sUser = null;
			Set hsGmsgidsThisUser = null;
			HashMap<String, Set> hmUserToSetAlreadyHaveMsgIds = new HashMap<String, Set<String>>()

			String userThisEmailSender = UtilDataUserGroupAcctImagesTaglines.cvtSenderFullToUserName(sender);

			//			hmSenderToUser.keySet().each {
			//				if (sender.contains(it))
			//				{
			//					if (sUser != null)
			//						throw new RuntimeException("why still in the loop sender in map set 2x" + sender)
			//
			//					// found the user, see which getFLRStrList_gotmsg
			//					sUser = hmSenderToUser.get(it)
			//					hsGmsgidsThisUser = hmUserToSetAlreadyHaveMsgIds.get(sUser);
			//					if (hsGmsgidsThisUser == null)
			//						hmUserToSetAlreadyHaveMsgIds.put(sUser, fillSetWithSeenMSgIdsThisUser(sUser))
			//					hsGmsgidsThisUser = hmUserToSetAlreadyHaveMsgIds.get(sUser);
			//				}
			//			}
			//			hsGmsgidsThisUser = hmUserToSetAlreadyHaveMsgIds.get(sUser);

			if (dboEmail.get("MovedToUserFavs") == null || dboEmail.get("MovedToUserFavs").equals("false"))
				//|| hsGmsgidsThisUser != null && !hsGmsgidsThisUser.contains(dboEmail.get ("GoogleMessageId").toString()))
			{
				//				if (hsGmsgidsThisUser == null)
				//				{
				//					hsGmsgidsThisUser = new HashSet<String>()
				//					hsGmsgidsThisUser.add(dbo.get ("GoogleMessageId").toString());
				//					hmSenderToUser.put (sUser, hsGmsgidsThisUser)
				//				}
				String tail = sBody + ", Sender [" + sender +"]"
				String flrmsg = null;
				if (dboEmail.get ("Subject") != null)
					flrmsg = UtilDate.getDateForFile() + " " + dboEmail.get ("Subject").toString() + "::" + tail
				//flrmsg = UtilDate.getDateForFile() + " " + "msg::" + dbo.get ("GoogleMessageId").toString() + "::" + sender+ ":" + dbo.get ("Subject").toString() + "::" + sBody  // utddata
				else
					flrmsg = UtilDate.getDateForFile() + " " + "nullsubjhk"                   + "::" + tail

				if (true || userThisEmailSender.equals ("ckckck")) //  && inserted < 1000) // if want a cap
				{

					if (true) // production switch
					{
						UtilMongo.putRecordFromFLRS(UtilMongo.getColl("favs" + userThisEmailSender), userThisEmailSender, flrmsg)
						O.o "propogae email for user [" + userThisEmailSender + "] flrmsg [" + flrmsg+ "]"
						UtilMongo.getColl("favs" + userThisEmailSender)


						// now update to indicate this has been propagated

						BasicDBObject dboEmailClone = new BasicDBObject(dboEmail)
						dboEmailClone.put "MovedToUserFavs", "true"

						UtilMongo.getColl(collnameSrcEmail).update (dboEmail, dboEmailClone)


					}
					else
						O.o "whw user [" + userThisEmailSender + "] flr [" + flrmsg+ "]"
					inserted++;
				}
			}
			else
			{
				//O.o "skip email insert user [" + userThisEmailSender + "] + [" + dboEmail.get ("GoogleMessageId").toString() + "]"
			}

			if (false)
				O.o(
						//"\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n" + i++ + ". (1-based) hkhkhkhkhkhkhkhkhkh	khkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhkhk\r\n"
						+ "\r\n  -- " + i + ". sender:" + sender + ":::"
						+ "\r\n  -- " + i + ". id:" + dboEmail.get ("GoogleMessageId").toString() + ":::"
						+ "\r\n  -- " + i + ". subject:" + dboEmail.get ("Subject").toString() + ":::"
						+ "\r\n  -- " + i + ". body<<<<HBK<<<<<<" + sBody + "\r\n>>>>>>HBK 80 cap>>>>>>>"
						)
			//O.o("for sender [" + sender + "] got user [" + sUser+ "]")
		}
		O.o "done email propagate for user ["+username+"], inserted ["+inserted+"]"


	} // private static void postMail(String collname_emails)

	public static void main (String[] args)
	{
		try
		{
			//UtilMongo.getDomainEmailMsgIdsInDB()
			//UtilMailRecMoveFromDB_postToUsers.distribEmailFromCentralCollectionToAccounts(THIS SHOULD BE PER PERSON NOW SE UTILMAILREADLOOP...2 Cfg.collnameEmailsSuckedIn) // "EmailsSuckedIn"
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


} // class
