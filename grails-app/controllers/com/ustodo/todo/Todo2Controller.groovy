package com.ustodo.todo

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.ustodo.SecUser
import com.ustodo.utilg.*
import com.ustodo.utilg.beans.BeanCommandHistory
import grails.plugins.springsecurity.Secured
import groovy.json.JsonSlurper

class Todo2Controller {

    def springSecurityService;

    public static boolean   profileroutput = true;
    //static def test = O.o("in static init again")
    // todo: clean this up - unused variables AND NOT NEEDED FOR AUTH?
    def authenticationService;

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"];  // http://grails.org/doc/2.3.x/ref/Controllers/allowedMethods.html

    /**
     *  index - initial delivery of the server - ISP file
     */

    @Secured(['ROLE_USER'])
    def index = {

        //O.oNoFilter(O.stringifyEachable("TDC.index top level map:" , params));
        if (!params.q)
            params.q     = "*"
        params.request = request;

        O.o("params.q:" + params.q );
        render(view: "todo2", model: params);
        //render(view: "i14_130717_TestWindowOpen", model: params);
    } // end index

    def index2 = {

        //O.oNoFilter(O.stringifyEachable("TDC.index top level map:" , params));
        if (!params.q)
            params.q     = "*"
        params.request = request;

        O.o("params.q:" + params.q );
        render(view: "tt", model: params);
        //render(view: "i14_130717_TestWindowOpen", model: params);
    } // end index

    // demo URL
    @Secured(['ROLE_USER'])
    def indexhk3 = {render "hello"} // http://u2d.co/todo/indexhk3

    // thisis ToDoController.ajax_getJSONTableData
    @Secured(['ROLE_USER'])
    def ajax_getJSONTableData =
            {
                //O.oNoFilter("=========== in TodoController.ajax_getJSONTableData =============== ");
                //O.oNoFilter(O.stringifyEachable("hkhkhkhk TDC.ajax_getJSONTableData top level map:" , params));
                try
                {
                    try {
                        //O.oNoFilter ("==top of T1DC.ajax_getJSONTableData " + SecUser.get(springSecurityService.principal.id).username  + " params.q [" + params.q + "] "+ " params.options [" + params.options + "]")
                        //O.oNoFilter ("==top of T2odoController.ajax_getJSONTableData HTML/object test " + " params.ajaxSendMap [" + params.ajaxSendMap + "]")
                        //O.oNoFilter ("==top of T3odoController.ajax_getJSONTableData HTML/object test " + " params.q [" + params.q + "]")
                    }
                    catch ( Exception e) {
                        System.out.println("Basic function fail: error in basic output")          //def alFileLines = map.alFileLines
                    }
                    if (!params.q)
                        throw new Exception ("params.q should be set by the time in ajax_getJSONTableData");
                    //params.q = "*"

//            O.oNoFilter("hk in ============================ in SecUser.get(springSecurityService.principal.id).username:");
//            O.oNoFilter("hk in ============================ SecUser.get(springSecurityService.principal.id).username:"+SecUser.get(springSecurityService.principal.id).username);
                    ArrayList arrayFlash = new ArrayList();
                    arrayFlash << "Caller [caller.ajax_getJSONTableData]";
                    java.util.LinkedHashMap ajaxWebMap = ProcessCommand.processCommand (  // ajaxWebMap   // returns  return new JSON(map).toString();
                            "caller.ajax_getJSONTableData",
                            SecUser.get(springSecurityService.principal.id).username,
                            params,
                            arrayFlash
                    );

                    def jsonStr = new grails.converters.JSON(ajaxWebMap).toString();
                    //O.oNoFilter("ajax_getJSONTableData jsonStr:"+jsonStr);

                    render ajaxWebMap as grails.converters.JSON
                } catch ( Exception e) {
                    O.or "fail1b", e          //def alFileLines = map.alFileLines
                }
            } // ajax_getJSONTableData






