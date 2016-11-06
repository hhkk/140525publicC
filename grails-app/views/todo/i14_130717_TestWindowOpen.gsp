<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="language" content="en" />

%{--<script type="text/javascript">--}%
%{--alert ("in i11.gsp head")--}%
%{--</script>--}%


<title>UsToDo</title>

<!-- DEMO styles - specific to this page -->
<link rel="stylesheet" type="text/css" href="css/complex.css" />    %{--from http://layout.jquery-dev.net/demos/css/complex.css--}%
<!--[if lte IE 7]>
		<style type="text/css"> body { font-size: 85%; } </style>
	<![endif]-->

%{--<g:javascript src="jquery/jquery-1.7.1.min.js" />--}%
%{--<link rel="shortcut icon" href="images/orangeicon.png" type="image/x-icon">--}%
%{--<link rel="shortcut icon" href="images/greencheck.png" type="image/x-icon">--}%
%{--C:\Users\henryms\.grails\2.2.3\projects\ustodo\tomcat\work\Tomcat\localhost\ustodo\grails-resources\images\yellowflower.jpg--}%
%{--C:\Users\henryms\sw\130707public\web-app\images--}%

<link rel="shortcut icon" href="images/yellowflower.jpg" type="image/x-icon">
%{--<link rel="shortcut icon" href="../images/grails_logo.png')}" type="image/x-icon">--}%
<g:javascript src="tinymce/jscripts/tiny_mce/tiny_mce.js" />


<script type="text/javascript" src="js/hk.jquery.min.js"></script>   %{--http://code.jquery.com/jquery.min.js--}%
<script type="text/javascript" src="js/jquery-ui-1.10.1/ui/jquery-ui.js"></script>   %{-- rather than http://layout.jquery-dev.net/lib/js/jquery-ui-latest.js from  http://jqueryui.com/download/ --}%
<script type="text/javascript" src="js/hk.jquery.layout-latest.min.js"></script>  %{--   curl http://layout.jquery-dev.net/lib/js/jquery.layout-latest.min.js  > hk.jquery.layout-latest.min.js --}%
<script type="text/javascript" src="js/hk.complex.js"></script>  %{--curl http://layout.jquery-dev.net/demos/js/complex.js > hk.complex.js--}%
<script type="text/javascript" src="js/hk.debug.js"></script> %{--curl http://layout.jquery-dev.net/lib/js/debug.js > hk.debug.js--}%
<script type="text/javascript" src="js/ng/angular.min.js"></script> %{--curl http://layout.jquery-dev.net/lib/js/debug.js > hk.debug.js--}%


%{--<script type="text/javascript" src="js/hk.jquery.min.js"></script>   --}%%{--http://code.jquery.com/jquery.min.js--}%
%{--<script type="text/javascript" src="js/jquery-ui-1.10.1/ui/jquery-ui.js"></script>   --}%%{-- rather than http://layout.jquery-dev.net/lib/js/jquery-ui-latest.js from  http://jqueryui.com/download/ --}%
%{--<script type="text/javascript" src="js/hk.jquery.layout-latest.min.js"></script>  --}%%{--   curl http://layout.jquery-dev.net/lib/js/jquery.layout-latest.min.js  > hk.jquery.layout-latest.min.js --}%
%{--<script type="text/javascript" src="js/tinymce_3.5.8_dev/jscripts/tiny_mce/tiny_mce_dev.js"></script>--}%
%{--<script type="text/javascript" src="js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>--}%
<script type="text/javascript" src="js/utiljs/HkSetUtilJSdataStructSet.js"></script>
<script type="text/javascript" src="js/utiljs/UtilTinyMCE.js"></script>  %{--   curl http://layout.jquery-dev.net/lib/js/jquery.layout-latest.min.js  > hk.jquery.layout-latest.min.js --}%
<script type="text/javascript" src="js/utiljs/UtilTinyMCECursorSelect.js"></script>  %{--   curl http://layout.jquery-dev.net/lib/js/jquery.layout-latest.min.js  > hk.jquery.layout-latest.min.js --}%
<script type="text/javascript" src="js/utiljs/handleErr.js"></script>
<script type="text/javascript" src="js/utiljs/UtilClass.js"></script>
<script type="text/javascript" src="js/utiljs/UtilDOM.js"></script>
<script type="text/javascript" src="js/utiljs/UtilString.js"></script>
<script type="text/javascript" src="js/utiljs/UtilClass.js"></script>
<script type="text/javascript" src="js/utiljs/UtilIndex.js"></script>
<script type="text/javascript" src="js/utiljs/UtilJavascript.js"></script>
<script type="text/javascript" src="js/utiljs/UtilString.js"></script>
<script type="text/javascript" src="js/utiljs/UtilClock.js"></script>
<script type="text/javascript" src="js/utiljs/UtilOptionParser.js"></script>
<script type="text/javascript" src="js/PageData.js"></script>

<script type="text/javascript" src="js/ajax/AjaxJsonFetch.js"></script>
<script type="text/javascript" src="js/emit/emit0001.js"></script>
<script type="text/javascript" src="js/emit/emitCheckbox.js"></script>
<script type="text/javascript" src="js/emit/emitButton.js"></script>
<script type="text/javascript" src="js/emit/table/emitJsonTable2.js"></script>
<script type="text/javascript" src="js/emit/EmitCategsGenEvent.js"></script>
<script type="text/javascript" src="js/emit/table/MainTableUtilBehavior.js"></script>
<script type="text/javascript" src="js/emit/IndexSuperclass.js"></script>
<script type="text/javascript" src="js/data/UtilData.js"></script>
<script type="text/javascript" src="js/utiljs/UtilCrudDB.js"></script>
<script type="text/javascript" src="js/utiljs/UtilInformationRetrieval.js"></script>
<script type="text/javascript" src="js/utiljs/UtilLog.js"></script>
<script type="text/javascript" src="js/utiljs/UtilLayout.js"></script>
<script type="text/javascript" src="js/utiljs/UtilKeyboard.js"></script>

%{--what we get back in the map utdoptions: params.options  // reflects back--}%
%{--for (var key in object) {--}%
%{--alert([key, object[key]].join("\n\n"));--}%
%{--}--}%
%{--<g:if true='${utdoptions.message}'>--}%
%{--<g:if env="development">--}%
%{--alert ("development")--}%
%{--<script type="text/javascript" src="js/utiljs/fartscroll.js"></script>--}%
%{--</g:if>--}%
%{--<g:else>--}%
%{--alert ("not development")--}%
%{--</g:else>--}%




%{--<script type="text/javascript" src="js/utiljs/UtilConsole.js"></script>--}%

%{--<g:javascript src="utiljs/onloadhk_fromMaingspss.js" />--}%


%{--<g:javascript src="ajax/AjaxJsonFetch.js" />--}%
%{--<g:javascript src="emit/emit0001.js" />--}%
%{--<g:javascript src="emit/emitCheckbox.js" />--}%
%{--<g:javascript src="emit/emitJsonTable2.js" />--}%
%{--<g:javascript src="emit/IndexSuperclass.js" />--}%
%{--<g:javascript src="jquery/jquery-1.7.1.min.js" />--}%
%{--<g:javascript src="utiljs/jqueryAutocomplete.js" />--}%
%{--<g:javascript src="utiljs/onloadhk_fromMaingspss.js" />--}%
%{--<g:javascript src="utiljs/stacktrace.js" />--}%
%{--<g:javascript src="utiljs/UtilClass.js" />--}%
%{--<g:javascript src="utiljs/UtilDOM.js" />--}%
%{--<g:javascript src="utiljs/UtilString.js" />--}%
%{--<g:javascript src="utiljs/UtilTinyMCE.js" />--}%
%{--<g:javascript src="utiljs/UtilIndex.js" />--}%
%{--<g:javascript src="utiljs/UtilJavascript.js" />--}%






<script type="text/javascript" src="js/hk.complex.js"></script>  %{--curl http://layout.jquery-dev.net/demos/js/complex.js > hk.complex.js--}%
<script type="text/javascript" src="js/hk.debug.js"></script> %{--curl http://layout.jquery-dev.net/lib/js/debug.js > hk.debug.js--}%

<script type="text/javascript">
    /*
     * complex.html
     *
     * This is a demonstration page for the jQuery layout widget
     *
     *	NOTE: For best code readability, view this with a fixed-space font and tabs equal to 4-chars
     */

    var outerLayout, innerLayout;


    function onActionSelect_userPickedASearchFromDropdown() {

        //http://stackoverflow.com/questions/650022/how-do-i-split-a-string-with-multiple-separators-in-javascript
        var s = getElementByIdHK('select_se_command').value;
        //var s2 = s.split(/\)\(/);
        var s2 = s.split('(');
        if (s2.length != 2)
            return;
        s2 = s2[1].split(')');
        //alert ("s2:"+s2[0]);
        var t = tinyMCE.get('txtUpper').getContent({format : 'html'}).trim()


        tinyMCE.activeEditor.setContent(t + " " + s2[0]);
        getElementByIdHK('txtUpper').focus();

        //if (t != "")
        //{
        //   window.open(t,t);
        //}
    }

    if (!document.all)
    {
        // chrome on win or mac
        //alert ('no doc all');
        window.captureEvents(Event.KEYPRESS);
        window.onkeypress = handlerWindowKey;

    }
    else // IE
    {
        //alert ('yes doc all');
        document.onkeypress = handlerWindowKey();
    }

















    var CONST_FIELD_UPPER_CLASS = "fld1upperclass";


    /*
     *#######################
     *     ON PAGE LOAD
     *#######################
     */
    $(document).ready( function()
    {   // not onload
        // create the OUTER LAYOUT
        //alert ("in i11a doc.ready");
        // UtilConsole_initCustomLog();
        //alert ("in i11b doc.ready");

        UtilKeyboard_setDomLeyHandler();

        outerLayout = $("body").layout( layoutSettings_Outer );




        /*******************************
         ***  CUSTOM LAYOUT BUTTONS  ***
         *******************************
         *
         * Add SPANs to the east/west panes for customer "close" and "pin" buttons
         *
         * COULD have hard-coded span, div, button, image, or any element to use as a 'button'...
         * ... but instead am adding SPANs via script - THEN attaching the layout-events to them
         *
         * CSS will size and position the spans, as well as set the background-images
         */

            // BIND events to hard-coded buttons in the NORTH toolbar
        outerLayout.addPinBtn( "#tbarPinWest", "west" );
        outerLayout.addOpenBtn( "#tbarOpenSouth", "south" );
        outerLayout.addCloseBtn( "#tbarCloseSouth", "south" );
        outerLayout.addPinBtn( "#tbarPinEast", "east" );
        outerLayout.addToggleBtn( "#tbarToggleNorth", "north" );

        // save selector strings to vars so we don't have to repeat it
        // must     prefix paneClass with "body > " to target ONLY the outerLayout panes
        var westSelector = "body > .ui-layout-west"; // outer-west pane
        var eastSelector = "body > .ui-layout-east"; // outer-east pane

        // CREATE SPANs for pin-buttons - using a generic class as identifiers
        $("<span></span>").addClass("pin-button").prependTo( westSelector );
        $("<span></span>").addClass("pin-button").prependTo( eastSelector );
        // BIND events to pin-buttons to make them functional
        outerLayout.addPinBtn( westSelector +" .pin-button", "west");
        outerLayout.addPinBtn( eastSelector +" .pin-button", "east" );

        // CREATE SPANs for close-buttons - using unique IDs as identifiers
        $("<span></span>").attr("id", "west-closer" ).prependTo( westSelector );
        $("<span></span>").attr("id", "east-closer").prependTo( eastSelector );
        // BIND layout events to close-buttons to make them functional
        outerLayout.addCloseBtn("#west-closer", "west");
        outerLayout.addCloseBtn("#east-closer", "east");


        /* Create the INNER LAYOUT - nested inside the 'center pane' of the outer layout
         * Inner Layout is create by createInnerLayout() function - on demand
         *
         innerLayout = $("div.pane-center").layout( layoutSettings_Inner );
         *
         */


        // DEMO HELPER: prevent hyperlinks from reloading page when a 'base.href' is set
        $("a").each(function () {
            var path = document.location.href;
            if (path.substr(path.length-1)=="#") path = path.substr(0,path.length-1);
            if (this.href.substr(this.href.length-1) == "#") this.href = path +"#";
        });

        createInnerLayout(); // hbk 130322

        //outerLayout.sizePane('north', '200')  ;
        //alert (document.getElementById('txtUpper').height)
        //alert ("1:" + $("#txtUpper").height())
        //  alert ($("#txtUpper_tools1").height())
        var heightMCETextboxOnly;
        var obj = document.getElementById('txtUpper');
        if(obj.offsetHeight)          {heightMCETextboxOnly=obj.offsetHeight+22;}
        else if(obj.style.pixelHeight){heightMCETextboxOnly=obj.style.pixelHeight;}

        //        var heightMCEToolbar;
        //        var obj = document.getElementById('txtUpper_tools1');
        //        if(obj.offsetHeight)          {heightMCEToolbar=obj.offsetHeight;}
        //        else if(obj.style.pixelHeight){heightMCEToolbar=obj.style.pixelHeight;}


        //alert ("2 divHeight:" + heightMCETextboxOnly)
        //alert ("3 total:" + heightMCETextboxOnly + 100)
        innerLayout.sizePane('north', heightMCETextboxOnly + 50);  // hbk


        //        var commandText = tinyMCE.get('txtUpper').getContent({format : 'text'}).trim();  // hbk130418
        //        var commandHtml = tinyMCE.get('txtUpper').getContent({format : 'html'}).trim();

        var commandText = getElementByIdHK('txtUpper').value.trim()  // real ampersand
        var commandHTML = getElementByIdHK('txtUpper').innerHTML.trim()   // encoded ampersand


        //alert ("commandHTML:" + commandHTML);
        //var s2 = getElementByIdHK('txtUpper').innerHTML.trim() ori

        //var commandText = tinyMCE.activeEditor.getContent({format : 'text'}).trim();
        //var s2 = tinyMCE.activeEditor.getContent({format : 'text'}).trim();
        //var s2 = getElementByIdHK('txtUpper').innerHTML.trim()
        //alert ("getElementByIdHK('txtUpper'):" + getElementByIdHK('txtUpper'))
        //var s2 = getElementByIdHK('txtUpper').innerHTML
        // alert ("commandText:" + commandText)  // real ampersand
        // alert ("commandHTML:" + commandHTML)  // encoded ampersand

        ajax_FetchJsonTableData('AJAXCALLERID_CALLER_TOP i11 doc ready', commandHTML, commandText, false, getElementByIdHK('idTextFieldUtdoptions').value)  // hbkajax_FetchJsonTableData

        //alert ("in here hk");




        //alert ("in here3");
        //$(function() {
        //  alert ("in here4");
//        $(document).bind("mouseup", function()
//        {
//            var selectedText = getSelectionHtmlAndText().text;
//            if (selectedText != "")
//            {
//                tinyMCE.getInstanceById('txtUpper').setContent(selectedText)
//                //tinyMCE.getInstanceById('txtUpper').focus()
//
//            }
//            //oooo ("selectedText:" + selectedText);
//            //                alert("in herer");
//            //                //getElementByIdHK('changetest').innerHTML = "mouse:begin";
//            //                if ((typeof x.Selector != "undefined"))
//            //                {
//            //                    //alert ("in mouse getClass(x):"+getClass(x))
//            //                    //var src = x.parameter.source;
//            //                    //var newValue = x.parameter[src];
//            //                    var mytext2 = x.Selector.getSelected().toString();
//            //
//            //                    //alert ("asd:"+mytext2.length)
//            //                    if (mytext2.length > 0)
//            //                    {
//            //                        alert("in heres:"+mytext2.toString());
//            //                        getElementByIdHK('changetest').innerHTML = "mouse:" + mytext2.toString();
//            //                        //if (DEBUGGING)
//            //                        //{
//            //                        //    getElementByIdHK('txtUpper').value = getElementByIdHK('txtUpper').value + " // " + mytext2.toString();
//            //                        //}
//            //                    }
//            //                }
//            //alert("mytext" + getClass(mytext));
//            }
//        );  //        end $(document).bind("mouseup", function()

        //            $(document).bind("keyup", function() {
        //
        //                //getElementByIdHK('changetest').innerHTML = "key:begin";
        //                if ((typeof x.Selector != "undefined"))
        //                {
        //                    var mytext = x.Selector.getSelected().toString();
        //                    //alert ("in key mytext:"+mytext)
        //                    if (mytext.length > 0)
        //                    {
        //                        alert("key:" + mytext);
        //                                getElementByIdHK('changetest').innerHTML = "key:" + mytext;
        //                        //getElementByIdHK('txtUpper').value = getElementByIdHK('txtUpper').value + " // " + mytext;
        //                    }
        //                }
        //                //alert("mytext" + getClass(mytext));
        //            });

        //        $(document).bind("mousemove", function() {
        //
        //            var mytext = x.Selector.getSelected();
        //            getElementByIdHK('changetest').innerHTML = 'mousemove';
        //            //alert("mytext" + getClass(mytext));
        //        });
        //});

//        alert ("hbkiframe2a--:"+ $("hbkiframe2").toString());
//        //  from http://stackoverflow.com/questions/12094300/jquery-resize-iframe-stops-when-contracting-quickly
//        $("hbkiframe2").resizable({
//            //containment: 'parent',
//            minWidth: 400,
//            minHeight: 200,
//            start: function () {
//                alert ("hi bob");
//                $(".widget_box").each(function (index, element) {
//                    var d = $('<div class="iframeCover" style="zindex:99;position:absolute;width:100%;top:0px;left:0px;height:' + $(element).height() + 'px"></div>');
//                    $(element).append(d);
//                });
//            },
//            stop: function () {
//                alert ("hi bob2");
//                $('.iframeCover').remove();
//            }
//        });
//        alert ("hbkiframe2b:"+ $("hbkiframe2"));
        ///alert ('hi hk done docinit1');


        //alert ('hi hk done docinit2c');

        initmcehbk("i11...desc.fld1upperclass",    // desc  hbk130616 textUpper
                CONST_FIELD_UPPER_CLASS,
                "newdocument,undo,redo,insertdate," +
                    //"|,forecolor,backcolor," +
                    //"|,bullist,numlist," +
                        "|,forecolor,bold,italic,underline,strikethrough,fontselect,fontsizeselect,|,tablecontrols",
                //buttons
                //                "newdocument,undo,redo,insertdate" +
                //                        "|,forecolor,backcolor," +
                //                        "|,bullist,numlist," +
                //                        "|,bold,italic,underline,strikethrough,fontselect,fontsizeselect,|,tablecontrols",
                "simple", // theme
                '20px', // 5    hbk
                "2",  // ht   hbk
                'txtUpper');

        oooo ('hi hk done docinit2d completed mce init');


        // KEEP THIS AT THE END ...IT FAILS
        // KEEP THIS AT THE END ...IT FAILS
        // KEEP THIS AT THE END ...IT FAILS
        // KEEP THIS AT THE END ...IT FAILS
        // Fart every 400 pixels scrolled in the document
        $(document).fartscroll();
        // Fart every 800 pixels scrolled in the document
        $(document).fartscroll(800);
        // Fart every 100 pixels scrolled in the body (probably a bit much)
        $("body").fartscroll(100);
        // Now I'm just adding more examples to make the page longer
        $("body").fartscroll(50);
        // SO MANY FARTS
        $("body").fartscroll(5);
        // I should register fart.io for this
        $("div").fartscroll(500);
        // I should register fart.io for this
        $("body").fartscroll(400);
        // Dammit, fart.io is taken
        $(window).fartscroll(600);
        // Alright, that's probably enough examples
        $("body").fartscroll(400);
        // KEEP THIS AT THE END ...IT FAILS
        // KEEP THIS AT THE END ...IT FAILS
        // KEEP THIS AT THE END ...IT FAILS
        // KEEP THIS AT THE END ...IT FAILS

        oooo ('hi hk done docinit');

    });  // end doc ready init hbk


    function resizeIframe(obj) {
        alert ("in here")
        obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
    }



    //    ajax_FetchJsonTableData("CALLER_TOP", '*', false)
    function testme2()  // hbk130623
    {

        alert ("ht1 is:" + $('#id-ui-layout-north-inner').attr('style').height);
        oooo ("ht2 is:" + $('#id-ui-layout-north-inner').height());


        return
        //var t = 'height: ' + $('testme2ta').val() + 'px;';
        var t = $('#testme2ta').val() + 'px';
        //alert ("setting to:" + t)
        $(".mceIframeContainer").attr ('style', 'height: '+t);
        //var t = '300px'
        //alert ("setting to:" + t)
        $('#txtUpper_ifr').css('height', t);
        //$('#txtUpper_ifr').css('height', '100px');

        //window.open("http://www.ibm.com");

        //window.history.pushState("object or string", "Title", "/ustodo/todo?q=joey" ); // -> http://localhost:8084/ustodo/todo?q=joey
        //window.history.pushState("object or string", "Title", "/ustodo/todo?q=" + getElementByIdHK('txtUpper').value);

        // see     function ajax_refreshCmdHist() { in index.gsp

        //see UtilMongo_updateRecordTimestamp(id, newtext, username)  implement tough function?

        //window.raise("error happened")


    }

    function testme3()
    {

        var t = $('#testme2ta').val() + 'px';
        //var t = '300px'
        //alert ("setting to:" + t)
        $('#txtUpper_ifr').css('height', t);

    }
    oooo ("reached end of main script section")

</script>

</head>

<body>

// i14_130717_TestWindowOpen

<form>
    <input type="button" value="Open Window" onclick="window.open('http://www.ibm.com')">
</form>


</body>
</html>
