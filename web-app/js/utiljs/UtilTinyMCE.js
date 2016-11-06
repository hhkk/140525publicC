/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/5/13
 * Time: 4:05 AM
 * To change this template use File | Settings | File Templates.
 */



var tSAVORIGINALACTIVETINYMCE = null;

function UtilTinyMCE_getContentTinyMce(whichTinyMce, format)
{
    // e.g., txtUpper, text or html
    return tinyMCE.get(whichTinyMce).getContent({format : format}).trim();
}



function initMyCustom() {
    //alert ("in myCustomOnInit:" + tinyMCE.activeEditor.getContent());
    ///alert ("hi hk 0")
    tinyMCE.activeEditor.setContent(tinyMCE.activeEditor.getContent())
    //alert ("hi hk 1")
    tinyMCE.activeEditor.focus();
    //$('#txtUpper_ifr').css('height', '70%');
    //alert ("hi hk 2")
    //tinyMCE.execCommand('mceFocus', false, 'txtUpper');
    //alert ("hi hk 3")

    northOnresize()

    tSAVORIGINALACTIVETINYMCE = tinyMCE.activeEditor.getBody();

//    if (tinyMCE.activeEditor == tinyMCE.get('txtUpper'))
//        //getElementByIdHK('txtUpper').value.trim()== '*' &&
//
//    {
//        //alert ("set to nothing");
//        //if (tinyMCE.getInstanceById('txtUpper') == null)
//            //alert ("tinyMCE.getInstanceById('txtUpper') is null");
//        //alert ("set to nothing 2");
//        tinyMCE.activeEditor.setContent("")
//        //alert ("set to nothing 2b");
//        //tinyMCE.getInstanceById('txtUpper').setContent("nothing")
//        //alert ("set to nothing2");
//    }


}


function myCustomOnChangeHandler(inst) {
//    alert("Some one modified something");
    //alert("in myCustomOnChangeHandler The HTML is now:" + inst.getBody().innerHTML);

//    ${
//        remoteFunction(action: 'ajax_autocompleteSearchUpper',
//                update: [success: 'leftnav', failure: 'not_listoutputxxxxxxxxxxx'],
//                params: '\'autocomp=\' + inst.getBody().innerHTML',
//                onComplete: 'postautocomplete(\'myCustomOnChangeHandler\');')
//    }
//

}


var stoppedTyping;

