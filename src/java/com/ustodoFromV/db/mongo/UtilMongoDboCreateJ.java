package com.ustodoFromV.db.mongo;


import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.ustodoFromV.util.O;
import com.ustodoFromV.util.UtilRegExJ;

public class UtilMongoDboCreateJ
{

    public static com.mongodb.BasicDBObject buildDboAndLike (String fieldToSrchOrNull, String[] sArrAndMatch)
    {
        BasicDBList basicDBList = new BasicDBList();

        int i = 0;
        for (int j = 0; j < sArrAndMatch.length; j++ ) 
        {
            //if (it[0].equals("["))
            //    it = it[1..-1]

            basicDBList.add(UtilMongoDboCreateJ.getDboLike(fieldToSrchOrNull, sArrAndMatch[j], false));
        }
        return new BasicDBObject("$and", basicDBList);
    }

    public static DBObject getDboLike(String field, String prestarstar, boolean caseSens)
    {
        prestarstar = UtilRegExJ.escapeMongoRequiredRegExChars(prestarstar);

        //O.oNoFilter("in here 4")
        try {
            //def o = null;
            //O.oNoFilter(o.tester());
        } catch (Exception e ) {
            O.o("in here bad");
            e.printStackTrace();
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
        return dboq;
    }

}
