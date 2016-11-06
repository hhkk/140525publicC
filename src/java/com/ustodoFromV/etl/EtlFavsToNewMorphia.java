package com.ustodoFromV.etl;

import com.google.code.morphia.Datastore;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.ustodoFromV.db.mongo.UtilMongoJ;
import com.ustodoFromV.db.morphia.UtilMorphia;
import com.ustodoFromV.domain.MyEntity;
import com.ustodoFromV.util.O;

public class EtlFavsToNewMorphia {

	public static void main(String[] a) {
		try {

			// getAllFavs("favsdrosen");
			// getAllFavs("favsbkon");
			// getAllFavs("favsckckck");
			O.o("start");
			long lstart = System.currentTimeMillis();
			moveOneFavsCollToMorphia("favsbkon");
			O.o("done in ms:" + (System.currentTimeMillis() - lstart));
			// count
			// MyEntity e2 = ds.find(MyEntity.class).get(); // get the first one
			// by
			// db.favsdrosen.find().count() == 3

			// db.MyEntity.find().count() == 3
		} catch (Exception e) {
			e.printStackTrace();

		}
		O.o("done");

	}

	public static void moveOneFavsCollToMorphia(String collnameSource) 
	{
		Datastore ds = UtilMorphia.getDs();

		DBCursor dbcur = (UtilMongoJ.getColl(collnameSource)).find(new BasicDBObject());
		int i = 0;
		while (dbcur.hasNext()) {
			if (i % 500 == 0)
				O.o("i2:" + i);
			i++;
			DBObject o = dbcur.next();
			// db.MyEntity.find().count()
			ds.save(new MyEntity(o.toString()));
		}
	}

	private static void getAllFavs(String collname)
	// private static ArrayList<EntityFLR> getAllFavs(String collname)
	{

		// SET UP SINK

		// SET UP SOURCE
		// at application start map classes before calling with morphia map*
		// methods
		// ds.ensureIndexes(); // creates indexes from @Index annotations in
		// your entities
		// ds.ensureCaps(); // creates capped collections from @Entity

		// MyEntity e2 = ds.find(MyEntity.class).get(); // get the first one by
		// // type
		// MyEntity e3 = ds.find(MyEntity.class).field("name")
		// .equal("someName").get(); // get the firs

		// testUpdate();

		// to update
		// http://stackoverflow.com/questions/7271960/morphia-saves-new-entities-instead-of-update-id

	}
}