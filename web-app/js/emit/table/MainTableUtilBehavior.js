/**

 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/26/13
 * Time: 7:02 AM
 * To change this template use File | Settings | File Templates.
 */


function MainTableUtilBehavior_tableMouseOveraRow(idSeqFiltered)  // hbk130413
{
    var idSeqBase = synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered];
    // mousing over row
    //oooo ("in tableMouseOveraRow idSeqFiltered:" + idSeqFiltered);
    //oooo ("in tableMouseOveraRow idSeqBase:" + idSeqBase);
    var txtUpperText = tinyMCE.get('txtUpper').getContent({format : 'text'}).trim();
    //if (!areWeInModeEdit() && (document.title.toLowerCase() == txtUpperText.toLowerCase())) // ie if no row clickedf
    if (!areWeInModeEdit()) // ie if no row clickedf
    {
        //var x = getElementByIdHK('idRowText'+(getPageData().arrFileLines[idSeqBase].dbid)).innerHTML.trim();
        //var x = getPageData().arrFileLines[idSeqBase].lineMinusDateStr.trim();
        //updateTxtUpper_FilterShownList_AndColorSearchedForFont_UnlessEditing('spanidFoundSearchedFor',  'blue')
        //if (!(typeof obj === "undefined"))
        //{
        if (getPageData().arrFileLines[idSeqBase].dboDbFlr.html != null)
        {
                //oooo ("html noy null")
            tinyMCE.getInstanceById('txtUpper').setContent(getPageData().arrFileLines[idSeqBase].dboDbFlr.html)
        }
        else
        {
            //oooo ("html is null")
            tinyMCE.getInstanceById('txtUpper').setContent(getPageData().arrFileLines[idSeqBase].lineMinusDateStr.trim())
        }
        tinyMCE.getInstanceById('txtUpper').focus()
       // }

        //document.getElementById('idRowText'+ getPageData().arrFileLines[idSeqBase].dbid).style.backgroundColor='#D6E6F9' // hbk 130414 C9E1FC
        document.getElementById('idRowText'+ synch_idSeqBaseMapToDbId[idSeqBase]).style.backgroundColor='#D6E6F9' // hbk 130414 C9E1FC

        // EBEEF2
    }
}

function MainTableUtilBehavior_colorTableRowTextBackground(idSeqFiltered)
{
    var color=null;
    if (idSeqFiltered%2==0)
        color = 'lightgrey' // see also css classTableRowEven
        //color = '#F0F0F0' // see also css classTableRowEven
    else
        color = 'white'
    document.getElementById('idRowText'+ synch_idSeqFilteredMapToDbId[idSeqFiltered]).style.backgroundColor=color

}

// leave a row
function MainTableUtilBehavior_tableMouseOutOfRow(idSeqFiltered)  // hbk130413
{
    //alert ('in MainTableUtilBehavior_tableMouseOutOfRow')
    // mousing out of row
    if (!areWeInModeEdit()) // ie if no row clickedf
    {
        MainTableUtilBehavior_colorTableRowTextBackground(idSeqFiltered);
    }
}

//colorFoundTextToIndicateOnmouseoverUsedSetFontColor

function setTimer_turnspanidFoundSearchedFor_colorInNms(elementId, color)
{
    //alert ("in setTimer_turnspanidFoundSearchedFor_colorInNms color:"+color);
    window.setTimeout(function() {
        document.getElementById(elementId).style.color=color
        //alert("timer worked");
    }, 700 /* run this after X ms */);

}