    def ajax_FindAndModify_FilelineRecordText  =
            {

                try
                {
                    O.o ("130329 in UtilMongo_updateRecordTimestamp")
                    UtilMongo.utilMongo_updateRecordText_usingFindAndModify(  // ajaxWebMap
                            params.callerId,
                            params.dbId,
                            params.newText.trim(),
                            params.newHtml.trim(),
                            SecUser.get(springSecurityService.principal.id).username
//                    options: getElementByIdHK('idTextFieldUtdoptions').value.trim(),
//                    callerId: callerId,
//                    dbId: dbId,
//                    newText: newText
                            //        '515606f40364dc42be038ade', "NEW CARP",  SecUser.get(springSecurityService.principal.id).username
                    );

                    def map = new HashMap();
                    map['result'] =  'success';

                    //def jsonStr = grails.converters.JSON(map).toString();
                    //O.oNoFilter(" jsonStr:"+jsonStr);

                    render new grails.converters.JSON(map).toString();

                    //render "{'result':'success'}" as JSON
                } catch ( Exception e) {
                    O.or "fail1c", e          //def alFileLines = map.alFileLines
                }
            } // ajax_FindAndModify_FilelineRecordText






    @Secured(['ROLE_USER'])
    def i1 = {
        def username = SecUser.get(springSecurityService.principal.id).username
        def map = ProcessCommand.indexCommon(params, request, username, flash, profileroutput);
        map.newuiTF = false
        render(view: "index", model: map);
    }

    @Secured(['ROLE_USER'])
    def i2 = {
        def username = SecUser.get(springSecurityService.principal.id).username
        def map = ProcessCommand.indexCommon(params, request, username, flash, profileroutput);
        map.newuiTF = false
        render(view: "i2realcontent", model: map);
    }


    @Secured(['ROLE_USER'])
    def i4 = {render(view: "i4slidesRefs");}

    @Secured(['ROLE_USER'])
    def i5 = {render(view: "i5append");}

    @Secured(['ROLE_USER'])
    def i6 = {render(view: "i6email");}

    @Secured(['ROLE_USER'])
    def i7 = {render(view: "i7emailmorph");}

    @Secured(['ROLE_USER'])
    def i8 = {render(view: "i8complex");}

    @Secured(['ROLE_USER'])
    def i9 = {render(view: "i9complexmine");}

    @Secured(['ROLE_USER'])
    def i10 = {render(view: "i10complexminemod");}

    @Secured(['ROLE_USER'])
    def i11 = {render(view: "i11ComplexUsToDo");}

    @Secured(['ROLE_USER'])
    def i12 = {render(view: "i12Simple");}

    @Secured(['ROLE_USER'])
    def i13 = {render(view: "i13SimpleSandbox");}








    // test params on a new URL
    // http://localhost:8084/ustodo/todo/index2?q=testerttttt
    def indexTestParamsonNewURL = {
        //def results = SecUser.findAll()
        def sb = new StringBuffer();
        params.each() {
            sb.append "param:" + it + "<br>\r\n"
        }

        //render "collnames:" + UtilMongo.schemaGetCollectionNames(Cfg.dbname)
        render "sb:" + sb   // sb:param:q=testerttttt param:action=index2 param:controller=todo

    }



    // test collnames on a new URL
    def indexTestCollNamesOnNewURL = {
        //def results = SecUser.findAll()

        def sb = "q [" + params.get("q") + "] ";
        UtilMongo.schemaGetCollectionNames(Cfg.dbname).each {
            sb = sb + "\r\n<br>" + it + ":" + UtilMongo.getCollCountByName("test",Cfg.dbname, it);
        }
        render sb

    }

    def indexhktest3 = {
        //def results = SecUser.findAll()

    }

    def indexhktest4 = {
        render "hi from index2"
    }
    //

    public String srchStr = "sssddsdsdsd";

    private static boolean printedEnv = false;

    public static int callCntIndex = 0;


    // /Users/hkon/sw/ustodo/110504utd/ustodo-1.1.1/grails-app/views/todo/index.gsp
    def testremote =
            {
                return "testremotexxx";
            }













    //@Secured(['ROLE_USER'])

