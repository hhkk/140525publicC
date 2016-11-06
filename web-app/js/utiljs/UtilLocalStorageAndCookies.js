/**
 * Created by henryms on 6/3/2014.
 */

// http://ejohn.org/blog/dom-storage/%20%20%20http://diveintohtml5.info/storage.html

//not tester
function supports_html5_storage() {
    try {
        return 'localStorage' in window && window['localStorage'] !== null;
    } catch (e) {
        return false;
    }
}