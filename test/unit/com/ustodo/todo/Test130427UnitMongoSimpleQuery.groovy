package com.ustodo.todo

import com.mongodb.BasicDBList
import com.mongodb.BasicDBObject
import com.mongodb.Mongo
import com.ustodo.UtilTestHelper
import com.ustodo.utilg.O
import com.ustodo.utilg.UtilMongo
import com.ustodo.utilg.UtilMongoDboCreate
import grails.test.GrailsUnitTestCase
import org.bson.types.ObjectId

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
class Test130427UnitMongoSimpleQuery extends UtilTestHelper {
    void setUp() {
        super.setUp();
    }

    void tearDown(){
   }

    void testSimpleFind130427()
    {
        try {

            def cursor;
            try {
                String collname = com.ustodo.utilg.Cfg.getCollNameThisUserFavs(uthUserUnitTestUser);
                //BasicDBObject dboWhere = new BasicDBObject ().append ("text", new BasicDBObject("\$exists", false ));

                BasicDBObject dboq1 = UtilMongoDboCreate.getDboLike("text", "tester", false)
                //BasicDBObject dboq2 = UtilMongo.getDboLike("text", "time", false)

                BasicDBList dbAnd = new BasicDBList();
                dbAnd.add(dboq1);
                //dbAnd.add(dboq2);
                BasicDBObject andQuery = new BasicDBObject('$and', dbAnd);

                // http://stackoverflow.com/questions/8567469/mongodb-find-a-document-by-non-existence-of-a-field
                // db.mycollection.find( { "price" : { "$exists" : false } } )
                cursor = UtilMongo.getCur(collname, andQuery)
                int i = 0;
                while(cursor.hasNext()) {
                    i++;
                    BasicDBObject dboResult = cursor.next();

                    //assert (dboResult.date.equals(newDateString))
                    System.out.println ("dboResult:" + dboResult.get('text'))
                }
                System.out.println ("cnt:" + i)

            } catch (Throwable t) {
                O.oerr 'error3 in unit test', t;
                t.printStackTrace();
                throw t;
            }
            finally {
                assertTrue (cursor != null)
                cursor.close();
            }

        } catch (Throwable t) {
            O.oerr 'error4 in unit test', t;
            t.printStackTrace();
            throw t;
        }
    }
}
