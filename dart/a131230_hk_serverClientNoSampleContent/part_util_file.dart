// E:\Users\henryms\sw\ustodo\130707public\dart\a131230_hk_serverClientNoSampleContent\part_util_error_handler.dart

part of lib_util_file;

class Util
{
  static void testhk()
  {
      try {
        var current = Directory.current;
        String classname = Directory.current.runtimeType.toString();
        String currentcccc = "joey";
        //print ("inside testhk"+current);

        print ("inside testhk"+current.toString());
        print ("inside testhk2"+classname.toString());

      }  catch (e) {
        handleErr("sdffdsf", e);
      }

  }

  static void handleErr(s, e) // Util.handleErr
  {
    print ("in handleErr");
    var current = Directory.current;
    String classname = Directory.current.runtimeType.toString();
    String currentcccc = "joey";
    //print ("inside testhk"+current);
    print ("inside testhk"+current.toString());
    print ("inside testhk2"+classname.toString());
  }


}


void handleErr2(s, e)
{
  print ("in handleErr2");
  var current = Directory.current;
  String classname = Directory.current.runtimeType.toString();
  String currentcccc = "joey";
  //print ("inside testhk"+current);
  print ("inside testhk"+current.toString());
  print ("inside testhk2"+classname.toString());
}

