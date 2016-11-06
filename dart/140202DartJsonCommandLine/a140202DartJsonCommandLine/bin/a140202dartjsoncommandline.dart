// Copyright (c) 2012, the Dart project authors.  Please see the AUTHORS file
// Copyright (c) 2012, the Dart project authors.  Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.

/**
 * A solar system visualization.
 */

// library solar;

import 'dart:html';
import 'dart:convert';
//import 'dart:json';
//import "package:json_object/json_object.dart";

//import 'dart:convert' show JSON;  // https://www.dartlang.org/docs/dart-up-and-running/contents/ch03.html#ch03-json

void main() {

  try {
      //https://github.com/chrisbu/dartwatch-JsonObject
      //   https://pub.dartlang.org/doc/
      var sJsonEmployees = '{ "employees" : [' +
      '{ "firstName":"John" , "lastName":"Doe" },' +
      '{ "firstName":"Anna" , "lastName":"Smith" },' +
      '{ "firstName":"Peter" , "lastName":"Jones" } ]}';

      //JSONObject jsonObject=new JSONObject(sJsonEmployees);
      //window.alert ("jsonObject.runtimeType.toString():" + jsonObject.runtimeType.toString());

      //String listAsJson = '["Dart",1.0]'; // input List of data
      window.alert ("JSON.runtimeType.toString():" + JSON.runtimeType.toString());
      var parsedList = JSON.decode(sJsonEmployees);
      List parsedList2 = JSON.decode(sJsonEmployees);
      // no work window.alert ("List.runtimeType.toString():" + List.runtimeType.toString());
      window.alert ("parsedList[0]:" + parsedList[0]); // Dart
      window.alert ("parsedList[1]:" + parsedList[1]); // Dart

      // no work window.alert ("List.runtimeType.toString():" + List.runtimeType.toString());
      //window.alert ("parsedList.runtimeType.toString():" + parsedList.runtimeType.toString());

      //window.alert ("parsedList.runtimeType.toString():" + parsedList.runtimeType.toString());
      print(parsedList[0]); // Dart
      print(parsedList[1]); // 1.0
      String mapAsJson = '{"language":"dart"}';  // input Map of data
      Map parsedMap = JSON.decode(mapAsJson);
      print(parsedMap["language"]); // dart



      String jsonString="{\"notes\": { \"note\": \"\"}}";
      JSONObject jsonObject=new JSONObject(jsonString);


      window.alert ("DART:0.3974  received jsonObject.toString():" + jsonObject.toString());

      JSONObject jsonObjectNotes=jsonObject.getJSONObject("notes");
      window.alert ("DART:0.3974  received jsonObjectNotes.toString():" + jsonObjectNotes.toString());

      window.alert ("DART:0.3975  received decoded:" + JSON.getClass().getName());
      final JSON_TO_BYTES = JSON.fuse(UTF8);

      List<int> bytes = JSON_TO_BYTES.encode(["json-object"]);
      var decoded = JSON_TO_BYTES.decode(bytes);
      window.alert ("DART:0.3975  received decoded:" + decoded);
      assert(decoded is List && decoded[0] == "json-object");

      var inverted = JSON.inverted;
      var jsonIdentity = JSON.fuse(inverted);
      var jsonObject2 = jsonIdentity.encode(["1", 2]);
      window.alert ("DART:0.3976  received jsonObject.toString():" + jsonObject2.toString());
      assert(jsonObject2 is List && jsonObject2[0] == "1" && jsonObject2[1] == 2);
      window.alert ("DART:0.3977  received jsonObject.toString():" + jsonObject2.toString());

      var sReceivedinDart= JSON.stringify(event.data);
      window.alert ("DART:0.398  received sReceivedinDart:" + sReceivedinDart);
//
      //window.alert('DART:0.3 dart got from js e.data:.getClass()'+ e.data.getClass());
      //window.alert('DART:0.3 x==0: dart posting back a message ');
      var data2Js = {};
      data2Js["type"] = "Json.Type_FromDart2Js";
      data2Js["action"] = "Json.Action_FromDart2Js";
      data2Js["payload"] = "Json.Payload_FromDart2Js";
      //window.alert('DART:0.3e  x==0:dart posting back a message ');
      var sSend_data3Js= JSON.stringify(data2Js);
      window.alert ("DART:0.399  not sending sSend_data3Js:" + sSend_data3Js);

      // SEND BACK A RESPONSE
      //window.alert ("DART:0.4  <<message posted from dart>>");
      //window.postMessage("<<message posted from dart>>", '*');
      //window.alert('dart done posting back a message ');
    }
    //else
    //{
//      window.alert('DART:0.6 x = $x dart not gonna post back a message - no inf loop');
//    }
    x++;
  });

  //window.alert('DART 9.3: dart defined message listener');



//try this one also easy js to dart and back:
//    http://stackoverflow.com/questions/10676228/how-can-i-send-and-receive-variables-to-and-from-javascript-functions-using-dart?rq=1

//  WHAT ARE OR WERE THESE?
//  window.on.message.remove(onFinishedListener);
//  //window.on.message.add(onFinishedListener);

//Element e1 = querySelector("#container");
// https://www.dartlang.org/articles/improving-the-dom/
  } catch (e, stackTrace) {
    window.alert("DART:10.5 in catch ERRORHBK" + stackTrace.toString());
    window.alert("DART:10.6 in catch ERRORHBK" + e.getClass());
  }
}



