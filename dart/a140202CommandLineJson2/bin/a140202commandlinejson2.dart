
//import 'dart:html';
import 'dart:convert';
//import 'dart:mirrors';

Object convertJsonStringToLinkedHashMap(String sJson)
{
  var lhmJson = JSON.decode(sJson);
  //  _LinkedHashMap print ("lhmJson.runtimeType.toString():" + lhmJson.runtimeType.toString());
  List parsedListdotEmployees = lhmJson["employees"];
  // _Type print("parsedListdotEmployees.runtimeType.runtimeType.toString():" + parsedListdotEmployees.runtimeType.runtimeType.toString()); // _Type
  // List  print("parsedListdotEmployees.runtimeType.toString():" + parsedListdotEmployees.runtimeType.toString()); // List




  if (parsedListdotEmployees is String)
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

  String sJsonEmployees = '{ "employees" : [' +
    '{ "firstName":"John" , "lastName":"Doe" },' +
    '{ "firstName":{"lastNamex":"Doex"} , "lastName":"Smith" },' +
    '{ "firstName":"Peter" , "lastName":"Jones" } ]}';
  print ("asdasd:" + convertJsonStringToLinkedHashMap(sJsonEmployees).toString());

}


