package com.ustodo.utilg

import com.mongodb.BasicDBList
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.DBObject
import com.mongodb.MongoException
import com.mongodb.QueryBuilder
import com.mongodb.Mongo

class UtilMongoSandbox {
	// from https://github.com/mongodb/mongo-java-driver/downloads
	// from http://www.mongodb.org/display/DOCS/Java+Tutorial



	private static boolean loadedConfig = Cfg.configSlurpDoIt();

	private static void shortSyntax()
	{
		DBCollection collection = UtilMongo.getColl("seeUtilMongoSandbox.java");

		// insert number 1 to 10 for testing
		for (int i = 1; i <= 10; i++) {
			collection.insert(new BasicDBObject().append("number", i));
		}
		def i = 6
	    collection.insert ( ['number':i] as BasicDBObject)
		O.o "coll count:" + collection.count
		def cur = UtilMongo.getColl("seeUtilMongoSandbox.java").find([number:['$lt':3]] as BasicDBObject)
		while(cur.hasNext()) {
			println "shortSyntax:" + cur.next()
		}
	}

    public static void testArrayDataType() // hbk120816
    {
        // from http://www.mongodb.org/display/DOCS/Java+Types#JavaTypes-Arrays
        ArrayList x = new ArrayList();
        x.add(1);
        x.add(2);
        x.add(3);
        x.add(new BasicDBObject("foo", "bar"));
        x.add(new BasicDBObject("foo2", new BasicDBObject("foo3", "bar3")));
        x.add(4);

        BasicDBObject doc = new BasicDBObject("x", x);

        DBCollection coll = (((new Mongo(Cfg.mongoip, Cfg.mongoport)).getDB(Cfg.dbname)).getCollection("test120815"));

        coll.remove(new BasicDBObject())
        assert (0 == coll.count())
        coll.insert(doc)
        assert (1 == coll.count())

        coll.find(new BasicDBObject("x", 1)).each {
            O.o "1:" + it
        }

        coll.find(new BasicDBObject("x.foo", "bar")).each {
            O.o "2:" + it
        }

        coll.find(new BasicDBObject("x.foo2.foo3", "bar3")).each {
            O.o "3:" + it
        }

        coll.find(new BasicDBObject("x.foo2.foo3", "barxxx")).each {
            O.o "4:" + it
        }



    }

	public static void doWork()
	{
		try {

            if (false)
            {

                DBCollection collection = UtilMongo.getColl("seeUtilMongoSandbox.java");

                O.o""
                // get first document
                DBObject dbObject = collection.findOne();
                O.o("0 " + dbObject);

                // get all available documents
                O.o""
                DBCursor cursor = collection.find();
                while (cursor.hasNext()) {
                    O.o("1 " + cursor.next());
                }

                // get document, where number = 5
                O.o""
                BasicDBObject query = new BasicDBObject();
                query.put("number", 5);
                DBCursor cursor2 = collection.find(query);
                while (cursor2.hasNext()) {
                    O.o("2 " + cursor2.next());
                }

                // get document, where number = 9 and number = 10
                O.o""
                BasicDBObject query2 = new BasicDBObject();
                List<Integer> list = new ArrayList<Integer>();
                list.add(9);
                list.add(10);
                query2.put("number", new BasicDBObject('$in', list));
                DBCursor cursor3 = collection.find(query2);
                cursor3.each {
                    O.o("3 " + it);
                }

                // get document, where number > 5
                O.o""
                BasicDBObject query3 = new BasicDBObject();
                query3.put("number", new BasicDBObject('$gt', 5));
                DBCursor cursor4 = collection.find(query3);
                while (cursor4.hasNext()) {
                    O.o("4 " + cursor4.next());
                }



                // get document, where 5 < number < 8
                O.o""
                BasicDBObject query4 = new BasicDBObject();
                query4.put("number", new BasicDBObject('$gt', 5).append('$lt', 8).append('$lt', 7));
                DBCursor cursor5 = collection.find(query4);
                while (cursor5.hasNext())
                {
                    O.o("5: " + cursor5.next());
                }
            }



			// get document, where 5 < number < 8 - using $AND
			if (false)
			{
                DBCollection collection = UtilMongo.getColl("seeUtilMongoSandbox.java");


                O.o""
				BasicDBObject query45 = new BasicDBObject();
				query45.put("number", new BasicDBObject('$gt', 5));
				BasicDBObject query452 = new BasicDBObject();
				query452.put("number", new BasicDBObject('$lt', 3));
				BasicDBObject query453 = new BasicDBObject();
				query453.put("number", new BasicDBObject('$gt', 5).append('$lt', 8).append('$lt', 7));
				BasicDBObject query44 = new BasicDBObject();
				//query44.put('$or', new BasicDBObject().append(query45).append(query452));
				query44.put('$or', query45);
				query44.put('$or', query453);
				DBCursor cursor55 = collection.find(query44);
				while (cursor55.hasNext())
				{
					O.o("55 " + cursor55.next());
				}

                // get document, where number != 8
                O.o""
                BasicDBObject query5 = new BasicDBObject();
                query5.put("number", new BasicDBObject('$ne', 8));
                DBCursor cursor6 = collection.find(query5);
                while (cursor6.hasNext()) {
                    O.o("6 " + cursor6.next());
                }

            }

			shortSyntax()

            if (true ) // hbk 120815 120816
            {
                DBCollection coll = (((new Mongo(Cfg.mongoip, Cfg.mongoport)).getDB(Cfg.dbname)).getCollection("test120815"));

                def bdo1 = new BasicDBObject("em", "1");
                def bdo2 = new BasicDBObject("em", "2");
                coll.insert([bdo1, bdo2]);
                //DBObject findObj = QueryBuilder.start().or(bdo2.or(bdo1)).get();
                //DBObject findObj = QueryBuilder.start().or(new BasicDBObject("un","shailesh@gmaillcom")).or(new BasicDBObject("em", "shai...@gmail.com")).get()

                // works DBCursor cur = coll.find(QueryBuilder.start().or(bdo1).or(bdo2).get()) // works
                DBCursor cur = coll.find(QueryBuilder.start().or(bdo1).or(bdo2).get())

                def i = 0;
                while (cur.hasNext())
                {
                    O.o "QueryBuilder out# " + i++ + ":" + cur.next();
                }

            }



			O.o("Done");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}


	}

