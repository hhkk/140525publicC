<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="tdd1ext/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">


    <g:javascript src="jquery/jquery-1.7.1.min.js" />
    <g:javascript src="jquery/jquery.layout-latest.js" />
    %{--<g:javascript src="utiljs/onloadhk_fromMaingspss.js" />--}%


    <g:javascript src="ajax/AjaxJsonFetch.js" />
    %{--<g:javascript src="emit/emit0001.js" />--}%
    <g:javascript src="emit/emitCheckbox.js" />
    <g:javascript src="emit/table/emitJsonTable2.js" />
    <g:javascript src="emit/IndexSuperclass.js" />
    %{--<g:javascript src="jquery/jquery-1.7.1.min.js" />--}%
    <g:javascript src="tinymce/jscripts/tiny_mce/tiny_mce.js" />
    <g:javascript src="utiljs/handleErr.js" />
    %{--<g:javascript src="utiljs/jqueryAutocomplete.js" />--}%
    <g:javascript src="utiljs/onloadhk_fromMaingspss.js" />
    %{--<g:javascript src="utiljs/stacktrace.js" />--}%
    %{--<g:javascript src="utiljs/UtilClass.js" />--}%
    <g:javascript src="utiljs/UtilDOM.js" />
    <g:javascript src="utiljs/UtilString.js" />
    <g:javascript src="utiljs/UtilTinyMCE.js" />
    <g:javascript src="utiljs/UtilIndex.js" />
    <g:javascript src="utiljs/UtilClass.js" />
    <g:javascript src="utiljs/UtilJavascript.js" />

    %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="tdd1ext/css">--}%
    %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">--}%

    <script type="text/javascript">
        $(document).ready(function () {
            $('body').layout({ applyDefaultStyles: true });
            setBackgroundImage(true);
            var myLayout = $('body').layout();
            myLayout.sizePane('north', 150);
            myLayout.sizePane('south', 150);
            myLayout.sizePane('west', 150);
            myLayout.sizePane('east', 150);

//            var state = myLayout.state;
//
//// current state
//            var isWestPaneOpen       = !state.west.isClosed;
//            //alert ("isWestPaneOpen:"+isWestPaneOpen)
////            var isSouthPaneHidden    = state.south.isHidden;
////            alert ("isWestPaneOpen:"=+isWestPaneOpen)
////            var isEastPaneSliding    = state.east.isSliding;
////            alert ("isWestPaneOpen:"=+isWestPaneOpen)
//
//// current dimensions
//            var westCurrentSize      = state.west.size;
//            //alert ("westCurrentSize:"+westCurrentSize)
//            //state.west.size=100;
//            myLayout.sizePane("west", 10);
//            //alert ("westCurrentSize now?:"+westCurrentSize)
//            var westMinimumSize      = state.west.minSize;
//            //alert ("westMinimumSize:"+westMinimumSize)
//            var containerInnerWidth  = state.container.innerWidth;
//            //alert ("containerInnerWidth:"+containerInnerWidth)
//            var containerPaddingLeft = state.container.paddingLeft;
//            //alert ("containerPaddingLeft:"+containerPaddingLeft)
//            //myLayout.sizePane("ui-layout-west", 10);
        });



        function areWeInModeEdit()
        {
            return (rowClickedDbId != "")
        }

        var rowClickedDbId = ""; // -1 means none
        var rowClickedIndex  = ""; // -1 means none
        var rowClickedSavedText = ""


        function tableMouseOveraRow(idSeq)  // hbk121119
            //function tableMouseOveraRow()
        {
            //var dbid = getPageData()[idSeqBase].dbid
            if (!areWeInModeEdit()) // ie if no row clickedf
            {
                // before load, prevent premature setcontent
                try
                {
                    // set text in lower on mouse over row
                    var e1 = getElementByIdHK('idRowText'+(getPageData()[idSeqBase].dbid)).innerHTML.trim() + "hkhkkh"
                    tinyMCE.activeEditor.setContent(e1)
                    //tinyMCE.get('fld2Lower').focus();
                    tinyMCE.getInstanceById('txtUpper').focus()
                    // now set action images
                }
                catch (err)
                {
                    //alert ("in my fav error 1")
                    //printStackTrace();
                    //alert ("in my fav error 2")
                    handleErr2("tableMouseOveraRowerr", err);
                }
            }
        }

        var rowClickedDbId = ""; // -1 means none
        var rowClickedIndex  = ""; // -1 means none
        var rowClickedSavedText = ""
        function tableClickaRow(idSeq, doubleClick) // not tablecheckarow
        {
            try
            {
                var dbid = getPageData()[idSeqBase].dbid
                //alert ("in tableClickaRow idSeqBase [" + idSeqBase + "] dbl [" + doubleClick + "] rowClickedDbId + [" + rowClickedDbId + "]")
                var willIneedToEnableAnotherRow =  (rowClickedDbId != dbid)

                // turn off existing clicked row
                if ( rowClickedDbId != "" && rowClickedDbId != dbid) //if already editing a row then first unedit
                {
                    tableChangeUnclickRowTA(rowClickedIndex)
                }

                if (rowClickedDbId != dbid && getSelectionHtmlAndText().text != "")
                {

                    rowClickedDbId = dbid
                    rowClickedIndex = idSeqBase
                    rowClickedSavedText = getElementByIdHK('idRowText'+ rowClickedDbId).innerHTML;


                    //alert ("saving text was:" + rowsavedtext)
                    getElementByIdHK('idRowText'+ dbid).innerHTML = "<td><textarea " +
                            " style='color:blue ; align: center' " +
                            " id='idRowTextArea" + dbid + "' " +
                            " class='classRowTextArea' " +
                            " onkeyup='handleEnter(this, event)' " +
                            " style='display: none;color:red ; align: center' " +
                            " cols='90' " +
                            " opacity='0.1' " +
                            " >" +
                            "TEXTWILLGOHERE" +
//                    " rows='1' " +
                        //" cols='200'>shxx2 bibx    " +
                            "</textarea></td>";

                    //tinyMCE.get('idRowTextArea'+ rowClickedDbId).setContent("hh:" + rowClickedSavedText)
                    alert ("in here")
                    getElementByIdHK('idRowTextArea'+ rowClickedDbId).innerHTML = rowClickedSavedText;
                    //getElementByIdHK('idRowTextArea'+ rowClickedDbId).setContent("hh:" + rowClickedSavedText)
                    //tinyMCE.activeEditor.setContent("hh:" + rowClickedSavedText)



                    // getElementByIdHK('idRowTextArea'+ rowClickedId).focus
                    //no go $("#idRowTextArea"+ rowClickedId).tinymce().focus();
                    // no go tinyMCE.get('idRowTextArea'+ rowClickedId).focus();



                    if (!doubleClick)
                    {
                        alert ("doubleClick rowtext focus")
                        console.log ("rowtext focus")
                        $("#idRowTextArea" + rowClickedDbId).focus();
                        //var te = tinyMCE.activeEditor

                        //te.setContent('new contents');
                        //te.setContent('');
                        //te.focus();
                        //te.setContent(rowClickedSavedText);
                        //setCaretTo(te2, 3)
                        //te.focus();
                        //tinymce.execCommand('mceFocus',false,'idRowTextArea');
                    }
                    //getElementByIdHK('idRowTextArea'+ rowClickedId).setSelectionRange(0,50000);

                }

                //focus up top - triple click?
                if (doubleClick)
                {
                    initmcehbk("i2.desc.tableClickArow", // 1
                           CONST_classRowTextArea,
                            "newdocument,undo,redo,insertdate," +
                                "|,forecolor,backcolor," +
                                "|,bullist,numlist," +
                                "|,bold,italic,underline,strikethrough,fontselect,fontsizeselect|,tablecontrols"
                        //theme_advanced_buttons2 : "/" +
                        //   "|,outdent,indent,justifyleft,justifycenter,justifyright,justifyfull,"+
                            , "advanced"
                            , '24px' // 5
                            , "330"
                            , "txtUpper"
                    );
                    console.log ("dblclick focus")
                    alert ("dblclick focus")
                    $("#idRowTextArea" + rowClickedDbId).focus();
                    //                var te = tinyMCE.activeEditor
                    //
                    //                //te.setContent('new contents');
                    //                te.setContent('');
                    //                te.focus();
                    //                te.setContent(rowClickedSavedText);
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
                //            //        $('input:[class=utdtable]').each(function(inde  x, value)  {
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
                console.log ("ignoring error in tableClickaRow, rowIdClicked:" + rowClickedDbId)
                //alert ("error in tableClickaRow, rowIdClicked:" + rowIdClicked)
                handleErr(err);
            }
        }

        alert ("rrrrttttt")
        initmcehbk("i2.desc.fld1upperclass", // 1
                "fld1upperclass",
                "",
                "simple",
                '16px', // 5
                "50",
                "idRowTextArea" + rowClickedDbId);

//
//        $('#leftnav').append(
//
//                $(document).ready(function() {
//                    for(i = 1; i <=10; i++) {
//                        $('<button/>', {
//                            text: i, //set text 1 to 10
//                            id: 'btn_'+i,
//                            click: function () { alert('hi'); }
//                        });
//                    }
//                });
//
//        );
//        $(document).ready(function() {
//            for(i = 1; i <=10; i++) {
//                $('<button/>', {
//                    text: i, //set text 1 to 10
//                    id: 'btn_'+i,
//                    click: function () { alert('hi'); }
//                });
//            }
//        });
//
//

        $('#leftnav').append("this text was appended");








</script>

    <style type="text/css" media="all">
    #page-backgroundcenter {position:absolute; top:0; left:0; width:100%; height:100%;}
    #contentcenter {position:absolute; z-index:1; x_padding:50px; }
    #page-backgroundnorth {position:absolute; top:0; left:0; width:100%; height:100%;}
    #contentnorth {position:absolute; z-index:1; x_padding:50px; }
    #page-backgroundsouth {position:absolute; top:0; left:0; width:100%; height:100%;}
    #contentsouth {position:absolute; z-index:1; }
    #page-backgroundeast {position:absolute; top:0; left:0; width:100%; height:100%;}
    #contenteast {position:absolute; z-index:1; x_padding:50px; }
    #page-backgroundwest {position:absolute; top:0; left:0; width:100%; height:100%;}
    #contentwest {position:absolute; z-index:1; }


    </style>

    %{--<meta name="layout" content="main">--}%

</head>
<body onload="onloadhk_fromMaingspss(true)">
<r:require module="jquery-ui"/>

<span id="spanidRedirect">
    ${html_redirecthk}
</span>

<div class="ui-layout-center">
    <div id="page-backgroundcenter">
        <img class="backimagehk" id="artselectintcenter" width="100%" height="100%">
    </div>
    <div id="contentcenter">
        <div id="testDynamicContent1">

        </div>

        <div id="scroll_hider" style='overflow:hidden; width:1290px;'>
            <div id="listtablehk" name="hkhkhkhk" style="width: 1290px; height:800px; overflow: scroll; xxxonClick="checkBoxARow(this)">
            <table>
                <span id="spanid_withinTableTest">
                </span>
            </table>
        </div>  %{--listtable--}%
    </div>  %{--scroll_hider--}%

</div>  %{--contentcenter--}%
hi center
</div>   %{--ui-layout-center--}%

<div class="ui-layout-north" align="center">
    <div id="page-backgroundnorth">
        <img class="backimagehk" id="artselectintnorth"  width="100%" height="100%">
    </div>
    <div id="contentnorth" >
        UsToDo
        <span id="id_status_log">
        </span>

        <g:textField
                style="display: xxxnone"
                id="idTextFieldUtdoptions"
                class="unhideOnUpperMouseOver"
                title="command line options: 1) a '+' to see time details on records.  2) -tm=4m limit records to max of 4 months old ('m' and 'd'ays are the two time units)"
                name="utdoptions_formFieldHTMLkey"
                value="options: ${utdoptions}"
                size="120px%"
        />

        <g:textArea
                id="txtUpper"
                name="q"
                class="fld1upperclass"
                style="xxheight:4em;color:blue;margin-left:0px;font-size:16px; vertical-align: top"
                type="text"
                size="100%"
                cols="90"
                rows="dontMatterWillBeMce"
                onkeypress=""
                onkeyup="copyupdown(1);handleEnter(this, event);${
                    remoteFunction(action: 'ajax_autocompleteSearchUpper',
                            update: [success: 'leftnav', failure: 'not_listoutputxxxxxxxxxxx'],
                            params: '\'autocomp=\' + this.value',
                            onComplete: 'postautocomplete(\'txtUpper\');')}"

                value="${txtUpper}"
        />

    </div>
