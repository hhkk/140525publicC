/**
 * Created by henryms on 5/25/2014.
 */
// <!-- ********************************* BEGIN DART EXPORT SECTION ********************************* -->
//<script>
//alert ("JS:0.01 hk document.location.pathname:"+document.location.pathname);
//alert ("JS:0.01b hk window.location.href:"+window.location.href);

var ii = 1;

function hbkJsListener(event){
    // if ( event.origin !== "http://javascript.info" )
    //  return



    //console.log (getClassSub("from hbkJsListener", event));
    //alert ("**************** TOP JS WINDOW MSG hbkJsListener LISTENJS0.004c " + getClassSub("from hbkJsListener event.data:", event.data));   //tostring:{"typehk":"js2dart","action":"action2","payload":"FROMJS: payload"}
    //alert ("**************** TOP JS WINDOW MSG hbkJsListener LISTENJS0.001d " + getClassSub("from hbkJsListener event.data:", event.data)); // string
    var myObj = eval('(' + event.data + ')');
    //alert ("JS hbkJsListener LISTENJS0.002d " + getClassSub("from hbkJsListener myObj:", myObj));
    //alert ("**************** TOP JS WINDOW MSG hbkJsListener LISTENJS0.004e " + getClassSub("from hbkJsListener myObj.typehk:", myObj.typehk));   //object



    //alert ("JS0.0044a is event type js2dart" + getClassSub("from hbkJsListener myObj", myObj));

    if (myObj.typehk == 'Json.Type_FromDart2Js')
    {
        alert("JS to process return from dart:" + myObj.typehk) // probably "js2dart"
    }
    else
    {
        alert("JS ignores msg of myObj.typehk:" + myObj.typehk)
        //alert(".0045b no to myObj.typehk == Json.Type_FromDart2Js, myObj.typehk:" + myObj.typehk)
    }

    //return; // truncated as events started firing on load

//    alert ("get ready for dart");



    //return;



    //    alert ("JS:0.02 top of hbkJsListener2. test if return");
    //if (event.data.contains('3344'))

    //alert ("JS:0.03 top of hbkJsListener2. received msg event.data:" + event.data);

    //    alert ("JS:0.036 top of hbkJsListener2. received msg event.data.type:" + event.data.type);
    //data2Js["type"] = "Json.Type_FromDart2Js";

    //    console.log ("in hbkJsListener hk")

    //document.getElementById("test").innerHTML = "received event.data:"+event.data
    //document.getElementById("test").innerHTML = "received event.data[4]:"+event.data[4]
    //document.getElementById("test").innerHTML = "received event.data.action:"+event.data.action
    }

function buttonClickHandler_SendThenReceiveFromDart() {
    //alert ("================= JS:0.04 top of buttonClickHandler_SendThenReceiveFromDart");
    var hkhkhk = document.querySelectorAll('#darta')
    //alert ("i  hkhkhk.length " + hkhkhk.length);
    var hkhkhk2 = document.querySelectorAll('.darta')
    //alert ("i  hkhkhk2.length " + hkhkhk2.length);
    //alert ("i  hkhkhk.item(0) " + hkhkhk.item(0));

    //alert ("i  hkhkhk " + hkhkhk.item(0));
    var hkhk1 = hkhkhk.hkhk1;
    //alert ("i  hkhk1 " + hkhk1);
    var outerLayout, innerLayout;


    var myObj = {};
    myObj["typehk"] = "js2dart";
    myObj["action"] = "action2";
    myObj["payload"] = "FROMJS: payload";
    var sSendToDart = JSON.stringify(myObj);  // flip side is eval
    //
    // alert ("JS:0.05 added msg:sSendToDart:" + sSendToDart);
    //alert ("in buttonClickHandler window.location.href:" + window.location.href);
    window.postMessage(sSendToDart,  "*");
    //window.postMessage(sSendToDart,  window.location.href);
    //console.log ("posted json message:" + sSendToDart);
    }

function buttonClickHandler2_testDomStorage() {
    alert ("================= JS:2.04 top of buttonClickHandler2_testDomStorage");
    var hk = document.querySelectorAll('#darta')
    alert ("i  hk.length " + hk.length);
    alert ("i  hk.item(0) " + hk.item(0));    //htmldivelement
    hk.item(0).setAttribute("bkbk","bkbkbk");
    alert ("i  hk.item(0).getAttribute('bkbk') " + hk.item(0).getAttribute("bkbk"));
    }

function buttonClickHandler3_testIfDartCanReadDomData() {
    //alert ("================= JS:3.04 top of buttonClickHandler3_testIfDartCanReadDomData");
    var data2 = {};
    data2["target"] = "MSGtargetDart";
    data2["type"] = "MSGtype";
    data2["action"] = "MSGaction";
    data2["payload"] = "FROMJS: MSGpayload";
    var sSendToDart = "{{{" + JSON.stringify(data2)+ "}}}";
    alert ("JS:0.05 postMessage " + sSendToDart + "");
    //alert ("in buttonClickHandler window.location.href:" + window.location.href);
    window.postMessage(sSendToDart,  "*");
    //alert ("JS:0.06 sSendToDart:" + sSendToDart);
    }

// from     dartexpense  C:\Users\henryms\Downloads\DartSourceCode\3 Final Source Code\12-src\12-src\DartExpense
function sendToDart(action, payload) {
    var data = {};
    data["type"] = "js2dart";
    data["action"] = action;
    data["payload"] = payload;
    var jsonData = JSON.stringify(data);
    alert ("JS:0.051 postMessage " + sSendToDart + "");
    window.postMessage(jsonData,window.location.href);
    // also seen this '*' form of postmessagewindow.postMessage(jsonData,"*");
    }

// fetch in JS from  http://javascript.info/tutorial/cross-window-messaging-with-postmessage
if (window.addEventListener){
    // alert ("pre addEventListener");
    addEventListener("message", hbkJsListener, false)
    //alert ("post addEventListener");
    } else {
    //alert ("pre attachEvent");
    //attachEvent("onmessage", hbkJsListener)
    //alert ("post attachEvent");
    }
//</script>
//<script type="application/dart" src="darttest/140121SolarTestNotSource/solar.dart"></script>
//    <script src="packages/browser/dart.js"></script>







//%{--asdasd--}%



//<!-- ********************************* END DART EXPORT SECTION ********************************* -->
