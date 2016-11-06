package com.ustodo.todo

import com.ustodo.utilg.O
import grails.plugins.springsecurity.Secured

class Todo8Controller {

    def springSecurityService;

    public static boolean   profileroutput = true;
    //static def test = O.o("in static init again")
    // todo: clean this up - unused variables AND NOT NEEDED FOR AUTH?
    def authenticationService;

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"];  // http://grails.org/doc/2.3.x/ref/Controllers/allowedMethods.html

    /**
     *  index - initial delivery of the server - ISP file
     */

    @Secured(['ROLE_USER'])
    def index = {

        //O.oNoFilter(O.stringifyEachable("TDC.index top level map:" , params));
        if (!params.q)
            params.q     = "*"
        params.request = request;

        O.o("params.q:" + params.q );
        render(view: "todoview", model: params);
        //render(view: "i14_130717_TestWindowOpen", model: params);
    } // end index

    public static void main (String[] args)
    {
    }
}
