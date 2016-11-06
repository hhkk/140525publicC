package com.ustodo.utilg;

import java.io.BufferedReader;
import com.mongodb.DBCollection;
import com.ustodo.utilg.O;

// some crufty file utils 

public class UtilLog {

//	public static String[] getFileAsStrArray (String filename, boolean bRespectComments, String commentChar, long start)
//	{
//		try
//		{
//			String stextline;
//			BufferedReader inx = null;
//			inx = new BufferedReader(new FileReader(filename));
//			int iCnt = -1;
//			Vector v = new Vector();
//			while (inx.ready())     {
//				iCnt++;
//				if (iCnt < start)
//					continue;
//				stextline = inx.readLine().trim();
//				if (!stextline.startsWith("#") && !stextline.equals("")) // if not a comment and not blank
//				{
//					v.add(stextline);
//					// iRet = Integer.valueOf(stextline).intValue();
//				}
//			}
//			inx.close();
//			
//			String[] sArr = v.toArray(new String[0]);
//			//String[] sArr = new String[v.size()];
//			//for ( int i = 0; i < v.size(); i++)
//			//{
//			//	System.err.println (v.get(i).getClass().getName());
//			//	sArr[i] = (String) v.get(i);
//			//}
//			
//			return sArr;
//		}
//		catch ( Exception e )
//		{
//			System.err.println("error in file:" + filename, e);
//			O.or("error in file:" + filename, e);
//		}
//		return null;
//		
//	}
	public static void TestHk()
	{
		
	}

	public static long fileLen (String filename)
	{
		try
		{
			BufferedReader inx = null;
			inx = new BufferedReader(new FileReader(filename));
			long lCnt = 0;
			while (inx.ready())     
			{
				lCnt++;
				inx.readLine().trim();
			}
			inx.close();
			
			return lCnt;
		}
		catch ( Exception e )
		{
			O.or("error in file:" + filename, e);
		}
		return 0;
		
	}

	public static List fileAsList (String filename)
	{
		return fileAsList(filename, false, "", -1)
	}
	public static List fileAsList (String filename, Long numLinesWanted_negForAll)
	{
		return fileAsList(filename, false, "", numLinesWanted_negForAll)
	}
	
	
	
	public static List fileAsList (String filename, Boolean xbRespectComments, 
		String commentChar, Long numLinesWanted_negForAll)
	{
		

		//		def count=0, MAXSIZE=100
		//		new File("foo.txt").withReader { reader ->
		//		  while (reader.readLine() != null) {
		//			if (++count > MAXSIZE) throw new RuntimeException('File too large!')
		//		  }
		//		}
			
		long lineCountToStartKeepAt = 0;
		if (numLinesWanted_negForAll > 0)
			lineCountToStartKeepAt = fileLen(filename) - numLinesWanted_negForAll; // get the last X in the file

		long start = System.currentTimeMillis();
		if (numLinesWanted_negForAll.longValue() <= 0)
			lineCountToStartKeepAt = 0;
		//O.o("pre file-to-list getcount fn [" + filename+ "])");
			
		List alRtn = new ArrayList<String>();
		try
		{
			String stextlineTrimmed;
			BufferedReader bufReader = null;
			bufReader = new BufferedReader(new FileReader(filename));
			int iCnt = -1;
			//O.o "start:" + lineCountToStartKeepAt;
			long ms = O.ms();
			int iCntSkipBlank = 0;
			int iCntSkipComment = 0;
			int iCntSkipOther = 0;
			while (bufReader.ready())     
			{
				iCnt++;
				stextlineTrimmed = bufReader.readLine().trim();
				if (iCnt < lineCountToStartKeepAt)
					continue;
				else
					alRtn.add(stextlineTrimmed);
					
					
					
					
				//				if (stextlineTrimmed.equals(""))
				//				{
				//					//					O.o("in UtilFIle.fileAsList, got  [" + iCnt + "] iter" + stextline);
				//					//iCntSkipBlank++;
				//					alRtn.add(stextlineTrimmed);
				//					// iRet = Integer.valueOf(stextline).intValue();s
				//
				//				}
				//				else
				//				{
				//					int firstchar = stextlineTrimmed[0].toCharacter(); // ascii for first char in file line raw
				//					//O.ofmt "firstchar", firstchar
				//					// normal, so far apparently properly-dated line
				//					//if (firstchar >= '0' && firstchar <= '9')
				//					//{
				//					//}
				//					// comment
				//					if (stextlineTrimmed[0].equals('#')) // if not a comment and not blank
				//					{
				//						//					O.o("in UtilFIle.fileAsList, got  [" + iCnt + "] iter" + stextline);
				//						//iCntSkipComment++;
				//						alRtn.add(stextlineTrimmed);
				//						//O.ofmt "iCntSkipComment", iCntSkipComment
				//						//alRtn.add(stextline);
				//						// iRet = Integer.valueOf(stextline).intValue();s
				//					}
				//					else
				//					{
				//						alRtn.add(stextlineTrimmed);
				//						//iCntSkipOther++;
				//						 //O.o "skip line other [" + +stextlineTrimmed+  "]"
				//					}
				//	
				//				}					
			}
			
			O.o("kept [" + alRtn.size() + "] from file [" + filename+ "] in [" + UtilPerf.msSince(ms) + "] ms "+ 
				O.fmt("skip blank",iCntSkipBlank) + O.fmt("skip comments",iCntSkipComment) + O.fmt("skip other",iCntSkipOther));
			bufReader.close();
			
			O.o ("")
			return alRtn;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			O.or("error in UtilFile:" + filename, e);
		}
		return null;
		
	}

	// convert string array to int number array
	// allows us to filter secIDs in EOD assignment or calc loop on the fly using
	// if ((new File(sDebugFileName)).exists())
	//     CalcTrace.convertStrArrayToIntArr((CalcTrace.getFileAsStrArray(sDebugFileName));
	// and then only calcing or assigning if the secID matches
	public static long[] cvtSArrToLArr (String[] sArrNums)
	{
		long[] iArr = new long[sArrNums.length];
		for (int i = 0; i < sArrNums.length; i++)
			iArr[i] = Long.parseLong(sArrNums[i]);
		return iArr;
	}
	
	public static HashSet cvtLarrToHs(long[] lArr)
	{
		HashSet hs = new HashSet();
		for (int i = 0; i < lArr.length; i++)
		{
			Long L = new Long(lArr[i]);
			hs.add(L);
		}
		return hs;
	}
	
	//	public static HashSet getFileAsLongHashSet(String filename)
	//	{
	//		return cvtLarrToHs(cvtSArrToLArr(CalcTrace.getFileAsStrArray(filename)));
	//	}

	
	public static String arrayToString(String[] a, String separator) {
		StringBuffer result = new StringBuffer();
		if (a.length > 0) {
			result.append(a[0]);
			for (int i=1; i<a.length; i++) {
				result.append(separator);
				result.append(a[i]);
			}
		}
		return result.toString();
	}
	
	public static String logHelp (DBCollection coll)
	{
		coll.getDB().name + ":" + coll.name
	}
	
}
	
