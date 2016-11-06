/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 6/8/13
 * Time: 4:53 AM
 * To change this template use File | Settings | File Templates.
 */
function UtilFadesAndPopups_message (s, t)
{
    //alert ("in here hk t:" + t);
    $messageCont = $('<div class="message_cont">');
    $message = $('<div>'+s+'</div>').hide();
    $messageCont.append($message);
    //alert ("in here hk2");
    //$('body').prepend($messageCont);
    $('#spanid_status').prepend($messageCont);
    //alert ("in here hk3");
    $message.fadeIn(200, function() {
        //alert ("in here hk4");
        setTimeout(function(){
            //alert ("in here hk5");
            $messageCont.fadeOut(t);
            //alert ("in here hk6");
        }, t)
    })
}
//alert ("in here hk7");

//several callers
function messageUstodoSave()
{
    $('#spanid_base_saved').fadeIn(0, function() {
        //alert ("fade in slow complete")
    });
    getElementByIdHK('spanid_base_saved').innerHTML =  " savedhk3 " ;

    $('#spanid_base_saved').fadeOut(1100, function() {
        //alert ("fade in slow complete")
    });

}
