window.raise = function(msg)
{
    throw new Error(msg)
}


function handleErr(err) {
    return handleErr2("no calledID provided", err)
}

function handleErr2(errHadlerId, err) {
    handleErr3(errHadlerId, err, false)
}
function handleErr3(errHadlerId, err, toLogOnlyOrActualAlertTF) {
    //console.log("errHadlerId:" + errHadlerId + ", stack:" + err.stack);
    // console.log("errHadlerId:" + errHadlerId );
    oooo ("in handleErr3 error. errHadlerId ["+errHadlerId+"] err [" + err + "]")
    // from http://www.reddit.com/r/javascript/comments/1k3cwb/consolelog_development_vs_live_best_practices/
    var callername = function () {
        var caller = arguments.callee.caller;
        while (caller && !caller.name) caller = caller.caller;
        if (caller) return caller.name;
        return "______";
    };
    oooo("  handleErr3x callername:"+callername)

    var txt = "There was an error on this page. The next dialog will explain in more detail if you want it.  Else just hit OK and try your luck.  \n\n";
    txt += "1a Err.message [" + err.message + "]\n\n";
    txt += "2a This message also on concole. Click OK to continue.\n\n";
    oooo ("in handleErr2 errmsg thus far:" + txt);
    window.raise("hk says window.raise error happened:" + txt) // this does get shown

    if (!toLogOnlyOrActualAlertTF)
    {
        // DUP OF ABOVE
        alert ("in handleErr2 error. errHadlerId ["+errHadlerId+"] err [" + err + "]")
        var txt = "There was an error on this page. The next dialog will explain in more detail if you want it.  Else just hit OK and try your luck.  \n\n";
        txt += "1a Err.message [" + err.message + "]\n\n";
        txt += "2a This message also on concole. Click OK to continue.\n\n";
        alert("in handleErr2 errmsg thus far:" + txt);
        window.raise("hk says window.raise error happened:" + txt) // this does get shown
    }
}
function handleErr(callerid, err) {
    handleErr2(callerid, err)
}
