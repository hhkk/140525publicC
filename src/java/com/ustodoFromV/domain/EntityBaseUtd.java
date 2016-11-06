package com.ustodoFromV.domain;

import java.util.List;

import com.ustodoFromV.db.mongo.UtilMongoDboCreateJ;
import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.annotations.Transient;
import com.mongodb.DBObject;
import com.ustodoFromV.db.mongo.UtilMongoJ;
import com.ustodoFromV.db.morphia.UtilMorphia;
import com.ustodoFromV.util.O;
import com.ustodoFromV.util.UtilString;

 
public abstract class EntityBaseUtd extends EntityBase {

//	private Class clazzThis = null;  
	@Transient private String collname = "initcollname";
	private java.util.Date dateCreated = null;
	private java.util.Date dateModified = null;
	@Transient private java.util.Date dateRead = null;
	private String hbkland = "it worked!!";

	//CONSTRUCTORS
	public EntityBaseUtd() // e.g., clazzThis = EntityUser.class
	{
		//this(this.getClass());
		dateCreated  = new java.util.Date();
		dateModified = new java.util.Date();
	}
	
	public EntityBaseUtd(Class clazzThis) // e.g., clazzThis = EntityUser.class
	{
		//Class<T> entityClass
		O.o("thisclass to string:" + this.getClass().toString());
		this.collname = UtilString.getAllAfterLast(this.getClass().toString(), ".") ;
	}

	// MORPHIA WITH CLASSES REGISTERED
	public static Morphia getMorphiaInit()
	{
		Morphia morphia = new Morphia();
		morphia.mapPackageFromClass(EntityBaseUtd.class);
		morphia.mapPackageFromClass(EntityUser.class);
		return morphia;
	}
	
	// SAVE
	public EntityBaseUtd saveBase () 
	{
		Datastore ds = UtilMorphia.getDs();
		//UtilFile.writeToFileCreateIfNeeded("before save");
		dateModified = new java.util.Date();
		dateRead = new java.util.Date();
		
		ds.save(this);
		//UtilFile.writeToFileCreateIfNeeded("after save");
		// Notification.show("in testSaveUser1",  "in testSaveUser2" , Notification.Type.ERROR_MESSAGE);
		return this;
		// "Dumm" to "Dummmmm"
	}

	

	//public abstract EntityBaseUtd getInstance();
	public <T> T getOneById(String sIdhex)
	{
		return getOne("id", sIdhex); 
	}
	
	public <T> T getOne(String field, String spattern)
	{
		//String collname = entityClass.toString().substring(entityClass.toString().lastIndexOf('.') + 1); 
		DBObject dbObj = (DBObject) UtilMongoJ.getColl(collname).findOne(UtilMongoDboCreateJ.buildDboAndLike(field, new String[]{spattern, spattern}));

		Morphia morphia = new Morphia();
		morphia.mapPackageFromClass(EntityBaseUtd.class);
		morphia.mapPackageFromClass(EntityUser.class);
		
		T t = null;
		
		if (dbObj != null)
		{
			O.o("dbObjMyEntity.toString():" + dbObj.toString());
			t = (T) morphia.fromDBObject(this.getClass(), dbObj);
		}
		return t;
	}
	

	/**
	 * update by id 
	 * 
	 */
	public void updateById(String sIdhexstring, String fieldToUpdate, Object oUpdateValue)
	{
		
		EntityUser me3 = getOne("name", "ckon");
		O.o("me3.getPassEncrypted()  should NOT start with yippee I updated:" + me3.getPassEncrypted());
		me3.setPassEncrypted("yippee I updated newpassdated<<" + new java.util.Date() + ">>");
		me3.save();
		
		EntityUser me4 = getOne("name", "ckon");
		O.o("me4.getPassEncrypted() should start with yippee I updated:" + me4.getPassEncrypted());
		
//		EntityUser me3 = getby(EntityUser.class, "name", name);
//		O.o("me3.getPassEncrypted()  should NOT start with yippee I updated:" + me3.getPassEncrypted());
//		me3.setPassEncrypted("yippee I updated" + sDate);
//		me3.save();
//
//		EntityUser me4 = getOne(EntityUser.class, "name", name);
//		O.o("me4.getPassEncrypted() should start with yippee I updated:" + me4.getPassEncrypted());
//		me4.setPassEncrypted("yippee I updated" + sDate);
//		me4.save();
	}
	
//	public void updateById(ObjectId oid, String fieldToUpdate, Object oUpdateValue)
//	{
//		updateById(oid.toHexString(), fieldToUpdate, oUpdateValue);
//	}
	
