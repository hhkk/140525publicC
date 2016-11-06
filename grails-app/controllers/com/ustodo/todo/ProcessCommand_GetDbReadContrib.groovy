	package com.ustodo.todo

    import com.mongodb.BasicDBObject
    import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.ustodo.utilg.Cfg
import com.ustodo.utilg.FileLine
import com.ustodo.utilg.O;
import com.ustodo.utilg.Profiler
import com.ustodo.utilg.UtilDate;
import com.ustodo.utilg.UtilFileLineRaw;
import com.ustodo.utilg.UtilMongo
import com.ustodo.utilg.UtilPerf
import com.ustodo.utilg.UtilSearch
import com.ustodo.utilg.UtilStr;
import com.ustodo.utilg.UtilURL;
import com.ustodo.utilg.UtilUtd_DBExportToCategColl;

class ProcessCommand_GetDbReadContrib {

	public static String WRITE_SUFFIX = " w";
	public static String WRITE_PREFIX = "w ";

    public static ArrayList getDbReadContrib(
          String callerId,
          ArrayList<FileLine> alFileLines_OUT,
          String txtUpper,
          Map maputdoptions,
          String collname,
          Map params,  //org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap params,
          boolean profileroutput,
          Vector vOutOneElem_textTopHitForLower,
          String usernameForLastSearchedForCache,
          //List<BasicDBObject> listdboOUTForIndexHtml,
          DBCollection dbc,
          maxRecordsWanted)
    {



        if (txtUpper.startsWith("speciallogin505"))
        {
            O.o "in if speciallogin505" // speciallogin505 [mkon]
            String username = UtilStr.keepAllAfterFirstOfThis(txtUpper, "[");
            username = UtilStr.keepAllBeforeFirstOfThisNew(username, "]");
            collname = "favs" + username
            txtUpper = "*"

        }
        else
        {
//            txtUpper = "*"
            //O.o "not in speciallogin505"
        }

        return getDbReadContribLocal(
            callerId,
            alFileLines_OUT,
            txtUpper,
            maputdoptions,
            collname,
            params,
            profileroutput,
            vOutOneElem_textTopHitForLower,
            usernameForLastSearchedForCache,
            //listdboOUTForIndexHtml,
            dbc,
                maxRecordsWanted)

    }




