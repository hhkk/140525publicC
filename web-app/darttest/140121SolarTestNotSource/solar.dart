// Copyright (c) 2012, the Dart project authors.  Please see the AUTHORS file
// Copyright (c) 2012, the Dart project authors.  Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.

/**
 * A solar system visualization.
 */

library solar;

import 'dart:async';
import 'dart:html';
import 'dart:math';
import 'dart:convert';
import 'lib/UtilJson.dart';

//import 'dart:json';
//import "package:json_object/json_object.dart";

import 'dart:convert' show JSON;  // https://www.dartlang.org/docs/dart-up-and-running/contents/ch03.html#ch03-json






/*
cd f:\Users\henryms\sw\ustodo\130707publicF\dart\140121SolarMsgWorks3Browsers
dart2js --out=solar.dart.js solar.dart
rem delete target
rm -rf f:\Users\henryms\sw\ustodo\130707publicF\web-app\darttest\140121SolarTestNotSource
rem copy
xcopy f:\Users\henryms\sw\ustodo\130707publicF\dart\140121SolarMsgWorks3Browsers f:\Users\henryms\sw\ustodo\130707publicF\web-app\darttest\140121SolarTestNotSource /E
 */




void dartMsg(String s)
{
  window.alert("DART:" + s);
}



void main() {

  try {
// https://www.dartlang.org/docs/dart-up-and-running/contents/ch03.html
    window.onMessage.listen((MessageEvent event) {
//dartMsg('in dart onMessage.listen');
//dartMsg('in dart onMessage.listen x:' + x.toString());
//dartMsg('in dart onMessage.listen Received message e: ${e}');


      //  dartMsg('DART:0.01 event.simpleName: ${event.simpleName}');  // in dart onMessage.listen Received message e.data: {"type":"js2dart","action":"action2","payload":"payload2"}
      //dartMsg('**************** TOP DART 2 WINDOW MSG LISTEN:0.1ahk receive msg in dart onMessage.listen Received message event.data: ${event.data}');  // in dart onMessage.listen Received message e.data: {"type":"js2dart","action":"action2","payload":"payload2"}
      //if (x == 0)
      try
      {


        //        String sJsonEmployees = '{ "employees" : [' +
        //        '{ "firstName":"John" , "lastName":"Doe" },' +
        //        '{ "firstName":{"lastNamex":"Doex"} , "lastName":"Smith" },' +
        //        '{ "firstName":"Peter" , "lastName":"Jones" } ]}';
        //print ("asdasd:" + convertJsonStringToLinkedHashMap(sJsonEmployees).toString());
        var myObj = convertJsonStringToLinkedHashMap(event.data);
        //print ("z1 myObj.toString()" + myObj.toString());
        //print ("2 o.simpleName:" + o.simpleNamesimpleName);
        //print ("z3 myObj..runtimeType.toString():" + myObj.runtimeType.toString());
        //print ("6 myObj.['employees'].toString():" + myObj.['employees'].toString());
        //print ("7 myObj.['employees'][0].toString():" + myObj.['employees'][0].toString()); // WORKS






        //dartMsg('***** ** DART:0.18: HBKHBKHBK received message, event.data.toString():'+ event.data.toString());
        //dartMsg('***** ** DART:0.19a: HBKHBKHBK received message, myObj.toString():'+ myObj.toString());
        //dartMsg('***** ** DART:0.19b: HBKHBKHBK received message, myObj.get(\'typehk\').toString():'+ myObj['typehk'].toString());
        if (myObj['typehk'].toString() != "js2dart") // WORKS!!
        {
          dartMsg("not js2dart, returning");
          return;
        }
        dartMsg("dart will process: js2dart, returning");
        //   no auto-doting: does not work dartMsg('***** ** DART:0.19b: HBKHBKHBK received message, myObj.typehk:'+ myObj.typehk);
        //print ("in dart window.onMessage.listen");

        //dartMsg('******* DART:0.2a: info: window.location.protocol:'+ window.location.protocol); "http:"
        //dartMsg('******* DART:0.2a: info: window.location.host:'+ window.location.host); "u2d.co"
        //dartMsg ("DART:0.397  received event.data:" + event.data);

        // https://www.dartlang.org/articles/json-web-service/#parsing-json

        String listAsJson = '["Dart",1.0]'; // input List of data
        //dartMsg ("DART:0.398  JSON.runtimeType.toString():" + JSON.runtimeType.toString());
        List parsedList = JSON.decode(listAsJson);
        // no work dartMsg ("List.runtimeType.toString():" + List.runtimeType.toString());
        //dartMsg ("DART:0.3990  parsedList.length:" + parsedList.length.toString()); // Dart
        //dartMsg ("DART:0.399  parsedList[0]:" + parsedList[0]); // Dart
        //try {
        //          dartMsg ("DART:0.3991  parsedList[1]:" + parsedList[1].toString()); // Dart
        //      } catch (e, stackTrace) {
        //      dartMsg("DART:error in DART:0.3992  " + e.toString() + ":" + stackTrace.toString());
        //  dartMsg("DART:10.6 in catch ERRORHBK" + e.get());
        //  }
        //dartMsg ("parsedList.runtimeType.toString():" + parsedList.runtimeType.toString());
        //print(parsedList[0]); // Dart
        //print(parsedList[1]); // 1.0
        try {
//          String mapAsJson = '{"language":"dart"}';  // input Map of data
//
//          String listAsJson = '''
//          [
//            {"score": 40},
//            {"score": 80}
//          ]
//          ''';
//
//          var mapScoresFromJsonStr= JSON.decode(mapAsJson);
//          assert(mapScoresFromJsonStr is Map);
//
//          var listScoresFromJsonStr = JSON.decode(listAsJson);
//          assert(listScoresFromJsonStr is List);
//          //print(parsedMap["language"]); // dart
//
//          //String jsonString="{\"notes\": { \"note\": \"\"}}";
//          //JSONObject jsonObject=new JSONObject(jsonString);
//
//          dartMsg ("DART:0.3974  received jsonObject.toString():" + jsonObject.toString());


          // this is not working as of 5/2014
          // see https://github.com/chrisbu/dartwatch-JsonObject
//          JSONObject jsonObjectNotes=jsonObject.getJSONObject("notes");
//          dartMsg ("DART:0.3974  received jsonObjectNotes.toString():" + jsonObjectNotes.toString());
//
//          dartMsg ("DART:0.3975  received decoded:" + JSON.getClass().getName());
//          final JSON_TO_BYTES = JSON.fuse(UTF8);
//
//          List<int> bytes = JSON_TO_BYTES.encode(["json-object"]);
//          var decoded = JSON_TO_BYTES.decode(bytes);
//          dartMsg ("DART:0.3975  received decoded:" + decoded);
//          assert(decoded is List && decoded[0] == "json-object");
//
//          var inverted = JSON.inverted;
//          var jsonIdentity = JSON.fuse(inverted);
//          var jsonObject2 = jsonIdentity.encode(["1", 2]);
//          dartMsg ("DART:0.3976  received jsonObject.toString():" + jsonObject2.toString());
//          assert(jsonObject2 is List && jsonObject2[0] == "1" && jsonObject2[1] == 2);
//          dartMsg ("DART:0.3977  received jsonObject.toString():" + jsonObject2.toString());
//
//          var sReceivedinDart= JSON.stringify(event.data);
//          dartMsg ("DART:0.398  received sReceivedinDart:" + sReceivedinDart);
//
          //dartMsg('DART:0.3 dart got from js e.data:.getClass()'+ e.data.getClass());
          //dartMsg('DART:0.3 x==0: dart posting back a message ');


        //          var data2Js = {};
        //          data2Js["typehk"] = "Json.Type_FromDart2Js";
        //          data2Js["action"] = "Json.Action_FromDart2Js";
        //          data2Js["payload"] = "Json.Payload_FromDart2Js";
        //

          myObj["typehkwas"] = myObj["typehk"];
          myObj["typehk"] = "Json.Type_FromDart2Js";


          //dartMsg('DART:0.3e  x==0:dart posting back a message ');
          //var sSend_data3Js= JSON.stringify(data2Js);
          //String sSend_data3Js= JSON.encode(data2Js);
          String sSend_data3Js= JSON.encode(myObj);
          //dartMsg ("DART:0.3993  not sending sSend_data3Js:" + sSend_data3Js);


          //          if (true) // test decode
          //          {
          //            var decoded_sSend_data3Js = JSON.decode(sSend_data3Js);
          //            if  (decoded_sSend_data3Js is Map) {
          //              dartMsg("39935 type confirmed: map");
          //            }
          //            if  (decoded_sSend_data3Js is List) {
          //              dartMsg("39936 type confirmed: List");
          //            }
          //
          //            dartMsg ("DART:0.3994 decoded_sSend_data3Js:" + decoded_sSend_data3Js.toString());
          //
          //          }

          // SEND BACK A RESPONSE
          //dartMsg ("DART:0.4  sending msg back to js ");
          //window.postMessage(sSend_data3Js, 'fromDartHBK');
          window.postMessage(sSend_data3Js, '*');
          //dartMsg('DART:dart done posting this back:sSend_data3Js:' + sSend_data3Js);
        } catch (e, stackTrace) {
          dartMsg("DART:error in DART:2.1:" + e.toString() + ":\r\n" + stackTrace.toString());
          //  dartMsg("DART:10.6 in catch ERRORHBK" + e.get());
        }
      } catch (e, stackTrace) {
        dartMsg("DART:error in DART:0.6991  " + e.toString() + ":" + stackTrace.toString());
        //  dartMsg("DART:10.6 in catch ERRORHBK" + e.get());
      }
      //else
      //{
      //      dartMsg('DART:0.6 x = $x dart not gonna post back a message - no inf loop');
      //    }
    });

//dartMsg('DART 9.3: dart defined message listener');



//try this one also easy js to dart and back:
//    http://stackoverflow.com/questions/10676228/how-can-i-send-and-receive-variables-to-and-from-javascript-functions-using-dart?rq=1

//  WHAT ARE OR WERE THESE?
//  window.on.message.remove(onFinishedListener);
//  //window.on.message.add(onFinishedListener);

//Element e1 = querySelector("#container");
// https://www.dartlang.org/articles/improving-the-dom/
    CanvasElement canvas = querySelector("#idCanvasPlanets");
    scheduleMicrotask(new SolarSystem(canvas).start);
  } catch (e, stackTrace) {
    dartMsg("DART:10.5 in catch ERRORHBK" + stackTrace.toString());
    dartMsg("DART:10.6 in catch ERRORHBK" + e.getClass());
  }
}

