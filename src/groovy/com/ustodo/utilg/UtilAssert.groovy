package com.ustodo.utilg


import java.util.Vector

import com.google.code.com.sun.mail.imap.IMAPInputStream;
import com.google.code.com.sun.mail.imap.IMAPMessage;
import com.google.code.com.sun.mail.imap.IMAPSSLStore
import com.google.code.javax.mail.Folder
import com.google.code.javax.mail.Store;
import com.google.code.javax.mail.internet.MimeMessage.RecipientType
import com.google.code.javax.mail.search.BodyTerm
import com.google.code.javax.mail.search.ComparisonTerm
import com.google.code.javax.mail.search.FromStringTerm
import com.google.code.javax.mail.search.IntegerComparisonTerm;
import com.google.code.javax.mail.search.MessageNumberTerm;
import com.google.code.javax.mail.search.OrTerm
import com.google.code.javax.mail.search.ReceivedDateTerm
import com.google.code.javax.mail.search.SearchTerm
import com.google.code.javax.mail.search.SubjectTerm
import com.mongodb.BasicDBObject;
import com.ustodo.utilj.Ozz
import grails.web.JSONBuilder;


class UtilAssert {
	public static void assertx(boolean b)
	{
		if (!b)
			throw new RuntimeException("assert fail");
	}
	public static void assertx(String desc, boolean b)
	{
		if (!b)
			throw new RuntimeException("assert fail desc:" + desc);
	}

	public static void assertequalsx(String desc, int i1, int i2)
	{
		if (!(i1 == i2))
			throw new RuntimeException("assert fail desc [${i1}] [${i2}] desc [${desc}] ");
	}

    public static void assertNonFatalJustOutIt (desc, testFailIfFalse)
    {
        if (!testFailIfFalse)
            O.o ("HKASSERT FAIL!!! [" + desc + "]")
        else
            O.o ("HKASSERT PASS!!! [" + desc + "]")
    }
}
