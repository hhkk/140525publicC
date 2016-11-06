/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 6/19/13
 * Time: 1:37 AM
 * To change this template use File | Settings | File Templates.
 */
function UtilKeyboard_setDomLeyHandler()
{
    // http://stackoverflow.com/questions/3369593/how-to-detect-escape-key-press-with-javascript

    //    $(document).keyup(function(e) {
    //
    //        if (e.keyCode == 27)
    //        {
    //            //alert ("gotDomEsc");
    //        }   // esc
    //    });

    document.onkeydown = function(evt)
    {
        //alert ( "in document.onkeydown");
        evt = evt || window.event; // escapehk
        if (evt.keyCode == 27) {
            //alert ("gotDomEsc2");
            singletonUtilLayout_layout_showAll_state.publicMethod_showMoreEastAndWest_orToggleWestIfAlreadyAllShown();

        }
    };

}
