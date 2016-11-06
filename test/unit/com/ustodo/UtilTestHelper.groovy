package com.ustodo

import com.ustodo.todo.ProcessCommand
import com.ustodo.utilg.UtilMongo
import grails.test.GrailsUnitTestCase
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.mock.web.MockHttpServletRequest;


class UtilTestHelper extends GrailsUnitTestCase {
    //mockRequest = new org.springframework.mock.web.MockHttpServletRequest();
    // see http://stackoverflow.com/questions/11785708/how-do-i-unit-test-a-grails-service-that-uses-a-converter
    //java.util.LinkedHashMap.metaClass.asType = { Class c -> c.newInstance(delegate)
    //}
    //addConverter(java.util.LinkedHashMap)

    // JSON
    // http://stackoverflow.com/questions/11785708/how-do-i-unit-test-a-grails-service-that-uses-a-converter

    //mockRequest = new org.springframework.mock.web.MockHttpServletRequest();
    // see http://stackoverflow.com/questions/11785708/how-do-i-unit-test-a-grails-service-that-uses-a-converter
    //java.util.LinkedHashMap.metaClass.asType = { Class c -> c.newInstance(delegate)
    //addConverter(java.util.LinkedHashMap)
//@TestFor(ToDoController)
//@TestMixin(ControllerUnitTestMixin)

//@TestMixin([GrailsUnitTestMixin, ControllerUnitTestMixin, DomainClassUnitTestMixin])
//@TestFor(ProcessCommand)
//@TestMixin(GrailsUnitTestMixin)


    /**
     * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
     */

    public static String uthUserUnitTestUser = "userUnitTestUser";

    // see http://svn.codehaus.org/grails/tags/GRAILS_RELEASE_1_0_RC4/test/web/org/codehaus/groovy/grails/web/servlet/mvc/GrailsParameterMapTests.groovy
    void setUp() {
        super.setUp();
    }

    void tearDown(){
    }


    private static GrailsParameterMap uthBuildMockParams_q(String qCommandString)
    {
        def mockRequest = new MockHttpServletRequest();
        mockRequest.addParameter("q", qCommandString);
        new GrailsParameterMap(mockRequest);
    }

    protected static java.util.LinkedHashMap uthProcessCommand(String qCommandString, String user)
    {
        GrailsParameterMap params = uthBuildMockParams_q(qCommandString);
        ArrayList arrayFlash = new ArrayList();
        arrayFlash << "Caller [caller.ajax_getJSONTableData]";
        ProcessCommand.processCommand (
                "caller.ajax_getJSONTableData",
                user,
                params,// -------------
                arrayFlash //mockflash
        );

    }

    protected static List uthGetDbIds_UtdSearchRecords(String qCommandString, String user)
    {
        ArrayList arrayFlash = new ArrayList();
        arrayFlash << "Caller [caller.ajax_getJSONTableData]";

        LinkedHashMap ajaxWebMap = uthProcessCommand(qCommandString, user);

        def list = new ArrayList();
        ajaxWebMap.alFileLines.each {
            list.add(it.dbid)
        }
        return list;
    }

    protected static int uthCount_UtdSearchRecords(String qCommandString, String user)
    {
        ArrayList arrayFlash = new ArrayList();
        arrayFlash << "Caller [caller.ajax_getJSONTableData]";
        LinkedHashMap ajaxWebMap = uthProcessCommand(qCommandString, user);

        ajaxWebMap.alFileLines.size()
        // def list = new ArrayList();
        // return list.size();
    }

    // just confirming the interface -     ajax_getJSONTableDataProxy calls uthProcessCommand under the covers anyway
    protected static int uthRemove_UtdSearchRecords(String qCommandString, String user)
    {
        int removed = 0;
        uthGetDbIds_UtdSearchRecords(qCommandString, user).each {
            UtilMongo.removeFavsRecById(it, user)
            removed++;
        }
        removed;
    }




















}