    // see 140526TestAngularGetNotAjax.html
    def ajax_getTestAngularStyleGet =
            {
                try
                {
                    O.o("in ajax_getTestAngularStyleGet");
                    ArrayList arrayFlash = new ArrayList();
                    arrayFlash << "Caller [caller.ajax_getJSONTableData]";

                    java.util.LinkedHashMap ajaxWebMap = new java.util.LinkedHashMap();
                    ajaxWebMap.put("A", "1");
                    ajaxWebMap.put("B", "2");

                    def jsonStr = new grails.converters.JSON(ajaxWebMap).toString();
                    //O.oNoFilter("ajax_getJSONTableData jsonStr:"+jsonStr);

                    render ajaxWebMap as grails.converters.JSON
                } catch ( Exception e) {
                    O.or "fail1b", e          //def alFileLines = map.alFileLines
                }
            } // ajax_getJSONTableData



























    // AUTOCOMPLETE **********************************************************************************
    // AUTOCOMPLETE **********************************************************************************
    // AUTOCOMPLETE **********************************************************************************
    // AUTOCOMPLETE **********************************************************************************
    // AUTOCOMPLETE **********************************************************************************
    // AUTOCOMPLETE **********************************************************************************
    // AUTOCOMPLETE **********************************************************************************
    // AUTOCOMPLETE **********************************************************************************
    // AUTOCOMPLETE **********************************************************************************
    // AUTOCOMPLETE **********************************************************************************
    // used only by automplete/per-key to gen the list output html for when-typing mode (as opposed to after hit enter full search mode)

    @Secured(['ROLE_USER'])
    def ajax_jsontest = {
        def jsonObj = new JsonSlurper().parseText( '{ "name":"Peter", "age": 23}' )
        assert jsonObj.name == "Peter"
        assert jsonObj.age == 23
        //this won't work, because it's not defined
        assert jsonObj.gender == null

        render "ajax test success  jsonObj.age:" + jsonObj.age
    }





    def ajax_autocompleteSearchUpper =
            {
                O.o "130310 >>>> in ajax_autocompleteSearchUpper [" + springSecurityService.principal + "]"
                O.o ">>>> params [" + params + "]"

                def user = null;
                //O.oc("ajax springSecurityService:", springSecurityService) // grails.plugins.springsecurity.SpringSecurityService
                try {
                    // worked pre open id user = SecOldUser.get(springSecurityService.principal.id)
                    user = SecUser.get(springSecurityService.principal.id)
                    //O.oc("ajax user1:", user) // ocdesc <user1:> clsnm <org.example.SecOldUser> non AbstractCollection toStr <org.example.SecOldUser : 2>
                    //O.oc("ajax user2:", user.username) // ocdesc <user2:> clsnm <java.lang.String> non AbstractCollection toStr <ckckck>


                } catch ( Exception e) {
                    O.or "fail1d", e
                }
                //O.o ("this.metaClass:"+this.metaClass)
                if (Cfg.debugging.contains(this.metaClass.toString()))
                    O.o "ajax2 ajax_autocompleteSearchUpper @@@@@@@@@@@@@@@@@@@@@@@@@ new auth got user:"+user.username


                String sToRender = ProcessCommand_GetDbReadContrib.autoCompleteNonClosure (params, user) // sendtoout=true/false
                //O.o "<<<< done ajax_autocompleteSearchUpper [" + springSecurityService.principal + "]"
                if (sToRender != null)
                {
                    //O.o "return 1 from ajax_autocompleteSearchUpper:" + params['autocomp']
                    render sToRender;
                }
                else
                {
                    //O.o "return 2 from ajax_autocompleteSearchUpper:" + params['autocomp']
                    render ""; // just return if don't want to render anything
                }
            } // ajax_autocompleteSearchUpper

    @Secured(['ROLE_USER'])
    def ajax_clearCmdHist =
            {
                def username = SecUser.get(springSecurityService.principal.id).username
                O.o "in todoc.ajax_clearCmdHist username:" + username + ", testparam:" + params['testparam'] + ", testparam2:" + params['testparam2']
                def countPre = UtilMongo.collCount(UtilMongo.getColl("CmdHist_" + username));
                //O.o "in 1 ajax_clearCmdHist pre count [" + UtilMongo.collCount(UtilMongo.getColl("CmdHist_" + username)) + "]";
                UtilMongo.removeAll("CmdHist_" + username, false);
                O.o "done 2 ajax_clearCmdHist counts pre [" + countPre + "] post [" + UtilMongo.collCount(UtilMongo.getColl("CmdHist_" + username)) + "]";
            }

