package com.ustodo.utilg;

import java.util.Calendar;

import java.sql.Date;

import com.google.code.com.sun.mail.imap.IMAPMessage;
import com.ustodo.utilg.mail.UtilMailHelper;


public class FileLine {
	String lineMinusDateStr
	private String filelinerawwhole
	String dbid;
	String filelinerawwhole_HTML
	public String datestr
	int fileLineNumThisLine1Based
	String ageLetter
	def dboDbFlr

    // used constructor from cursor loop on db reads in
    public FileLine (int fileLineNumThisLine1Based, String fileLineRaw, org.bson.types.ObjectId dbid, def dboDbFlr_)  // 1 of 2 FILE LINE INPUT
    {
        //O.o ("creating fileline id.toString():" + dbid.toString());

        try {
            this.fileLineNumThisLine1Based = fileLineNumThisLine1Based
            datestr = fileLineRaw[0..18]
            filelinerawwhole = fileLineRaw
            this.dbid = dbid.toString();
            lineMinusDateStr = fileLineRaw[19..-1]
            dboDbFlr = dboDbFlr_ // .html  .text
            common();
        } catch ( Exception e) {
            O.oerr("error constructing fileline 2 from imapmsg", e)
        }
        //O.o ("idx:" + i + ". FileLine: date [" + date + "] line [" + line + "]");
    }

    //constructor (may not be used as of 130519)
	public FileLine(int index, IMAPMessage im) // 1 of 2 IMAP
	{
		try {
			this.fileLineNumThisLine1Based = index;

			datestr = UtilDate.getDateLikeFileFormatFromUtilDate(im.getReceivedDate())
			lineMinusDateStr = 	UtilMailHelper.cvtImapMsgToStrShort(im, false)
			//filelinerawwhole = "WHOLELINEFROM_FILELINE_LINE32:" + datestr + "  " + lineMinusDateStr
			filelinerawwhole = datestr + "  " + lineMinusDateStr
            cmomon();
		} catch ( Exception e) {
			O.oerr("error constructing fileline 1 from imapmsg", e)
		}
		//O.o ("FileLine from IMAPMessage: idx:" + index + ". FileLine.toString() [" + toString() + "]");
	}



    public String toString()
    {
        String s =
            "  ageLetter:" + ageLetter +
                    ", datestr:" + datestr +
                    ", dbid:" + dbid +
                    ", lineNoDate:" + lineMinusDateStr
        return s;

    }



    public String emit(String desc, boolean emit)
    {
        String s = "$desc : lineNoDate <$lineMinusDateStr> datestr [$datestr]"
        if (emit)
            O.o(s)
        s
    }

    private void common()
    {
        ageLetter = UtilDate.renderAgeAsLetterFromNowToFileDateStr (datestr)
        if (lineMinusDateStr == null)
            throw new Exception ( "why is lineMinusDateStr null?")
    }

	public static int validateFileLine (String fileLine)
	{
		return 1;// fail - not implemented
	}


	public static void main (String[] args )
	{
		FileLine fl = new FileLine (1, "123")
	}

}
