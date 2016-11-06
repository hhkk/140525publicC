%{--
http://u2d.co/todo3
--}%

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="Todo3AngularModule">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="language" content="en" />

    <title>UsToDo3</title>

    <link rel="shortcut icon" href="images/yellowflower.jpg" type="image/x-icon">
    <script src="js/todo3/libs/jquery.min.js"></script>
    <script src="js/todo3/libs/jquery-ui.min.js"></script>
    <script src="js/todo3/libs/bootstrap.min.js"></script>
    <script src="js/todo3/libs/angular.min.js"></script>
    <script src="js/todo3/libs/angular-local-storage.min.js"></script>
    <script src="js/todo3/libs/sortable.js"></script>

    <script src="http://cdn.kendostatic.com/2014.1.318/js/kendo.all.min.js"></script>
    <script src="http://cdn.kendostatic.com/2014.1.318/styles/kendo.common.min.css"></script>




    <script src="js/todo3/libs/sortable.js"></script>

    %{--<g:javascript src="nghbk/angular.min.js"/>--}%
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









        //    http://jsfiddle.net/t3AG6/4/
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

<body ng-controller="AppAngularTodo3Ctrl" ng-init="init()">



<div id="example" class="k-content">
    <div id="vertical">
        <div>
            <p>
                Outer splitter : top pane (resizable and collapsible)
            </p>
        </div>
        <div id="middlePane">
            <div id="horizontal" style="height: 100%">
                <div>
                    <p>
                        Inner splitter :: left pane
                    </p>
                </div>
                <div>
                    <p>
                        Inner splitter :: center pane
                    </p>
                </div>
                <div>
                    <p>
                        Inner splitter :: right pane
                    </p>
                </div>
            </div>
        </div>
        <div>
            <p>
                Outer splitter : bottom pane (non-resizable, non-collapsible)
            </p>
        </div>
    </div>



</body>

</html>
