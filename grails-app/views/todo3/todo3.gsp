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

    <title>UsToDo3</title>



    <link rel="shortcut icon" href="images/yellowflower.jpg" type="image/x-icon">
    %{--<script src="js/todo3/libs/jquery.min.js"></script>--}%
    %{--<script src="js/todo3/libs/jquery-ui.min.js"></script>--}%
    %{--<script src="js/todo3/libs/bootstrap.min.js"></script>--}%
    %{--<script src="js/todo3/libs/angular.min.js"></script>--}%
    %{--<script src="js/todo3/libs/angular-local-storage.min.js"></script>--}%
    %{--<script src="js/todo3/libs/sortable.js"></script>--}%

    %{--<script src="http://cdn.kendostatic.com/2014.1.318/js/kendo.all.min.js"></script>--}%
    %{--<script src="http://cdn.kendostatic.com/2014.1.318/styles/kendo.common.min.css"></script>--}%

    %{--<base href="http://demos.telerik.com/kendo-ui/splitter/index">--}%
    <style>html { font-size: 12px; font-family: Arial, Helvetica, sans-serif; }</style>
    <title></title>

    <link rel="stylesheet" href="http://cdn.kendostatic.com/2014.1.528/styles/kendo.common.min.css">
    <link rel="stylesheet" href="http://cdn.kendostatic.com/2014.1.528/styles/kendo.rtl.min.css">
    <link rel="stylesheet" href="http://cdn.kendostatic.com/2014.1.528/styles/kendo.default.min.css">
    <link rel="stylesheet" href="http://cdn.kendostatic.com/2014.1.528/styles/kendo.dataviz.min.css">
    <link rel="stylesheet" href="http://cdn.kendostatic.com/2014.1.528/styles/kendo.dataviz.default.min.css">
    <link rel="stylesheet" href="http://cdn.kendostatic.com/2014.1.528/styles/kendo.mobile.all.min.css">

    %{--<link href="http://cdn.kendostatic.com/2014.1.528/styles/kendo.common.min.css" rel="stylesheet" />--}%
    %{--<link href="http://cdn.kendostatic.com/2014.1.528/styles/kendo.default.min.css" rel="stylesheet" />--}%
    %{--<link href="http://cdn.kendostatic.com/2014.1.528/styles/kendo.dataviz.min.css" rel="stylesheet" />--}%
    %{--<link href="http://cdn.kendostatic.com/2014.1.528/styles/kendo.dataviz.default.min.css" rel="stylesheet" />--}%
    <script src="http://cdn.kendostatic.com/2014.1.528/js/jquery.min.js"></script>
    <script src="http://cdn.kendostatic.com/2014.1.528/js/kendo.all.min.js"></script>



    <g:javascript src="nghbk/angular.min.js"/>
    %{--<script src="js/todo3/libs/sortable.js"></script>--}%

    <g:javascript src="todo3/appAngularTodo3.js"/>
    %{--<script src="js/nghbk/angular.min.js"></script>--}%


    <script type="text/javascript">
        function init()
        {
            try
            {
                alert ("in init");
            }
            catch (err) {
                alert ("error in todo3.gsp:" + err.toString())
            }
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
            splitter = $("#vertical")
                    .data("kendoSplitter")
                    .size("#middlePane", $(window).height() - 200 + "px")
                    .trigger("resize")
        };

        resizeSplitter();













</script>

</head>

<body ng-app="Todo3AngularModule">

    <div id="example">
        <div id="vertical">
            <div id="top-pane">
                <div id="horizontal" style="height: 100%; width: 100%;">
                    <div id="left-pane">
                        <div class="pane-content">
                            <h3>Inner splitter / left pane</h3>
                            <p>Resizable and collapsible.</p>
                        </div>
                    </div>
                    <div id="center-pane">
                        <div class="pane-content">
                            <h3>Inner splitter / center pane</h3>
                            <p>Resizable only.</p>
                        </div>
                    </div>
                    <div id="right-pane">
                        <div class="pane-content">
                            <h3>Inner splitter / right pane</h3>
                            <p>Resizable and collapsible.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div id="middle-pane">
                <div class="pane-content">
                    <h3>Outer splitter / middle pane</h3>
                    <p>Resizable only.</p>
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
                        { collapsible: false },
                        { collapsible: false, size: "100px" },
                        { collapsible: false, resizable: false, size: "100px" }
                    ]
                });

                $("#horizontal").   kendoSplitter({
                    panes: [
                        { collapsible: true, size: "220px" },
                        { collapsible: false },
                        { collapsible: true, size: "220px" }
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
