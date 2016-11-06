    package com.ustodo.command

/**
 * These are written to the DB:
 * ucp.s4a_flrtextonly_withDate, ucp.htmlVersionOfFullCommandUrlsEhanced
 */

import com.ustodo.utilg.O
import com.ustodo.utilg.UtilDate
import com.ustodo.utilg.UtilStr
    import com.ustodo.utilg.UtilStringUstodo
    import com.ustodo.utilg.UtilURL

class UtdCmdProc {

    static final String CMDTYPE_W = "w"; // write
    static final String CMDTYPE_R = "r"; // simple redirect
    static final String CMDTYPE_U = "u"; // utility
    static final String CMDTYPE_H = "h"; // hotlink

    private String cmdraw;

    String getCmdraw() {
        return cmdraw
    }

    private String dataOriFullText_q = null;

    public String textPortionparsedCmd_Cat;
    public String s2_cmdraw_normalized_NO_CMD; //

    public String s3b_cmdraw_normalized_NO_CMD_catOrAllNoCmdIfNoSlash; // CATEGORY or all
    public String s3c_cmdraw_normalized_NoMoreCmd_UrlsFixedWtitleIfNotQuoted; //
    public String dataField_date;
    public String[] arrCmdSplitBySpace_toFindCommand;



    public boolean bwrite = false;
    public String htmlredirect;
    public String  flrtextonly;
    public String s4a_flrtextonly_withDate; // to this add date
    public String s4b_categoryOnlyIfWriteandSlash_keepNoTrailSlash;

    public String htmlVersionOfFullCommandUrlsEhanced;

    CommandMetaData.CommandMetaDataRecord commandMetaDataRecordMatchingOrDefault = null;

    public String toString()
    {
        def sb = new StringBuffer();
        int i = 0
        sb.append ((i++).toString() + O.fmt3("cmdraw >>> ", cmdraw ))
        sb.append ((i++).toString() + O.fmt3("bwrite >>> ", bwrite)	)
        sb.append ((i++).toString() + O.fmt3("cmdraw", cmdraw))
        sb.append ((i++).toString() + O.fmt3("s2_cmdraw_normalized_NO_CMD", s2_cmdraw_normalized_NO_CMD)	)
        sb.append ((i++).toString() + O.fmt3("s3c_cmdraw_normalized_NoMoreCmd_UrlsFixedWtitleIfNotQuoted", s3c_cmdraw_normalized_NoMoreCmd_UrlsFixedWtitleIfNotQuoted)	)
        sb.append ((i++).toString() + O.fmt3("arrSplitSpace", arrCmdSplitBySpace_toFindCommand))
        sb.append ((i++).toString() + O.fmt3("commandMetaDataRecordMatchingOrDefault", commandMetaDataRecordMatchingOrDefault))
        sb.append ((i++).toString() + O.fmt3("htmlredirect", htmlredirect)	)
        sb.append ((i++).toString() + O.fmt3("flrtextonly", flrtextonly))
        sb.append ((i++).toString() + O.fmt3("s4a_flrtextonly_withDate", s4a_flrtextonly_withDate))
        sb.append ((i++).toString() + O.fmt3("s4b_categoryOnlyIfWriteandSlash_keepNoTrailSlash", s4b_categoryOnlyIfWriteandSlash_keepNoTrailSlash)	)
        sb.toString();
    }

    public UtdCmdProc(String user, Map params)
    {
        try {

            //O.oNoFilter(O.stringifyEachable("TDC.UtdCmdProc constructor top level map:" , params));
            dataOriFullText_q = params.q;
            if (params.commandHTMfL)
            {
                params.commandHTML = UtilStr.replaceAllTrailingInstancesOfWithInterimTrim(params.commandHTML, "<br />", "");
            }
            parseCommandAndCategory_paramsDotQ_and_commandHTML_calledByConstructor(user, params)
        } catch ( Exception e )
        {
            O.oerr("callerIdUtdCmdProcConstructor", e)
            System.err.println ("err in here:" + O.getStackTraceToString(e));
        }
        //O.oNoFilter("completed UtdCmdProc constructor OK")
    }

    public boolean write()
    {
        return bwrite;
    }

