
function buildHtmlRowPreRowTextArea (idSeqFiltered, rowTextOrHtml, dbid)
{
    var strClassTableRow = null;
    if (idSeqFiltered % 2 == 0)
    {
        strClassTableRow = 'classTableRowText classTableRowEven'
    } else
    {
        strClassTableRow = 'classTableRowText classTableRowOdd'
    }
    rowTextOrHtml = UtilString_replaceAll(rowTextOrHtml, "<br>", "&nbsp;");
    rowTextOrHtml = UtilString_replaceAll(rowTextOrHtml, "</br>", "&nbsp;");
    rowTextOrHtml = UtilString_replaceAll(rowTextOrHtml, "<br />", "&nbsp;");
    rowTextOrHtml = UtilString_replaceAll(rowTextOrHtml, "<p>", "&nbsp;");
    rowTextOrHtml = UtilString_replaceAll(rowTextOrHtml, "</p>", "&nbsp;");

    // oooo ("rowTextOrHtml [" + rowTextOrHtml + "]")

    //console.log ("in buildHtmlRowPreRowTextArea : idSeqBase:" + idSeqBase + "rowTextOrHtml:" + rowTextOrHtml + "dbid:" + dbid)
    return "<td style='word-wrap: break-word' id=\"idRowText"+ dbid +
        "\" class=\"" + strClassTableRow  +  // hbk130413
        "\" onclick=\"MainTableUtilBehavior_tableClickaRow('"+ idSeqFiltered+ "',true) " +
        //"\" onkeydown=\"keyHandlerForEachTableRow() " +
        "\" onmouseover=\"MainTableUtilBehavior_tableMouseOveraRow('"+ idSeqFiltered + "') \" "+
        " onmouseout=\"MainTableUtilBehavior_tableMouseOutOfRow('"+ idSeqFiltered + "') \" "+
        " ondblclick=\"MainTableUtilBehavior_tableClickaRow('"+ idSeqFiltered +"',true) \""+
        ">" +
        //"<pre>" +
        rowTextOrHtml +
        //UtilString_replaceAll(rowTextOrHtml, " ", "&nbsp;") +
        //"</pre>"+
        "</td>"
}

// tester

function keyHandlerForEachTableRow()
{
    alert ('in keyHandlerForEachTableRow')
}

//onkeyup="alert('key up!)"



var synch_dbIdToIdSeqBaseMap;
var synch_idSeqBaseMapToDbId;
var synch_idSeqFilteredMapToDbId;
var synch_dbIdToIdSeqFilteredMap;
var synch_IdSeqFilteredMapToIdSeqBase;
var synch_IdSeqBaseMapToIdSeqFiltered;

var synch_strFilterWordsInOriForm = null;

