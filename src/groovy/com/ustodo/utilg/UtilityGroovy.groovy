package com.ustodo.utilg;

import java.util.Calendar;

import java.sql.Date;


public class UtilityGroovy {

	public UtilityGroovy(int fileLineNumThisLine1Based, String fileLineRaw)
	{
		try {
		} catch ( Exception e) {
			println ("error constructing fileline from:" + fileLineRaw)
		}
		//O.o ("idx:" + i + ". FileLine: date [" + date + "] line [" + line + "]");
	}

	public static void main (String[] args )
	{
		UtilityGroovy fl = new UtilityGroovy (1, "123")
	}

}