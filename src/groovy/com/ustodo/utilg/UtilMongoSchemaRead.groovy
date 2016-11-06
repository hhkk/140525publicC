package com.ustodo.utilg

import com.mongodb.DB

class UtilMongoSchemaRead extends UtilMongo {


	//public static String listColls 
	
	
	public static void collListRecentDocs_insert (String desc, String s) // e.g., 
	{	
		UtilMongo.putToCollNameValue(desc, "RecentDocs", s)	
	}
	
	public static String[] getSchemaCollsThisDB(String dbname)
	{
		def rtn = []
		DB db = utilMongoDBGet(dbname);
		db.getCollectionNames().each {
			O.o "collnamelistitem[" + it + "]"
			rtn << it
		}
		rtn
	}
	
	public static boolean collExists(String dbname, String collname)
	{
		DB db = utilMongoDBGet(dbname);
		List<String> databaseNames = db.getDatabaseNames();
	}


}