    def ajax_refreshCmdHist =
            {
                // http://localhost:8084/ustodo/todo/ajax_refreshCmdHist/autocomp?retaintechnique=hijoey
                def username = SecUser.get(springSecurityService.principal.id).username
                def retaintechnique = params['retaintechnique'];
                O.o "in 1 ajax_refreshCmdHist pre count [" + UtilMongo.collCount(UtilMongo.getColl("CmdHist_" + username)) + "] retaintechnique:"+retaintechnique;
                //def sb = new StringBuffer("[");
                String[] emptystrarr = {""};
                def selectResult = UtilMongo.select(  // cmd hist
                        "CmdHist_" + username,  //   String collname,
                        'tagOrAll',  //   String fieldToSrchOrNull,
                        emptystrarr,         //   String strSrchPreStarStar,
                        null,      //   String orderfieldOrNull,
                        -1, //   int orderdir,
                        -1, //   int limitIfGt0,
                        null, //   Str tm,
                        null //   dbo)
                )

                // O.oc ("selectResult :", selectResult );
                //selectResult.toj
                //selectResult.each {
                //O.o ("it:" + it);
                // 857. it:[_id:501ac5820364f36472ca26d3, tagOrAll:tony docs, date:2012-08-02_14_22_58, cmd:tony docs u]
                //   sb.append it.cmd.toString().replaceAll("\t", " ") + "\t"
                //};
                //sb.append "]"


                //repeat for JSON test
                selectResult = UtilMongo.select(  // cmd hist
                        "CmdHist_" + username,  //   String collname,
                        'tagOrAll',  //   String fieldToSrchOrNull,
                        '',         //   String strSrchPreStarStar,
                        null,       //   String orderfieldOrNull,
                        -1,         //   int orderdir,
                        -1, //   int limitIfGt0,
                        null, //   Str tm,
                        null //   dbo)
                )

                //O.oc ("selectResult :", selectResult );





                //selectResult.toj
                //grails.converters.JSON.Builder.cre
                //org.codehaus.jackson.map.ObjectMapper o =
                //com.fasterxml.jackson.core.JsonGenerator.

                def recentCmds = []
                selectResult.each {
                    O.o ("refresh recent it:" + it);
                    // 857. it:[_id:501ac5820364f36472ca26d3, tagOrAll:tony docs, date:2012-08-02_14_22_58, cmd:tony docs u]
                    //sb.append it.cmd.toString().replaceAll("\t", " ") + "\t"
                    recentCmds << it.cmd.toString()
                };
                //sb.append "]"





                O.o "done 2 ajax_refreshCmdHist post count [" + UtilMongo.collCount(UtilMongo.getColl("CmdHist_" + username)) + "]";
                //render "ajax_refreshCmdHist:" + sb;
                def maptest = [:]
                def strtest = 'hiagainjoe'
                maptest.henry = 'kon';
                //render "ajax_refreshCmdHist:" + sb
                //render "ajax_refreshCmdHist:" + sb as JSON;
                //render maptest as JSON;
                //render recentCmds as JSON;
                //render (template:"/some/template", collection:['foo', 'bar', 1342], var:'theItem')
                //render (template:"/templates/test_template", collection:['foo', 'bar', 1342], var:'theItem')
                render (template:"/templates/test_template", collection:recentCmds, var:'it')
            }


    @Secured(['ROLE_USER'])
    def ajax_clearCmdHist_one =
            {
                def username = SecUser.get(springSecurityService.principal.id).username
                def recentCmdToDel = params['recentCmdToDel'];
                //O.o "in 1 ajax_clearCmdHist_one to del recentCmdToDel [" + recentCmdToDel + "]";

                O.o "in 2 ajax_clearCmdHist_one to del [" + recentCmdToDel + "] pre count [" + UtilMongo.collCount(UtilMongo.getColl("CmdHist_" + username)) + "]";

                UtilMongo.remove("CmdHist_" + username, 'cmd', recentCmdToDel);
                O.o "done 3 ajax_clearCmdHist_one to del [" + recentCmdToDel + "] post count [" + UtilMongo.collCount(UtilMongo.getColl("CmdHist_" + username)) + "]";
                render "Removed recent [" + recentCmdToDel + "]"
            }

