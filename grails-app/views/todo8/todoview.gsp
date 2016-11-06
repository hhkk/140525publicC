%{--
http://u2d.co/todo3
http://jsfiddle.net/t3AG6/4/

current state : backed up todo3 to C:\140525publicC\grails-app\views\todo3\todo3Back140707.gsp
copied from click EDIT THIS EXAMPLE from http://demos.telerik.com/kendo-ui/splitter/index
 to here - works!!  140707

--}%

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="Todo8AngularModule">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="language" content="en" />

    <title>UsToDo8</title>

    <link rel="shortcut icon" href="images/yellowflower.jpg" type="image/x-icon">
    <style>html { font-size: 150%; font-family: Arial, Helvetica, sans-serif; }</style>

    <link rel="stylesheet" href="http://cdn.kendostatic.com/2014.1.528/styles/kendo.common.min.css">
    <link rel="stylesheet" type="text/css" href="kendo/telerik.kendoui.2014.1.416.core/styles/kendo.blueopal.min.css">
    <link rel="stylesheet" type="text/css" href="css/complex.css">
    <script src="http://cdn.kendostatic.com/2014.1.528/js/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-beta.17/angular.js"></script>
    <script src="/jqueryUI/customdark/jquery-ui-1.11.0.custom/jquery-ui.js"></script>
    <script src="js/utiljs/UtilClass.js"></script>
    <script src="js/utiljs/UtilRegExURL.js"></script>
    <script src="js/utiljs/UtilSetCaretInTextField.js"></script>
    <script src="js/utiljs/UtilContentEditable.js"></script>


    %{--<g:resource dir="css" file="complex.css" absolute="true" />--}%
    %{--<g:resource dir="css" file="complex.css" absolute="true" />--}%

    <g:javascript src="kendoui/kendo.ui.core.min.js"/>
    %{--<g:javascript src="nghbk/angular.min.js"/>--}%
    %{--<g:javascript src="nghbk/angular.min.js"/>--}%

    <g:javascript src="todo8/appAngularTodo8.js"/>
    %{--<script src="js/nghbk/angular.min.js"></script>--}%

    <script type="text/javascript">

        $("#vertical").kendoSplitter({
            orientation: "vertical",
            panes: [
                { collapsible: false, resizable: false, size: "100px" },
                { collapsible: false },
                { collapsible: false, resizable: false, size: "100px" }
            ]
        });

        $("#horizontal").kendoSplitter({
            panes: [
                { collapsible: true },
                { collapsible: false },
                { collapsible: true }
            ]
        });

        $(window).resize(function() {
            resizeSplitter()
        });

        resizeSplitter = function() {
            //alert ("hi mom");
            //alert ("hi mom1"+ $(window).height());
            //alert ("hi mom2:" + ($(window).height() - 200));
            // splitter = $("#vertical")
            // .data("kendoSplitter")
            // .size("#middlePane", $(window).height() - 200 + "px")
            // .trigger("resize")
        };

        function navigateWithBackToURL()
        {
            //alert('sdfsdfxx1')
            //var url = "https://google.com";
            window.location = "https://google.com?q=u2d";
            //window.location.replace("https://google.com?q=u2d");
            //alert('sdfsdfxx2')
        }

    </script>

</head>

<body ng-app="Todo8AngularModule">







