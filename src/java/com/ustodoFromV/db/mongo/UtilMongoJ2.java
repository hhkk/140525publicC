package com.ustodoFromV.db.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;
import com.ustodoFromV.Cfg;
import com.ustodoFromV.util.O;


public class UtilMongoJ2 {

    // from https://github.com/mongodb/mongo-java-driver/downloads
    // from http://www.mongodb.org/display/DOCS/Java+Tutorial


    public static String testCallFromVaadinJava()
    {
        //UtilFile.addLineToFileAppend("/tmp/tfilerejects.txt", ""+ new java.util.Date());
        return "successful call from vaadin java to Grails code";
    }



    //private static boolean loadedConfig = Cfg.configSlurpDoIt();

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
//    	ArrayList<String> al = new ArrayList<String>();
//
//        DBCursor dbcur = coll.find();
//        while (dbcur.hasNext())
//        	al.add((String) dbcur.next().get("filelineraw"));
//        return al.toArray(new String[al.size()]);
//    }
    public static DBCollection getColl (String collname, boolean drop)
    {
        if (drop)
        {
            DBCollection dbc = getColl(collname);
        }
        return getCollFromScratch (Cfg.getMongoip(), Cfg.getMongoPort(), Cfg.getDbname(), collname);
    }
    public static DBCollection getColl (String collname)
    {
    	return getCollFromScratch (Cfg.getMongoip(), Cfg.getMongoPort(), Cfg.getDbname(), collname);
    }

    public static DBCollection getCollFavs (String username)
    {
    	return getColl(Cfg.getCollNameThisUserFavs(username));
    }