    private static ArrayList getDbReadContribLocal(
        String callerId,
        ArrayList<FileLine> alFileLines_OUT,
        String txtUpper,
        Map maputdoptions,
        String collname,
        Map params, //     org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
        boolean profileroutput,
        Vector vOutOneElem_textTopHitForLower,
        String usernameForLastSearchedForCache,
        //List<BasicDBObject> listdboOUTForIndexHtml,
        DBCollection dbc,
        int maxRecordsWanted)
	{
        def timeStart = new Date();
        O.o (" in getDbReadContribLocal maxRecordsWanted:" + maxRecordsWanted);
        //        txtUpper + "] callerId [" + callerId + "] maputdoptions [" + maputdoptions + "]");


        txtUpper = txtUpper.trim()
        if (txtUpper.equals(""))
            txtUpper = "*"
        BasicDBObject dboCaller = null
        if (txtUpper.equals("*"))
        {
            txtUpper = "*"
            dboCaller = new BasicDBObject();
            dboCaller.put("archived", new BasicDBObject('$ne', new Boolean("true")));
        }

        //O.o ("in xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx collname:" + collname);
        //O.o "--d getDbReadContrib 1 txtUpper:" + txtUpper
		//O.o "--d getDbReadContrib 3 maputdoptions:" + maputdoptions +", --d getDbReadContrib 5 params:" + params + " usernameForLastSearchedForCache:" + usernameForLastSearchedForCache
        ProcessCommand.hsSearchedTextLast.put(usernameForLastSearchedForCache, txtUpper)
		//O.o "--d getDbReadContrib 4 collname:" + collname

		//O.o "--d getDbReadContrib 6 profileroutput:" + profileroutput


		def rtn = [];
		int iCntDBLines = 0;
		int iCntDBLinesMatch = 0;
		UtilPerf.perfMsgsForUIAppend "<br> / " + Profiler.check(" ** start db read", profileroutput);


		//DBCollection dbc = UtilMongo.getOrCreateDBAndColl("db read for txtUpper", Cfg.dbname, collname);
		//O.o("size match on db read:" + (confirmsize == dbc.count))
		iCntDBLines = dbc.count;
		DBCursor cur = null;
		//		if (txtUpper.equals("*"))
		//			cur = dbc.find()
		//		else
        String[] sArrTxtUpperLcaseSplit = txtUpper.toLowerCase().split(" ");
        //O.oc ("txtUpper:", rtnSearchN®ow)


		if (dbc.count() < 4)//if fewer than 4 records (ie at user startup) then create an index
		{
			UtilMongo.ensureIndex(dbc, "date")
			O.o "****** completed index"
		}


        //O.o ("******************************************************* maputdoptions.get(\"tm\"):" + maputdoptions.get("tm"))
		cur = UtilMongo.select( // real app fetch of file line records
				collname,
				"filelineraw",
                sArrTxtUpperLcaseSplit,
				"date",
				0,
				0,
				maputdoptions.get("tm"),
                dboCaller// dbo
                )
		//UtilFile.createEmptyFileWithDateDeleteIfNeeded("/tmp/ttd.txt")

		int i2	 = -1;
		// LOOP
        O.oNoFilter("curcount cur.count():" + cur.count())
		while (cur.hasNext())
		{
			i2++
            if (false)   // test
            {
                def dboDbFlr = cur.next();
            }
            else // read
            {
                def dboDbFlr = cur.next();

                //O.oc("dboDbFlr:" + dboDbFlr)



                def dboText = dboDbFlr.text.toString().trim();
                def textCat = UtilStr.keepAllBeforeLastOfThis(dboText, " / ")
                String fileLineRaw_orFileLineRawCatOnly_reconstituted = null;

                // 73 OK
                // 74 not
                //if (iCntDBLinesMatch > 5 )
                //   break;
                if (maxRecordsWanted > 0 && iCntDBLinesMatch >= maxRecordsWanted) // was 199 not 200
                {
                    //O.o "MAXED OUTPUTS"
                    break;
                }

                //O.o (" 1111111111111111112222222222222222222 -------------- dboDbFlr.keySet():" + dboDbFlr.keySet());

                fileLineRaw_orFileLineRawCatOnly_reconstituted = dboDbFlr.date.toString().trim() + " " + dboText;

                def gotFLR = fileLineRaw_orFileLineRawCatOnly_reconstituted

                // for user ckckck, cap * mode at 1000
                if (gotFLR != null)
                {
                    //UtilFile.writeAppend("/tmp/ttd.txt", gotFLR + "\r\n")
                    iCntDBLinesMatch++;



                    String fileLineRawURL = null;
                    // LOOP
                    if (iCntDBLinesMatch == 1)
                        vOutOneElem_textTopHitForLower.add(fileLineRaw_orFileLineRawCatOnly_reconstituted)
                    //fileLineRawURL = fileLineRaw_orFileLineRawCatOnly_reconstituted;
                    fileLineRawURL = UtilURL.compileLinksToHREFs (fileLineRaw_orFileLineRawCatOnly_reconstituted);
                    if (!fileLineRawURL.trim().equals(fileLineRaw_orFileLineRawCatOnly_reconstituted.trim()))
                        //O.oNoFilter("unequal on fileLineRawURL:" + fileLineRawURL);
                    if (fileLineRawURL != null)
                        fileLineRaw_orFileLineRawCatOnly_reconstituted = fileLineRawURL;
                    //O.o("add:" + " ( ================ from DB oldway[${oldway}] [" + i + "] " + fileLineRaw)
                    fileLineRaw_orFileLineRawCatOnly_reconstituted = procNC(params, fileLineRaw_orFileLineRawCatOnly_reconstituted)







                    FileLine fl = new FileLine(i2, fileLineRaw_orFileLineRawCatOnly_reconstituted, dboDbFlr._id, dboDbFlr)
                    O.o ("adding to fl.dbid:"+fl.dbid + ", firstWordInSearch.toLowerCase():" + sArrTxtUpperLcaseSplit + ", alFileLines_OUT.size():"+alFileLines_OUT.size());


                    alFileLines_OUT.add (fl);

                    //def o = UtilFileLineRaw.cvtFileLineRawToDbo(fileLineRaw_orFileLineRawCatOnly_reconstituted, iCntDBLinesMatch)
                    //O.o "----------------- dboDbFlr.keySet():" + dboDbFlr.keySet()
                    //O.o "----------------- dboDbFlr.get('\$oid'):" + dboDbFlr.get("\$oid")
                    ///o.put("oooooooooooooooooooo hkoid", dboDbFlr.get("_id"))
                    alFileLines_OUT

                    //o.put("hkoid", "sdsdsdsdsd")
                    //listdboOUTForIndexHtml.add ( o )
                }

                //O.o "reverse 4"
                //o("fileLineRaw: "+fileLineRaw);

            } // else
		} // while cur.hasnext

		String s1 = Profiler.check("done db read cnt [${alFileLines_OUT.size()}] ", profileroutput);
		UtilPerf.perfMsgsForUIAppend "<br> / " + s1;
        //O.oNoFilter("s1hk:" + s1);

		rtn << iCntDBLines;
		rtn << iCntDBLinesMatch;
        def timeElapsedDBread = (new Date()).getTime() - timeStart.getTime()

        O.oNoFilter ("done db read in [" + timeElapsedDBread + "] for [" + sArrTxtUpperLcaseSplit  + "] i2+1 ["+ (i2+1) + "] iCntDBLinesMatch ["+iCntDBLinesMatch+"]");

        rtn
	}