    private void parseCommandAndCategory_paramsDotQ_and_commandHTML_calledByConstructor(String user, Map params)
    {
        try
        {
            cmdraw = params.q;
            if (cmdraw == null || cmdraw.trim().equals(""))
                cmdraw = "*" //ustodo read search first by default

            // PRELIM LOOP FIND DUPS
            arrCmdSplitBySpace_toFindCommand = cmdraw.trim().split(" ")
//            boolean cmdfoundOnInitScan_toSeeIfWeWantAddU = false;
//
//            // editorial 130712 : this is suboptimal a duplicate run through the list: above just to decide if need to add 'u'
//            // ADD A DEFAULT 'U' IF NEEDED SO WE ARE NORMALIZED ON DEFAULT
//            // it's a read maybe?
//            if (!cmdfoundOnInitScan_toSeeIfWeWantAddU) // then reset with " u" to at least have a command
//            {
//                cmdraw = cmdraw + " u"
//            }

            cmdraw = cmdraw.trim()
            arrCmdSplitBySpace_toFindCommand = cmdraw.split " "

            boolean precmd = false;
            boolean postcmd = false;

            // FIND REF DATA CMD RECORD
            int i = 0;
            CommandMetaData.getvCommandMetaDataRecords().each
                    { o ->
                        i++;
                        CommandMetaData.CommandMetaDataRecord commandMetaDataRecord = o;
                        //O.o "comparing [" + arrSplitSpace[0] + "] and [" + arrSplitSpace[last] + "] to cmdcode [" + commandMetaDataRecord[ModeSchema_1CMDCODE] + "]"
                        if (arrCmdSplitBySpace_toFindCommand[0].equals(commandMetaDataRecord.code)) // front
                        {
                            O.oNoFilter(i + ". hi hk match pre --------------------- arrCmdSplitBySpace_toFindCommand[0] [" + arrCmdSplitBySpace_toFindCommand[0] + "] commandMetaDataRecord.code ["+ commandMetaDataRecord.code + "] vs ")
                            precmd = true;
                            commandMetaDataRecordMatchingOrDefault = commandMetaDataRecord;
                        }
                        else if (arrCmdSplitBySpace_toFindCommand[-1].equals(commandMetaDataRecord.code)) // end
                        {
                            //commandMetaDataRecord.code
                            //O.oNoFilter(i + ". hi hk match post --------------------- user post cmd [" + arrCmdSplitBySpace_toFindCommand[-1] +
                             //       "] vs cmd ref data code ["+ commandMetaDataRecord.code + "] ")
                            postcmd = true;
                            commandMetaDataRecordMatchingOrDefault = commandMetaDataRecord;
                        }
                        else
                        {
                            O.oNoFilter()
                        }
                        // yes we search all sub-optimally even after finding a hit
                    }

            // IF NO MATCH, APPLY DEFAULT OF "U" FOR SEARCH
            if (commandMetaDataRecordMatchingOrDefault == null)
            {
                cmdraw = cmdraw + " u";
                arrCmdSplitBySpace_toFindCommand = cmdraw.split (" ");
                commandMetaDataRecordMatchingOrDefault = CommandMetaData.defaultCommandMetaDataRecord_search;
                postcmd = true;
            }

            // SEPARATE COMMAND FROM TEXT
            if (precmd)
                s2_cmdraw_normalized_NO_CMD = UtilStr.keepAllButFirstX(cmdraw, commandMetaDataRecordMatchingOrDefault.code.length()+1)trim() // +1 for space
            else if (postcmd)
                s2_cmdraw_normalized_NO_CMD = UtilStr.keepAllButLastX(cmdraw, commandMetaDataRecordMatchingOrDefault.code.length()+1).trim() // +1 for space
            else
            throw new Exception("neither pre nor post cmd on cmdraw [" + cmdraw + "]")

            // QUOTED ?
            boolean bCommandWasQuoted = false;
            if (
                    s2_cmdraw_normalized_NO_CMD.startsWith("\"") && s2_cmdraw_normalized_NO_CMD.endsWith("\"") ||
                    s2_cmdraw_normalized_NO_CMD.startsWith("'") && s2_cmdraw_normalized_NO_CMD.endsWith("'")
               ) // if not surrounded by quotes
            {
                bCommandWasQuoted = true;
                s2_cmdraw_normalized_NO_CMD = UtilStr.keepAllButLastX(s2_cmdraw_normalized_NO_CMD, 1);
                s2_cmdraw_normalized_NO_CMD = UtilStr.keepAllButFirstX(s2_cmdraw_normalized_NO_CMD, 1).trim();
            }

            // CATEGORY? _ CURRENTLY ASSUMES LEVEL 1 ONLY
            // for a write command, this determines what portion will be read back
            textPortionparsedCmd_Cat = UtilStringUstodo.buildCategoryForReadAfterWrite(s2_cmdraw_normalized_NO_CMD);

            s3b_cmdraw_normalized_NO_CMD_catOrAllNoCmdIfNoSlash = UtilStr.keepAllBeforeLastOfThis(s2_cmdraw_normalized_NO_CMD, ' / ')

            s2_cmdraw_normalized_NO_CMD  = s2_cmdraw_normalized_NO_CMD.trim()
            // DISTINGUISH 1 FROM 5 : CMD CODE VS CATEGORY RESPECTIVELY
            if (commandMetaDataRecordMatchingOrDefault.group.equals(CMDTYPE_R)) // r for redirect
                htmlredirect = commandMetaDataRecordMatchingOrDefault.urlPreAmble + s2_cmdraw_normalized_NO_CMD + commandMetaDataRecordMatchingOrDefault.urlPostAmble
            // dt:list of places to register a new command #1
            else if (commandMetaDataRecordMatchingOrDefault.group.equals(CMDTYPE_U)) // utility
            {
                if (commandMetaDataRecordMatchingOrDefault.code.equals("sm")) // hot link
                {
                    flrtextonly = s2_cmdraw_normalized_NO_CMD     //
                }
                else
                    throw new RuntimeException("unrecognized cmd code within util category [" + commandMetaDataRecordMatchingOrDefault.code + "]");
            }
            else if (commandMetaDataRecordMatchingOrDefault.group.equals(CMDTYPE_H)) // hotlink
            {
                if (isModeHotlink()) // hot link
                    flrtextonly = s2_cmdraw_normalized_NO_CMD
                else
                    throw new RuntimeException("unrecognized cmd code within util category [" + commandMetaDataRecordMatchingOrDefault.code + "]");
            }
            else if (commandMetaDataRecordMatchingOrDefault.code.equals(CMDTYPE_W)) // not url (thus needing no url fix or title add)
            {
                bwrite=true
                if (!bCommandWasQuoted) // if not surrounded by quotes`
                    s3c_cmdraw_normalized_NoMoreCmd_UrlsFixedWtitleIfNotQuoted = fixUrlsInTextIncludingTitleFetchFromWeb(s2_cmdraw_normalized_NO_CMD)
                else
                    s3c_cmdraw_normalized_NoMoreCmd_UrlsFixedWtitleIfNotQuoted = s2_cmdraw_normalized_NO_CMD

                flrtextonly = s3c_cmdraw_normalized_NoMoreCmd_UrlsFixedWtitleIfNotQuoted
                dataField_date = UtilDate.getDateForFile()
                s4a_flrtextonly_withDate = dataField_date + " " + s3c_cmdraw_normalized_NoMoreCmd_UrlsFixedWtitleIfNotQuoted
                if ( s3c_cmdraw_normalized_NoMoreCmd_UrlsFixedWtitleIfNotQuoted.indexOf(" / ") >= 0 )
                {
                    s4b_categoryOnlyIfWriteandSlash_keepNoTrailSlash = UtilStr.keepAllBeforeLastOfThis(s3c_cmdraw_normalized_NoMoreCmd_UrlsFixedWtitleIfNotQuoted, " / ") + " / "
                }
            }
            else //
                flrtextonly = s2_cmdraw_normalized_NO_CMD
            //throw new RuntimeException("unrecognized cmd type:" + vCommandMetaDataRecords[ModeSchema_1CMDCODE]);

            //O.oNoFilter("================ done processing command:" + this.toString())
            O.oNoFilter("================ done processing command commandMetaDataRecordMatchingOrDefault:" + commandMetaDataRecordMatchingOrDefault)
        } catch ( Exception e)
        {
            O.oerr("UtdCmdProc.parseCommandAndCategory_paramsDotQ_and_commandHTML_calledByConstructor", e)
        }

    } // parseCommandAndCategory_paramsDotQ_and_commandHTML_calledByConstructor



