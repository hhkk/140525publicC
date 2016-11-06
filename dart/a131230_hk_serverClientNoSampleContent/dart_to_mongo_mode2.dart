// E:\Users\henryms\sw\ustodo\130707public\dart\a131230_hk_serverClientNoSampleContent\dart_to_mongo_mode2.dart
// https://github.com/vadimtsushko/mongo_dart/blob/master/example/raw_queries.dart

part of lib_util_mongo;

  void getMongo_Mode2()
  {

    // https://mail.google.com/mail/u/0/#inbox/14357018f2861424
    //main(){
    try {
      print ("in getMongo_Mode2");
      Db db = new Db("mongodb://127.0.0.7/mongo_dart-test");
      DbCollection coll;
      ObjectId id;
      db.open().then((c){
        print('connection open');
        coll = db.collection("simple_data");
        coll.remove();
        for (var n = 0; n<1000; n++){
          coll.insert({"my_field":n,"str_field":"str_$n"});
        }
        return coll.findOne({"my_field": 17});
      }).then((val){
        print("Filtered by my_field=17 $val");
        id = val["_id"];
        return coll.findOne({"_id":id});
      }).then((val){
        print("Filtered by _id=$id: $val");
        print("Removing doc with _id=$id");
        coll.remove({"_id":id});
        return coll.findOne({"_id":id});
      }).then((val){
        print("Filtered by _id=$id: $val. There more no such a doc");
        print("Filtered by {'str_field': {'\$regex': new BsonRegexp('^str_(5|7|8)17\$')}");
        return coll.find({'str_field': {'\$regex': new BsonRegexp('^str_(5|7|8)17\$')}}).forEach((v)=>print(v));
      }).then((dummy){
        db.close();
      });
    }
    catch ( e)
    {
      //Util.handleErr("asdasd", e);
    }
  }
