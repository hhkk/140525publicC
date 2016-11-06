/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 5/2/13
 * Time: 4:09 AM
 * To change this template use File | Settings | File Templates.
 */



function deleteOneRow(idSeqBase)
{
    //alert ("in deleteOneRow deleting idSeqBase:"+ idSeqBase)
    //alert ("in deleteOneRow deleting jsonTableArrayDataCurrent[idSeqBase].dbid :"+ pagedata[idSeqBase].dbid)

    //bulkOperationCommon_onSelected( "_"+getPageData()[idSeqBase].dbid, '/ustodo/todo/ajax_deleteButton')
    // alert ("in delete one row")
    if (getPageData().status_deleting == true)
    {
        alert ("ErrorCode:1, please email 'error code 1' to ustodo.com@gmail.com");
        return;
    }
    else if (getPageData().status_deleting == false || getPageData().status_deleting == null)
    {
        //alert ("got a false.");
    }
    else
    {
        alert ("ERROR: please email ustodo.com@gmail.com - error");
        return
    }

    // works!! alert ("deleting:" + getPageData()[idSeqBase].lineMinusDateStr);
    getPageData().elementFromArrFileLineJustdeletedForUndo = getPageData().arrFileLines[idSeqBase] // for undo delete record


    jQuery.ajax({type:'POST',
        //data:'{autocomp=' + sDbIdListToBulkOperateOn_underscoreDelimited + '}',
        data:'autocomp=' + "_"+getPageData().arrFileLines[idSeqBase].dbid + '',
        //data:'{autocomp=' + sDbIdListToBulkOperateOn_underscoreDelimited + '}',
        url:'/todo/ajax_deleteButton',
        success:function (data, textStatus) {
            UtilFadesAndPopups_message("Deleted record", 150    );
            //alert("delete done got back data:" + data)
            //alert("delete done got back textStatus:" + textStatus)
            jQuery('#spanIdBulkOpResult').html(data);
            emitButton('spanidundobutton', "undo last delete", 'eventhandlerOnclickButton_undoDelete', 'click to see what was deleted and be able to copy it    ');
            //alert ("saving for undo:"+getPageData().elementFromArrFileLineJustdeletedForUndo.lineMinusDateStr);
            getPageData().arrFileLines.splice (idSeqBase,1);

            getElementByIdHK('spanid_withinTableTest').innerHTML = genHTMLtableData (
                latestCommand,
                getPageData().arrFileLines,
                true // ignore visible bit
            );
            if (synch_strFilterWordsInOriForm)
                hideRowsNotContainingFullTermSet("caller_DeleteOneRow", synch_strFilterWordsInOriForm, true, false, false, true, compareModes.startsWith)

            refreshCategPageDataAndHTMLbasedOnArrFileLineContent();

        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert ("in deleteOneRow error");
            jQuery('#spanIdBulkOpResult').html('Unable to delete record:'+XMLHttpRequest.responseText);
        }, complete:function (XMLHttpRequest, textStatus) {

        }});

    getPageData().status_deleting = false;

    //alert ("in deleteOneRow post bulkOperationCommon_onSelected ")
}




function crudArchiveToggleOneRow(idSeqBase, currentlyArchived) // cloned from crudPromoteOneRow
{
    //alert ("in crudArchiveOneRow currentlyArchived:" + currentlyArchived);
    jQuery.ajax({type:'POST',
        //data:'{autocomp=' + sDbIdListToBulkOperateOn_underscoreDelimited + '}',
        data:'idListUnderscoreDelimited=' + "_"+getPageData().arrFileLines[idSeqBase].dbid + '',
        //data:'{autocomp=' + sDbIdListToBulkOperateOn_underscoreDelimited + '}',
        url:'/todo/ajax_crudArchiveToggleOneRow',
        success:function (data, textStatus) {
            //alert("delete done")
            jQuery('#spanIdBulkOpResult').html(data);

            //alert ("idseq:" + idSeqBase)
            //var temp = getPageData()[idSeqBase]
            if (!currentlyArchived)
            {
                getPageData().arrFileLines[idSeqBase].dboDbFlr.archived = true
                //getPageData().splice (idSeqBase,1) // move up from here
            }
            else
                getPageData().arrFileLines[idSeqBase].dboDbFlr.archived = false
            //getPageData().splice(0,0,temp) // insert to here
            getElementByIdHK('spanid_withinTableTest').innerHTML = genHTMLtableData(
                latestCommand,
                getPageData().arrFileLines,
                true // ignore visible bit
            );
            emitButton('#spanidundobutton', "undo promote")
            refreshCategPageDataAndHTMLbasedOnArrFileLineContent();
        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            jQuery('#spanIdBulkOpResult').html('Unable to promote record:'+XMLHttpRequest.responseText);
        }, complete:function (XMLHttpRequest, textStatus) {
        }});
} // end fn crudArchiveOneRow

function crudPromoteOneRow(idSeqBase)
{

    //        var pagedata_local = getPageData()
    //        //  alert ("in promoteOneRow deleting id:"+ jsonTableArrayDataCurrent[idSeqBase].dbid)
    //        bulkOperationCommon_onSelected( "_"+pagedata_local[idSeqBase].dbid, '/ustodo/todo/ajax_touchSingleButton')

    //alert ("in promote  one row")
    jQuery.ajax({type:'POST',
        //data:'{autocomp=' + sDbIdListToBulkOperateOn_underscoreDelimited + '}',
        data:'idListUnderscoreDelimited=' + "_"+getPageData().arrFileLines[idSeqBase].dbid + '',
        //data:'{autocomp=' + sDbIdListToBulkOperateOn_underscoreDelimited + '}',
        url:'/todo/ajax_touchSingleButton',
        success:function (data, textStatus) {
            //alert("delete done")
            jQuery('#spanIdBulkOpResult').html(data);

            //alert ("idseq:" + idSeqBase)
            var temp = getPageData().arrFileLines[idSeqBase]
            getPageData().arrFileLines.splice (idSeqBase,1) // move up from here
            getPageData().arrFileLines.splice(0,0,temp) // insert to here
            getElementByIdHK('spanid_withinTableTest').innerHTML = genHTMLtableData (
                latestCommand,
                getPageData().arrFileLines,
                true // ignore visible bit
            );
            emitButton('#spanidundobutton', "undo promote")
            refreshCategPageDataAndHTMLbasedOnArrFileLineContent();

        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            jQuery('#spanIdBulkOpResult').html('Unable to promote record:'+XMLHttpRequest.responseText);
        }, complete:function (XMLHttpRequest, textStatus) {
        }});


//        var temp = pagedata_local[idSeqBase]
//        pagedata_local.splice (idSeqBase,1) // move up from here
//        pagedata_local.splice(0,0,temp) // insert to here
//        emitJsonTables(pagedata_local)
}
