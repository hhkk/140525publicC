package com.ustodo.utilg

import com.ustodo.todo.InvalidRequestException

import java.util.Map;
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


class UtilArgs {

	public static Map<String, String> parseArgs (String args) throws InvalidRequestException// e.g., "-t=1m -d=3m
	{
        args = " " + args; // so splitter finds first arg
		Map<String, String> hm = new HashMap()
		try
		{
            //O.o "parseArgs args [" + args + "]";
            if (args == null) // looks like JS delivers a "null" string initially??
                throw new InvalidRequestException("args is null")
			String[] split1 = args.split " -"
            O.o "130115 atgs proc # split1 [" + split1 + "]";
			try
			{
                int i = 0;
				split1.each {
                    i++;
                    //O.o "130115 hbkffddss1 wwwwwwwwwwwww wssssss          processing argstest #:" + i + " it [" + it + "]";
					if (it.startsWith("-"))
						it = it[1,-1]
					//O.o "hbkffddss2 s1:" + it
					String[] b = it.split "="  // it will be eg name=value
					if (b.length == 2)
					{
						String key = b[0].trim()
						String val = b[1].trim()
						//O.o "130115 adding argstest key=val [" + key + "]=[" + val + "] argstest"
						hm.put key, val
					}
					else
                    {
						O.o("130115 WARNING: xxx ignoring arg argstest:" + it)
                    }
				}
			} catch ( RuntimeException e)
			{
				O.or "!!!!!!!!!!!!!!!!!!! ERROR PROCESSING ARGS argstest:" , e
				return null //
			}
            //O.oc ("hm argstest:", hm)
			return hm;
		}
		catch (Throwable t )
		{
			O.or("t", t);
			throw t;
			assert(false);
		} finally {
			//O.o "done part 2"
			assert (true)
		}
	}

	private static boolean validate(String args)
	{
		//		String[] split1 = args.split " -"
		//		try
		//		{
		//			split1.each {
		//				O.o "s1:" + it
		//				String[] split2eq = it.split "="  // it will be eg name=value
		//				O.o "s2:" +  split2eq
		//				O.o "s2len:" + split2eq.length
		//				if (split2eq.length != 2)
		//					O.o "split2eq.length != 2"
		//				else
		//					O.o "split2eq.length == 2"
		////					throw new RuntimeException ("invalid args:" + args)
		//			}
		//		} catch ( RuntimeException e)
		//		{
		//			O.or "asdasd:" , e
		//			return false
		//		}
		//		return true;

	}
	//		int locdash = 0
	//
	//		while (0 <= locdash)
	//		{
	//
	//			locdash = args.indexOf ("-")
	//			O.o "locdash1:" + locdash
	//			O.o "args1:" + args
	//			if (locdash < 0)
	//				break;
	//
	//			if (locdash > 0 && !args[locdash-1].equals(" "))
	//			{
	//				//throw new RuntimeExceprtion();
	//				O.o "Ignoring args:" + args
	//				return false
	//			}
	//			O.o "locdash2:" + locdash
	//			O.o "args2:" + args
	//			O.o "args[1,-1]:" + args[1,-1]
	//			args = args.substring(locdash+(args[1,-1].indexOf ("-")))
	//			O.o "args3:" + args
	//		}

    // convert key value args map to a string like -tm=36m -ctx=hk
    public static String buildArgStr(Map<String, String> map)
    {
        def sb = new StringBuffer();
        map.keySet().each {
            sb.append "-" + it + "=" + map.get(it) + " ";
        }
        sb
    }

	public static void main (String[] args)
	{
		try
		{
			parseArgs ("-tm=36m -ctx=hk / gb test ")
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
