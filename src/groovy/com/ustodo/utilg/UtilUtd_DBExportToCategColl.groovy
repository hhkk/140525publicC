package com.ustodo.utilg

    import com.mongodb.DBCollection;
import com.mongodb.DBCursor;


class UtilUtd_DBExportToCategColl {
	// see also UtilMongo_File_Email_Db

	public static final String FAVSCATEGS = "favscategs";


	private static Map<String, Map<String, String>> categCachePerUser = new HashMap<String, Map<String, String>>();

	public static void main (String[] args)
	{
		try
		{
			if (true) // create initial colls from DB
			{
				UtilMongo.removeAll(UtilMongo.getColl(FAVSCATEGS), false)
				// String user, String collname, String collnamecategs, String filename
				exportFavsCategsToCategsColl("mkon", "favsmkon", FAVSCATEGS);
				//exportFavsCategsToCategsColl("zkon", "favszkon", FAVSCATEGS);
				exportFavsCategsToCategsColl("ckckck", "favsckckck", FAVSCATEGS);

				UtilMongo.getColl(FAVSCATEGS).ensureIndex("date")
				UtilMongo.getColl(FAVSCATEGS).find().sort(new Dbo("date", 1)).eachWithIndex { it, i ->
					O.o("it:" + it)
				}
				O.o("cnt:" + UtilMongo.getColl(FAVSCATEGS).count())
			} else {
				String username = "mkon"
				HashMap<String, String> hm = getUserMap_DatestrToCateg_createIfNotInMemYet(username) // date to categ this user

				hm.keySet().each {
					def value = hm.get(it.toString())
					//O.o("got for user [${username}] categ:" + it + "->" + value)
				}
			}
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


	public static add_DatestrToCateg (String username, String date, String categ)
	{
		Vector<String> vecDatesToRemove = new Vector<String>()
		HashMap<String, String> hmUserCategMap = getUserMap_DatestrToCateg_createIfNotInMemYet(username)
		// remove pre-existing categs
		hmUserCategMap.keySet().each {
			String categold = hmUserCategMap.get(it)
			if (categold.equals(categ))
				vecDatesToRemove.add(it);
		}
		vecDatesToRemove.each {
			hmUserCategMap.remove(it)
		}
		//add new one
		hmUserCategMap.put(date, categ);
		O.o "for user [${date}] added categ [${categ}] date  [${date}] "
	}


	public static HashMap<String, String> getUserMap_DatestrToCateg_createIfNotInMemYet (String username)
	{
		HashMap<String, String> userCategMap = categCachePerUser.get(username)
		if (userCategMap == null)
			userCategMap = getFromDBHashMapDateToStrThisUser(username)

		userCategMap;
	}

	private static HashMap<String, String> getFromDBHashMapDateToStrThisUser (String username)
	{
		HashMap<String, String> userCategMapFromDB = new HashMap<String, String>();
		def seenalready = new HashSet();
		// this iteration should be latest first so most recent is first on the output
		UtilMongo.getColl(FAVSCATEGS).find(new Dbo("username", username)).sort(new Dbo("date", 1)).eachWithIndex { it, i ->
			if (!seenalready.contains(it.categ))
			{
				userCategMapFromDB.put(it.date, it.categ);
				seenalready.add(it.categ)
			}
		}
		categCachePerUser.put(username,  userCategMapFromDB)
		userCategMapFromDB
	}


	private static DBCursor getCurCategs_all(String username2)
	{
		UtilMongo.getColl("categs").find()
	}

	public static HashMap getCategHMFromFLR(String filelineraw, String username)
	{
		String filelinetext = filelineraw[20..-1]
		HashMap hmRtn = null;
		if (filelinetext.indexOf(" / ") > 0)
		{
			String categ = UtilStr.keepAllBeforeLastOfThis(filelinetext, " / ").trim();
			hmRtn  = new HashMap();
			hmRtn.put ("username", username)
			hmRtn.put ("date", filelineraw[0..18])
			hmRtn.put ("categ", categ)
		}
		hmRtn;
	}





	private static String exportFavsCategsToCategsColl(String username, String collnameFLR, String collnamecategs)
	{
		//UtilFile.addLineToFileTrunc(Cfg.fnFavsCkckckExport, "start export " + new java.util.Date() + "\r\n")

		DBCollection collcategs = UtilMongo.getColl(collnamecategs)
		UtilMongo.getColl(collnameFLR).ensureIndex("date")
		HashSet hsCategDedup = new HashSet();


		// EACH
		int inumkept = 0;
		// for each FLR (this coll/user) sorted by date desc
		try
		{
			UtilMongo.getColl(collnameFLR).find().sort(new Dbo("date", -1)).eachWithIndex{ it, i ->
				// get fileLine as dbo
				// 3:filelineraw date text

				if ( inumkept > 199)
					throw new Exception("not an exception - hit max ")

				//if ( i % 500 == 0)

				def keys = it.keySet(); // works - set of fields 0. keys:[_id, filelineraw, text, count, type, date]
				//O.o "keys:" + keys
				String filelineraw = it.filelineraw;
				String filelinetext = it.text;
				String filelinedate = it.date;
				//O.o "dedupkey:" + filelinetext
				//			String dedupkeyDate = dboflr.text;
				// phases: categ only no date or user
				// phases: include then user, date, count in that order
				// user / category / count
				// user / word / count
				// user / tagOrAll / count
				HashMap hmCateg = null;
				if (hmCateg != null)
				{

					if (!hsCategDedup.contains(hmCateg.get("categ")))
					{
						hsCategDedup.add(categ)
						def cntpre = collcategs.count();
						//O.o "cntpre:" + cntpre
						//O.o "collcategs:" + collcategs
						def dbo = new Dbo()
						dbo.put("username", username)
						dbo.put("date", filelinedate)
						dbo.put("categ", categ)
						collcategs.insert(dbo)
						if (collcategs.count() != (cntpre+1)) // .. check added one
						{
							throw new Exception("error in counts"+collcategs.count() + " vs:" +  (cntpre+1))
						}
						inumkept++;
					}

				}

				if (filelinetext.indexOf(" / ") > 0)
				{
					String categ = UtilStr.keepAllBeforeLastOfThis(filelinetext, " / ").trim();
					if (!hsCategDedup.contains(categ))
					{ // changeme
						if (inumkept % 10 == 0)
							O.o(i + ". keep it #[${inumkept}] [${it}]")
						//if ( i < 10)
						//O.o( "categ:" + categ)
						//	O.o "first few hashset hs:" + it
						//O.o "in test mode!: - change:" + it
						// O.o "i+1:[(${i}+1)]prod14 [${it.size()}]:" + it
						hsCategDedup.add(categ)
						def cntpre = collcategs.count();
						//O.o "cntpre:" + cntpre
						//O.o "collcategs:" + collcategs
						def dbo = new Dbo()
						dbo.put("username", username)
						dbo.put("date", filelinedate)
						dbo.put("categ", categ)
						collcategs.insert(dbo)
						if (collcategs.count() != (cntpre+1)) // .. check added one
						{
							throw new Exception("error in counts"+collcategs.count() + " vs:" +  (cntpre+1))
						}
						inumkept++;
					}
					else
					{
						//O.o(i + ". skip it:" + it)
						//O.o(i + ". skip categ seen already:"+categ)
					}
				}
			}
		} catch ( Exception e) {
			O.or("error at end ", e)
		}
		O.o "collcount:" + UtilMongo.getCollFavs(username).count();
		O.o "done export to file:" + collnameFLR


	}




}


