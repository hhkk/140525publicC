package com.ustodoFromV.db.morphia;

import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.ustodoFromV.Cfg;
import com.ustodoFromV.db.mongo.UtilMongoDboCreateJ;
import com.ustodoFromV.db.mongo.UtilMongoJ;
import com.ustodoFromV.domain.EntityBaseUtd;

public class UtilMorphia {


	//public static EntityBaseUtd testMorphiaFromMongoDBuery(Class<? extends EntityBaseUtd> clazz,  String collname, String field, String spattern)



	//	public static <T> T getOne(Class<T> entityClass, String field, String spattern)
	//	{
	//		String collname = entityClass.toString().substring(entityClass.toString().lastIndexOf('.') + 1); 
	//		DBObject dbObj = (DBObject) UtilMongoJ.getColl(collname).findOne(com.ustodo.db.mongo.UtilMongoDboCreateJ.buildDboAndLike(field, new String[] {spattern, spattern}));
	//
	//		Morphia morphia = new Morphia();
	//		morphia.mapPackageFromClass(EntityBaseUtd.class);
	//		morphia.mapPackageFromClass(EntityUser.class);
	//		
	//		T t = null;
	//		
	//		if (dbObj != null)
	//		{
	//			O.o("dbObjMyEntity.toString():" + dbObj.toString());
	//			t = morphia.fromDBObject(entityClass, dbObj);
	//		}
	//		return t;
	//	}
	//	
	//
	//	
	public static void updateById()
	{
		//		MyEntity vacancy1 = new MyEntity("123");
		//		vacancy1.setName("Dumm");
		//		MyEntity vacancy2 = new MyEntity("456");
		//		vacancy2.setName("Dummmmm");
		//
		//		
		//		getDs().save(vacancy1);
		//
		//		vacancy2.setId(vacancy1.getId());
		//		getDs().save(vacancy2);// This line will update the
	}
	/**
	 * get datastore  
	 */
	public static Datastore getDs()
	{
		Datastore ds = null;
		try {
			//public static String mongoip = "50.19.78.201";
			//public static String dbname = "livetest";
			//public static String dbnamelivetest2 = "livetest2";
			// O.o("in here -----------------"); //
			ds = (new Morphia()).createDatastore(new Mongo(Cfg.getMongoip(), Cfg.getMongoPort()), Cfg.getDummyDbname());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;

	}
	public static void testconditionDatastore(Datastore ds)
	{
		// at application start map classes before calling with morphia map methods
		ds.ensureIndexes(); // creates indexes from @Index annotations in your entities
		ds.ensureCaps(); // creates capped collections from @Entity
	}


	

	public static <T> T[] getAll(Class<T> entityClass, String field, String spattern)
	{
		String collname = entityClass.toString().substring(entityClass.toString().lastIndexOf('.') + 1); 
		DBCursor dbCur = UtilMongoJ.getColl(collname).find(UtilMongoDboCreateJ.
				buildDboAndLike(field, new String[]{spattern, spattern}));

		Morphia morphia = EntityBaseUtd.getMorphiaInit();
		ArrayList<T> al = new ArrayList<T>();
		if (dbCur != null)
		{
			int i = 0;
			while (dbCur.hasNext())
			{
				DBObject dbo = dbCur.next();
				//O.o(i++ + ". getAll:" + dbo.toString());
				al.add(morphia.fromDBObject(entityClass, dbo));
			}
		}

		//T[] t = new T[al.size()]; 
		//al.toArray(new T[0]);

		// http://stackoverflow.com/questions/14993092/how-to-declare-and-instantiate-a-new-generic-array

		//T[] arr =  (T[]) Array.newInstance(EntityBase.class , al.size());
		//for (int i = 0; i < arr.length; i++)
		//			arr[i] = al.get(i);


		//T[] arr2 =  (T[]) Array.newInstance(al.get(0).getClass().getComponentType(), al.size());
		T[] arr2 =  (T[]) Array.newInstance(entityClass, al.size());
		//T[] arr2 =  (T[]) Array.newInstance( ((T) al.get(0)).getClass().getComponentType(), al.size());


		for (int i = 0; i < arr2.length; i++)
			arr2[i] = al.get(i);

		return arr2;

	}


	// from http://grokbase.com/t/gg/morphia/124rnv1g3s/is-it-possible-to-show-the-resulting-json-query-for-a-query-object
	//	If you are just trying to debug then you can cast to the
	//	implementation class and get the DBObject of the query being used.
	//((QueryImpl)query).getQueryObject().toString()	


	public static void delete( Datastore ds, Class clazz, String field, String equal)
	{
		ds.delete(ds.find(clazz).field(field).equal(equal));	
	}
	
	public static void deleteAll(Class clazz)
	{
		getDs().delete(getDs().createQuery(clazz));
	}
}




/**
 * Morphia will be primed and Mongo will run the queried returning dbobj's 
 * 
 * @param morphia
 * @param collname
 * @param field
 * @param spattern
 * @return
 */
//public static DBObject getMorphiaPrimedAndBuildDBObject(Morphia morphia, String collname, String field, String spattern)
//{
//	//http://stackoverflow.com/questions/12371299/morphia-mongodb-wildcard-query 
//	//Pattern regexp = Pattern.compile(spattern);
//	// use this regular expression to create a query
//
//	//Query<MyEntity> q = getDs().createQuery(MyEntity.class).filter(field, regexp);
//	
//	//MyEntity o2 = q.retrievedFields (true,"name").get();
//	//className" : "com.ustodo.domain.MyEntity
//	
//	//http://stackoverflow.com/questions/12995808/how-to-retrieve-a-specific-field-from-an-entity-using-morphia-and-play-framework
//	//Shop shop = ds.createQuery(Shop.class).retrievedFields(true, "primeCategory").get();
//	//Shop shop = ds.createQuery (TaskAnalysis.class).retrievedFields (true,"primeCategory","Other_field_1","Other_field_2").get();
//	
//	// works MyEntity o = getDatastore().createQuery (MyEntity.class).retrievedFields (true,"name").get();
//	
//	// works Iterable <MyEntity> it = getDatastore().createQuery (MyEntity.class).retrievedFields (true,"name").fetch();
//	//						Iterator<MyEntity> it2 = it.iterator();
//	//						int i = 0;
//	//						while (it2.hasNext())
//	
//	//Iterable <MyEntity> it = getDatastore().createQuery (MyEntity.class).retrievedFields (true,"name").fetch();
//	// com.google.code.morphia.query.WhereCriteria
//	
//	//Object o2 = getDatastore();
//	//O.o("o2:" + o2.getClass().getName());
//	
//	//com.mongodb.BasicDBObject dboQuery = com.ustodo.db.mongo.UtilMongoDboCreateJ.buildDboAndLike(field, new String[] {"df", "df"});
//	//O.o("dboQuery.getClass().getName():" + dboQuery.getClass().getName());
//	//O.o("dboQuery:" + dboQuery);
//	
//	//qi.where(arg0)
//	
//	//qi.where(arg0);
//	//O.o("qi:" + qi.getClass().getName());
//	
//	// load the DBObject from a Mongo collection
//	//BasicDBObject blogEntryDbObj = (BasicDBObject) db.getCollection("BlogEntries").findOne(new BasicDBObject("_id", new ObjectId(blogEntryId));
//	DBObject dbObj = (DBObject) UtilMongoJ.getColl(collname).findOne(com.ustodo.db.mongo.UtilMongoDboCreateJ.buildDboAndLike(field, new String[] {"df", "df"}));
//	if (dbObj == null)
//	{
//		O.o("no data hbk");
//	}
//	else
//	{
//		O.o("dbObjMyEntity.toString():" + dbObj.toString());
//		morphia.mapPackageFromClass(EntityBaseUtd.class);
//		morphia.mapPackageFromClass(EntityUser.class);
//
//		// and then map it to our BlogEntry object
//		//MyEntity me = morphia.fromDBObject(MyEntity.class, dbObjMyEntity);	
//	
//		
//		
//		// FROMDBOBJECT
//		// FROMDBOBJECT
//		// FROMDBOBJECT
//		// FROMDBOBJECT
//		// FROMDBOBJECT
//		// FROMDBOBJECT
//		// FROMDBOBJECT
//		// FROMDBOBJECT
//		// FROMDBOBJECT
//		
//		
//		
//		return dbObj;	
//		//O.o("me:" + me);
//		//O.o("me.getName:" + me.getName());
//
//		// WORKS - hard coded classes	  	
//		//			O.o("dbObjMyEntity.toString():" + dbObjMyEntity.toString());
//		//			com.google.code.morphia.Morphia morphia = new com.google.code.morphia.Morphia();
//		//			morphia.mapPackageFromClass(MyEntity.class);
//		//			// and then map it to our BlogEntry object
//		//			MyEntity me = morphia.fromDBObject(MyEntity.class, dbObjMyEntity);	
//		//			O.o("me:" + me);
//		//			O.o("me.getName:" + me.getName());
//	}
//	
//
//	// https://github.com/mongodb/morphia/wiki/MappingObjects
//	// and not?  https://morphia.googlecode.com/svn/site/morphia/apidocs/com/google/code/morphia/query/WhereCriteria.html#addTo(com.mongodb.DBObject)
//	
//	//Object o = getDatastore().createQuery (MyEntity.class).where(x).retrievedFields (true,"name");
//	//O.o("o:" + o.getClass().getName());
//
//		//Iterable<MyEntity> it = getDatastore().createQuery (MyEntity.class).retrievedFields (true,"name").fetch();
//		//Iterator<MyEntity> it2 = it.iterator();
////			int i = 0;
////			while (it2.hasNext())
////			{
////				if (i % 1000 == 0)
////					try {
////						O.o ("read");
////						System.in.read();
////					} catch (IOException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
////				MyEntity ox = it2.next();
////				O.o("ox:" + ox.getName());
////				i++;
////			}
//	//MyEntity o = getDatastore().createQuery (MyEntity.class).retrievedFields (true,"className","Other_field_1","Other_field_2").get();
//	return null;
//	//O.o ("done");
//
//}








//DBObject dbObj = getMorphiaPrimedAndBuildDBObject(morphia, "EntityUser", "name", "df");
//EntityUser me = morphia.fromDBObject(EntityUser.class, dbObj);
//O.o ("me:" + me.toString());  // class com.ustodo.domain.MyEntity

//EntityUser me2 = get(EntityUser.class, morphia, "EntityUser", "name", "df");
//O.o ("me2:" + me2.toString());  // class com.ustodo.domain.MyEntity
// O.o ("me3:" + e.getClass().getName());  // com.ustodo.domain.EntityUser

// class com.ustodo.domain.MyEntity
//O.o (o.toString());  // class com.ustodo.domain.MyEntity
//O.o (o.getClass().toString()); // class java.lang.Class
