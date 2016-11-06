package com.ustodo.todo

import com.ustodo.UtilTestHelper
import com.ustodo.utilg.O

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

class Test130507_getJSONTableData_FindTwoSimpleUnitTestDataRecords extends UtilTestHelper
{
    void test_ajax_getJSONTableData_CountUnitTestDataRecords()
    {
        boolean deleteWhenDone = false;

        try
        {
            String qCommandStringGloballyUnique = "unittestdata" + new java.util.Date();

            // phase 0 - remove prior test data
            UtilTestHelper.uthRemove_UtdSearchRecords(qCommandStringGloballyUnique, uthUserUnitTestUser);
            assert (UtilTestHelper.uthCount_UtdSearchRecords(qCommandStringGloballyUnique, uthUserUnitTestUser) == 0);

            // phase 1 - write
            UtilTestHelper.uthProcessCommand(qCommandStringGloballyUnique + "1 w", uthUserUnitTestUser)
            UtilTestHelper.uthProcessCommand(qCommandStringGloballyUnique + "2 w", uthUserUnitTestUser)

            // phase 2 - count and assert
            assert (UtilTestHelper.uthCount_UtdSearchRecords(qCommandStringGloballyUnique, uthUserUnitTestUser) == 2);

            // phase 3 - delete and confirm
            if (deleteWhenDone) {
                UtilTestHelper.uthRemove_UtdSearchRecords(qCommandStringGloballyUnique, uthUserUnitTestUser);
                assert (UtilTestHelper.uthRemove_UtdSearchRecords(qCommandStringGloballyUnique, uthUserUnitTestUser) == 0);
            }
        } catch (Throwable t) {
            O.oerr 'error in unit test', t;
            t.printStackTrace();
            throw t;
        }
    }
}
