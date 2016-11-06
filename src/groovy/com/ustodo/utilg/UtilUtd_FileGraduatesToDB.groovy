package com.ustodo.utilg


class UtilUtd_FileGraduatesToDB {
	// see also UtilMongo_File_Email_Db


	public static void main (String[] args)
	{
		try
		{
			int cntGood = 0
			int cntGoodAlmost = 0
			int cntBad = 0
			int cntrepl = 0
			//			String s = "1998-05-06 10:00:00	add run line and explore line (dir to explore -file to edit)	jp3 to do	work"
			//			//String s = "1998"
			//			boolean b = UtilRegEx.matches ("^\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d.*", s)
			//			//boolean b = UtilRegEx.matches ("^####-##-## ##:##:##.*", s)
			//			//String N = "\\d\\d""
			//			//boolean b = UtilRegEx.matches ("", s)
			//			O.o "outcome:" + b
			//			//O.o "outcome:" + fileLineRawHasProperDate(s)
			//			//System.exit(1);
			if (true)
			{
				UtilFile.addLineToFileTrunc("/tmp/tfilerejects.txt", "testRun_" + new java.util.Date()+"\r\n")
				UtilFile.addLineToFileTrunc("/tmp/tfilepasses.txt", "testRun_" + new java.util.Date()+"\r\n")
				Map hmRestToTime = new HashMap();
				UtilFile.fileAsList(Cfg.fnFavsCkckck).eachWithIndex  { it, i ->

					if (i % 500 == 0 )
						O.o i + ". " + it
					if (UtilRegEx.fileLineRawHasProperDateAndTime(it))
					{
						cntGood++;
						String time = ((String)it)[0..18]
						String rest = ((String)it)[19..-1].trim();
						rest = processrest(rest);
						String priorMaxDateThisRest = hmRestToTime.get(rest)
						if (priorMaxDateThisRest == null || time.compareTo(priorMaxDateThisRest) > 0)
						{
							if (priorMaxDateThisRest != null)
							//O.o("placing or replacing " + priorMaxDateThisRest + " with time " + time + " for rest [${rest}]")
							cntrepl++
							hmRestToTime.put(rest, time)
						}
					}
					else if (UtilRegEx.fileLineRawHasProperDateOnly(it))
					{
						cntGoodAlmost++;
						String time = ((String)it)[0..9] + " 00:00:00"
						String rest = ((String)it)[10..-1].trim();
						rest = processrest(rest);
						String priorMaxDateThisRest = hmRestToTime.get(rest)
						if (priorMaxDateThisRest == null || time.compareTo(priorMaxDateThisRest) > 0)
						{
							if (priorMaxDateThisRest != null)
							//O.o("placing or replacing " + priorMaxDateThisRest + " with time " + time + " for rest [${rest}]")
							cntrepl++
							hmRestToTime.put(rest, time)
						}
					}
					else
					{
						cntBad++;
						//if (cntBad < 100 )
						UtilFile.addLineToFileAppend("/tmp/tfilerejects.txt", ((String)it).trim()+"\r\n")
					}
				} // end loop

				// now write sorted output to file
				def arr = hmRestToTime.keySet().toArray();
				O.o "arr:" + arr.getClass().getName();
				Object o = new Object();
				O.o "o:" + o.getClass().getName();
				O.oc ("arr[0]", arr[0])
				O.oc ("arr.length", arr.length)
				//java.util.Arrays.sort(arr).eachWithIndex  { rest, i ->
				// Pass 2
				ArrayList arrtfilepasses = new ArrayList();
				arr.sort().eachWithIndex  { rest, i ->
					if (i % 1000 == 0)
						O.oc (i + ". Pass 2 it:", rest)
					String time = hmRestToTime.get(rest);

					// TODO turn one tab into a categ (kw)

					String flr_reconstructed = time + "\t" + rest;
					arrtfilepasses.add(flr_reconstructed);
				}
				// now sort with time included
				arrtfilepasses.sort().eachWithIndex  { it, i ->
					it = ((String)it).replace("\t", " ") // clear tabs
					if (i % 1000 == 0)
						O.o (i + ". Pass 3 it:", it)
					UtilFile.addLineToFileAppend("/tmp/tfilepasses.txt", it+"\r\n")
				}

			}
			O.o "total ${(cntGood+cntBad+cntGoodAlmost)}"
			O.o "cntGood ${cntGood}"
			O.o "cntGoodAlmost ${cntGoodAlmost}"
			O.o "cntrepl ${cntrepl}"
			O.o "cntbad ${cntBad}"
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

	private static String processrest(String rest)
	{
		if (rest.endsWith("work") || rest.endsWith("home"))
			rest = UtilStr.keepAllButLastX(rest, 4).trim();
		// reverse order for this example:  1998-05-06 10:00:00	c:\temp\pctinstall	putnam directories	work
		String[] arrtabs = rest.split("\t")
		if (arrtabs.length > 1)
		{
			//String[] a = new String[0];
			def x = arrtabs[1..-1];
			//O.oc("x:", x)

			//def y = x.toArray();
			rest = UtilStr.join(x, " ") + " / " + arrtabs[0]
		}
		rest;// rtn
	}

}