function initmcehbk(
    desc,  // 1
    taclass,
    buttons,
    themein,
    inFontSize, // 5
    inheight,
    textareaIdToInit)
{

//    try {
//        var urloffset  = ''
//        if (UrlExists('js/tinymce/jscripts/tiny_mce/themes/simple/skins/default/content.css'))
//            urloffset = '../'
//
////        UrlExistsAlert('js/tinymce/jscripts/tiny_mce/themes/simple/skins/default/content.css')
////        UrlExistsAlert('../js/tinymce/jscripts/tiny_mce/themes/simple/skins/default/content.css')
  //  UrlExistsAlert('http://yahoo.com')
        //UrlExistsAlert('yahoohenrykon.com')
////        UrlExistsAlert('js/template_list.js')
////        UrlExistsAlert('../js/template_list.js')
//    }
//    catch(err)
//    {
//        handleErr('UrlExistsAlertIfNot', err)
//    }
//



    try
    {
        //alert ("in initmcehbk desc [" + desc + "] taclass [" + taclass + "]")
        //console.log ("in initmcehbk")
        //console.log ("did init taclass:"+taclass)

        tinyMCE.init
        (
            {
//                paste_auto_cleanup_on_paste : true,
//                paste_preprocess : function(pl, o) {
//                    // Content string containing the HTML from the clipboard
//                    alert("hi mom" + o.content);
//                    o.content = "-: CLEANED :-\n" + o.content;
//                },


                // General options
                oninit : initMyCustom,

                setup : function(ed)
                {

                    try
                    {
//                        ed.onActivate.add(function(ed) {
//                            alert('Editor was activated: ' + ed.id);
//                        });


                        // 2 Add a custom button
                        ed.addButton('mybutton', {
                            title : 'myTitle',
                            image : 'myImage.png',
                            onclick : function() {ed.selection.setContent('<p>Hello!</p>')}
                        });


                        ed.onClick.add
                        (
                            function(ed,e)
                            {
                                //alert ("onClick:" + e.target.nodeName);
                                //if (e.target.nodeName == 'A'&&e.altKey) {
                                if (e.target.nodeName == 'A' && e.altKey) {
                                    window.open(e.target.href,"_blank");
                                }
                            }
                        );

                        ed.onKeyUp.add //hbk130616
                        (
                            function(ed,l)
                            {

                                // console.log("in keyup add")

                                var txtUpperText = tinyMCE.get('txtUpper').getContent({format : 'text'}).trim();
                                if (document.title.toLowerCase() != txtUpperText.toLowerCase())
                                    document.title = "* "+txtUpperText;

                                var txtUpperHtml = tinyMCE.get('txtUpper').getContent({format : 'html'}).trim();
                                //alert("in ed.onKeyUp.add3 l.keyCode :" + l.keyCode );
                                //                                if (txtUpperText.endsWith('...'))
                                //                                {
                                //                                        //alert ('yes match')
                                //                                    tinyMCE.activeEditor.setContent(txtUpperText[0])
                                //                                    //setCaretToPos(getElementByIdHK('txtUpper'), 10000)
                                //                                    //var rng = tinymce.DOM.createRng();
                                //                                    //tinyMCE.activeEditor.setSelectionRange(tinyMCE.activeEditor, 0, 1);
                                //                                    var textareahbk = document.getElementById("txtUpper");
                                //                                    console.log ("moveCaretToEnd now textareahbk:"+ textareahbk );
                                //                                    moveCaretToEnd(textareahbk);
                                //
                                //
                                //
                                //                                    //tinyMCE.activeEditor.selection.select(ed.dom.select('#_mce_temp_rob')[0])
                                //                                }
                                //                                else
                                //                                {
                                //                                    //alert ('no match')
                                //                                }

                                // Type here
                                //var txtUpperText = tinyMCE.activeEditor.getContent({format : 'html'}).trim();
                                //var txtUpperText = tinyMCE.activeEditor.getContent({format : 'text'}).trim();
                                //console.log ('in initmcehbk.1 in MCE Key up event: ' + l.keyCode + ", txtUpperText [" + txtUpperText + "]");

                                if (l.keyCode == 27) // escape
                                {
                                    //oooo ("console > got an escape")

                                    // escapehk
                                    if ( rowClickedDbId != "") //if already editing a row then first unedit
                                    {
                                        oooo ("console > got an escape 1")
                                        //alert ("escapehk")
                                        //alert ("txtUpper:"+ document.getElementById("txtUpper").value);
                                        //alert ("unclick id:" + rowClickedIndex)
                                        //console.log ("calling tableChangeUnclickRowTA")
                                        tableChangeUnclickRowTA("console > got an escape", rowClickedIndexCurrent)
                                        // togglezoom
                                        //innerLayout.show('south')
                                        //innerLayout.show('east')
                                        //innerLayout.show('west')

                                        //        console.log("post unclick rowClickedIndex:" + rowClickedIndex)
                                        //        console.log("post unclick rowClickedDbId:" + rowClickedDbId)

                                    }
                                    else
                                    {
                                        oooo ("console > got an escape 2")
                                        //document.activeElement.blur()
                                        //oooo("2setting to idTextFieldUtdoptions")
//                                        $('#idTextFieldUtdoptions').focus();
//                                        $('#idTextFieldUtdoptions').blur();

                                        //id="select_se_command"

                                        $('#select_se_command').focus();
                                        $('#select_se_command').blur();
                                    }
                                }
                                else if (l.keyCode == 13) // ENTER KEY
                                {
                                    if (true) // make this false to disable enter key from either form of tinymce rich text box
                                    {
                                        //alert ("gg:" + convertStringToAsciiCommaSeparatedString(tinyMCE.activeEditor.getContent({format : 'html'}).trim()))
                                        //tinyMCE.activeEditor.setContent(trimTrailingNbspWithSpace(tinyMCE.activeEditor.getContent({format : 'html'})));
                                        if (txtUpperText == "")
                                        {
                                            txtUpperText = "*"
                                            tinyMCE.get('txtUpper').setContent("*");
                                        }
                                        txtUpperText = tinyMCE.get('txtUpper').getContent({format : 'text'}).trim();
                                        txtUpperHtml = tinyMCE.get('txtUpper').getContent({format : 'html'}).trim();


                                        //as compared with var commandText = getElementByIdHK('txtUpper').value.trim()  // real ampersand
                                        //as compared with var commandHTML = getElementByIdHK('txtUpper').innerHTML.trim()   // encoded ampersand


                                        //alert ("s1 [" + convertStringToAsciiCommaSeparatedString(txtUpperText) + "]");
                                        //alert ("s2 [" + toHex(txtUpperText) + "]");

                                        // alert ("got an enter")
                                        while (txtUpperText.trim().endsWith("<br />"))
                                        {
                                            cnt++;if (cnt > 2)
                                            break;
                                            txtUpperText = txtUpperText.substr(0, txtUpperText.length-6).trim()
                                        }

                                        if (txtUpperText.endsWith("&nbsp;"))
                                        {
                                            alert ("do I ever get here?");
                                        }
                                        else
                                        {
                                            // alert ("3 Key up event1 in enter key [" + txtUpperText  + "]")

                                            var cnt = 0
                                            while (txtUpperText.trim().endsWith("<br />"))
                                            {
                                                cnt++;if (cnt > 2)
                                                break;
                                                //alert ("3.5 Key up event trimming from:" + txtUpperText)
                                                txtUpperText = txtUpperText.substr(0, txtUpperText.length-6).trim()
                                                //alert ("trimming to:" + txtUpperText)
                                            }
                                            //alert ("4 Key up event in enter key pre filter [" + txtUpperText + "]")
                                            while (txtUpperText.trim().endsWith("<br />"))
                                            {
                                                cnt++;if (cnt > 2)
                                                break;
                                                //alert ("trimming from:" + txtUpperText)
                                                txtUpperText = txtUpperText.substr(0, txtUpperText.length-6).trim()
                                                //alert ("trimming to:" + txtUpperText)
                                            }
                                            //alert ("5 1.777 Key up event in enter key ready to submit[" + txtUpperText + "] ensdwith ' w' [" + txtUpperText.endsWith(" w") + "]")
                                            //alert ("5 1.777 Key up event in enter key ready to submit [" + txtUpperText + "] getElementByIdHK('idTextFieldUtdoptions').value [" + getElementByIdHK('idTextFieldUtdoptions').value + "]")
                                            //console.log ("calling ajax from mcehandler 1.777")

                                            // see also in gsp "        function onActionButtonClick_userClickedReset() {"
                                            ajax_FetchJsonTableData("AJAXCALLERID_CALLER_MCEHANDLER", txtUpperHtml.trim(), txtUpperText.trim(), false, getElementByIdHK('idTextFieldUtdoptions').value)

                                        }
                                        //alert ("in tinymce.dom.Event.cancel(e);")
                                        //tinymce.dom.Event.cancel(evt);  hbk130616

                                    }
                                }
                                else // ELSE A CHARACTER
                                {
                                    //alert ("in ed.onKeyUp.add");
                                    //alert ("6 Key up event in else remotefunction")
                                    //copyupdown(2);
                                    //                            ${
                                    //                                remoteFunction(action: 'ajax_autocompleteSearchUpper',
                                    //                                    update: [success: 'leftnav', failure: 'not_listoutputxxxxxxxxxxx'],
                                    //                                    //params: '\'autocomp=\' + l.content',
                                    //                                    params: '\'autocomp=\' + tinyMCE.activeEditor.getContent({format : \'text\'})',
                                    //                            onComplete: 'postautocomplete(\'from_tiny_mce\');')
                                    // RESTRICT VIEWED RECORD SET
                                    //alert ("comparing to:" + hksetTermsInTxtupperSpaceDelimited.toString())
                                    //alert ("comparing:" + taclass)
                                    //alert ("to:" + CONST_FIELD_UPPER_CLASS )

                                    if (taclass == CONST_FIELD_UPPER_CLASS )
                                    {
                                        PageData_state_txtUpperEdited = true;
                                        //alert ("set edted to true");
                                        //alert ("eureka it'txtUpperText true!");
                                        if (document.getElementById('idCheckboxFilter').checked)
                                        {
                                            // is there already a timer? clear if if there is
                                            // var stoppedTyping // outside the function
//                                            if (stoppedTyping)
//                                                clearTimeout(stoppedTyping);
//                                            // set a new timer to execute 3 seconds from last keypress
//                                            stoppedTyping = setTimeout(function(){
//                                                //alert ("you stopped typing seconds are up - filter  !!")
                                                hideRowsNotContainingFullTermSet('character in mce', tinyMCE.get('txtUpper').getContent({format : 'text'}), true, true, true, true, compareModes.contains)
                                            //}, 500); // millisecond timer


                                        }
                                    }




                                }
                            }

                        );

                        ed.onInit.add(function(editor) {
                            tinymce.dom.Event.add(editor.getBody(), "focus", function(e) {
                                //oooo('focus1 ' +taclass);
                                if (taclass == CONST_FIELD_UPPER_CLASS )
                                {
                                    //oooo('focus12 '+rowClickedIndexCurrent)
                                    if (rowClickedIndexCurrent != "")
                                        tableChangeUnclickRowTA("unclick row hk", rowClickedIndexCurrent)
                                }

                            });
                        });

                        ed.onInit.add(function(editor) {
                            tinymce.dom.Event.add(editor.getBody(), "blur", function(e) {
                                //oooo('blur2 ' +taclass);
                            });
                        });

                        ed.onInit.add (
                            function(ed)
                            {
                                //alert ("in mce onInit init . add")
                                ed.getDoc().body.style.fontSize = inFontSize;
                                ed.getDoc().body.style.color = "olive";          // tinymce color constrolled here hbk color
//                                ed.getDoc().body.style.padding = "0"; //hbk
//                                ed.getDoc().body.style.margin = "0";  // hbk
                                //tinyMCE.execCommand('mceFocus', false, 'txtUpper');
                                var rich = (typeof tinyMCE != "undefined") && tinyMCE.activeEditor != null && !tinyMCE.activeEditor.isHidden();
                                //alert ("rich2:" + rich);
                                //var s = tinyMCE.activeEditor.getContent();
                                //tinyMCE.activeEditor.setContent(s)

                                //tinyMCE.activeEditor.focus();

                                //tinyMCE.get('txtUpper').focus();

                                //window.parent.tinyMCE.get(textareaIdToInit).focus()

                                this.focus();

                                window.parent.tinyMCE.execCommand('mceFocus', false, textareaIdToInit);



                            }
                        );

                    }
                    catch(err)
                    {
                        handleErr(err);
                    }
                },
                //onchange_callback : "myCustomOnChangeHandler",
                mode : 'specific_textareas', // see http://www.tinymce.com/wiki.php/Configuration:editor_selector
                //mode : 'textareas', // see http://www.tinymce.com/wiki.php/Configuration:editor_selector
                //editor_selector : /(mceEditor|mceRichText)/   not tried - from article
                editor_selector : taclass, // only textareas with this clas
                //editor_selector : "classRowTextArea", // only textareas with this clas
                //elements : "fld2Lower", // only textareas with this clas

                //theme : themein, //advanced or simple
                theme : "simple", //advanced or simple
                //theme : "advanced", //advanced or simple

                // https://groups.google.com/forum/#!msg/jquery-ui-layout/dGIYxZX2JLI/W074IODoGv8J
                //north__onresize: function () { alert ("resized"); },
                //south__onresize: alert ("resized") },
                ////plugins : "autoresize, autolink,lists,spellchecker,pagebreak,style,layer,table," +
                // "save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media," +
                // "searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars," +
                // "nonbreaking,xhtmlxtras,template",
                //plugins : "autoresize",

//                //plusin 'autoresize',  // hbk130414
                width: '100%',
                height: '100%',
//                autoresize_min_height: 200,
//                autoresize_max_height: 800,





                //width: "100%",
                //theme_advanced_statusbar_location : "bottom",
                //theme_advanced_resizing : true,
                //theme_advanced_toolbar_location : "left",
                //theme_advanced_toolbar_align : "left",

                theme : 'simple',
                //plugins : 'autoresize',

// from http://stackoverflow.com/questions/1799123/how-to-automatic-resize-tinymce
//                width: '100%',
//                height: 400,
//                autoresize_min_height: 400,
//                autoresize_max_height: 800,




                //height: inheight,
                // Theme options
                //theme_advanced_buttons1 : buttons,

                //theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
                //theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,spellchecker,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,blockquote,pagebreak,|,insertfile,insertimage",

                theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleselect,formatselect,fontselect,fontsizeselect",
                // NEAR ORIGINAL        theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
                // NEAR ORIGINAL       //theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
                // NEAR ORIGINAL       //theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,spellchecker,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,blockquote,pagebreak,|,insertfile,insertimage",
                //init_instance_callback: function (inst) { alert ("in resize"); inst.execCommand('mceAutoResize'); },
                //init_instance_callback: function (inst) { alert("hi") },
                // Skin options
                //skin : "o2k7",
                //skin_variant : "silver",
                toolbar_items_size : 'small',
                // Example content CSS (should be your site CSS)
                //content_css : "js/tinymce/jscripts/tiny_mce/themes/simple/skins/default/content.css",




                // Drop lists for link/image/media/template dialogs
                template_external_list_url : "js/template_list.js",
                external_link_list_url : "js/link_list.js",
                external_image_list_url : "js/image_list.js",
                media_external_list_url : "js/media_list.js",
                //js/tinymce/examples/lists/link_list
                //        template_external_list_url : "../js/tinymce/examples/lists/template_list.js",
                //        external_link_list_url : "../js/tinymce/examples/lists/link_list.js",
                //        external_image_list_url : "../js/tinymce/examples/lists/image_list.js",
                //        media_external_list_url : "../js/tinymce/examples/lists/media_list.js",

                // Replace values for the template plugin
                template_replace_values : {
                    username : "Some User",
                    staffid : "991234"
                },



                // new lines only one line
                force_br_newlines : true,
                force_p_newlines : false,
                forced_root_block : ''   // Needed for 3.x
                ,auto_focus: textareaIdToInit,

                apply_source_formatting : true,
                remove_linebreaks: false
            }
        );

    }
    catch (err)
    {
        handleErr("in error in mceinit", err);
    }
}