    public static DBCollection getCollFreshCappedNot (String collname)
    {
        DB db = null;
		try {
			db = (new Mongo(Cfg.getMongoip(), Cfg.getMongoPort())).getDB(Cfg.getDbname());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        dropColl("getCollFreshCappedNot", collname, true);
        DBCollection coll = db.getCollection(collname);
//coll.ensureIndex(collname)
        String field = "filelineraw";
//ensureIndex(coll, field)
        return coll;
//db.createCollection("mycoll", {capped:true, size:100000, max:100});
//coll.validate();
    }



    public static DBCollection getCollFromScratch (String ip, int port, String namedb, String collname)
    {
        try {
			return (((new Mongo(ip, port)).getDB(namedb)).getCollection(collname));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }


// GET RECORDS/DBO	SUBMIT QUERY
    public static DBCursor getCur(String collname, BasicDBObject dboquery)
    {
        return getColl(collname).find(dboquery);
    }

    public static void updateRecord_UsingMongoUpdate(DBCollection coll, BasicDBObject dboValues, BasicDBObject dboWhere)
    {
        //O.o("130421 in updateRecord_UsingMongoFindAndModify")
        WriteResult writeResult = coll.update(dboWhere, dboValues);
        writeResult.getLastError();
    }


    public static BasicDBObject updateRecord_UsingMongoFindAndModify(DBCollection coll, BasicDBObject dboValues, BasicDBObject dboWhere)
    {
        //O.o("130421 in updateRecord_UsingMongoFindAndModify")
        return (BasicDBObject) coll.findAndModify(dboWhere, dboValues);
    }



    public static void updateRecordTimestamp(String id, String username, String strDateInFileFormat)
    {
       try
       {

            //O.oNoFilter ("start updateRecordTimestamp id:" + id + ", username:" + username + ", Cfg.getCollNameThisUserFavs(username):" + Cfg.getCollNameThisUserFavs(username))
            BasicDBObject dboWhere = new BasicDBObject ().append ("_id", new ObjectId(id));

            BasicDBObject dboToUpdate = getFlrById(id, username);
            //O.o "updating obj:" + dboToUpdate.toString();
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
            BasicDBObject dboSetter = new BasicDBObject("$set", dboSet);

            //getCollFavs(username).update(dboWhere, (DBObject) UtilJson.cvtJsonToDbo("{ '\$set' : { 'newText': '$newtext'}}")   );
            DBCollection coll = getCollFavs(username);
            updateRecord_UsingMongoUpdate(coll, dboSetter, dboWhere);


            //O.o ("done updateRecordTimestamp id:" + id + ", username:" + username + ", Cfg.getCollNameThisUserFavs(username):" + Cfg.getCollNameThisUserFavs(username))

            return ;

        } catch (Exception e ) {
            O.or("fail in updateRecordTimestamp", e);
            //throw e;
        }
    } // end fn

    public static void updateRecordArchiveToggle (String id, String username,  boolean archive)
    {


        try
        {
            //O.oNoFilter ("*************** start updateRecordSetArchiveTrue id:" + id + ", username:" + username + ", Cfg.getCollNameThisUserFavs(username):" + Cfg.getCollNameThisUserFavs(username))
            BasicDBObject dboWhere = new BasicDBObject ().append ("_id", new ObjectId(id));

            BasicDBObject dboToUpdate = getFlrById(id, username);
            //O.o "updating obj:" + dboToUpdate.toString();
            BasicDBObject dboArchivedUpdate = new BasicDBObject();
            if ((Boolean) dboToUpdate.get("archived"))
            {
                //O.oNoFilter("&&&&&&&&&&&&&&& setting archived to FALSE on id:"+ id)
                dboArchivedUpdate.put("archived", false);
            }
            else
            {
                //O.oNoFilter("&&&&&&&&&&&&&&& setting archived to TRUE on id:"+ id)
                dboArchivedUpdate.put("archived", true);
            }
            BasicDBObject dboValues = new BasicDBObject("$set", dboArchivedUpdate);
            //getCollFavs(username).update(dboWhere, (DBObject) UtilJson.cvtJsonToDbo("{ '\$set' : { 'newText': '$newtext'}}")   );
            DBCollection coll = getCollFavs(username);
            updateRecord_UsingMongoUpdate(coll, dboValues, dboWhere);
            //O.oNoFilter ("done archive id:" + id + ", username:" + username + ", Cfg.getCollNameThisUserFavs(username):" + Cfg.getCollNameThisUserFavs(username))

            return ;

        } catch (Exception e ) {
            O.or("fail in updateRecordTimestamp", e);
            //throw e;
        }


        // http://www.mkyong.com/mongodb/java-mongodb-update-document/
        //		A normal way to update an existing document. Find hosting = hostB, and update it with a new document.
        //
        //			BasicDBObject newDocument = new BasicDBObject();
        //			newDocument.put("hosting", "hostB");
        //			newDocument.put("type", "shared host");
        //			newDocument.put("clients", 111);
        //
        //			collection.update(new BasicDBObject().append("hosting", "hostB"), newDocument);

    } // end fn archive






//        // introducing update 2 update vs findAndModify
//    public static void utilMongo_updateRecordText_usingFindAndModify(
//            String callerId,
//            String dbId,
//            String newText,
//            String newHtml,
//            String userName)
//    {
//        // '515606f40364dc42be038ade', "NEW CARP",  SecUser.g   et(springSecurityService.principal.id).username
//
//        try
//        {
//            O.o ("130329 in UtilMongo_updateRecordTimestamp " +
//                    "[ callerId:"   + callerId +
//                    ", dbId:" + dbId +
//                    ", newText:" + newText +
//                    ", userName:" + userName +
//                    "]")
//            BasicDBObject dboWhere = new BasicDBObject ().append ("_id", new ObjectId(dbId));
//
//            //            #     # ######  ######     #    ####### #######
//            //            #     # #     # #     #   # #      #    #
//            //            #     # #     # #     #  #   #     #    #
//            //            #     # ######  #     # #     #    #    #####
//            //            #     # #       #     # #######    #    #
//            //            #     # #       #     # #     #    #    #
//            //             #####  #       ######  #     #    #    #######
//            BasicDBObject dboValues = new BasicDBObject ();
//            dboValues.put("date", UtilDate.getDateForFile());
//            dboValues.put("filelineraw", UtilDate.getDateForFile() + " " + newText);
//            dboValues.put("text", newText)
//            dboValues.put("html", newHtml)
//
//
//            def coll = getCollFavs(userName);
//            updateRecord_UsingMongoFindAndModify(coll, new BasicDBObject("\$set", dboValues), dboWhere)
//            //updateRecord_UsingMongoFindAndModify(coll, dboValues, dboWhere)
//
//
//        } catch (Exception e ) {
//            O.or("fail in utilMongo_updateRecordTest", e);
//            throw e;
//        }
//
//
//        // http://www.mkyong.com/mongodb/java-mongodb-update-document/
//        //		A normal way to update an existing document. Find hosting = hostB, and update it with a new document.
//        //
//        //			BasicDBObject newDocument = new BasicDBObject();
//        //			newDocument.put("hosting", "hostB");
//        //			newDocument.put("type", "shared host");
//        //			newDocument.put("clients", 111);
//        //
//        //			collection.update(new BasicDBObject().append("hosting", "hostB"), newDocument);
//
//    }



//    // same args as below currently
//    public static DBCursor getCurOrderWasStarStar(String collname, String fieldToSrch, String s,
//                                                  String orderfieldNullable, int orderdir)
//    {
//        try
//        {
//            DBCollection coll = getColl(collname)
//            BasicDBObject dboq = new BasicDBObject();
//            String rx = ".*"+s+".*";
//            dboq.put(fieldToSrch,  java.util.regex.Pattern.compile(rx));
//
//            DBCursor cur = null;
//            if (orderfieldNullable != null)
//            {
//                // ORDER BY
//                BasicDBObject dboorder = new BasicDBObject();
//                dboorder.put(orderfieldNullable, orderdir);
//                cur = coll.find(dboq).sort(dboorder);
//                // collection.find().sort( {column1:1or -1 [, column2:1 or -1] });
//            } else // no order
//                cur = coll.find(dboq)
//            cur;
//
//        } catch (Throwable t )
//        {
//            O.or("t.getMessage() 2 [" + t.getMessage() + "]", t);
//            throw t;
//        } finally {
//            //O.o "done Mongo ************************* namedb:" + Cfg.dbname
//            assert (true)
//        }
//
//    }
//

//    public static BasicDBObject[] getRecsFileLineRawEqual(DBCollection coll, String fileLineRawLike_piece)
//    {
//        def rtn = [];
//        BasicDBObject dboquery= new BasicDBObject(); // filelineraw compare
//        dboquery.put("filelineraw", fileLineRawLike_piece)
//        DBCursor dbcursor = coll.find(dboquery);
//        dbcursor.each  {rtn << it}
//        rtn;
//    }
//
    public static void remove(String collname, String fieldname, String strSrchExact)
    {
        O.o ("MongoDB in remove, collname:" + collname + ", fieldname:" + fieldname + ", strSrchExact:" + strSrchExact);
        DBCollection dbc = getColl(collname);
        //BasicDBObject dboremove =getDboLike (fieldname, strSrchExact, false) // pre
        BasicDBObject dbo = new BasicDBObject();
        dbo.put(fieldname, strSrchExact);

        dbc.remove(dbo);
        //coll.findAndRemove(dboq)

        //getColl("favsckckck").remove (cvtJsonStrToDboTest("{'date':'2012-01-18 05:23:06'}"))
        //		def rtn = [];
        //		BasicDBObject dboquery= new BasicDBObject(); // filelineraw compare
        //		dboquery.put("filelineraw", fileLineRawLike_piece)
        //		coll.remove(dboquery);

    }

//    public static void removeFavsRecById(String id, String username)
//    {
//        BasicDBObject dboFlr = getFlrById(id, username);
//        if (dboFlr != null)
//            O.o "got dboFlr date [" +dboFlr.date + "] newText ["  + dboFlr.text + "]"
//        else
//            throw new RuntimeException("no flr record to delete by id ${id} for username ${username}");
//
//
//        int ipre = getCollCnt(getColl(Cfg.getCollNameThisUserFavs(username)))
//        DBCollection coll = getColl(Cfg.getCollNameThisUserFavs(username))
//        coll.setWriteConcern(WriteConcern.FSYNC_SAFE);
//
//        BasicDBObject dboremove = new BasicDBObject()
//        dboremove.put("_id", new ObjectId(id))
//        coll.remove(dboremove)
//        int ipost = getCollCnt(getColl(Cfg.getCollNameThisUserFavs(username)))
//        if (ipre != (ipost+1))
//            throw new RuntimeException("seems like not exactly one record was deleted, deleting by id ${id} username ${username}, went from ${ipre} to ${ipost}");
//        O.oNoFilter ("done remove getFlrById deleted one by id ${id} username ${username} from ${ipre} to ${ipost}")
//    }

//    public static String[] getRecsFileLineRawEqual_StrArr(DBCollection coll, String fileLineRawLike_piece)
//    {
//        ArrayList<String> als = new ArrayList<String>(); 
//        BasicDBObject dboquery= new BasicDBObject(); // filelineraw compare
//        dboquery.put("filelineraw", fileLineRawLike_piece);
//        DBCursor dbcursor = coll.find(dboquery);
//        while (dbcursor.hasNext())
//        {
//        	als.add((String) dbcursor.next().get("filelineraw"));	
//        }
//        return als.toArray(new String[als.size()]);
//    }


//	public static BasicDBObject[] getRecsFileLineRawLike(String collname, String fileLineRawLike_piece)
//	{
//		getRecsFileLineRawEqual(getColl(collname,fileLineRawLike_piece));
//	}


//    public static ArrayList<DBObject> collAsList (String dbname, String collname, boolean stringify)
//    {
//        List alRtn = new ArrayList<String>();
//        try
//        {
//            int itcnt= 0;
//            DBCollection coll = getColl(dbname, collname);
//            DBCursor dbcur = null;
//            for (int i=0, dbcur = coll.find(); iterator.hasNext();i++) {
//            	BasicDBObject dbo = dbcur.next();
//            	type type = (type) iterator.next();
//            }
//            dbcur.close();
//            if (stringify)
//                alRtn.add(it.toString());
//            else
//                alRtn.add(it);
//
//            return alRtn;
//        }
//        catch ( Exception e )
//        {
//            e.printStackTrace();
//            O.or("error in coll as list", e);
//        }
//        return null;
//    }



    //	public static void setWhiteboardNameVal (def name, def value) {
    //
    //		DBCollection dbcl = getOrCreateDBAndColl("setWhiteboardNameVal", TodoController.getNameDB(), "configplatform");
    //		if (false)
    //			removeAll("setWhiteboardNameVal", dbcl);
    //
    //		BasicDBObject doc = new BasicDBObject();
    //
    //		//doc.put("name", "MongoDB");
    //		//doc.put("type", "database");
    //		doc.put(name, value);
    //		dbcl.insert(doc);
    //	}
    //
    //	public static getWhiteboardNameVal(String name)
    //	{
    //		DBCollection dbcl = getOrCreateDBAndColl("setWhiteboardNameVal", TodoController.getNameDB(), "configplatform");
    //		if (false)
    //			removeAll("getWhiteboardNameVal", dbcl);
    //
    //		BasicDBObject doc = new BasicDBObject();
    //
    //		//doc.put("name", "MongoDB");
    //		//doc.put("type", "database");
    //		doc.get(name);
    //	}
    //

    static int callCnt_getOrCreateDBAndColl = 0;
    public static DBCollection getOrCreateDBAndColl(String desc, String dbname, String collname)
    {
        DB db = utilMongoDBGet(dbname);
        DBCollection coll = db.getCollection(collname); // mongo call
        //long lstart = O.now();
        //O.o "INFO: done got collection [", coll.count() + "] " + O.fmt("collName", coll.getName()) + ", desc [" + desc + "]" + O.fmt ("dbname", dbname) ;
        //if ((-1 + callCnt_getOrCreateDBAndColl++) % 50 == 0)
        //{
        //	O.o "PerfTest: took my time getting numbers this time:" + UtilPerf.msSince(lstart);
        //}
        return coll;
    }

    //	public static DBCollection utilMongoGetCreateDBandColl (String nameDBColl, String datetime)
    //	{
    //		String DB_NAME =   "D" + nameDBColl + "_" + datetime;
    //		String COLL_NAME = "C" + nameDBColl + "_" +  datetime;
    //		return utilMongoGetColl(DB_NAME, COLL_NAME);
    //
    //	}

    public static Long getCollCnt(DBCollection dbc)
    {
        return collCount(dbc);
    }

    public static Long collCount(DBCollection dbc)
    {
        return dbc.count();
    }

    public static Long getCollCountByName(String desc, String dbname, String collname)
    {
        DBCollection dbc = getColl(collname);
        return dbc.count();
    }

    public static BasicDBObject[] queryEquals(DBCollection coll, DBObject dboquery)
    {
    	ArrayList<BasicDBObject> rtn = new ArrayList<BasicDBObject>();
        for (DBCursor dbcursor = coll.find(dboquery); dbcursor.hasNext();)
        	rtn.add((BasicDBObject) dbcursor.next());
        return rtn.toArray(new BasicDBObject[rtn.size()]);
    }
    public static void writeUsToDoStdString(DBCollection dbc, String s)
    {
        BasicDBObject doc = new BasicDBObject();

        //doc.put("name", "MongoDB");
        //doc.put("type", "database");
        doc.put("filelineraw", s);
        doc.put("type", "filelineraw");
        doc.put("count", 1)	;
        //O.o "coll insert 1"
        dbc.insert(doc);
    }

    public static void write_No_See_PUT_INSTEAD()
    {
    }

    public static void put (String collName, BasicDBObject dbo)
    {
        put ("no desc given", collName,dbo);
    }
    public static void put (DBCollection dbc, BasicDBObject dbo)
    {
        put ("no desc given", dbc ,dbo);
    }
    public static void put (String desc, String collName, BasicDBObject dbo)
    {
        DBCollection dbc = getColl(collName);
        put (desc, dbc, dbo);
    }

    public static void put (String desc, DBCollection dbc, BasicDBObject dbo)
    {
        //BasicDBObject dbo2 = new BasicDBObject()
        O.o ("coll insert 2");
        dbc.insert(dbo);
        O.o("wrote dbobj.getClass [" + dbo.getClass() + "] coll.getFullName [" + dbc.getFullName() + "] dbobj.toString() [" + dbo.toString() + "]");
    }


    public static void putToCollNameValue(String desc, String collName, String name, String value)
    {
        put(desc, collName, new BasicDBObject(name, value));
    }

    public static void testPutRecord()
    {
        BasicDBObject doc = new BasicDBObject();
        doc.put("name", "MongoDB");
        doc.put("type", "database");
        doc.put("count", 1);
        BasicDBObject info = new BasicDBObject();
        info.put("x", 203);
        info.put("y", 102);
        doc.put("info", info);
        //coll.insert(doc);
    }

//    public static int putCollection(DBCollection dbc, ArrayList al)
//    {
//        Profiler.start("putCollection size" + al.size(), true);
//        al.each {
//            putRecordFromFLRS(dbc, it.toString());
//        }
//        Profiler.end("putCollection size" + al.size().toString(), true);
//    }
//
    // introducing putrecord
//    public static int putRecordFromFLRS ( DBCollection coll, String username, String filelinerawstr_wDate, String commandHTML)
//    {
//        coll.setWriteConcern(WriteConcern.FSYNC_SAFE);
//
//        
//        
//		//        y:000> s="012345"
//		//        		012345
//		//        		y:000> s
//		//        		012345
//		//        		y:000> s.substring(1,4)
//		//        		123
//		//        		y:000> s[0..4]
//		//        		01234
//		//        		y:000> s.substring(0,4)
//		//        		0123
//		//        		y:000> s[0..3]
//		//        		0123
//		//        		y:000>
//        
//        
//        
//        HashMap hm = new HashMap();
//        hm.put("filelineraw", filelinerawstr_wDate);
//        hm.put("date", filelinerawstr_wDate.substring(0, 19));
//        hm.put("CreateDate",UtilDate.getDateForFile());
//        hm.put("text", filelinerawstr_wDate.substring(20);
//        hm.put("html", commandHTML);
//        hm.put("type", "filelineraw");
//        hm.put("count", 1);
//
//        BasicDBObject doc = new BasicDBObject(hm);
//        def cnt = coll.count()
//
//        //O.o "coll insert 3"
//        //coll.setWriteConcern(WriteConcern.FSYNC_SAFE);
//        //coll.insert(doc);
//        coll.insert(doc);
//
//        def cnt2 = coll.count()
//        //UtilAssert.assertx("db insert to coll [${coll.getName()}] failed?: cnt+1 SHOULD == cnt2, BUT :" + (cnt+1) + " != " + cnt2, (cnt+1)==cnt2);
//
//        // add to category table
//        if (username != null)
//        {
//            HashMap hmCateg = UtilUtd_DBExportToCategColl.getCategHMFromFLR(filelinerawstr_wDate, username)
//            if (hmCateg != null)
//            {
//                BasicDBObject dboCateg = new BasicDBObject(hmCateg);
//                DBCollection collfavscategs = getColl("favscategs")
//                cnt = collfavscategs.count()
//                //O.o "coll insert 4"
//
//                collfavscategs.insert(dboCateg);
//                cnt2 = collfavscategs.count()
//                UtilAssert.assertx("db insert to [${collfavscategs.getName()}] failed?: cnt=cnt2", (cnt+1)==cnt2);
//
//                UtilUtd_DBExportToCategColl.add_DatestrToCateg(hmCateg.get("username"), hmCateg.get("date"), hmCateg.get("categ") )
//            }
//        }
//
//        return 0
//    }


    // introducing putrecord
    // hbk130516
//    public static int putCommandHistory ( String username, String filelinerawstr_wDate)
//    {
//
//        def coll = getOrCreateDBAndColl("db  write history",  Cfg.dbname, username+"'History");
//        coll.setWriteConcern(WriteConcern.FSYNC_SAFE);
//
//        HashMap hm = new HashMap();
//        hm.put("filelineraw", filelinerawstr_wDate);
//        hm.put("date", filelinerawstr_wDate[0..18]);
//        hm.put("CreateDate",UtilDate.getDateForFile());
//        hm.put("text", filelinerawstr_wDate[20..-1]);
//        hm.put("type", "filelineraw");
//        hm.put("count", 1);
//
//        BasicDBObject doc = new BasicDBObject(hm);
//        def cnt = coll.count()
//
//        //O.o "coll insert 3"
//        //coll.setWriteConcern(WriteConcern.FSYNC_SAFE);
//        //coll.insert(doc);
//        coll.insert(doc);
//
//        def cnt2 = coll.count()
//        UtilAssert.assertx("db insert to coll [${coll.getName()}] failed?: cnt+1 SHOULD == cnt2, BUT :" + (cnt+1) + " != " + cnt2, (cnt+1)==cnt2);
//
//        // add to category table
//        //        if (username != null)
//        //        {
//        //            HashMap hmCateg = UtilUtd_DBExportToCategColl.getCategHMFromFLR(filelinerawstr_wDate, username)
//        //            if (hmCateg != null)
//        //            {
//        //                BasicDBObject dboCateg = new BasicDBObject(hmCateg);
//        //                DBCollection collfavscategs = getColl("favscategs")
//        //                cnt = collfavscategs.count()
//        //                //O.o "coll insert 4"
//        //
//        //                collfavscategs.insert(dboCateg);
//        //                cnt2 = collfavscategs.count()
//        //                UtilAssert.assertx("db insert to [${collfavscategs.getName()}] failed?: cnt=cnt2", (cnt+1)==cnt2);
//        //
//        //                UtilUtd_DBExportToCategColl.add_DatestrToCateg(hmCateg.get("username"), hmCateg.get("date"), hmCateg.get("categ") )
//        //            }
//        //        }
//
//        return 0
//    }
//




    // ========================================================
    // SHUT DOWN
    // ========================================================

//    public static void dropDB (String dbname, boolean prompt)
//    {
//        O.o "dropping db:" + dbname
//        if (!prompt || UtilCons.getYtrueNFalse("sure you want to delete DB:" + dbname)) {
//            getMongo().dropDatabase(dbname); // mongo call
//            O.o ("db dropped:" + dbname);
//        }
//        else
//            O.o("skipped drop");
//    }

//    public static void removeAll(String collname)
//    {removeAll(getColl(collname));}
//    public static void removeAll(DBCollection dbc)
//    {removeAll(null, dbc, true);}
//    public static void removeAll(DBCollection dbc, boolean confirm)
//    {removeAll(null, dbc, confirm);}
//    public static void removeAll(String collname, boolean confirm)
//    {removeAll(null, getColl(collname), confirm);}
//
//
//    public static void removeAll(String desc, DBCollection dbc)
//    {
//        removeAll(desc, dbc, true);
//    }
//

//    public static void removeAll(String desc, DBCollection dbc, boolean confirm)
//    {
//        //http://www.mongodb.org/display/DOCS/Removing
//        if (!confirm || UtilCons.getYtrueNFalse("sure you want to empty collection:" + dbc.getName() + "?"))
//        {
//            O.o("removeAll:" + dbc.getName() + ", desc:" + desc);
//            dbc.remove(new BasicDBObject()); // mongo call
//        }
//        else
//            O.o("empty collection skipped");
//        //coll.remove({}) // fails
//        //db.things.remove({});    // removes all
//        //db.things.remove({n:1}); // removes all where n == 1
//        //If you have a document in memory and wish to delete it, the most efficient method is to specify the item's document _id value as a criteria:
//
//        //db.things.remove({_id: myobject._id});
//    }
//
    public static void dropColl (String desc, String collname, boolean confirm)
    {
        dropColl(desc, getColl(collname), confirm);
    }
    public static void dropColl (String desc, DBCollection coll, boolean confirm)
    {
        //if (!confirm || UtilCons.getYtrueNFalse("really drop coll? desc:" + desc)) {
        O.o("coll dropped [" + coll.getName() + "]");
        coll.drop();
        //}			else
        //	O.o "not dropped"
    }





    // ========================================================
    // SCHEMA
    // ========================================================

    public static Set<String> schemaGetDBNames()
    {
    	Set<String> setdbnames = new HashSet<String>(); 
        Mongo m = getMongo();
        List<String> ldbs = m.getDatabaseNames();
        Iterator iter = ldbs.iterator();
        while (iter.hasNext())
        { 
        	String dbname = (String) iter.next();
        }

        return setdbnames;
    }

    public static List<String> schemaGetDBNamesArr()
    {
        List<String> l = new ArrayList<String>();
        Mongo m = getMongo();
        return m.getDatabaseNames();
    }

//    public static void schemaEmitDBsLevel0 (int modPrint_NegAll_ZeroNone_posCount, String regex, boolean promptYN)
//    {
//        List<String> dbnames = new ArrayList<String>();
//        if (!promptYN)
//        {
//            dbnames = schemaGetDBNames();
//        } else {
//            dbnames.add(UtilCons.getPicklist("pick a db to browse", schemaGetDBNames()));
//        }
//
//        dbnames.eachWithIndex { dbname, i ->
//            schemaDBEmitDBByName(dbname, 0, 0, 0, dbname, false)(dbname, i, dbnames.size(), modPrint_NegAll_ZeroNone_posCount, promptYN);
//        }
//
//    }
//
//    public static void schemaDBEmitDBByName(String dbname, int indexdb, int totalNumDBs,
//                                            int modPrint_NegAll_ZeroNone_posCount, String regex, boolean promptYN)
//    {
//        try
//        {
//            //if (indexdb == 29)
//            //{
//            // ===============================================
//            // 1 GET DB
//            // ===============================================
//            com.mongodb.DB db = utilMongoDBGet(dbname);
//            Set<String> colls = schemaGetCollectionNames(dbname);
//            O.o O.fmt("\r\n\r\nL1 *** NEW DB *** dbname", dbname) + " db [" + indexdb + "] of ["+totalNumDBs + "] " + O.fmt("num collects in db", colls.size());
//            //O.oc "colls", colls;
//            //System.exit(1);
//
//            // ===============================================
//            // 2 PER COLLECTION THIS DB
//            // ===============================================
//            if (colls.size() > 0)
//            {
//                colls.eachWithIndex { collname, indexcoll ->
//                    schemaEmitRowsGivenColl(db.getCollection(collname), modPrint_NegAll_ZeroNone_posCount, promptYN)
//                } // if index == x
//            }
//            //} else {
//            //	O.o ("no DB included in loop" + O.fmt("dbname", dbname))
//            //}
//        } catch (Throwable t) {
//            O.or("in utilMongoGetOrPrintDBCollections", t);
//        } finally {
//            O.o "done utilMongoGetOrPrintDBCollections"
//        }
//    }
//

//    public static List schemaGetCollectionNames(String dbname)
//    {
//        DB db = utilMongoDBGet(dbname);
//        return db.getCollectionNames() as List; // mongo call
//    }

    // created 110815
//    public static void schemaEmitRowsGivenColl(DBCollection collread, int modPrint_NegAll_ZeroNone_posCount, boolean promptYN)
//    {
//        //coll.find().eachWithIndex { doc, j ->
//        //	breakpointableCall("L3 " + O.fmt("db", dbname) + O.fmt("coll", collname) + O.fmt(" doc:" , doc.toString()))
//        //}
//
//        String regex;
//        if (promptYN)
//            regex = UtilCons.getRegEx("enter search query")
//        else
//            regex = /.*/
//
//        BasicDBObject query = new BasicDBObject();
//        //query.put ("count", 1)
//
//        //	query.put ({ filelineraw : { $regex : /start.*shut/i, $nin : ['acmeblahcorp'] } })
//
//            BasicDBObject q = new BasicDBObject();
//            O.ofmt("pattern.toString() (find all records matching this string)", regex	.toString()) ;
//            q.put("filelineraw",  java.util.regex.Pattern.compile(regex));
//
//        //O.o("L2 (coll) db index [" + indexdb + "] collection [" + indexcoll + "] of [" + colls.size() +
//        //		"] collect name [" + collname + "] #records [" + collread.getCount() + "] pattern [" + regex + "]"  );
//        O.o("L2  #records [" + collread.getCount() + "] pattern [" + regex + "]"  );
//
//
//        // ===============================================
//        // 3 PER RECORD
//        // ===============================================
//        O.ofmt "modPrint_NegAll_ZeroNone_posCount", modPrint_NegAll_ZeroNone_posCount
//        if (modPrint_NegAll_ZeroNone_posCount != 0)
//        {
//            int loop = 0;
//            int countshowed = 0;
//            DBCursor dbcursor = collread.find(q);
//
//            while(dbcursor.hasNext())
//            {
//                String curnext = dbcursor.next();
//                //if (k % countMod == 0)
//                //if (k % 1 == 0)
//                if (modPrint_NegAll_ZeroNone_posCount < 0 || countshowed < modPrint_NegAll_ZeroNone_posCount)
//                {
//                    countshowed++;
//                    //O.o("L3 (record) " + countshowed + ". " + O.fmt("db", dbname) + O.fmt("coll", collname) + O.fmt("curnext.toString()" , curnext.toString()))
//                    O.o("L3 (record) " + countshowed + ". " +  O.fmt("curnext.toString()" , curnext.toString()))
//                }
//                loop++;
//            }
//            O.o("L3 record summary showed [" + countshowed + "] (mod [" + modPrint_NegAll_ZeroNone_posCount
//                    + "]) of [" + loop + "].")
//        }
//    }

//    private static long now()
//    {
//        UtilPerf.now();
//    }


	//    private static void notesOnFileStreamSaveToMongo()
	//    {
	//        Mongo m;
	//		try {
	//			m = new Mongo( Cfg.mongoip, Cfg.mongoport);
	//	        String fn = "/Users/hkon/sw/ustodo/favsckckck8.txt";
	//	        DB db = m.getDB(Cfg.dbname);
	//
	//	        GridFS gridfs = new GridFS(db);
	//		} catch (UnknownHostException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//    }

	//    public static BasicDBObject cvtJsonStrToDboTest(String s) {
	//        return com.mongodb.util.JSON.parse(s);
	//    }

	//    public static String cvtDboToJsonStr(BasicDBObject dbo) {
	//        dbo.toString();
	//    }

    public static void ensureIndex(DBCollection coll, String field) {
        coll.ensureIndex(field); // see also possibly http://docs.mongodb.org/manual/reference/command/reIndex/ db.collection.reIndex();

    }


    // com.ustodo.utilg.UtilMongoRegexJSON.regexDbReadStarStar("favsckckck", "filelineraw", "kon")

    public static Vector convertCurToVec (DBCursor cur){
    	Vector<BasicDBObject> v = new Vector<BasicDBObject>();
        while (cur.hasNext())
        {
            v.add((BasicDBObject) cur.next());
            //O.oc("convertCurToVec", it);
        }
        return v;
    }

    // forconsole: select("favsckckck", String fieldToSrchOrNull, String s, String orderfieldNullable, int orderdir, int limitIfGt0
    public static DBCursor select(String collname,
                  String fieldToSrchOrNull,
                  String[] sArrTxtUpperLcaseSplit,
                  String orderfieldOrNull,
                  int orderdir,
                  int limitIfGt0,
                  String maputdoptionsOrNull_tm,
                  BasicDBObject dboFromCallerAnd
    ) throws Throwable
    {

        //O.oNoFilter "== select countlimit:" + limitIfGt0 + " q [" + sArrTxtUpperLcaseSplit + "] options [" + maputdoptionsOrNull_tm + "]";
        // main resource with examples http://www.mkyong.com/mongodb/java-mongodb-query-document/
        try
        {
            // O.o("top of mongo select -- " + strSrchPreStarStar)
            DBCollection coll = getColl(collname);
            //dbo.put("filelineraw", "/.*simpletest.*/i")
            //Profiler.check("pre regex find")

            DBCursor cur = null;

            // ORDER

            // PATTERN RESTRICT
            BasicDBObject dboq = new BasicDBObject();
            if (fieldToSrchOrNull != null)
            {
                if (sArrTxtUpperLcaseSplit != null && !sArrTxtUpperLcaseSplit[0].equals("*"))
                {
                    dboq = UtilMongoDboCreateJ.buildDboAndLike(fieldToSrchOrNull, sArrTxtUpperLcaseSplit);
                }
                //O.o(" sArrTxtUpperLcaseSplit.length:"+sArrTxtUpperLcaseSplit.length)

            } // if (fieldToSrchOrNull != null)
            else
            {
                // add no constraint
            }

            // DBO from Caller
            if (dboFromCallerAnd != null)
            {
                BasicDBList basicDBList = new BasicDBList();
                basicDBList.add (dboq);
                basicDBList.add (dboFromCallerAnd);
                
                dboq = new BasicDBObject("$and", basicDBList);
            }

            // DATE RESTRICT
            String minDateStr = null;
            if (maputdoptionsOrNull_tm != null)
                minDateStr = buildMinDateStrFromMaxAge ();
            if (minDateStr != null)
            {
                //O.o "130115 select date constraint  ------------------------------------------------ yes date constraint:" + minDateStr
                //dboq.put("date", new BasicDBObject("$gt", minDateStr).append("$lt", UtilDate.getDateForFile() ));
            }
            else
            {
                //O.o "130115 ------ no date constraint"
            }

            Date t = new Date();
            cur = coll.find(dboq);
            //O.oNoFilter("db find done in [" + UtilPerf.timeSince(t, "dbFind")+ "]")

            if (limitIfGt0 > 0)
                cur = cur.limit(limitIfGt0);

            if (orderfieldOrNull != null)
            {
                BasicDBObject dboorder = new BasicDBObject();
                //O.o "orderfieldNullable:" + orderfieldNullable
                //O.o "orderdir:" + orderdir
                dboorder.put(orderfieldOrNull, orderdir); // 1 ASC 2 DESC
                cur = cur.sort(dboorder);
            }

            return cur;

        } catch (Throwable t )
        {
            O.or("ddd7 t.getMessage() 3 [" + t.getMessage() + "]", t);
            throw t;
        } finally {
            //O.o "done Mongo namedb:" + Cfg.dbname
            assert (true);
        }
    }


	public static String buildMinDateStrFromMaxAge()
	{
		GregorianCalendar gc = new GregorianCalendar();
		long yyyy = gc.get(Calendar.YEAR);
		long mm = gc.get(Calendar.MONTH)+1;
		long dd = gc.get(Calendar.DAY_OF_MONTH);
		long hh = gc.get(Calendar.HOUR_OF_DAY);
		long mn = gc.get(Calendar.MINUTE);
		long ss = gc.get(Calendar.SECOND);

		return "" + yyyy + "-" + pz(mm) + "-" + pz(dd) + " " + pz(hh) + ":" +pz(mn) + ":" + pz(ss);

	}

	// padZeroToTwoWide
	public static String pz(long l) {
		String s = "" + l;
		if (s.length() ==2 )
			return s;
		else
			return "0" + s;

	}

    public static List<String> getAllUtdDatAsFLRStrList_deletemre ()
    {
        BasicDBObject dboq = new BasicDBObject();
        String rx = ".*utdData.*";
        dboq.put("filelineraw",  java.util.regex.Pattern.compile(rx));
        DBCursor cur = getCur("favsckckck", dboq);
        ArrayList arrl = new ArrayList();
        while (cur.hasNext())
        {
            arrl.add(cur.next().get("filelineraw"));
        }
        return arrl;
    }


    public static List<String> getAllUtdDatAsFLRStrList ()
    {
        BasicDBObject dboq = new BasicDBObject();
        String rx = ".*utdData.*";
        dboq.put("filelineraw",  java.util.regex.Pattern.compile(rx));
        DBCursor cur = getCur("favsckckck", dboq);
        ArrayList arrl = new ArrayList();
        while (cur.hasNext())
        {
            arrl.add(cur.next().get("filelineraw"));
        }
        return arrl;
    }


    public static List<String> getFLRStrList_gotmsg (String collname)
    {
        BasicDBObject dboq = new BasicDBObject();
        String rx = ".*msg::*";
        dboq.put("filelineraw",  java.util.regex.Pattern.compile(rx));
        DBCursor cur = getCur(collname, dboq);
        ArrayList arrl = new ArrayList();
        while (cur.hasNext())
        {
            arrl.add(cur.next().get("filelineraw"));
        }
        return arrl;
    }


    //	public static Map<String, String> getUtdDatAsMap (String subcollutddataname)
    //	{
    //		HashMap hmRtn = new HashMap();
    //		List l = getAllUtdDatAsFLRStrList()
    //		l.each {
    //			String flrstr = it;
    //			// 2012-02-14 01:54:41 : utdData::senderToUserMap/8454897511@txt.att.net/bkon
    //			String[] arrStrPostSplitUtdData = flrstr.split("utdData::")
    //			O.o("testing flrstr:" + flrstr)
    //			if (arrStrPostSplitUtdData.length != 2)
    //				throw new RuntimeException("UdData data element poorly formed1:" + flrstr)
    //			String strPreSlashSplit = arrStrPostSplitUtdData[1]
    //			String[] arrStrPostSplitSlash = strPreSlashSplit.split("/")
    //			if (arrStrPostSplitSlash.length != 3)
    //				throw new RuntimeException("UdData data element poorly formed2:" + flrstr)
    //			if (arrStrPostSplitSlash[0].equals (subcollutddataname))
    //			{
    //				hmRtn.put(arrStrPostSplitSlash[1], arrStrPostSplitSlash[2])
    //				O.o("got UtdMapData:" + arrStrPostSplitSlash[1] + "->" + arrStrPostSplitSlash[2])
    //			}
    //		}
    //		hmRtn;
    //	}


    //		Dbo dboq = new Dbo();
    //		String rx = ".*"+preStarLikeStarString+".*";
    //		//if (!casesens)
    //		rx = "(?i)"+rx; //  + "/i"
    //		dboq.put(fieldname,  java.util.regex.Pattern.compile(rx));


//    public static Set<String> getDomain(String collname, String field)
//    {
//        HashSet<String> hsRtn = new HashSet<String>();
//        def dbc = getColl(collname);
//        DBCursor cur = dbc.find();
//        while (cur.hasNext())
//        {
//            BasicDBObject dbo = ((BasicDBObject) cur.next());
//            String msgId = dbo.get(field);
//            if (msgId == null)
//                O.o("Warn: null msgid")
//            hsRtn.add(msgId)
//            O.o "added msgid:" + msgId;
//        }
//        return hsRtn;
//    }
	//    public static Set<String> getDomainEmailMsgIdsInDB()
	//    {
	//        return getDomain(Cfg.collnameEmailsSuckedIn, "GoogleMessageId");
	//    }
    



    public static BasicDBObject getFlrById(String id, String username) {
        BasicDBObject dboq = new BasicDBObject ();
        BasicDBObject dboFlrRtn = null;

        dboq.put("_id", new ObjectId(id));

        // from  getAllUtdDatAsFLRStrList
        //		String rx = ".*utdData.*";
        //		dboq.put("filelineraw",  java.util.regex.Pattern.compile(rx));
        DBCursor cur = getCur(Cfg.getCollNameThisUserFavs(username), dboq);
        ArrayList arrl = new ArrayList();
        while (cur.hasNext())
        {
            dboFlrRtn = (BasicDBObject) cur.next();
            //O.o "got one:" +slfr
            arrl.add(dboFlrRtn);
        }

        if (arrl.size() > 1)
            throw new RuntimeException("> 1 objs for one id:" + id + ", coll:" + Cfg.getCollNameThisUserFavs(username) );

        //O.o ("done getting getFlrById id:" + id + ", username:" + username + ", Cfg.getCollNameThisUserFavs(username):" + Cfg.getCollNameThisUserFavs(username))
        return (BasicDBObject) arrl.get(0); // array not needed, but as a template for a list fetcher
    }









    public static void listIndexes(String collname)
    {
        DBCollection coll = getColl(collname);
        List<DBObject> list = coll.getIndexInfo();

        for (DBObject o : list) {
            System.out.println(o);
        }
    }
//    public static boolean testSoupToNuts() {
//        // native style
//        String collname = "CollTest120815";
//        try
//        {
//
//            if (true) // from http://stackoverflow.com/questions/5917803/how-to-do-or-and-and-in-mongo-query-for-java-driver
//            {
//                //                BasicDBObject query = new BasicDBObject();
//                //                query.append("name", "Anbu");
//                //
//                //                BasicDBObject orQuery = new BasicDBObject();
//                //                orQuery.put("name", "Kalaio");
//                //                List<BasicDBObject> orQueries = new ArrayList<BasicDBObject>();
//                //                orQueries.add(orQuery);
//                //
//                //                orQuery = new BasicDBObject();
//                //
//                //                orQuery.put("name", "Kumar");
//                //                orQueries.add(orQuery);
//                //
//                //                orQuery = new BasicDBObject();
//                //                orQuery.put("$or", orQueries);
//                //
//                //                query.put("$or", orQueries);
//                //
//                //                DBCursor cur = coll.find(query);//Not Working
//                //                O.o "cur count1:" + cur.count();
//                //                DBCursor cur1 = coll.find(orQuery);//Not Working
//                //                O.o "cur count orQ :" + cur1.count();
//
//                //DBCursor cur = coll.find(orQuery);//Working
//            }
//
//
//            if (true)
//            {
//                UtilMongoSandbox.doWork()
//
//
//            }
//
//
//
//
//
//
//
//
//
//
//
//
//            if (false)
//            {
//
//
//
//
//
//                O.o "in testSoupToNuts collname:" + collname
//                DBCollection coll = (((new Mongo(Cfg.mongoip, Cfg.mongoport)).getDB(Cfg.dbname)).getCollection(collname));
//                coll.remove(new BasicDBObject ())
//                assert (coll.count() == 0);
//
//
//                def obj1 = new BasicDBObject ("test", "test1");
//                // IÎNSERT 1
//                coll.insert(obj1)
//
//
//
//
//                ArrayList x = new ArrayList();
//                x.add(1);
//                x.add(2);
//                x.add(new BasicDBObject("foo", "bar"));
//                x.add(4);
//
//                BasicDBObject doc = new BasicDBObject("x", x);
//
//                // INSERT 2
//                coll.insert(new BasicDBObject ("arrayWithHashTest", doc))
//
//                def found = 0;
//                coll.find(obj1).each {
//                    O.o found + ". found record :" + it
//                    found++
//                }
//
//                O.o "done found:" + found // 0found record :[_id:501f64820364be2250dcb611, test:test1]
//                assert found == 1;
//
//                // REMOVE 1
//                coll.remove(obj1);
//
//                assert (coll.count() == 1);
//
//                // REMOVE 2 (REMOVE ALL)
//                coll.remove(new BasicDBObject ())
//
//                assert (coll.count() == 0);
//
//            }
//
//
//
//
//
//
//        } catch (Throwable t ) {
//            O.or ("era", t)
//            t.printStackTrace();
//        }
//        finally {
//            O.o "done testSoupoToNuts collname:" + collname
//        }
//        //fail "Implement me"
//    }








    //    public static void main(String[] args)
    //    {
    //        try {
    //
    //            //		getDB()
    //            //		db.collection.find().sort({ _id : -1 }).limitIfGt0(10)
    //            //		BasicDBList dbl = new BasicDBList();
    //            //		dbl.put(1, "Test1")
    //            //		dbl.put(2, "Test2")
    //            //		dbl.put(3, "Test3")
    //            //		dbl.put(3, "Test4")
    //            //
    //            //		put("insert from test", "insert from test UtilMongoMain", dbl)
    //
    //            // (2) works!  purpose: test order in db call
    //
    //
    //            //						DBCursor cur = select("favsckckck", "filelineraw",
    //            //					"hk", "filelineraw", -1, 7) // or 1 for forward sort
    //            //
    //            //
    //            //			cur.eachWithIndex { it, i ->
    //            //				if (i < 10)
    //            //					O.o "t [${i}]. rrr:"+it
    //            //			}
    //            //			O.o "asdasd: done. showed first X of "+cur.count()
    //
    //            if (true)
    //            {
    //
    //            }
    //
    //            if (true)
    //            {
    //                testSoupToNuts()
    //            }
    //            if (false)
    //
    //            {
    //                BasicDBObject dboFlr = getFlrById("4f55a52203646313003865c4", "ckckck")
    //                if (dboFlr != null)
    //                    O.o "got dboFlr date [" +dboFlr.date + "] newText ["  + dboFlr.newText + "]"
    //                else
    //                    O.o("is null:")
    //            }
    //
    //            if (false)
    //            {
    //                BasicDBObject dboFlr = removeFavsRecById("4f55a52203646313003865c4", "ckckck")
    //
    //            }
    //
    //
    //            if (false)
    //            {
    //                O.o "asas1:" + getDomainEmailMsgIdsInDB()
    //                //O.o "asas:" + getDomainEmailMsgIdsInDB()
    //                O.o "asas2:" + getDomainEmailMsgIdsInDB().size()
    //            }
    //
    //
    //        }
    //        catch (Throwable t )
    //        {
    //            O.or("t", t);
    //            throw t;
    //            assert(false);
    //
    //        } finally {
    //            O.o "in finally done"
    //        }
    //    }

} // class 









//
//package com.mkyong.core;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
//import com.mongodb.DBObject;
//import com.mongodb.Mongo;
//import com.mongodb.MongoException;
//
///**
//* Java MongoDB : Query document
//*
//*/
//
//public class QueryDocumentApp {
//   public static void main(String[] args) {
//
//	   try {
//
//		   Mongo mongo = new Mongo("localhost", 27017);
//		   DB db = mongo.getDB("yourdb");
//
//		   // get a single collection
//		   DBCollection collection = db.getCollection("dummyColl");
//
//		   // insert number 1 to 10 for testing
//		   for (int i = 1; i <= 10; i++) {
//			   collection.insert(new BasicDBObject().append("number", i));
//		   }
//
//		   // get first document
//		   DBObject dbObject = collection.findOne();
//		   System.out.println(dbObject);
//
//		   // get all available documents
//		   DBCursor cursor = collection.find();
//		   while (cursor.hasNext()) {
//			   System.out.println(cursor.next());
//		   }
//
//		   // get document, where number = 5
//		   BasicDBObject query = new BasicDBObject();
//		   query.put("number", 5);
//		   DBCursor cursor2 = collection.find(query);
//		   while (cursor2.hasNext()) {
//			   System.out.println(cursor2.next());
//		   }
//
//		   // get document, where number = 9 and number = 10
//		   BasicDBObject query2 = new BasicDBObject();
//		   List<Integer> list = new ArrayList<Integer>();
//		   list.add(9);
//		   list.add(10);
//		   query2.put("number", new BasicDBObject("$in", list));
//		   DBCursor cursor3 = collection.find(query2);
//		   while (cursor3.hasNext()) {
//			   System.out.println(cursor3.next());
//		   }
//
//		   // get document, where number > 5
//		   BasicDBObject query3 = new BasicDBObject();
//		   query3.put("number", new BasicDBObject("$gt", 5));
//		   DBCursor cursor4 = collection.find(query3);
//		   while (cursor4.hasNext()) {
//			   System.out.println(cursor4.next());
//		   }
//
//		   // get document, where 5 < number < 8
//		   BasicDBObject query4 = new BasicDBObject();
//		   query4.put("number", new BasicDBObject("$gt", 5).append("$lt", 8));
//		   DBCursor cursor5 = collection.find(query4);
//		   while (cursor5.hasNext()) {
//			   System.out.println(cursor5.next());
//		   }
//
//		   // get document, where number != 8
//		   BasicDBObject query5 = new BasicDBObject();
//		   query5.put("number", new BasicDBObject("$ne", 8));
//		   DBCursor cursor6 = collection.find(query5);
//		   while (cursor6.hasNext()) {
//			   System.out.println(cursor6.next());
//		   }
//
//		   // collection.remove(new BasicDBObject());
//
//		   System.out.println("Done");
//
//	   } catch (UnknownHostException e) {
//		   e.printStackTrace();
//	   } catch (MongoException e) {
//		   e.printStackTrace();
//	   }
//
//   }
//}

// bottom java





//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//=== FIND BY ID ===============================================
//import com.mongodb.Mongo;
//import com.ustodo.utilg.UtilMongo;
//import com.ustodo.utilg.UtilMongoDboCreate;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
//import com.mongodb.DBCursor;
//import org.bson.types.ObjectId;
//import java.util.regex.Pattern;
//
//db = new Mongo( "192.168.1.197" , 27017 ).getDB("livetest");
//
//def pdf(db, dbo) {
//    try {
////println("Hello $dbo")
//        cur = db.getCollection("favsbkon").find(dbo);
//        int cnt = 0;
//        BasicDBObject dboFound;
//        while(cur.hasNext()) {
//            dboFound = cur.next();
//            cnt++;
//            System.out.println("------------------>"+dboFound);
//        }
//        System.out.println("===========================found:" + cnt);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}
//1) QUERY
//QUERY WITH REGEXP WORKS WIH ABOVE (this is a single line below):
//pdf ( db, new BasicDBObject().append("filelineraw",  java.util.regex.Pattern.compile("(?i).*"+com.ustodo.utilg.UtilRegEx.escapeMongoRequiredRegExChars("p?setsize=10&pict_id=0003477")+".*")))
//
//QUERY SIMPLE NO REGEX WORKS WIH ABOVE SIMPLEST:
//pdf ( db, new BasicDBObject().append("filelineraw",  java.util.regex.Pattern.compile("(?i).*"+com.ustodo.utilg.UtilRegEx.escapeMongoRequiredRegExChars("insurance")+".*")))
//
//UPDATE!! WORKS WIH ABOVE SIMPLEST:
//updateRecordTimestamp("51af7d33a6182033a24e3961", "bkon", "2013-06-05 13:59:53")
//
//
//2013-06-05 13:59:53
//51af7d33a6182033a24e3961
//import java.newText.Normalizer;
//2) TRY UPDATING DATE BY USER AND RECORD ID
//BasicDBObject dboWhere = new BasicDBObject ().append ("_id", new ObjectId("51af7d33a6182033a24e3961"));
//
//BasicDBObject dboToUpdate = getFlrById("51af7d33a6182033a24e3961", "bkon");
//def newtext2 = dboToUpdate.get("newText")
//
//
//BasicDBObject dboSet = new BasicDBObject();
//dboSet.append("filelineraw", "2013-06-05 13:59:53" + " " + newtext2);
//dboSet.append("date", strDateInFileFormat);
//dboSet.append("newText", newtext2);
//BasicDBObject dboSetter = new BasicDBObject("\$set", dboSet);
//
//
//
//
//
//// String original = "aáeéiíoóöőuúüű AÁEÉIÍOÓÖŐUÚÜŰ";
//
//
//def removeAccents( newText) {
//    return newText == null ? null
//    : Normalizer.normalize(newText, Normalizer.Form.NFD)
//                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
//}
//
//
//
//// ================================ 1
//
//pdf ( db, UtilMongoDboCreate.getDboLike("filelineraw","http", false))
//pdf ( db, UtilMongoDboCreate.getDboLike("filelineraw", "httpx=*?/", fals
//        pdf ( db, UtilMongoDboCreate.getDboLike("filelineraw", "tester=*?/", false))
//        pdf ( db, UtilMongoDboCreate.getDboLike("filelineraw", "p?setsize=10&pict_id=0003477", false))
//
//
//
//
//// ================================ 2
////my own dbo - not using like builder
//        //  prestarstar = com.ustodo.utilg.UtilRegEx.escapeMongoRequiredRegExChars("p?setsize=10&pict_id=0003477")
//        prestarstar = com.ustodo.utilg.UtilRegEx.escapeMongoRequiredRegExChars("tester")
//
//        dbo = new BasicDBObject();
//        rx = "(?i).*"+prestarstar+".*";
//        dbo.put("filelineraw",  java.util.regex.Pattern.compile(rx));
//        pdf ( db, dbo)
//
//// ================================ 3
//        rx = ;
//
//        pdf ( db, new BasicDBObject().append("filelineraw",  java.util.regex.Pattern.compile("(?i).*"+com.ustodo.utilg.UtilRegEx.escapeMongoRequiredRegExChars("p?setsize=10&pict_id=0003477")
//                +".*")))
//
//        TRYING
//        pdf ( db, new BasicDBObject().append("filelineraw", Pattern.compile("(?i).*[?]setsize=10[&]pict_id=0003477.*")))
//        pdf ( db, new BasicDBObject().append("filelineraw", Pattern.compile("(?i).*Physician Directory.*")))
//
//
//
//// ================================ 4
//
//
//        WORKS BY DB ID 130520
//        pdf ( db, new BasicDBObject("_id", new ObjectId("5195fa410364705529838bbc")))
//
//
//// ================================== 5
//        dbo = UtilMongoDboCreate.getDboLike("filelineraw", "httpx=*?/", false)
//        dbo = UtilMongoDboCreate.getDboLike("filelineraw", "httpx=\\*\\?/", false)
//
//        dbo = UtilMongoDboCreate.getDboLike("filelineraw", "httpx=", false)
//
//
//
//
//        db = new Mongo( "192.168.1.197" , 27017 ).getDB("livetest");
//        dbo = UtilMongoDboCreate.getDboLike("filelineraw", "httpx=\\*\\?/", false)
//        pdf ( db, dbo)
//
//
//        db = new Mongo( "127.0.0.1" , 27017 ).getDB("livetest");
//
//        eėw
////   BasicDBObject dboq1 = getDboLike("newText", "tester", false)
//        pdf ( db, new BasicDBObject("_id", new ObjectId("517a420e0364967c668af5df")))
//        pdf ( db, UtilMongoDboCreate.getDboLike("filelineraw", "httpx=*?", false)) // works
//        pdf ( db, UtilMongoDboCreate.getDboLike("filelineraw", "httpx=*?/", false)) //
//
//        pdf ( db, UtilMongoDboCreate.getDboLike("filelineraw", "httpx", false))
//        pdf ( db, UtilMongoDboCreate.getDboLike("filelineraw", "httpx", false))
//
//        works!  "Hello/You/There?/".replaceAll("\\?/", "a");  // need to double escape the ‘?’
//        works   "Hello/You/There=?/".replaceAll("=\\?/", "a");
//        works finale!  "httpx=*?/".replaceAll("=\\*\\?/", "a");
//        test /  "httpx=*?/".replaceAll("=\\*\\?\\/", "a");
//
//        works!  dbo = UtilMongoDboCreate.getDboLike("filelineraw", "httpx=\\*\\?/", false)
//
//        http://stackoverflow.com/questions/399078/what-special-characters-must-be-escaped-in-regular-expressions
//
//
//
//
//
//
//
//
//
//
//
//
//// ===============================
//
//        === END FIND BY ID ===============================================
//
//
//        //query.put("i", new BasicDBObject("$gt", 50));  // e.g. find all where i > 50
//        //"_id" : { "$oid" : "4f09e3f20364589a5c3c7067"} ,
//
////optional
//        boolean auth = db.authenticate(myUserName, myPassword);
//
//        Set<String> colls = db.getCollectionNames();
//
//for (String s : colls) {
//    System.out.println(“for:” + s);
//}
//colls.each
//
//
//
//
//this gdocs file is a backup - find the file on disk by opening folder … from console
//FILE PATH:
///Users/hkon/sw/ustodo/110504utd/ustodo-1.1.1/src/consoleTestsHbk/MongoConsoleShellHBK.groovy
//
//
//
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.Mongo;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBCursor;
//import com.ustodo.utilj.O;
//import com.ustodo.utilg.UtilMongo;
//
//if (true)
//{
//    def al = com.ustodo.utilg.UtilFile.fileAsList("/Users/hkon/sw/ustodo/favsckckck.csv");
//    int a = al.size();
//
//    int b = getCollAndDBCountByName("console", "livetest", "favsckckck");
//    assert (a == b);
//
//}
//
//
//def o(s)
//{
//    System.out.println("console O:" + s)
//}
//
//m = new Mongo();
//Set<String> sdb = m.getDatabaseNames();
//sdb.each() { dbname ->
//    o "list db names:" + dbname
//    schemaGetCollectionNames(dbname).each { collname ->
//        o dbname + "." + collname
//    }
//}
//
////String DB_NAME = "T110703_FAVSCKCKCK8_DB110724_084225"
////String COLL_NAME = "T110703_FAVSCKCKCK8_COLL110724_084225"
////DBCollection dbc = utilMongoGetColl("mongo groovy console", DB_NAME, COLL_NAME)
//
//
//
////System.exit(0);
//if (false)
//{
//    String NAME = "T110703_FAVSCKCKCK8"
//    DBCollection collread = utilMongoCookStdDBandColl("mongo groovy console", NAME, "110725_090100")
//
//    collread.findOne().each() {
//        o "eee:" + it
//    }
//
//    //            DBCollection collread = utilMongoGetColl("test read phase 2", DB_NAME, COLL_NAME);
//    BasicDBObject query = new BasicDBObject();
//    //query.put ("count", 1)
//
//    //    query.put ({ filelineraw : { $regex : /start.*shut/i, $nin : ['acmeblahcorp'] } })
//
//    BasicDBObject q = new BasicDBObject();
//    //String m = /[z]+/
//    //String m = /.*123.*/
//    String m = /.*1.*/
//    O.fmt("m.toString()", m.toString()) ;
//    q.put("filelineraw",  java.util.regex.Pattern.compile(m));
//    int collCount = collread.getCount();
//    DBCursor cur = collread.find(q);
//    int k = 0;
//    while(cur.hasNext()) {
//
//        String curnext = cur.next();
//        //if (k % countMod == 0)
//        if (k % 1 == 0)
//        {
//            O.o("phase 2 doc from: " + O.fmt ("NAME", NAME) + O.fmt("k", k) + O.fmt("curnext", curnext));
//        }
//        k++;
//    }
//
//    O.o("got [" + k + "] results.")
//
//
//}
//
//