    def ajax_restoreCmdHist_one =
            {
                def username = SecUser.get(springSecurityService.principal.id).username
                def recentCmdToRestore = params['recentCmdToRestore'];
                //O.o "in 1 ajax_restoreCmdHist_one recentCmdToRestore:" + recentCmdToRestore;
                O.o "in 2 ajax_restoreCmdHist_one to recentCmdToRestore [" + recentCmdToRestore + "] pre count [" + UtilMongo.collCount(UtilMongo.getColl("CmdHist_" + username)) + "]";
                //
                try
                {
                    def beanCommandHistory = new BeanCommandHistory(recentCmdToRestore, 'tagOrAll');
                    beanCommandHistory.push(username);
                }
                catch (Exception err) {
                    O.or("error restoring", err);
                }
                //
                O.o "done 3 ajax_restoreCmdHist_one to del [" + recentCmdToRestore + "] post count [" + UtilMongo.collCount(UtilMongo.getColl("CmdHist_" + username)) + "]";
                render "Restored recent [" + recentCmdToRestore + "]"
            }


    def ajax_checkbox = // was autocompleteSearch2 =
            {
                def username = SecUser.get(springSecurityService.principal.id).username
                String autocomp_userInput_id = params['autocomp'];
                O.o("ajax_checkbox record id:" + autocomp_userInput_id)
                BasicDBObject dboFlr = UtilMongo.getFlrById( autocomp_userInput_id, username)
                //O.o("got ac2:" + listFlr.size())
                //def textcontents = "[" + "Hi there - this is your computer talking." + new java.util.Date() + "]";
                def textcontents = dboFlr.text;
                //`render 'nichts';
                def srtn = '<textarea name="topscratch" title="This &#39;lower&#39; box is an editor/scratch area.  \
            You can edit records here before saving.  Lower is seeded with the first search result (from below) as a template for a next search or save.\
             " id="fld2Lower" rows="3" cols="110" onclick="this.focus();this.select()" >' +
                        textcontents+ '</textarea> \
			'
                //O.o "a2 returning:" + textcontents
                render srtn
            } // autocompletesearch

    // DELETE
    def ajax_deleteButton =
            {
                O.o ("in ajax_deleteButton");
                String ajaxarg_idList = params['autocomp'];
                O.o ("in ajax_deleteButton:" + ajaxarg_idList );
                def username = SecUser.get(springSecurityService.principal.id).username
                O.o("autocompleteSearch3del ajaxarg_idList:" + ajaxarg_idList)
                int i = 0;
                ajaxarg_idList.split("_").each {
                    //UtilMongo.remove(message, message, message)
                    if (!it.trim().equals(""))
                    {
                        i++;
                        try {

                            UtilMongo.removeFavsRecById( it, username)
                        } catch (Exception e) {
                            render "<font color=red>delete failed for id [" + it + "]</font>"

                        }

                    }
                }
                O.o ("done ajax_deleteButton");
                render "";
            } // autocompletesearch


    def ajax_crudArchiveToggleOneRow  =
            {
                //O.o ("in ajax_touchSingleButton");
                String ajaxarg_idListUnderscoreDelimited = params['idListUnderscoreDelimited'];
                O.oNoFilter ("in ajax_crudArchiveOneRow ajaxarg_idListUnderscoreDelimited:" + ajaxarg_idListUnderscoreDelimited );
                def username = SecUser.get(springSecurityService.principal.id).username
                int i = 0;
                ajaxarg_idListUnderscoreDelimited.split("_").each {
                    //UtilMongo.remove(message, message, message)
                    if (!it.trim().equals(""))
                    {
                        i++;
                        O.oNoFilter("fake update of time within archive")
                        UtilMongo.updateRecordArchiveToggle(it, username, true)
                    }
                }
                O.o ("done ajax_deleteButton");
                render "<font color=green>touched [" + i + "]</font>"
            }


