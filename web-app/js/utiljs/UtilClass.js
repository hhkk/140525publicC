/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/3/13
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */



function getClassSub (desc, obj)
{

    try {
        s = "\r\n\r\n"
        if (obj === null)
            s = s + " getClassSub a says desc [" + desc + "] obj passed in is null. ";
        if (obj === undefined)
            s = s + " getClassSub a says desc [" + desc + "] obj passed in is undefined. ";
        else {
            s = s + " getClassSub a says desc [" + desc + "]. ";
            s = s + "\r\n a.0b obj.toString [" + obj.toString() + "], ";

            s = s + "\r\n a.1 typeof obj [" + typeof obj + "], ";

            s = s + "\r\n b getClassSub obj.prototype string [" + Object.prototype.toString.call(obj) + "]";

            s = s + "\r\n c obj.prototype parsed [" + Object.prototype.toString.call(obj).match(/^\[object\s(.*)\]$/)[1] + "] ";

            s = s + "\r\n d obj.constructor parsed [" + obj.constructor + "] ";


            var properties = [];
            s = s + ("\r\n e typeof properties [" + typeof properties + "]");

            var i = 0;
            for (var key in obj) {
                i++;
                //if (obj.hasOwnProperty(key) && typeof obj[key] !== 'function') {
                //if (obj.hasOwnProperty(key) ) {
                if (typeof obj[key] !== 'function')
                    key = "++:" + key;
                else
                    key = "--:" + key;
                properties.push(key);
                if (key == 'srcElement') // object Window
                {
                    alert (i + ". found prop srcElement[" + getClassSub("recCall", obj.srcElement) + "]"); // shows ageLetter etc
                    if (obj['srcElement'] == null) {
                        alert (i + ". is null!!!!!!!!!!!!!!!!!!!!");
                    }
                    else {
                        alert ("himom"); // shows ageLetter etc
                        //alert ("found prop srcElement[" + getClassSub("recCall", obj['srcElement']) + "]"); // shows ageLetter etc
                    }
                }
                else if (key == 'tSAVORIGINALACTIVETINYMCE') {
                    //alert ("found prop tSAVORIGINALACTIVETINYMCE[" + getClassSub("recCall on tSAVORIGINALACTIVETINYMCE", obj.tSAVORIGINALACTIVETINYMCE) + "]"); // shows ageLetter etc
                }
                //alert ("found prop[" + key + "]"); shows ageLetter etc
                //}
            }

            s = " getclass desc [" + desc + "] " + s + "(\r\n PROPERTIES found [" + properties.length + "] keys in object keys are <<" + properties.join(", ") + ">>, ";
        }

        var cons = ", this.constructor:" + this.constructor

        //oalert ("in getclass: $(this).prototype.toString()))" + ($(this).prototype.toString()));
        if (typeof obj === "undefined")
            s = s + " UsToDo says this class desc [" + desc + "] is undefined. " + cons;
    } catch (e) {
        alert ("error in getClass:e:" + e)
    }


    return s;

}  // function getClassSub (desc, obj)



function getClass(desc, obj)
{

    var s = desc + "\r\n\r\n";

    //s = s + "obj.constructor [" + obj.constructor + "], ";

    if (obj)
    {
        s = s + "\r\n\r\ngetclass1 obj:" + getClassSub (desc, obj)
        s = s + "\r\n\r\ngetclass2 parent:" + getClassSub ("obj.parent:", obj.parent )
    }
    else
    {
        s = s + "\r\n\r\nskip getclass1 and getclass2 as obj is NOT";
    }

    s = s + "\r\n\r\ngetclassmain3 this:" + getClassSub ("this:", this )

    //s = s + "this.constructor [" + this.constructor + "], ";
    //s = s + "this.parent.constructor [" + this.parent.constructor + "], ";

    // see http://stackoverflow.com/questions/1249531/how-to-get-a-javascript-objects-class
    //        see also Object.getPrototypeOf(a);
    //        Depending on what you need #getClass() for, there are several options in JavaScript:
    //
    //        typeof()
    //                instanceof
    //                func.prototype, proto.isPrototypeOf
    //        obj.constructor

    // TODO CONSIDER THESE:
    //oalert ("$(this).getName():" + $(this).getName());
    //oalert ("getClass($(this).parent())):" + getClass($(this).parent()));
    //oalert ("getClass($(this).parent())):" + $(this).constructor);
    //oalert ("this.class:" + this.class);
    //oalert ("this.class:" + this.class);
    //oalert ("this.constructor:" + this.constructor);
    return s;

} // function getClass(desc, obj)


