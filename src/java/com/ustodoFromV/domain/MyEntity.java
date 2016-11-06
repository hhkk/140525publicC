package com.ustodoFromV.domain;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.annotations.Entity;
import com.ustodoFromV.db.morphia.UtilMorphia;


//https://github.com/mongodb/morphia/wiki/GettingStarted
//http://u2d.co:8080/utdv/
// create mongodb http://www.mkyong.com/mongodb/how-to-create-database-or-collection-in-mongodb/
//	> show dbs
//	livetest        0.203125GB
//	local   0.078125GB
//	morphiatest     0.203125GB
//	> use morphiatest
//	switched to db morphiatest
//	> show collections
//	MyEntity
//	system.indexes
//	> db.MyEntity.find()
//	{ "_id" : ObjectId("5313a2433e07be2a
//	 }
//	>
// db.MyEntity.find().count()

// C:\Users\henryms\Downloads\mongodb-win32-x86_64-2008plus-2.4.0\bin>mongo 50.19.78.201


// SEE BOTTOM OF PAGE 

@Entity
public class MyEntity extends EntityBaseUtd {
	private String name;

	public MyEntity() {
	}
	
	public MyEntity(String name) {
		this.name = name;
	}
	
	public MyEntity save() {return (MyEntity) saveBase();};
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public static void main(String[] saArgs) {

		try {
			Datastore ds = UtilMorphia.getDs();
			MyEntity e = new MyEntity("ccc");
			ds.save(e);

			MyEntity e2 = ds.find(MyEntity.class).get(); // get the first one by type
			MyEntity e3 = ds.find(MyEntity.class).field("name").equal("someName").get(); // get the firs
			
			e.testUpdate();
			
			// to update
			// http://stackoverflow.com/questions/7271960/morphia-saves-new-entities-instead-of-update-id

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println ("done");
	}


}
