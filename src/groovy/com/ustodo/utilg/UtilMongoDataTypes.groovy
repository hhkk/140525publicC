package com.ustodo.utilg

import com.mongodb.BasicDBObject
import com.mongodb.DBCursor

class UtilMongoDataTypes {
	// from https://github.com/mongodb/mongo-java-driver/downloads
	// from http://www.mongodb.org/display/DOCS/Java+Tutorial

	private static boolean loadedConfig = Cfg.configSlurpDoIt();

	public static void main (String[] args)
	{
		try
		{
			// from http://www.mongodb.org/display/DOCS/Java+Types
			UtilMongo.removeAll("colltest")

//			ArrayList x = new ArrayList();
//			x.add(1000);
//			x.add(2000);
//			x.add(new BasicDBObject("foo", "bar"));
//			x.add(4000);
//			BasicDBObject dbo = new BasicDBObject("x", x);
//			UtilMongo.put("colltest", dbo)

			HashMap y = new HashMap();
			y.put(5000, new BasicDBObject("foo", "bar"));
			UtilMongo.put("colltest", new BasicDBObject("y", y))

			UtilMongo.getCur("colltest", new BasicDBObject()).eachWithIndex { it, i ->
				O.oc("hash becomes [${i}] it:", it) // y is com.mongodb.BasicDBObject
				O.o("[${i}] s:"+ it.toString())
				// if array x
				// com.mongodb.BasicDBList s = it.x
				//O.oc "s[1]:", s[1]
				// if y
				def s = it.y
				O.oc "level 2 s", s // <level 2 s> clsnm <com.mongodb.BasicDBObject>
				O.o "it.x: [${it.x}]"

			}


		}
		catch (Throwable t )
		{
			O.or("t", t);
			throw t;
			assert(false);
		} finally {
			assert (true)
		}

		// 2 arraylist
		try
		{
			// from http://www.mongodb.org/display/DOCS/Java+Types
			UtilMongo.removeAll("colltest")

			ArrayList x = new ArrayList();
			x.add(1000);
			x.add(2000);
			//x.add(new BasicDBObject("foo", "bar"));
			x.add(4000);
			BasicDBObject dbo = new BasicDBObject("x", x);
			UtilMongo.put("colltest", dbo)

//			HashMap y = new HashMap();
//			y.put(5000, new BasicDBObject("foo", "bar"));
//			UtilMongo.put("colltest", new BasicDBObject("y", y))
//
			BasicDBObject dboq = new BasicDBObject();
			DBCursor cur = UtilMongo.getCur("colltest", dboq)
			UtilMongo.getCur("colltest", dboq).eachWithIndex { it, i ->
				O.oc("arraylist [${i}] it:", it) // x is a ...
				O.o("[${i}] s:"+ it.toString())
				// if array x
				// com.mongodb.BasicDBList s = it.x
				//O.oc "s[1]:", s[1]
				// if y
				def s = it.x
				O.oc "level 2 s", s // clsnm <com.mongodb.BasicDBList
				O.o "it.x: [${it.x}]"

			}


		}
		catch (Throwable t )
		{
			O.or("t", t);
			throw t;
			assert(false);
		} finally {
			assert (true)
		}

	}



}


