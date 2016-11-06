/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/3/13
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
// help:jquery instr / selector // dot class (all) and pound id  # .


// from http://stackoverflow.com/questions/1720320/how-to-dynamically-create-css-class-in-javascript-and-apply
testme()
{
    $.injectCSS({
        //"#test": {
        ".": {
            height: 123
        }
    });
}
