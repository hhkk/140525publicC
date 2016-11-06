package com.ustodoFromV.util;

public class UtilString {
	
	public static String getAllAfterLast(String s, String last)
	{
		return s.substring(s.lastIndexOf(last)+1);
	}
	
	public static String keepAllBeforeFirstOfThis (String s, String delim)
	{
		int i = s.indexOf(delim);
		return s.substring(0, i);
	}
	

}
