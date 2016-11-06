package com.ustodo.utilg

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.DBCursor

class UtilUtd_Diff_fileDb {
	// see also UtilMongo_File_Email_Db



	public static boolean compareFileAndDBSizeOnly (String fn, String namedb, String namecoll)
	{
		try
		{
			def a = UtilMongo.getCollCountByName("test2ValidateDBSizeVSFileSize", namedb, namecoll)
			def b = UtilFile.fileAsList(fn).size()
			if (a == b)
				O.o "SUCCESS file and db same size "
			else
			{
				O.o "FAIL  file and db diff size file " + b + " db:" + a + " diff:" + (b-a)
			}

			return a == b;
		}
		catch (Throwable t )
		{
			O.or("t", t);
			throw t;
		} finally {
			O.o "done compare db and file  *************************:" + namedb + ":" + namecoll
			assert (true)
		}
	}


	public static void diff_FileToColl_viaFileColl(String f, String collNameTgt, boolean dirfwdTrueBackFalse, int numSkipSource)
	{
		//TURN FILE TO COLL
		String tempcollname_favsckckckTempViaFLR = "favsckckckTempViaFLR";
		UtilFileLineRaw.insertFileIntoColl_ViaFileLineRaw(f, tempcollname_favsckckckTempViaFLR)

		// DIFF COLLS
		if (dirfwdTrueBackFalse)
		{
			DBCollection collsrc = UtilMongo.getColl(tempcollname_favsckckckTempViaFLR);
			DBCollection colltgt = UtilMongo.getColl(collNameTgt);
			diff_Colls_OneWay ( collsrc, colltgt, numSkipSource)
		}
		else
			diff_Colls_OneWay ( UtilMongo.getColl(collNameTgt),  UtilMongo.getColl(tempcollname_favsckckckTempViaFLR),   numSkipSource)

	}


	public static void diff_FileToColl_WoFileColl (String fn, DBCollection colltgt, int numSkipSource)
	{
		// DIFF COLLS
			diff_Enumerable_ToColl_OneWay ( UtilFile.getFileAsList(fn), fn, colltgt, numSkipSource)
	}


	private static void diff_FileToColl_OneWay (String fn, DBCollection collTarget, int numSkipSource)
	{
		diff_Enumerable_ToColl_OneWay (UtilFile.getFileAsList(fn), fn, collTarget, numSkipSource)
	}
	
	private static void diff_Colls_OneWay (DBCollection collSource, DBCollection collTarget, int numSkipSource)
	{
		diff_Enumerable_ToColl_OneWay (UtilMongo.getCollAsArrayFLR(collSource), collSource.getName(), collTarget, numSkipSource);
	}

	// oWeCanEachOn may have come from file or DB
	private static void diff_Enumerable_ToColl_OneWay (Object oWeCanEachOn, String sourceDesc, DBCollection collTarget, int numSkipSource)
	{
		O.o("diffing sourceDesc:" + sourceDesc + " vs " + collTarget.getName())
		int matchyes = 0;
		int matchno = 0;
		int dupCnt = 0;

		String[] sArrSourceRecsNotFound = [];
		int dupExampleOutputModCnt = 0

		String filelineraw_source = null;

		try {

			int mod = 1000;
			int i = -1;
			//while (dbcursor_src.hasNext())
			Profiler.start("diff_Colls_OneWay");
			oWeCanEachOn.each
			{ // for each record in coll2
				i++
				//BasicDBObject dbosrc = dbcursor_src.next()
				if (it instanceof BasicDBObject)
				{
					BasicDBObject dbosrc = it;
					filelineraw_source = dbosrc.filelineraw.toString().trim();
				}
				else if (it instanceof String)
				{
					filelineraw_source = it;
				}
				else 
				{
					O.oc "it_345:" , it;
					throw new Exception ("unknown obj type");
				}

				boolean output = false;
				if (i % mod == 0)
					output=true;
				if (i > 5000)
					mod = 5000
						
				if (output)
				{
					int totalImplied = numSkipSource + i;
					Profiler.check("diff_Colls_OneWay:src [${sourceDesc}] tgt [${collTarget.getName()}] " +
							" skip [${numSkipSource}] " +
							"i:" + i + " (totalImplied=[${totalImplied}]) " +
							",  matchyes:"+matchyes + ", matchno:"+matchno + ", dupCnt:"+dupCnt);
							
				}
				//O.oc("it", it)
				BasicDBObject dboquery= new BasicDBObject(); // filelineraw compare
				dboquery.put("filelineraw", filelineraw_source)
				//O.ofmt("filelineraw", filelineraw)
				DBCursor dbcursor_tgt = collTarget.find(dboquery);

				//O.o("filelineraw diff test [" + filelineraw + "]")

				boolean foundone = false;
				int howmanyfoundForThatSourceRecord = 0;
				while (dbcursor_tgt.hasNext())
				{ // for each record in coll2
					BasicDBObject dbotgt = dbcursor_tgt.next()
					foundone = true
					howmanyfoundForThatSourceRecord++
					//if (output)
					//O.o "dbcursor.numSeen()", dbcursor_tgt.numSeen()

				}
				//			dbcursor.each  { // for each record in coll2
				//				foundone = true
				//				howmanyfoundForThatSourceRecord++
				//				if (output)
				//					O.o "dbcursor.numSeen()", dbcursor.numSeen()
				//
				//			}
				if (foundone)	
					matchyes++;
				else
				{
					matchno++;
					O.o("missing (#[${matchno}]) from diffing sourceDesc:" + sourceDesc + " vs " + collTarget.getName() +  ":" + filelineraw_source )
					//sArrSourceRecsNotFound << filelineraw

				}

				// were there dups?
				if (howmanyfoundForThatSourceRecord > 1){
					//O.o "dups in target found, howmanyfound "+  howmanyfoundForThatSourceRecord + "<<<" + filelineraw +  ">>>"
					dupCnt++;

				}
				//O.o "dup:" + howmanyfoundForThatSourceRecord + "[" + filelineraw + "]"
			} // each
			if (i < 5)
			{
				throw new RuntimeException("not enough checked")
			}
			Profiler.check ("DONE coll compare one way source [${sourceDesc}] " +
					", i:" + i +
					", numSkipSource [${numSkipSource}] target coll [${collTarget.getName()}]" +
					",  matchyes:"+matchyes +
					", matchno:"+matchno +
					", dupCnt:"+dupCnt, true);
			O.oarr "sArrSourceRecsNotFound",sArrSourceRecsNotFound
		} catch (Throwable t) {
			O.or "Fail while or near seeking  [${filelineraw_source}]", t
			//throw t
		}
		finally {
			O.o "done"
		}

	}
	
	
}
