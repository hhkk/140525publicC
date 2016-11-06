function eventhandlerOnclickButton_undoDelete()
{
    alert ("undo delete is not yet implemented, but you can ctrl-C or cmd-C now to copy to your paste buffer ["+getPageData().elementFromArrFileLineJustdeletedForUndo.lineMinusDateStr + "]");
}

function eventhandlerOnclickTestButton3()
{
    //alert ("in eventhandlerOnclickTestButton3");

    for (j = 0; j < getPageData().arrFileLines.length; j++)
    {
//        if (getPageData().arrFileLines[i].visiblehk)
//        {
//
//        }
    }

    // http://api.jquery.com/each/
    //    $("td[id^=" + value + "]") // startswith selector
    //$(".intro,.demo")
    //$('.classTableRowEven,.classTableRowOdd').each( function (element) {

    //alert ("found i:" + i);

    //console.log ("in buildHtmlRowPreRowTextArea : idSeqBase:" + idSeqBase + "rowText:" + rowText + "dbid:" + dbid)

}

function emitButton(spanidstring, buttonlabel, buttonfunction, buttonTooltip) {
    //alert ("in emitButton spanidstring:" + spanidstring)
        var arrayTableRowsHtml = [];
        arrayTableRowsHtml.push ("<input type='button'")
        arrayTableRowsHtml.push ("onClick=" + buttonfunction + "()")
        arrayTableRowsHtml.push ("value='"+buttonlabel+"'")
        arrayTableRowsHtml.push ("title='"+buttonTooltip+"'")
        arrayTableRowsHtml.push ("style='vertical-align:middle; position:relative;top:0px;z-index:0;'")
        arrayTableRowsHtml.push (">")

    //        <INPUT type="button" value="TestMe2" title="xRefresh" src="xxxxxxxxx.png" height="19px" width="19px"
//        name="clearhtmllog" class="xxxunhideOnUpperMouseOver" id="yyycommandlinetoggle"
//        onclick="testme();getElementByIdHK('id_status_log').innerHTML=''"
//            >


//        arrayTableRowsHtml.push ("<span id='id_status'></span>");
        var s = arrayTableRowsHtml.join(" ")
        //jQuery(spanidstring).html(s);  // eg #spanidundobotton'
        //alert ("in emitButton spanidstring LOOK FOR:" + spanidstring)
        document.getElementById(spanidstring).innerHTML = s;
        //alert ("done emitButton")
}


