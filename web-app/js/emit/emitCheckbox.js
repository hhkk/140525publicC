function emitCheckbox() {
    alert ("in emitCheckbox")
    try {
        var arrayTableRowsHtml = [];
        arrayTableRowsHtml.push ("All")
        arrayTableRowsHtml.push ("<input type='checkbox'")
        arrayTableRowsHtml.push ("onClick='checkAll()'")
        arrayTableRowsHtml.push ("unchecked")
        arrayTableRowsHtml.push ("value='hkhk'")
        arrayTableRowsHtml.push ("title='Select all or none of the checkbox list below'")
        arrayTableRowsHtml.push ("style='vertical-align:middle; position:relative;top:1px;'")
        arrayTableRowsHtml.push ("/>")
//        arrayTableRowsHtml.push ("<span id='id_status'></span>");
        var s = arrayTableRowsHtml.join(" ")
        document.getElementById('testDynamicContent1').innerHTML = s
    }
    catch (err) {
        handleErr(err);
    }
}


