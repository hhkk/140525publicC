package com.ustodo.utilg

import com.mongodb.DBCollection
import com.mongodb.DBObject

class UtilDiffTest {

	private static boolean loadedConfig = Cfg.configSlurpDoIt();

	public static void diffTests()
	{

		// from Cfg.groovy
		//public static String dbname = "livetest";
		//public static String collnameUnitFavs = "favsckckck"
		//public static String fn = "/Users/hkon/sw/ustodo/favsckckck.csv";



		try
		{
			Profiler.check("Top of diff")
			// compare counts
			// 1/4
			//			if (false)
			//				UtilUtd_Diff_fileDb.compareFileAndDBSizeOnly(fn, Cfg.dbname, Cfg.collnameFavsCkckck);
			//
			//			// 2/4
			//			if (false) // file lines without intermediate coll
			//			{
			//				Profiler.check("Start for each file - sourced from file itself to new coll")
			//				DBCollection colltgt = UtilMongo.getColl(Cfg.collnameFavsCkckck);
			//				UtilMongo.indexEnsure(colltgt, "filelineraw")
			//				UtilUtd_Diff_fileDb.diff_FileToColl_WoFileColl(fn, colltgt, 0 )
			//				Profiler.check("End for each file")
			//			}
			//			// 3/4
			//			// DIRECTION 1
			//			// per file line, check DB
			//			boolean loadedFileToColl = false;

			if (true) // 3.1/4
			{
				String tempcollname_RealOrNot = Cfg.collnameFavsCkckck;
				String fn = Cfg.fnFavsCkckck;
				//String fn = Cfg.fnFavsCkckckSmall;
				O.o "working with file fn:" + fn
				O.o "working with coll tempcollname_RealOrNot:" + tempcollname_RealOrNot
				//String tempcollname_RealOrNot = "favsckckckTempViaFLR";

				// 3.1.1 put file in coll
				DBCollection collsrc = null;
				//collsrc = UtilMongo.getCollFreshCappedIndexOption(tempcollname_RealOrNot, true)
				collsrc = UtilMongo.getCollFreshCappedNot(tempcollname_RealOrNot)
				UtilFileLineRaw.insertFileIntoColl_ViaFileLineRaw(fn, collsrc)

				// 3.1.2 save file in hm <str -> int>
				HashMap hm = UtilFile.getFileAsHmStrToInt(fn);
				String[] fileAsStrArr = UtilFile.getFileAsStrArray(fn)

				// 3.1.3 LEFT TO RIGHT iterate file hm - lookup in db
				DBCollection colltgt = UtilMongo.getColl(tempcollname_RealOrNot)
				//UtilMongo.indexEnsure(colltgt, "filelineraw")
				//hm.keySet().asList().eachWithIndex {it, i ->
				fileAsStrArr.eachWithIndex {it, i ->
					//O.oc "it:", it
					//if (it.contains("indrawebprod14.netreach.net"))

					if (it.toString().trim().equals(""))
						O.o "blankline on hm iteration rec 0b#:[${i}] skip validation"
					else
					{
						//O.o "confirm rec 0b#:[${i}] it:[${it}]"

						String[] sa = UtilMongo.getRecsFileLineRawEqual_StrArr(colltgt, it);
						if (sa.length != 1)
						{

							O.o "LtoR, #0b:[${i}] confirmed not found DB has file record [${it}]"
							throw new RuntimeException("found sa.length:[${sa.length}] <> 1 file on rec 0b#:[${i}], sa.len:[${sa.length}] on rec [${it}]");
						}
						else
						{
							if (i % 1000 == 0)
								O.o "confirmed phase 2 #0b[${i}] DB has file record [${it}]"
						}
					}
				}

				// 3.1.4 RIGHT TO LEFT iterate coll - confirm existence in hm
				collsrc.find().eachWithIndex  { it, i ->
					if (hm.get(it.filelineraw) == null)
						throw new RuntimeException("missing from hm");
					//O.o "found hm.get(it.filelineraw):" + hm.get(it.filelineraw) + ", " + it.filelineraw
					// confirm order in = order out
					Integer integerPosInFile = hm.get(it.filelineraw);
					assert (integerPosInFile != null)
					O.o "confirmed #0b[${i}] file has db record [${it.filelineraw}]"
					//assert ( i == integerPosInFile.intValue())
					// throw new RuntimeException("wrong ints");
				}

				// 3.1.5 now try to iterate the db coll by date
				//DBCollection collsrcRebuildfile = UtilMongo.getColl(tempcollname_RealOrNot)


				// 3.1.6 now save the coll to a file - time orders




				O.o "SUCCESS"



			}// file to coll
			//			{
			//				// file to temp coll
			//				String tempcollname_favsckckckTempViaFLR = "favsckckckTempViaFLR";
			//				loadedFileToColl = true;
			//				HashMap hm = new HashMap();
			//				DBCollection collsrc = UtilFileLineRaw.insertFileIntoColl_ViaFileLineRaw(fn, tempcollname_favsckckckTempViaFLR)
			//				String[] sa = UtilFile.getFileAsStrArray(fn);
			//				// confirm order in = order out
			//				for (int i in (0..sa.length-1))
			//					hm.put(sa[i], i)
			//				// confirm order in = order out
			//				Object curRsltSrc = collsrc.find();
			//				curRsltSrc.eachWithIndex  { it, i ->
			//					if (hm.get(it.filelineraw) == null)
			//						throw new RuntimeException("missing int");
			//					O.oc "hm.get(it.filelineraw):", hm.get(it.filelineraw)
			//					// confirm order in = order out
			//					Integer integer = hm.get(it.filelineraw);
			//					O.o "i:" + i + ", " + integer.intValue().toString()
			//					assert ( i == integer.intValue())
			//						// throw new RuntimeException("wrong ints");
			//				}
			//
			//				// reset for next compare
			//				DBCollection collsrc2_tempcollname_favsckckckTempViaFLR =
			//					UtilMongo.getColl(tempcollname_favsckckckTempViaFLR);
			//				Object curRsltSrc2_tempcollname_favsckckckTempViaFLR = collsrc2_tempcollname_favsckckckTempViaFLR.find();
			//				Profiler.check("Start for each file rec")
			//				//DBCollection collsrc = UtilMongo.getColl(tempcollname_favsckckckTempViaFLR, true); // drop true
			//				DBCollection colltgt = UtilMongo.getColl(Cfg.collnameFavsCkckck);
			//				UtilMongo.indexEnsure(colltgt, "filelineraw")
			//				O.oc("o2", curRsltSrc2_tempcollname_favsckckckTempViaFLR)
			//
			//				UtilUtd_Diff_fileDb.diff_Enumerable_ToColl_OneWay (curRsltSrc2_tempcollname_favsckckckTempViaFLR, "from coll:" + collsrc.getName(),  colltgt, 0)
			//				Profiler.check("End for each file")
			//			}

			// 3.5 pending - use hashtable for DB coll contents - map back with OID - test perf

			// 4/4
			// DIRECTION 2
			// per livetest.favsckckck DB record
			//			if (false)
			//			{
			//				// file to temp coll
			//				String tempcollname_favsckckckTempViaFLR = "favsckckckTempViaFLR";
			//				if (!loadedFileToColl)
			//					UtilFileLineRaw.insertFileIntoColl_ViaFileLineRaw(fn, tempcollname_favsckckckTempViaFLR)
			//				List listSrc = UtilMongo.getCollAsStrListFLR(Cfg.collnameFavsCkckck);
			//				DBCollection colltgt = UtilMongo.getColl(tempcollname_favsckckckTempViaFLR);
			//				UtilUtd_Diff_fileDb.diff_Enumerable_ToColl_OneWay ( listSrc, colltgt, 0)
			//			}


			//UtilUtd_Diff_fileDb.diff_FileToColl_viaFileColl(Cfg.fnFavsCkckck, Cfg.collnameFavsCkckck,false, 0) // num to skip - 0 missing to 34600 on 111111
			// OTHER DIR
			//UtilUtd_Diff_fileDb.diff_FileToColl_viaFileColl(Cfg.fnFavsCkckck, Cfg.collnameFavsCkckck,true, 0) // num to skip - 0 missing to 34600 on 111111
			//UtilMongo

		} catch (Throwable t )
		{
			O.or("t.getMessage() 1 [" + t.getMessage( ) + "]", t);
			throw t;
		} finally {
			O.o "done Mongo utildifftest ************************* namedb:" + Cfg.dbname
			assert (true)
		}
	}

	public static void main (String[] args)
	{
		UtilDiffTest.diffTests();
	}



}


