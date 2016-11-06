// E:\Users\henryms\sw\ustodo\130707public\dart\a131230_hk_serverClientNoSampleContent\dart_to_mongo_mode1.dart
part of lib_util_mongo;

void getMongo_Mode1_top_level()
{
  print ("in getMongo_Mode1_top_level");
  dart_to_mongo_mode1class.getMongo_Mode1();
}

void getMongo_Mode1_top_level2()
{
  print ("in getMongo_Mode1_top_level2");
  dart_to_mongo_mode1class.getMongo_Mode1();
}

class dart_to_mongo_mode1class
{

  static void getMongo_Mode1()
  {
    print ("in dart_to_mongo_mode1class.getMongo_Mode1");
    //getMongo_Mode1_top_level2();
    try
    {
      void displayZip(Map zip) {
        print('state: ${zip["state"]}, city: ${zip["city"]}, zip: ${zip["id"]}, population: ${zip["pop"]}'    );
      }
      Db db = new Db("mongodb://reader:vHm459fU@ds037468.mongolab.com:37468/samlple");
      var zips = db.collection('zip');
      db.open().then((_){
        print('''
          * Zips for state NY, with population between 14000 and 16000,
      * reverse ordered by population''');
        return zips.find(
            where.eq('state','NY').inRange('pop',14000,14200).sortBy('pop', descending: true))
        .forEach(displayZip);
      }).then((_) {
        print('\n* Find ZIP for code 78829 (BATESVILLE)');
        return zips.findOne(where.eq('id','78829'));
      }).then((batesville) {
        displayZip(batesville);
        print('* Find 10 ZIP closest to BATESVILLE');
        return zips.find(
            where.near('loc',batesville["loc"]).limit(10))
        .forEach(displayZip);
      }).then((_) {
        print('closing db');
        db.close();
      });

    } catch (e) {
      //Uti.handleErr2("dfdfdf", e);
      //e.printStackTrace();

    }
    print ("2.done in dart_to_mongo_mode1class.getMongo_Mode1");
  }
}
