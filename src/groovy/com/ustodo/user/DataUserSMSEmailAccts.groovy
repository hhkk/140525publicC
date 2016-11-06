package com.ustodo.user;

import java.util.Map;
import com.mongodb.DBCursor;
import com.ustodo.utilg.UtilMongo;
import com.ustodo.utilg.O;
import com.ustodo.utilg.UtilJson;
import com.ustodo.utilg.UtilMongo;

public class DataUserSMSEmailAccts 	{


	private static String collNameDataUserSMSEmailAccts = "collDataUserSMSEmailAccts";




	public static Vector getVecAcctsThisUser(String username)
	{
		//		public static DBCursor select(String 1 collname, String 2 fieldToSrch, String 3 firstWordInSearch, String 4 orderfield, int 5 orderdir, int 6 limit,
		//			Map 7 maputdoptions, boolean 8 MODE_SEARCH_STAR)
		def cur = UtilMongo.select (
			"favs" + username, // collname
			"filelineraw", // field
			"utddata", // this is an email search of some kind for config data
			"date",
			0,
			0,
			null,
			null)
		//UtilFile.createEmptyFileWithDateDeleteIfNeeded("/tmp/ttd.txt")

//		int i2	 = -1;
//		while (cur.hasNext()) // records with utddata
//		{
//			i2++
//			def dbo = cur.next();
//
//			def dboText = dbo.text.toString().trim();
//			def textCat = UtilStr.keepAllBeforeLastOfThis(dboText, " / ")
//			String fileLineRaw_orFileLineRawCatOnly_reconstituted = null;
//
//			if (iCntDBLinesMatch > 200 && (MODE_SEARCH_STAR || MODE_SEARCH_STAR2))
//			{
//				O.o "got y'all to cap!!!!!"
//				break;
//			}
//
//			if (MODE_SEARCH_STAR2)
//				fileLineRaw_orFileLineRawCatOnly_reconstituted = dbo.date.toString().trim() + " " + textCat + "/ C";
//			else
//				fileLineRaw_orFileLineRawCatOnly_reconstituted = dbo.date.toString().trim() + " " + dboText;
//
//			def gotFLR = null;
//			if (!MODE_SEARCH_STAR2)
//			{
//				if (MODE_SEARCH_STAR || UtilSearch.match(fileLineRaw_orFileLineRawCatOnly_reconstituted, rtnSearchNow))
//				{
//					gotFLR = fileLineRaw_orFileLineRawCatOnly_reconstituted
//				}
//			} else // MODE_SEARCH_STAR2 want first time cats only
//			{
//				if (!hs_MODE_SEARCH_STAR2.contains(textCat))
//				{
//					hs_MODE_SEARCH_STAR2.add(textCat)
//					gotFLR = fileLineRaw_orFileLineRawCatOnly_reconstituted;
//				}
//			}
//
//
//			// for user ckckck, cap * mode at 1000
//			if (gotFLR != null)
//			{
//				//UtilFile.writeAppend("/tmp/ttd.txt", gotFLR + "\r\n")
//				iCntDBLinesMatch++;
//				String fileLineRawURL = null;
//				fileLineRawURL = UtilURL.compileLinksToHREFs (fileLineRaw_orFileLineRawCatOnly_reconstituted);
//				if (fileLineRawURL != null)
//					fileLineRaw_orFileLineRawCatOnly_reconstituted = fileLineRawURL;
//				//O.o("add:" + " ( ================ from DB oldway[${oldway}] [" + i + "] " + fileLineRaw)
//				fileLineRaw_orFileLineRawCatOnly_reconstituted = procNC(params, fileLineRaw_orFileLineRawCatOnly_reconstituted)
//
//				alReadLinesDbOnly.add (new FileLine(i2, fileLineRaw_orFileLineRawCatOnly_reconstituted));
//			}
//
//			//O.o "reverse 4"
//			//o("fileLineRaw: "+fileLineRaw);
//		}

		def v = UtilMongo.convertCurToVec(cur)




	}

	public static void main(String[] args)
	{
		try
		{
			O.o "ASDSAD"
			throw new Exception( "no error")
		} catch (Throwable t) {
			O.oerr "end:", t
		}
		finally {
			O.o "done"
		}
	}


}
