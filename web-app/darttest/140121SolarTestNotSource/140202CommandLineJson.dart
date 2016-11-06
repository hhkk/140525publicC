
//import 'dart:html';
//import 'dart:convert';
//import 'dart:mirrors';
import 'lib/UtilJson.dart';

void main() {


  try
  {
    String sJsonEmployees = '{ "employees" : [' +
        '{ "firstName":"John" , "lastName":"Doe" },' +
        '{ "firstName":{"lastNamex":"Doex"} , "lastName":"Smith" },' +
        '{ "firstName":"Peter" , "lastName":"Jones" } ]}';
    print ("asdasd:" + convertJsonStringToLinkedHashMap(sJsonEmployees).toString());
  } catch (e, stackTrace) {
    print("DART:10.5 in catch ERRORHBK" + stackTrace.toString());
    print("DART:10.6 in catch ERRORHBK" + e.getClass());
  }

}


