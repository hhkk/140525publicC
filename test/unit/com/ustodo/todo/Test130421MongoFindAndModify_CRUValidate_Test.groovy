package com.ustodo.todo

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.WriteConcern
import com.ustodo.utilg.O
import com.ustodo.utilg.UtilDate
import com.ustodo.utilg.UtilMongo
import org.bson.types.ObjectId
import com.ustodo.UtilTestHelper

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

class Test130421MongoFindAndModify_CRUValidate_Test extends UtilTestHelper {

    void testFindAndModifyFLRRecordDate()
    {
        String dbid;
        String recordtext = 'unitesttester';

        String firstDateString = UtilDate.getDateForFile()
        O.oNoFilter("firstDateString:"+firstDateString);

        // step1 insert
        try {   // see http://svn.codehaus.org/grails/tags/GRAILS_RELEASE_1_0_RC4/test/web/org/codehaus/groovy/grails/web/servlet/mvc/GrailsParameterMapTests.groovy

            DBCollection coll = UtilMongo.getCollFavs(uthUserUnitTestUser);
            coll.setWriteConcern(WriteConcern.FSYNC_SAFE);

            HashMap hm = new HashMap();
            hm.put("filelineraw", firstDateString + " " + recordtext);
            hm.put("date", firstDateString);
            hm.put("CreateDate",firstDateString);
            hm.put("text", recordtext);
            hm.put("type", "filelineraw");
            hm.put("count", 1);

            BasicDBObject doc = new BasicDBObject(hm);
            def cnt = coll.count()

            //O.o "coll insert 3"
            //coll.setWriteConcern(WriteConcern.FSYNC_SAFE);
            //coll.insert(doc);
            coll.insert(doc);
            def cnt2 = coll.count()
            assert (cnt == cnt2-1)

            //assert (dboModified != null);
        } catch (Throwable t) {
            O.oerr 'error1 in unit test', t;
            t.printStackTrace();
            throw t;
        }


        // step2 read (get dbid for update)
        try {   // see http://svn.codehaus.org/grails/tags/GRAILS_RELEASE_1_0_RC4/test/web/org/codehaus/groovy/grails/web/servlet/mvc/GrailsParameterMapTests.groovy

            def cursor;
            try
            {
                HashMap hm = new HashMap();
                hm.put("filelineraw", firstDateString + " " + recordtext);
                hm.put("date", firstDateString);
                hm.put("CreateDate",firstDateString);
                hm.put("text", recordtext);
                hm.put("type", "filelineraw");
                hm.put("count", 1);

                cursor = UtilMongo.getCur(com.ustodo.utilg.Cfg.getCollNameThisUserFavs(uthUserUnitTestUser), new BasicDBObject(hm))
                int i = 0;
                while(cursor.hasNext())
                {
                    i++;
                    BasicDBObject dboResult = cursor.next();
                    assert (dboResult.filelineraw.equals(firstDateString + " " + recordtext))
                    assert (dboResult.date.equals(firstDateString))
                    assert (dboResult.CreateDate.equals(firstDateString))
                    assert (dboResult.text.equals(recordtext))
                    assert (dboResult.type.equals("filelineraw"))
                    //O.oNoFilter("dboResult.count.getClass():"+dboResult.count.getClass()) // Integer
                    assert (((Integer)dboResult.count).intValue() == 1)
                    dbid = dboResult._id;
                    O.oNoFilter("dbid:"+dbid)
                }
                assert (i == 1);
            } catch (Throwable t) {
                O.oerr 'error3 in unit test', t;
                t.printStackTrace();
            }
            finally {
                assert (cursor != null)
                cursor.close();
            }
            //O.oNoFilter("donetests2")

        } catch (Throwable t) {
            O.oerr 'error4 in unit test', t;
            t.printStackTrace();
        }

        Thread.sleep(1029) // guarantee unique date


        // step3 update by id
        String newDateString = UtilDate.getDateForFile()
        try {   // see http://svn.codehaus.org/grails/tags/GRAILS_RELEASE_1_0_RC4/test/web/org/codehaus/groovy/grails/web/servlet/mvc/GrailsParameterMapTests.groovy
            DBCollection coll = UtilMongo.getCollFavs(uthUserUnitTestUser);
            BasicDBObject dboWhere = new BasicDBObject ().append ("_id", new ObjectId(dbid));
            O.oNoFilter("newDateString:"+newDateString);
            BasicDBObject dboValues = new BasicDBObject ().append("\$set", new BasicDBObject ().append("date", newDateString));
            BasicDBObject dboModified = UtilMongo.updateRecord_UsingMongoFindAndModify(coll, dboValues, dboWhere);
        } catch (Throwable t) {
            O.oerr 'error in unit test', t;
            t.printStackTrace();
            throw t;
        }


        // step4 validate
        try {   // see http://svn.codehaus.org/grails/tags/GRAILS_RELEASE_1_0_RC4/test/web/org/codehaus/groovy/grails/web/servlet/mvc/GrailsParameterMapTests.groovy

            def cursor;
            try
            {
                DBCollection coll = UtilMongo.getCollFavs(uthUserUnitTestUser);
                BasicDBObject dboWhere = new BasicDBObject ().append ("_id", new ObjectId(dbid));
                cursor = UtilMongo.getCur(com.ustodo.utilg.Cfg.getCollNameThisUserFavs(uthUserUnitTestUser), dboWhere)
                int i = 0;
                 //O.oNoFilter("start test")
                while(cursor.hasNext())
                {
                    i++;
                    BasicDBObject dboResult = cursor.next();
                    // O.oNoFilter("donetests1")
                    assert (dboResult.date.equals(newDateString))
                    assert (dboResult.text.equals(recordtext))
                }
                assert (i == 1);
            } catch (Throwable t) {
                O.oerr 'error3 in unit test', t;
                t.printStackTrace();
            }
            finally {
                assert (cursor != null)
                cursor.close();
            }
            //O.oNoFilter("donetests2")

        } catch (Throwable t) {
            O.oerr 'error4 in unit test', t;
            t.printStackTrace();
            throw t;
        }

    }





}
