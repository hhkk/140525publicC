import 'dart:io';
import 'lib_util_mongo.dart' as lib_util_mongo;
import 'lib_util_error_handler.dart';
import 'part_util_error_handler_nonlib.dart';

//import 'dart:json' as JSON; // see http://blog.markwatson.com/2013/08/rest-service-and-client-in-dart.html
import 'dart:convert'; // see https://www.dartlang.org/articles/json-web-service/

main() {
  print ("start hk");

  Util.handleErr("from h_t_s.dart", new Exception());
  Util2.handleErr3("from h_t_s.dart", new Exception());
  handleErr2("from h_t_s.dart", new Exception());
  handleErr4("from h_t_s.dart", new Exception());

  try
  {
//    HttpServer.bind('127.0.0.1', 8089).then((server) {
//      server.listen((HttpRequest request) {
//      });
//    });

    // localhost:8089
    var port = 8089;
    HttpServer.bind('localhost', port).then((HttpServer server) {
      print('Server started. Go to localhost:${port}');
      // JSON is of runtimeType JsonCodec
      //print('JSON.runtimeType.toString():' + JSON.runtimeType.toString());

      server.listen((HttpRequest request) {

        // begin test stuff

        print ("got a request 2:" + request.toString());
        print ("got a request 3:" + request.headers.toString());
        print ("got a request 4 request.uri.queryParameters.keys.toString():" + request.uri.queryParameters.keys.toString());

        // two ways to call the lib

        int i = 0;
        if (i != 0)
        {

          //  1 top level method with no class
          print ("===================== mongo mode 1, call mode A via top level");
          lib_util_mongo.getMongo_Mode1_top_level();

          //  2 static method within a class
          print ("===================== pre mongo mode 1, but dart style call mode B via class");
          lib_util_mongo.dart_to_mongo_mode1class.getMongo_Mode1();
          print ("done test 2");

        }

        if (true)
        {
          // second mode of calling
          print ("===================== pre mongo mode 2 call mode A via top level");
          lib_util_mongo.getMongo_Mode2();
          print ("============ done test 3");

        }


        // end test stuff


        var resp = JSON.encode({
          'name': 'Mark',
          'hobby': 'hiking2'}
        );

        // example 1
        //request.response.write('Hello, world');
        //request.response.close();

        // example 2
        request.response..headers.set(HttpHeaders.CONTENT_TYPE,
        'application/json');
        request.response..headers.set('Access-Control-Allow-Origin','*');
        request.response..headers..write(resp)..close();




      });
    });

  }
  catch (e)
  {
    Util.handleErr("from h_t_s.dart", e);
  }
  print ("end hk");
}