function after__processAjaxJsonFetchSuccess__updateTxtUpper_FilterShownList_AndColorSearchedForFont_UnlessEditing (strSpanIdFoundOrFiltered, textToPutInTxtUpper, colorToUse, showFilteredStat)  // hbk130413
{
    //console.log ("iupdateTxtUpper_FilterShownList_AndColorSearchedForFont_UnlessEditing colorToUse:" + colorToUse)
    //console.log ("iupdateTxtUpper_FilterShownList_AndColorSearchedForFont_UnlessEditing strSpanIdFoundOrFiltered:" + strSpanIdFoundOrFiltered)
    //alert ("iupdateTxtUpper_FilterShownList_AndColorSearchedForFont_UnlessEditing colorToUse:" + colorToUse);
    if (!areWeInModeEdit()) // ie if no row clickedf
    {
        //alert ("in 2 updateTxtUpper_FilterShownList_AndColorSearchedForFont_UnlessEditing");
        //try
        //{
            // FIRST REPLACE TEXT
        tinyMCE.getInstanceById('txtUpper').setContent(textToPutInTxtUpper)
        tinyMCE.getInstanceById('txtUpper').focus()

        // NOW COLOR
        //alert ("in color:" + colorToUse);
        //  alert ("in document.getElementById('spanidFoundSearchedFor'):" + document.getElementById('spanidFoundSearchedFor'));
        document.getElementById(strSpanIdFoundOrFiltered).style.color=colorToUse


        //console.log ("iupdateTxtUpper_FilterShownList_AndColorSearchedForFont_UnlessEditing setOfTermsInTxtUpper:" + setOfTermsInTxtUpper)
        //var setOfTermsInTxtUpper = convertStringToTermSetAndArray(textToPutInTxtUpper).set;
        // alert ("compareModes.contains:" + compareModes.contains)
        hideRowsNotContainingFullTermSet(
            "updateTxtUpper_FilterShownList_AndColorSearchedForFont_UnlessEditing2",
            textToPutInTxtUpper,
            true,
            true,
            true,
            showFilteredStat,
            compareModes.contains)
        //        }
        //        catch (err)
        //        {
        //            //alert ("in my fav error 1")
        //            //printStackTrace();
        //            //alert ("in my fav error 2")
        //            //console.log ("tableMouseOveraRowerr")
        //            handleErr2("tableMouseOveraRowerr", err);
        //        }
    }
    //else
    //alert ("in edit already")
    //            alert('in checkBoxARow class [:' + getClass(tthis) + '] id [' + tthis.id + ']');
    //        $('input:[class=utdtable][checked=checked]').each(function(index, value)  {
    //        $('input:[class=utdtable]').each(function(index, value)  {
    //            if ($(this).attr('id')==oid)
    //            {
    //                tinyMCE.activeEditor.setContent(jQuery(this).next().parent().next().find('.tabletext').html().trim())
    //            }
    //        });




}

function singleLevelTextareaHandler(idSeq, thishk, eventhk)
{
    alert ("in singleLevelTextareaHandler idSeqBase:" +idSeqBase + ", eventhk.keyCode:" + eventhk.keyCode)
    if (eventhk.keyCode == 27)
        tableChangeUnclickRowTA("from within singleLevelTextareaHandler", idSeqBase)
}


var CONST_classRowTextArea = "classRowTextArea";
function MainTableUtilBehavior_tableClickaRow(idSeqFiltered, doubleClick) // not tablecheckarow
{
    //alert ("in MainTableUtilBehavior_tableClickaRow idSeqFiltered:"+idSeqFiltered)
    //alert ("in MainTableUtilBehavior_tableClickaRow synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered]:"+synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered])
    UtilFadesAndPopups_message("Editing record: hit ESCape to save", 1250    );

    if (synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered] === rowClickedIndexCurrent)
    {

        //alert ("leavingSince synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered]:" + synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered]);
        //alert ("leavingSince rowClickedIndexCurrent:" + rowClickedIndexCurrent);
        return;
    }

    try
    {
        var idDb = getPageData().arrFileLines[synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered]].dbid

        // turn off existing clicked row
        if ( rowClickedDbId != "" && rowClickedDbId != idDb) //if already editing a row then first unedit
        {
            tableChangeUnclickRowTA("new row clicked so unclick old", rowClickedIndexCurrent)
        }


            //alert ("textareaContentPre:"+textareaContentPre)

        //        console.log ("111111111111 in tableClickaRow " +
        //            ", idSeqBase:" + idSeqBase +
        //            ", rowClickedDbId:" + rowClickedDbId +
        //            ", idDb:" + idDb +
        //            ", textareaContentPreText.trim() [" + textareaContentPreText.trim() + "] "
        //
        //        )

        //alert ("in ztableClickaRow set textareaContentPre:"+textareaContentPre);

        //alert ("in tableClickaRow idSeqBase [" + idSeqBase + "] dbl [" + doubleClick + "] rowClickedDbId + [" + rowClickedDbId + "]")

        // switch selected row
        //oooo ("getSelectionHtmlAndText().text != \"\":" + (getSelectionHtmlAndText().text != ""));
        //oooo ("getSelectionHtmlAndText().text :" + getSelectionHtmlAndText().text)
        if (rowClickedDbId != idDb && getSelectionHtmlAndText().text == "")
        {

            //oooo("inside in getSelectionHtmlAndText")
            rowClickedDbId = idDb
            rowClickedIndexCurrent = synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered];

            //var recordText = getElementByIdHK('idRowText'+ rowClickedDbId).innerHTML;

            //alert ("saving text was:" + rowsavedtext)
            getElementByIdHK('idRowText'+ idDb).innerHTML = "<td><textarea " +
                " style='color:purple ;font-size:large; align: center' " +
                        " id='idRowTextArea" + idDb + "' " +
                " class='classRowTextArea' " +
                " onkeyup='singleLevelTextareaHandler(" + synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered] + ", this, event)' " +
                " style='display: none;color:red ; align: center' " +
                " cols=80% " +
                " opacity='0.1' " +
                " >" +
                "TEXTWILLGOHERE" +
