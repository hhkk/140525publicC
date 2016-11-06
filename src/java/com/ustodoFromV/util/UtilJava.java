package com.ustodoFromV.util;

public class UtilJava {
	
	public static String getObjectId(Object o)
	{
		return Integer.toHexString(System.identityHashCode(o));
	}
}
