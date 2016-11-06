/**
 * Created by henryms on 7/27/2014.
 */

function isContentEditable(el)
{
    return el.isContentEditable;
}

function keypressHandler(event)
{
    // WHICH ASCII
    //alert(getClass("event", event));
    if ( event.which == 13 ) {
        alert("enter pressed");
    }
    //$.print( event );

    // PREVENT DEFAULT
    if (false) {
        event.preventDefault();
    }
}
