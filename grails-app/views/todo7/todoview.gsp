%{--
http://u2d.co/todo3
http://jsfiddle.net/t3AG6/4/

current state : backed up todo3 to C:\140525publicC\grails-app\views\todo3\todo3Back140707.gsp
copied from click EDIT THIS EXAMPLE from http://demos.telerik.com/kendo-ui/splitter/index
 to here - works!!  140707

--}%

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="Todo3AngularModule">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="language" content="en" />

    <title>UsToDo6</title>

    <link rel="shortcut icon" href="images/yellowflower.jpg" type="image/x-icon">
    <style>html { font-size: 12px; font-family: Arial, Helvetica, sans-serif; }</style>

    <link rel="stylesheet" href="http://cdn.kendostatic.com/2014.1.528/styles/kendo.common.min.css">
    <link rel="stylesheet" type="text/css" href="kendo/telerik.kendoui.2014.1.416.core/styles/kendo.blueopal.min.css">
    <script src="http://cdn.kendostatic.com/2014.1.528/js/jquery.min.js"></script>
    <g:javascript src="kendoui/kendo.ui.core.min.js"/>
    <g:javascript src="nghbk/angular.min.js"/>
    <g:javascript src="todo3/appAngularTodo3.js"/>
    %{--<script src="js/nghbk/angular.min.js"></script>--}%
    <g:javascript src="tinymce411/tinymce_4.1.1_dev/tinymce/js/tinymce/tinymce.full.js"/>

    <script type="text/javascript">

        function tinyMceInitMyCustom()
        {
            alert ("in tinyMceInitMyCustom");
        }

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
//            splitter = $("#vertical")
//                    .data("kendoSplitter")
//                    .size("#middlePane", $(window).height() - 200 + "px")
//                    .trigger("resize")
        };

        resizeSplitter();


        //alert ("pre tinymce init");
        //        tinyMCE.init({
        //            // General options
        //            mode : "textareas",
        //            theme : "advanced",
        //            plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",
        //
        //            // Theme options
        //            theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleselect,formatselect,fontselect,fontsizeselect",
        //            theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
        //            theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
        //            theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak",
        //            theme_advanced_toolbar_location : "top",
        //            theme_advanced_toolbar_align : "left",
        //            theme_advanced_statusbar_location : "bottom",
        //            theme_advanced_resizing : true
        //        });

        tinymce.init({
            setup : function(ed) {
                // New event
                ed.on('init', function(args) {
                    alert ("hi from mce init");
                });

                ed.on('ObjectResizeStart', function(e) {
                    if (e.target.nodeName == 'IMG') {
                        // Prevent resize
                        e.preventDefault();
                    }
                });

                ed.on('ObjectResized', function(e) {
                    alert(e.target, e.width, e.height);
                });



            },
            theme : "modern", //advanced or simple
            selector: "textarea",
            plugins: "autoresize",
            width: '100%',
            height: 400,
            //autoresize_min_height: 300,
            //autoresize_max_height: 800,
            resize: "both",
            statusbar : true,
            theme_modern_toolbar_location : "bottom",
//            init_instance_callback: function (editor) {
//                $(editor.getContainer()).find(".mce-path").css("opacity", "0");
//            },
            plugins: [
                    // autoresize
                " fullscreen  advlist autolink lists link image charmap print preview anchor",
                "searchreplace visualblocks code fullscreen",
                "insertdatetime media table contextmenu paste "
            ],
// from http://www.tinymce.com/wiki.php/Configuration:style_formats
//            style_formats: [
//                {title: 'Bold text', inline: 'b'},
//                {title: 'Red text', inline: 'span', styles: {color: '#ff0000'}},
//                {title: 'Red header', block: 'h1', styles: {color: '#ff0000'}},
//                {title: 'Example 1', inline: 'span', classes: 'example1'},
//                {title: 'Example 2', inline: 'span', classes: 'example2'},
//                {title: 'Table styles'},
//                {title: 'Table row 1', selector: 'tr', classes: 'tablerow1'}
//            ],
            toolbar: "fullscreen  insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
        });
        //alert ("post tinymce init");


        //        plugins: [
        //            "advlist autolink lists link image charmap print preview anchor",
        //            "searchreplace visualblocks code fullscreen",
        //            "insertdatetime media table contextmenu paste moxiemanager"
        //        ],


    function xx()
    {
        //alert('sdfsdfxx1')
        //var url = "https://google.com";
        window.location = "https://google.com?q=u2d";
        //window.location.replace("https://google.com?q=u2d");
        //alert('sdfsdfxx2')
    }


    </script>

</head>

<body ng-app="Todo3AngularModule">

<div id="example" ng-controller="AppAngularTodo3Ctrl">
    <div id="vertical" style="height: 100%; width: 100%;">
        <div id="top-pane0" >
            <form method="post" action="somepage">
                <textarea name="content" style="width:100%"></textarea>
            </form>
        </div>
        <div id="top-pane">
            <div id="horizontal" style="height: 100%; width: 100%;">
                <div id="left-pane">
                    <div class="pane-content">
                        <h3>left-pane Inner splitter / left pane</h3>
                        <p>Resizable and collapsible.</p>
                        <button onclick="xx()">
                            sdfsdf
                        </button>
                    </div>
                </div>
                <div id="center-pane">
                    <div class="pane-content">
                        <h3>center-pane Inner splitter / center pane</h3>
                        <p>Resizable only.</p>
                    </div>
                </div>
                <div id="right-pane">
                    <div class="pane-content">
                        <h3>right-pane Inner splitter / right pane</h3>
                        <p>Resizable and collapsible.</p>
                    </div>
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
        <div id="bottom-pane">
            <div class="pane-content">
                <h3>Outer splitter / bottom pane</h3>
                <p>Non-resizable and non-collapsible.</p>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            $("#vertical").kendoSplitter({
                orientation: "vertical",
                panes: [
//                    { collapsible: false, size: "220px"  },
                    { collapsible: false, resizable: true, size: "420px" },
                    { collapsible: false, size: "420px" },
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

</body>

</html>
