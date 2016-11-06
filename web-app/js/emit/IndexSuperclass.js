/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/3/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */

var iCallCount_updateStatus = 0
var lastms = 0;

function updateStatus(status)
{
    var now = Date.now();
    var elapsed = now - lastms
    if(lastms==0)
        elapsed=0;
    lastms = now;

    iCallCount_updateStatus++;
    var s = '[' + iCallCount_updateStatus + '] status [' + status + '] ms since last log entry [' + elapsed + '] '

    //document.getElementById('id_status').innerHTML = s
    getElementByIdHK('id_status_log').innerHTML =  "<br>" + s + document.getElementById('id_status_log').innerHTML
}

