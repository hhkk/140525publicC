package com.ustodo.todo
//import org.example.SecOldUser
import com.mongodb.DBCollection
import com.ustodo.command.UtdCmdProc;
import com.ustodo.utilg.Cfg
import com.ustodo.utilg.FileLine
import com.ustodo.utilg.O
import com.ustodo.utilg.Profiler
import com.ustodo.utilg.Rte
import com.ustodo.utilg.UtilArgs

import com.ustodo.utilg.UtilFile
import com.ustodo.utilg.UtilMongo
import com.ustodo.utilg.UtilPerf
import com.ustodo.utilg.UtilStr
import com.ustodo.utilg.UtilURL;
import com.ustodo.utilg.mail.UtilEmail;
import com.ustodo.utilg.mail.UtilAllUserEmailToDB;
import com.ustodo.utilg.UtilEnv;

import com.ustodo.utilg.beans.BeanCommandHistory;

import com.ustodo.utilj.utdrequest.UtdRequest;



class ProcessCommand {

    private static int seq = 0;

    public static HashMap hsSearchedTextLast = new HashMap();

    //    java.lang.String, java.lang.String, org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap, java.util.LinkedHashMap, java.lang.Boolean


    public static Object testme(Object params)
    {
        try {

        } catch (Throwable t) {
            O.or("index top level", t)
            throw t;
        }

    }


