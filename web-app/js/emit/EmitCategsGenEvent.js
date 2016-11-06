function eventHandlerClickCategCB(i) // codeIndex: txtUpper paint and table filter on categ click'
{
    //alert ("may not be well implemented : in [" + i + "] eventHandlerClickCategCB:" + getPageData().arrFileLines[i])

    var strSentenceTermCollector = ""
    //var setSentenceTermCollector = new hkset()

    $('.categcb').each (function(indexCateg) {
        if (this.checked)
        {
            var setAndArrTermsInFilterCateg = convertStringToTermSetAndArray(getPageData().arrStrRawTextCategs[indexCateg], setCategDelimsToExclude);

            setAndArrTermsInFilterCateg.arr.forEach( function(termInCateg) {
                strSentenceTermCollector = strSentenceTermCollector + " " + termInCateg;
                //setSentenceTermCollector.add(termInCateg)
            })
//            alert("this.id:"+ this.id + ", this.checked, " + this.checked + ", indexCateg:" + indexCateg + ":" + setTermsInFilterCateg.tostring())

        }
    });


    var setmodefalseVsSubstrmodeTrue = true;
    hideRowsNotContainingFullTermSet("eventHandlerClickCategCB", strSentenceTermCollector, setmodefalseVsSubstrmodeTrue, true, false, true, compareModes.startsWith)
    //alert ("in testme1:" + tinyMCE.get('txtUpper').getContent({format : 'text'}));

}

var indexCurrentCategSelected = -1;
function eventHandlerMouseoverCateg(i)
{
    //console.log ("in eventHandlerMouseoverCateg");
    if(indexCurrentCategSelected == i)
    {
        //alert("early exit");
        return;
    }
    //oooo ("in eventHandlerMouseoverCateg i:" + i);
    //alert ("in eventHandlerMouseoverCateg:" + i);
    //alert ("in eventHandlerMouseoverCateg:" + $('body').data('pageData_arrStrRawTextCategs')[i])
    // alert ("hi mom");
    //console.log ("in eventHandlerMouseoverCateg getPageData().arrStrRawTextCategs[i]:" + getPageData().arrStrRawTextCategs[i])

    if (countCategCBsChecked() > 0)
        return;

    //getElementByIdHK('spanid_categs_selected').innerHTML = setTermsInFilterCateg.keys();
    var x = getPageData().arrStrRawTextCategs[i];
    if (i == -1)
    {
        //alert ("in over");
        x = "*";
    }
    hideRowsNotContainingFullTermSet("eventHandlerMouseoverCateg2", x, true, false, false, true, compareModes.startsWith)

    if (indexCurrentCategSelected >= 0)
        document.getElementById('idRowCateg'+indexCurrentCategSelected).style.backgroundColor='white' // hbk 130414 C9E1FC

    if (i >= 0)
        document.getElementById('idRowCateg'+i).style.backgroundColor='#D6E6F9' // hbk 130414 C9E1FC

    indexCurrentCategSelected = i;

    // refresh tabledata
}

//function eventHandlerMouseoutCateg(i)
//{
//    document.getElementById('idRowCateg'+i).style.backgroundColor='grey' // hbk 130414 C9E1FC
//}

function countCategCBsChecked()
{
    var cnt=0;
    $('.categcb').each (function(indexCateg) {
        if (this.checked)
        {
            cnt++;
        }
    });
    return cnt;
}

//
//function eventHandlerMouseOutCateg(event)
//{
//
//
//    oooo('in eventHandlerMouseOutCateg');
//    //best: http://jsfiddle.net/RH3tA/9/
//    // http://stackoverflow.com/questions/8021846/event-stoppropagation-not-working-in-chrome-with-jquery-1-7
//    //event.stopPropagation(); //cancel bubbling
//    event.stopImmediatePropagation(); //cancel bubbling
//    event.preventDefault(); //cancel bubbling
//
//    ele = event.target || event.srcElement
//    if (ele.id === "elementId_DivCateg"){
//        oooo('in eventHandlerMouseOutCateg right id');
//        setAsVisibleAllArrFileLineRows_WithRepaint()
//    }
//    else
//        oooo('in eventHandlerMouseOutCateg not my id in cancel bubbling');
//}








/**
 * build html for categs
 */
