/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 4/7/13
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */

    var timerID = null;
    var timerRunning = false;

    function stopclock() {
        if(timerRunning)
        clearTimeout(timerID);
        timerRunning = false;
        }

    function startclock(){
        stopclock();
        showtime();
        }

    function showtime() {
        var now = new Date()
        var hours = now.getHours()
        var minutes = now.getMinutes()
        var seconds = now.getSeconds()
        var timeValue = "" + ((hours > 12) ? hours - 12 : hours)
        timeValue  += ((minutes < 10) ? ":0" : ":") + minutes
        timeValue  += ((seconds < 10) ? ":0" : ":") + seconds
        timeValue  += (hours >= 12) ? " P.M." : " A.M."
        //document.clock.face.value = timeValue
        //timerID = setTimeout("showtime()",1000)
        //timerRunning = true
        }


//        </SCRIPT>
//            <BODY onLoad="startclock()">
//                <!-------------------------------------------------------------------------------------------->
//                    <form name="clock" onSubmit="0">
//                        <INPUT TYPE="text" NAME="face" SIZE=11 VALUE ="....Initializing....">
//                        </form>
