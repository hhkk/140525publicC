package com.ustodo.utilg.user


import com.mongodb.DBCollection
import com.ustodo.utilg.Cfg
import com.ustodo.utilg.Dbo
import com.ustodo.utilg.O
import com.ustodo.utilg.UtilFile
import com.ustodo.utilg.UtilMongo
import com.ustodo.utilg.UtilFileLineRaw


class U111124 {
	private static boolean loadedConfig = Cfg.configSlurpDoIt();

	public static void main(String[] args)
	{
		try {
			//	T111121ScriptDriver_Tests.test1Common();
			// see also T111121ScriptDriver_Tests
			// just get a json doc
			List l = UtilFile.fileAsList(Cfg.fnFavsCkckck,-1);
			//l = l.reverse()
			DBCollection dbc = UtilMongo.getColl("TestScriptDriver")
			l.eachWithIndex  { it, i ->
				//UtilFileLineRaw.cvtFileLineRawToDbo(l[0], 0)
				//int i = 35168;
				
				def lproc = l[i];
				//def lproc = it;
				
				//O.o "class:" + l.getClass().getName();
				//O.o("processingfile:" + lproc)
				//O.oc("l:", l)
				Dbo dbo = UtilFileLineRaw.cvtFileLineRawToDbo(lproc, 0)
				dbc.insert(dbo)
				//O.o "saved from file to DB:" + l[0]
				Dbo dbo1p5 = new Dbo();
				dbo1p5.put("filelineraw", lproc);
				Dbo dbo2 = dbc.findOne(dbo1p5);
				if (dbo2 == null)
					O.o("fail on rec# ${i} rec ${lproc}")
				else
					O.o("succ on rec# ${i} rec ${lproc}")
				assert (dbo2 != null);
				O.o "got back from DB:" + dbo2.toString()
			}
		} catch (Throwable t) {
			O.or "end:", t



		}
		finally {
			O.o "done"
		}
	}
}