function genHTMLtableData(searchStr, arrFileLines, ignoreVisibleBit)
{
    try
    {
        var html_TableData = null;

        synch_dbIdToIdSeqBaseMap = {};
        synch_idSeqBaseMapToDbId = {};
        synch_idSeqFilteredMapToDbId = {};
        synch_dbIdToIdSeqFilteredMap = {};
        synch_IdSeqFilteredMapToIdSeqBase = {};
        synch_IdSeqBaseMapToIdSeqFiltered = {};
        
        
        //alert("in genHTMLtableData ignoreVisibleBit:" + ignoreVisibleBit);
        // table http://jsfiddle.net/dNjAW/
        //alert ("in render_jsonTableData_inDoc searchStr [" + searchStr + "] caller is [" + arguments.callee.caller.toString());
        //alert ("---------------in render_jsonTableData_inDoc pagedata")
        //alert ("in render_jsonTableData_inDoc pagedata [" + pagedata + "]")


        //stringableData = XMLHttpRequest.responseText;
        //alert ("XMLHttpRequest.responseText:" + XMLHttpRequest.responseText)   //  0, 1, ... // HBK121117

        //jsonTableData = JSON.parse(XMLHttpRequest.responseText);



        // console.log ("xxxxxxx  jsonTableData:" + jsonTableData) // HBK121117

        // found [6] props in first json record [ageLetter, class, fileLineNumThisLine1Based, filelinerawwhole_HTML, lineMinusDateStr, datestr]
        //alert ("jsonTableData.length [" + jsonTableData.length + "]");

        var arrayTableRowsHtml = [];
//            arrayTableRowsHtml.push ("<div id='listtablehkxxx' name='hkhkhkhkxxx' style='xxheight:650px; overflow : auto;' >")

        // console.log ("json on client[0]:" + jsonTableData[0])

        //arrayTableRowsHtml.push ("<div id='boundstable' width='700' style='word-wrap: break-word'>");
        arrayTableRowsHtml.push ("<table width='100%' style='table-layout:'fixed' align='left'>");
        //arrayTableRowsHtml.push ("<table width='100%'>");

//        arrayTableRowsHtml.push ("<col width='2%'>") ;// counter
        arrayTableRowsHtml.push ("<col width='2%'>") ;// checkbox
        arrayTableRowsHtml.push ("<col width='2%'>") ;// age
        arrayTableRowsHtml.push ("<col width='2%'>") ;// images archive
        arrayTableRowsHtml.push ("<col width='2%'>") ;// images touch
        arrayTableRowsHtml.push ("<col width='2%'>") ;// images trash
        arrayTableRowsHtml.push ("<col width='2%'>") ;// images DEMOTE
        arrayTableRowsHtml.push ("<col width='2%'>") ;// images PROMOTE
        arrayTableRowsHtml.push ("<col width='450px'>"); // text



//        arrayTableRowsHtml.push ("<col width='100'>"); // dbid
        //for (oneRow in jsonTableData) {
        //        alert ("jsonTableData.length:"   +jsonTableData.length)
        // JUST FYI ... getclass - see DB obj properties
        // getClass("pagedata[0]", pagedata[0])


        //alert ("reached html_TableData")

        //alert ("hihihi1");
        var idSeqFiltered = -1;
        var idSeqBase = -1;
        for (idSeqBase=0; idSeqBase < arrFileLines.length; idSeqBase++)
        {
            //if (filterToHttp && !UtilString_contains(getPageData().arrFileLines[idSeqBase]['lineMinusDateStr'], "http"))
            //    continue;

            var dbId = getPropFileLine(idSeqBase, 'dbid');

            //arrFileLines[idSeqBase].idxReal = idSeqBase;

            if (!arrFileLines[idSeqBase].visiblehk && !ignoreVisibleBit)
            {
                //alert ("hihihi");
                continue;
            }

            idSeqFiltered++;
            synch_dbIdToIdSeqBaseMap[dbId] = idSeqBase;
            synch_idSeqBaseMapToDbId [idSeqBase] = dbId;
            synch_idSeqFilteredMapToDbId [idSeqFiltered] = dbId;
            synch_dbIdToIdSeqFilteredMap [dbId] = idSeqFiltered;
            synch_IdSeqFilteredMapToIdSeqBase [idSeqFiltered] = idSeqBase;
            synch_IdSeqBaseMapToIdSeqFiltered [idSeqBase] = idSeqFiltered;

            arrFileLines[idSeqBase].visiblehk = true;

            // alert ("333333333a")
            arrayTableRowsHtml.push ("<tr id='idRow"+ getPropFileLine(idSeqBase, 'dbid') + "' style='vertical-align:middle'  " +
                " >" // hbk
            );
            //alert ("333333333b:"+"<tr id='idRow"+ pagedata[idSeqBase].dbid + "' style='vertical-align:middle' class='"+class1+"'>")

            // 1 COUNTER - remember to uncomment also above: arrayTableRowsHtml.push ("<col width='2%'>") ;// counter
//            arrayTableRowsHtml.push ("<td class='tableIndexSeq'>");
//            arrayTableRowsHtml.push ((idSeqBase+1).toString())
//            arrayTableRowsHtml.push ("</td>");
            //alert ("333333333c:")

            // 2 AGE
            arrayTableRowsHtml.push ("<td class='tableAgeLetter'>");
            //alert ("333333333d2:")
            arrayTableRowsHtml.push (getPropFileLine(idSeqBase, 'ageLetter'));
            //alert ("333333333d3:"+pagedata[idSeqBase].ageLetter)
            arrayTableRowsHtml.push ("</td>")

            // 3 CHECKBOX
            arrayTableRowsHtml.push ("<td class='tableImages'>");
            arrayTableRowsHtml.push ("<input type='checkbox' " +
                " class='utdtablecb' " +
                " name='cbsName2' " +
                " onClick='getSetNumSel2("+idSeqBase.toString()+")' " +
                " id='"+getPropFileLine(idSeqBase, 'dbid')+"' " +
                " idSeqBase='"+(idSeqBase+1).toString()+"' " +
                " />" );
            arrayTableRowsHtml.push ("</td>");
            //alert ("333333333d1:")

            // 4 IMAGEs ARCHIVE TOGGLE
            if ( getPageData().arrFileLines[idSeqBase].dboDbFlr.archived == true) // ARCHIVED
            {
                arrayTableRowsHtml.push ("<td class='tableImages' title=\"UNarchive (return to '*' listings).\" onclick=\"crudArchiveToggleOneRow('"+idSeqBase.toString()+"', true);\">");
                arrayTableRowsHtml.push ("<span id=\"idRowCellImages"+ getPropFileLine(idSeqBase, 'dbid') + "\"   >");
                arrayTableRowsHtml.push ("<img src=\"images/findicons.com/folder-grey-icon.png\" height=\"18\" width=\"18\"\">");
            }
            else  // NOT ARCHIVED
            {
                arrayTableRowsHtml.push ("<td class='tableImages' title=\"Archive (will not appear in '*' search but still findable)\" onclick=\"crudArchiveToggleOneRow('"+idSeqBase.toString()+"',false);\">");
                arrayTableRowsHtml.push ("<span id=\"idRowCellImages"+ getPropFileLine(idSeqBase, 'dbid') + "\"   >");
                arrayTableRowsHtml.push ("<img src=\"images/findicons.com/folder-icon-notarchived.png\" height=\"18\" width=\"18\"\">"); // star  asterisc.jpeg
            }

            arrayTableRowsHtml.push ("</span>");
            arrayTableRowsHtml.push ("</td>");

            // 5 IMAGEs TOUCH / MAKE CURRENT
            arrayTableRowsHtml.push ("<td class='tableImages' title=\"Set modified date to now (and re-sort)\" onclick=\"crudPromoteOneRow('"+idSeqBase.toString()+"');\">");
            arrayTableRowsHtml.push ("<span id=\"idRowCellImages"+ getPropFileLine(idSeqBase, 'dbid') + "\"   >");
            arrayTableRowsHtml.push ("<img src=\"images/findicons.com/uparrow.jpeg\" height=\"12\" width=\"12\"\">");

            arrayTableRowsHtml.push ("</span>");
            arrayTableRowsHtml.push ("</td>");

            // 7 IMAGEs PROMOTE
            arrayTableRowsHtml.push ("<td class='tableImages' title=\"Promote reminder level\">");
            arrayTableRowsHtml.push ("<span id=\"idRow2CellImages"+ getPropFileLine(idSeqBase, 'dbid') + "\"   >");
            arrayTableRowsHtml.push ("<img src=\"images/findicons.com/PromoteReminder.png\" height=\"17\" width=\"17\" onclick=\"deleteOneRow('"+idSeqBase.toString()+"');\">");
            arrayTableRowsHtml.push ("</span>")
            arrayTableRowsHtml.push ("</td>")

            // 6 IMAGEs DEMOTE
            arrayTableRowsHtml.push ("<td class='tableImages' title=\"Demote reminder level\">");
            arrayTableRowsHtml.push ("<span id=\"idRow2CellImages"+ getPropFileLine(idSeqBase, 'dbid') + "\"   >");
            arrayTableRowsHtml.push ("<img src=\"images/findicons.com/DemoteReminder.png\" height=\"17\" width=\"17\" onclick=\"deleteOneRow('"+idSeqBase.toString()+"');\">");
            arrayTableRowsHtml.push ("</span>")
            arrayTableRowsHtml.push ("</td>")

            // 6 IMAGEs 6 TRASH
            arrayTableRowsHtml.push ("<td class='tableImages' title=\"Delete\">");
            arrayTableRowsHtml.push ("<span id=\"idRow2CellImages"+ getPropFileLine(idSeqBase, 'dbid') + "\"   >");
            arrayTableRowsHtml.push ("<img src=\"images/findicons.com/gnome_edit_delete.png\" height=\"17\" width=\"17\" onclick=\"deleteOneRow('"+idSeqBase.toString()+"');\">");
            arrayTableRowsHtml.push ("</span>")
            arrayTableRowsHtml.push ("</td>")





            // HK - remember to mod column widths above as well





            //alert ("333333333d9a:")

            // 7 TEXT
            //arrayTableRowsHtml.push ("<td onmouseover=\"tableMouseOveraRow("+jsonTableData[ic]+")>")
            //arrayTableRowsHtml.push ("<td onmousxxxeover=\"tableMouseOveraRow("+jsonTableData[ic]+")'>")
            //arrayTableRowsHtml.push ("<td onmousxxeover('tableMouseOveraRow(" + ")'>")
            //alert ("creating cell ["+"<td onmousxxxeover=\"tableMouseOveraRow("+ pagedata[ic].dbid +") onclick='tableClickaRow()'>\"" + "]")
            //arrayTableRowsHtml.push ("<td onmousxxxeover=\"tableMouseOveraRow('"+ pagedata[ic].dbid +"') onclick='tableClickaRow()'>\"")
            //arrayTableRowsHtml.push ("<td onmousxxxeover=\"tableMouseOveraRow("+ pagedata[ic].dbid +") onclick='tableClickaRow()'>\"")
            //alert ("333333333d9b:")
//            arrayTableRowsHtml.push ("<span id=\"idRowCellOuter"+ pagedata[idSeqBase].dbid + "\"   >")

            // PUSH ONE ROW TO HTML ACCUMULATOR
            //alert ("pre")
            var textOrHtmlForTableRow = null;
            if (getPageData().arrFileLines[idSeqBase]['dboDbFlr']['html'] != null)
            {
                textOrHtmlForTableRow = getPageData().arrFileLines[idSeqBase]['dboDbFlr']['html'];
                //alert ("xxxxxxxxx textOrHtmlForTableRow:" + textOrHtmlForTableRow);
            }
            else
            {
                textOrHtmlForTableRow = getPageData().arrFileLines[idSeqBase]['lineMinusDateStr'];
                //alert ("dddddddd textOrHtmlForTableRow:" + textOrHtmlForTableRow);
            }

            arrayTableRowsHtml.push (
                buildHtmlRowPreRowTextArea(
                    idSeqFiltered,
                    //getPropFileLine(idSeqBase, 'lineMinusDateStr'),
                    textOrHtmlForTableRow,
                    dbId
            ));
            //alert ("post")

            //arrayTableRowsHtml.push ("</span>");


            // EXTRAS
                        // ARCHIVED
//                        arrayTableRowsHtml.push ("<td>arch2")
//                        arrayTableRowsHtml.push (getPageData().arrFileLines[idSeqBase].dboDbFlr.archived)
//                        arrayTableRowsHtml.push ("</td>")

//                        // DBID remember to comment or un above: arrayTableRowsHtml.push ("<col width='100'>"); // dbid
//                        arrayTableRowsHtml.push ("<td>dbid")
//                        arrayTableRowsHtml.push (getPageData().arrFileLines[idSeqBase].dbid)
//                        arrayTableRowsHtml.push ("</td>")
////
////                        // DATE
//                        arrayTableRowsHtml.push ("<td>date")
//                        arrayTableRowsHtml.push (getPageData().arrFileLines[idSeqBase].datestr)
//                        arrayTableRowsHtml.push ("</td>")
            // END EXTRAS

            arrayTableRowsHtml.push ("</tr>")
            //alert ("ff:" + varName )
            //alert ("ff2:" + jsonTableData[varName] )
            //alert ("ff3:" + getClass("jsonTableData[varName]",jsonTableData[varName]))
            //s            //alert ("ff5:" + varName)   //  0, 1, ...
            //alert ("ff6:" + getClass("varName",varName))

        }
        arrayTableRowsHtml.push ("</table>")
        //arrayTableRowsHtml.push ("</div>"); // boundstable

        //        alert ("getClass(jsonTableData):" + getClass(jsonTableData))   //  returns Array
        //        alert ("jsonTableData.length:" + jsonTableData.length)   //  returns Array
        //        alert ("getClass(jsonTableData[0]):" + getClass(jsonTableData[0])) // returns Object
        //        alert ("getClass(jsonTableData[0].ageLetter):" + getClass(jsonTableData[0].ageLetter)) // returns String
        // FAILS alert ("jsonTableData[0].keys():" + jsonTableData[0].keys()) // returns no such function keys


        //alert ("getClass(jsonTableData[0][0]):" + getClass(jsonTableData[0][0])) // UsToDo says this class is undefined., this.constructor:function Window() { [native code] }
        //for each (var item in jsonTableData) arrayTableRowsHtml << item ;

        //        for (varName in jsonTableData) {
        //            arrayTableRowsHtml.push
        //            //alert ("ff:" + varName )
        //            arrayTableRowsHtml.push ("")
        //            //alert ("ff2:" + jsonTableData[varName] )
        //            //alert ("ff3:" + getClass("jsonTableData[varName]",jsonTableData[varName]))
        //            //alert ("ff5:" + varName)   //  0, 1, ...
        //            //alert ("ff6:" + getClass("varName",varName))
        //
        //        }
        //for each (var item in jsonTableData) arrayTableRowsHtml << item ;

        //getElementByIdHK('spanid_liveJsonDataTable').innerHTML = "["+jsonTableData+"]"
        //alert ("set to:"+"ssss"+arrayTableRowsHtml.join(" "))
//            arrayTableRowsHtml.push ("</div>")
        // getElementByIdHK('spanid_liveJsonDataTable').innerHTML = arrayTableRowsHtml.join("")
    //        oooo("synch_dbIdToIdSeqBaseMap:" + synch_dbIdToIdSeqBaseMap);
    //        oooo("synch_idSeqMapToDbId:" + synch_idSeqMapToDbId);

        return arrayTableRowsHtml.join(" ")
    }
    catch (err) {
        alert ("error in /Users/hkon/sw/ustodo/110504utd/ustodo112/web-app/js/emit/emitJsonTable2.js")
        handleErr2("genHTMLtableData", err);
    }
}

