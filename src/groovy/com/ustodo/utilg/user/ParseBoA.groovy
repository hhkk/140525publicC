package com.ustodo.utilg.user

import com.ustodo.utilg.O;
import com.ustodo.utilg.UtilFile;

class ParseBoA {

	public static void main(String[] args)
	{
		try
		{
			O.o "ASDSAD"
			commonParse "/Users/hkon/tmp/tboa.txt"
		} catch (Throwable t) {
			O.oerr "end:", t
		}
		finally {
			O.o "done"
		}
	}

	static	int RP0_NICKNAME = 0
	static	int RP1_ACCTID = 1
	static	int RP2_DATECONFSTAT = 2
	static	int RP3_CLICKOPTIONS = 3

	public static void commonParse(String fn)
	{
		int irelposinrec = 0;
		String[] sa = UtilFile.getFileAsStrArray(fn);
		//def a = l.
		List lrecs = new ArrayList<String[]>();

		String currec = null;
		try {
			for (int i = 0; i < sa.length; i++)
			{
				//O.o i + ". out:" + sa[i]
				if (!sa[i].trim().equals(""))
				{
					def recsiz = 3;
					//O.o "in:" + sa[i] + " i:" + i
					currec = sa[i];
					//java.util.RandomAccessSubList rsl = sa[i..i+recsiz];
					def rsl = -1;
					try {
						rsl = sa[i..i+recsiz];
					} catch ( Exception e)
					{
						O.oerr("did u remember to add to the file an extra line at the end?", e)
					}
					//O.oc "rsl", rsl;
					String[] rec = rsl.toArray(new String[rsl.size()])
					//O.o "rec #" + lrecs.size(), rec;
					lrecs.add(rec)
					//O.o "added:" + rec
					i = i + recsiz; // next rec
					if ((i + recsiz) >= sa.length)
					{
						if (sa.length > i + 1)
						{
							throw new Exception ("left records on the table")
						}
						O.o("done after adding ${lrecs.size()} records")
						break;
					}
				}
			} // FOR
		} catch ( Exception e)
		{
			println "asdasdad"
			O.oerr ("currec:" + currec, e)
		}


		// now we have a 2-deep hierarchy
		String[][] arecs = lrecs.toArray()
		for (int r = 0; r < sa.length; r++)
		{
			if (r >= arecs.length)
			{
				O.o recsum("TAIL one off?:", arecs[r-1])
				break;

			}
			if (arecs[r][RP0_NICKNAME].trim().equals(arecs[r+1][RP0_NICKNAME].trim()) )
			{
				//O.o recsum("dups:", arecs[r])
				r++;
			}
			else
			{
				if (
				!arecs[r][RP0_NICKNAME].contains("PenFed") &&
				!arecs[r][RP0_NICKNAME].contains("GMAC") &&
				!arecs[r][RP0_NICKNAME].contains("elec.Cedr150kLoc") &&
				!arecs[r][RP0_NICKNAME].contains("Pasz") &&
				!arecs[r][RP0_NICKNAME].contains("Chase") &&
				true

				)
					O.o recsum("one off:", arecs[r])
			}
		}

	}

	private static String recsum (String desc, String[] rec)
	{
		def sb = new StringBuffer();

		sb.append  "Desc:" + desc;
		sb.append  "\t "+rec[RP0_NICKNAME];
		sb.append  "\t "+rec[RP1_ACCTID];
		sb.append  "\t "+rec[RP2_DATECONFSTAT];
		sb.append  "\t "+rec[RP3_CLICKOPTIONS];

		sb

	}

}