//                    " rows='1' " +
                //" cols='200'>shxx2 bibx    " +
                "</textarea></td>";

            //tinyMCE.get('idRowTextArea'+ rowClickedDbId).setContent("hh:" + getElementByIdHK('idRowText'+ rowClickedDbId).innerHTML)
            //alert("kjkjkjkj")
            //alert("test:"+getElementByIdHK('idRowText'+ rowClickedDbId).innerHTML)

            //getElementByIdHK('idRowTextArea'+ rowClickedDbId).innerHTML = getElementByIdHK('idRowText'+ rowClickedDbId).innerHTML;

            // getElementByIdHK('idRowTextArea'+ rowClickedDbId).innerHTML = recordText; // replacing TEXTWILLGOHERE


            if (getPageData().arrFileLines[synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered]].dboDbFlr.html != null) //replacing TEXTWILLGOHERE
            {
                //oooo ("html noy null");
                getElementByIdHK('idRowTextArea'+ rowClickedDbId).innerHTML = getPageData().arrFileLines[synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered]].dboDbFlr.html;
            }
            else
            {
                //oooo ("html is null");
                getElementByIdHK('idRowTextArea'+ rowClickedDbId).innerHTML = getPageData().arrFileLines[synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered]].lineMinusDateStr.trim();
            }

            //getElementByIdHK('idRowTextArea'+ rowClickedDbId).setContent("hh:" + getElementByIdHK('idRowText'+ rowClickedDbId).innerHTML)
            //tinyMCE.activeEditor.setContent("hh:" + getElementByIdHK('idRowText'+ rowClickedDbId).innerHTML)



            // getElementByIdHK('idRowTextArea'+ rowClickedId).focus
            //no go $("#idRowTextArea"+ rowClickedId).tinymce().focus();
            // no go tinyMCE.get('idRowTextArea'+ rowClickedId).focus();



            if (!doubleClick)
            {
                //console.log ("rowtext focus")
                //alert ("rowtext focus")
                $("#idRowTextArea" + rowClickedDbId).focus();
                //var te = tinyMCE.activeEditor

                //te.setContent('new contents');
                //te.setContent('');
                //te.focus();
                //te.setContent(getElementByIdHK('idRowText'+ rowClickedDbId).innerHTML);
                //setCaretTo(te2, 3)
                //te.focus();
                //tinymce.execCommand('mceFocus',false,'idRowTextArea');
            }
            //getElementByIdHK('idRowTextArea'+ rowClickedId).setSelectionRange(0,50000);
            // togglezoom
            UtilLayout_layout_hideAll();

        }

        //focus up top - triple click?
        if (doubleClick) // happens on single click here too
        {
            //alert ("in doubleclick")
            initmcehbk("index.desc.classRowTextArea", // 1  classRowTextArea
                "classRowTextArea",
                "newdocument,undo,redo,insertdate," +
                    "|,forecolor,backcolor," +
                    "|,bullist,numlist," +
                    "|,bold,italic,underline,strikethrough,fontselect,fontsizeselect,|,tablecontrols",


                //theme_advanced_buttons2 : "/" +
                //   "|,outdent,indent,justifyleft,justifycenter,justifyright,justifyfull,"+
                "simple", // "advanced",
                '24px',  // 5
                "330",
                "idRowTextArea" + rowClickedDbId

            );
            //alert ("dblclick focus")
            $("#idRowTextArea" + rowClickedDbId).focus();
            //alert ("what going on here?")
            var textareaContentPreText = tinyMCE.activeEditor.getContent({format : 'text'}).trim()
            var textareaContentPreHtml = tinyMCE.activeEditor.getContent({format : 'html'}).trim()
            getPageData().arrFileLines[synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered]].textareaContentPreText = textareaContentPreText
            getPageData().arrFileLines[synch_IdSeqFilteredMapToIdSeqBase[idSeqFiltered]].textareaContentPreHtml = textareaContentPreHtml
            //                var te = tinyMCE.activeEditor
            //
            //                //te.setContent('new contents');
            //                te.setContent('');
            //                te.focus();
            //                te.setContent(getElementByIdHK('idRowText'+ rowClickedDbId).innerHTML);
            //                //setCaretTo(te2, 3)
            //                te.focus();
            //                //console.log ("fld2lower focus")
            //                //$("#fld2Lower").focus();
            //                //tinyMCE.activeEditor.focus();
            //                //$("#idRowTextArea" + rowClickedDbId).focus();
            //                //getElementByIdHK('fld2Lower').focus();
            //                //tinymce.execCommand('mceFocus',false,'fld2Lower');
            //                window.scrollTo(0,0);
        }
        //getElementByIdHK('idRow'+ rowIdClicked).style.display = ''
        //getElementByIdHK('idRowTextArea'+rowIdClicked).style.display = 'none'
        //  alert ("only one row can be edited at a time")



        //

        //            //            alert('in checkBoxARow class [:' + getClass(tthis) + '] id [' + tthis.id + ']');
        //            //        $('input:[class=utdtable][checked=checked]').each(function(index, value)  {
        //
        //            getElementByIdHK('idRowText'+ oid).style.display = 'none'
        //            getElementByIdHK('idRowTextArea'+oid).style.display = ''
        //
        //            //getElementByIdHK('idRowTextArea'+oid).value =             getElementByIdHK('idRowText'+ oid).innerText.trim()
        //            getElementByIdHK('idRowTextArea'+oid).value =             getElementByIdHK('idRowText'+ oid).innerHTML.trim() // hbk 121113
        //            // to include ID in clicked row details
        //            // getElementByIdHK('idRowTextArea'+oid).value = oid + ":" + getElementByIdHK('idRowText'+ oid).innerText.trim()
        //
        //            tinyMCE.activeEditor.setContent(getElementByIdHK('idRowText'+ oid).innerHTML.trim()) // tinymce works - sets text value
        //            // hbk 121108tinyMCE.get('fld2Lower').execCommand('mceInsertContent', false, '');//!!! works - adds text at cursor
        //            // hbk 121108 tinyMCE.get('fld2Lower').focus();
        //            //tinyMCE.get('fld2Lower').focus();
        //            //tinyMCE.get('idRowTextArea'+oid).focus(); // hbk 121110
        //            getElementByIdHK('idRowTextArea'+oid).focus();
        //
        //            //121110tinymce.get('idRowTextArea'+oid).getBody().select();
        //            getElementByIdHK('idRowTextArea'+oid).setSelectionRange(0,50000);
        //            //getElementByIdHK('fld2Lower').focus();
        //
        //            //121110setCaretToPos(getElementByIdHK('idRowTextArea'+oid), 10000);
        //            //alert ("dddddddddddd")
        //
        //
        //            // ALTERNATIVE TOGGLES
        //            //tinymce.execCommand('mceToggleEditor',true,'idRowTextArea'+ oid);
        //            //tinymce.execCommand('mceToggleEditor',true,'fld2Lower');
        //            // no save button for now getElementByIdHK('idRowTextAreaSaveButton'+oid).style.display = '' // turn on button
        //
        //            //        $('input:[class=utdtable]').each(function(index, value)  {
        //            //            if ($(this).attr('id')==oid)
        //            //            {
        //            //                tinyMCE.activeEditor.setContent(jQuery(this).next().parent().next().find('.tabletext').html().trim())
        //            //
        //            //            }
        //            //        });
        //            //}
    }
    catch(err)
    {
        console.log ("ignoring error n MainTableUtilBehavior_tableClickaRow, rowIdClicked:" + rowClickedDbId)
        //alert ("error in tableClickaRow, rowIdClicked:" + rowIdClicked)
        handleErr(err);
    }
}


