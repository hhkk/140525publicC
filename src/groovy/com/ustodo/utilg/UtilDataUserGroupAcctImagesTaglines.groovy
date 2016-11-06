package com.ustodo.utilg

import com.google.code.com.sun.mail.imap.IMAPFolder;
import com.google.code.javax.mail.FetchProfile;
import com.google.code.javax.mail.Store
import com.google.code.javax.mail.search.AndTerm
import com.google.code.javax.mail.search.SearchTerm
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection

class UtilDataUserGroupAcctImagesTaglines {

	public static String ofilter = null; // "got new email from sender"; //, "wrote dbobj.getClass"; // null; // "FLRtoString"

	public static Vector getVec_arr_User_pass()
	{
		Vector v = new Vector();

		v.add Arrays.asList("admin", "andim");
		v.add Arrays.asList("ckckck", "oliveoil");//    me
		v.add Arrays.asList("mkon", "mkon"); // max
		v.add Arrays.asList("marktest", "marktest"); // max
		v.add Arrays.asList("sm", "soleo"); // sean
		v.add Arrays.asList("bkon", "roundhill"); //  beth kon
		v.add Arrays.asList("bcook", "roundhill"); //  bryan
		v.add Arrays.asList("dcook", "roundhill"); //  dylan
		v.add Arrays.asList("mark", "massav"); // mark kon
		v.add Arrays.asList("morris", "fern");
		v.add Arrays.asList("ck", "kc");
		v.add Arrays.asList("a123", "a312");
		v.add Arrays.asList("lb", "bl");
		v.add Arrays.asList("zk", "kz");
		v.add Arrays.asList("mk", "km");
		v.add Arrays.asList("guest", "gtuse");
		v.add Arrays.asList("sm", "soleo");
		v.add Arrays.asList("drosen", "wyckoff");
		v.add Arrays.asList("ustodo", "NOTREALUSER"); // makes maintenance clear out the
		v
	}

	public static Vector getVec_arr_sender_user_msgmedia()
	{
		Vector v = new Vector();
		v.add Arrays.asList("6176452512@mms.att.net", "sm", "MsgText");


		v.add Arrays.asList("6179015792@mms.att.net", "morris", "MsgText");
		v.add Arrays.asList("mhpaperboy@gmail.com", "morris", "MsgText");



		v.add Arrays.asList("7813676036@messaging.sprintpcs.com", "mkon", "MsgText");
		v.add Arrays.asList("maxwellkon@gmail.com", "mkon", "MsgEmail");

		v.add Arrays.asList("7813676036xxxxxxx@messaging.sprintpcs.com", "marktest", "MsgText");
		v.add Arrays.asList("maxwellkonxxxxxxxxx@gmail.com", "marktest", "MsgEmail");

		v.add Arrays.asList("7814054265@messaging.sprintpcs.com", "ckckck", "MsgText");
        v.add Arrays.asList("henry.kon@gmail.com", "ckckck", "MsgEmail");
        v.add Arrays.asList("zachkon@gmail.com", "zk", "MsgEmail");
        v.add Arrays.asList("HenryKon@crd.com", "ckckck", "MsgEmail");
		v.add Arrays.asList("ustodo.com@gmail.com", "ckckck", "MsgEmail");

		v.add Arrays.asList("8454897511@txt.att.net", "bkon", "MsgText");

		v.add Arrays.asList("lbaigell@hotmail.com", "lb", "MsgEmail");
		v.add Arrays.asList("ustodo.com@gmail.com", "ustodo", "MsgEmail");

        return v;


	}

	public static boolean isSenderATextMsgDomain(String sender)
	{
		if (sender.contains("mms.att.net")) return true;
		if (sender.contains("messaging.sprintpcs.com")) return true;
		if (sender.contains("txt.att.net")) return true;
		if (sender.contains("messaging.nextel.com")) return true;
		if (sender.contains("mobile.mycingular.com")) return true;
		if (sender.contains("page.nextel.com")) return true;
		if (sender.contains("tmomail.com")) return true;
		if (sender.contains("vtext.com")) return true;
		if (sender.contains("mobile.mycingular.com")) return true;
		if (sender.contains("text.wireless.alltel.com")) return true;
		if (sender.contains("mobile.celloneusa.com")) return true;
		if (sender.contains("mms")) return true;
		if (sender.contains("mms")) return true;
		if (sender.contains("mms")) return true;
		if (sender.contains("mms")) return true;
	}

	public static Vector cvtUserNameToSetOfPossibleSenders(String username)
	{
		Vector v = new Vector();
		getVec_arr_sender_user_msgmedia().each {
			if (username.equals(it[1]))
				v.add(it[0])
		}
		v
	}


