package com.ustodo.utilg.mail

import com.ustodo.utilg.mail.UtilMailHelper.EmailSrchResult;




class UtilMailDataSearchDisposeSpec {


	String smailbox;
	String searchstr;
	int ageMax;
	int ageUnit;
	String srchMode;


	String emailAcct;
	
	int numEmitReq;
	int numToDBReq;

	private EmailSrchResult esRslt;
	private boolean persistToDb;
	private boolean emptyCollFirst; // empty all user collections before receive loop
	private boolean bMarkEmailsAsReadIfMakeItToDb;
	boolean unseenonly
	int bodyCharsMax
	boolean forTextMsg_MovedyToSubj
	
	// CONSTRUCTOR
	public UtilMailDataSearchDisposeSpec (	String smailbox,
		String searchstr,
		int ageMax,
		int ageUnit,
		int numToDBReq,
		String srchMode,
		boolean persistToDb,
		String emailAcct, // eg Cfg.collnameEmailsSuckedIn
		boolean emptyCollFirst, // eg Cfg.collnameEmailsSuckedIn
		boolean bMarkEmailsAsReadIfMakeItToDb, // eg Cfg.collnameEmailsSuckedIn
		boolean unseenonly,
		int bodyCharsMax,
		boolean forTextMsg_MovedyToSubj
	) {
		super();
		this.smailbox = smailbox;
		this.searchstr = searchstr;
		this.ageMax = ageMax;
		this.ageUnit = ageUnit;
		this.numToDBReq = numToDBReq;
		this.srchMode = srchMode;
		this.persistToDb = persistToDb;
		this.emailAcct = emailAcct;
		this.emptyCollFirst = emptyCollFirst;
		this.bMarkEmailsAsReadIfMakeItToDb = bMarkEmailsAsReadIfMakeItToDb;
		this.unseenonly = unseenonly;
		this.bodyCharsMax = bodyCharsMax;
		this.forTextMsg_MovedyToSubj = forTextMsg_MovedyToSubj;
		esRslt = new EmailSrchResult(); //embed return in the request
		if (bMarkEmailsAsReadIfMakeItToDb && !persistToDb)
			throw new RuntimeException("invalid mode:")
	}
	
	public String toString() {
		return "EmailSearchRequest tostr:<!<!<!<!<{" +
		"]\r\n, smailbox [" + smailbox +
		"]\r\n, searchstr:" + searchstr +
		"\r\n, --> ageMax:" + ageMax +
		"\r\n, ageMaxUnits:" + UtilMailHelper.decodeAgeUnits(ageUnit) +
		"\r\n, --> numToDBReq:" + numToDBReq +
		"\r\n, srchMode:" + srchMode +
		"\r\n, persistToDb:" + persistToDb +
		"\r\n, --> emptyCollFirst:" + emptyCollFirst +
		"\r\n, bMarkEmailsAsReadIfMakeItToDb:" + bMarkEmailsAsReadIfMakeItToDb +
		"\r\n, --> unseenonly:" + unseenonly +
		"\r\n, --> bodyCharsMax:" + bodyCharsMax +
		"\r\n, --> forTextMsg_MovedyToSubj:" + forTextMsg_MovedyToSubj +
		"\r\n, RESULT: esRslt.toString:" + esRslt.toString() +
		"\r\n	>!>!>!>!>!>"
	}
	public String getAgeUnits() {
		return UtilMailHelper.decodeAgeUnits(ageUnit)
	}

	EmailSrchResult esRslt;
}
