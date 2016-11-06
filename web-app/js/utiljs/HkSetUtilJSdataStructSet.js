/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 5/27/13
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */

// from http://stackoverflow.com/questions/7958292/mimicking-sets-in-javascript
//Working demo: http://jsfiddle.net/jfriend00/5Rub3/
//
//    Constructors:
//
//        var myData1 = new set();
//var myData2 = new set({first: 1, second: 2});
//This has the following methods:
//
//    add(key, value)                 // adds or updates a key in the set
//add({key: value, key2: value})  // add or updates multiple keys in the set
//get(key)                        // returns value or undefined if key doesn't exist
//remove(key)                     // removes a key from the set
//remove(["a", "b"]);             // removes all keys in the passed in array
//remove("a", "b", ["first", "second"]);   // removes all keys specified
//has(key)                        // returns true/false if key exists in the set
//isEmpty()                       // returns true/false for whether set is empty
//keys()                          // returns an array of keys in the set
//clear()                         // clears all data from the set



//function hkset(callerid, initialData) {
//    // can pass initial data for the set in an object
//    alert("in hkset(initialData) callerid:"+callerid);
//    this.data = initialData || {};
//}

function hkset(initialData) {
    // can pass initial data for the set in an object
    //  alert("in hkset(initialData) callerid:"+callerid);
    this.data = initialData || {};
}

hkset.prototype =
{
    add: function(key, val) {
        //oooo ("0000000000000000000  in here hk:"+key)
        if (typeof key === "object") {
            //console.log ("in hkset.add yes typeof key === object")
            for (var index in key) {
                if (key.hasOwnProperty(index)) {
                    this.add(index, key[index]);
                }
            }
        } else {
            var currentCount = this.data[key]

            if (!currentCount)
                currentCount = 1
            else
            {
                currentCount = currentCount + 1
                //console.log("in hkset.add######################## categ builder increment <<"+ key +">> to [" + currentCount + "]");
            }
            //console.log ("in hkset.add key[" + key +  "] currentCount [" + currentCount +"]");


            this.data[key] = currentCount;
        }
    },
    get: function(key) {
        return this.data[key];
    },
    remove: function(key) {
        // can be one or more args
        // each arg can be a string key or an array of string keys
        var item;
        for (var j = 0; j < arguments.length; j++) {
            item = arguments[j];
            if (typeof key === "string") {
                delete this.data[item];
            } else if (item.length) {
                // must be an array of keys
                for (var i = 0; i < item.length; i++) {
                    delete this.data[item[i]];
                }
            }
        }
    },
    toString: function() {
        //alert ("in set tostring")
        return Object.keys(this.data).toString();
    },
    has: function(key) {
        return this.data.hasOwnProperty(key);
    },
    isEmpty: function() {
        for (var key in this.data) {
            if (this.data.hasOwnProperty(key)) {
                return false;
            }
        }
        return true;
    },
    keys: function() {
        return Object.keys(this.data);

        // see http://stackoverflow.com/questions/684672/loop-through-javascript-object
            //            for (var key in p) {
            //                if (p.hasOwnProperty(key)) {
            //                    alert(key + " -> " + p[key]);
            //                }
        //return this.data.keys()
    },
    clear: function() {
        this.data = {};
    }
};