    // TOUCH SINGLE
    def ajax_touchSingleButton =
            {
                //O.o ("in ajax_touchSingleButton");
                String ajaxarg_idListUnderscoreDelimited = params['idListUnderscoreDelimited'];
                O.o ("in ajax_touchSingleButton ajaxarg_idListUnderscoreDelimited:" + ajaxarg_idListUnderscoreDelimited );
                def username = SecUser.get(springSecurityService.principal.id).username
                int i = 0;
                ajaxarg_idListUnderscoreDelimited.split("_").each {
                    //UtilMongo.remove(message, message, message)
                    if (!it.trim().equals(""))
                    {
                        i++;
                        UtilMongo.updateRecordTimestamp(it, username, UtilDate.getDateForFile())
                    }
                }
                O.o ("done ajax_deleteButton");
                render "<font color=green>touched [" + i + "]</font>"
            } // autocompletesearch


    // BULK TOUCH
    //public static boolean areWeInAsynch = false;
    def ajax_bulkTouchButton =
            {
                try
                {
//                if (areWeInAsynch)
//                {
//                    throw new Exception("areWeInAsynch");
//                }
//                areWeInAsynch = true;
                    //Thread.sleep(2000)

                    O.o ("in ddd ajax_bulkTouchButton params 1:" + params);
                    O.o ("in ddd ajax_bulkTouchButton params 2:" + springSecurityService.principal);
                    def username = SecUser.get(springSecurityService.principal.id).username
                    String ajaxarg_touchList = params['autocomp'];
                    O.o("in ajax_bulkTouchButton list:" + ajaxarg_touchList)
                    int i = 0;
                    def listids = []
                    ajaxarg_touchList.split("_").each {
                        //UtilMongo.remove(message, message, message)
                        if (!it.trim().equals(""))
                        {
                            listids << it.trim()
                            O.o "calling updateRecordTimestamp id = [" + it.trim() + "]"
                            UtilMongo.updateRecordTimestamp(it.trim(), username, UtilDate.getDateForFile());
                            i++
                        }
                    }

                    O.o "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx done ajax_bulkTouchButton for [" + i + "] records listids:"+listids

                    render "<font color=green>touched [" + i + "] records</font>"
                } catch (Exception e ) {
                    O.or("fail in ajax_bulkTouchButton", e);
                    throw e;
                }
                finally {
                    areWeInAsynch = false;
                }
            }
    // COPYONEROW
    def ajax_insert =
            {
                try
                {

                    O.o ("in ddd ajax_insert params 1:" + params);
                    O.o ("in ddd ajax_insert params 2:" + springSecurityService.principal);
                    //data:'autocomp=' + sDbIdListToBulkOperateOn_underscoreDelimited+', lineMinusDateStr=' + newJsonObj.lineMinusDateStr,
                    def username = SecUser.get(springSecurityService.principal.id).username
                    String ajaxarg_touchList = params['autocomp'];
                    String newtext = params['newtext'];
                    O.o("in ajax_insert list:" + ajaxarg_touchList)
                    O.o("in ajax_insert newtext:" + newtext)
                    int i = 0;





                    BasicDBObject doc = new BasicDBObject(hm);
                    def cnt = dbc.count()

                    //O.o "dbc insert 3"
                    //dbc.setWriteConcern(WriteConcern.FSYNC_SAFE);
                    //dbc.insert(doc);
                    dbc.insert(doc);

                    def cnt2 = dbc.count()
                    //UtilAssert.assertx("db insert to coll [${dbc.getName()}] failed?: cnt+1 SHOULD == cnt2, BUT :" + (cnt+1) + " != " + cnt2, (cnt+1)==cnt2);

                    // add to category table
                    if (username != null)
                    {
                        HashMap hmCateg = UtilUtd_DBExportToCategColl.getCategHMFromFLR(filelinerawstr_wDate, username)
                        if (hmCateg != null)
                        {
                            BasicDBObject dboCateg = new BasicDBObject(hmCateg);
                            DBCollection collfavscategs = UtilMongo.getColl("favscategs")
                            cnt = collfavscategs.count()
                            //O.o "dbc insert 4"

                            collfavscategs.insert(dboCateg);
                            cnt2 = collfavscategs.count()
                            UtilAssert.assertx("db insert to [${collfavscategs.getName()}] failed?: cnt=cnt2", (cnt+1)==cnt2);

                            UtilUtd_DBExportToCategColl.add_DatestrToCateg(hmCateg.get("username"), hmCateg.get("date"), hmCateg.get("categ") )
                        }
                    }









                    render "<font color=green>touched [" + i + "] records</font>"

                } catch (Exception e ) {
                    O.or("fail in ajax_insert", e);
                    throw e;
                }
                finally {
                    areWeInAsynch = false;
                }



                //query.put("", new BasicDBObject("$in", listids));  // e.g. find all where i > 50
//
//            cursor = coll.find(query);
//
//            try {
//                while(cursor.hasNext()) {
//                    System.out.println(cursor.next());
//                }
//            } finally {
//                cursor.close();
//            }


            } // bulk touch