// from http://stackoverflow.com/questions/1799123/how-to-automatic-resize-tinymce
//$('.tinymce').tinymce({
//    theme : 'advanced',
//    plugins : 'autoresize',
//    width: '100%',
//    height: 400,
//    autoresize_min_height: 400,
//    autoresize_max_height: 800,
//});








/**
 *
 * @param txtareaid // txtUpper
 * @param imagelocation // images/findicons.com/gnome_edit_delete.png
 */
function UtilTinyMCE_insertimage(txtareaid, imagelocation)
{
    var ed = tinyMCE.get(txtareaid);                // get editor instance
    var range = ed.selection.getRng();                  // get range
    var newNode = ed.getDoc().createElement ( "img" );  // create img node
    newNode.src=imagelocation;                           // add src attribute
    range.insertNode(newNode);
}

// utility function to output editor and record editor contents
function UtilTinyMCE_debugOutputMCEContents()
{
    alert ("in testme1:" + tinyMCE.get('txtUpper').getContent({format : 'text'}));
    alert ("in testme2:" + tinyMCE.get('txtUpper').getContent({format : 'html'}));
    alert ("in testme3:" + tinyMCE.activeEditor.getContent({format : 'text'}).trim());
    alert ("in testme4:" + tinyMCE.activeEditor.getContent({format : 'html'}).trim());
}
