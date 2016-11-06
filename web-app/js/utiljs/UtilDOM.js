/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/3/13
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */

function getElementByIdHK(id)
{
    var element = document.getElementById(id)
    //alert("in getElementByIdHK id:"+id)
    if (element == null)
    {
        handleErr2('getElementByIdHK', "error, no such element id [" + id + "]")
    }
    return element;
}

//9
function setTitle(s) {
    //alert("in setTitle:" + s);
    document.title = ("[" + s + "]");
}

function funcRef() {
    alert ("hi mom")

}

// must mean seturl in title
function setURL(callerID, searchString, optionsColonStr)
{
     //alert ("in seturl searchString [")
    // alert ("in seturl searchString [" + searchString + "] optionsColonStr [" + optionsColonStr + "] callerID:" + callerID)
    //alert("fn.setURL from searchString ["+searchString+"] optionsColonStr ["+optionsColonStr+"]");

    var asurl = null;
//    $.getScript("js/utiljs/UtilOptionParser.js", function(){
        //alert("fn.setURL from searchString ["+searchString+"] optionsColonStr ["+optionsColonStr+"]");

        //asurl = UtilOptionParser2_genOptionsURLStringFromColonStr(optionsColonStr)
    console.log ("fix this hk todo")
        // here you can use anything you defined in the loaded script
 //   });


    searchString =UtilString_replaceplusw2bForUrlEncode(searchString)
    //alert ("string to set as url [" + searchString + "]" )
    //searchString = UtilString_replaceAll(searchString, "+", "yy%2B")
    // this is where we set (not) window.location
    //alert ("push window state");
    //alert("document.URL:"+document.URL);

    var controllerNameForSearchURLConstruction = ""


    var root = urlMatchController(getDocumentURL());
    //alert ("root:" + root);

    var sss = "/" + root + "?q=" + searchString;
    //alert ("sss:" + sss);
    window.history.pushState("object or string", "Title", sss); // -> http://localhost:8084/ustodo/todo?q=searchString
    //see also replaceState window.history.replaceState("object or string", "Title", "/ustodo/todo?q=" + searchString ); // -> http://localhost:8084/ustodo/todo?q=searchString
    //window.location('hi mom')

    //window.onpopstate = funcRef

}

/**
 * get the active URL
 * @returns {string}
 */
function getDocumentURL()
{
    return document.URL
}