	//	public void updateByOtherThanId(String fieldToSelect, String valueToSelect, String fieldToUpdate, Object valueToUpdate)
	//	{
	//		EntityUser me3 = getOne(EntityUser.class, "name", name);
	//		O.o("me3.getPassEncrypted()  should NOT start with yippee I updated:" + me3.getPassEncrypted());
	//		me3.setPassEncrypted("yippee I updated" + sDate);
	//		me3.save();
	//
	//		EntityUser me4 = getOne(EntityUser.class, "name", name);
	//		O.o("me4.getPassEncrypted() should start with yippee I updated:" + me4.getPassEncrypted());
	//		me4.setPassEncrypted("yippee I updated" + sDate);
	//		me4.save();
	//		
	//	}
	
	
	
	private static EntityBaseUtd test(Class<? extends EntityBaseUtd> clazz)
	{
		EntityBaseUtd e3 = UtilMorphia.getDs().find(clazz).field("name").equal("ckon3").get(); // get the firs
		EntityBaseUtd a= UtilMorphia.getDs().createQuery(clazz).disableValidation().filter("name =", "a1").order("-id").get();		
		return e3;
		//O.o(e3.toString());
	}
	
	
	
//	public <T> T getOne(Class<T> entityClass, String field, String spattern)
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
	
	//	public EntityBaseUtd get (ObjectId oid, <T> T clazz) 
	//	{
	//		Datastore ds = UtilMorphia.getDs();
	//		//UtilFile.writeToFileCreateIfNeeded("before save");
	//		ds.save(this);
	//		//UtilFile.writeToFileCreateIfNeeded("after save");
	//		// Notification.show("in testSaveUser1",  "in testSaveUser2" , Notification.Type.ERROR_MESSAGE);
	//		return this;
	//		// "Dumm" to "Dummmmm"
	//	}



	
	public static List<EntityUser> testQueryRegexp(Class<? extends EntityBaseUtd> clazz)
	{
		String title = "l";
		    //Pattern regExp = Pattern.compile(title + ".*", Pattern.CASE_INSENSITIVE);
		    //return UtilMorphia.getDs().find(title).filter("title", regExp).sort("title").asList();
		return null;
	}
	
	
	

	
	public EntityBaseUtd getOne()
	{
		Datastore ds = UtilMorphia.getDs();
		MyEntity e2 = ds.find(MyEntity.class).get(); // get the first one by type
		return e2;
		//MyEntity e3 = ds.find(MyEntity.class).field("name").equal("someName").get(); // get the firs
	}
	
	public EntityBaseUtd get(Class<EntityBaseUtd> clazz, String field, String equals)
	{
		Datastore ds = UtilMorphia.getDs();
		EntityBaseUtd e3 = ds.find(clazz).field(field).equal(equals).get(); // get the firs
		return e3;
		
		//return ds.find(clazz).field(field).equal(equals).get(); // get the firs
		
		//Query<EntityBaseUtd> = ds.createQuery(Person.class).field("address").equal(addr);		
		
		//return e3;
	}
	
	public void delete(String field, String equals)
	{
		// http://stackoverflow.com/questions/8462941/in-mongodb-morphia-how-to-delete-or-replace-an-array-object
		// https://code.google.com/p/morphia/wiki/Datastore
		//   ds.delete( ds.createQuery(Hotel.class).filter("pendingDelete", true) );
		Datastore ds = UtilMorphia.getDs();
		MyEntity e3 = ds.find(MyEntity.class).field("name").equal("someName").get(); // get the firs
	}
	
	
	
	
	public void testUpdate() {
		
		MyEntity vacancy1 = new MyEntity("123");
		vacancy1.setName("Dumm");
		MyEntity vacancy2 = new MyEntity("456");
		vacancy2.setName("Dummmmm");

		Datastore ds = UtilMorphia.getDs();
		ds.save(vacancy1);
		vacancy2.setId(vacancy1.getId());  // =======================================================
		ds.save(vacancy2);// This line will update the
													// "Dumm" to "Dummmmm"
	}
	
	public String toString() {
		String s = 
				//" base.dateCreated:" + dateCreated.toString() + 
				", base.dateModified:" + dateModified.toString(); 
		return s;
	}
	

	/**
	 * get datastore for db named above in this file 
	 * @return
	 */
	
	

	
	


}