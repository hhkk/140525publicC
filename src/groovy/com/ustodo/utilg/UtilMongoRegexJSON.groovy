package com.ustodo.utilg

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.Mongo

class UtilMongoRegexJSON {
	// from https://github.com/mongodb/mongo-java-driver/downloads
	// from http://www.mongodb.org/display/DOCS/Java+Tutorial

	private static boolean loadedConfig = Cfg.configSlurpDoIt();

	//
	//	public static DBCollection[] getRecsRegex (String collname, String regex) // regular expression
	//	{
	//		def rtn = [];
	//		DBCollection coll = (((new Mongo(Cfg.mongoip, Cfg.mongoport)).getDB(Cfg.dbname)).getCollection(collname));
	//		O.ofmt("pattern.toString() (find all records matching this string)", regex	.toString()) ;
	//		BasicDBObject dboquery= new BasicDBObject(); // filelineraw compare
	//		dboquery.put("filelineraw",  java.util.regex.Pattern.compile(regex));
	//	}

	public static List regexDbReadStarStar_flr(String collname, String field, String s)
	{
		try
		{			def alFlr = new ArrayList();
			int i = 0;
			regexDbReadStarStar(collname, field, s).each {
				i++
				//O.o " regex db search found i:[${i}] it:[${it}]"
				BasicDBObject dbof = it;
				String flr = dbof.filelineraw;
				alFlr << flr

				if (!((String)flr).contains(s))
					throw new RuntimeException("not herein2!");
				//O.o "keyset:" + dbof.keySet.toString()
				//O.o "getname:" + dbof.get("name");
				//works: O.o "getid:" + dbof.get("_id");
			}
			Profiler.check("post regex find and iterate loop")
			O.o("found [${i}] records on regex search for [${s}] ")
			//O.o("done ")
			alFlr;

		} catch (Throwable t )
		{
			O.or("t.getMessage() 5 [" + t.getMessage() + "]", t);
			throw t;
		} finally {
			O.o "done Mongo ************************* namedb:" + Cfg.dbname
			assert (true)
		}

	}



	// com.ustodo.utilg.UtilMongoRegexJSON.regexDbReadStarStar("favsckckck", "filelineraw", "kon")
	public static DBCursor regexDbReadStarStar(String collname, String fieldToSrch, String s)
	{
		regexDbReadStarStarOrderBy( collname,  fieldToSrch,  s,
			null, -1)
	}


	// SPRINT ORDER BY
	// MAIN SEARCH FOR TODOCONTROLLER
	// com.ustodo.utilg.UtilMongoRegexJSON.regexDbReadStarStar("favsckckck", "filelineraw", "kon")
	public static DBCursor regexDbReadStarStarOrderBy(String collname, String fieldToSrch, String s,
		String orderfield, int orderdir)
	{
		// literal .*s.* - no split below
		try
		{
			DBCollection coll = UtilMongo.getColl(collname)
			BasicDBObject dboq = new BasicDBObject();
			String rx = ".*"+s+".*";
			//if (!casesens)
				//rx = rx + "/i"
			dboq.put(fieldToSrch,  java.util.regex.Pattern.compile(rx));
			//dbo.put("filelineraw", "/.*simpletest.*/i")
			//Profiler.check("pre regex find")

			DBCursor cur = null;
			if (orderfield != null)
			{
				// ORDER BY
				BasicDBObject dboorder = new BasicDBObject();
				dboorder.put(orderfield, orderdir);
				cur = coll.find(dboq).sort(dboorder);
				// collection.find().sort( {column1:1or -1 [, column2:1 or -1] });
			} else // no order
				cur = coll.find(dboq)
			cur;

		} catch (Throwable t )
		{
			O.or("t.getMessage() 6 [" + t.getMessage() + "]", t);
			throw t;
		} finally {
			O.o "done Mongo ************************* namedb:" + Cfg.dbname
			assert (true)
		}

	}

	private static void jsonAndLeftovers()
	{
		//		// 5 json to dbo
		//		def sJsOri = "{ \"name\" : \"henry b kon\"}"
		//		Object o2 = UtilMongo.cvtJsonStrToDboTest(sJsOri);
		//		//O.oc "test:", o2
		//
		//		// 6 dbobj to json
		//		dboq = new BasicDBObject();
		//		dboq.put("name", "henry b kon")
		//		def sJsCheck = UtilMongo.cvtDboToJsonStr(dboq);
		//		assert sJsOri.equals(sJsCheck);
		//
		//		// 7 find record in db then from db to file
		//
		//		// 8 find record in file then insert into temp db coll and see if it's in there
		//


	}


	public static void main (String[] args)
	{
		try
		{
			//String s = UtilCons.gets("enter substring");
			String s = "kon";
			regexDbReadStarStar("favsckckck", "filelineraw", s)
		} catch (Throwable t )
		{
			O.or("t.getMessage() 7 [" + t.getMessage() + "]", t);
			throw t;
		} finally {
		}

	}






}


