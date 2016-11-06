package com.ustodo.utilg

import com.mongodb.BasicDBObject

class UtilUtd_DBExportToFile {
	// see also UtilMongo_File_Email_Db



	public static void main (String[] args)
	{
		try
		{
            exportToFileAllForUser("ckckck");
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

    public static void exportToFileAllForUser(String username)
    {
        exportToFile_Favs(username);
    }

    public static String exportToFile_Favs(String username)
	{
		//UtilFile.addLineToFileTrunc(Cfg.fnFavsCkckckExport, "start export " + new java.util.Date() + "\r\n")
        String collname = "favs"+username;
        String filename = "/tmp/tDBexportFavs_"+collname+".csv";
		BasicDBObject dbo = new BasicDBObject ();


        if (true)
        {
            // timing check
            Profiler.check("start coll iter")
            UtilMongo.getCur(collname, dbo).
                    eachWithIndex  { it, i ->
                if (i % 5000 == 0)
                {
                    O.o "coll record i:" + i
                }
            }
            Profiler.check("done coll iter")

            // first iteraction
            UtilMongo.getCur(collname, dbo).
                    eachWithIndex  { it, i ->
                if (i % 5000 == 0)
                {
                    O.o "iter 1 test coll record i:" + i
                }
            }
            Profiler.check("done coll iter 2 with no writes to see raw iter time")
            // timing check
        }


		UtilMongo.getColl(collname).ensureIndex("date")

		BasicDBObject dboorder = new BasicDBObject();
		dboorder.put("date", 1);

		// second iteraction
		int iLinesWritten = 0;
		UtilMongo.getCur(collname, dbo).sort(dboorder).each {
			UtilFile.addLineToFileAppend(filename, it.filelineraw + "\r\n")
			iLinesWritten
		}
		O.o "iLinesWritten:" + iLinesWritten;
        O.o "collname:" + collname
		O.o "collcount:" + UtilMongo.getCollFavs(username).count();
		O.o "done export to file:" + collname


	}

}