function handlerWindowKey(e) // was handlerA
{
    console.log ("in handlerWindowKey")
    // var key1="32"; // space
    var key1 = "19"; // ctrl-s
    var keyctrlt = "20"; // ctrl-t
    var keyctrlu = "21"; // ctrl-u
    var keyShiftF3 = "35"; // shift-f3
    var keyShiftF4 = "36"; // shift-f4
    var x = '';

    //if (DEBUGGING)
    //alert ('1 in handlerA'); // KeyboardEvent
    //alert ('2 in handler getClass(e):' + getClass(e)); // KeyboardEvent
    //alert ('2.5 in handler getClass(document):' + getClass(document)); // class:Object
    //alert ('3 in handler getClass(document.all):' + getClass(document.all));

    //var evnt = window.event;
    var evnt = e || window.event
    try
    {
        if (document.all) {
            //if (DEBUGGING)
            //alert ('in document.all');
            x = evnt.keyCode;
        }
        else
        {
            //if (DEBUGGING)
            //alert ('not in document.all');
            x = evnt.charCode;
        }
    }
    catch(err)
    {
        handleErr3("from in handlerWindowKey", err, true);
    }

    //alert ("in key handlerA x ["+ x +"]");

    if (x == key1) // ctrl-s?
    {
        console.log("yes in ctrl-s");

        //document.hkformname.q.focus();

        //getElementByIdHK('txtUpper').focus();

        window.parent.tinyMCE.get('txtUpper').focus()
        window.parent.tinyMCE.get('txtUpper').setContent("")



            //            //        if (document.activeElement.id == 'txtUpper')
            //            //        {
            //            //            //getElementByIdHK('txtUpper').value = '';
            //            //            console.log ("window.getSelection().length" + window.getSelection().length);
            //            //            if (window.getSelection().length == 0)
            //            //            {
            //            //                console.log ("case1.1")
            //            //                //getElementByIdHK('txtUpper').value = '';
            //            //                //document.hkformname.q.focus();
            //            //                window.parent.tinyMCE.get('txtUpper').focus()
            //            //
            //            //
            //            //            }
            //            //            else
            //            //            {
            //            //                //alert ("case1.2")
            //            //                try {
            //            //                    var sTrimmed = trim(getElementByIdHK('txtUpper').value).toString();
            //            //                    //                            if (sTrimmed.endsWith('/'))
            //            //                    //                            {
            //            //                    //                                getElementByIdHK('txtUpper').value = '';
            //            //                    //                                document.hkformname.q.focus();
            //            //                    //                            }
            //            //                    //                            else
            //            //                    //                            {
            //            //                    getElementByIdHK('txtUpper').value = sTrimmed + " / "
            //            //                    //setCaretPosition('txtUpper', (getElementByIdHK('txtUpper').value.length) - 2);
            //            //                    //document.hkformname.q.focus();
            //            //                    //                            }
            //            //                    getElementByIdHK('txtUpper').focus();
            //            //                    getElementByIdHK('txtUpper').select();
            //            //                    //alert ("setcaret");
            //            //                }
            //            //                catch(err)
            //            //                {
            //            //                    handleErr(err);
            //            //                }
            //            //
            //            //            }
            //        }
            //        else
            //        {
            //
            //            console.log("case2")
            //
            ////                var t = tinyMCE.activeEditor.getBody()
            ////                t.focus();
            //            tSAVORIGINALACTIVETINYMCE.focus();
            ////                var te = tinymce.get('txtUpper');
            ////                setCaretTo(te, 10)
            //            //alert("t:"+t)
            //            //                var i = 0;
            //            //                for(var key in $("#txtUpper"))
            //            //                {
            //            //                    //if ( obj.hasOwnProperty(key) && typeof obj[key] !== 'function' ) {
            //            //                        //properties.push(key);
            //            //                    i =  i +1;
            //            //                         console.log ("found prop[" + key + "] i [" + i + "]");
            //            //                    //}
            //            //                }
            //            //                console.log (getClass("$(#txtUpper)", $("#txtUpper")))
            //            //alert ("tiny version2:" + tinyMCE.majorVersion + '.' + tinymce.minorVersion);
            //
            //            //$("#txtUpper").tinymce().focus();
            //
            ////                tinyMCE.activeEditor.setContent('aa'+ tinyMCE.activeEditor.getContent())
            ////                //tinyMCE.activeEditor.focus();
            ////                tinyMCE.execCommand('mceFocus', false, 'txtUpper');
            ////                var te = tinymce.get('txtUpper');
            ////                setCaretTo(te, 3)
            ////
            ////
            //            //alert(getClass("document.hkformname.q:", document.hkformname.q))
            //            //var elem = getElementByIdHK("txtUpper").focus();
            //            //document.hkformname.q.focus();
            //
            //            //tinyMCE.execCommand('mceFocus', 'txtUpper', false);
            //            //tinyMCE.execInstanceCommand("mce_editor_0", "mceFocus");
            //            //tinyMCE.get('txtUpper').focus();
            //
            //
            //        }

        //alert ("selection:" + window.getSelection())


        //document.q.focus();

        //  	  location.href='http://www.expertsrt.com';
    }
    else if (x == keyctrlu) // ctrl-u
    {
        alert("testme");
    }
    else if (x == keyShiftF3)
    {
        var numsel = getSetNumSel2();
        if (numsel > 0)
        {
            deleteSelected(getPageData());
            return false;
        }
        else
        {
            alert('no records selected')
            return false;
        }
        //alert("testme");
    }
    else if (x == keyShiftF4)
    {
        var numsel = getSetNumSel2();
        if (numsel > 0)
        {
            bulkTouchSelected();
            return false;
        }
        else
        {
            alert('no records selected')
            return false;
        }
        //alert("testme");
    }

}

/**
 * get selected text
 * @returns {{}}
 */
function getSelectionHtmlAndText() {
    //alert ("in getSelectionHtmlAndText");
    var rtn = {};
    var html = "";
    var ttext = "";
    if (typeof window.getSelection != "undefined") {
        var sel = window.getSelection();
        if (sel.rangeCount) {
            var container = document.createElement("div");
            for (var i = 0, len = sel.rangeCount; i < len; ++i) {
                container.appendChild(sel.getRangeAt(i).cloneContents());
            }
            html = container.innerHTML;
            ttext = container.innerText;
        }
    } else if (typeof document.selection != "undefined") {
        if (document.selection.type == "Text") {
            html = document.selection.createRange().htmlText;
            ttext = document.selection.createRange().htmlText;
        }
    }
    //alert("in getSelectionHtmlAndText:" + html);
    //alert("in getSelectionText:" + ttext);
    rtn.html = html;
    rtn.text = ttext;
    return rtn;
}

