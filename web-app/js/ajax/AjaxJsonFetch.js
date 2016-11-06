
/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/3/13
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */

var callcnt_ajax_FetchJsonTableData = 0
var latestCommand = null;


function resetWindow()
{
    alert ("in reset window");
    tinyMCE.getInstanceById('txtUpper').setContent("*")
    ajax_FetchJsonTableData('resetWindow i11 doc ready', "", "", false, getElementByIdHK('idTextFieldUtdoptions').value)  // hbkajax_FetchJsonTableData
    singletonUtilLayout_layout_showAll_state.publicMethod_showEastAndWest();

    //alert ("done reset window");

}
function ajax_FetchJsonTableData (callerId, commandHTML, commandText, looopinbackin, options)     // then processAjaxJsonFetchSuccess
{

    //alert ("in ajax_FetchJsonTableData callerid [" + callerId + "] with options:" + options)
    callcnt_ajax_FetchJsonTableData++;
    latestCommand = commandText ;
    var callcnt_ajax_FetchJsonTableData_local = callcnt_ajax_FetchJsonTableData;

    //alert ("in ajax_FetchJsonTableData") ;
    //var isThisaWrite = endsWithhk(commandText .toString().trim(), " w")
    oooo ("in here hbk")

    var isThisaWrite = endswithhk_includingHtmlConversion(commandText.toString().trim(), " w");

    //console.log ("comparing commandText  [" + commandText .toString().trim() + "]")

    //if (isThisaWrite)
    //    alert ("start ajax w write [" + stringify(commandText ) + "]")
    //else
    //    alert ("start ajax w/o write [" + stringify(commandText ) + "]")
    getElementByIdHK('spanid_status_filter').innerHTML="";
    var vsReadWrite = null;
    if (isThisaWrite)
        vsReadWrite="Writing..."
    else
        vsReadWrite="Read..."

    var loopBack = null;
    if (looopinbackin)
        loopBack="yes"
    else
        loopBack="no"

    var vSucessFail = null;

    if (!looopinbackin)
        msstart = Date.now();


    // update statuses
    var statushk = "[" + vsReadWrite + "] [" + commandText  + "]"
    //"<form name=\"clock\" onSubmit=\"0\">"+
    //"<INPUT TYPE=\"text\" NAME=\"face\" SIZE=11 VALUE =\"....Initializing....\">"+
    //"</form>";
    //getElementByIdHK('spanid_withinTableTest').innerHTML =  statushk //'spanid_withinTableTest'


    if (isThisaWrite)
    {
        UtilFadesAndPopups_message("Saved new record", 1250    );
    }


    //startclock()
    //        </SCRIPT>
    //            <BODY onLoad="startclock()">
    //                <!-------------------------------------------------------------------------------------------->
    //                    <form name="clock" onSubmit="0">
    //                        <INPUT TYPE="text" NAME="face" SIZE=11 VALUE ="....Initializing....">
    //                        </form>


    try
    {
        //console.log (" ******************************************* in ajax_FetchJsonTableData search [" + commandText  +  "] callerId [" + callerId + "] recursing [" + looopinbackin + "]")
        //alert(" ******************************************* in ajax_FetchJsonTableData commandText  [" + commandText  +  "] callerId [" + callerId + "]")

        //alert ("send2:" + stringify(send))
        var dataObj = {};
        dataObj["q"]=commandText ;
        domWideSearchStr = commandText ;
        //alert ("invoking table ajax get searching for json 'q' [" + dataObjHash_Search + "]")
        // var x = dataObj;
        //alert ("invoking table ajax get searching for json 'dataObjHash_Search.constructor' [" + dataObjHash_Search.constructor + "]")
        //alert ("invoking table ajax get searching for json 'dataObjHash_Search instanceof Hash' [" + (dataObjHash_Search instanceof Hash) + "]")
        //alert ("invoking table ajax get searching for json '( Object.prototype.toString.call( dataObjHash_Search ) ) ' [" + ( Object.prototype.toString.call( dataObjHash_Search ) ) + "]")
        //alert ("got here :" + getElementByIdHK('idTextFieldUtdoptions'))
        dataObj["options"]= getElementByIdHK('idTextFieldUtdoptions').value.trim();
        dataObj["q"]= commandText ;


        //alert ("in ajax_FetchJsonTableData sending pre covnersion ["+ commandText  + "]");
        //var send =
        //alert ("in ajax_FetchJsonTableData sending post conversion ["+ send + "]");
        //alert ("send:" + stringify(send))


        var ajaxSendMap = {}

        ajaxSendMap['q'] = unHTMLifyString(commandText );
        //alert ("sending commandHTML [" + commandHTML + "]");
        ajaxSendMap['commandHTML'] = commandHTML;
        ajaxSendMap['options'] = getElementByIdHK('idTextFieldUtdoptions').value.trim()

        // see also other ajax code snippets https://docs.google.com/document/d/1iaWbmmMD0Lc70cfjhQJhHdjkdX     0tzDoeCsRX42TNBBQ/edit
        //alert ("callerid [" + callerId + "] calling /ustodo/todo/ajax_getJSONTableData with options:" + getElementByIdHK('idTextFieldUtdoptions').value.trim())
        //alert ("commandHTML:"+commandHTML);
        var timeStartAjaxCall = new Date();
//        var foo = new Date();
//        var bar = new Date();
//        var baz = new Date();
//        baz.setTime(bar.getTime() - foo.getTime());
        //alert("hi mom");
//
        var jqxhr = $.post
            (
                "/todo/ajax_getJSONTableData"
                ,
                ajaxSendMap
                ,
                function(ajaxWebMap)
                {
                    //alert ("111 in function(data)");
                    //alert ("data:"+data);
                    //alert ("data.aaa:"+data.aaa);
                    //alert ("data.alFileLines:"+data.alFileLines);
                    //alert ("hi mom1:"+data)
                    //alert ("hi mom2:"+data['aaa'])
                    //alert ("hi mom3:"+data['bbb'])
                    //                    $.each(data.items, function(i,item){
                    //                        alert ("item:"+item)
                    //    //                            $("<img/>").attr("src", item.media.m).appendTo("#images");
                    //                        if ( i == 1 ) return false;
                    //                    });
                }
            )
                .success(function(ajaxWebMap)
                {
                    //var timeAjaxDoneCallPreProcessing = new Date();
                    vSucessFail = "Success";
                    //if (isThisaWrite)
                    //    getElementByIdHK('spanid_status').innerHTML = getElementByIdHK('spanid_status').innerHTML + " YOYOYOYOSAVED!!" + statushk
                    //getElementByIdHK('spanid_status').innerHTML = getElementByIdHK('spanid_status').innerHTML + " YOYOYOYOSAVED!!" + statushk

                    //oooo("ajax call ajax_getJSONTableData success, isThisaWrite:" + isThisaWrite)
                    //alert ("in here s");
                    processAjaxJsonFetchSuccess(ajaxWebMap, isThisaWrite)
                    //alert("setting it");

                    //var timeAjaxDonePostProcessing = new Date();
                    //timeElapsedServerOnAjaxCall = timeAjaxDoneCallPreProcessing.getTime() - timeStartAjaxCall.getTime()
                    //timeElapsedClientProcessingReturn = timeAjaxDonePostProcessing.getTime() - timeAjaxDoneCallPreProcessing.getTime()
                    //oooo ("ajax monitor timeElapsedServerOnAjaxCall:" + timeElapsedServerOnAjaxCall)
                    //oooo ("ajax monitor timeElapsedClientProcessingReturn:" + timeElapsedClientProcessingReturn)

                })
                .error(function(XMLHttpRequest, textStatus, errorThrown)
                {
                    UtilFadesAndPopups_message("FAILED: Save record", 750    );
                    vSucessFail = "Error";
                    //  commandText  +"] successfail [" + vSucessFail + "]")
                    alert("111 error return from ajax:"+'jjjj:'+XMLHttpRequest.responseText);
                    jQuery('#divdelstatus').html('jjjj:'+XMLHttpRequest.responseText);
                    //updateStatus( "ajidx [" + callcnt_ajax_FetchJsonTableData_local + "] >> Callback ERROR. Done in [" + (Date.now() - msstart) + "]", writehk)
                    //                    getElementByIdHK('id_status').innerHTML = ">>error"
                    //
                    //                    //alert ("ajax_FetchJsonTableData function error :" + textStatus + ", errorThrown:" + errorThrown)
                    //
                })
                .complete(function()
                {
                    //alert("111 complete");
                    //updateStatus(">> Callback COMPLETE. Done in [" + (Date.now() - msstart) + "]", 'No')
                    jQuery('#spanidundobotton').html("");

                })
            ;

        //alert ("post new json call")



    }
    catch (err) {

        //      commandText  +"] successfail [" + "N/A (error) " + "]");
        alert (" ******************************************* error in ajax_FetchJsonTableData search [" + commandText  +  "] callerId [" + callerId + "]");
        console.log (" ******************************************* error in ajax_FetchJsonTableData search [" + commandText  +  "] callerId [" + callerId + "]");
        handleErr("ajax caller", err);
    } finally {
        //updateStatus( "ajidx [" + callcnt_ajax_FetchJsonTableData_local + "] DONE [" + commandText  + "] total ms [" + (Date.now() - msstart) + "]", isThisaWrite )
        rowClickedDbId = "";
        rowClickedIndexCurrent  = "";

    }
    //alert("done ajax_FetchJsonTableData pagedata:"+pagedata)
    //alert("done ajax_FetchJsonTableData pagedata.length:"+pagedata.length)
}