<div id="example" ng-controller="AppAngularTodo8Ctrl">

    <button onclick="buttonClickSetHtml('hi hk');">SetHtml</button>
    <button onclick="buttonClickShowHtml();">ShowHtml</button>
    <button onclick="buttonClickSetHtmlCreatedTextNode();">SetHtmlCreatedTextNode</button>
    <button onclick="buttonClickCEFocus();">CEFocus</button>
    <button onclick="buttonClickCEBlur();">CEBlur</button>
    <button onclick="buttonSetCaretAtEnd();">CaretAtEnd</button>


    %{--// DRAGGABLE WORKS --}%
    %{--<div id="droppable">Drop here</div>--}%
    %{--<div id="draggable">Drag me</div>--}%
    %{--<script>--}%
    %{--$( "#droppable" ).droppable({--}%
    %{--drop: function() {--}%
    %{--alert( "dropped" );--}%
    %{--}--}%
    %{--});--}%
    %{--$( "#draggable" ).draggable();--}%
    %{--</script>--}%


    <div id="vertical" style="height: 100%; width: 100%;">

        %{-- TOP PANE--}%
        <div id="top-pane0" >
            <form method="post" action="somepage">
                %{--<textarea name="content" style="width:100%"></textarea>--}%
                %{--<div id="contenteditableeditor" contenteditable="true" >Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras lorem nulla, sollicitudin nec commodo quis, ultricies in mauris. Cras facilisis pretium nunc ut auctor. Fusce congue diam sit amet ipsum luctus vitae porttitor sapien egestas. Suspendisse vestibulum nibh vitae nulla accumsan sed laoreet dolor iaculis. Vivamus id laoreet felis.</div>--}%
                <div id="contenteditableeditor" contentEditable="true"
                     onclick = "$('#contenteditableeditor')[0].contentEditable = true;"
                %{--onmouseout = "this.contentEditable = false; this.blur()"--}%
                %{--onmouseover = "this.contentEditable = true; this.focus()" --}%
                >
                    %{--onmouseover = "alert ("hihk");this.contentEditable = true;" >--}%
                    Lorem ipsum dolor sit amet, consectetur <B>adipiscing</B> elit. Cras lorem <b>hi mom</b>nulla, sollicitudin nec commodo quis, ultricies in mauris. Cras facilisis pretium nunc ut auctor. Fusce congue diam sit amet ipsum luctus vitae porttitor sapien egestas. Suspendisse vestibulum nibh vitae nulla accumsan sed laoreet dolor
                    <div
                    %{--onmouseover="showURLeditor()" contentEditable="false"--}%
                    >
                        Bla bla <a href="http://google.com">Google</a> Bla bla
                    </div>
                    iaculis. Vivamus id laoreet felis.</div>
            </form>
        </div>
        <div id="horizontal" style="height: 100%; width: 100%;">

            %{-- LEFT PANE--}%
            <div id="left-pane">
                <div class="pane-content">
                    <h3>left-pane Inner splitter / left pane</h3>
                    <p>Resizable and collapsible.</p>
                    <li ng-repeat="friend in friends">
                        {{friend.name}}
                    </li>

                </div>
            </div>

            %{-- CENTER PANE--}%
            <div id="center-pane">
                <div class="pane-content">
                    <h3>center-pane Inner splitter / center pane</h3>
                    <p>Resizable only.</p>
                </div>
            </div>

            %{-- RIGHT PANE--}%
            <div id="right-pane">
                <div class="pane-content">
                    <h3>right-pane Inner splitter / right pane</h3>
                    <p>Resizable and collapsible.</p>
                </div>
            </div>
        </div>
        <div id="middle-pane">
            <div class="pane-content">
                <h3>middle-pane Outer splitter / middle pane</h3>
                <p>Resizable only.</p>
                hkhkhk:{{friends.length}}

            </div>
        </div>
    </div>


    <style scoped>
    #vertical {
        height: 380px;
        width: 700px;
        margin: 0 auto;
    }

    #middle-pane { background-color: rgba(60, 70, 80, 0.10); }
    #bottom-pane { background-color: rgba(60, 70, 80, 0.15); }
    #left-pane, #center-pane, #right-pane  { background-color: rgba(60, 70, 80, 0.05); }

    .pane-content {
        padding: 0 10px;
    }
    </style>
</div>







<script id="1 BUTTON CLICK SCRIPTS">

    // 1 BUTTONS
    function buttonClickSetHtml(s)    {
        //$("#contenteditableeditor").html("<a href='http://google.com' target = 'blank'>Try clicking this link.</a>");
        $("#contenteditableeditor").html("lorem " + buildAhref ("click googlecom", 'http://google.com'));

    }

    function buttonClickShowHtml()   {
        alert ($("#contenteditableeditor").html());
    }

    function boldHTML() {
        var element = document.createElement("b");
        element.innerHTML = "Bold text";
        return element;
    }

    function buttonClickSetHtmlCreatedTextNode()    {
        var x = document.createTextNode('[NODE]');

        document.body.appendChild(boldHTML());
        alert (getClass(x));
    }

    function buttonClickCEFocus()    {
        //$("#contenteditableeditor")[0].focus();
        //setCaret(0);
        //placeCaretAtEnd($("#contenteditableeditor"))
    }
    function buttonClickCEBlur()    {
        $("#contenteditableeditor")[0].blur();
    }
    function buttonSetCaretAtEnd()    {
        placeCaretAtEnd($("#contenteditableeditor"));
    }
