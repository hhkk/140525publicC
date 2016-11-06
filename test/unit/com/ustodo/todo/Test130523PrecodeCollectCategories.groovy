package com.ustodo.todo

import com.ustodo.utilg.O
import grails.test.GrailsUnitTestCase

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
//@TestFor(ToDoController)
//@TestMixin(ControllerUnitTestMixin)

//@TestMixin([GrailsUnitTestMixin, ControllerUnitTestMixin, DomainClassUnitTestMixin])
//@TestFor(ProcessCommand)
//@TestMixin(GrailsUnitTestMixin)

class Test130523PrecodeCollectCategories extends GrailsUnitTestCase {

    //org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap params;
    //org.springframework.mock.web.MockHttpServletRequest mockRequest;

    void setUp() {
        //mockRequest = new org.springframework.mock.web.MockHttpServletRequest();
        // see http://stackoverflow.com/questions/11785708/how-do-i-unit-test-a-grails-service-that-uses-a-converter
        //java.util.LinkedHashMap.metaClass.asType = { Class c -> c.newInstance(delegate)
        //}
        //addConverter(java.util.LinkedHashMap)
        super.setUp();
    }

    void tearDown(){
        //java.util.LinkedHashMap.metaClass.asType = null

    }

    void testCollectCategs()
    {
        Map ajaxWebMap = null;

        try {   // see http://svn.codehaus.org/grails/tags/GRAILS_RELEASE_1_0_RC4/test/web/org/codehaus/groovy/grails/web/servlet/mvc/GrailsParameterMapTests.groovy
            def mockRequest = new org.springframework.mock.web.MockHttpServletRequest();
            mockRequest.addParameter("q", "*")
            //mockRequest.addParameter("a.b", "tester2")
            def params = new org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap(mockRequest);
            //assert params['a'] instanceof Map

            //params.put ("q", "tester");

            ajaxWebMap = ProcessCommand.ajax_getJSONTableData_ (  // ajaxWebMap  // returns  return new JSON(map).toString();
                    (Object) "ckckck",
                    (String) "caller.ajax_getJSONTableData",
                    (org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap) params,
                    -1); //

            // http://stackoverflow.com/questions/11785708/how-do-i-unit-test-a-grails-service-that-uses-a-converter

            O.o ("ajaxWebMap.toString():" + ajaxWebMap.toString());

            O.oNoFilter("pppppp:" + ajaxWebMap.alFileLines.size());

        } catch (Throwable t) {
            O.oerr 'error in unit test', t;
            t.printStackTrace();
        }
        O.oNoFilter("errass")
        def a = ajaxWebMap.alFileLines.size();
        assert  (a >  20000);
        O.oNoFilter("callerhkhhk1:"+ajaxWebMap.alFileLines.size())

        ajaxWebMap.alFileLines.eachWithIndex
        {
            it, i ->
            if (i % 10000 == 0)
                O.oNoFilter("callerhkhhk2:"+it)
        }



        //ajaxWebMap.arrayFlash
    }







    //    var line1xxcc = "";





    //        //        var topval = document.getElementById('txtUpper');
    //        //        alert ("topval:" + topval);
    //        //        var beginsWith = function(needle, topval){
    //        //            if(haystack.substr(0, needle.length) == needle){
    //        //                return true;
    //        //            }
    //        //            return false;
    //        //        }
    //        //
    //        alert("1in confirmAction"+document.getElementById('txtUpper').value);
    //        var topval = document.getElementById('txtUpper').value;
    //
    //        var cls = getClass(topval);
    //        alert("1topval:" + topval + ", cls:" + cls);
    //        var boo = false;
    //        alert("1.5test:" + boo);
    //        alert("2in confirmAction"+topval.startsWithhkhk("He"));
    //              alert("3in confirmAction"+topval.startsWith("He"));
    //        alert("4in confirmAction"+document.getElementById('txtUpper').value);
    //        if (document.getElementById('txtUpper').value.startsWith("sm"))
    //        {
    //            alert ("in sm confirm1");
    //            return confirm('Are you sure you want to send this message?');
    //        }
    //        else
    //        {
    //            alert ("in sm confirm2");
    //            return true;
    //        }
    //    }
    //





}
