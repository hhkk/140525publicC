/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/3/13
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */

function urlMatchController(s)
{
    //alert ("in testRegEx2");
    var regex = /\/.*\/(.*)\?/;
    var results = regex.exec(s);
    if(!results)  {
        return "";
    }    else    {
        return results[1];
    }
}


function buildAhref (text, url)
{
    return ("<a href='" + url + "'>" + text + "</a>");


}