/**
 * either as string or as set find full match or hide
 *
 * @param lineMinusDateStr
 */

var compareModes = {
    contains : 3,
    startsWith : 4
}

function hideRowsNotContainingFullTermSet
    (desc,
     strFilterWordsInOriForm,
     setmodefalseVsSubstrmodeTrue,
     caseInsensitiveTrue,
     bRefreshCategs,
     showFilteredStat,
     compareMode)
{

    oooo ("in hideRowsNotContainingFullTermSet desc:" + desc)
    if (!compareMode)
        alert ('compareMode undefined on desc:' + desc);

    if (caseInsensitiveTrue)
        strFilterWordsInOriForm = strFilterWordsInOriForm.toLowerCase();

    //showFilteredStat= true;
    var setMustHave = convertStringToTermSetAndArray(strFilterWordsInOriForm, CONST_ARR_CATEG_DELIMS).set;

    //  console.log ( "===============")
    //alert ( " in hideRowsNotContainingFullTermSet desc:" + desc);
    //alert ( " in hideRowsNotContainingFullTermSet compareMode:" + compareMode);
    //alert ( " in hideRowsNotContainingFullTermSet strFilterWordsInOriForm:" + strFilterWordsInOriForm)
    //alert ( " in hideRowsNotContainingFullTermSet setMustHave:" + setMustHave)
    //alert ( " in hideRowsNotContainingFullTermSet setMustHave:" + setMustHave + ", setmodefalseVsSubstrmodeTrue:" + setmodefalseVsSubstrmodeTrue);

    // refresh tabledata
    // FOR EACH TABLE ROW
    //alert ("in updateTurnOffArr... pagedata.arrFileLines:" + getPageData().arrFileLines)
    if (caseInsensitiveTrue)
        setMustHave = UtilIR_convertSetToLowerCase(setMustHave)

    var intPassedFilterCount = 0;

    //oooo ("strFilterWordsInOriForm :" + strFilterWordsInOriForm )

    // for each row of page data central main rows
    for (var i = 0; i < getPageData().arrFileLines.length; i++)
    {
        //alert (i + ". filelinerawpagedata:" + getPageData().arrFileLines[i].dboDbFlr.filelineraw);
        //       var strRowFileLineCategWhole  = convertFileLineToItsCateg(getPageData().arrFileLines[i].lineMinusDateStr);
        //hbk130630 var strRowFileLine  = getPageData().arrFileLines[i].lineMinusDateStr;
        var strRowFileLine  = getPageData().arrFileLines[i].dboDbFlr.filelineraw    ;
        if (caseInsensitiveTrue)
            strRowFileLine = strRowFileLine.toLowerCase();
        //      var setRowFileLineCategTerms =  convertStringToTermSetAndArray(strRowFileLineCategWhole , setCategDelimsToExclude).set

        var setRowFileLineTerms =  convertStringToTermSetAndArray(strRowFileLine , setCategDelimsToExclude).set
        var wereAllCategTermsFoundInFlr = true; // be optimistic
        var arrTermsInMustHaveFilter = setMustHave.keys();

        // for each word in text upper
        var seenThis = false;
        for (j = 0; j < arrTermsInMustHaveFilter.length; j++)
        {
            var strTermInMustHaveFilter = arrTermsInMustHaveFilter[j];
            if (caseInsensitiveTrue)
                strTermInMustHaveFilter = strTermInMustHaveFilter.toLowerCase();
            //alert (j + ". hh strTermInMustHaveFilter:"+ strTermInMustHaveFilter);
            if (strTermInMustHaveFilter != "*")
            {

                //alert(index + ': ' + value);
                //console.log ( " i [" + i + "] j [" + j + "] testing strTermInMustHaveFilter [" + strTermInMustHaveFilter + "] against strRowFileLine   [" + strRowFileLine + "]")

                //            if (!(strTermInMustHaveFilter in setRowFileLineTerms))
                //            {
                //                wereAllCategTermsFoundInFlr = false;
                //
                //          }
                if (!setmodefalseVsSubstrmodeTrue)
                {
                    alert ("in !setmodefalseVsSubstrmodeTrue")      ;
                    if (!setRowFileLineTerms.has(strTermInMustHaveFilter) && !setMustHave.has("*"))
                    {
                        wereAllCategTermsFoundInFlr = false;
                        //console.log ("   turn false strTermInMustHaveFilter:" + strTermInMustHaveFilter)
                        //console.log ( "FALSE SET MODE i [" + i + "] j [" + j + "] testing strTermInMustHaveFilter [" + strTermInMustHaveFilter + "] against strRowFileLine   [" + strRowFileLine + "]")
                        break;
                    }
                    //console.log ( "TRUE  SET MODE i [" + i + "] j [" + j + "] testing strTermInMustHaveFilter [" + strTermInMustHaveFilter + "] against strRowFileLine   [" + strRowFileLine + "]")
                } else   // substr mode not set mode
                {
                    //alert ("in here")
                    if (compareMode == compareModes.contains)
                    {
                        //alert ("in compareModes.contains")      ;
                        if (!UtilString_contains(strRowFileLine,strTermInMustHaveFilter))
                        {
                            wereAllCategTermsFoundInFlr = false;
                            //console.log ( "FALSE STR MODE i [" + i + "] j [" + j + "] testing strTermInMustHaveFilter [" + strTermInMustHaveFilter + "] against strRowFileLine   [" + strRowFileLine + "]")
                            break;
                        }
                    }
                    else if (compareMode == compareModes.startsWith)
                    {
                        //alert ("in compareModes.startsWith strRowFileLine.substring(20,-1):" + strRowFileLine.substring(20,-1));
                        //if (!seenThis)
                        //{
                        //alert ("in compareModes.startsWith strRowFileLine.substring(20):" + strRowFileLine.substring(20));
                        //alert ("in compareModes.startsWith strFilterWordsInOriForm:" + strFilterWordsInOriForm);
                        //seenThis = true;
                        //}

                        if (!(UtilString_startsWith(strRowFileLine.substring(20), strFilterWordsInOriForm)))
                        {
                            wereAllCategTermsFoundInFlr = false;
                            //console.log ( "FALSE STR MODE i [" + i + "] j [" + j + "] testing strTermInMustHaveFilter [" + strTermInMustHaveFilter + "] against strRowFileLine   [" + strRowFileLine + "]")
                            break;
                        }
                        else
                        {
                            if (false && i == 5)
                                alert ("yes starts with [" + strRowFileLine.substring(20) + "] [" + strFilterWordsInOriForm + "]");

                        }

                    }
                    else
                    {
                        alert ("UtDerror#000001, invalid compareMode:" + compareMode);
                    }
                    //console.log ( "TRUE STR MODE i [" + i + "] j [" + j + "] testing strTermInMustHaveFilter [" + strTermInMustHaveFilter + "] against strRowFileLine   [" + strRowFileLine + "]")

                }
            } // if not  *
        }


        //        $.each (setFilterTermsMustHave, function(index, strTermInMustHaveFilter ) {
        //            //alert(index + ': ' + value);
        //            console.log ( " i [" + i + "] testing strTermInMustHaveFilter [" + strTermInMustHaveFilter + "] against strRowFileLine   [" + strRowFileLine   + "]")
        //
        ////            if (!(strTermInMustHaveFilter in setRowFileLineTerms))
        ////            {
        ////                wereAllCategTermsFoundInFlr = false;
        ////            }
        //            if (!setRowFileLineTerms.hasOwnProperty(strTermInMustHaveFilter)) {
        //                wereAllCategTermsFoundInFlr = false;
        //                console.log ("   turn false strTermInMustHaveFilter:" + strTermInMustHaveFilter)
        //            }
        //            else
        //            {
        //                console.log ("   stay true strTermInMustHaveFilter:" + strTermInMustHaveFilter)
        //            }
        //
        //
        //        });

        // FOR EACH TERM IN THE FILTER CATEGORY TERM SET
        //if (wereAllCategTermsFoundInFlr)
        //console.log ( " i [" + i + "] setting strRowFileLine   [" + strRowFileLine   + "] to [" + wereAllCategTermsFoundInFlr + "]")
        //alert ("i:"+i);
        //oooo ("in hideRowsNotContainingFullTermSet matches:" + wereAllCategTermsFoundInFlr + ", " + i + " of " + getPageData().arrFileLines.length + " strRowFileLine:" + strRowFileLine);

        getPageData().arrFileLines[i].visiblehk = wereAllCategTermsFoundInFlr
        if (wereAllCategTermsFoundInFlr)
            intPassedFilterCount++;

    } // for each table row decide if it matches required input filter

    // UPDATE HTML
    getElementByIdHK('spanid_withinTableTest').innerHTML =  genHTMLtableData (
        latestCommand,
        getPageData().arrFileLines,
        false // ignore visible bit
    ); // uses pagedata

    restripeTableGreyWhite();

    //    var strMouseOverSearchWords = "onmouseover  =\"updateTxtUpper_FilterShownList_AndColorSearchedForFont_UnlessEditing('spanidFoundSearchedFor', domWideSearchStr, 'yellow') \" " +
    //        " onmouseout=\"setTimer_turnspanidFoundSearchedFor_colorInNms ( 'spanidFoundSearchedFor', 'blue')\"";
    //    getElementByIdHK('spanid_status').innerHTML = "found [" + getPageData().arrFileLines.length + "] for [<span id='spanidFoundSearchedFor' " +
    //        strMouseOverSearchWords + ">" + setMustHave.toString() + "</span>]"
    //    document.getElementById('spanidFoundSearchedFor').style.color='blue'

    // WORKS DON'T DELETE var strMouseOverFilterWords = "onmouseover  =\"updateTxtUpper_FilterShownList_AndColorSearchedForFont_UnlessEditing('spanidFilteredSearchedFor', getPageData().strFilterText, 'yellow') \" " +
    //    " onmouseout=\"setTimer_turnspanidFoundSearchedFor_colorInNms ( 'spanidFilteredSearchedFor', 'blue')\"";
    if (showFilteredStat)
    {
        //alert ("hi mom1")
        //oooo("hi mom1");
        var strMouseOverFilterWords = "";
        //oooo ("pre fade in desc:" + desc);
        //alert ("intPassedFilterCount:" + intPassedFilterCount)
//        getElementByIdHK('spanid_status_filter').innerHTML = "filtered to ["+ intPassedFilterCount + "] by [<span id='spanidFilteredSearchedFor' "
//            + ">" + strFilterWordsInOriForm + "</span>]";
        var bWas_spanid_status_filter_StatusPopulated = false
            if (getElementByIdHK('spanid_status_filter').innerHTML)
                bWas_spanid_status_filter_StatusPopulated = true;

        if (!bWas_spanid_status_filter_StatusPopulated)
        {
            $('#spanid_status_filter').fadeOut(0, function() {
                oooo ("fade in slow complete")
            });
        }

        // comment: filtered to [    by [
        var searchedForString = "<font size=-2>filtered to "+ intPassedFilterCount +
            "</font> <font size=-2>&nbsp;by</font> <span id='spanidFilteredSearchedFor' "
            + ">" + strFilterWordsInOriForm + "</span>"

        if (strFilterWordsInOriForm == "*")
        {
            $('#spanid_status_filter').fadeOut(300, function() {
                //alert ("fade in slow complete")
                getElementByIdHK('spanid_status_filter').innerHTML = "";
            });
        }
        else
        {
            getElementByIdHK('spanid_status_filter').innerHTML = searchedForString;
        }



        if (!bWas_spanid_status_filter_StatusPopulated)
        {
            //oooo ("hi mom2");
            $('#spanid_status_filter').fadeIn(400, function() {
                //alert ("fade in slow complete")
            });
        }
        synch_strFilterWordsInOriForm = strFilterWordsInOriForm;
            //strMouseOverFilterWords + ">" + setMustHave.toString() + "</span>]";

    }
    else
    {
        alert ("hi mom2")
        //getElementByIdHK('spanid_status_filter').innerHTML = "";  // noneeeeee
        //oooo ("pre fade out desc:" + desc);
        $('#spanid_status_filter').fadeOut('slow', function() {
            alert ("fade out complete")
        });
        //getElementByIdHK('spanid_status_filter').innerHTML = "";  // noneeeeee
    }

    getPageData().strFilterText = setMustHave.toString();
    if (document.getElementById('spanidFilteredSearchedFor') != null)
        document.getElementById('spanidFilteredSearchedFor').style.color='blue'

    //    getElementByIdHK('spanid_status').innerHTML = "found [" + getPageData().arrFileLines.length + "] for [<span id='spanidFoundSearchedFor' " +
    //        strMouseOverFilterWords + ">" + searchStr + "</span>]"
    //    document.getElementById('spanidFoundSearchedFor').style.color='blue'
    //
    //
    //
    //    getElementByIdHK('spanid_status_filter').innerHTML =
    //        "filtered to ["+ intPassedFilterCount + "] by [" + setFilterTermsMustHave + "]";
    //
    //
    //
    //

    // save for the end to show completion
    //tinyMCE.getInstanceById('txtUpper').setContent(setMustHave)

    if (bRefreshCategs)
        refreshCategPageDataAndHTMLbasedOnArrFileLineContent();

    //oooo ( " done hideRowsNotContainingFullTermSet " +    setMustHave)
    //alert ("done hideRowsNotContainingFullTermSet")

} // end hideRowsNotContainingFullTermSet

