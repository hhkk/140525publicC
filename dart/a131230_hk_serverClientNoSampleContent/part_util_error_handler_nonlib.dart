// E:\Users\henryms\sw\ustodo\130707public\dart\a131230_hk_serverClientNoSampleContent\part_util_error_handler_nonlib.dart

//part of lib_util_error_handler;
import 'dart:io';

class Util2
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
        handleErr3("sdffdsf", e);
      }

  }

  static void handleErr3(s, e) // Util.handleErr
  {
    print ("in handleErr3");
    var current = Directory.current;
    String classname = Directory.current.runtimeType.toString();
    String currentcccc = "joey";
    //print ("inside testhk"+current);
    print ("inside testhk"+current.toString());
    print ("inside testhk2"+classname.toString());
  }


}


void handleErr4(s, e)
{
  print ("in handleErr4");
  var current = Directory.current;
  String classname = Directory.current.runtimeType.toString();
  String currentcccc = "joey";
  //print ("inside testhk"+current);
  print ("inside testhk"+current.toString());
  print ("inside testhk2"+classname.toString());
}

