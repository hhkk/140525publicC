package com.ustodo.utilg;

import java.util.Calendar;

import java.sql.Date;


public class HKFileLine {
	String lineMinusDateStr
	String date
	int fileLineNumThisLine1Based
	String ageLetter

	public HKFileLine(int fileLineNumThisLine1Based, String fileLineRaw)
	{
		try {
			this.fileLineNumThisLine1Based = fileLineNumThisLine1Based
			date = fileLineRaw[0..18]
			lineMinusDateStr = fileLineRaw[19..-1]
			ageLetter = UtilDate.renderAgeAsLetterFromNowToFileDateStr (date)
		} catch ( Exception e) {
			println ("error constructing fileline from:" + fileLineRaw)
		}
		//O.o ("idx:" + i + ". FileLine: date [" + date + "] line [" + line + "]");
	}

	public static void main (String[] args )
	{
		HKFileLine fl = new HKFileLine (1, "123")
	}

}