function ajax_FindAndModifyRecord (callerId, dbId, newText, newHtml)
{
    //ajax_FindAndModifyRecord('testme', '515606f40364dc42be038ade', 'hkonTestUser')

    try
    {
        //alert ("in ajax_FindAndModifyRecord dbId:" + dbId + ", newText:" + newText)
        var dataObj = {};
        dataObj["newtext"]=newText;
        dataObj["options"]= getElementByIdHK('idTextFieldUtdoptions').value.trim();

        //dataObj["q"]= command;

        //var send = unHTMLifyString(command)
        var jqxhr = $.getJSON
            (
                "/todo/ajax_FindAndModify_FilelineRecordText"
                ,
                {
                    options: getElementByIdHK('idTextFieldUtdoptions').value.trim(),
                    callerId: callerId,
                    dbId: dbId,
                    newText: newText,
                    newHtml: newHtml
                    //q: 'jpey'
                }
                ,
                function(ajaxWebMap)
                {
                    //alert ("111 in function(data)");
                    //alert ("data:"+data);
                    //alert ("data.aaa:"+data.aaa);
                    //alert ("data.alFileLines:"+data.alFileLines);
                    //alert ("hi mom1:"+data)
                    //alert ("hi mom2:"+data['aaa'])
                    //alert ("hi mom3:"+data['bbb'])
                    //                    $.each(data.items, function(i,item){
                    //                        alert ("item:"+item)
                    //    //                            $("<img/>").attr("src", item.media.m).appendTo("#images");
                    //                        if ( i == 1 ) return false;
                    //                    });
                }
            )
                .success(function(ajaxWebMap)
                {
                    //alert("333 ajax_FindAndModifyRecord succcess");
                    vSucessFail = "Success";
                    UtilFadesAndPopups_message("Saved modified record", 1250    );
                    //alert("333 succcess");
                    //updateStatus("success ajax_FindAndModifyRecord")
                })
                .error(function(XMLHttpRequest, textStatus, errorThrown)
                {
                    UtilFadesAndPopups_message("FAILED: Save modified record", 1250    );
                    alert("333 ajax_FindAndModifyRecord error"+XMLHttpRequest.responseText);
                    updateStatus("ajax Error")
                })
                .complete(function()
                {
                    //alert("333 ajax_FindAndModifyRecord complete");

                })
            ;
    }
    catch (err) {

        updateStatus("ajax_FindAndModifyRecord Error");
        alert (" ******************************************* error in ajax_FindAndModifyRecord ");
        console.log (" ******************************************* error in ajax_FindAndModifyRecord ");
        //console.log (" ******************************************* error in ajax_FetchJsonTableData search [" + command +  "] callerId [" + callerId + "]")
        handleErr("ajax_FindAndModifyRecord Error", err);
    } finally {
        //updateStatus( "ajidx [" + callcnt_ajax_FetchJsonTableData_local + "] DONE [" + command + "] total ms [" + (Date.now() - msstart) + "]", isThisaWrite )

    }
    //alert("done ajax_FetchJsonTableData pagedata:"+pagedata)
    //alert("done ajax_FetchJsonTableData pagedata.length:"+pagedata.length)
}