function refreshCategPageDataAndHTMLbasedOnArrFileLineContent ()
{
    //alert ("in refreshCategPageDataAndHTMLbasedOnArrFileLineContent getPageData().arrFileLines.length:" + getPageData().arrFileLines.length)
    // COLECT UP CATEGS AND DISPLAY BEFORE SHOWING HTML OF TABLE
    var setRawTextCategAsFileLineTruncated = new hkset();
    var arrRawTextCategAsFileLineTruncated = [];

    var idSeqBase = 0
    var found = 0;

    // pass #1 - gen counts for pass #2

    for (idSeqBase=0; idSeqBase < getPageData().arrFileLines.length; idSeqBase++)
    {
        // FIRST GET CATEGS OUT OF THE WAY IN THE RSAVE AWAY THE CATEG FOR THIS HBK130525

        if (getPageData().arrFileLines[idSeqBase].visiblehk)
        {
            found++;
            var categAsFileLineTruncated = null;

            categAsFileLineTruncated = convertFileLineToItsCateg(getPageData().arrFileLines[idSeqBase].lineMinusDateStr)

            if (categAsFileLineTruncated != null )
            {
                if (!(setRawTextCategAsFileLineTruncated.has(categAsFileLineTruncated)))
                arrRawTextCategAsFileLineTruncated.push(categAsFileLineTruncated)
                //alert("for line [" + lineMinusDateStr + "] adding categAsFileLineTruncated [" +categAsFileLineTruncated + "]")
                //alert("arrRawTextCategAsFileLineTruncated.length:"+arrRawTextCategAsFileLineTruncated.length)
                //alert  ("categAsFileLineTruncated:"+categAsFileLineTruncated);
                setRawTextCategAsFileLineTruncated.add(categAsFileLineTruncated);
                //oooo ("in categ builder categAsFileLineTruncated [" + categAsFileLineTruncated +
                //    "] count [" + setRawTextCategAsFileLineTruncated.get(categAsFileLineTruncated) + "]");

            }

        }
    }

    // SET PAGE DATA CATEGS
    getPageData().setArrRawTextCategs(arrRawTextCategAsFileLineTruncated)

    // NOW UPDATE CATEGS HTML by time, listing count
    //alert ("in genhtml_Categs arrRawTextCategAsFileLineTruncated.length:"+arrRawTextCategAsFileLineTruncated.length)
    //alert ("setRawTextCategAsFileLineTruncated:"+setRawTextCategAsFileLineTruncated);

//
    var h = "<h3 " +
        " onmouseover='eventHandlerMouseoverCateg(-1)' " +
        " onclick='onActionButtonClick_userClickedStarSearch()' " +
        " value='hi mom' " +
        "class=\"header2\">" +
        "<font size=-1>" +
        "<span id='thisone'>";


        // TABLE OF WIDGETS NEXT TO 'TAGS'
        h = h  + "<table id='elementId_Table_TagWidgets' >";
        h = h  + "<tr>";
        h = h  + "<td width='20%' align='left' >";
        h = h  + "BrowserHK";

        h = h  + "</td>";

        h = h  + "</tr>";
        h = h  + "</table>  ";

        // now close the tags and widget span
        h = h  + "</span>" +
            "</font>" +
            "</h3>";
        //"<h3 id=\"idheader\" class=\"header\" onmouseover=\"eventHandlerMouseoverCateg2xxx(-1)\" ><span id='spanid_categs_selected'  >*</span></h3>";




    // http://www.quirksmode.org/js/events_order.html
    // http://www.quirksmode.org/js/events_order.html

    // pass #2 - given counts from pass #1

    h = h  + "<div  id='codeloc1' class='ui-layout-content'>";

    h = h  + "<table id='elementId_TableCateg' >";
    h = h  + "<tbody id='elementId_TableCateg_tbody'>";


    h = h + "<tr id='idRowCateg" + i + "' " +
        " onmouseover=eventHandlerMouseoverCateg(-1) " +
        "><td><span>";
    h = h + "*<br>";
    h = h + "</span></td></tr>";



    for (i = 0; i < arrRawTextCategAsFileLineTruncated.length; i++)
    {
        //alert ("in loop")
        h = h + "<tr id='idRowCateg" + i + "' " +
            " onmouseover=eventHandlerMouseoverCateg(" + i + ") " +
            "><td><span>"
        var categAsFileLineTruncated;
        categAsFileLineTruncated = arrRawTextCategAsFileLineTruncated[i]
        //alert("testing ["+categAsFileLineTruncated + "] : " + setRawTextCategAsFileLineTruncated)
        h = h + "<input class = \"categcb\" id=\" categcbid_" + i + "\" type=\"checkbox\" onclick=\"eventHandlerClickCategCB(" + i + ")\"  ></input>" +
            setRawTextCategAsFileLineTruncated.get(categAsFileLineTruncated) +
            ". <a href=\"todo?q="+ categAsFileLineTruncated + "\" target=\"_blank\"  >     " +
            categAsFileLineTruncated + "</a><br>"; // hbk130525
        h = h + "</span></td></tr>"
    }
    h = h  + "</tbody>";
    h = h + "</table>"
    h = h + "</div>";
    allfound = false;
    getElementByIdHK('spanid_categs').innerHTML = h;

    getElementByIdHK('ui-layout-west-inner-hbk').style.overflow='hidden';
    getElementByIdHK('elementId_Table_TagWidgets').style.overflow='scroll';
    getElementByIdHK('codeloc1').style.overflow='scroll';
    getElementByIdHK('elementId_TableCateg').style.overflow='scroll';
    getElementByIdHK('elementId_TableCateg_tbody').style.overflow='scroll';



    // SLICK TRICK HERE!!  DON'T LOSE IT

    // TODO USE THIS EVENTHANDLER MORE:
    //document.getElementById("elementId_DivCateg").addEventListener('mouseout',eventHandlerMouseOutCateg,true);

        //    function eventHandlerMouseOutCateg(event) {
        ////    //best: http://jsfiddle.net/RH3tA/9/
        ////    if (countCategCBsChecked() > 0)
        ////        return;
        ////
        ////    e = event.toElement || event.relatedTarget;
        ////    if (e.parentNode == this ||
        ////        e == this) {
        ////        return;
        ////    }
        //        setAsVisibleAllArrFileLineRows_WithRepaint()
        ////    //alert('MouseOut');
        //    };




    //alert ("done refreshCategPageDataAndHTMLbasedOnArrFileLineContent found:" + found)
    //document.getElementById("elementId_TableCateg").addEventListener('mouseout',eventHandlerMouseOutCateg,true);




}