	private static HashMap hsCached_getSetUserNamesStr = null;
	public static String cvtSenderFullToUserName(String senderFull)
	{
		String sender = senderFull;
		if (sender.contains("<"))
			sender = UtilStr.keepAllAfterLastOfThis(sender, "<")
		if (sender.contains(">"))
			sender = UtilStr.keepAllBeforeLastOfThisNewerStripsEndDelim(sender, ">")

		if (!senderFull.equals(sender))
		{
			//O.o("converted [" +senderFull + "] to [" + sender+ "]")

		}

		if (hsCached_getSetUserNamesStr != null)
			return hsCached_getSetUserNamesStr.get (sender)

		HashMap hMap = new HashMap();
		getVec_arr_sender_user_msgmedia().each {
			hMap.put (it[0], it[1])
		}
		hsCached_getSetUserNamesStr = hMap
		String rtn = hsCached_getSetUserNamesStr.get (sender)
		if (rtn == null)
		{
			O.o "MISSING DATA: NO USER FOR SENDER:" + sender
			rtn ="THEUNKNOWNUSER"
		}
		rtn
	}



	public static String utilBuildSenderMapFLR(String sender, String username){
		//new FileLine(0, UtilDate.getDateLikeFileFormatFromUtilDate() + " utdData::senderToUserMap/"+ sender + "/" + username);
		return (UtilDate.getDateLikeFileFormatFromUtilDate() + " utdData::senderToUserMap/"+ sender + "/" + username)
	}
	// database of users and email/sms text ids
	public static void utilInsertSenderToUserMapToDb()
	{
		DBCollection coll = UtilMongo.getColl("favsckckck")
		String user = null;
		Vector vgetVec_arr_sender_user = getVec_arr_sender_user_msgmedia();
		vgetVec_arr_sender_user.each {
			List l = ((List) it)
			UtilMongo.putRecordFromFLRS(coll, user, utilBuildSenderMapFLR(l[0], l[1]))
		}
	}

    public static String getTaglineRandom()

    {
        def v = new Vector();
        v.add ("Priority-Heirachy Mind");
        v.add ("A place to look at art");
        v.add ("A web meta-application integration");

        def rnd = Math.floor((Math.random()*(v.size())))
        return v.elementAt((int)rnd)


        //
        //
        //
        //            <br>Sharable personal information filtering and retrieval
        //            <br>Email processing and (group) schedule automation
        //            <br>Web Mind Coordination Toolkit
        //            <br>App Integration Engine.  Notes Network Platform.
        //                <br>App Integration.  Records management. Email automation.
        //            <br>Escape the inbox
        //            <br>Individual or group usage
        //            <br>Take control of your information
        //            <br>Intuitive record save and share service
        //            <br>Computer-aided multitasking
        //            <br>Attention Manager.  Executive Function.  Cognitive ecosystem.
        //            <br>Manage your attention across all of your information sources
        //            <br>Automate your life.  ~ A home for your <font size=+1><font color=aqua>s</font><font
        //            color=pink>t</font><font color=yellow>i</font><font color=aqua>c</font><font
        //            color=yellow>k</font><font color=aqua>i</font><font color=yellow>e</font><font
        //            color=pink>s</font></font>
        //
        //    <br>Stickie Note Blotter Journal
        //    <br>Schedule yourself
        //    and share snippets and process email with others on a programmable
        //    messaging platform.
        //    <br>A time-word-topic pivot "document synthesizer"
        //    <br>No more application silos
        //    <br>Priority-Heirachy Mind
        //    <br>Skylike Mind
        //    <br>Records Management
        //    <br>A place to look at art
        //    <br>A web meta-application integration
        //    email task-conversation illocution engine.
        //    <br>Manage all your applications and data from one simple user interface.
        //    <br>A resting place and productivity space for mind, data, fingers.
        //    <br>A Way to Wrap your mind around it all
        //    <br>Your personal semantic web
        //    <br>Personalized enrichment of records data - semantic and syntactic attributes (see <a href="http://en.wikipedia.org/wiki/HTML5" target="_blank">HTML5 spec</a>)
        //            <br>Annotate and browse your links
        //            <br>A Way to Keep Track of it all.  Personal workflow engine.
        //                <br>Mini Me - Junior Producer.  Your mind in a database.
        //                <br>Mind Blotter.  Internet switchblade.
        //            <br>Play well with others
        //            <br>Stickies, notes, email, documents, calendar, ...
        //            <br>Schedule. Prioritize. Remind
        //            <br>A single point of launch entry into all your links, apps, data types and sources.  Integrates document management with record-keeping.
        //                <br>Shared record and record set authoring.
        //            <br>Coordinate.  Share.  Publish.
        //        <br>Database. Programmable. Web service.
        //
        //
        //



    }





        public static void main (String[] args)
	{
		try
		{
			if (true) // remove data first
			{
				O.o ("pre remove:" + UtilMongo.getCollCount("favsckckck"))

				UtilMongo.remove("favsckckck", "filelineraw", "utdData")
				//UtilMongo.remove("favsckckck", "filelineraw", "2012-02-11 01:27:55 gburch / open web analytics / do search for   Open Web Analytics e")

				O.o ("post remove:" + UtilMongo.getCollCount("favsckckck"))
			}

				//INSERT DATA
				O.o ("pre insert:" + UtilMongo.getCollCount("favsckckck"))
			utilInsertSenderToUserMapToDb();
				O.o ("post insert:" + UtilMongo.getCollCount("favsckckck"))
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
