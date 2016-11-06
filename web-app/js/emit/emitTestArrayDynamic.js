function emitTestArrayDynamic() {
    //alert ("in onloadhk")
    var results = "";
    var myArray    = new Array();
    myArray[0] = "Customizable";
    myArray[1] = "Marketing Collateral";
    myArray[2] = "Online Marketing";
    myArray[3] = "Training";
    myArray[4] = "Event Resources";
    myArray[5] = "Marketing Logos";
    myArray[6] = "Competitive Solution Comparison";
    myArray[7] = "Sales Tools";
    myArray[8] = "Retail Marketing"
    myArray[9] = "Internal";

    results = "<table>";
    for (var i=0; i<myArray.length; i++) {
    results += "<tr><td>" + myArray[i] + "</td>";
    results += "<td>" + myArray[i+1] + "</td></tr>";
    }

results += "<tr><td colspan=2><a href='#' onclick='javascript:RedirectParentToDownload();'>View all content ></a></td></tr>";
results += "<table><br /> <br />";

var div = document.getElementById("javascript_onload_testdiv");
div.innerHTML = results;
}

window.onload=function(){alert("in window.onload")};
