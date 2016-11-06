	package com.ustodo.utilg

import com.ustodo.utilg.O;

class UtilPerf {
	private static StringBuffer perfMsgsForUI = null;
	public static void perfMsgsForUIAppend (String s)
	{
		perfMsgsForUI.append	 s;
	}

	public static void perfMsgsForUIClear ()
	{
		perfMsgsForUI = new StringBuffer("perfMsgsForUI");
	}

	public static String getPerfMsgsForUI()
	{
		perfMsgsForUI.toString();
	}


	
	

	public static long start()
	{
		return System.currentTimeMillis();
	}
	
	public static long now()
	{
		return start();
	}
	
	public static long msSince(long ms)
	{
		return System.currentTimeMillis()-ms;
	}
	public static long timeMsSince(long ms)
	{
		return msSince(ms)	
	}

	public static long msSinceO(long ms, String desc)
	{
		O.o("This took ms [" + msSince(ms).toString() + "] desc [" + desc + "]");
	}

    public static String timeSince(Date d, String desc)
    {
        def elapsed = (new Date()).getTime() - d.getTime();
        return ("This took ms [" + elapsed + "] desc [" + desc + "]");

    }
}
