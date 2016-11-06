/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 5/25/13
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */

var PageData_state_txtUpperEdited = false;
var calledCountLimiter1 = 0;
var rowClickedDbId = "";
var rowClickedIndexCurrent  = "";

var pageData_currentSearchString = null;

function areWeInModeEdit()  {
    var x = tinyMCE.get('txtUpper').getContent({format : 'text'}).trim();
    var y = (x != "");
    if (!y)
        PageData_state_txtUpperEdited = false;
    //alert ("y:" + y);
    return y && (rowClickedDbId != "" || PageData_state_txtUpperEdited)
    //var isTxtUpperEmpty =
    // empty else been edited else currently editing a row
    //return (!(UtilTinyMCE_getContentTinyMce('txtUpper', 'text') == '*') && (rowClickedDbId != "" || PageData_state_txtUpperEdited));

}

function pagedata() // Todo - I believe JS style wants camel case
{
    this.arrFileLines = null // eg lineMinusDateStr ?
    // this.arrFileLines[idSeqBase].visiblehk
    this.arrStrRawTextCategs = null // used in emit categ.js
    this.strFilterText = null
    this.elementFromArrFileLineJustdeletedForUndo = null;


    calledCountLimiter1++;
    if (calledCountLimiter1 > 1)
    {
        alert("error - called new pagedata > 1")
    }

    this.setArrFileLines = setArrFileLines;
    function setArrFileLines(arrFileLines)
    {
        //alert ("setting arrFileLines:" + arrFileLines)
        this.arrFileLines=arrFileLines;
    }




//    this.setSetCategs = setSetCategs;
//    function setSetCategs(setCategs)
//    {
//        this.setCategs=setCategs;
//    }

    this.setArrRawTextCategs = setArrRawTextCategs;
    function setArrRawTextCategs(arrStrRawTextCategs)
    {
        this.arrStrRawTextCategs=arrStrRawTextCategs;
    }



}

var privatePagedata = null;

function getPageData()
{
    if (privatePagedata == null)
    {
        privatePagedata = new pagedata()
        $('body').data('pagedata', privatePagedata);  // store in element
    }

    //alert ("getpagedata returning:"+privatePagedata)
    return privatePagedata;
}

// lineMinusDateStr
// dbid
function setPropFileLine(idSeqBase, key, value)
{
    getPageData().arrFileLines[idSeqBase][key] = value
//    var textareaContentPreHtml = getPageData().arrFileLines[idSeqBase].textareaContentPreHtml

}

// eg dbid

function getPropFileLine(idSeqBase, key)
{
    //alert ("in get")
    return getPageData().arrFileLines[idSeqBase][key];
}


//// from http://www.htmlgoodies.com/html5/tutorials/create-an-object-oriented-javascript-class-constructor.html#fbid=EEs8Sw4Kdtt
//
//var pagedata = Class({
//    initialize: function(name, age) {
//        this.name = name;
//        this.age  = age;
//    },
//    toString: function() {
//        return "My name is "+this.name+" and I am "+this.age+" years old.";
//    }
//});
//
//
//var Class = function(methods) {
//    var klass = function() {
//        this.initialize.apply(this, arguments);
//    };
//
//    for (var property in methods) {
//        klass.prototype[property] = methods[property];
//    }
//
//    if (!klass.prototype.initialize) klass.prototype.initialize = function(){};
//
//    return klass;
//};
