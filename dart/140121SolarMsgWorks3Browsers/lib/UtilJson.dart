
//import 'dart:html';
import 'dart:convert';
//import 'dart:mirrors';

Object convertJsonStringToLinkedHashMap(String sJson)
{
  print ("in UtilJson.convertJsonStringToLinkedHashMap");
  //window.alert("in UtilJson.convertJsonStringToLinkedHashMap");
  var lhmJson = JSON.decode(sJson);
  //  _LinkedHashMap print ("lhmJson.runtimeType.toString():" + lhmJson.runtimeType.toString());
  List parsedListdotEmployees = lhmJson["employees"];
  // _Type print("parsedListdotEmployees.runtimeType.runtimeType.toString():" + parsedListdotEmployees.runtimeType.runtimeType.toString()); // _Type
  // List  print("parsedListdotEmployees.runtimeType.toString():" + parsedListdotEmployees.runtimeType.toString()); // List




  if (parsedListdotEmployees is List)
    print ("yes parsedListdotEmployees is List");
  else
    print ("no parsedListdotEmployees is List");

//  print("parsedListdotEmployees.simpleName.toString():" + parsedListdotEmployees.getClass().simpleName.toString());

//  var cls = parsedListdotEmployees.getClass();
//  Expect.equals('MyClass', cls.simpleName);

  // no work class print("parsedListdotEmployees.class.toString():" + parsedListdotEmployees.class.toString());
  print ("lhmJson.runtimeType.toString():" + lhmJson.runtimeType.toString());
  return lhmJson;

  //  var sJsonEmployees = '{ "employees" : [' +
  //  '{ "firstName":"John" , "lastName":"Doe" },' +
  //  '{ "firstName":{"lastNamex":"Doex"} , "lastName":"Smith" },' +
  //  '{ "firstName":"Peter" , "lastName":"Jones" } ]}';
  //
  //window.alert ("jsonObject.runtimeType.toString():" + jsonObject.runtimeType.toString());

  //String listAsJson = '["Dart",1.0]'; // input List of data
  //List parsedList2 = JSON.decode(sJsonEmployees);
  //print("parsedList:" + parsedList.toString());
  //print("parsedList.runtimeType.toString():" + parsedList.runtimeType.toString()); // runtimeType:_LinkedHashMap
  //var parsedListdot1 = parsedList.employees;
  //var parsedListdotEmployees = parsedList["employees"]; // runtimeType:List
  //print("parsedListdotEmployees.toString():" + parsedListdotEmployees.toString());
  //print("parsedListdotEmployees.runtimeType.toString():" + parsedListdotEmployees.runtimeType.toString());
  //print("parsedListdotEmployees[1].toString():" + parsedListdotEmployees[1].toString());
  //print("parsedListdotEmployees[1][\"firstName\"].toString():" + parsedListdotEmployees[1]["firstName"].toString());
  //print("parsedListdotEmployees.runtimeType.toString():" + parsedListdotEmployees.runtimeType.toString()); //

  //print("sJsonReconstructed:" + sJsonReconstructed); //
  //print("sJsonReconstructed.runtimeType.toString():" + sJsonReconstructed.runtimeType.toString()); //


}
String convertLinkedHashMapToJsonString(Object lhmJson)
{
  return JSON.encode(lhmJson);
}


void main() {
//  LibraryMirror core_lib = libraries['dart:core'];
//  Expect.isTrue(core_lib is LibraryMirror);

  try{
    String sJsonEmployees = '{ "employees" : [' +
    '{ "firstName1":"John" , "lastName":"Doe" },' +
    '{ "firstName2":{"lastNamex":"Doex"} , "lastName":"Smith" },' +
    '{ "firstName3":"Peter" , "lastName":"Jones" } ]}';
    var o = convertJsonStringToLinkedHashMap(sJsonEmployees);
    var s = convertJsonStringToLinkedHashMap(sJsonEmployees).toString();

    print ("1 o.toString()" + o.toString());
    //print ("2 o.simpleName:" + o.simpleNamesimpleName);
    print ("3 o.runtimeType.toString():" + o.runtimeType.toString());
    //print ("4 o.runtimeType.toString():" + o[0]);
    //print ("5 o.firstName1:" + o.firstName1);
    print ("6 o['employees'].toString():" + o['employees'].toString());
    print ("7 o['employees'][0].toString():" + o['employees'][0].toString()); // WORKS
    //print ("7 o.runtimeType.toString():" + o[0]['firstName1']);
    //print ("asdasd:" + convertJsonStringToLinkedHashMap(sJsonEmployees).toString());

  } catch (e, stackTrace) {
    print ("DART:error in DART:0.3992  " + e.toString() + ":" + stackTrace.toString());
    //  dartMsg("DART:10.6 in catch ERRORHBK" + e.get());
  }


}