Element notes = querySelector("#fps");
num fpsAverage;

/// Display the animation's FPS in a div.
void showFps(num fps) {
  if (fpsAverage == null) fpsAverage = fps;
  fpsAverage = fps * 0.05 + fpsAverage * 0.95;
  notes.text = "${fpsAverage.round()} fps";
}

/**
 * A representation of the solar system.
 *
 * This class maintains a list of planetary bodies, knows how to draw its
 * background and the planets, and requests that it be redraw at appropriate
 * intervals using the [Window.requestAnimationFrame] method.
 */
class SolarSystem {
  CanvasElement canvas;

  num width;
  num height;

  PlanetaryBody sun;

  num renderTime;

  SolarSystem(this.canvas);

// Initialize the planets and start the simulation.
  start() {
// Measure the canvas element.
    Rectangle rect = canvas.parent.client;
    width = rect.width;
    height = rect.height;
    canvas.width = width;

// Create sun.
    final mercury = new PlanetaryBody(this, "orange", 0.382, 0.387, 0.241);
    final venus   = new PlanetaryBody(this, "green", 0.949, 0.723, 0.615);
    final earth = new PlanetaryBody(this, "#33f", 1.0, 1.0, 1.0);
    final moon  = new PlanetaryBody(this, "gray", 0.2, 0.14, 0.075);
    earth.addPlanet(moon);

    final mars  = new PlanetaryBody(this, "red", 0.532, 1.524, 1.88);

    final f = 0.1;
    final h = 1 / 1500.0;
    final g = 1 / 72.0;

    final jupiter  = new PlanetaryBody(this, "gray", 4.0, 5.203, 11.86);
    final io       = new PlanetaryBody(this, "gray", 3.6*f, 421*h, 1.769*g);
    final europa   = new PlanetaryBody(this, "gray", 3.1*f, 671*h, 3.551*g);
    final ganymede = new PlanetaryBody(this, "gray", 5.3*f, 1070*h, 7.154*g);
    final callisto = new PlanetaryBody(this, "gray", 4.8*f, 1882*h, 16.689*g);
    jupiter..addPlanet(io)
      ..addPlanet(europa)
      ..addPlanet(ganymede)
      ..addPlanet(callisto);

    sun = new PlanetaryBody(this, "#ff2", 14.0)..addPlanet(mercury)
      ..addPlanet(venus)
      ..addPlanet(earth)
      ..addPlanet(mars)
      ..addPlanet(jupiter);

    addAsteroidBelt(sun, 150);

    //dartMsg('DART: no more requestRedraw');

    requestRedraw();
  }

