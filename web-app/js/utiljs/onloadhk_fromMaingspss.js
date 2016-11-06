var grailsMapElement_TxtUpper = null;


function setBackgroundImage(newuiTF)
{
    //alert("in setBackgroundImage()");
    //alert ("in setBackgroundImage newuiTF:"+newuiTF);

    // config: alternate images for background
    var rnd = Math.floor((Math.random()*18)+1);
    //var src="../images/backs/white" + rnd + ".jpg"
    //alert("in setBackgroundImage() src:"+src);
    var s = null
    if (!newuiTF)
        s = "images/backs/white" + rnd + ".jpg"
    else
        s = "../images/backs/white" + rnd + ".jpg"

    if (newuiTF)
    {
        getElementByIdHK("artselectintnorth").src=s
        getElementByIdHK("artselectintsouth").src=s
        getElementByIdHK("artselectinteast").src=s
        getElementByIdHK("artselectintcenter").src=s
        getElementByIdHK("artselectintwest").src=s
    }
    else //old style
    {
        getElementByIdHK("artselectint3").src=s
    }


    //$('backimagehk').src=srchk;
    //alert ("1xx")
    //alert ($ == jQuery)
    //alert ("2xx")

}

function onloadhk_fromMaingspss(newuiTF) { // from body onload main.gsp

    try {
       //alert ("in onloadhk_fromMaingsp");
       //alert ("in onloadhk_fromMaingsp hi hk newuiTF:"+newuiTF);
        //$("#idhk").css("background-color","yellow"); // works

        //testDynamicContent1()
        //var hkroot = "<g:resource dir='' file='' />" + "/";
        //alert ("in onload:"+hkroot); // this comes after document.load

        probablyInLogin = false;
        t = document.getElementById("txtUpper")
        grailsMapElement_TxtUpper = null
        if (t != null)
        {
            grailsMapElement_TxtUpper = t.value;
        }
        else
        {
            probablyInLogin = true
        }

        t2 = document.getElementById("spanidRedirect")
        if (t2 != null)
        {
            redirect = t2.innerHTML.trim() != "";

        }


        //alert ("in onloadhk redirect:" + redirect);

        if (!probablyInLogin)
        {
            alert ("calling ajaxFetchJsonTableData grailsMapElement_TxtUpper:" + grailsMapElement_TxtUpper)
            ajax_FetchJsonTableData("onloadhk_fromMaingspss", grailsMapElement_TxtUpper);
        }

        $('#fld2Lower').focus(function() {
            //alert("in herefocus")
            $(this).val('');
        });


        //
        //        textarea.onfocus = function() {
        //            alert("in here hk")
        //            moveCaretToEnd(textarea);
        //
        //            // Work around Chrome's little problem
        //            window.setTimeout(function() {
        //                moveCaretToEnd(textarea);
        //            }, 1);
        //        };


        //        alert ("getClass field [" + getClass(navigator) + "]");
        //        alert ("postgetclass in onload");
        //        txthk = "<p>Browser CodeName: " + navigator.appCodeName + "</p>";
        //        txthk+= "<p>Browser Name: " + navigator.appName + "</p>";
        //        txthk+= "<p>Browser Version: " + navigator.appVersion + "</p>";
        //        txthk+= "<p>Cookies Enabled: " + navigator.cookieEnabled + "</p>";
        //        txthk+= "<p>Platform: " + navigator.platform + "</p>";
        //        txthk+= "<p>User-agent header: " + navigator.userAgent + "</p>";
        //        //getElementByIdHK("txthk").innerHTML=txthk;        // alert("in onloadHK1.");
        //        //useragent = navigator.userAgent;
        //alert (getClass(x));
        //getElementByIdHK("browserinfo").innerHTML='example from within';        // alert("in onloadHK1.");

        // document.bgColor='rgb(255,255,255)'
        var s = "asd"
        document.bgColor = 'E0E0E0'


        //alert ("in onload")
        //document.hkformname.fld2Lower.focus(); // was works for years: document.hkformname.q.focus();
        //tinymce.execCommand('mceFocus',false,'fld2Lower');
        //alert("in onloadHK1.5");
        // this line below causes a textbox-entered string to be displayed as a linkable browser address string
        // works chrome at least: window.history.pushState("object or string", "Title", "/ustodo?q="+getElementByIdHK('txtUpper').value);
        // try this for firefox
        // have also seen it without the "window."
        //window.history.pushState("object or string", "Title", "/ustodo/todo?q=" + getElementByIdHK('txtUpper').value);
        if (!probablyInLogin)
        {
            setTitle(getElementByIdHK('txtUpper').value);
            setBackgroundImage(newuiTF);
            emitCheckbox('testDynamicContent1');
        }

        //alert ("done onloadhk_fromMaingspss")


    }
    catch (err) {
        handleErr(err);
    }



}

