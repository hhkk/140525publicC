/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 5/27/13
 * Time: 3:17 AM
 * To change this template use File | Settings | File Templates.
 */




//function convertStringToTermSetAndArray(sentence, setExclude)
//{
//    var setTerms = {};
//    var arrTerms = [];
//    var idSeqBase = 0
//    var sentenceSplit = sentence.split(" ");
//
//    for (i=0; i < sentenceSplit.length; i++)
//    {
//        // FIRST GET CATEGS OUT OF THE WAY IN THE RSAVE AWAY THE CATEG FOR THIS HBK130525
//        if (!(sentenceSplit[i] in setExclude  || sentenceSplit[i] in setTerms))
//        {
//            arrTerms.push(sentenceSplit[i])
//            setTerms[sentenceSplit[i]]=true;
//            //alert("in convertStringToTermSetAndArray for sentence [" + sentence + "] adding term [" + sentenceSplit[i] + "]")
//            //alert("arrTerms.length:"+arrTerms.length)
//            //alert  ("categ:"+categ);
//        }
//        else
//        {
//
//            // alert  ("skipping word categ:" + sentenceSplit[i]);
//        }
//    }
//    //alert("in convertStringToTermSetAndArray for sentence [" + sentence + "] got term set [" + arrTerms    + "]")
//    return setTerms
//}

function convertStringToTermSetAndArray(sentence, setExclude)
{
    //var setTerms = {};
    var setTerms = new hkset();

    var arrTerms = [];
    var idSeq = 0
    var sentenceSplit = sentence.split(" ");
    if (setExclude == null) // to allow null and make code simpler below
        setExclude = new hkset();

    for (i=0; i < sentenceSplit.length; i++)
    {
        // FIRST GET CATEGS OUT OF THE WAY IN THE RSAVE AWAY THE CATEG FOR THIS HBK130525
        if (!(sentenceSplit[i] in setExclude  && !sentenceSplit[i] in setTerms))
        {
            arrTerms.push(sentenceSplit[i].trim())
            //setTerms[sentenceSplit[i]]=true;
            setTerms.add(sentenceSplit[i].trim());
            //alert("in convertStringToTermSetAndArray for sentence [" + sentence + "] adding term [" + sentenceSplit[i] + "]")
            //alert("arrTerms.length:"+arrTerms.length)
            //alert  ("categ:"+categ);
        }
    }
    //alert("in convertStringToTermSetAndArray for sentence [" + sentence + "] got term set [" + arrTerms    + "]")
    return {
        'set': setTerms,
        'arr': arrTerms
    };
}

/**
 * turn on and off rows matching a term set
 * @param setTermsMustHave
 */
function setVisibleAllArrFileLineRows() // for categ table mouseout
{
    //alert ("in setVisibleAllArrFileLineRows")
    for (var i = 0; i < getPageData().arrFileLines.length; i++)
    {
        getPageData().arrFileLines[i].visiblehk = true;
    } // for each table row
    getElementByIdHK('spanid_withinTableTest').innerHTML =  genHTMLtableData(
        latestCommand,
        getPageData().arrFileLines,
        false // ignore visible bit
    ); // uses pagedata
}



function convertFileLineToItsCateg(lineMinusDateStr) // HBK130525
{
    var idxMaxDelim = -1;
    for (i =0;i<CONST_ARR_CATEG_DELIMS.length; i++)
    {
        var idx=UtilString_lastIndexOf(lineMinusDateStr, CONST_ARR_CATEG_DELIMS[i]);
        if (idx > idxMaxDelim)
            idxMaxDelim = idx;
    }

    if (idxMaxDelim > 0 ) // if found something defining a categorizable entity
        return lineMinusDateStr.substr(0, idxMaxDelim).trim();
    else
        return null

}

function UtilIR_convertSetToLowerCase (setOfStrings) // HBK130525
{
    var arrStr = setOfStrings.keys();
    var setRtn = new hkset();
    for (i =0;i<arrStr.length; i++)
    {
        setRtn.add(arrStr[i].toLowerCase())
    }
    return setRtn;
}

var CONST_ARR_CATEG_DELIMS = [' / ', ' // ', ' /// ', ' -- '];
//var setCategDelimsToExclude = {};
var setCategDelimsToExclude = new hkset();
for (i =0;i<CONST_ARR_CATEG_DELIMS.length; i++)
{
    //alert ("initing")
    //setCategDelimsToExclude[CONST_ARR_CATEG_DELIMS[i]] = true
    setCategDelimsToExclude.add(CONST_ARR_CATEG_DELIMS[i], true)
}