  void draw(num _) {
    num time = new DateTime.now().millisecondsSinceEpoch;
    if (renderTime != null) showFps(1000 / (time - renderTime));
    renderTime = time;

    var context = canvas.context2D;
    drawBackground(context);
    drawPlanets(context);
    requestRedraw();
  }

  void drawBackground(CanvasRenderingContext2D context) {
    context.clearRect(0, 0, width, height);
  }

  void drawPlanets(CanvasRenderingContext2D context) {
    sun.draw(context, new Point(width / 2, height / 2));
  }

  void requestRedraw() {
    window.requestAnimationFrame(draw);
  }

  void addAsteroidBelt(PlanetaryBody body, int count) {
    Random random = new Random();

// Asteroids are generally between 2.06 and 3.27 AUs.
    for (int i = 0; i < count; i++) {
      var radius = 2.06 + random.nextDouble() * (3.27 - 2.06);
      body.addPlanet(
          new PlanetaryBody(this, "#777",
          0.1 * random.nextDouble(), radius, radius * 2));
    }
  }

  num normalizeOrbitRadius(num r) => r * (width / 10.0);

  num normalizePlanetSize(num r) => log(r + 1) * (width / 100.0);
}

/**
 * A representation of a plantetary body.
 * This class can calculate its position for a given time index,
 * and draw itself and any child planets.
 */