	private static String procNC(def params, def fileLineRaw)
	{
		boolean nc1 = false;
		boolean nc2 = false;
		if (params.utdoptions != null)
		{
			nc1 = params.utdoptions.contains(" nc1 " ) || params.utdoptions.startsWith("nc1 ") || params.utdoptions.endsWith(" nc1");
			nc2 = params.utdoptions.contains(" nc2 " ) || params.utdoptions.startsWith("nc2 ") || params.utdoptions.endsWith(" nc2");
			if (nc1 || nc2)
			{
				String rest = fileLineRaw[19..-1]
				fileLineRaw = fileLineRaw[0..18] + " " + UtilStr.keepAllAfterLastOfThis(rest, " / ")
			}
			if (nc2)
			{
				String rest = fileLineRaw[19..-1]
				fileLineRaw = fileLineRaw[0..18] + " " + UtilStr.keepAllAfterLastOfThis(rest, " // ")
			}
		}
		fileLineRaw

	}

	private static int callCntAC = 0;
	private static int callCntACExecuted = 0;


	public static String autoCompleteNonClosure(def params, def user)
	{
		def collnameNewauth = "collname not using new auth";
		def fqfilename = "fqfilename not using new auth"

		def usernameNewauth = user.username;
		collnameNewauth = Cfg.getCollNameThisUserFavs(usernameNewauth)
		fqfilename = Cfg.getFqFileName(usernameNewauth)


		StringBuffer sbRtnHtml = null;

		callCntAC++;
		String autocomp_userInput = params['autocomp'];
        //O.o("``in autoCompleteNonClosure user:" + user + ", autocomp_userInput:" + autocomp_userInput)
		autocomp_userInput = autocomp_userInput.trim()

		boolean hasTextChanged = false; // assume user has not changed input to autosearchajax

		String prevsrch = ProcessCommand.hsSearchedTextLast.get(usernameNewauth)
		if (prevsrch != null)
			prevsrch = prevsrch.trim();
		String autocomp_userInput_trim = autocomp_userInput;
		if (autocomp_userInput_trim != null)
			autocomp_userInput_trim = autocomp_userInput.trim();

		//O.o("in autoCompleteNonClosure2 prevsrch:" + prevsrch)
		if (prevsrch == null || (!prevsrch.equals (autocomp_userInput_trim) && !autocomp_userInput_trim.startsWith(prevsrch)))
		{
				//O.o("autoSilence CHANGE: usernameNewauth:" + usernameNewauth  + " autocomp_userInput [" + autocomp_userInput + "]")
			hasTextChanged = true;
		}
		else
		{
				//O.o("autoSilence NOCHANGE: usernameNewauth:" + usernameNewauth  + " autocomp_userInput [" + autocomp_userInput + "]")
		}

		// TURNED OFF WITH FALSE BELOW !!!!  erJAN 13 2012
		//O.os "AUTO-CHECK autocomp_userInput", autocomp_userInput
		//O.o "AUTO-CHECK hasTextChanged:" + hasTextChanged + " based on autocomp_userInput [" + autocomp_userInput_trim +"] vs [" + prevsrch + "]";
		String s = autocomp_userInput.trim()
		boolean endsWithSpaceOrW = s.endsWith(WRITE_SUFFIX) || s.endsWith(" ")
		boolean beginsWithW = s.startsWith(WRITE_PREFIX);
		//O.os "AUTO-CHECK endsWithSpaceOrW", endsWithSpaceOrW
		if (autocomp_userInput.trim().length() > 0 && hasTextChanged && !(endsWithSpaceOrW || beginsWithW)) // not space or w - we care so no ajax overwrite
		{
			//O.o "AUTO-CHECK autocomplete not skip"
			//autocomp_userInput = UtilStr.keepAllButFirstX( autocomp_userInput, 0).trim()
			if (autocomp_userInput.trim().equals(""))
			{
				//	O.o "AUTOCOMPLETE nothing in ajax amode post [${autocomp_userInput.trim()}]"
				//render "nuttin"
				return;
			}
			//	O.o "AUTOCOMPLETE something in ajax amode post [${autocomp_userInput}]"

			//else - gnerate content for return based on string match
			HashMap<String, String> userCategMap = UtilUtd_DBExportToCategColl.getUserMap_DatestrToCateg_createIfNotInMemYet(usernameNewauth)
			String autocomp_userInputLowerTrimmed = params['autocomp'].toString().trim().toLowerCase();
			if (userCategMap == null)
				O.o("userCategMap is null");
			sbRtnHtml = getAjaxCatSet(userCategMap, autocomp_userInputLowerTrimmed)
			//StringBuffer sbRtnHtml = new StringBuffer("Category search (ajax) [${autocomp_userInput}]:");


			//O.o( "AUTOCOMPLETE done sbRtnHtml.toString():" + sbRtnHtml.toString())
			return sbRtnHtml.toString()

			// EARLY RETURN ABOVE !!!!
			// EARLY RETURN ABOVE !!!!
			// EARLY RETURN ABOVE !!!!
			// EARLY RETURN ABOVE !!!!
			// EARLY RETURN ABOVE !!!!
			// EARLY RETURN ABOVE !!!!

		} else {
			//O.o "AUTO-CHECK autocomplete skip"
			//render "";
			//throw new Exception("erra")
			return null;
		}
		//else
		//	o("too short for autocomplete [" + autocomp_userInput+ "]");

	}