function processAjaxJsonFetchSuccess(ajaxWebMap, isThisaWrite)
{
    //alert ("in processAjaxJsonFetchSuccess");

    //alert ("xxy in ajax_FetchJsonTableData success");
    //if (ajaxWebMap.ucp.html_redirecthk)

    //alert ("ajaxWebMap.html_redirecthk:" + ajaxWebMap.html_redirecthk)
    if (ajaxWebMap.html_redirecthk != null)
    {
        //alert ("will open ajaxWebMap.html_redirecthk [" + ajaxWebMap.html_redirecthk + "]")
        //window.open(ajaxWebMap.html_redirecthk,ajaxWebMap.html_redirecthk, 'height=900,width=600');
        // obs 130506 window.open(ajaxWebMap.html_redirecthk,ajaxWebMap.html_redirecthk);
        //window.open(ajaxWebMap.html_redirecthk,'_blank');
        //alert ("hi1")
        //window.open(ajaxWebMap.html_redirecthk,'name','toolbar=1,scrollbars=1,location=1,statusbar=0,menubar=1,resizable=1,width=800,height=600');
        //window.open(ajaxWebMap.html_redirecthk,'name','toolbar=1,scrollbars=1,location=1,statusbar=1,menubar=1,resizable=1,width=800,height=600');
        window.open(ajaxWebMap.html_redirecthk,'name','toolbar=1,scrollbars=1,location=1,statusbar=1,menubar=1,resizable=1');

        //window.open(ajaxWebMap.html_redirecthk);
        //window.open("http://www.ibm.com");
        //window.open("http://www.apple.com",'_newtab')
    }
    else
    {
        //alert ("ajaxWebMap.html_redirecthk is null")
    }
    // commandText  +"] arrayFlash [" + ajaxWebMap.arrayFlash + "]")
    //alert("111a success");
    //alert("111b success: data.cmdraw:"+ data.cmdraw);
    //alert("111c success: data.alFileLines:"+data.alFileLines);
    //alert("111d success: data.alFileLines.length    :"+data.alFileLines.length);
    //alert("111d success: data.alFileLines[0]:"+data.alFileLines[0]);
    //alert("111d success: data.alFileLines[0].toString():"+data.alFileLines[0].toString());
    //alert("111d success: data.alFileLines[1]:"+data.alFileLines[1]);
    //alert("111d success: data.alFileLines[1].toString():"+data.alFileLines[1].toString());

    //alert("111e success post genHTMLtableData isThisaWrite:"+isThisaWrite);

    document.title = ajaxWebMap.ucp.s2_cmdraw_normalized_NO_CMD.toUpperCase()

    getElementByIdHK('select_se_command').selectedIndex = 1;  // instruct set select index
    if ( isThisaWrite ) // ajaxWebMap.pendingRead )
    {

        UtilFadesAndPopups_message("Saved", 750    );

        var commandToRerun = ajaxWebMap.ucp.textPortionparsedCmd_Cat;
        //alert ("pre null tests ")
        if (isNull(commandToRerun)) // will be null on a read return from server, so use it, no need to strip non cat text
        {
            //alert ("it was null")
            commandToRerun = ajaxWebMap.ucp.s2_cmdraw_normalized_NO_CMD // after a write, run the corresponding read
        }
        else
        {
            //alert ("it was not null");
        }
        //alert ("xxx:post yes write:"+commandToRerun)
        //alert ("and the answer is commandToRerun:" + commandToRerun);

        ajax_FetchJsonTableData ("AJAXCALLERID_POSTWRITECALLER", commandToRerun, commandToRerun, true, ajaxWebMap.utdoptions);
        //updateStatus( "ajidx [" + callcnt_ajax_FetchJsonTableData_local + "] >> Done AJAX post write", writehk)
    }
    else  // not a write
    {
        //alert ("xxx:post not write")
        //updateStatus(">> Callback SUCCESS. Done web part in [" + (Date.now() - msstart) + "] arrayFlash [" + ajaxWebMap.arrayFlash + "]", 'No')
        //alert("adding to pagedata")
        // SET PAGE DATA MATCHING FILE LINES

        getPageData().setArrFileLines(ajaxWebMap.alFileLines)
        //getPageData().setArrFileLines(ajaxWebMap.alFileLines)

        getElementByIdHK('spanid_base_saved').innerHTML = "";

        pageData_currentSearchString = ajaxWebMap.ucp.s2_cmdraw_normalized_NO_CMD
        // commented: found [
        getElementByIdHK('spanid_status').innerHTML = "<span id='spanidFoundSearchedFor' " +
            strEventsForSearchString + " onmouseover=\"eventhandlerOnmouseverResetTxtUpperTo_PageData_currentSearch()\">" + pageData_currentSearchString +
            "</span><font size=-2>&nbsp;found</font> <font size=-2> " + getPageData().arrFileLines.length + "</font> "

        //alert ("ajaxWebMap.commandHTML:"  + ajaxWebMap.commandHTML);

        //$('body').data('pagedata', ajaxWebMap.alFileLines);

        //UtilData_dumpFileLines(); // UtilData.dumpFileLines
        //alert ("testxsx")


        //$('body').data('pagedata', ajaxWebMap.alFileLines);
        //$('body').data('pageData_arrStrRawTextCategs', arrStrRawTextCategs);

        //alert ("html_categs:" + html_categs)



        //alert ("pre genHTMLtableData")
        //var searchStr = ajaxWebMap.ucp.s2_cmdraw_normalized_NO_CMD

        var tableHtml = genHTMLtableData(
            ajaxWebMap.ucp.s2_cmdraw_normalized_NO_CMD,
            getPageData().arrFileLines,
            true // ignore visible bit
        ); // uses pagedata

        getElementByIdHK('spanid_withinTableTest').innerHTML = tableHtml

        if (document.getElementById('idCheckboxLinks').checked)
        {
            hideRowsNotContainingFullTermSet('http filter', "http", true, true, true, true, compareModes.contains)
        }


        //alert ("done genHTMLtableData");
        //console.log ("ttt:" + htmlTableData)

        //getElementByIdHK('spanid_status').innerHTML = Searched [${txtupper}] found [${alFileLines == null ? 0 : alFileLines.size()}] Selected [<span id="numlines">0</span>]
        //alert("getElementByIdHK('txtUpper').innerHTML:"+getElementByIdHK('txtUpper').innerHTML)

        // alert ("at set 2")

        // SEARCHED-FOR USED TO BE HOVER COLOR TIMER
        //        var strEventsForSearchString = "onmouseover  =\"after__processAjaxJsonFetchSuccess__updateTxtUpper_FilterShownList_AndColorSearchedForFont_UnlessEditing('spanidFoundSearchedFor', domWideSearchStr, 'yellow', false) \" " +
        //            " onmouseout=\"setTimer_turnspanidFoundSearchedFor_colorInNms ( 'spanidFoundSearchedFor', 'blue')\"";
        //var strEventsForSearchString = "onclick  =\"after__processAjaxJsonFetchSuccess__updateTxtUpper_FilterShownList_AndColorSearchedForFont_UnlessEditing('spanidFoundSearchedFor', domWideSearchStr, 'yellow', false) \" "
        var strEventsForSearchString = "onclick='onActionButtonClick_userInvokedSearch()' " ;

        document.getElementById('spanidFoundSearchedFor').style.color='blue'
        //document.getElementById('spanidFoundSearchedFor').onCloseButtonClick =()

        //document.getElementById('spanidFoundSearchedFor').style.color='green'

        refreshCategPageDataAndHTMLbasedOnArrFileLineContent();
        PageData_state_txtUpperEdited = false;


        //alert ("calling arrStrRawTextCategsCumulatorDeduped.length:" + arrStrRawTextCategsCumulatorDeduped.length)



        //alert ("post genHTMLtableData")


        //alert ("sdsd:"+ajaxWebMap.txtUpper)
        //alert ("setting url caller 5 getElementByIdHK('idTextFieldUtdoptions').value:" + getElementByIdHK('idTextFieldUtdoptions').value);
        //alert ("setting url caller 5b ajaxWebMap.ucp.s2_cmdraw_normalized_NO_CMD:" + ajaxWebMap.ucp.s2_cmdraw_normalized_NO_CMD);
        setURL(5, ajaxWebMap.ucp.s2_cmdraw_normalized_NO_CMD, getElementByIdHK('idTextFieldUtdoptions').value);
        //alert ("post genHTMLtableData 2")
        tinyMCE.getInstanceById('txtUpper').setContent(ajaxWebMap.ucp.s2_cmdraw_normalized_NO_CMD)

    }

    if (tinyMCE.get('txtUpper'))
    {
        if (getElementByIdHK('txtUpper').value.trim()== '*')
        {
            if (tinyMCE.get('txtUpper').getContent({format : 'text'}).trim() == "*")
            {
                //alert ("set to nothing 1");
                tinyMCE.getInstanceById('txtUpper').setContent("");
                //alert ("set to nothing 1b");
            }

            //alert ("set to nothing");
            //if (tinyMCE.getInstanceById('txtUpper') == null)
            //alert ("tinyMCE.getInstanceById('txtUpper') is null");
            //tinyMCE.activeEditor.setContent("")
            //tinyMCE.getInstanceById('txtUpper').setContent("nothing")
            //alert ("set to nothing2");
        }
    }


}

