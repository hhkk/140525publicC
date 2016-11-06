package com.ustodo.testmongosouptonuts

import com.ustodo.utilg.UtilMongo

class TestMongoSoupToNuts extends GroovyTestCase {

    void xtestFileBackups() {
        assert (UtilMongo.testSoupToNuts());
    }
}