    private static String fixUrlsInTextIncludingTitleFetchFromWeb(String s)
    {
        String sspace = s.replaceAll("\t", " ")
        sspace = sspace.replaceAll("\r", " ")
        sspace = sspace.replaceAll("\n", " ")
        String[] arr = sspace.split(" ")
        for (int i = 0; i < arr.length; i++)
        {
            if ((arr[i].contains(".") || arr[i].contains(".")) && UtilURL.isUrl(arr[i], true))
            {
                //O.o("found url:" + arr[i])
                String url = arr[i].trim();
                String urlnew = url;
                if (!url.toLowerCase().startsWith("http"))
                    urlnew = "http://" + url
                String title = com.ustodo.utilg.UtilURL.getTitle("Caller is UtdCmdProc.fixUrlsInTextIncludingTitleFetchFromWeb", urlnew, false, false);
                s = s.replace(url, "[" + title + "] " +  urlnew)
            }
        }
        s
    }

    // dt:list special mode processor indicator functions
    public boolean isModeHotlink()
    {
        //O.oNoFilter("in isModeHotlink ===========================================")
        //O.oNoFilter("in isModeHotlink commandMetaDataRecordMatchingOrDefault:" + commandMetaDataRecordMatchingOrDefault);
        //O.oNoFilter("in isModeHotlink commandMetaDataRecordMatchingOrDefault.code:" + commandMetaDataRecordMatchingOrDefault.code);
        commandMetaDataRecordMatchingOrDefault.code.equals("h") // is UtdCmdProc.CMDTYPE_U
    }
    public boolean isModeSendMessage()
    {
        commandMetaDataRecordMatchingOrDefault.code.equals("sm") // is UtdCmdProc.CMDTYPE_U
    }

} // END CLASS UTDCMDPROC