	private static StringBuffer getAjaxCatSet (HashMap<String, String> userCategMap, String autocomp_userInputLowerTrimmed)
	{
		//O.o("TOP AJAX GET CAT SET ")
		StringBuffer sbRtnHtml = new StringBuffer("Matching categories (${autocomp_userInputLowerTrimmed})");
		int keptcount = 0;

		Set seenAlready = new HashSet(); // this dedup may be redundant with one in getUserMap_DatestrToCateg_createIfNotInMemYet
		userCategMap.keySet().sort().reverse().eachWithIndex { it2, i ->
			String value = userCategMap.get (it2.toString())
			if (value != null)
			{
				String valuelower = value.toLowerCase();
				if (UtilStr.matchesGoogleStyle(valuelower, autocomp_userInputLowerTrimmed, i) || autocomp_userInputLowerTrimmed.equals("*"))
				//if (valuelower.contains(autocomp_userInputLowerTrimmed) || autocomp_userInputLowerTrimmed.equals("*"))
				{

					if (!seenAlready.contains(valuelower))
					{
						keptcount++;
						String age = UtilDate.renderAgeAsLetterFromNowToFileDateStr(it2)
						String href = UtilURL.buildHtmlHrefUtdLink(value, true, 50)
						// String htmlAjaxLine = "<br>" + keptcount + ". [${age}] [" + href + "]";
						String htmlAjaxLine = "<br>" + i + "&nbsp;" + href;
						sbRtnHtml.append(htmlAjaxLine)
						seenAlready.add(valuelower)
					}
					//O.o "AUTOCOMPLETE match  [${value}] vs [${autocomp_userInput}]"
				}
			}
			else
			{
				//O.o "AUTOCOMPLETE no match  [${value}] vs [${autocomp_userInput}]"

			}
		}
		if (keptcount == 0)
			sbRtnHtml.append "<br>no matching categories"
//			sbRtnHtml.append "<br>no matching categories for [${autocomp_userInputLowerTrimmed}]"

		//O.o "ajax cat set returning:" + sbRtnHtml.toString()

		sbRtnHtml
	}


	public static void main(String[] args)
	{
        //		DBCursor cur = UtilMongo.select(  // test
        //				"favsckckck",
        //				"filelineraw",
        //				"tester",
        //				"tester2",
        //				"date",
        //				0,
        //				0,
        //				null //Str tm
        //				);
        //		while (cur.hasNext())
        //		{
        //			def dboDbFlr = cur.next();
        //			O.o "dboDbFlr.keySet:" + dboDbFlr.keySet()
        //			O.o "   dboDbFlr.get('_id'):" + dboDbFlr.get("_id")
        //
        //
        //		}
	}
}
