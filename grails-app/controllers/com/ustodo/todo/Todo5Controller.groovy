package com.ustodo.todo

//purpose: working angular get from server
// consider also cross-domain retrieval using JSONP?
// http://u2d.co/todo5

import com.ustodo.utilg.O
import grails.plugins.springsecurity.Secured

class Todo5Controller {

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
        render(view: "140713AngularGetAjaxToGrailsServerWorks", model: params);
        //render(view: "i14_130717_TestWindowOpen", model: params);
    } // end index




    //@Secured(['ROLE_USER'])

    // see 140526TestAngularGetNotAjax.html
    def ajax_getTestAngularStyleGet =
        {
            try
            {
                O.o("in ajax_getTestAngularStyleGet");
                ArrayList arrayFlash = new ArrayList();
                arrayFlash << "Caller [caller.ajax_getJSONTableData]";

                java.util.LinkedHashMap ajaxWebMap = new java.util.LinkedHashMap();
                ajaxWebMap.put("A", "1");
                ajaxWebMap.put("B", "2");

                def jsonStr = new grails.converters.JSON(ajaxWebMap).toString();
                //O.oNoFilter("ajax_getJSONTableData jsonStr:"+jsonStr);

                render ajaxWebMap as grails.converters.JSON
            } catch ( Exception e) {
                O.or "fail1b", e          //def alFileLines = map.alFileLines
            }
        } // ajax_getJSONTableData














    public static void main (String[] args)
    {
    }
}
