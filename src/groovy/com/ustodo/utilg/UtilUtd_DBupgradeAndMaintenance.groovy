package com.ustodo.utilg

import com.mongodb.BasicDBObject

class UtilUtd_DBupgradeAndMaintenance {
    // see also UtilMongo_File_Email_Db

    public static void main (String[] args)
    {
        try
        {
            runDBMaint("ckckck");
        }
        catch (Throwable t )
        {
            O.or("t", t);
            throw t;
            assert(false);
        } finally {
            O.o "done"
            assert (true)
        }
    } // main

    public static void runDBMaint(String username)
    {
        runDBMaint_Detail(username);
    }

    public static String runDBMaint_Detail(String username)
    {
        O.o "done runDBMaint_Detail"
    }

}
