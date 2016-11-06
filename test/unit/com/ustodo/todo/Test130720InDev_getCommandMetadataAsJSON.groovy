package com.ustodo.todo

import com.ustodo.UtilTestHelper
import com.ustodo.utilg.O
import grails.test.GrailsUnitTestCase

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
class Test130720InDev_getCommandMetadataAsJSON extends GrailsUnitTestCase {
    void test_tryToGetCommandMetadataAsJSON()
    {
        try
        {
            String user = "ckckck"
            String qCommandStringGloballyUnique = "unittestdata" + new java.util.Date();

            // phase 0 - remove prior test data
            UtilTestHelper.uthRemove_UtdSearchRecords(qCommandStringGloballyUnique, user);
            assert (UtilTestHelper.uthCount_UtdSearchRecords(qCommandStringGloballyUnique, user) == 0);

            // phase 1 - insert testdata
            UtilTestHelper.uthProcessCommand(qCommandStringGloballyUnique + "1 w", user)
            UtilTestHelper.uthProcessCommand(qCommandStringGloballyUnique + "2 w", user)

            // phase 2 - count confirm inserted records
                assert (UtilTestHelper.uthCount_UtdSearchRecords(qCommandStringGloballyUnique, user) == 2);

            // phase 3 - delete final to close
            UtilTestHelper.uthRemove_UtdSearchRecords(qCommandStringGloballyUnique, user);

            // phase 4 - confirm delete
            assert (UtilTestHelper.uthRemove_UtdSearchRecords(qCommandStringGloballyUnique, user) == 0);
        } catch (Throwable t) {
            O.oerr 'error in unit test', t;
            t.printStackTrace();
            throw t;
        }
    }
}
