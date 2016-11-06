package com.ustodo.utilg

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection

class UtilMongoCollCreate extends UtilMongo {


	// current 111207
	public static void main (String[] args) {

		Dbo d = new Dbo();
		d.put("a", "1")
		d.put("b", new BasicDBObject("b":"2"))
		O.o("hghghg:" + d.toString());


		String collname = "111208Test_"+Cfg.collnameFavsCkckck;
		try {
			DBCollection dbc = UtilMongoCollCreate.createColl (
					"UtilFileLineRaw.groovy", // desc 1
					collname, // collname 2
					true, // indexed 3
					true, // capped 4
					"filelinerawnodate", // unique field
					"-1", // unique index
					true, // background
					true // confirm
					);

			//			public static DBCollection createColl (
			//				String desc,
			//				String collname,
			//				boolean indexed,
			//				boolean capped,
			//				String unique, String uniqueDir,
			//				boolean background,
			//				boolean confirm)

			UtilFileLineRaw.insertFileIntoColl_ViaFileLineRaw( Cfg.fnFavsCkckck, dbc)

		} catch (Throwable t )
		{
			O.or("t.getMessage() 4 [" + t.getMessage( ) + "]", t);
			throw t;
		} finally {
			O.o "done in UtilFileLineRaw UtilMongoCollCreate.createColl *******************"
			assert (true)
		}

	}
	// called by UtilFileLineRaw 111206
	public static DBCollection createColl (
		String desc,
		String collname,
		boolean indexed,
		boolean capped,
		String unique, String uniqueDir,
		boolean background,
		boolean confirm)
	{
		//		coll.ensureIndex(new BasicDBObject("id", 1).append("unique", true));
		//		coll.createIndex(new BasicDBObject("name", 1));

		//http://vsbabu.org/mt/archives/2010/03/02/simple_mongodbjava_example.html
		//def strAsJson1 = "{integer:1, bool: true}" as BasicDBObject
		UtilMongo.dropColl(desc, collname, confirm)
		//BasicDBObject a = (BasicDBObject) com.mongodb.util.JSON.parse(json);


//		//def coll = UtilMongo.getColl(collname)
//
//		// http://www.mongodb.org/display/DOCS/Indexes#Indexes-CreationOptions
//		// db.things.ensureIndex({firstname: 1, lastname: 1}, {unique : true, dropDups : true});
//		Dbo dbocollcreate = new Dbo();
//
//		if (unique != null)
//			dbocollcreate.put(unique, 1)
//		//db.createCollection("mycoll", {capped:true, size:100000, max:100});
//		//coll.validate();
//		if (capped)
//			dbocollcreate.putAt("capped", true)
//
//		if (background)
//			dbocollcreate.putAt("background", true)
//
//		//coll.ensureIndex({firstname: 1}, {unique: true});
//		if (unique != null)
//		{
//			dbocollcreate.putAt("unique", true)
//		}
//		dbocollcreate.putAt("size", 100000000)

		//if (indexed)
		//	UtilMongo.getColl(collname).ensureIndex("filelineraw")

		UtilMongo.getColl(collname)

	}

	//coll.dropindexes
	//coll.reindex
}
