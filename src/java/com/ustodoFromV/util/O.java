package com.ustodoFromV.util;

import java.io.PrintWriter;
import java.io.StringWriter;


	//import redis.clients.jedis.Jedis;

	public class O {
		
		static int callCount = 0;
		//private static Logger logger=Logger.getLogger("Ologger");		// see c:/u and X:/tmp/log4j	
		

		
		public static void oNotifyHuman (String s)
		{
			//Notification.show(s, Notification.Type.HUMANIZED_MESSAGE);
            System.out.println("S:" + s);
		}

		
		public static void o (String s)
		{
			callCount++;
			System.out.println(new java.util.Date() + " " + callCount + ". [" + s + "]");
		}
		public static void oClass (String desc, Object a)
		{
			o ("oclass1:desc:" + desc + ", a.toSring():[" + a.toString() + "]");
			o ("oclass2:desc:" + desc + ", a.getClass():[" + a.getClass() + "]");
			o ("oclass3:desc:" + desc + ", a.getClass().getName():[" + a.getClass().getName() + "]");
			o ("oclass4:desc:" + desc + ", UtilJava.getObjectId(a):[" + UtilJava.getObjectId(a) + "]");
		}
	    public static String oerr(String callerId, Throwable t)
	    {
	        O.o("ERROR: O.OERR callerId [" + callerId +  "] t.getMessage() 9 <<" + t.getMessage() + ">> stackTraceToString [ " + stackTraceToString(t, 2000) + "] end stackTraceToString");
	        t.printStackTrace();
	        return stackTraceToString(t, -1);
	    }
	    public static String or(String callerId, Throwable t)
	    {
	        return oerr(callerId, t);
	    }
//
//
//	    static int i = 0;
//
//	    private static String fn = "/Users/hkon/tmp/Otemp.txt";
//
//	    private static int callCntO=0;
//	    private static long lastms = 0;
//	    private static String filter = "130421"; // blank or null will work as ALL/no filter
//
//	    public static String o(String s)
//	    {
//	        if (filter == null || filter.trim().equals("") || s.contains(filter))
//	        {
//	            olast(s);
//	        }
//	    }
//
//	    public static String oNoFilter(String s)
//	    {
//	        olast(s);
//	    }
//
//	    public static String olast(String s)
//	    {
//	        if (s == null)
//	            return null;
//	        //if (!s.contains("FileLine from IMAPMessage"))
//	        //if (UtilMailSearch.ofilter != null && !s.contains(UtilMailSearch.ofilter)) return s;
//	        long now = System.currentTimeMillis();
//
//	        // break every x seconds
//	        if ((now - lastms) > 3000)
//	            oConsoleAndFileAsNeeded ("\r\n**********************************"+ (new java.util.Date().toString()));
//
//	        oConsoleAndFileAsNeeded ("" + callCntO++ + ". ms:" + (now-lastms) + " s [" + s + "]");
//	        lastms = now;
//
//	        return s;
//	    }
//
//
//	    public static String o(String desc, Object ox)
//	    {
//	        String s = "odesc:" + desc + "o.toString() [" + ox.toString() + "]";
//	        O.o (s);
//	    }
//
//	    public static String o(String desc, int i)
//	    {
//	        String s = "oint desc:" + desc + "-> i:" + i;
//	        O.o (s);
//	    }
//
//
//		//	    public static String op (String s)
//		//	    {
//		//	        UtilPerf.perfMsgsForUIAppend "<br> / " + s;
//		//	        o (s
//		//;	    }
//
//		//	    public static boolean isLoggingOn()
//		//	    {
//		//	        UtilFile.exists(fn);
//		//	    }
//
//	    public static String otime(String s)
//	    {
//	        O.o ("otime:" + new java.util.Date().toString()+ ":" + s);
//	    }
//
//		//	    public static of (String varName, String varValue)
//		//	    {
//		//	        varName " [" + varValue + "] "
//		//	    }
//
//	    private static void oConsoleAndFileAsNeeded(String s)
//	    {
//			//	        if (UtilFile.exists(fn))
//			//	        {
//			//	            UtilFile.write(fn, s+"\r\n", 10000000);
//			//	        }
//	        System.out.println(s + "\r\n")
//	    }
//
//	    public static void os(String s)
//	    {
//	        oConsoleAndFileAsNeeded(s); // no crlf
//	    }
//
//	    public static void os(String s, Object ox)
//	    {
//	        oConsoleAndFileAsNeeded(s + "[" + ox + "]" );
//	    }
//
//	    //	public static void oc(String desc, Object ox)
//	    //	{
//	    //		if (ox != null)
//	    //		{
//	    //			//if (o instanceof java.util.AbstractCollection) // get size if appropriate
//	    //			//	System.out.println ("ocdesc {" + desc +						"} sz {" + ((java.util.AbstractCollection) o).size() + "} clsnm {" + o. getClass().getName() +					"} toStr {" + o.toString());
//	    //			//else {
//	    //			//System.out.println ("ocdesc {" + desc +						"} clsnm {" + o.getClass().getName() +						"} toStr {" + o.toString()			);
//	    //			//}
//	    //		o.getClass().toString() [" + o.getClass().toString() + "]");
//	    //		else
//	    //			System.out.println ("oc:varname [" + desc + "] OBJECT IS NULL");
//	    //	}
//	    //
//	    //
//
//	    public static void oc(Object o)
//	    {
//	        oc ("none specified", o)
//	    }
//
//		//	    public static void oc(String desc, Object o)
//		//	    {
//		//	        //t                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   3cs67g                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      o catch who's calling if (!desc.equals("will never equal this I bet!!"))
//		//	        //  throw new RuntimeException("in herehk");
//		//	        //O.o "ococococ:" + Thread.dumpStack()
//		//	        O.o ("Begin oc ["+ desc +  "]===========================")
//		//	        if (o != null)
//		//	            if (o instanceof java.util.AbstractCollection) // get size if appropriate
//		//	            {
//		//	                O.o ("ocdesc:AbsColl [${desc}] sz [${((java.util.AbstractCollection) o).size()}] \
//		//	                    clsnm [${o.getClass().getName()}] oc.absColl.toStr [${o.toString()}]");
//		//	                o.eachWithIndex {it, i ->
//		//	                    O.o (i.toString() + ". ociter_AC: " + it)
//		//	                }
//		//	            }
//		//	            else if (o instanceof java.util.Map) // get size if appropriate
//		//	            {
//		//	                O.o ("ocdesc:Map [${desc}] count [${o.size()}] clsnm [${o.getClass().getName()}");
//		//	                                o.keySet().eachWithIndex {it, i ->
//		//	                                    i++;
//		//	                                    O.o (i + ". ociter:Map [${desc}] key [${it}] val [${((java.util.Map)o).get(it)}]");
//		//	                                }
//		//	            }
//		//	            else
//		//	                O.o ("ocdesc:plain desc [${desc}] clsnm [${o.getClass().getName()} oc.plain toStr [${o.toString()}]"
//		//	                );
//		//	        //"] o.getClass().toString() [" + o.getClass().toString() + "]");
//		//	        else
//		//	            O.o ("oc:varname {" + desc + "} can't emit oc because OBJECT IS == NULL");
//		//
//		//	        O.o ("End oc ["+ desc +  "]===========================")
//		//	    }
//
//	    //	public static void oc(Object o)
//	    //	{
//	    //		oc ("no varname passed", o);
//	    //	}
//
//	    public static long elapsed(long msstart)
//	    {
//	        return System.currentTimeMillis()-msstart;
//	    }
//
//	    public static String or(String callerId, Throwable t)
//	    {
//	        oerr(callerId,t) // mirror
//	    }
//	    public static String oerr(String callerId, Throwable t)
//	    {
//	        O.oNoFilter("ERROR: O.OERR callerId [" + callerId +  "] t.getMessage() 9 <<" + t.getMessage() + ">> stackTraceToString [ " + getStackTraceToString(t, 2000) + "] end stackTraceToString");
//	        t.printStackTrace();
//	        return getStackTraceToString(t);
//	    }
//
//	    public static void oerrNoStack(String s, Throwable t)
//	    {
//	        O.o(s +  "orNoStack: O.or.1 t.getMessage() [" + t.getMessage() + "]");
//	    }
//
//
//	    public static String getStackTraceToString(Throwable e) {
//	        return getStackTraceToString(e, 500);
//	    }
//
	    public static String stackTraceToString(Throwable e, int maxOrAMillIfNegative) {
	        StringWriter s = new StringWriter();
	        e.printStackTrace(new PrintWriter(s));
	        String sss2 = s.toString();
	        //O.o ("sss2:" + sss2);
	        //O.ofmt ("sss2.length():", sss2.length());
	        if (maxOrAMillIfNegative < sss2.length()-1)
	        {
	        	System.err.println("stacktrace is truncated " );
	        	maxOrAMillIfNegative = 1000000;
	        }
	        return "[@@@@@@@@@@@@@@@@@@@@@@ " + sss2.substring(0,Math.min(maxOrAMillIfNegative, sss2.length()-1)) + " @@@@@@@@@@@@@@@@@@@@@]";
	    }
//
	    //	    public static String ofmt (String varname, Object ox)
//	    {
//	        return o(fmt(varname, ox));
//	    }
//
//	    public static String stringifyMap (String desc, org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap gpm)
//	    {
//	        O.o "hi there 130202a1.5"
//	        def map = new HashMap();
//	        gpm.keySet().each {
//	            map[it] = gpm[it]
//	        }
//	        return stringifyMap(desc, map)
//	    }
//
//	    public static stringifyEachable(desc, s)
//	    {
//	        String r = ""
//	        int i = 0
//	        s.each {
//	            i++;
//	            r = r + "\r\neachable desc [" + desc + "] #" + i + ". " + it.toString()
//	        }
//	        return r;
//	    }
//
//	    public static String stringifyMap (String desc, Map map)
//	    {
//	        def sb = new StringBuffer("stringifyMap desc:"+desc);
//	        map.keySet().eachWithIndex { key, i ->
//	            sb.append("\r\n   desc [" + desc + "] " + i + ". " + fmt(key, map[key]) );
//	        }
//	        sb.toString();
//	    }
//
//	    public static String fmt (String varname, Object ox)
//	    {
//	        String s = ", varname:[" + varname +"]=[" + ox + "]";
//	        return s;
//	    }
//
//	    public static String fmt2 (String varname, Object ox) // comma
//	    {
//	        String s = null;
//	        s = ", [" + varname +"]=[" + ox + "]";
//	        return s;
//	    }
//
//	    public static String fmt3 (String varname, Object ox) // newline
//	    {
//	        String s = null;
//	        s = "[" + varname +"]=[" + ox + "]\r\n";
//	        return s;
//	    }
//
//	    public static String oarr(String desc, String[] xxarr)
//	    {
//	        StringBuffer sb = new StringBuffer();
//	        sb.append "array emitter [${desc}] of num elements:" + xxarr.length
//
//	        xxarr.eachWithIndex  { it, i ->
//	            def s = "arr: " + i + ". " + it
//	            sb.append s
//	        }
//	        String sx = sb.toString();
//	        O.o "oarr:" + sx
//	        return sx;
//	    }
//
//	    public static void dumpstack()
//	    {
//	        // \. is just an example search below
//	        O.o "dumpstack:" + Thread.currentThread().getStackTrace().findAll {!(it.toString().contains("tonydeluca"))}.join("\r\nhbk1     ")
//	    }
//
//
//	    public static long ms ()
//	    {
//	        return System.currentTimeMillis();
//	    }
//
//
//	    //	public static String oredisSortedSet (String desc, Jedis jedis, String key)
//	    //	{
//	    //		return "REDIS: sortedSet cnt [" +jedis.zcard(key) + "] tostring [" + jedis.zrange(key, 0, 100000000).toString() + "]";
//	    //	}
//	    public static long tc() // time current
//	    {
//	        return System.currentTimeMillis();
//	    }
//	    public static long tmsince(long tm)
//	    {
//	        return O.tc()-tm;
//	    }
//	    public static long now()
//	    {
//	        return UtilPerf.now();
//	    }
//
//
//	    public static String formatList(String desc, List l)
//	    {
//	        int i = 0;
//	        StringBuffer sb = new StringBuffer();
//	        sb.append "\r\n"
//	        l.each {
//	            sb.append	((i=++i).toString() +
//	                    ". " +
//	                    desc + ": " +
//	                    it.toString() +
//	                    "\r\n"
//	            );
//	        }
//	        return sb.toString();
//	    }
//
//	    // assert - see UtilAssert
//

	}

	
