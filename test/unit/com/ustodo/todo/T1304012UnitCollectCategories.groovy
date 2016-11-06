package com.ustodo.todo

import com.ustodo.UtilTestHelper
import com.ustodo.utilg.O

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

class T1304012UnitCollectCategories extends UtilTestHelper {


    void testCollectCategs()
    {
        try {   // see http://svn.codehaus.org/grails/tags/GRAILS_RELEASE_1_0_RC4/test/web/org/codehaus/groovy/grails/web/servlet/mvc/GrailsParameterMapTests.groovy
            //assert params['a'] instanceof Map

            //params.put ("q", "tester");

            ArrayList arrayFlash = new ArrayList();
            arrayFlash << "Caller [caller.ajax_getJSONTableData]";
            Map ajaxWebMap = uthProcessCommand ("*", "ckckck");
            // http://stackoverflow.com/questions/11785708/how-do-i-unit-test-a-grails-service-that-uses-a-converter
            O.o ("ajaxWebMap.toString():" + ajaxWebMap.toString());
            O.oNoFilter()
            def a = ajaxWebMap.alFileLines.size();
            // assert  (a >  20000);
            O.o("callerhkhhk1:"+ajaxWebMap.alFileLines.size())

            ajaxWebMap.alFileLines.eachWithIndex
                    {
                        it, i ->
                            if (i % 10000 == 0)
                                O.oNoFilter("callerhkhhk2:"+it)
                    }




        } catch (Throwable t) {
            O.oerr 'error in unit test', t;
            t.printStackTrace();
            throw t;
        }
    }

}
