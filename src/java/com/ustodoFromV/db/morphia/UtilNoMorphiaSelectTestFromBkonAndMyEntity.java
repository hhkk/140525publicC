package com.ustodoFromV.db.morphia;

import com.mongodb.DBCursor;
import com.ustodoFromV.util.O;
import com.ustodoFromV.db.mongo.UtilMongoJ;

public class UtilNoMorphiaSelectTestFromBkonAndMyEntity {
	
	public static void main(String[] sa)
	{
		// String[] sa2 = {"wave", "wave"}; // works 
		// String[] sa2 = null; // works 
		String[] sa2 = null; 
		DBCursor cur = UtilMongoJ.selectlocal("favsbkon", "filelineraw", sa2, null);
		// Wed Mar 05 00:17:31 EST 2014. o:{ "_id" : { "$oid" : "512db9440364ed43a6347a76"} , "date" : "2012-08-05 13:59:53" , "filelineraw" : "2012-08-05 13:59:53 books / anna karenina" , "html" : "books / anna karenina" , "text" : "books / anna karenina"}
		//DBCursor cur = UtilMongoJ.selectlocal("MyEntity", null, null);
		// Wed Mar 05 00:15:48 EST 2014. o:{ "_id" : { "$oid" : "53151cb83e07a19cd4987ea7"} , "className" : "com.ustodo.domain.MyEntity" , "name" : "{ \"_id\" : { \"$oid\" : \"4f09e3e50364589a5c3c2c6b\"} , \"icnt\" : 3152 , \"filelineraw\" : \"1999-11-10 13:06:03 instr unix / instr bash / bash-2.02$ du -s * | sort -nr | head    / disk files give me 10 largest\" , \"date\" : \"1999-11-10 13:06:03\" , \"text\" : \"instr unix / instr bash / bash-2.02$ du -s * | sort -nr | head    / disk files give me 10 largest\"}" , "version" : 1393892536447}

		
		while (cur.hasNext())
		{
			Object o = cur.next();
			O.o("o:" + o);
			
		}
		
		//https://morphia.googlecode.com/svn/site/morphia/apidocs/com/google/code/morphia/query/WhereCriteria.html#addTo(com.mongodb.DBObject)	
	}

}
