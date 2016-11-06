package com.ustodo.utilg


class UtilUtd_DBgetTags {
	// see also UtilMongo_File_Email_Db


	public static void main (String[] args)
	{
		try
		{
			//doWorkGetTags();
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

//	public static String doWorkGetTags()
//	{
//		UtilFile.addLineToFileTrunc(Cfg.fnFavsCkckckExport, "start export " + new java.util.Date() + "\r\n")
//
//		BasicDBObject dbo = new BasicDBObject ();
//		UtilMongo.getCur(Cfg.collnameFavsCkckck, dbo).eachWithIndex  { it, i ->
//			String fltxt  = it.filelinerawdate + " " + it.filelinerawnodate;
//			O.o("fltxt:" + fltxt)
//
//			//			String fldate = it.filelinerawdate;
//			//			//if (fldate.compareOTo("2011") > 0)
//			if (i > 25000)
//			{
//				//O.o("\r\nfltxt1:" + fltxt)
//				int endtags = fltxt.lastIndexOf(" / ")
//				if (endtags > 0)
//				{
//					// tagOrAll levels
//					// 1 full string minus the last/anchor
//					// 2 as tokenized by " / "
//					// 2 words within tags
//
//					// date
//					O.o("\r\nfltxt2:" + fltxt)
//					TreeSet ts = new TreeSet();
//					fltxt.split(" / ")[-2..0].eachWithIndex { it2, i2 ->
//						ts.add(it2.trim())
//					}
//					O.o("added tagOrAll [${it2.trim()}]")
//				}
//
//				//				O.o("tags [${v}] from flt [${fltxt}]")
//				//			}
//			}
//
//
//		}
//
//
//	}
}
