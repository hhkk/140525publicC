/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/3/13
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * create map of options - name value pairs
 * @param optionsString
 * @returns {{}}
 */
function parseOptions(optionsString) // string to map for then calling genOptionsURLStringFromMap
{

    alert("in here2");
    // from http://stackoverflow.com/questions/7790088/parse-option-string
    var s = 'Text="Andrey\'s Photos; Vacation";Font=Arial;Size=12;SingleQuoted=\'The dog said "Foo"\'';
    var re = /(.*?)=(?:"(.*?)"|'(.*?)'|([^;]*?))(?:;|$)/g;
    var match;

    var optionsMap = {}
    while(match = re.exec(s))
    {
        var string_val = match[2] || match[3] || match[4];
        var num_val = parseFloat(string_val);
        var val = isNaN(num_val) ? string_val : num_val;
        optionsMap[match[1].toLowerCase()] = val;
    }
    return optionsMap
}

function s(optionsMap)
{
    alert("n here");
    var optStr = ""
    for (var key in optionsMap) {
        optStr = optStr + [key, optionsMap[key]].join(":");
    }
    alert ("parsed opt map1 to [" + optStr + "]")
    return optStr
}

function UtilOptionParser2_genOptionsURLStringFromColonStr(optionsColonStr)
{
    console.log("in  UtilOptionParser2_genOptionsURLStringFromColonStr got [" + optionsColonStr+ "]")
    var optStr = ""
    var opts = optionsColonStr.split(" ")
    alert("opts.length:"+opts.length)
    alert("opts[0]:"+opts[0])
    for (var keyColonValueStr in opts)
    {
        var oneKeyValueArr = keyColonValueStr.split(":")
        if(oneKeyValueArr.length != 2)
            alert("invalid option setting:"+keyColonValueStr)
        var key = oneKeyValueArr[0];
        var value = oneKeyValueArr[1];

        //optStr = optStr + [key, optionsMap[key]].join(":");
        optStr = optStr + "xxd"+key+"%20"+value
    }
    alert("in  UtilOptionParser2_genOptionsURLStringFromColonStr parsed to url component [" + optStr + "]")
    return optStr
}