</script>

<script id="2 KEY AND FOCUS HANDLERS">


    //        alert ("hi hk");
    //var p = document.getElementById("contenteditableeditor");

    // 2 FOCUS
    $('#contenteditableeditor')[0].onfocus = function() {
        //alert("contenteditableeditor focus"); // works
    };

    // 3 KEYSTROKE EVENT
    $('#contenteditableeditor').keypress( function(event)
            {keypressHandler2(event);}
    );
</script>

<script id="3 URL PROCESSING IN CONTENT EDIT">


    // 4 URL processing
    // http://jsfiddle.net/TgAGk/1/
    // http://stackoverflow.com/questions/2459180/how-to-edit-a-link-within-a-contenteditable-div
    //        document.getElementById("contenteditableeditor").addEventListener("input", function() {
    //            //      alert("input event fired");
    //        }, false);
    //        $("contenteditableeditor" ).draggable();


    // http://jsfiddle.net/GeVpe/22/
    //        <div id="content" contentEditable="true" onclick = "this.contentEditable = true;" onmouseout = "this.contentEditable = false;">
    //            <a href="http://google.com" target = "blank">Try clicking this link.</a>
    //        </div>

    // http://stackoverflow.com/questions/4823691/insert-an-html-element-in-a-contenteditable-element
</script>

<script id="4 KENDO SPLIT SIZE COLLAPSIBLE RESIZABLE INIT">

    // 5 ONLOAD AND SPLITTER STYLES
    $(document).ready(function() // not onload
    {
        $("#vertical").kendoSplitter({
            orientation: "vertical",
            panes: [
//                    { collapsible: false, size: "220px"  },
                { collapsible: false, resizable: true, size: "420px" },
                { collapsible: false, resizable: true, size: "420px" },
                { collapsible: false, resizable: true, size: "120px" }
            ]
        });

        $("#horizontal").kendoSplitter({
            panes: [
                { collapsible: true, resizable: true, size: "220px" },
                { collapsible: false, resizable: true },
                { collapsible: true, resizable: true, size: "220px" }
            ]
        });
    });

</script>

<script id="5 CONTENT EDITABLE INIT">
    // 5.5 CE (contenteditable) features
    $( "#contenteditableeditor" )[0].spellcheck = false;
    $( "#contenteditableeditor" )[0].focus();


</script>

<script id="6 KEYPRESS">


    // 6 CTRL KEY LISTENING FOR URL EDITING FROM http://stackoverflow.com/questions/12059211/how-to-make-clickable-anchor-in-contenteditable-div
    //        var content = document.getElementById('contenteditableeditor');
    //
    //        document.addEventListener('keydown', function(event) {
    //            if (event.keyCode === 17) {          // when ctrl is pressed
    //                //alert ("ctrl key down");
    //                content.contentEditable = false; // disable contentEditable
    //            }
    //        }, false);
    //
    //        document.addEventListener('keyup', function(event) {
    //            if (event.keyCode === 17) {          // when ctrl is released
    //                content.contentEditable = true;  // reenable contentEditable
    //                //alert ("ctrl key up");
    //            }
    //        }, false);



    document.addEventListener('keydown', function (event) {
        if (isContentEditable($("#contenteditableeditor")[0]) &&
                document.querySelector(":focus") == $("#contenteditableeditor")[0] &&
                event.which == 27)
        {
            alert("begin save:");
        }

    });
    function keypressHandler2(event)
    {
        // WHICH ASCII
        //alert(getClass("event", event));
        //alert("event.which:" + event.which);
        if ( event.which == 13 ) {
            //alert("enter pressed");
        }
        //$.print( event );
        // PREVENT DEFAULT
        if (false) {
            event.preventDefault();
        }
        //$("#contenteditableeditor")[0].innerHTML = "sdfsdfsdfxxx";
        //document.getElementById("contenteditableeditor").innerHTML = "sdfsdfsdf";
    }

    // 7 RANGE SELECTION AND PASTE
    // from http://stackoverflow.com/questions/4823691/insert-an-html-element-in-a-contenteditable-element
    //   is http://jsfiddle.net/timdown/XGSyn/

</script>







</body>

</html>
