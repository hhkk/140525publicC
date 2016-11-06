package com.ustodo.utilg

import java.util.List

import com.mongodb.Mongo

class MongoSchemaCleanup extends UtilMongo {


	public static void main(String[] args) {
		// dropDBsAll(true);
	}

	// COLL DROP
	public static void dropColl (String dbname, String collname, boolean prompt)
	{
		if (!prompt || UtilCons.getYtrueNFalse("Delete coll? " + collname))
		{
			def db = utilMongoDBGet(dbname);
			def coll = db.getCollection(collname);
			try {
				coll.drop();
				O.o "db.coll dropped [" + dbname+"."+collname + "]";
			} catch ( Exception e) {
				O.o "##########################  unable to delete [" + dbname+"."+collname + "], continuing " + e.getMessage();
			}
		}
	}

	// COLL DROP SWEEP
	public static String[] dropColls(String dbname, boolean prompt)
	{
		def sarrcollnames= UtilMongoSchemaRead.getSchemaCollsThisDB(dbname);
		O.o "collcount:" + sarrcollnames.length
		boolean deleteAll = false;
		int cntThisDB = 0;
		sarrcollnames.each {
			if (!"system.indexes".equals(it))
			{
//				if (!deleteAll && ++cntThisDB % 3 == 0)
//				{
//					deleteAll = UtilCons.getYtrueNFalse("want to delete all this DB [" + dbname + "] careful - will sweep delete all remaining colls this DB")
//				}
//				dropColl(dbname, it.toString(), prompt || deleteAll)
				dropColl(dbname, it.toString(), prompt)
			}
		}
	}

	// DB DROP SWEEP
	public static String[] dropDBsAll(boolean prompt)
	{
		Set<String> sdb = (new Mongo()).getDatabaseNames();
		int dbCountOri = sdb.size();
		sdb.each() {
			itDbName ->
			if (true || UtilMongoSchemaRead.getSchemaCollsThisDB (itDbName).length != 0)
			{
				O.o("************* next DB ***********************");
				if (UtilCons.getYtrueNFalse("Delete DB collections (with prompt)? [" + itDbName + "]"))
				{
					dropColls(itDbName, true);
					if (UtilCons.getYtrueNFalse("Drop DB too now that we're done with it's coll drops? [" + itDbName + "]"))
					{
						UtilMongo.dropDB(itDbName, false)
					}
				}
			}
		}
		O.o "Done db and coll sweep delete interactive from [" + dbCountOri + "] to [" + (new Mongo()).getDatabaseNames().size() + "]"

		
	}

	
	public static List schemaListDBsAndCollsWithinEachDB_topLevel()
	{
		def m = new Mongo();
		Set<String> sdb = m.getDatabaseNames();
		int hierCountLevel1 = 0;
		sdb.each() { dbname ->
			
			O.o "H:" + hierCountLevel1++ + " list db names:" + dbname
			int hierCountLevel2 = 0;
			UtilMongo.schemaGetCollectionNames(dbname).each { collname ->
				O.o "H:" + hierCountLevel1 + "." + hierCountLevel2++ + " : " + dbname + "." + collname
			}
		}
	}

}
