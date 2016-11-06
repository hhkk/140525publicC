package com.ustodo.utilg

class UtilThread {
	
	private static callcount = 0;

	public static String getDumpStackStr()
	{
		callcount++
		def sb = new StringBuffer("getDumpStackStr");
		int i = 0;
		for( StackTraceElement ste : Thread.currentThread().getStackTrace() ) {
			i++;
			O.o( "DUMPSTACK: " +callcount+"." + i + ". " + ste + "\n" );
		}
	}


}
