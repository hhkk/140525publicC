/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/3/13
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */



/**
 * add endswith to string prototype
 */
if (typeof String.endsWith !== 'function') {
    String.prototype.endsWith = function (suffix) {
        return this.indexOf(suffix, this.length - suffix.length) !== -1;
    };
}




function UtilString_replaceplusw2bForUrlEncode(str) {
    //alert ("set a3 url as [" + str + "]")
    var sori = str;

    str = str.replace(/%/g,'%25')
    str = str.replace(/ /g,'%20')
    str = str.replace(/\+/g,'%2B')
    str = str.replace(/#/g,'%23')
    str = str.replace(/\:/g,'%3A')
    //str = str.replace("\:",'%3A')
    str = str.replace(/\\/g,'%5C')
    str = str.replace(/@/g,'%40')
    str = str.replace(/\$/g,'%24')
    str = str.replace(/\^/g,'%5E')
    str = str.replace(/&/g,'%26')
    str = str.replace(/{/g,'%7B')
    str = str.replace(/}/g,'%7D')
    str = str.replace(/\[/g,'%5B')
    str = str.replace(/\]/g,'%5D')
    str = str.replace(/\"/g,'%22')
    str = str.replace(/;/g,'%3B')
    str = str.replace(/</g,'%3C')
    str = str.replace(/>/g,'%3E')
    str = str.replace(/,/g,'%2C')
    str = str.replace(/\//g,'%2F')
    str = str.replace(/\?/g,'%3F')
    str = str.replace(/\=/g,'%3D')
    str = str.replace(/\|/g,'%7C')
//    alert ("set  s [" + sori + "] to be url [" + str + "]")
    //                                   !@  #  $  %  ^  &  *()_+  {  }  [  ]  :  "  ;  '<  >  ,  ./  ?  \  |  =  -           !@#$%^&*()_+{}[]:";'<>,./?\|=-
    http://translate.google.com/#auto/pl/!%40%23%24%25%5E%26*()_%2B%7B%7D%5B%5D%3A%22%3B'%3C%3E%2C.%2F%3F%5C%7C%3D-
    return str;
    // see also
    // "some+multi+word+string".replace(/\+/g,' ') from http://stackoverflow.com/questions/13574980/jquery-replace-all-instances-of-a-character-in-a-string


}
function UtilString_replaceAll(str, find, replace) {
    return str.replace(new RegExp(find, 'g'), replace);
    // see also
    // "some+multi+word+string".replace(/\+/g,' ') from http://stackoverflow.com/questions/13574980/jquery-replace-all-instances-of-a-character-in-a-string


}

// untested
function convertStringToHex(str) {
    var hex = '';
    for(var i=0;i<str.length;i++) {
        hex += ''+str.charCodeAt(i).toString(16);
    }
    return hex;
}



/**
 * convert string to ascii - a debugging aid
 */
    function convertStringToAsciiCommaSeparatedString(s)
{
    //alert('in stringify')
    var a = s.split('')
    var d = ''
    var i = 1;
    a.forEach(function(item)
    {
        //alert ("in loop")
        d = d + ", [" + i + ":" + item + ":" + s.charCodeAt(i-1) + "]"

        i = i +1
    });
    //alert ("a[len-1]:" + a[a.length-1])
    //alert ("a[len-2]:" + a[a.length-2])
    return d;
}

/**
 * convert html spsecial characters to standard ascii equivalent
 * a runtime tool to allow strings to be compared correctly
 * e.g., &nbsp; vs " " 32 vs 160 ascii
 */
function unHTMLifyString(s)
{
    var a = s.split('')
    var i = 0;
    a.forEach(function(item)
    {
        if (s.charCodeAt(i) == 160)
        {
            //alert ("converting i[" + i + "]")
            a[i] = ' '
        }
        i = i +1
    });
    var unHTMLifiedString = a.join('');
    //alert('in unHTMLifyString from ['+ stringify(s) +'] to [' + stringify(unHTMLifiedString) + ']')
    return unHTMLifiedString ;
//        return s;
}

function trimTrailingNbspWithSpace(s)
{
    var lenpre;
    var str;
    do {
        lenpre = s.length;
        str = s.replace(new RegExp('&nbsp;' + '$'), '');
        lenpost = str.length;
        //alert ("in trimmer");
    } while (lenpre != lenpost)
    return str

}



function endswithhk_includingHtmlConversion(s1, s2)
{
    //alert('in endswithhk2 START s1 [' + s1 + '] s2 [' + s2 + ']')
    var a = s1.split('')
    var b = s2.split('')

    var alen = a.length
    var blen = b.length

    if (alen < blen)
        return false;

    var astart = alen  - blen

    //var i = 0
    //b.forEach(function(item)
    for (var i = 0; i < b.length; i++)
    {
        //alert ("in loop")
        //alert('in endswithhk2 compare s1 [' + a[astart + i)] + '] vs [' + b[i] + ']')

        var c2 = s2.charCodeAt(i), c1 = s1.charCodeAt(astart + i)
        if (true) // convert160To32
        {
            if (c1 == 160)
                c1 = 32
            if (c2 == 160)
                c2 = 32
        }

        //alert('in endswithhk2 [' + s1 + '] vs [' + s2 + '] compare pos [' + i + '] s1 [' + s1.charCodeAt(astart + i) + '->'+c1+'.' +  a[astart + i] + '] vs [' + s2.charCodeAt(i) +  '->'+c2+'.' + b[i] + ']')
        if (c2 != c1)
        {
            //alert('in endswithhk2  [' + s1 + '] vs [' + s2 + '] FALSE s1 [' + s1 + '] s2 [' + s2 + ']')
            return false;
        }
    }
    //alert('in endswithhk2  [' + s1 + '] vs [' + s2 + '] TRUE s1 [' + s1 + '] s2 [' + s2 + ']')
    return true;
}






function UtilString_lastIndexOf(s, s2)
{
    return s.lastIndexOf(s2);
}

function UtilString_getAllUpToLastIndexOf(s, s2)
{
    return s.substr(0, UtilString_lastIndexOf(s, s2))
}

function UtilString_contains(s, s2)
{
    var t = (UtilString_lastIndexOf(s, s2) >= 0)
    //console.log ( "UtilString_contains testing s [" + s + "] contains s2 [" + s2 + "] t [" + t + "]");

    return t
}


UtilString_startsWith = function( value, pattern ) {
    return value.indexOf( pattern ) === 0;
};















