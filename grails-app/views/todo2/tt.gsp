

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="todo">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="language" content="en" />

<title>UsToDo</title>

<!-- DEMO styles - specific to this page -->
<link rel="stylesheet" type="text/css" href="css/complex.css" />    %{--from http://layout.jquery-dev.net/demos/css/complex.css--}%
%{--<link rel="stylesheet" href="css/bootstrap.min.css">--}%

<!--[if lte IE 7]>
		<style type="text/css"> body { font-size: 85%; } </style>
	<![endif]-->

<link rel="shortcut icon" href="images/yellowflower.jpg" type="image/x-icon">
%{--from http://jquery.com/download/--}%
<g:javascript src="jquery/jquery-2.1.0.min.js" />
%{--<script src="js/jquery/jquery-2.1.0.min.js"></script>--}%
%{--/<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>--}%



%{--<g:javascript src="hk.jquery.min.js" />--}%
<g:javascript src="jquery-ui-1.10.1/ui/jquery-ui.js" />


%{--<g:javascript src="hk.jquery.layout-latest.min.js" />--}%
%{--from http://layout.jquery-dev.net/downloads.cfm--}%
%{--<g:javascript src="jquery/jquery.layout.min-1.2.0.js" />--}%
%{--<g:javascript src="jquery/jquery.layout-1.2.0.js" />--}%
<g:javascript src="jquery/jquery.layout-latest.js" />

<g:javascript src="hk.complex.js" />
<g:javascript src="hk.debug.js" />
%{--<g:javascript src="ng/angular.min.js" />--}%

%{--<link rel="shortcut icon" href="../images/grails_logo.png')}" type="image/x-icon">--}%
<g:javascript src="tinymce/jscripts/tiny_mce/tiny_mce.js" />



<g:javascript src="utiljs/HkSetUtilJSdataStructSet.js"/>
<g:javascript src="utiljs/UtilTinyMCE.js"/>
<g:javascript src="utiljs/UtilTinyMCECursorSelect.js"/>
<g:javascript src="utiljs/handleErr.js"/>
<g:javascript src="utiljs/UtilClass.js"/>
<g:javascript src="utiljs/UtilDOM.js"/>
<g:javascript src="utiljs/UtilString.js"/>
<g:javascript src="utiljs/UtilClass.js"/>
<g:javascript src="utiljs/UtilIndex.js"/>
<g:javascript src="utiljs/UtilJavascript.js"/>
<g:javascript src="utiljs/UtilString.js"/>
<g:javascript src="utiljs/UtilClock.js"/>
<g:javascript src="utiljs/UtilOptionParser.js"/>
<g:javascript src="PageData.js"/>

<g:javascript src="ajax/AjaxJsonFetch.js"/>
<g:javascript src="emit/emit0001.js"/>
<g:javascript src="emit/emitCheckbox.js"/>
<g:javascript src="emit/emitButton.js"/>
<g:javascript src="emit/table/emitJsonTable2.js"/>
<g:javascript src="emit/EmitCategsGenEvent.js"/>
<g:javascript src="emit/table/MainTableUtilBehavior.js"/>
<g:javascript src="emit/IndexSuperclass.js"/>
<g:javascript src="data/UtilData.js"/>
<g:javascript src="utiljs/UtilCrudDB.js"/>
<g:javascript src="utiljs/UtilInformationRetrieval.js"/>
<g:javascript src="utiljs/UtilLog.js"/>
<g:javascript src="utiljs/UtilLayout.js"/>
<g:javascript src="utiljs/UtilKeyboard.js"/>
<g:javascript src="utiljs/UtilFadesAndPopups.js"/>



<g:javascript src="hk.complex.js"/>
<g:javascript src="hk.debug.js"/>
<g:javascript src="utiljs/UtilCache.js"/>
<g:javascript src="utiljs/DartInterface.js"/>

<g:javascript src="utiljs/UtilClass.js" />

<g:javascript src="nghbk/bootstrap.min.js"/>
<g:javascript src="nghbk/angular.min.js"/>
<g:javascript src="nghbk/angular-local-storage.min.js"/>
<g:javascript src="nghbk/sortable.js"/>
<g:javascript src="nghbk/ngapphbk.js"/>




%{-- "F:\Users\henryms\sw\ustodo\130707publicF\web-app\js\ng\angular.min.js"--}%
%{--<g:javascript src="ng/angular.min.js"/>--}%
%{--<script src="ng/angular.min.js"/></script>--}%


<script type="text/javascript">
    try
    {

        function testme(s)
        {
//            alert ('starttestme');

            fnTopLineToggle('testme')
//
//            var c=document.getElementById("idCanvasPlanets");
//            var ctx=c.getContext("2d");
//            ctx.font="20px Georgia";
//            ctx.fillText("Hello World2!",10,50);
//            alert ('endtestme');
        }



        function eventhandlerOnmouseverResetTxtUpperTo_PageData_currentSearch()
        {
            if (!areWeInModeEdit()) // ie if no row clickedf
                tinyMCE.getInstanceById('txtUpper').setContent(pageData_currentSearchString);
        }

        function eventhandlerOnclickTestButton4()
        {
            alert ("in eventhandlerOnclickTestButton4");
        }
        function onActionSelect_userPickedASearchFromDropdown() {
            //alert ("in onActionSelect_userPickedASearchFromDropdown");
            //http://stackoverflow.com/questions/650022/how-do-i-split-a-string-with-multiple-separators-in-javascript
            var s = getElementByIdHK('select_se_command').value;
            //var s2 = s.split(/\)\(/);
            var s2 = s.split('(');
            if (s2.length != 2)
                return;
            s2 = s2[1].split(')');
            //alert ("s2:"+s2[0]);
            // get and set upper field
            var t = tinyMCE.get('txtUpper').getContent({format : 'html'}).trim()
            tinyMCE.activeEditor.setContent(t + " " + s2[0]);
            getElementByIdHK('txtUpper').focus();

        }

        function onActionButtonClick_userInvokedSearch(s)
        {
            //s           ss
            // see also in utiltinymce key handler
            ajax_FetchJsonTableData("AJAXCALLERID_CALLER_MCEHANDLER", s, s, false, getElementByIdHK('idTextFieldUtdoptions').value)
            setURL(1, s, getElementByIdHK('idTextFieldUtdoptions').value)
            tinyMCE.getInstanceById('txtUpper').setContent(s)
        }


        function onActionButtonClick_userClickedClearSearch()
        {
            //alert ("testme button") // testme2
            var info = {}
            info["screen.width"] = screen.width;
            info["screen.height"] = screen.height;
            info["$(window).width()"]=$(window).width();
            info["$(window).height()"]=$(window).height();
            info["$(document).width()"]=$(document).width();
            info["$(document).height()"]=$(document).height();
            //alert ("hi hk info:" + info);

            $("body").layout({applyDemoStyles:false});

            //getElementByIdHK('spanid_categs').innerHTML = getElementByIdHK('divId_ui-layout-center').innerHTML;
            //getElementByIdHK('ui-layout-west-inner-hbk').style.overflow='hidden';
            getElementByIdHK('elementId_Table_TagWidgets').style.overflow='scroll';
            getElementByIdHK('codeloc1').style.overflow='scroll';
            getElementByIdHK('elementId_TableCateg').style.overflow='scroll';
            getElementByIdHK('elementId_TableCateg_tbody').style.overflow='scroll';
            //alert ("testme button done")


            tinyMCE.getInstanceById('txtUpper').setContent('');
            setURL(2, '...', getElementByIdHK('idTextFieldUtdoptions').value)
            // tinyMCE.getInstanceById('txtUpper').focus()
        }


        try
        {
            if (!document.all)
            {
                // chrome on win or mac
                //alert ('no doc all');
                //if (window == null)
                //   alert ("window is null")
                //else
                //   alert ("window is not null")


                //alert ("ffd:" + getClass("window",window))

                window.captureEvents(Event.KEYPRESS);
                //alert ("got 1")
                window.onkeypress = handlerWindowKey;
                //alert ("got 2")

            }
            else // IE
            {
                ///alert ('yes doc all');
                document.onkeypress = handlerWindowKey();
                //alert ('yes doc all 2');
            }

        }
        catch (err) {
            oooo ("MOBILE ONLY? error in i11ComplexUsToDo.gsp keyboard setup")
            //handleErr2("genHTMLtableData", err);
        }

















        var CONST_FIELD_UPPER_CLASS = "fld1upperclass";


        /*
         *#######################
         *     ON PAGE LOAD
         *#######################
         */
        $(document).ready( function() // not onload
        {
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


            // DEMO HELPER: prevent
            //
            // hyperlinks from reloading page when a 'base.href' is set
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

            innerLayout.sizePane('north', heightMCETextboxOnly + 50);  // hbk

            //        var commandText = tinyMCE.get('txtUpper').getContent({format : 'text'}).trim();  // hbk130418
            //        var commandHtml = tinyMCE.get('txtUpper').getContent({format : 'html'}).trim();
            var commandText = getElementByIdHK('txtUpper').value.trim()  // real ampersand
            var commandHTML = getElementByIdHK('txtUpper').innerHTML.trim()   // encoded ampersand

            ajax_FetchJsonTableData('AJAXCALLERID_CALLER_TOP i11 doc ready', commandHTML, commandText, false, getElementByIdHK('idTextFieldUtdoptions').value)  // hbkajax_FetchJsonTableData

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


            var strHtmlLinkRunsJavascript = "<a href=\"javascript:void(0)\" onclick=\"myJsFunc();\">Run JavaScript Code</a>";
            var strHtmlButton = "<INPUT type=\"button\" value=\"Reset\" title=\"Remove filters and start fresh with a '*' search.\" src=\"xxxxxxxxx.png\" height=\"19px\" width=\"19px\" " +
                    " name=\"resetbutton\" class=\"resetbutton\" id=\"idresetbutton\" " +
                    " onclick=\"resetWindow();getElementByIdHK('id_status_log').innerHTML=''\">&nbsp;&nbsp;";

            getElementByIdHK('spanidBelowSettings').innerHTML = strHtmlLinkRunsJavascript + strHtmlButton;

            // write text in canvas

            //alert('hi hk done docinit2d completed mce init');

            //alert ("hi there1");

//            $("#hkhkhkhk").mouseenter(function(){
//                alert ("ho hk01");
//            })
//
//            $("#bkbkbkbk").mouseenter(function(){
//                alert ("hi bkbkbkbk");
//            })
//
//            //$("div.overhk").innerhtml="sdfsdfsfsdf";
//            $(".hkhkhkhk5").mouseenter(function(){
//                alert ("hi hkhkhkhk5");
//            })

//            $('body').on('mouseenter', 'input.categcb', function()
//            {
//                alert("in mouseenterx");
//                //eventHandlerMouseoverCateg(-2);
//            });
            //alert("defined mouseenter");


            // KEEP THIS AT THE END ...IT FAILS
            // KEEP THIS AT THE END ...IT FAILS
            // KEEP THIS AT THE END ...IT FAILS
            // KEEP THIS AT THE END ...IT FAILS
            // Fart every 400 pixels scrolled in the document
//            $(document).fartscroll();
//            // Fart every 800 pixels scrolled in the document
//            $(document).fartscroll(800);
//            // Fart every 100 pixels scrolled in the body (probably a bit much)
//            $("body").fartscroll(100);
//            // Now I'm just adding more examples to make the page longer
//            $("body").fartscroll(50);
//            // SO MANY FARTS
//            $("body").fartscroll(5);
//            // I should register fart.io for this
//            $("div").fartscroll(500);
//            // I should register fart.io for this
//            $("body").fartscroll(400);
//            // Dammit, fart.io is taken
//            $(window).fartscroll(600);
//            // Alright, that's probably enough examples
//            $("body").fartscroll(400);
            // KEEP THIS AT THE END ...IT FAILS
            // KEEP THIS AT THE END ...IT FAILS
            // KEEP THIS AT THE END ...IT FAILS
            // KEEP THIS AT THE END ...IT FAILS

            // put nothing below here !!!!!!!!!!!!!!!!!!!!!!!!!!


            //$("body").onunload="alert ('bye!')"
//            $( window ).onbeforeunload(function() {
//                alert( "Handler for .unload() called." );
//            });
// no work           window.onbeforeunload = function() {
//                alert( "Handler for .unload() called.");
//            };
//  works!!          $("body").on('mouseenter', 'input.categcb', function()
//            {
//                alert("in mouseenterxxxx");
//                //eventHandlerMouseoverCateg(-2);
//            });
            oooo ('hi hk done docinit');

        });  // end doc ready init hbk


        function resizeIframe(obj) {
            alert ("in here")
            obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
        }


        //    ajax_FetchJsonTableData("CALLER_TOP", '*', false)
        function testme2hkhk()  // hbk130623
        {

            alert ("in testme2hkhk ht1 is:" + $('#id-ui-layout-north-inner').attr('style').height);
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
            alert ("in testme3")
            //layoutSettings_Inner.sizePane( 'west', 200 );
            innerLayout.sizePane('west', '40%')
            alert ("in testme3b")

            var t = $('#testme2ta').val() + 'px';
            //var t = '300px'
            //alert ("setting to:" + t)
            $('#txtUpper_ifr').css('height', t);

        }

        function launchChild()
        {
            // var t = tinyMCE.get('txtUpper').getContent({format : 'html'}).trim()

            var url = '/todo?q=' +tinyMCE.get('txtUpper').getContent({format : 'html'}).trim();

            //var url = UtilString_replaceAll(s, '%s', txtUpperText);

            alert ("opening URL [" + url + "]")
            window.open(url);
        }


        function myJsFunc()
        {
            alert ("in myJsFunc()")
        }

        oooo ("reached end of main script section")
    }
    catch (err) {
        alert ("error in i11ComplexUsToDo.gsp")
        handleErr2("genHTMLtableData", err);
    }
</script>

</head>

<body ng-controller="TodoCtrl" ng-init="init()">
























%{--<script type="text/javascript">--}%
%{--alert ("in i11.gsp")--}%
%{--</script>--}%








%{--<div id="contains_layout_stuff">--}%
%{-- BEGIN LAYOUT --}%

%{-- outer west  1a/4 --}%
<div class="ui-layout-west" >

    <div class="header"><font size=-1>History</font></div>

    <div class="content">
        <span id="spanid_recent">
        </span>
        <div class="subhead" >
            I'm a within header HK subheader
            <ul>
                <li><a href="#" onClick="outerLayout.toggle('north')">Toggle NorthHK</a></li>
                <li><a href="#" onClick="outerLayout.toggle('north')">Toggle NorthHK</a></li>
                <li><a href="#" onClick="outerLayout.toggle('north')">Toggle NorthHK</a></li>
            </ul>
        </div>

        <div class="row">
            <div class="col-xs-3">
                hbk33
                <div class="list-group">
                    <a href="#" ng-repeat="todos in model" class="list-group-item" ng-class="{'active' : currentShow === $index}"
                       ng-click="changeTodo($index)" >
                        <span class="badge">{{todos.list.length}}</span>
                        {{todos.name}} hbk123
                    </a>
                </div>
            </div>
            hbk4
            <div class="col-xs-6">
                <div class="row">
                    <div class="col-xs-7">
                        <div class="input-group">
                            <span class="input-group-btn">
                                hbkbtn-default
                                %{--<button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>--}%
                                <button type="button"><span class="glyphicon glyphicon-search"></span></button>
                                <input type="button" onclick="hbkbutton(event)"/>
                                <button id="todoSubmithbk"  ng-click="hbkbutton()">Addhk</button>
                            </span>
                            <input type="search" class="form-control" placeholder="Search" ng-model="todoSearch">
                        </div>
                    </div>
                    <div class="col-xs-5">
                        <ul class="nav nav-pills todo-filter">
                            <li ng-class="{'active' : show == 'All' }" ng-click="show='All'"><a href="#">All</a></li>
                            <li ng-class="{'active' : show == 'Incomplete' }" ng-click="show='Incomplete'"><a href="#">Incomplete</a></li>
                            <li ng-class="{'active' : show == 'Complete' }" ng-click="show='Complete'"><a href="#">Complete</a></li>
                        </ul>
                    </div>
                </div>
                <div class="row" id="todoAdd">
                    <div class="form-group">
                        <input class="todoField" id="newTodoField" type="text" ng-model="newTodo" placeholder="Add New Todo" ng-enter>
                        <button id="todoSubmit" class="btn btn-default" ng-click="addTodo()">Addhk</button>
                    </div>
                </div>
                hbk44
                <div class="row">
                    <ul class="list-unstyled" ui-sortable="todoSortable" ng-model="todos" ng-repeat="todos in model" ng-show="$index === currentShow">

                        <!--FILTERED LIST -->
                        <li class="todoTask" ng-repeat="todo in todos.list | filter:showFn | filter :todoSearch ">
                            <input class="todoCheckbox" ng-model="todo.isDone" type="checkbox">
                            <!--xx<label class="todoCheckboxlabel" >yy</label>zz-->
                            xx1
                            %{--<edit-in-place value="todo.taskName"></edit-in-place>--}%
                            {{todo.taskName}}
                            xx2

                            <!--hbk deleteTodo is here -->
                            <button type="button" class="close pull-right" aria-hidden="true" ng-click="deleteTodo($index)">&times;</button>
                        </li>
                    </ul>
                </div>
            </div>
        </div>



        <div class="col-xs-3">
            hbk33
            <div class="list-group">
                <a href="#" ng-repeat="todos in model" class="list-group-item" ng-class="{'active' : currentShow === $index}"
                   ng-click="changeTodo($index)" >
                    <span class="badge">{{todos.list.length}}</span>
                    {{todos.name}}
                </a>
            </div>
        </div>


        <ul>
            <li><a href="#" onClick="outerLayout.toggle('north')">Toggle NorthHK</a></li>
            <li><a href="#" onClick="outerLayout.toggle('south')">Toggle South</a></li>
            <li><a href="#" onClick="outerLayout.toggle('west')"> Toggle West</a></li>
            <li><a href="#" onClick="outerLayout.toggle('east')"> Toggle East</a></li>
            <li><a href="#" onClick="outerLayout.hide('north')">Hide North</a></li>
            <li><a href="#" onClick="outerLayout.hide('south')">Hide South</a></li>
            <li><a href="#" onClick="outerLayout.show('south', false)">Unhide South</a></li>
            <li><a href="#" onClick="outerLayout.hide('east')"> Hide East</a></li>
            <li><a href="#" onClick="outerLayout.show('east', false)">Unhide East</a></li>
            <li><a href="#" onClick="outerLayout.open('east')"> Open East</a></li>
            <li><a href="#" onClick="outerLayout.open('north'); outerLayout.sizePane('north', 'auto')">  Resize North="auto"</a></li>
            <li><a href="#" onClick="outerLayout.sizePane('north', 100); outerLayout.open('north')">  Resize North=100</a></li>
            <li><a href="#" onClick="outerLayout.sizePane('north', 300); outerLayout.open('north')">  Resize North=300</a></li>
            <li><a href="#" onClick="outerLayout.sizePane('north', 10000); outerLayout.open('north')">Resize North=10000</a></li>
            <li><a href="#" onClick="outerLayout.open('south'); outerLayout.sizePane('south', 'auto')">  Resize South="auto"</a></li>
            <li><a href="#" onClick="outerLayout.sizePane('south', 100); outerLayout.open('south')">  Resize South=100</a></li>
            <li><a href="#" onClick="outerLayout.sizePane('south', 300); outerLayout.open('south')">  Resize South=300</a></li>
            <li><a href="#" onClick="outerLayout.sizePane('south', 10000); outerLayout.open('south')">Resize South=10000</a></li>
            <li><a href="#" onClick="outerLayout.panes.north.css('backgroundColor','#FCC')">North Color = Red</a></li>
            <li><a href="#" onClick="outerLayout.panes.north.css('backgroundColor','#CFC')">North Color = Green</a></li>
            <li><a href="#" onClick="outerLayout.panes.north.css('backgroundColor','')">    North Color = Default</a></li>
            <li><a href="#" onClick="alert('outerLayout.name = \''+outerLayout.options.name+'\'')">Show Layout Name</a></li>
            <li><a href="#" onClick="showOptions(outerLayout,'defaults')">Show Options.Defaults</a></li>
            <li><a href="#" onClick="showOptions(outerLayout,'north')">   Show Options.North</a></li>
            <li><a href="#" onClick="showOptions(outerLayout,'south')">   Show Options.South</a></li>
            <li><a href="#" onClick="showOptions(outerLayout,'west')">    Show Options.West</a></li>
            <li><a href="#" onClick="showOptions(outerLayout,'east')">    Show Options.East</a></li>
            <li><a href="#" onClick="showOptions(outerLayout,'center')">  Show Options.Center</a></li>
            <li><a href="#" onClick="showState(outerLayout,'container')"> Show State.Container</a></li>
            <li><a href="#" onClick="showState(outerLayout,'north')">     Show State.North</a></li>
            <li><a href="#" onClick="showState(outerLayout,'south')">     Show State.South</a></li>
            <li><a href="#" onClick="showState(outerLayout,'west')">      Show State.West</a></li>
            <li><a href="#" onClick="showState(outerLayout,'east')">      Show State.East</a></li>
            <li><a href="#" onClick="showState(outerLayout,'center')">    Show State.Center</a></li>
        </ul>
    </div>

    <div class="footer">Automatically positioned footer</div>

</div>

%{-- outer east  2a/4 --}%
<div class="ui-layout-east">

    <div class="header">Frequent Trending Excluded</div>

    <div class="subhead">I'm a subheader</div>

    <div class="content">
        <h3><b>Inner Layout</b></h3>
        <ul id="createInner">
            <li><a href="#" onClick="createInnerLayout(); return false;">CREATE Inner Layout</a></li>
        </ul>
        <ul id="innerCommands" style="display: none;">
            <li><a href="#" onClick="innerLayout.toggle('north')">Toggle North</a></li>
            <li><a href="#" onClick="innerLayout.toggle('south')">Toggle South</a></li>
            <li><a href="#" onClick="innerLayout.toggle('west')"> Toggle West</a></li>
            <li><a href="#" onClick="innerLayout.toggle('east')"> Toggle East</a></li>
            <li><a href="#" onClick="innerLayout.hide('north')">Hide North</a></li>
            <li><a href="#" onClick="innerLayout.hide('south')">Hide South</a></li>
            <li><a href="#" onClick="innerLayout.hide('west')"> Hide West</a></li>
            <li><a href="#" onClick="innerLayout.hide('east')"> Hide East</a></li>
            <li><a href="#" onClick="innerLayout.show('east')"> Show East</a></li>
            <li><a href="#" onClick="innerLayout.sizePane('north', 50); innerLayout.open('north')">   Resize North=50</a></li>
            <li><a href="#" onClick="innerLayout.sizePane('north', 300); innerLayout.open('north')">  Resize North=300</a></li>
            <li><a href="#" onClick="innerLayout.sizePane('north', 10000); innerLayout.open('north')">Resize North=10000</a></li>
            <li><a href="#" onClick="innerLayout.sizePane('south', 50); innerLayout.open('south')">   Resize South=50</a></li>
            <li><a href="#" onClick="innerLayout.sizePane('south', 300); innerLayout.open('south')">  Resize South=300</a></li>
            <li><a href="#" onClick="innerLayout.sizePane('south', 10000); innerLayout.open('south')">Resize South=10000</a></li>
            <li><a href="#" onClick="innerLayout.panes.north.css('backgroundColor','#FCC')">North Color = Red</a></li>
            <li><a href="#" onClick="innerLayout.panes.north.css('backgroundColor','#CFC')">North Color = Green</a></li>
            <li><a href="#" onClick="innerLayout.panes.north.css('backgroundColor','')">    North Color = Default</a></li>
            <li><a href="#" onClick="alert('innerLayout.name = \''+innerLayout.options.name+'\'')">Show Layout Name</a></li>
            <li><a href="#" onClick="showOptions(innerLayout,'defaults')">Show Options.Defaults</a></li>
            <li><a href="#" onClick="showOptions(innerLayout,'north')">   Show Options.North</a></li>
            <li><a href="#" onClick="showOptions(innerLayout,'south')">   Show Options.South</a></li>
            <li><a href="#" onClick="showOptions(innerLayout,'west')">    Show Options.West</a></li>
            <li><a href="#" onClick="showOptions(innerLayout,'east')">    Show Options.East</a></li>
            <li><a href="#" onClick="showOptions(innerLayout,'center')">  Show Options.Center</a></li>
            <li><a href="#" onClick="showState(innerLayout,'container')"> Show State.Container</a></li>
            <li><a href="#" onClick="showState(innerLayout,'north')">     Show State.North</a></li>
            <li><a href="#" onClick="showState(innerLayout,'south')">     Show State.South</a></li>
            <li><a href="#" onClick="showState(innerLayout,'west')">      Show State.West</a></li>
            <li><a href="#" onClick="showState(innerLayout,'east')">      Show State.East</a></li>
            <li><a href="#" onClick="showState(innerLayout,'center')">    Show State.Center</a></li>
        </ul>
    </div>

    <div class="footer">I'm a footer</div>


</div> %{--<div class="ui-layout-east">--}%



%{-- outer north  3a/4 --}%
<div class="ui-layout-north">

<script type="text/javascript">
    function fnTopLineToggle(s) {

        var x = $( '.topLineToggle' ).toggle();
        var i = 0;
        for (var key in x) {
            if (x.hasOwnProperty(key)) {
                console.log(key + " -> " + x[key]);
                try {
                    x[key].style.zIndex="10";

                } catch (err)
                {

                }
                //x[key].style.background="blue";
            }
        }
        //alert (i++ + ". x:" + x)

    }
</script>


%{--SOLAR--}%
<canvas onmouseover="fnTopLineToggle('canvas')"
        id="idCanvasPlanets"
        width="200px"
        height="20px">
</canvas>

<div class="header" id="joey">
    <table width="100%" height="30%" >
        <tr height="20px" >
            <td >
                <font size=-1>
                    <div style="z-index:2; background:transparent;"
                         align='left'
                         title="Notes&links, Personal database, email/calendar-timer/Gdocs/web-app-connect, language authoring,
                        json/data, tools/methods/workflows, script/execution, notify/remind action, language/knowledge editor, hierarchy/tags, programmable database,attention manager,
                        group/shared">

                        UsToDo
                        %{--UsToDox --}%

                        <button class="topLineToggle" style="display:none" onclick="buttonClickHandler_SendThenReceiveFromDart()">1 Send-Rcv-Dart</button>
                        <button class="topLineToggle" style="display:none" onclick="buttonClickHandler2_testDomStorage()">2 Dom data test in JS</button>
                        <button class="topLineToggle" style="display:none" onclick="buttonClickHandler3_testIfDartCanReadDomData()">3 test can Dart Read Dom Data</button>
                        <button style="border:none; background-color:transparent; vertical-align: top;display:none "
                                class="topLineToggle"
                                type="submit" name="method_premium"
                                title="Us To Do is a knowledge editor for shared note-taking and action-making with connections to your web sites, email, documents, and other resources."
                                onclick="alert('Us To Do is a knowledge editor for note-taking and action-making making with connections to your web sites, email, documents, and other resources.')"
                                value="<TMPL_VAR lang_premium_download>">
                            <img src="images/skin/questionmark2.jpeg" height="13" width="13" alt="" />
                        </button>
                        <button style="border:none; background-color:transparent; vertical-align: top;display:none "
                                class="topLineToggle"
                                type="submit" name="method_premium"
                                title="Send an email question to the developer."
                                onclick="alert('Send an email question to the developer.')"
                                value="<TMPL_VAR lang_premium_download>">
                            <img src="images/chat.png" height="13" width="13" alt="" />
                        </button>
                        <button style="border:none; background-color:transparent; vertical-align: top;display:none "
                                class="topLineToggle"
                                type="submit" name="method_premium"
                                title="Send yourself an email archive of your UsToDo records for backup and editing."
                                onclick="alert('Send yourself an email archive of your UsToDo records for backup and editing.')"
                                value="<TMPL_VAR lang_premium_download>">
                            <img src="images/emailarchive.png" height="13" width="13" alt="" />
                        </button>

                        <font size = -3>
                            <span class="topLineToggle" style="display:none" id="fps"></span></font>
                    </div>
                </font>
            </td>
            <td align="right">
                <div align="right">
                    <g:link controller="logout"><sec:username/></g:link>
                </div>
            </td>

        </tr>
    </table>

    %{--<div id="darta" name="ddd" hkhkhk="lll" itemValue="hkhk1" style="display: none">--}%
    %{--END SOLAR--}%

</div>

%{--<script type="application/dart" src="a131230_3_webappfromdarted.dart"></script>--}%
%{--<script type="application/dart" src="darttest/test1/a131230_3_WebAppFromDartEd/web/a131230_3_webappfromdarted.dart"></script>--}%
%{--darttest/test1/a131230_3_WebAppFromDartEd/web/a131230_3_webappfromdarted.html--}%
%{--<script src="packages/browser/dart.js"></script>--}%
%{--<script src="darttest/test1/a131230_3_WebAppFromDartEd/packages/browser/dart.js"></script>--}%

<script type="application/dart" src="darttest/140121SolarTestNotSource/solar.dart"></script>
<script src="packages/browser/dart.js"></script>


<script type="text/javascript">

    //                    alert ("i  hk.length " + hk.length);
    //                    alert ("i  hk.item(0) " + hk.item(0));    //htmldivelement
    //                    hk.item(0).setAttribute("bkbk","bkbkbk");
    //                    alert ("i  hk.item(0).getAttribute('bkbk') " + hk.item(0).getAttribute("bkbk"));
    //                    alert ("i  hk.item(0).dataset.hasDataAttr  ('hk') " + hk.item(0).dataset.hasDataAttr("hk"));    //htmldivelement
    //                    alert ("i  hk.item(0).id " + hk.item(0).id);
    //                    //alert ("i  hk.item(0).draggable " + hk.item(0).draggable);
    //                    //alert ("i  hk.item(0).itemValue " + hk.item(0).itemValue);
    //                    //alert ("i  hk.item(0).name " + hk.item(0).name);
    //                    alert ("i  hk.item(0).attributes " + hk.item(0).attributes); // namednodemap
    //                    alert ("i  hk.item(0).dataset " + hk.item(0).dataset);    //domstringmap
    //                    alert ("i  hk.item(0).dataset.hasAttribute(hk) " + hk.item(0).dataset.hasAttribute("hk"));    //domstringmap
    //                    alert ("i  hk.item(0).id " + hk.item(0).id);
</script>



<ul class="toolbar">
    %{--careful erasing - this line generates a click-worthy button from gsp/html/js--}%

    <li >
        <input type='button' id='idButtonReset' value='All' onclick="onActionButtonClick_userInvokedSearch('*')" title = "Search for '*', which means show all records by date.">&nbsp;
    </li>
    <li >
        <input type='button' id='idButtonReset'
               value='Inbox' onclick="onActionButtonClick_userInvokedSearch('inbox')"
        %{-- onmouseover="alert('nowly HK!')"--}%
               title = "Search for Nowly (things you are doing right now).">&nbsp;
        <input type='button' id='idButtonReset'
               value='Now' onclick="onActionButtonClick_userInvokedSearch('nowly')"
        %{-- onmouseover="alert('nowly HK!')"--}%
               title = "Search for Nowly (things you are doing right now).">&nbsp;
        <input type='button' id='idButtonReset'
               value='Today' onclick="onActionButtonClick_userInvokedSearch('today')"
               title = "Today's commitments)">&nbsp;
        <input type='button' id='idButtonReset'
               value='Daily' onclick="onActionButtonClick_userInvokedSearch('Daily')"
               title = "Daily reminders">&nbsp;
        <input type='button' id='idButtonReset'
               value='Weekly' onclick="onActionButtonClick_userInvokedSearch('weekly')"
               title = "Weekly reminders">&nbsp;
        <input type='button' id='idButtonReset'
               value='Beyond' onclick="onActionButtonClick_userInvokedSearch('beyond')"
               title = "Monthly and beyond reminders (not implemented)">&nbsp;
        <input type='button' id='idButtonReset'
               value='Manage' onclick="onActionButtonClick_userInvokedSearch('reminder management')"
               title = "Management reminders (not implemented)">&nbsp;



    </li>
    <li >
        <input type='button' id='idButtonReset' value='Calendar' onclick="onActionButtonClick_userInvokedSearch('reminder management')" title = "Calendar scheduler (not implemented)">&nbsp;
        <input type='button' id='idButtonReset' value='Email' onclick="onActionButtonClick_userInvokedSearch('reminder management')" title = "Email integration (not implemented)">&nbsp;
    </li>
    <li >
        <input type='button' id='idButtonReset' value='Cust' onclick="onActionButtonClick_userInvokedSearch('new button here')" title = "Custom favorites - create or invoke (not implemented).">&nbsp;
    </li>
    <li >
        <input type='button' id='idButtonReset' value='Search' onclick="onActionButtonClick_userInvokedSearch(tinyMCE.get('txtUpper').getContent({format : 'text'}))" title = "Search for current text">&nbsp;
        <input type='button' id='idButtonClearSearch' value='Clear' onclick="onActionButtonClick_userClickedClearSearch()" title = "Clear current text">&nbsp;
        <input type='button' id='idButtonSave' value='Save' onclick="xxxonActionButtonClick_userInvokedSearch('*')" title = 'Save current text (not implemented, use w command)'>&nbsp;
    </li>
    <li >
        <input type='button' id='idButtonReset' value='Size' onclick="singletonUtilLayout_layout_showAll_state.publicMethod_showMoreEastAndWest_orToggleWestIfAlreadyAllShown()" title = "Toggle through panel and font sizes (not implemented)">&nbsp;
    </li>
    <li >
        <select size=1 id="select_se_command"
                xxxwidth="500" style="width: 150px"
                title="Actions can be applied to your records and can link or launch to external applications such as email, facebook, news, or shopping. Click to see a list of actions."
                onchange="onActionSelect_userPickedASearchFromDropdown()">
            <option>~ SEARCH ~</option>
            <option selected=yes>&nbsp;&nbsp;&nbsp;&nbsp;Action</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;UsToDo search all (*)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;UsToDo hotlink - redirect to first web link in results (h)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;UsToDo hot cat - hot topic record entry mode (ca)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;UsToDo Topic-only Search (*2)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Personal Email/Gmail</option>

            <option>~ SAVE ~</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;UsToDo record insert (w)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;UsToDo record replace update/delete r</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Calendar record insert</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Google Docs Record Insert</option>

            <option>~ SEND/SHARE/PUBLISH ~</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;via SMS Text Message
            to ...</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;via Email</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;via Facebook</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;via Google+</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;via Twitter</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;via UsToDo</option>

            <option>~ META-SEARCH ~</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Amazon (a)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Bing (b)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Craigslist Boston (cr)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Domain Names - Lean Domain Search (dm)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Ebay (eb)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Email/Gmail (e)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Facebook - must be signed in (f)</option>
            <optgroup
                    label="&nbsp;&nbsp;&nbsp;&nbsp;Google (you must be signed in">
                <option>&nbsp;&nbsp;Google (g)</option>
                <option>&nbsp;&nbsp;Google Books (gb)</option>
                <option>&nbsp;&nbsp;Google Calendar (c)</option>
                <option>&nbsp;&nbsp;Google Contacts (cn)</option>
                <option>&nbsp;&nbsp;Google Docs (d)</option>
                <option>&nbsp;&nbsp;Google Docs title only (d2)</option>
                <option>&nbsp;&nbsp;Google Images (i)</option>
                <option>&nbsp;&nbsp;Google Maps (m)</option>
                <option>&nbsp;&nbsp;Google News (n)</option>
                <option>&nbsp;&nbsp;Google Reader (gr)</option>
                <option>&nbsp;&nbsp;Google Scholar (gs)</option>
                <option>&nbsp;&nbsp;Google Shopping (s)</option>
                <option>&nbsp;&nbsp;Google Tasks (t)</option>
            </optgroup>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;NewEgg (ne)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;NY Times (ny)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Reddit (r)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Sam Ash Music Store (sa)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Thesaurus (th)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Twitter (tw)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Weather (wt)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Wikipedia (k)</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Youtube (y)</option>

            <option>~ TOOLS ~</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Help</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Plugins</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Search Engine Management</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;External Application Management</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Query the database</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Author Plugin</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Write a script</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Write a workflow</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;History of search</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;History of launch</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;History of topic</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Create a Groovy or Javascript script</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Visualization</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Workflow management</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Email management</option>
            <option>&nbsp;&nbsp;&nbsp;&nbsp;Index DB</option>

        </select>

    </li>
    <li title = 'Dynamic filter - as you type, records within the search set are dynamically restricted' onclick="alert ('hi mom')">
        <input type='checkbox'  id='idCheckboxFilter' value='filter' >&nbsp;Filter
    &nbsp;
    &nbsp;
        <select size=1 id="select_filter"
                xxxwidth="500" style="width: 150px"
                title="For search and dynamic filter - determines what fields are searched."
                onchange="onActionSelect_userPickedASearchFromDropdown()">
            <option>Tags, all levels, and text (default)</option>
            <option>Tags, all levels, text, and connected text</option>
            <option>Tags and all levels</option>
            <option>Level 1 and up </option>
            <option>Level 2 and up </option>
            <option>Level 3 and up </option>
            <option>Level 4 and up </option>
        </select>

    </li>
    <li title = 'restrict results to web links'>

        <input type='checkbox' id='idCheckboxLinks' value='links' >&nbsp;Links
    </li>
    <li onmouseover="emitButton('spanid_testbuttonListItem','materializedTestButtonLabel', 'eventhandlerOnclickTestButton4');" id="testbuttonListItem1" class="first">
        <input type="button" value="Launch" title = "open a new browser tab with the command below" onclick="launchChild()">
    </li>
    <li onmouseover="emitButton('spanid_testbuttonListItem','materializedTestButtonLabel', 'eventhandlerOnclickTestButton4');" id="testbuttonListItem1" class="first">
        <input type="button" value="Backup" title = "back up all stored user data" onclick="alert ('not implemented, but you can copy paste screen content to a Google doc')">
    </li>


    <script>
        function closeWindow() {
            window.open('','_parent','');
            window.close();
            //alert('in hkhkhk');
        }
    </script>
    <span style="visibility: hidden;display:none">

        <li id="tbarPinWest" ><span></span>Context</li>
        <li id="tbarOpenSouth"><span></span>Latest</li>
        <li id="tbarCloseSouth"><span></span>Category</li>
        <li id="tbarPinEast" class="last"><span></span>Match</li>
        <li id="tbarToggleNorth" class="first"><span></span>Hide Me</li>
        <li id="tbarToggleNorth" class="first"><span></span>

            <INPUT type="button" value="TestMe2" title="Test #2 - don't click" src="xxxxxxxxx.png" height="19px" width="19px"
                   name="clearhtmllog" class="xxxunhideOnUpperMouseOver" id="yyycommandlinetoggle"
                   onclick="testme2();getElementByIdHK('id_status_log').innerHTML=''"
            >

            <input rows="4" cols="50" id="testme2ta">
            200
        </input>

            <INPUT type="button" value="TestMe3" title="Test #3 " height="19px" width="19px"
                   onclick="testme3();"
            >

        </li>
        <li onmouseover="emitButton('spanid_testbuttonListItem','testButtonLabel', 'eventhandlerOnclickTestButton3');" id="testbuttonListItem1" class="first"><span></span>
            moseover to create test button
        </li>
        <li id="testbuttonListItem2" class="first">
            xx<span id='spanid_testbuttonListItem'></span>
        </li>
    </span>

</ul>

</div> %{--<div class="ui-layout-north">--}%


%{-- outer south 4a/4 --}%
<div class="ui-layout-south">
    <div class="header">Under construction (Outer - South)</div>
    <div class="content">
        <p>I only have a resizer/toggler when 'open'</p>
    </div>

</div>%{--<div class="ui-layout-south">--}%


%{-- END LAYOUT --}%
















<div id="mainContent">
    <!-- DIVs for the INNER LAYOUT -->

    <div class="ui-layout-center" >
        %{--<div class="ui-layout-center" id="divId_ui-layout-center">--}%
        <h3 class="header2">

            %{--spanid_status--}%
            <span id=spanid_status>
            </span>

            %{--spanid_status_filter--}%
            <span id=spanid_status_filter>
            </span>

            <span id=spanid_base_saved>
            </span>

            <span id=spanidundobutton>
            </span>

            <span id=spanIdNumLines ></span>
            <span id='spanIdBulkOpResult'></span>
            <span id='spanidundobotton'></span>

        </h3>
        <div class="ui-layout-content">

            <p id="createInner2" style="font-weight: bold;"><a href="#" onClick="createInnerLayout(); return false;"
            >Click here to CREATE the Inner Layout</a></p><!-- outerLayout.open('east');  -->

            <span id="spanid_withinTableTest">
            </span>

        </div>
        <div class="footer">Center panes can have headers &amp; footers too</div>
    </div> %{--<div class="ui-layout-center">--}%

    <div class="ui-layout-north" id="id-ui-layout-north-inner" xxxonresize="console.log('hi dad')">

        <textArea
                id="txtUpper"
                rows="2"
                name="q"
                class="fld1upperclass"
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

                style="color:olive;font-size:24px;margin-left:1px;vertical-align: bottom; height:3em"
        />
        ${q}
    </textarea>
        <br>
    </div>     %{--<div class="ui-layout-north"--}%

    <div class="ui-layout-south">
        Settings
        <span id="spanidBelowSettings" style="color:blue;margin-left:0px">
        </span>

        <form action="" method="GET">

            <input type="text" name="field1"/>
            <input type="submit" name="go" value="Submit"/>


            <br>Options:
            <input type = "text"
                   id="idTextFieldUtdoptions"
                   title="command line options: 1) a '+' to see time details on records.  2) -tm=4m limit records to max of 4 months old ('m' and 'd'ays are the two time units)"
                   name="utdoptions_formFieldHTMLkey"
                   value="${utdoptions}"
                   size="120px%"
            />
            <br>
            sdfsdfsdfsdfsd
            <INPUT type="button" value="testme" title="xRefresh" src="xxxxxxxxx.png" height="19px" width="19px"
                   name="clearhtmllog" class="xxxunhideOnUpperMouseOver" id="yyycommandlinetoggle"
                   onclick="testme();getElementByIdHK('id_status_log').innerHTML=''"
            >
            <span id="id_status_log">    </span>
        </form>
        <br>
        <p>See the <a href="#" onclick="outerLayout.open('east'); return false;">Outer-East pane</a> for commands to manipulate the Inner Layout</p>


    </div> %{--<div class="ui-layout-south">--}%

    <div class="ui-layout-west" id='ui-layout-west-inner-hbk'>
        %{--<h3 id="idheader" height='100px' class="header2">--}%
        %{--<span id="spanid_categ_header" onmouseover="hideRowsNotContainingFullTermSet('eventHandlerMouseoverCateg2', '*', false, true, false, true)">--}%
        %{--a--}%
        %{--</span>--}%
        %{--</h3>--}%
        <span id="spanid_categs">
        </span>
    </h3>
    </div>     %{--<div class="ui-layout-west"--}%

    <div class="ui-layout-east" xxonmouseover="layout_changePanelSize(innerLayout, 'east', 500)" xxonmouseout="layout_changePanelSize(innerLayout, 'east', 100)">  History Email Docs Calendar
        <br><a href="/todo?q=*">*</a>
        <br><a href="/todo?q=today">today</a>
        <br><a href="/todo?q=outlook%20filter">outlook filter</a>
        <br><a href="/todo?q=weekly">weekly</a>
        <br><a href="/todo?q=monthly">monthly</a>
        <br><a href="/todo?q=scrum">scrum</a>
        <br><a href="/todo?q=monitor">monitor</a>
        <br><a href="/todo?q=crd%20rev">crd rev</a>
        <br><a href="/todo?q=crd%20project">crd project</a>
        <br><a href="/todo?q=hk%20project">hk project</a>
    </div> %{--<div class="ui-layout-east"--}%

</div> %{--<div id="mainContent">--}%

</body>
</html>



%{--//alert ("in here3");--}%
%{--//$(function() {--}%
%{--//  alert ("in here4");--}%
%{--//        $(document).bind("mouseup", function()--}%
%{--//        {--}%
%{--//            var selectedText = getSelectionHtmlAndText().text;--}%
%{--//            if (selectedText != "")--}%
%{--//            {--}%
%{--//                tinyMCE.getInstanceById('txtUpper').setContent(selectedText)--}%
%{--//                //tinyMCE.getInstanceById('txtUpper').focus()--}%
%{--//--}%
%{--//            }--}%
%{--//            //oooo ("selectedText:" + selectedText);--}%
%{--//            //                alert("in herer");--}%
%{--//            //                //getElementByIdHK('changetest').innerHTML = "mouse:begin";--}%
%{--//            //                if ((typeof x.Selector != "undefined"))--}%
%{--//            //                {--}%
%{--//            //                    //alert ("in mouse getClass(x):"+getClass(x))--}%
%{--//            //                    //var src = x.parameter.source;--}%
%{--//            //                    //var newValue = x.parameter[src];--}%
%{--//            //                    var mytext2 = x.Selector.getSelected().toString();--}%
%{--//            //--}%
%{--//            //                    //alert ("asd:"+mytext2.length)--}%
%{--//            //                    if (mytext2.length > 0)--}%
%{--//            //                    {--}%
%{--//            //                        alert("in heres:"+mytext2.toString());--}%
%{--//            //                        getElementByIdHK('changetest').innerHTML = "mouse:" + mytext2.toString();--}%
%{--//            //                        //if (DEBUGGING)--}%
%{--//            //                        //{--}%
%{--//            //                        //    getElementByIdHK('txtUpper').value = getElementByIdHK('txtUpper').value + " // " + mytext2.toString();--}%
%{--//            //                        //}--}%
%{--//            //                    }--}%
%{--//            //                }--}%
%{--//            //alert("mytext" + getClass(mytext));--}%
%{--//            }--}%
%{--//        );  //        end $(document).bind("mouseup", function()--}%

%{--//            $(document).bind("keyup", function() {--}%
%{--//--}%
%{--//                //getElementByIdHK('changetest').innerHTML = "key:begin";--}%
%{--//                if ((typeof x.Selector != "undefined"))--}%
%{--//                {--}%
%{--//                    var mytext = x.Selector.getSelected().toString();--}%
%{--//                    //alert ("in key mytext:"+mytext)--}%
%{--//                    if (mytext.length > 0)--}%
%{--//                    {--}%
%{--//                        alert("key:" + mytext);--}%
%{--//                                getElementByIdHK('changetest').innerHTML = "key:" + mytext;--}%
%{--//                        //getElementByIdHK('txtUpper').value = getElementByIdHK('txtUpper').value + " // " + mytext;--}%
%{--//                    }--}%
%{--//                }--}%
%{--//                //alert("mytext" + getClass(mytext));--}%
%{--//            });--}%

%{--//        $(document).bind("mousemove", function() {--}%
%{--//--}%
%{--//            var mytext = x.Selector.getSelected();--}%
%{--//            getElementByIdHK('changetest').innerHTML = 'mousemove';--}%
%{--//            //alert("mytext" + getClass(mytext));--}%
%{--//        });--}%
%{--//});--}%

%{--//        alert ("hbkiframe2a--:"+ $("hbkiframe2").toString());--}%
%{--//        //  from http://stackoverflow.com/questions/12094300/jquery-resize-iframe-stops-when-contracting-quickly--}%
%{--//        $("hbkiframe2").resizable({--}%
%{--//            //containment: 'parent',--}%
%{--//            minWidth: 400,--}%
%{--//            minHeight: 200,--}%
%{--//            start: function () {--}%
%{--//                alert ("hi bob");--}%
%{--//                $(".widget_box").each(function (index, element) {--}%
%{--//                    var d = $('<div class="iframeCover" style="zindex:99;position:absolute;width:100%;top:0px;left:0px;height:' + $(element).height() + 'px"></div>');--}%
%{--//                    $(element).append(d);--}%
%{--//                });--}%
%{--//            },--}%
%{--//            stop: function () {--}%
%{--//                alert ("hi bob2");--}%
%{--//                $('.iframeCover').remove();--}%
%{--//            }--}%
%{--//        });--}%
%{--//        alert ("hbkiframe2b:"+ $("hbkiframe2"));--}%
%{--///alert ('hi hk done docinit1');--}%


%{--//alert ('hi hk done docinit2c');--}%
