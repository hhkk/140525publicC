package com.ustodo.utilg

import com.mongodb.BasicDBObject

class UtilUtd_FileImportToDB {
	// see also UtilMongo_File_Email_Db


	public static void main (String[] args)
	{
		try
		{
			doWork();
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

	public static String doWork()
	{
		
		
		if (false) // test replace hat with comma
		{
			def filStrLst = UtilFile.fileAsList(Cfg.fnFavsCkckck);
			Set hsstrsight = new HashSet();
			filStrLst.eachWithIndex { it, i ->
				BasicDBObject dboflr = UtilFileLineRaw.cvtFileLineRawToDbo (it, i)
				String datetext = dboflr.date + " " + dboflr.text;
				hsstrsight.add(datetext)
			}
			
			filStrLst.eachWithIndex { it, i ->
				BasicDBObject dboflr = UtilFileLineRaw.cvtFileLineRawToDbo (it, i)
				String datetext = dboflr.date + " " + dboflr.text;
				String datetextNoHat = datetext.replace('^', ',')
				if (datetext.indexOf('^') > 0 && hsstrsight.contains(datetext) != null && hsstrsight.contains(datetextNoHat) != null )
				{
					O.o( "pseudomatch found datetext [${datetext}] ")
				}
			}
			
			//String textRepl = text.replace('^', ',')
			//if (!textRepl.equals(text))
			
		}
        else if (true) // production load of file to DB
        {
            Profiler.check"top UtilUtd_FileImportToDB"
            O.o "TOP ################################################################################################"
            UtilFileLineRaw.insertFileIntoColl_ViaFileLineRaw(Cfg.fnFavsCkckck, UtilMongo.getColl(Cfg.collnameFavsCkckck))
            //		O.o "TOP ################################################################################################"
            //			UtilFileLineRaw.insertFileIntoColl_ViaFileLineRaw(Cfg.fnFavsCkckck, UtilMongo.getColl(Cfg.collnameFavsCkckck))
            //		O.o "TOP ################################################################################################"
            //			UtilFileLineRaw.insertFileIntoColl_ViaFileLineRaw(Cfg.fnFavsCkckck, UtilMongo.getColl(Cfg.collnameFavsCkckck))
            //		O.o "TOP ################################################################################################"
            //			UtilFileLineRaw.insertFileIntoColl_ViaFileLineRaw(Cfg.fnFavsCkckck, UtilMongo.getColl(Cfg.collnameFavsCkckck))
            //		O.o "TOP ################################################################################################"
            //			UtilFileLineRaw.insertFileIntoColl_ViaFileLineRaw(Cfg.fnFavsCkckck, UtilMongo.getColl(Cfg.collnameFavsCkckck))
            Profiler.check"done UtilUtd_FileImportToDB"
        }


        O.o "done 2"

	}

}
