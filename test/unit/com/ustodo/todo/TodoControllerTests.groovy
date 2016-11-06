package com.ustodo.todo

import com.ustodo.utilg.O
import com.ustodo.utilg.UtilUtd_DBExportToFile

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
//@TestFor(ToDoController)
class TodoControllerTests extends GroovyTestCase {
    void testSomething1() {
        assert (true);
    }

    void xtestFileBackups() {
        try
        {
            O.o "attempt!"
            UtilUtd_DBExportToFile.exportToFile("favsckckck", "/tmp/tfavsckckckexport.csv");
            UtilUtd_DBExportToFile.exportToFile("favsmkon", "/tmp/tfavsmkonexport.csv");
            UtilUtd_DBExportToFile.exportToFile("favsbkon", "/tmp/tfavsbkonexport.csv");
            //UtilUtd_DBExportToFile.exportToFile("favsckckck", "/tmp/tfavsckckckexport.csv");
            assert (true);
            //assert (false);
        } catch (Throwable t ) {
            t.printStackTrace();
            O.or ("faile", t)
            assert (false);
        }


        O.o "success!"


        //fail "Implement me"
    }
}
