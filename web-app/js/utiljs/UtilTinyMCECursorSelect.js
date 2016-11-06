/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/25/13
 * Time: 6:56 AM
 * To change this template use File | Settings | File Templates.
 */


//<textarea id="test">Some text</textarea>

function moveCaretToEnd(el) {
    if (typeof el.selectionStart == "number") {
        alert ("in heere hk2")
        el.selectionStart = el.selectionEnd = el.value.length;

    } else if (typeof el.createTextRange != "undefined") {
        alert ("in heere hk3")
        el.focus();
        var range = el.createTextRange();
        range.collapse(false);
        range.select();
    }
}


//textarea.onfocus = function() {
//    moveCaretToEnd(textarea);
//
//    // Work around Chrome's little problem
//    window.setTimeout(function() {
//        var textarea = document.getElementById("test");
//        moveCaretToEnd(textarea);
//    }, 1);
//};