function setAsVisibleAllArrFileLineRows_WithRepaint()
{
    //oooo ("in setAsVisibleAllArrFileLineRows_WithRepaint");
    //alert ("in setAsVisibleAllArrFileLineRows_WithRepaint")
    for (var i = 0; i < getPageData().arrFileLines.length; i++)
    {
        getPageData().arrFileLines[i].visiblehk = true;
    }
    // UPDATE HTML
    getElementByIdHK('spanid_withinTableTest').innerHTML =  genHTMLtableData(
        latestCommand,
        getPageData().arrFileLines,
        false// ignore visible bit

    ); // uses pagedata

}

function restripeTableGreyWhite()
{

    $('.classTableRowText').each( function (i, element) {
        if (i % 2 == 0)
        //element.className = 'classTableRowText classTableRowEven';
            element.setAttribute("class", 'classTableRowText classTableRowEven');
        else
            element.className = 'classTableRowText classTableRowOdd';
        //element.setAttribute("class", 'classTableRowText classTableRowOdd');
    });

}



function getSetNumSel2(idSeq)
{
    oooo ("in getNumSel2");


    var cntchecked = 0;
    $('input:[class=utdtablecb][class=utdtablecb]').each(function(index, value)
    {
        if ($(this).attr('checked')=='checked')
        {

            //checkall('input:[class=tabletext][class=tabletext]')
            //$(this).checked='checked'
            oooo("checked");
            cntchecked++;
            //alert("html():"+$(this).html());
        }
        else
        {
            oooo("unchecked");
            //alert("uncheced")
        }

        //alert($(this).attr('checked'));
    });


    //$('input:[id=numlines][id=numlines]').each(function(index, value)  {
    // WORKS!! setattr $(this).attr('checked', 'checked') // setattr  WORKS
    //getElementByIdHK('spanIdNumLines').innerHTML = "Checked ["+cntchecked+"]"

    //}
    //alert ("numsel:" + cntchecked)
    oooo ("done getNumSel2");
    return cntchecked


    //alert ("hktest:" + $("input:[class='utdtable']").size());
    //alert ("hktest:" + $("input:[class=utdtable][checked='true']").size());

    //        $("input:[class=utdtable]").each(listoutput.children, function(e) {
    //                           alert ("hi hk");
    //                        e.style.display = "none";
    //                    })
    //
    //
    //        $("#hkhkhk > *").eachTag(listoutput.children, function(e) {
    //                alert ("hi hk");
    //            //e.style.display = "none";
    //        })





    // WORKED prior to 9/17/2012
    //        var numsel = 0;
    //        //var x = getClass(document.formnameRecList.cbsName);
    //        var x = getClass(document.listtablehk.cbsName2);
    //        if (x == 'NodeList')                              // IF MULTIPLE SELECTED
    //        {
    //            alert ("in x == nodelist");

    //
    //            for (i = 0; i < document.hkhkhkhk.cbsName2.length; i++) {
    //                //	alert('sssss');
    //                if (document.hkhkhkhk.cbsName2[i].checked == true) {
    //                    //alert ('ind selected:' + numsel)	;
    //                    numsel++;
    //                }
    //            }
    //        }
    //        else if (x == 'HTMLInputElement')                              // IF MULTIPLE SELECTED
    //        {
    //            alert ("in x == HTMLInputElement    ");
    //            if (document.hkhkhkhk.cbsName2.checked == true) {
    //                //alert ('ind selected:' + numsel)	;
    //                numsel++;
    //            }
    //        }
    //        else {
    //            alert("unknown error hk:"+x);
    //        }
    //
    //        //alert ('total selected:' + numsel)	;
    //        //getElementByIdHK('divid_numselected').value = 'ddddd:'+numsel;
    //        return numsel
}









