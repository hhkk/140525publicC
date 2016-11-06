/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/3/13
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */

function testRegEx()
{
    //alert ("in testRegEx2");
    var str = "http://www.alessandrolacava.com/?code=ALE69";
    var regex = /code=(.*)/;
    var results = regex.exec(str);
    if(!results)  {
        console.log("no match2");
    }    else    {
        console.log(results[1]);
    }
}

function testRegEx2()
{
    //alert ("in testRegEx2");
    var str = "http://u" +
        "2d.co/todo?q=*";
    var regex = /\/.*\/(.*)\?/;
    var results = regex.exec(str);
    if(!results)  {
        console.log("no match2");
    }    else    {
        console.log(results[1]);
    }
}
    //  window.open("http://james.padolsey.com/javascript/regular-expressions-in-javascript-part-2/", windowName, "height=200,width=200");

    // http://www.alessandrolacava.com/capturing-groups-in-regular-expressions-regex-in-javascript/
    //The previous code extracts the string that follows the code= part of str. That string is captured in the first group of the RegEx,
    // that’s why I use results[1] to display it. When you utilise groups–through the use of parenthesis ()–you can refer to them using
    // indices, starting from 1. Indeed, at the index 0 you find the whole match. In the previous example, results[0] is equal to code=ALE69

testRegEx2()

function extractUrlsFromString()
{
    window.open("http://james.padolsey.com/javascript/regular-expressions-in-javascript-part-2/", windowName, "height=200,width=200");
}