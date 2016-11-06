package com.ustodo.command

import com.ustodo.utilg.O;


import com.ustodo.utilg.UtilCons;
import com.ustodo.utilg.UtilDate
import com.ustodo.utilg.UtilStr
import com.ustodo.utilg.UtilURL

class CommandMetaData
{

    public static class CommandMetaDataRecord {
        String name;
        String code;
        String urlPreAmble;
        String urlPostAmble;
        String group;

        CommandMetaDataRecord(String name, String code, String urlPreAmble, String urlPostAmble, String group) {
            this.name = name
            this.code = code
            this.urlPreAmble = urlPreAmble
            this.urlPostAmble = urlPostAmble
            this.group = group
        }
    }

    private static Vector vCommandMetaDataRecords = null;
    public static CommandMetaDataRecord defaultCommandMetaDataRecord_search =
         new CommandMetaDataRecord("UsToDo (search)","u",null,null,  UtdCmdProc.CMDTYPE_W)

    public static synchronized Vector getvCommandMetaDataRecords()
    {
        if (vCommandMetaDataRecords == null)
        {
            //String metaPrePreAmble = "<metaPrePreAmble HTTP-EQUIV=\"REFRESH\" content=\"0; url="
            String metaPrePreAmble = ""
            // ucp.htmlredirect = 		"<metaPrePreAmble HTTP-EQUIV=\"REFRESH\" content=\"0; url=javascript:window.open('"+s+"','_parent');\">";

            Vector v = new Vector();
            //	see above private static final int ModeSchema_0NAME = 0
            //	private static final int ModeSchema_1CMDCODE = 1
            //	private static final int ModeSchema_2HEAD = 2
            //	private static final int ModeSchema_3TAIL = 3
            //	private static final int ModeSchema_4NUMID = 4
            //	private static final int ModeSchema_5GROUP = 5 // std redirect is "r", write is "w"
            v.add(new CommandMetaDataRecord("Amazon", "a",metaPrePreAmble+"http://www.amazon.com/s?field-keywords=","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Domain(LeanDomain)","dm",metaPrePreAmble+"http://www.leandomainsearch.com/search?q=","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>Search",     "g",metaPrePreAmble+"https://www.google.com/search?q=","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Bing",              "b",metaPrePreAmble+"http://www.bing.com/?q=","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("CraigsBoston",      "cr",metaPrePreAmble+"http://boston.craigslist.org/search/?areaID=4&subAreaID=&query=","&catAbb=sss", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Wikipedia",         "k",metaPrePreAmble+"http://en.wikipedia.org/wiki/","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>Contacts",   "cn",metaPrePreAmble+"https://mail.google.com/mail/#contacts/search/","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Ebay",              "eb",metaPrePreAmble+"http://www.ebay.com/sch/i.html?_nkw=","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>Mail",       "e",metaPrePreAmble+"https://mail.google.com/mail/#search/","", UtdCmdProc.CMDTYPE_R ));
            v.add(new CommandMetaDataRecord("Google>Docs",       "d",metaPrePreAmble+"https://drive.google.com/?tab=po&authuser=0#search/","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>Calendar",   "c",metaPrePreAmble+"https://www.google.com/calendar/render?tab=oc&q=" ,"", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>Images",     "i",metaPrePreAmble+"https://www.google.com/search?q=","&view=0&tbm=isch&source=og", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>Maps",       "m",metaPrePreAmble+"http://maps.google.com/maps?q=","&view=0&hidden=1", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>Plus",       "gp",metaPrePreAmble+"https://plus.google.com/s/","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>Reader",     "gr",metaPrePreAmble+"http://www.google.com/reader/view/#search/","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>Scholar",    "gs",metaPrePreAmble+"http://scholar.google.com/scholar?q=","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>Tasks",      "t",metaPrePreAmble+"https://mail.google.com/tasks/m?pli=1?SAVE_WHATS_TO_THE_RIGHT_HERE_(YOUR_NAME_HERE)=","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>Youtube",    "y",metaPrePreAmble+"http://www.youtube.com/results?search_query=","&view=0&hidden=1", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>News",       "n",metaPrePreAmble+"http://news.google.com/?q=","&view=0&hidden=1", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Google>Books",      "gb",metaPrePreAmble+"https://www.google.com/search?q=","&btnG=Search+Books&tbm=bks&tbo=1", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("NewEgg",            "ne",metaPrePreAmble+"http://www.newegg.com/Product/ProductList.aspx?Submit=ENE&DEPA=0&Order=BESTMATCH&Description=","=0&y=0", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("NYT",               "ny",metaPrePreAmble+"http://query.nytimes.com/gst/sitesearch_selector.html?query=","&type=nyt&x=0&y=0", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Reddit",            "r",metaPrePreAmble+"http://www.reddit.com/search?q=","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("SamAshMusic",       "sa",metaPrePreAmble+"http://www.samash.com/webapp/wcs/stores/servlet/CatalogSearchResultView?storeId=10052&catalogId=10002&langId=-1&pageSize=12&beginIndex=0&sType=SimpleSearch&resultCatEntryType=2&ipstate=&iptext=","&ip_requestUri=TopCategoriesDisplay&ip_categoryId=&ip_mode=&x=0&y=0&wcsiptext=&iscached=)", "r"));
            v.add(new CommandMetaDataRecord("Google>Shopping","s",metaPrePreAmble+"https://www.google.com/search?q=","&tbm=shop", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Facebook","f",metaPrePreAmble+"https://www.facebook.com/search/results.php?q=","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Twitter","tw",metaPrePreAmble+"https://twitter.com/#!/search/","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Weather","wt",metaPrePreAmble+"http://weather.com","", UtdCmdProc.CMDTYPE_R));
            v.add(new CommandMetaDataRecord("Thesaurus","th",metaPrePreAmble+"http://www.merriam-webster.com/dictionary/","",  UtdCmdProc.CMDTYPE_R));

            // WRITERS
            v.add(new CommandMetaDataRecord("UsToDo Write","w",null,null, UtdCmdProc.CMDTYPE_W)); // To Do why is this a 'W'?
            v.add(new CommandMetaDataRecord("UsToDo Mail Check","e3",null,null, UtdCmdProc.CMDTYPE_W)); // in that it writes the DB - not implemented and this is NOT e2
            v.add(defaultCommandMetaDataRecord_search);

            // "U"tilities
            v.add(new CommandMetaDataRecord("UsToDo (hotlink)","h",null,null, UtdCmdProc.CMDTYPE_H));
            v.add(new CommandMetaDataRecord("UsToDo (sendmail)","sm",null,null, UtdCmdProc.CMDTYPE_U));

            // VALIDATION: NO DUPS
            Set<String> setCheckIfAnyCommandDups = new HashSet<String>();
            v.elements().each { o ->
                CommandMetaData.CommandMetaDataRecord commandMetaDataRecord = o;
                if (setCheckIfAnyCommandDups.contains(commandMetaDataRecord.code))
                    throw new Exception("more than one Command Record with code [" + commandMetaDataRecord.code + "]");
                // check for match
//                    if (arrCmdSplitBySpace_toFindCommand[0].equals(commandMetaDataRecord.code) || code.equals(commandMetaDataRecord.code))
//                        cmdfoundOnInitScan_toSeeIfWeWantAddU = true
                setCheckIfAnyCommandDups.add(commandMetaDataRecord.code)
            }



            vCommandMetaDataRecords = v;
        }
        return vCommandMetaDataRecords;
    }
     
     public static void getMatchingCommandRefData(code)
     {
         getvCommandMetaDataRecords().elements().each { o ->
             CommandMetaData.CommandMetaDataRecord commandMetaDataRecord = o;
             if (setCheckIfAnyCommandDups.contains(commandMetaDataRecord.code))
                 throw new Exception("more than one Command Record with code [" + commandMetaDataRecord.code + "]");
             // check for match
//                    if (arrCmdSplitBySpace_toFindCommand[0].equals(commandMetaDataRecord.code) || code.equals(commandMetaDataRecord.code))
//                        cmdfoundOnInitScan_toSeeIfWeWantAddU = true
             setCheckIfAnyCommandDups.add(commandMetaDataRecord.code)
         }
     }

}
