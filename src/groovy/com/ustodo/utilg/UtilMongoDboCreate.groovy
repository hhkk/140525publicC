
package com.ustodo.utilg

import com.mongodb.*
import com.mongodb.gridfs.GridFS
import org.bson.types.ObjectId

class UtilMongoDboCreate
{

    public static com.mongodb.BasicDBObject buildDboAndLike (String fieldToSrchOrNull, String[] sArrSrchTermsAndPreStarStar)
    {
        BasicDBList basicDBList = new BasicDBList();

        int i = 0;
        sArrSrchTermsAndPreStarStar.each {
            //if (it[0].equals("["))
            //    it = it[1..-1]

            basicDBList.add(UtilMongoDboCreate.getDboLike(fieldToSrchOrNull, it, false))
        }
        return new BasicDBObject('$and', basicDBList);
    }

    public static DBObject getDboLike(String field, String prestarstar, boolean caseSens)
    {
        String prestarstarSav_forPrePostCompare = prestarstar;
        prestarstar = UtilRegEx.escapeMongoRequiredRegExChars(prestarstar)

        //O.oNoFilter("in here 4")
        try {
            //def o = null;
            //O.oNoFilter(o.tester());
        } catch (Exception e ) {
            O.oNoFilter("in here bad")
            e.printStackTrace()
        }
        //O.oNoFilter("in here done")
        //O.o("---> in getDboLike field [" + field + "] prestarstar ["+prestarstar +"]")
        BasicDBObject dboq = new BasicDBObject();

        //{
            // http://stackoverflow.com/questions/399078/what-special-characters-must-be-escaped-in-regular-expressions
            // "httpx=*?/".replaceAll("=\\*\\?/", "a");  works


            //        s = s.replaceAll("\\.", "\\\\\\\\.");
            //        s = s.replaceAll("\\^", "\\\\\\\\*");
            //s = s.replaceAll("h", "yy");
            //s = s.replaceAll("\\^", "\\\\\\\\^");
            //        s = s.replaceAll("\\-", "\\\\\\\\-");
            //        s = s.replaceAll("\\+", "\\\\\\\\+");

            //s = s.replaceAll("\\$", "\\\\\\\\$");
            // .^$*+?()[{\|
        //}



        // O.o ("==================== converted pre search [" + prestarstar + "] to [" + preStarStarEscaped + "]")

        String rx = ".*"+prestarstar+".*";
        //String rx = prestarstar;

        //O.o("java.util.regex.Pattern.compile(rx).getClass___________________________ :" + o.getClass().getName()); // java.util.regex.Pattern
        if (!caseSens)
            rx = "(?i)"+rx; //  + "/i"
        //O.o (" add like and ******** >> from HHKK" + prestarstarSav_forPrePostCompare + "HHKK to rx HHKK" + rx + "HHKK")

        dboq.put(field,  java.util.regex.Pattern.compile(rx));
        dboq;
    }

}
