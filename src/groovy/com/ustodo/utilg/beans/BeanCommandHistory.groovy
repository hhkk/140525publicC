package com.ustodo.utilg.beans

import com.mongodb.BasicDBObject
import com.ustodo.command.UtdCmdProc
import com.ustodo.utilg.UtilMongo
import com.ustodo.utilg.UtilDate

/**
 * Created with IntelliJ IDEA.
 * SecUser: hkon
 * Date: 6/27/12
 * Time: 3:26 AM
 * To change this template use File | Settings | File Templates.
 */
class BeanCommandHistory extends Bean {
    String tagOrAll;
    String date;
    String cmd

    BeanCommandHistory(String cmd, String tagOrAll) {
        this.cmd = cmd
        this.tagOrAll = tagOrAll
        this.date = UtilDate.getDateForFileNoSpacesForFileSystem()
    }

    BeanCommandHistory(UtdCmdProc ucp)
    {
        this.cmd = ucp.cmdraw;     // eg including w but no date
        this.tagOrAll = ucp.s3b_cmdraw_normalized_NO_CMD_catOrAllNoCmdIfNoSlash    // no date no cmd
        this.date = UtilDate.getDateForFileNoSpacesForFileSystem()  // date as string
    }

    public void push(String user)
    {
        //O.o "attempt BeanCommandHistory inserte a search"
        //Profiler.check("in beancmdhist pre write")
        if (!"*".equals(this.tagOrAll))
        {
            BasicDBObject doc = new BasicDBObject();
            doc.put("cmd", cmd)	;
            doc.put("tagOrAll", tagOrAll);
            doc.put("date", date);
        }
        //Profiler.check("in beancmdhist post write")
        //O.o "success:inserted a recent search to coll: "+columnname + "[" + tagOrAll + "]"
    }

    private static String getCollName(String user)
    {
        return "CmdHist_"+user;
    }














}