class PlanetaryBody {
  final String color;
  final num orbitPeriod;
  final SolarSystem solarSystem;

  num bodySize;
  num orbitRadius;
  num orbitSpeed;

  final List<PlanetaryBody> planets = <PlanetaryBody>[];

  PlanetaryBody(this.solarSystem, this.color, this.bodySize,
                [this.orbitRadius = 0.0, this.orbitPeriod = 0.0]) {
    bodySize = solarSystem.normalizePlanetSize(bodySize);
    orbitRadius = solarSystem.normalizeOrbitRadius(orbitRadius);
    orbitSpeed = calculateSpeed(orbitPeriod);
  }

  void addPlanet(PlanetaryBody planet) {
    planets.add(planet);
  }

  void draw(CanvasRenderingContext2D context, Point p) {
    Point pos = calculatePos(p);
    drawSelf(context, pos);
    drawChildren(context, pos);
  }

  void drawSelf(CanvasRenderingContext2D context, Point p) {
// Check for clipping.
    if (p.x + bodySize < 0 || p.x - bodySize >= context.canvas.width) return;
    if (p.y + bodySize < 0 || p.y - bodySize >= context.canvas.height) return;

// Draw the figure.
    context..lineWidth = 0.5
      ..fillStyle = color
      ..strokeStyle = color;

    if (bodySize >= 2.0) {
      context..shadowOffsetX = 2
        ..shadowOffsetY = 2
        ..shadowBlur = 2
        ..shadowColor = "#ddd";
    }

    context..beginPath()
      ..arc(p.x, p.y, bodySize, 0, PI * 2, false)
      ..fill()
      ..closePath();

    context..shadowOffsetX = 0
      ..shadowOffsetY = 0
      ..shadowBlur = 0;

    context..beginPath()
      ..arc(p.x, p.y, bodySize, 0, PI * 2, false)
      ..fill()
      ..closePath()
      ..stroke();
  }

  void drawChildren(CanvasRenderingContext2D context, Point p) {
    for (var planet in planets) planet.draw(context, p);
  }

  num calculateSpeed(num period) =>
  period == 0.0 ? 0.0 : 1 / (60.0 * 24.0 * 2 * period);

  Point calculatePos(Point p) {
    if (orbitSpeed == 0.0) return p;
    num angle = solarSystem.renderTime * orbitSpeed;
    return new Point(orbitRadius * cos(angle) + p.x,
    orbitRadius * sin(angle) + p.y);
  }
}



// GRAVEYARD
//window.onMessage.listen( (event) => onData);
//void onData(){
//  dartMsg('in ondata');
//  print ("in ondata");
//  var a = 10 + 25;
//}



// no work? dataReceived
//  dataReceived(MessageEvent e) {
//    //var data = JSON.parse(e.data);
//    print(e);
//  }
// window.on.message.add(dataReceived);


//  var subscription = null;
//  var completed = false;
//  subscription = window.onMessage.listen(expectAsyncUntil1(
//      (e) {
//        var data = e.data;
//        if (data is String) return; // Messages from unit test protocol.
//        if (data['recipient'] != 'DART') return;  // Not for me.
//        completed = true;
//        subscription.cancel();
//        //expect(data, isMap);
//        var returnedValue = data['data'];
//        //expect(returnedValue, isNot(same(value)));
//        //verifyGraph(value, returnedValue);
//      },
//      () => completed));

//window.onMessage.listen( (event) => onData);
//dartMsg('yo homey3'+ window.onMessage);
//dartMsg("yo homey4 after addeventlistener");
//dartMsg('document.data.data.a:' + document.data.data);


//print("yo homeyx");
//dartMsg('yo homey3');

// from dartexpense  C:\Users\henryms\Downloads\DartSourceCode\3 Final Source Code\12-src\12-src\DartExpense
//  onFinishedListener(event) {
//    //    var data = JSON.parse(event.data);
//    //    if (data["type"] == "js2dart") {
//    //      if (data["action"] == "chartComplete") {
//    //
//    //        window.on.message.remove(onFinishedListener);
//    //
//    //        returnToListButton.disabled = false;
//    //      }
//    //    }
//  };
//  //window.on.message.add(onFinishedListener);




