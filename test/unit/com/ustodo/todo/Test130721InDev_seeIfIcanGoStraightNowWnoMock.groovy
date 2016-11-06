package com.ustodo.todo

import com.ustodo.UtilTestHelper
import com.ustodo.utilg.O

class Test130721InDev_seeIfIcanGoStraightNowWnoMock extends UtilTestHelper {
    void test_tryToGetCommandMetadataAsJSON()
    {
        def o = uthGetDbIds_UtdSearchRecords("tester", "ckckck");
        O.oNoFilter("o:"+o);
    }
}
