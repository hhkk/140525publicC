/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/8/13
 * Time: 1:42 AM
 * To change this template use File | Settings | File Templates.
 */

var getKeys = function(obj){
    var keys = [];
    for(var key in obj){
        keys.push(key);
    }
    return keys;
}
var getKeysAndValues = function(obj){
    var keys = [];
    for(var key in obj){
        keys.push(key + "       ["+obj[key]+"]            ");
    }
    return keys;
}

Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] == obj) {
            return true;
        }
    }
    return false;
}


function isFunction(functionToCheck) {
    var getType = {};
    return functionToCheck && getType.toString.call(functionToCheck) === '[object Function]';
}

function isNull(o){
    return (null == o);
}

/**
 * see if a file exists on the server
 * http://stackoverflow.com/questions/11337304/is-it-possible-to-check-if-a-file-returns-a-404-error-if-its-on-a-different-ser
 * @param imageUrl
 * @param callback
 */
function checkExists(imageUrl, callback) {
    var img = new Image();

    img.onerror = function() {
        callback(false);
    };

    img.onload = function() {
        callback(true);
    };

    img.src = imageUrl;
}

//$(document).ready(function() {
//    checkExists('http://google.com/fake.jpg', function(exists) {
//        alert('Fake exists? ' + exists);
//    });
//
//    checkExists('http://www.lessons4living.com/images/penclchk.gif', function(exists) {
//        alert('Real exists? ' + exists);
//    });
//});

//alternative  http://stackoverflow.com/questions/3646914/how-do-i-check-if-file-exists-in-jquery-or-javascript

function UrlExists(url)
{
    var http = new XMLHttpRequest();
    http.open('HEAD', url, false);
    http.send();
    return http.status!=404;
}

function UrlExistsAlert(url)
{
    try {
        if (!UrlExists(url))
        {
            alert("url not exist :"+url)
        }else{
            alert("url exists :"+url)
        }
        return
    }
    catch (err) {
        handleErr("error in UrlExistsAlert", err);
    }
    finally {

    }

}


