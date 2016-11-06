package com.ustodo.todo

import com.ustodo.UtilTestHelper
import com.ustodo.utilg.O

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
//@TestFor(ToDoController)
//@TestMixin(ControllerUnitTestMixin)

//@TestMixin([GrailsUnitTestMixin, ControllerUnitTestMixin, DomainClassUnitTestMixin])
//@TestFor(ProcessCommand)
//@TestMixin(GrailsUnitTestMixin)

class Test130523PASSESPrecodeCollectCategories extends UtilTestHelper {
    void testCollectCategs()
    {
        try {
            Map ajaxWebMap = uthProcessCommand("*", "ckckck");
            def a = ajaxWebMap.alFileLines.size();
            assert  (a == 100 );

        } catch (Throwable t) {
            O.oerr 'error in unit test', t;
            t.printStackTrace();
            throw t;
        }
    }
}
