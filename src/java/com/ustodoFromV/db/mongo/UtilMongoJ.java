package com.ustodoFromV.db.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Set;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.ustodoFromV.Cfg;
import com.ustodoFromV.util.O;

public class UtilMongoJ {
    // from https://github.com/mongodb/mongo-java-driver/downloads
    // from http://www.mongodb.org/display/DOCS/Java+Tutorial

    //private static boolean loadedConfig = CfgUtd.configSlurpDoIt();

    public static void main(String[] sa)
    {
    	getCollectionNames();
    }
    

    // ========================================================
    // GET/CREATE DB ARTIFACTS
    // ========================================================
    public static com.mongodb.Mongo getMongo ()
    {
        try {
			return new com.mongodb.Mongo(Cfg.getMongoip(), Cfg.getMongoPort());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    // Auth
    //	boolean auth = db.authenticate(myUserName, myPassword);



    // GET DB
    public static DB getDB ()
    {
        Mongo m = getMongo();
        return m.getDB(Cfg.getDbname());
    }

    public static DB getDB (String dbname)
    {
        Mongo m = getMongo();
        return m.getDB(dbname);
    }

    public static DB utilMongoDBGet (String dbname)
    {
        Mongo m = getMongo(); // com.mongodb.DB
        // test
        return m.getDB(dbname);
    }



    // GET COLLECTION
	//    public static DBCollection getCollAsStrListFLR (DBCollection coll)
	//    {
	//    	ArrayList al = new ArrayList();
	//
	//        coll.find().each {
	//        al << it.filelineraw
	//    }
	//        al
	//    }
    
    public static DBCollection getColl (String collname, boolean drop)
    {
        if (drop)
        {
            DBCollection dbc = getColl(collname);
        }
        return getCollFromScratch (Cfg.getMongoip(), Cfg.getMongoPort(), Cfg.getDbname(), collname);
        //return getCollFromScratch (Cfg.mongoip, Cfg.mongoport, Cfg.dbname, collname);
    }
    
    public static DBCollection getColl (String collname)
    {
        return getCollFromScratch (Cfg.getMongoip(), Cfg.getMongoPort(), Cfg.getDbname(), collname);
    }

	//    public static DBCollection getCollFavs (String username)
	//    {
	//        getColl(CfgUtd.getCollNameThisUserFavs(username));
	//    }


	//    public static DBCollection getCollFreshCappedNot (String collname)
	//    {
	//        DB db = (new Mongo(CfgUtd.mongoip, CfgUtd.mongoport)).getDB(CfgUtd.dbname);
	//        dropColl("getCollFreshCappedNot", collname, true);
	//        DBCollection coll = db.getCollection(collname);
	//        //coll.ensureIndex(collname)
	//        String field = "filelineraw";
	//        //UtilMongo.ensureIndex(coll, field)
	//        return coll;
	//        //db.createCollection("mycoll", {capped:true, size:100000, max:100});
	//        //coll.validate();
	//    }



    public static DBCollection getCollFromScratch (String ip, int port, String namedb, String collname)
    {
        DBCollection coll = null;
		try {
			coll = (((new Mongo(ip, port)).getDB(namedb)).getCollection(collname));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return coll;
        
    }
    public static DBCollection getCollectionTest (String sCollName)
    {
        return getDB().getCollection(sCollName);
    }
    
    public static Long collCount(DBCollection dbc)
    {
        return dbc.count();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // forconsole: com.ustodo.utilg.UtilMongo.select("favsckckck", String fieldToSrchOrNull, String s, String orderfieldNullable, int orderdir, int limitIfGt0
    public static DBCursor testGetCursor()
    {

        //O.oNoFilter "== UtilMongo.select countlimit:" + limitIfGt0 + " q [" + sArrTxtUpperLcaseSplit + "] options [" + maputdoptionsOrNull_tm + "]";
        // main resource with examples http://www.mkyong.com/mongodb/java-mongodb-query-document/
        try
        {
            // O.o("top of mongo select -- " + strSrchPreStarStar)
            //DBCollection coll = UtilMongoJ.getColl("favsdrosen");
            //dbo.put("filelineraw", "/.*simpletest.*/i")
            //Profiler.check("pre regex find")



            // ORDER

            // PATTERN RESTRICT
            //BasicDBObject dboq = new BasicDBObject();

            //cur = cur.limit(limitIfGt0)

            return (UtilMongoJ.getColl("favsdrosen")).find(new BasicDBObject());

        } catch (Throwable t )
        {
            System.out.println("ddd7 t.getMessage() 3 [" + t.getMessage() + "]");
            t.printStackTrace();
        } finally {
            //O.o "done Mongo namedb:" + Cfg.dbname
        }
        return null;
    }

    
    
    
    
    // forconsole: com.ustodo.utilg.UtilMongo.select("favsckckck", String fieldToSrchOrNull, String s, String orderfieldNullable, int orderdir, int limitIfGt0
    public static DBCursor select(String collname,
                  String fieldToSrchOrNull,
                  String[] sArrTxtUpperLcaseSplit,
                  String orderfieldOrNull,
                  int orderdir,
                  int limitIfGt0,
                  String maputdoptionsOrNull_tm,
                  BasicDBObject dboFromCallerAnd
    )
    {
    	return UtilMongoJ.select(collname, fieldToSrchOrNull, sArrTxtUpperLcaseSplit, orderfieldOrNull, orderdir, limitIfGt0, maputdoptionsOrNull_tm, dboFromCallerAnd);
    }


    
    
    
    public static class DboOrderBy extends BasicDBObject 
    {
    	public DboOrderBy (String field, int asc1desc2)
    	{
    		put(field, asc1desc2); // 1 ASC 2 DESC
//          cur = cur.sort(dboorder);
    	}
    	
    }
    
    
    
    
    
    
    // forconsole: com.ustodo.utilg.UtilMongo.select("favsckckck", String fieldToSrchOrNull, String s, String orderfieldNullable, int orderdir, int limitIfGt0
    public static DBCursor selectlocal(
		String collname,
		String fieldToSrchOrNull,
		String[] sArrAndMatch,
		DboOrderBy dboOrderByOrNull) 
    {
    	
        //    	cur = UtilMongo.select( // real app fetch of file line records
		//    			collname,
		//    			"filelineraw",
		//                sArrTxtUpperLcaseSplit,
		//    			"date",
		//    			0,
		//    			0,
		//    			maputdoptions.get("tm"),
		//                dboCaller// dbo
		//                )    
    	
    	
        //O.oNoFilter "== UtilMongo.select countlimit:" + limitIfGt0 + " q [" + sArrTxtUpperLcaseSplit + "] options [" + maputdoptionsOrNull_tm + "]";
        // main resource with examples http://www.mkyong.com/mongodb/java-mongodb-query-document/
        try
        {
            // O.o("top of mongo select -- " + strSrchPreStarStar)
            DBCollection coll = UtilMongoJ.getColl(collname);
            //dbo.put("filelineraw", "/.*simpletest.*/i")
            //Profiler.check("pre regex find")


            // ORDER

            // PATTERN RESTRICT
            BasicDBObject dboq = new BasicDBObject();
            if (fieldToSrchOrNull != null)
            {
                if (sArrAndMatch != null && !sArrAndMatch[0].equals("*"))
                {
                    dboq = UtilMongoDboCreateJ.buildDboAndLike(fieldToSrchOrNull, sArrAndMatch);
                }
                //O.o(" sArrTxtUpperLcaseSplit.length:"+sArrTxtUpperLcaseSplit.length)
            }

            DBCursor cur = coll.find(dboq);

            // AND
            //BasicDBList basicDBList = new BasicDBList();
            //basicDBList.add (dboq);
            //basicDBList.add (new BasicDBObject());
            //dboq = new BasicDBObject("$and", basicDBList);

            // SORT
			//            BasicDBObject dboorder = new BasicDBObject();
			//            dboorder.put("date", 2); // 1 ASC 2 DESC
            if (dboOrderByOrNull != null)
            	cur = cur.sort(dboOrderByOrNull);

            return cur;

        } catch (Throwable t)
        {
            O.oerr("ddd7 t.getMessage() 3 [" + t.getMessage() + "]", t);
            throw new RuntimeException("got throwable, see console output for stacktrace:" + t.getMessage());
            
        } finally {
            //O.o ("done Mongo namedb:" + Cfg.dbname);
            assert (true);
        }
    }
    
    public static void removeEmptyAllFromCollection()
    {
    	// http://docs.mongodb.org/manual/reference/method/db.collection.remove/
    	// http://www.mkyong.com/mongodb/mongodb-hello-world-example/
    	
    	
    	
    }
    
    public static void getCollectionNames()
    {
    	
    	DB db = getDB();
    			
    	Set<String> colls = db.getCollectionNames();

    	for (String s : colls) {
    	    System.out.println(s);
    	}
    }
    
    
    
    
    public static void updateRecordTimestamp(String id, String username, String strDateInFileFormat) throws Exception 
    {
       try
       {

            O.o ("start updateRecordTimestamp id:" + id + ", username:" + username + ", Cfg.getCollNameThisUserFavs(username):" + Cfg.getDummyCollname());
            BasicDBObject dboWhere = new BasicDBObject ().append ("_id", new ObjectId(id));

            BasicDBObject dboToUpdate = getFlrById(id, username);
            O.o( "updating obj:" + dboToUpdate.toString());
            // 826. updating obj:{
            //"_id" : { "$oid" : "4f09e3f20364589a5c3c7067"} ,
            // "icnt" : 20556 ,
            // "filelineraw" : "2010-03-18 04:10:46 ustodo urls / http://209.6.248.38:8082/ustodo/home/lkp2" ,
            // "date" : "2010-03-18 04:10:46" ,
            // "newText" : "ustodo urls / http://209.6.248.38:8082/ustodo/home/lkp2"}
            //def dt = UtilDate.getDateForFile();
            String newtext2 = (String) dboToUpdate.get("text");


            BasicDBObject dboSet = new BasicDBObject();
            dboSet.append("filelineraw", strDateInFileFormat + " " + newtext2);
            dboSet.append("date", strDateInFileFormat);
            dboSet.append("text", newtext2);
            throw new RuntimeException ("what to do here? was (see comment below this for accuracy) BasicDBObject dboSetter = new BasicDBObject(\"$set\", dboSet)");
//            BasicDBObject dboSetter = new BasicDBObject("\$set", dboSet);
//            //getCollFavs(username).update(dboWhere, (DBObject) UtilJson.cvtJsonToDbo("{ '\$set' : { 'newText': '$newtext'}}")   );
//            DBCollection coll = getCollFavs(username);
//            updateRecord_UsingMongoUpdate(coll, dboSetter, dboWhere)
//            O.o ("done updateRecordTimestamp id:" + id + ", username:" + username + ", Cfg.getCollNameThisUserFavs(username):" + Cfg.getCollNameThisUserFavs(username))
//            return ;

        } catch (Exception e ) {
            O.or("fail in updateRecordTimestamp", e);
            throw e;
        }
    } // end fn

    
    
    public static BasicDBObject getFlrById(String id, String username) {
        BasicDBObject dboq = new BasicDBObject ();
        BasicDBObject dboFlrRtn = null;

        dboq.put("_id", new ObjectId(id));

        // from  getAllUtdDatAsFLRStrList
        //		String rx = ".*utdData.*";
        //		dboq.put("filelineraw",  java.util.regex.Pattern.compile(rx));
        DBCursor cur = getCur(Cfg.getCollNameThisUserFavs(username), dboq);
        ArrayList<BasicDBObject> arrl = new ArrayList<BasicDBObject>();
        while (cur.hasNext())
        {
            dboFlrRtn = (BasicDBObject) cur.next();
            //O.o "got one:" +slfr
            arrl.add(dboFlrRtn);
        }

        if (arrl.size() > 1)
            throw new RuntimeException("> 1 objs for one id:" + id + ", coll:" + Cfg.getCollNameThisUserFavs(username));

        //O.o ("done getting getFlrById id:" + id + ", username:" + username + ", Cfg.getCollNameThisUserFavs(username):" + Cfg.getCollNameThisUserFavs(username))
        return (BasicDBObject) arrl.get(0); // array not needed, but as a template for a list fetcher
    }
   
    
    
    public static DBCursor getCur(String collname, BasicDBObject dboquery)
    {
        return getColl(collname).find(dboquery);
    }
    
    
    public static DBCollection getCollFavs (String username)
    {
        return getColl(Cfg.getCollNameThisUserFavs(username));
    }
   
    
    

}