</div>

<div class="ui-layout-south">
    <div id="page-backgroundsouth">
        <img  class="backimagehk" id="artselectintsouth"  width="100%" height="100%">
    </div>


    <div id="contentsouth">
        hi south

        <span id=spanid_statusccc style="color:blue;margin-left:0px"  onmouseover="tinyMCE.activeEditor.setContent(domWideSearchStr)">
        </span>
        <span id=spanIdNumLines style="color:blue;margin-left:0px;">
        </span>
        <span id='spanIdBulkOpResult'>
        </span>

        <span id="id_status">    </span>
        <g:if test="${flash.message.toString().contains("Saved")}">
            <font color="orange" size = +2>
        </g:if>

        ${flash.message} %{-- Saved [ --}%
        <g:if test="${flash.message.toString().contains("Saved")}">
            </font>
            <br>
        </g:if>



    </div>
</div>

<div class="ui-layout-east">
    <div id="page-backgroundeast">
        <img  class="backimagehk" id="artselectinteast"  width="100%" height="100%">
    </div>
    <div id="contenteast">
        hi east
    </div>
</div>

<div    class="ui-layout-west">
    <div id="page-backgroundwest">
        <img  class="backimagehk" id="artselectintwest"  width="100%" height="100%">
    </div>
    <div id="contentwest">

        hi west
        <div id="leftnav">
    </div>
</div>

</body>
</html>
