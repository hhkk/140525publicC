package com.ustodo.todo

    import com.mongodb.BasicDBList
    import com.mongodb.BasicDBObject
import com.ustodo.UtilTestHelper
import com.ustodo.utilg.O
    import com.ustodo.utilg.UtilMongo
import com.ustodo.utilg.UtilMongoDboCreate

/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 5/3/13
 * Time: 9:47 PM
 * To change this template use File | Settings | File Templates.
 */
class Test130503MongoRegexInsertSearchTests extends UtilTestHelper {


    def testRegExFindWithDollarSignsAndSuch()    {
        print 'hi'
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
                assert (cursor != null)
                cursor.close();
            }

        } catch (Throwable t) {
            O.oerr 'error4 in unit test', t;
            t.printStackTrace();
            throw t;
        }
    }
}
