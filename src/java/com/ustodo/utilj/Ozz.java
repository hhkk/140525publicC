package com.ustodo.utilj;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import com.ustodo.utilg.UtilPerf;

//import redis.clients.jedis.Jedis;

public class Ozz {

	private static long lastms = 0;

	static int i = 0;

	
	public static void ov(String s)  // for HTML callers - no echo of a log statement 
	{
		o(s);
		return;
	}

	public static String o(String desc, Object o)
	{
		return o ("odesc obj obj :" + desc + ":" + o.toString());
	}
	
	public static String o(String desc, String s)
	{
		return o ("odesc:" + desc + ":" + s);
	}
			
	
	
	
	private static int callCntO=0;
	public static String o(String s)
	{
		long now = System.currentTimeMillis();
		if ((now - lastms) > 3000)
			System.out.println ("\r\n\r\n"+ (new java.util.Date().toString())+ " **********************************");
		lastms = now;
		System.out.println ("\r\n" + callCntO++ + ". " + s);
		return s;
	}
	
	// output simple = os
	public static void os(String s) {
		System.out.print(s);
	}
	public static void os(String s, Object o) {
		System.out.print(s + "[" + o + "]" );
	}
	
	// output class 
	public static void oc(String desc, Object o)
	{
		if (o != null)
			if (o instanceof java.util.AbstractCollection) // get size if appropriate
				System.out.println ("ocdesc <" + desc + 
						"> sz <" + ((java.util.AbstractCollection) o).size() + "> clsnm <" + o.getClass().getName() +
						"> toStr <" + o.toString() 
						); 
			else	
				System.out.println ("ocdesc <" + desc + 
					"> clsnm <" + o.getClass().getName() +
					"> toStr <" + o.toString() 
					); 
					//"] o.getClass().toString() [" + o.getClass().toString() + "]");
		else
			System.out.println ("oc:varname [" + desc + "] OBJECT IS NULL");
	}

	
	public static long elapsed(long msstart) 
	{
		return System.currentTimeMillis()-msstart;
	}
	
	public static void or(String s, Throwable t)
	{
		Ozz.o(s +  " [" + t.getMessage() + "]");
		String x = getStackTraceToString(t, 2000);
		//O.o("xxxxxxxxxxxxxxxxxxxxxxxxxx len:" + x.length());
		Ozz.o(s +  " 2001 [ " + x + "] 2001");
		Ozz.o("x.lrn:"+x.length());
		//return s +  " [" + t.getMessage() + "] 2000 [ " + getStackTraceToString(t, 300) + "] 2000";  
	}
	
	
	public static String getStackTraceToString(Throwable e) {
		return getStackTraceToString(e, 500);
	}
	
	public static String getStackTraceToString(Throwable e, int max) { 
		StringWriter s = new StringWriter(); 
		e.printStackTrace(new PrintWriter(s));
		String sss2 = s.toString();
		//O.o ("sss2:" + sss2);
		//O.ofmt ("sss2.length():", sss2.length());
		return "[@@@@@@@@@@@@@@@@@@@@@@ " + sss2.substring(0,Math.min(max, sss2.length()-1)) + " @@@@@@@@@@@@@@@@@@@@@]"; 
	}

	// fmt = format (a variable) 
	public static String ofmt (String varname, Object o)
	{
		return o(fmt(varname, o));
	}
	
	public static String fmt (String varname, Object o)
	{
		String s = ", fmt: " + varname +"=[" + o + "]";
		return s;	
	}
	
	public static long ms ()
	{
		return System.currentTimeMillis();
	}

	
//	public static String oredisSortedSet (String desc, Jedis jedis, String key)
//	{
//		return "REDIS: sortedSet cnt [" +jedis.zcard(key) + "] tostring [" + jedis.zrange(key, 0, 100000000).toString() + "]";
//	}
	public static long tm()
	{
		return System.currentTimeMillis();
	}
	public static long tmsince(long tm)
	{
		return tm()-tm;
	}
	public static long now()
	{
		return UtilPerf.now();
	}

//	// mongo / json objects out 
//	public static String odbo("query result each:", Dbo it, boolean emit)
//	{
//		
//	}

	
	
}