    public static java.util.LinkedHashMap processCommand(
            String callerId, // called by todo.index and ajax get data
            String username,
            org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap params, // org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap params,// -------------
            ArrayList arrayFlash
    )
    {
        O.oNoFilter("=========== xx     in ProcessCommand.processCommand() USER[" + username + "] P.Q[" + params.q + "] ");
        boolean profileroutput = false;





        // TEST WEB SERVICE CALL
        //O.oNoFilter("=========== in ProcessCommand.processCommand() pre test =============== ");
        //String s34 = UtdRequest.testWebCall()
        //O.oNoFilter("=========== in ProcessCommand.processCommand() post test =============== s34:" + s34);

        //O.oNoFilter( "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  131110 in proc cmd caller [" + callerId + "] username [" + username +"] local ip [" + InetAddress.getLocalHost().getHostAddress() +  "]"  );
        //O.oNoFilter( "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  130205 in todoc.index params [" + O.stringifyMap("130205 params:", params) + "]");

        //O.oNoFilter(O.stringifyEachable("processCommand.processCommand top level map:" , params));

        //O.o "13020a0 =========== todo.Index.NonClosureToBreakIn.callerId [" + callerId + "]  params.options [" + params.options + "]"
        //O.o "130202b todo.Index.NonClosureToBreakIn.callerId [" + callerId + "]  params.options [" + params.options + "]"
        //O.o "hbkw2e3r5 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ params.utdoptions [" + params.utdoptions + "]"
        //O.o "hbkw2e3r5 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ params.options [" + params.options + "]"

        Map mapOptions = UtilArgs.parseArgs(params.options);

        try
        {

            int maxRecordsWanted = -1;
            if (params.q.split(" ")[0].equals("*") )
                maxRecordsWanted = 100;



            mapOptions.put("tm", "1200m");
            if (!mapOptions.containsKey("au"))
                mapOptions.put("au", "n");
            if (!mapOptions.containsKey("details"))
                mapOptions.put("details", "n");

            UtilPerf.perfMsgsForUIClear();

            def collnameNewauth = Cfg.getCollNameThisUserFavs(username);

            // GET UCP
            UtdCmdProc ucp = new UtdCmdProc(username, params);

            //   O.oc ("DENVER1: params.q:", params.q);
            //O.oc ("ucp:", ucp);

            // interesting variables used in the past:
            //O.oc "1axxxxx in todoIndexNonClosureToBreakIn params", params// -------------  937. ocdesc {1axxxxx params} clsnm {org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap} toStr {[q:tester, controller:todo]}
            //O.oc "3axxxxx mockFlash", mockFlash// ------------- 939. ocdesc {3axxxxx mockFlash} clsnm {org.codehaus.groovy.grails.web.servlet.GrailsFlashScope} toStr {[message:]}
            //O.oc "4axxxxx request", request// ------------- 940. ocdesc {4axxxxx request} clsnm {org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper} toStr {org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper@1327ce86}
            //O.oc "4ab javax.servlet.forward.request_uri (todo controller) 4 request", request //  org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper
            //O.oc "4ab1 javax.servlet.forward.request_uri", ((org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper) request).getAttribute("javax.servlet.forward.request_uri")
            //O.oc "4ab2 javax.servlet.forward.context_path", ((org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper) request).getAttribute("javax.servlet.forward.context_path")
            //O.oc "4ab3 javax.servlet.forward.servlet_path", ((org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper) request).getAttribute("javax.servlet.forward.servlet_path")
            //request.getAttributeNames().each() {
            //    O.o "4ab (todo controller) request.getAttributeNames:", it //  org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper
            //}
            //O.oc "ss a:", ((org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper)request).parameterMap.keySet()
            // oc:varname [params] getClass [org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap] toString[["hkhk":"asd", "controller":"todo"]]


            // RECENT HISTORY
            def recentTag = new BeanCommandHistory(ucp); // date
            recentTag.push(username)

            // save the category or context
            //			if (ucp.bwrite && ucp.cmdraw.contains(" / ")) // save ctx=category
            //			{
            //				hsLastCatWritten.put(username, UtilStr.keepAllBeforeLastOfThis(ucp.cmdraw, " / "))
            //				def cat = UtilStr.keepAllBeforeLastOfThis(ucp.cmdraw, " / ")
            //				hmopt.put("ctx", cat)
            //				params.utdoptions = params.utdoptions + " ctx =" + cat
            //			}
            //

            boolean syncemail = (params.syncemail != null && params.syncemail.equals("on"));
            def paramsInSavedPrev = params.savedPrev;
            def paramsInScratch = params.scratch;
            // CONFIG
            boolean MODE_READ_FILE = false;// important !!!!!!!!!!!!!!!!
            boolean MODE_ASSERT_FILEREAD_VS_DBREAD_AMTS = true

            def rtnSearchedPrev;
            String txtUpper;
            def rtnSavedPrev;

            //O.o "############################################### [${search}]"

            //boolean MODE_EMAIL_ADVANCED = search.endsWith(" e2"); // email search
            //if (MODE_EMAImouseL_ADVANCED) // gmail
            //	paramsInSearchNow=UtilStr.keepAllButLastX(paramsInSearchNow, 3)

            TodoController.callCntIndex++;

            boolean WANT_TO_PRINT_ENV = false;
            if (WANT_TO_PRINT_ENV && !TodoController.printedEnv)
            {
                String s = UtilEnv.getEnv(); // classpath
                o "ENV [" + s + "]";
                TodoController.printedEnv=true;
            }

            //O.o "username:" + username
            if (UtilStr.isNullOrBlank(username))
                throw new RuntimeException("error no user");

            seq++;
            String mode = ""

            String fqfilename = Cfg.getFqFileName(username)
            UtilFile.touchThoNoDateUpdateIfAlreadyThere(fqfilename) // file create if needed

            // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            // FILE AND DBWRITE DB WRITE WRITE WRITE WRITE WRITE hihk  WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE
            // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            // O.o "ffffffffffffffffffffffffffffffffffffffffffffff " + paramsInSearchNow

            // remember that W3 starts with W for example
            DBCollection coll = null;
            //def pendingRead = false;
            ArrayList<FileLine> alFileLines = new ArrayList<FileLine>();
            if (ucp.write()) // if bwrite
            {
                //pendingRead = true;
                try
                {
                    // DB  WRITE DB
                    UtilPerf.perfMsgsForUIAppend "<br> / " + Profiler.check(" ** start db  write", profileroutput);
                    //O.ofmt("@@@@@@@@@@@@@@@@@@@@@@ DB  WRITE a    ttempt namecoll", collnameNewauth);
                    O.o ("DB  WRITE attempt namecoll" + collnameNewauth);
                    coll = UtilMongo.getOrCreateDBAndColl("db  write",  Cfg.dbname, collnameNewauth);
                    //coll.setWriteConcern(WriteConcern.SAFE);
                    //coll.setWriteConcern(new WriteConcern(1, 0, true));
                    //coll.setWriteConcern(WriteConcern.REPLICAS_SAFE);

                    //O.o("writeStr:" + ucp.s4a_flrtextonly_withDate)
                    int cntpre = coll.count();
                    //UtilMongo.putRecordFromFLRS (coll, username, ucp.s4a_flrtextonly_withDate, params.commandHTML)




                    // OUTLINE: WRITE

                    UtilMongo.putRecordFromFLRS (coll, username, ucp.s4a_flrtextonly_withDate, ucp.htmlVersionOfFullCommandUrlsEhanced)
                    O.oNoFilter("ucp.htmlVersionOfFullCommandUrlsEhanced:"+ ucp.htmlVersionOfFullCommandUrlsEhanced);
                    int cntpost = coll.count();
                    //Thread.sleep(1000)
                    //O.o "############################################## did we not sleep?"

                    UtilPerf.perfMsgsForUIAppend "<br> / " + Profiler.check("done db  write", profileroutput);
                    //confirm db  write both via counts and via rrtrieval of record just saved


                    if (true) // code block for validation of write VALIDATION
                    {
                        // UtilAssert.assertequalsx("db  write pre+1 to post count compare", cntpre+1, cntpost)
                        UtilPerf.perfMsgsForUIAppend "<br> / " + Profiler.check(" pre confirm db  write", profileroutput);
                        if (UtilMongo.getRecsFileLineRawEqual(coll, ucp.s4a_flrtextonly_withDate).length == 0)
                            throw new Rte ("DB write/read failed on flr [" + ucp.s4a_flrtextonly_withDate + "]")
                        else
                            O.o("DB write validated by pre-post count flr [" + ucp.s4a_flrtextonly_withDate + "]");

                        UtilPerf.perfMsgsForUIAppend "<br> / " + Profiler.check(" done confirm db  write", profileroutput);
                        arrayFlash << "wrote ["+ ucp.flrtextonly  + "]"
                    } // END VALIDATION

                    // END WRITE TO DB

                    // FILE WRITE FILE
                    O.o(" @@@@@@@@@@@@@@@@@@@@@@@write to file:" + fqfilename)
                    UtilPerf.perfMsgsForUIAppend "<br> / " + Profiler.check(" ** start file  write", profileroutput);
                    if (fqfilename != null)
                    {
                        new File(fqfilename).withWriterAppend { out ->
                            out.writeLine(ucp.s4a_flrtextonly_withDate);
                        }
                        O.o "wrote to file [${fqfilename}] text [${ucp.s4a_flrtextonly_withDate}]"
                    }
                    UtilPerf.perfMsgsForUIAppend "<br> / " + Profiler.check("end file  write", profileroutput);

                    O.o "130228 adding to mockFlash: Saved [" + ucp.s3c_cmdraw_normalized_NoMoreCmd_UrlsFixedWtitleIfNotQuoted + "]<br>"
                    arrayFlash << "<br> <font size= +0 color='orange'> **********************  Saved [" + ucp.flrtextonly + "]</font><br>";
                    //O.o "----------  mockFlash to [" + mockFlash['message'] + "] --------------------"

                    O.o("user [" + username + "] saved [" +     ucp.s4a_flrtextonly_withDate + "]");
                } catch ( Throwable t)
                {
                    O.or("most likely a fail on write to file or DB ", t);
                }
                //pendingRead = true;

                txtUpper = ucp.s2_cmdraw_normalized_NO_CMD ;
            } // if write
            //            else if (ucp.textPortionparsedCmd_Cat != null)
            //                txtUpper = ucp.textPortionparsedCmd_Cat;
            //            else
            //                txtUpper = ucp.s2_cmdraw_normalized_NO_CMD
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!! END WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE

            else // not a write
            {
                if (syncemail)
                {
                    Profiler.check("per email cycle")
                    UtilAllUserEmailToDB.readEmailAndPropagate()
                    Profiler.check("post email cycle")
                }


                // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                // SEARCH (READ) START
                // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

                if (UtilStr.isNotNullBlank(ucp.flrtextonly))
                {
                    // DB READ DB
                    // NOTE OUT VAR: alReadLinesDbOrFileOrEmail_OUT
                    Vector vOutOneElem_textTopHitForLower = new Vector();
                    //listdboOUTForIndexHtml = new ArrayList();

                    if (!coll) // e.g., if no write above
                        coll = UtilMongo.getOrCreateDBAndColl("db read for rtnSearchNow", Cfg.dbname, collnameNewauth); // was line 99 of ProcessCommand_GetDbReadContrib
                    //coll = UtilMongo.getOrCreateDBAndColl("db  write",  Cfg.dbname, collnameNewauth);

                    O.o ("-------------------- in 1 received params.options [" + params.options + "]")

                    ProcessCommand_GetDbReadContrib.getDbReadContrib (
                            callerId + " > ToDoNonCloser1 in todoIndexNonClosureToBreakIn",
                            alFileLines,
                            ucp.s2_cmdraw_normalized_NO_CMD, // txtUpper,
                            UtilArgs.parseArgs(params.options),
                            collnameNewauth,
                            params,
                            profileroutput,
                            vOutOneElem_textTopHitForLower,
                            username,
                            //listdboOUTForIndexHtml,
                            coll,
                            maxRecordsWanted)

                    //O.oc( "sssssssssssssssssssssssssssssss what do we read? alReadLinesDbOrFileOrEmail[0]", alFileLines) // class is : FileLine

                    if (ucp.isModeHotlink())
                    {
                        boolean foundUrl = false;
                        alFileLines.each
                                {
                                    FileLine fl = (FileLine) it;
                                    String textNoDate = fl.lineMinusDateStr;
                                    String[] sarr = textNoDate.split(" ");
                                    if (!foundUrl)
                                    {
                                        for (int i = 0; i < sarr.length; i++)
                                        {
                                            String s = sarr[i].trim();
                                            //O.o "testing if is url: " +s;
                                            if (UtilURL.isUrl(s, true))
                                            {
                                                O.o "s2 isurl yes:"+s
                                                s = UtilURL.convertHrefedLinkToRaw(s);
                                                O.o "s3 isurl yes:"+s

                                                // THIS IS THE HOTLINK ONE
                                                // new windw http://stackoverflow.com/questions/2852154/open-html-meta-redirect-in-new-window
                                                //ucp.htmlredirect = 		"<meta HTTP-EQUIV=\"REFRESH\" content=\"0; url="+"http://ibm.com"+"\">";
                                                // URL=javascript:window.open('http://google.com','_parent')
                                                // <meta http-equiv="refresh" content="5; URL=javascript:window.open('http://google.com','_parent');">
                                                // ucp.htmlredirect = 		"<meta HTTP-EQUIV=\"REFRESH\" content=\"0; url=javascript:window.open('"+s+"','_parent');\">";
                                                // works ucp.htmlredirect = 		"<meta HTTP-EQUIV=\"REFRESH\" content=\"0; url="+s+"\">";
                                                ucp.htmlredirect = 		s;

                                                O.o("in hotlink redirect set to :" + ucp.htmlredirect);
                                                foundUrl = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                    }
                    else if (ucp.isModeSendMessage())
                    {
                        String cmdremoved = ucp.s2_cmdraw_normalized_NO_CMD;
                        O.o "HBK - process here ---------------------------------------------876876876 cmdremoved[" + cmdremoved + "]"


                        // sm henry.kon@gmail.com subject / body this is more of the body
                        String[] crarr = cmdremoved.split " "
                        O.o "got in sm mode cmdremoved [" + cmdremoved + "]"  // "sm tester"
                        String recipient = crarr[0]
                        //O.o "got in sm mode recipient [" + recipient + "]"  // "sm tester"
                        String restAfterEmailAddr_subjSlashBody = crarr[1..-1].join(" ")
                        String subject;
                        String body;
                        if (restAfterEmailAddr_subjSlashBody.contains(" / "))
                        {
                            subject = UtilStr.keepAllBeforeLastOfThisNewerStripsEndDelim(restAfterEmailAddr_subjSlashBody, " / ")
                            body = UtilStr.keepAllAfterLastOfThis(restAfterEmailAddr_subjSlashBody, " / ")
                        }
                        else
                        {
                            subject = restAfterEmailAddr_subjSlashBody
                            body = ""

                        }

                        O.o "in sm mode subject [" + subject + "(from user [" + username + "])"  // "sm tester"
                        O.o "in sm mode body [" + body + "]"  // "sm tester"

                        if (!cmdremoved.contains("notreally"))
                            UtilEmail.sendMail(recipient, subject, body + "\r\n\r\n -- This email was sent to you by user [" + username + "] on UsToDo.com.", username)

                        O.o "done sendmail"
                        // sm

                    }
                    else
                    {
                        //O.o "no special mode detected"
                    }

                    //O.o ("Found [" + iCntDBLinesMatch + "] records in ToDo controller search ")
                    // DONE DB READ =========================


                } // if not blank on user input

            }  // else to is a write
            //O.o "params.utdoptions:" + params.utdoptions
            params.utdoptions = UtilArgs.buildArgStr(mapOptions);

            UtilPerf.perfMsgsForUIAppend "<br> / " + Profiler.check("at very bottom of index", profileroutput);

            def alRecentCommands = []; // aka cmdhist
            Profiler.check("pre cmdhist build username" + username)
            //check cache and add as needed - thread test to save? if

            //def runCmdHist=false //FOR RIGHT HAND NAV
            // CAREFUL DELETING COMMAND HISTORY
            //            if (runCmdHist)
            //            {
            //                def cur = UtilMongo.select(
            //                    "CmdHist_" + username,
            //                    fieldToSrchOrNull,
            //                    fieldToSrchOrNull,
            //                    "date", // orderfield or null
            //                    // eldOrNull,
            //                    -1, // orderDir, 1 = ASC 2 = DESC
            //                    limitIfGt0,
            //                    maputdoptionsOrNull,
            //                    false);
            //
            //                Profiler.check("mid- cmdhist build post select pre loop")
            //                //            (java.lang.String, null, null, java.lang.String, java.lang.Integer, java.lang.Integer, null, null) values: [CmdHist_ckckck, null, null, date, -1, 100, ...]
            //
            //                //            UtilMongo.select(
            //                //                    "CmdHist_" + username,  //   String collname,
            //                //                    'tagOrAll',  //   String fieldToSrchOrNull,
            //                //                    '',         //   String strSrchPreStarStar,
            //                //                    null,      //   String orderfieldOrNull,
            //                //                    -1, //   int orderdir,
            //                //                    -1, //   int limitIfGt0,
            //                //                    null, //   Map maputdoptionsOrNull,
            //                //                    true //   boolean MODE_SEARCH_STAR)
            //
            //
            //                int i2	 = 0;
            //                // LOOP
            //                def seen = [] as Set
            //                while (cur.hasNext())
            //                {
            //                    i2++
            //                    //Profiler.check("mid1 " + i2 + " cmdhist build post select pre loop")
            //                    def dboRead = cur.next();
            //                    //O.oc "dboRead:", dboRead
            //                    //Profiler.check("mid2 cmdhist build post select pre loop")
            //
            //                    def cmd = dboRead.get("cmd");
            //                    //O.oc "cmd:", cmd
            //                    //Profiler.check("mid3 cmdhist build post select pre loop")
            //                    def utdcmd = new UtdCmdProc(cmd, null);
            //                    def recentstr = utdcmd.cmdraw
            //                    //O.o (i2 + ". found recentstr:" + recentstr)
            //                    if (!seen.contains(recentstr))
            //                    {
            //                        alRecentCommands <<  recentstr;
            //                        seen << recentstr
            //                    }
            //                } // while cur.hasnext
            //                Profiler.checrtnSearchedPrevk("post cmdhist build, total was:" + i2)
            //            }
            //            else
            //            {
            O.o "SKIPPED CMD HIST user:" +username
            //  }

            String status = "worked";

            arrayFlash << "Caller [" + callerId + "]"

            O.o "SKIPPED CMD HIST 130325 txtupper:" +txtUpper
            O.o "SKIPPED CMD HIST 130325 ucp.cmdraw:" +ucp.cmdraw

            //O.oNoFilter("hbk  processCommand returning params.utdoptions:" + params.utdoptions)
            // remote/local
            //O.oNoFilter("hbk  processCommand returning params.options:" + params.options)
            Map ajaxWebMap = [
                    //ip: request.getRemoteAddr().toString()+O.fmt("getUserRealAuth()",getUserRealAuth())+UtilPerf.getPerfMsgsForUI(),
                    //listdboOUTForIndexHtml: listdboOUTForIndexHtml,
                    //rtnFld5Top_mainSearch: rtnFld5Top_mainSearch
                    alFileLines: alFileLines,
                    alRecentCommands: alRecentCommands,
                    cborder: params.cborder,    // reflects back
                    cbword: params.cbword,  // reflects back
                    cmdraw: ucp.cmdraw,
                    fqFileName: ("<font color=\"GREEN\">"+mode),
                    html_redirecthk: ucp.htmlredirect, //hotlink
                    //pendingRead: pendingRead, // $$$$$$$$$$$$$$$$$$$$$$$$$
                    savedPrev: rtnSavedPrev,
                    scratch: paramsInScratch,
                    searchedPrev: rtnSearchedPrev,
                    seq:seq,
                    status: status,
                    txtUpper: txtUpper,
                    hihk: 'hihk2',
                    //txtUpperCandidates: txtUpperCandidates,

                    ucp: ucp,  // $$$$$$$$$$$$$$$$$$$$$$$$$
                    user1:username,
                    arrayFlash:arrayFlash,
                    utdoptions: params.options,  // reflects back
                    commandHTML:params.commandHTML
            ];

            //O.oc ("ajaxWebMap:", ajaxWebMap) // java.util.LinkedHashMap
            ajaxWebMap;

            // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            // SEARCH (READ) END
            // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        } catch (Throwable t) {
            O.or("index top level", t)
            throw t;
//            render "failure hk"
        } finally {

            // history log to hk email

            if (false && !username.equals("ckckck"))
            {
                try {
                    (new Thread(new SendCmdLogEmailThread.SendCmdLogEmailThread_(username, params.q))).start();

                    O.o "sent email ustodo user command [" + username + "]" + "params.q [" + params.q + "]";
                } catch ( Exception e )
                {
                    O.or("error sending non ckckck email notice - MAYBE THE NETWORK IS UNAVAILABLE", e)
                }
            }
            //Profiler.end("ToDoC.index", true);
        }
    } // end indexNonClosureToBreakIn



}