	// see compound and or queries / BasicDBObjectBuilder / https://groups.google.com/forum/#!topic/mongodb-user/-f4QgAUUCSs

	private static void testOrQueryNode()
	{
		// from http://stackoverflow.com/questions/5917803/how-to-do-or-and-and-in-mongo-query-for-java-driver
		// ------- INSERTS
		DBCollection coll = UtilMongo.getColl("seeUtilMongoSandbox.java");
		UtilMongo.removeAll(coll, false)
		coll.insert(new BasicDBObject("name", "111")) // note can add record with insert new DBO or with an o.put or with append
		coll.insert(new BasicDBObject("name", "222"));
		coll.insert(new BasicDBObject().append ("name", "333").append("lastname", "444"))
		coll.insert(new BasicDBObject().append ("name", "555").append("name", "666"))
		coll.find().each {O.o("all:" + it)}


		// ------- QUERY
		BasicDBObject query1 = new BasicDBObject("lastname", "444"); // utd120816example1
		BasicDBList orQueries2 = new BasicDBList();
		orQueries2.add(new BasicDBObject("name", "222"));
		orQueries2.add(new BasicDBObject("name", "333"));
		query1.put('$or', orQueries2);
		O.o "query1 in:" + query1
		coll.find(query1).each {O.o("query1 out:" + it)}

		BasicDBObject orQuery2 = new BasicDBObject('$or', orQueries2);
		O.o "orquery2 in:" + orQuery2
		coll.find(orQuery2).each {O.o("orQuery2 out:" + it)} // works
		//DBCursor cur = coll.find(query);//Not Working

	}

	//		List<BasicDBObject> orQueries = new ArrayList<BasicDBObject>();
	//		orQueries.add(new BasicDBObject("name", "222"));
	//		orQueries.add(new BasicDBObject("name", "333"));


	public static void main (String[] args)
	{
		try {


            if (false)
            {
                O.oc("top level", new BasicDBObject('$gt', 5).append('$lt', 8))
                def i = 1
                DBCollection coll = UtilMongo.getColl("seeUtilMongoSandbox.java");
                coll.insert ( ['number':i] as BasicDBObject)
                coll.insert ( ['number':2] as BasicDBObject)
                coll.insert ( ['number':3] as BasicDBObject)
                coll.insert ( ['number':4] as BasicDBObject)
                coll.insert ( ['number':5] as BasicDBObject)
                coll.insert ( ['number':6] as BasicDBObject)
                coll.insert ( ['number':7] as BasicDBObject)
                coll.insert ( ['number':8] as BasicDBObject)
                coll.insert ( ['number':9] as BasicDBObject)
                coll.insert ( ['number':10] as BasicDBObject)

                // doWork()

            }

            if (false)
                testOrQueryNode()

            if (false)
                testOrQueryNode()

            if (false)
                doWork()

            if (true)
                testArrayDataType()




        } catch (Exception e) {
			O.or ("bottomOfMain", e)
		}
		finally
		{
			// empty coll
			DBCollection coll = UtilMongo.getColl("seeUtilMongoSandbox.java");
			coll.remove(new BasicDBObject());
			O.o "doneAll"
		}

	}



	//	def m = new Mongo()
	//
	//	def db = m.getDB("mydb")
	//	def coll = db.getCollection("testCollection")
	//
	//	coll.drop()
	//
	//	def doc = [name:"MongoDB", type:"database", count:1,
	//				info: [x:203, y:102]
	//			  ] as BasicDBObject
	//	def doc2 = [name:"MongoDB2", type:"database", count:2,
	//		info: [x:203, y:102] ] as BasicDBObject
	//
	//
	//	coll.insert(doc)
	//	coll.insert(doc2)
	//
	//	println coll.getCount()
	//
	//	def obj = coll.findOne([count:1] as BasicDBObject)
	//	println obj
	//
	//	println "showing a custom query"
	//	def cur = coll.find([count:['$lt':3]] as BasicDBObject)
	//	while(cur.hasNext()) {
	//		println cur.next()
	//	}
	//







}







//
//public class QueryDocumentApp {
//   public static void main(String[] args) {
//
//   }
//}






