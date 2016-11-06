/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/26/13
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */

function UtilData_dumpFileLines(dboPageDataTable__)
{


    alert ("in here mom")

    try {
        var idSeqBase = 0

        for (idSeqBase=0; idSeqBase < dboPageDataTable__.length; idSeqBase++)
        {
            var o = dboPageDataTable__[idSeqBase];
            var s = "o.dbid:" + o.dbid
            s = s + ", o.ageLetter:" + o.ageLetter
            s = s + ", o.lineMinusDateStr:" + o.lineMinusDateStr
            console.log(idSeqBase + ". " + s)
        }
    }
    catch (err) {
        alert ("error in /Users/hkon/sw/ustodo/110504utd/ustodo112/web-app/js/emit/emitJsonTable2.js")
        handleErr2("genHTMLtableData", err);
    }

//    var a = getPageData();
//    for (var i = 0; i < a.size; i++)
//    {
//        console.log (i + ". a:" + a[i].dbid)
//    }

}
