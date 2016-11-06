package com.ustodo.utilg

import com.mongodb.BasicDBObject
import com.mongodb.DBObject

class UtilIndex {
	// see also UtilMongo_File_Email_Db


	public static void main (String[] args)
	{
		try
		{
			buildIndex_LastNPhraseSetsByDateDesc();
		}
		catch (Throwable t )
		{
			O.or("t", t);
			throw t;
			assert(false);
		} finally {
			O.o "done"
			assert (true)
		}


	} // main

	public static TreeSet buildIndex_LastNPhraseSetsByDateDesc()
	{
		// hm - hierarchy of words from 1 letters to 2 phonemes to 3 words to 4 phrases to 5 phrase set to 6 record
		// a flexible, intuitive, and novel framework for organizing information ranging from unstructured to semi to struct
		// integrating external applications in a unified user experience and interface
		// personal-cum-social - cum in the sense of same-together as well as individual unit of adoption

		HashMap hm = new HashMap();
		UtilFile.addLineToFileTrunc(Cfg.fnFavsCkckckExport, "start export " + new java.util.Date() + "\r\n")

		BasicDBObject dbo = new BasicDBObject ();
		UtilMongo.getCur(Cfg.collnameFavsCkckck, dbo).eachWithIndex  { it, i ->
			O.oc "it", it
			BasicDBObject dboflr = it;
			String fldate = dboflr.filelinerawdate
			String fltxt = dboflr.filelinerawnodate
			String fl  = fldate + " " + fltxt
			O.o("reconstructed fl:" + fl)
			Dbo dboflindex = new Dbo();
			dboflr.keySet().each {
				O.o("flrkey", it)
			}
			//dboflindex.put("flid", it.);
			//			String fldate = it.filelinerawdate;
			//			//if (fldate.compareOTo("2011") > 0)
			if (i > 25000)
			{
				//O.o("\r\nfltxt1:" + fltxt)
				int endtags = fltxt.lastIndexOf(" / ")
				if (endtags > 0)
				{
					// tagOrAll levels
					// 1 full string minus the last/anchor
					// 2 as tokenized by " / "
					// 2 words within tags

					// tagOrAll metadata
					// date
					O.o("\r\nfltxt2:" + fltxt)
					TreeSet ts = new TreeSet();
					fltxt.split(" / ")[-2..0].eachWithIndex { it2, i2 ->
						ts.add(it2.trim())
						O.o("added tagOrAll [${it2.trim()}]")
						ts
					}
					//O.o it
				}

				//				O.o("tags [${v}] from flt [${fltxt}]")
				//			}
			}


		}


	}
}
