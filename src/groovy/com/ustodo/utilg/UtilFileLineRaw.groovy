package com.ustodo.utilg

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection


class UtilFileLineRaw
// see also class UtilFileDBLoadCompare {
{
	private static BasicDBObject cvtFileLineRawToDbo (String filelineraw, int icnt)
	{
		BasicDBObject dboOuter= new BasicDBObject();
		//if (icnt <5)
		//	O.ofmt("+++++++++++++first few cvtFileLineRawToDbo() filelineraw", filelineraw)
		if (filelineraw.size()  < 20)
		{
			O.o "invalid line : kickout [" + filelineraw + "]"
			return null;
		}

		dboOuter.put("icnt", icnt);
		dboOuter.put("filelineraw", filelineraw[0..18] + " " + filelineraw[20..-1]);
		//dboOuter.put("filelinerawdate", filelineraw[0..18]); // 1998-05-06 10:00:00
		dboOuter.put("date", filelineraw[0..18]); // 1998-05-06 10:00:00
		//dboOuter.put("text", filelineraw[20..-1].replaceAll('^', ','));
		dboOuter.put("text", filelineraw[20..-1]);
		//		if (icnt % 1000 == 0)
		//			Profiler.check("line icnt:" + icnt + " inserted to dboOuter.toString() [" + dboOuter.toString() + "]", true);

		return dboOuter
		//		dbc.insert(dboOuter);
	}
	//	private static BasicDBObject cvtDBOToFileLineRaw (String filelineraw, int icnt)
	//	{
	//		BasicDBObject dboOuter= new BasicDBObject();
	//		//if (icnt <5)
	//		//	O.ofmt("+++++++++++++first few cvtFileLineRawToDbo() filelineraw", filelineraw)
	//		if (filelineraw.size()  < 20)
	//		{
	//			O.o "invalid line : kickout [" + filelineraw + "]"
	//			return null;
	//		}
	//
	//		dboOuter.put("icnt", icnt);s
	//		dboOuter.put("filelineraw", filelineraw);
	//		dboOuter.put("filelinerawdate", filelineraw[0..18]); // 1998-05-06 10:00:00
	//		if (filelineraw.size() < 19)
	//		{
	//
	//		}
	//		dboOuter.put("filelinerawnodate", filelineraw[20..-1]);
	//		//		if (icnt % 1000 == 0)
	//		//			Profiler.check("line icnt:" + icnt + " inserted to dboOuter.toString() [" + dboOuter.toString() + "]", true);
	//
	//		return dboOuter
	//		//		dbc.insert(dboOuter);
	//	}

	public static void emitbackOutADBMailObject (com.mongodb.BasicDBObject dbo)
	{
		UtilGroovyMetaObject.emitObj(dbo)
		O.o("emitbackOutADBMailObject:" + dbo.toString())
		//O.o("iiii:" + dbo.ReceivedDate)
		//dbcl
	}






	public static DBCollection w (String fn, DBCollection coll )
	{
		insertFileIntoColl_ViaFileLineRaw (fn, coll, true)
	}

	public static DBCollection insertFileIntoColl_ViaFileLineRaw (String fn, DBCollection coll, boolean dedup, boolean emptyCollFirst
    , boolean bIndexDate)
	{
		if (emptyCollFirst)
			UtilMongo.removeAll(coll)
		O.o "inserting file [${fn}] to coll [${coll.getName()}]"
		HashSet hs = new HashSet();
		def filStrLst = UtilFile.fileAsList(fn);
		try
		{

			Set hsstraightDateText = new HashSet();
			filStrLst.eachWithIndex { it, i ->
				BasicDBObject dboflr = UtilFileLineRaw.cvtFileLineRawToDbo (it, i)
				String datetext = dboflr.date + " " + dboflr.text;
				hsstraightDateText.add(datetext)
			}


			// insert file into a new collection
			int trackSkipRepeat = 0;
			int trackSuccess = 0;
			int trackfilesize = filStrLst.size();
			int trackAttemptedMin = filStrLst.size();


            // for each line in the file
			filStrLst.eachWithIndex { it, i ->
				BasicDBObject dboflr = UtilFileLineRaw.cvtFileLineRawToDbo (it, i)
				String dedupkey = dboflr.text;
				String dedupkeyDesc = "text"
				//				if (false)
				//				{
				//
				//					dedupkey = dboflr.filelineraw;
				//					dedupkeyDesc = "filelineraw incl date"
				//
				//				}
				//if (i > skipFirst)
				//{
				if (true) //  || limit <= 0 || ((i) < limit))
				{
					try {
						boolean output = ( i % 1000 == 0)
						//	O.o("file [${fn}] to coll [${collname}] interim. " + i + ". " + it)
						if (dedup)
						{
							String datetext = dboflr.date + " " + dboflr.text;
							String datetextNoHat = datetext.replaceAll('^', ',')
							boolean bhatcovered = datetext.indexOf('^') > 0 && hsstraightDateText.contains(datetextNoHat) != null;
							if (bhatcovered)
								O.o "bhatcovered:"  + datetext
							if (!hs.contains(dedupkey) && !bhatcovered)
							{ // changeme
								//if ( i < 10)
								//	O.o "first few hashset hs:" + it
								//O.o "in test mode!: - change:" + it
								// O.o "i+1:[(${i}+1)]prod14 [${it.size()}]:" + it
								hs.add(dedupkey)
								def cntpre = UtilMongo.getCollCnt(coll);
								coll.insert(dboflr)
								if (coll.count() != (cntpre+1)) // .. check added one
								{
									Rte.rte("db insert file? cntpre [${cntpre}] coll.count() [${coll.count()}]")
								}

								// TURN AROUND AND CONFIRM FILE REC GOT INTO THE DB
								def cur = coll.find(dboflr)
								int i2 = 0;
								cur.each { i2++ }
								if (i2 == 0)
								{
									String s = it;
									O.oc "it:", it
									//O.o "fail on immmediate find:" + it
									O.o "fail on immmediate find len:" + s.length()
									throw new RuntimeException("fail on immmediate find:" + it)
								}
								else
								{
									//if (i % 5000 == 0)
									//	O.o "SUCCEED on immmediate find #[${i}] :" + it

								}

								//if (i < 5)
								//if (output)
								trackSuccess++
								//O.o("inserted file to coll rec: #[${i}] in file [${fn}] to coll [${coll}] add loop:" + dbo.filelineraw)
								//if (insertcnt % 100 == 0)
								//O.o("insert rec0b# [${i}] to coll [${coll.getName()}] on insertcnt [${insertcnt}]  skipcnt [${skipcnt}] to DB [${it}]")

							}
							else
							{
								//if (output)
								trackSkipRepeat++;
								//if (trackSkipRepeat % 100 == 0)
								O.o("skip rec 0b#	 [${i}] on skipcnt [${trackSkipRepeat}] dedupkeyDesc [${dedupkeyDesc}] insert to DB [${it}] ")
							}
						} else
						{
							BasicDBObject dbo = UtilFileLineRaw.cvtFileLineRawToDbo (it, i)
							coll.insert(dbo)
							//if (i < 5)
							//if (output)
							trackSuccess++
						}
						//}
					} catch ( Exception e) {
						O.or("error in line index >>>>>>>>>>>>>>> [ ${i}] of [${fn}] line [${it}]", e)
					}
				} // if not past max wanted
			} // end loop

			int collcount = UtilMongo.getCollCnt(coll);
			//			O.o "done file to coll " + fn +
			//					"\r\n coll:" + coll.getName() +
			//					"\r\n coll:" + coll.count() +
			//					"\r\n file:" + trackfilesize +
			//					"\r\n attempted(incl min):" + trackAttemptedMin +
			//					"\r\n success:" + trackSuccess +
			//					"\r\n skip repeat:" + trackSkipRepeat +
			//					"\r\n to coll " + coll.getName() +
			//					"\r\n collcount " + collcount +
			//					"\r\n, FILECNT:" + filStrLst.size().toString() +
			//					" VS getCollCnt(coll):" + UtilMongo.getCollCnt(coll).toString()


            if (bIndexDate)
                UtilMongo.ensureIndex(coll, "date")
            O.o "****** completed index"

			O.o "done file to coll " + fn +
					"\r\n coll:" + coll.getName() +
					"\r\n file:" + trackfilesize +
					"\r\n skip repeat:" + trackSkipRepeat +

					//assert (UtilFile.fileAsList(fn).size() == coll.count)
					//O.o "done confirming the coll insert count integrity"


					O.o ("trackAttemptedMin", trackAttemptedMin)
			O.o ("trackSuccess", trackSuccess)
			O.o ("trackSkipRepeat", trackSkipRepeat)
			UtilAssert.assertequalsx ("trackAttemptedMin == trackSuccess + trackSkipRepeat", trackAttemptedMin, trackSuccess + trackSkipRepeat)
			UtilAssert.assertequalsx ("collcount == trackSuccess", collcount, trackSuccess)
			O.o("done assertions")


		}
		catch (Throwable t )
		{
			O.or("t", t);
			throw t;
			assert(false);
		} finally {
			assert (true)
		}
		coll;


	}


	public static void main (String[] args)
	{
		try
		{
			doWork();
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

	public static String doWork() // production
	{


		UtilFileLineRaw.insertFileIntoColl_ViaFileLineRaw(
				Cfg.fnFavsCkckck, UtilMongo.getColl(Cfg.collnameFavsCkckck))

		return null;
	}



}
