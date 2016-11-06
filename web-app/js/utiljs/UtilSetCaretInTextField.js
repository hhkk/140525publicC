/**
 * Created by henryms on 7/27/2014.
 */
    // 2.5 SET CURSOR POS
function setCaret(n) {
    var el = document.getElementById("contenteditableeditor");
    var range = document.createRange();
    var sel = window.getSelection();
    range.setStart(el.childNodes[3], n);
    range.collapse(true);
    sel.removeAllRanges();
    sel.addRange(range);
    el.focus();
    //alert ("and end");
}

// from http://stackoverflow.com/questions/4233265/contenteditable-set-caret-at-the-end-of-the-text-cross-browser/4238971#4238971
function placeCaretAtEnd(el) {
    el.focus();
    if (typeof window.getSelection != "undefined"
        && typeof document.createRange != "undefined") {
        var range = document.createRange();
        range.selectNodeContents(el);
        range.collapse(false);
        var sel = window.getSelection();
        sel.removeAllRanges();
        sel.addRange(range);
    } else if (typeof document.body.createTextRange != "undefined") {
        var textRange = document.body.createTextRange();
        textRange.moveToElementText(el);
        textRange.collapse(false);
        textRange.select();
    }
}

