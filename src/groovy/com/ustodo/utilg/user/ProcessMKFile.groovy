package com.ustodo.utilg.user

import com.sun.org.apache.xalan.internal.xsltc.compiler.Number;
import com.ustodo.utilg.O
import com.ustodo.utilg.UtilDate;
import com.ustodo.utilg.UtilFile
import com.ustodo.utilg.Profiler
import com.ustodo.utilg.UtilFileLineRaw
import com.ustodo.utilg.UtilMongo

class ProcessMKFile {

	public static void main(String[] args)
	{
		try 
		{
            // original load
            if (true)
            {
                O.o "Top of the Mainin to Ya"
                if (true) // step 1 - create the file
                    commonParse "/Users/hkon/sw/ustodo/110504utd/ustodo112/test/unit/data/mkonSample_SrcForComputerProcess.txt"
                // up to now I guess we only have created a file    "/tmp/t.txt"
                if (true) // step 2 - load the file for marktest to DB
                {
                    UtilMongo.dropColl("mk util", UtilMongo.getColl("favsmarktest"), true)
                    Profiler.check"top UtilUtd_FileImportToDB"
                    O.o "TOP ################################################################################################"
                    boolean bindex=true;
                    UtilFileLineRaw.insertFileIntoColl_ViaFileLineRaw("/tmp/t.txt", UtilMongo.getColl("favsmarktest"), false, true, bindex)
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

            }
            else
            {

            }
		} catch (Throwable t) {
			O.or "end:", t
		}
		finally {
			O.o "done"
		}
	}

	private static int countTabsAtHead(String s)
	{
		//O.o "countTabsAtHead processing:" + s + "[" + s.replace("\t","T") + "]" 
		int i = 0;
		while (true && s.length() > i)
		{ 
			if (!s[i].equals("\t"))
				return i
			else
				i++;
		}	
		return i;
	}
	
    private static String buildStrOut(Vector vecStanding)
    {
        def sb = new StringBuffer()
        int i = 0;
        int j = 0;
        for (j = 0; j < vecStanding.size(); j++)
        {
            if (!((String) vecStanding.elementAt(j)).trim().equals(""))
                sb.append (vecStanding.elementAt(j) + " / ");
        }
        String srtn = sb.toString()
        if (j > 0)
        {
            srtn = srtn[0..-4].trim() // " / "
        }
        return  srtn;
    }                                              
	
    private static Vector condVec(Vector vecStanding, int numTabsAtHead, String currec)
    {
		O.o("PRE vecSt [" + vecStanding + "] vec len [" + vecStanding.size() + "] numTabs [" + numTabsAtHead + "] currec [" + currec + "]");
		
		Vector vout = new Vector();
		for (int i = 0 ; i < numTabsAtHead; i++)
        {
			if (i < vecStanding.size())
				vout.add(vecStanding.elementAt(i));	
			else
				vout.add("\t");
			
        }
		vout.add(currec.trim());
		//O.o("POST vout [" + vout + "] voutlen [" + vout.size() + "]");
		
		vout;
		
    }
    
	

    static int callcnt = 0;
	public static void commonParse(String fn)
	{
		int irelposinrec = 0;
		String[] sa = UtilFile.getFileAsStrArray(fn);
		//def a = l.
		List lrecs = new ArrayList<String[]>();

		String currec = null;
		try {
			
			// for each file line

            Vector<String> vecStanding = new Vector<String>();
			Vector<Vector<String>> vecOfVecsPreFinalNonDeduped = new Vector<String>();
			Vector<Vector<String>> vecOfVecsFinalDeduped = new Vector<String>();
			String fnout = "/tmp/t.txt";
			O.o "writing to file :" + fnout
			UtilFile.addLineToFileTrunc(fnout, "restart output:" + new java.util.Date() + "\r\n");
			
			for (int i = 0; i < sa.length; i++)
			{
				//O.o i + ". out:" + sa[i]
				if (!sa[i].trim().equals(""))
				{
					currec = sa[i];
					int numTabsAtHead = countTabsAtHead(currec)
                    O.o "processing line [" + currec + "] numTabsAtHead:" + numTabsAtHead;

					vecStanding = condVec(vecStanding, numTabsAtHead, currec)
					vecOfVecsPreFinalNonDeduped.add(vecStanding)
				}
			} // FOR

			// now dedup
			for (int j = 0; j < vecOfVecsPreFinalNonDeduped.size(); j++)
			{
                // match this to n+1 and pick winner if not both
				if (j < vecOfVecsPreFinalNonDeduped.size()-1)
                {
					if (!redundantFirstInLightSecond(vecOfVecsPreFinalNonDeduped.elementAt(j), vecOfVecsPreFinalNonDeduped.elementAt(j+1)))
					{
						vecOfVecsFinalDeduped.add vecOfVecsPreFinalNonDeduped.elementAt(j)
						
					}
                }
			}
			
			// now emit
            int downcount = 159;
			for (int j = 0; j < vecOfVecsFinalDeduped.size(); j++)
			{
                downcount--;
                boolean specialDateHandlingToMakeSequential = true;
                String dt = null;
                if (specialDateHandlingToMakeSequential)
                {

                    String s = UtilDate.dateForFile;    // 2012-03-21 03:56:56
                    s = s[0..-6]
                    String js = downcount.toString();

                    if (js.size() < 2)
                        js = s + "00:0" + js
                    else if ( js.size() == 2 )
                        js = s + "00:" + js
                    else if ( js.size() == 3 )
                    {
                        js = s + "0" + js[0..-3]+ ":" + js[1..2]
                        
                    }
                    else
                        throw new Exception()
                    dt = js;
                    O.o "dt [" + dt + "]"

                }
                UtilFile.addLineToFileAppend(fnout,dt + " " + buildStrOut(vecOfVecsFinalDeduped.elementAt(j)) + "\r\n");
				//O.o("call:" + callcnt++ + ", POST numTabsAtHead [" + numTabsAtHead+ "] vecStanding [" + vecStanding + "] vecStanding [" + vecStanding.size() + "]");
				//UtilFile.addLineToFileTrunc(fnout, "restart output:" + new java.util.Date() + "\r\n");
				
			}
			
			
		} catch ( Exception e)
		{
			println "asdasdad"
			O.or ("currec:" + currec, e)
		}
	}

	private static boolean redundantFirstInLightSecond(Vector v1, Vector v2)
	{
		int i = 0;
		for (i = 0; i < Math.min(v1.size(), v2.size()); i++)
		{
			if (!v1.elementAt(i).equals(v2.elementAt(i)))
			{
				break;
			}
		}
		O.o "compare on v1.size() [" + v1.size() + "] to i [" + i + "]"
		if (i == (v1.size()))
			return true;
		else
			return false
	}

}
