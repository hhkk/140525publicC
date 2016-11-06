    package com.ustodo.todo

import com.mongodb.BasicDBObject
    import com.ustodo.UtilTestHelper
    import com.ustodo.utilg.O
import com.ustodo.utilg.UtilMongo

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

class Test130421UnitMongoFindFieldNotExistsTest extends UtilTestHelper {

    void testFindNotExists()
    {
        try {

            def cursor;
            try {
                String collname = com.ustodo.utilg.Cfg.getCollNameThisUserFavs(uthUserUnitTestUser);
                BasicDBObject dboWhere = new BasicDBObject ().append ("text", new BasicDBObject("\$exists", false ));
                // http://stackoverflow.com/questions/8567469/mongodb-find-a-document-by-non-existence-of-a-field
                // db.mycollection.find( { "price" : { "$exists" : false } } )
                cursor = UtilMongo.getCur(collname, dboWhere)
                int i = 0;
                while(cursor.hasNext()) {
                    i++;
                    BasicDBObject dboResult = cursor.next();

                    //assert (dboResult.date.equals(newDateString))
                    System.out.println ("dboResult:" + dboResult)
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
