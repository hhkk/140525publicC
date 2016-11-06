/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 4/19/13
 * Time: 4:49 AM
 * To change this template use File | Settings | File Templates.
 */

    // from http://www.webdeveloper.com/forum/showthread.php

var customLog =
{
    oriLog : '',
    Log : function()
    {
        // create string to display
        var displaystring = '';
        for(var i = 0, len = arguments.length; i < len; i++)
        {
            displaystring += arguments[i];
            if( i + 1 != len) displaystring += ', ';
        }
        alert(displaystring);
        //customLog.oriLog(arguments);
    }
}



//window.onload = function()
function UtilConsole_initCustomLog()
{
    if(console != null)
    {
        customLog.oriLog = console.log;
        console.log = customLog.Log;
    }

    //console.log('test 1', 'test 2');
}
