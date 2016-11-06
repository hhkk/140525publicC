package com.ustodo.todo

import com.ustodo.UtilTestHelper
import com.ustodo.utilg.O

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
class T1304012ProcessCommand_ajax_getJSONTableData extends UtilTestHelper {

    void testCountOnly_NoInsertOrDelete_ActualUnittestdataOccurences()
    {
        try {   // see http://svn.codehaus.org/grails/tags/GRAILS_RELEASE_1_0_RC4/test/web/org/codehaus/groovy/grails/web/servlet/mvc/GrailsParameterMapTests.groovy
            Map ajaxWebMap = uthProcessCommand ("unittestdata", "ckckck");
            assertTrue ("should be at least one unittestdata entry", ajaxWebMap.alFileLines.size() > 0)
        } catch (Throwable t) {
            O.oerr 'error in unit test', t;
            t.printStackTrace();
            throw t;
        }
    }
}