    //    +-+-+-+ +-+-+-+-+ +-+-+-+-+
    //    |G|e|t| |J|s|o|n| |D|a|t|a|
    //    +-+-+-+ +-+-+-+-+ +-+-+-+-+
    // multivalued return object
    public static class RtnValGenAutoCompHTML
    {
        public RtnValGenAutoCompHTML(String html, int count) {
            super();
            this.html = html;
            this.count = count;
        }
        public String html= null;
        public int count = 0;

        public String toString()
        {
            return O.fmt("count", count) + O.fmt("html", html);
        }

    }




    public static genAutoCompHTML(String autocomp_userInput, String fqfilename, List alFileLines, long maxCountPostSearchFilter)
    {
        StringBuffer sbRtnHtml = new StringBuffer();
        // cache file in mem
        //session.setAttribute "alfilelines", alFileLines

        // special case for welcome utd help

        int iCnt = 0;
        alFileLines.each {
            String fileLine = it;
            //o("in todo (((((((((((((((( autoCompleteLineOut [" + autoCompleteLineOut + "]");

            // hbk
            if (fileLine.length() > 20 && UtilSearch.match ( fileLine, autocomp_userInput))
            {
                String lineToAddToOutput = UtilDate.renderAgeAsLetterFromNowToFileDateStr(fileLine [0..18]) + " &nbsp;" + fileLine [20..-1]
                // hbk
                //o "{{{{{{{{{{{{{{{ 1111 [" + lineToAddToOutput+ "]"
                // ********************************************************
                // STEP 1 - CONVERT/COMPILE LINKS (HTTP://...) TO HREF (<A HREF...)
                // ********************************************************
                lineToAddToOutput = UtilURL.compileLinksToHREFs (lineToAddToOutput);
                //o "{{{{{{{{{{{{{{{ 2222 [" + lineToAddToOutput+ "]"
                //alLinesReturn.add lineToAddToOutput;
                //O.o ("222222222222222222222222222params['textstr']:" + params['textstr'])
                //O.o ("333333333333333333333333333params.cbhilite:" + params.cbhilite)
                //if ("true".equals (params.cbhilite))

                // ********************************************************
                // STEP 2 - COLOR HILITE HIGHLGHT SEARCH TERMS
                // ********************************************************
                // GREEN OFF autoCompleteLineOut = UtilSearch.colorTags(autoCompleteLineOut, autocomp_userInput, "green");
                //lineToAddToOutput = UtilSearch.colorTags(lineToAddToOutput, autocomp_userInput, "green");
                // = tagOrAll.replaceAll (autocomp_userInput,"<font color=blue>"+autocomp_userInput+"</font>");

                //sbRtnHtml.append ( "<br>" );

                if (iCnt++ %2 == 0)
                    sbRtnHtml.append("<font color='black'> ")
                else
                    sbRtnHtml.append("<font color='rgb(88,88,88)'> ")


                sbRtnHtml.append ("<br>" + iCnt + ". " + lineToAddToOutput + "&nbsp;&nbsp;");
                sbRtnHtml.append (" </font> ");
            }
        }
        return new RtnValGenAutoCompHTML(sbRtnHtml.toString(), iCnt);
    }
    // END AUTOCOMPLETE **********************************************************************************



    // END AUTH / FILE ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((


    public static void main (String[] args)
    {
        O.o( '<textarea name="topscratch" title="This &#39;lower&#39; box is an editor/scratch area.  \
You can edit records here before saving.  Lower is seeded with the first search result (from below) as a template for a next search or save.\
 " id="fld2topscratch" rows="3" cols="110" onclick="this.focus();this.select()" >Tttrrr / Hhhh::, Sender [7814054265@messaging.sprintpcs.com] \
ReceivedDate [Thu Mar 01 04:56:16 EST 2012]::msg</textarea> \
		')

    }
}