/**
 * convert tinymce back to regular textarea
 * write changes to DB and I guess back to record as needed
 * @param idSeq //  currently clicked
 */
function tableChangeUnclickRowTA(callerId, idSeqBase)
{
    //alert ("in tableChangeUnclickRowTA idSeqBase:"+idSeqBase + ", callerId:"+callerId);

    var s = tinyMCE.activeEditor.getContent().trim()

    var idDb = getPropFileLine(idSeqBase, 'dbid')

    //alert ("once this is working again ... see why two assignments below")
    // PRE ROW EDIT SAVE
    var textareaContentPreText = getPageData().arrFileLines[idSeqBase].textareaContentPreText
    var textareaContentPreHtml = getPageData().arrFileLines[idSeqBase].textareaContentPreHtml
    // POST EDIT
    //var textareaContentPostText = getElementByIdHK('idRowText'+ idDb).innerHTML;
    var textareaContentPostText = tinyMCE.activeEditor.getContent({format : 'text'}).trim()
    var textareaContentPostHtml = tinyMCE.activeEditor.getContent({format : 'html'}).trim()
    //var textareaContentPostHtml = tinyMCE.activeEditor.getContent({format : 'html'}).trim().replace("&nbsp;", " ")

    //alert ("textareaContentPostHtml:" + textareaContentPostHtml);


    var resetToRecordHtml = textareaContentPreHtml
    if ( textareaContentPreHtml != textareaContentPostHtml)
    {
//        if (confirm('Save? (previous version will be put in the command box)')) {
//            ajax_FindAndModifyRecord('called after I checked rec change after row edit and escape]', idDb, textareaContentPostText)
//
//            resetToRecordHtml = textareaContentPostText
//        } else {
//            resetToRecordHtml = textareaContentPreText
//        }

            ajax_FindAndModifyRecord('called after I checked rec change after row edit and escape]', idDb, textareaContentPostText, textareaContentPostHtml)

            getPageData().arrFileLines[idSeqBase]['lineMinusDateStr'] = textareaContentPostText
            getPageData().arrFileLines[idSeqBase]['dboDbFlr']['html'] = textareaContentPostHtml


            setPropFileLine(idSeqBase, 'lineMinusDateStr', textareaContentPostHtml)

            resetToRecordHtml = textareaContentPostHtml

        getElementByIdHK('spanid_base_saved').html = "savedhk4"

    }

    //    console.log ("22222222222 in `tableChangeUnclickRowTA " +
    //        ", callerId:" + callerId +
    //        ", idSeqBase:" + idSeqBase +
    //        ", idDb:" + idDb +
    //        ", rowClickedDbId:" + rowClickedDbId +
    //        ", textareaContentPre [" + textareaContentPre + "] " +
    //        ", textareaContentPostText [" + textareaContentPostText  + "]"
    //        )


    //alert ("in tableChangeUnclickRowTA idSeqBase:" + idSeqBase  + ", dbid:" +  dbid);
    //var s = getElementByIdHK('idRowText'+ dbid).innerHTML

    //alert ("restoring plaintext content dbid:" + dbid + ", with [" + s + "]")

    //        var x = "<td id='idRowText"+ dbid + "' " +
    //                " onmouseover=\"tableMouseOveraRow('"+ idSeqBase + "')\"" +
    //                " onclick=\"tableClickaRow('"+ idSeqBase +"', false) \""+
    //                " ondblclick=\"tableClickaRow("+ idSeqBase +", true)\" >"
    //                + s +
    //                "</td>";

    //console.log ("in tableChangeUnclickRowTA 2 [" + x + "]")
    //console.log ("resetToRecordHtml [" + resetToRecordHtml + "]")

    getElementByIdHK('idRowText'+ idDb).innerHTML = buildHtmlRowPreRowTextArea(idSeqBase, resetToRecordHtml, -99999999);
    //                            "<td id=\"idRowText"+ rowIdClicked + "\" onmouseover=\"tableMouseOveraRow('"+ rowIdClicked +
    //                                    "')\" onclick=\"tableClickaRow('"+ rowIdClicked +"')\">"
    //getElementByIdHK('idRowText'+ dbid).innerHTML = s

    //                            "<span " +
    //                            " style='color:blue ; align: center' " +
    //                            " id='idRowTextArea" + rowIdClicked + "' " +
    //                            " onkeyup='handleEnter(this, event)' " +
    //                            " style='display: none;color:blue ; align: center' " +
    //                            " opacity='0.1' " +
    //                            " rows='1' " +
    //                            " cols='90' " +
    //                            " >" +
    //                            s +
    //                        //" cols='200'>shxx2 bib " +
    //                            "</span>";
    //getElementByIdHK('idRowTextArea'+ rowIdClicked).innerHTML = s
    //getElementByIdHK('idRowTextArea'+ rowIdClicked).focus
    //getElementByIdHK('idRowTextArea'+ rowIdClicked).setSelectionRange(0,50000);

    // tinyMCE.activeEditor.setContent(getElementByIdHK('idRowText'+oid).innerHTML.trim())
    //getElementByIdHK('idRowText'+ rowIdClicked).innerHTML

    rowClickedDbId = ""
    rowClickedIndexCurrent = ""
    MainTableUtilBehavior_tableMouseOutOfRow(synch_IdSeqBaseMapToIdSeqFiltered[idSeqBase])
    //alert ("c == 27 DONE rowIdClicked:"+rowIdClicked)

}


