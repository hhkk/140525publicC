package com.ustodoFromV.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

// some crufty file utils

public class UtilFile {

	//	//	public static String[] getFileAsStrArray (String filename, boolean bRespectComments, String commentChar, long start)
	//	//	{
	//	//		try
	//	//		{
	//	//			String stextline;
	//	//			BufferedReader inx = null;
	//	//			inx = new BufferedReader(new FileReader(filename));
	//	//			int iCnt = -1;
	//	//			Vector v = new Vector();
	//	//			while (inx.ready())     {
	//	//				iCnt++;
	//	//				if (iCnt < start)
	//	//					continue;
	//	//				stextline = inx.readLine().trim();
	//	//				if (!stextline.startsWith("#") && !stextline.equals("")) // if not a comment and not blank
	//	//				{
	//	//					v.add(stextline);
	//	//					// iRet = Integer.valueOf(stextline).intValue();
	//	//				}
	//	//			}
	//	//			inx.close();
	//	//
	//	//			String[] sArr = v.toArray(new String[0]);
	//	//			//String[] sArr = new String[v.size()];
	//	//			//for ( int i = 0; i < v.size(); i++)
	//	//			//{
	//	//			//	System.err.println (v.get(i).getClass().getName());
	//	//			//	sArr[i] = (String) v.get(i);
	//	//			//}
	//	//
	//	//			return sArr;
	//	//		}
	//	//		catch ( Exception e )
	//	//		{
	//	//			System.err.println("error in file:" + filename, e);
	//	//			O.or("error in file:" + filename, e);
	//	//		}
	//	//		return null;
	//	//
	//	//	}
	//	public static void TestHk()
	//	{
	//
	//	}
	//
	//	public static long fileLen (String filename)
	//	{
	//		try
	//		{
	//			BufferedReader inx = null;
	//			inx = new BufferedReader(new FileReader(filename));
	//			long lCnt = 0;
	//			while (inx.ready())
	//			{
	//				lCnt++;
	//				inx.readLine().trim();
	//			}
	//			inx.close();
	//
	//			return lCnt;
	//		}
	//		catch ( Exception e )
	//		{
	//			O.or("error in file:" + filename, e);
	//		}
	//		return 0;
	//
	//	}
	//
	//	public static HashMap getFileAsHmStrToInt (String fn)
	//	{
	//		HashMap hm = new HashMap();
	//		String[] sa = UtilFile.getFileAsStrArray(fn);
	//		// confirm order in = order out
	//		for (int i = 0; i < sa.length; i++)
	//		{
	//			hm.put(sa[i], i);
	//			if ((""+i).toString().trim().equals(""))
	//			{
	//				O.o ("whatup? line0based:" + i + ", in file:" + fn);
	//			}
	//		}
	//		return hm;
	//	}
	//	public static List getFileAsList (String filename)
	//	{
	//		return fileAsList(filename)
	//	}
	//	public static List fileAsList (String filename)
	//	{
	//		return fileAsList(filename, false, "", -1)
	//	}
	//	public static List getFileAsList (String filename, Long numLinesWanted_negForAll)
	//	{
	//		return getFileAsList(filename, false, "", numLinesWanted_negForAll)
	//	}
	//	public static List fileAsList (String filename, Long numLinesWanted_negForAll)
	//	{
	//		return fileAsList(filename, false, "", numLinesWanted_negForAll)
	//	}
	//
	//	public static List fileFavsAsList (int numLinesWanted_negForAll)
	//	{
	//		return fileAsList("/Users/hkon/sw/ustodo/favsckckck.csv", false, "", numLinesWanted_negForAll)
	//	}
	//
	//	// from
	//	public static String[]  getFileAsStrArray(String fn) throws IOException {
	//		getFileAsStrArray (fn, false, "", -1)
	//	}
	//	public static String[] getFileAsStrArray(String filename, Boolean xbRespectComments,
	//	String commentChar, Long numLinesWanted_negForAll) throws IOException {
	////		FileReader fileReader = new FileReader(filename);
	////		BufferedReader bufferedReader = new BufferedReader(fileReader);
	////		List<String> lines = new ArrayList<String>();
	////		String line = null;
	////		while ((line = bufferedReader.readLine()) != null) {
	////			lines.add(line);
	////		}
	////		bufferedReader.close();
	////		return lines.toArray(new String[lines.size()]);
	////		return lines.toArray(new String[lines.size()]);
	//		def l = fileAsList( filename, xbRespectComments, commentChar, numLinesWanted_negForAll );
	//		return l.toArray(new String[l.size()]);
	//	}
	//
	//
	//	public static List fileAsList (String filename, Boolean xbRespectComments,
	//		String commentChar, Long numLinesWanted_negForAll)
	//	{
	//
	//
	//		//		def count=0, MAXSIZE=100
	//		//		new File("foo.txt").with	ader { reader ->
	//		//		  while (reader.readLine() != null) {
	//		//			if (++count > MAXSIZE) throw new RuntimeException('File too large!')
	//		//		  }
	//		//		}
	//
	//		long lineCountToStartKeepAt = 0;
	//		if (numLinesWanted_negForAll > 0)
	//			lineCountToStartKeepAt = fileLen(filename) - numLinesWanted_negForAll; // get the last X in the file
	//
	//		long start = System.currentTimeMillis();
	//		if (numLinesWanted_negForAll.longValue() <= 0)
	//			lineCountToStartKeepAt = 0;
	//		O.o("pre file-to-list getcount fn: [" + filename+ "])");
	//
	//		List alRtn = new ArrayList<String>();
	//		try
	//		{
	//			String stextlineNotTrimmed;
	//			BufferedReader bufReader = null;
	//			if (filename == null)
	//				throw new RuntimeException ("filename is null!!!!!!!!!!!!!!!!");
	//
	//			bufReader = new BufferedReader(new FileReader(filename));
	//			int iCnt = -1;
	//			//O.o "start:" + lineCountToStartKeepAt;
	//			long ms = O.ms();
	//			int iCntSkipBlank = 0;
	//			int iCntSkipComment = 0;
	//			int iCntSkipOther = 0;
	//			while (bufReader.ready())
	//			{
	//				iCnt++;
	//				stextlineNotTrimmed = bufReader.readLine();
	//				if (iCnt < lineCountToStartKeepAt)
	//					continue;
	//				else
	//				{
	//					//O.o("file list stextlineTrimmed: [" + stextlineTrimmed+ "])");
	//					//stextlineTrimmed.eachWithIndex  { it, i ->
	//					//	O.o "char [${i}]" + it
	//					//}
	//
	//					alRtn.add(stextlineNotTrimmed);
	//				}
	//
	//
	//
	//
	//				//				if (stextlineTrimmed.equals(""))
	//				//				{
	//				//					//					O.o("in UtilFIle.fileAsList, got  [" + iCnt + "] iter" + stextline);
	//				//					//iCntSkipBlank++;
	//				//					alRtn.add(stextlineTrimmed);
	//				//					// iRet = Integer.valueOf(stextline).intValue();s
	//				//
	//				//				}
	//				//				else
	//				//				{
	//				//					int firstchar = stextlineTrimmed[0].toCharacter(); // ascii for first char in file line raw
	//				//					//O.ofmt "firstchar", firstchar
	//				//					// normal, so far apparently properly-dated line
	//				//					//if (firstchar >= '0' && firstchar <= '9')
	//				//					//{
	//				//					//}
	//				//					// comment
	//				//					if (stextlineTrimmed[0].equals('#')) // if not a comment and not blank
	//				//					{
	//				//						//					O.o("in UtilFIle.fileAsList, got  [" + iCnt + "] iter" + stextline);
	//				//						//iCntSkipComment++;
	//				//						alRtn.add(stextlineTrimmed);
	//				//						//O.ofmt "iCntSkipComment", iCntSkipComment
	//				//						//alRtn.add(stextline);
	//				//						// iRet = Integer.valueOf(stextline).intValue();s
	//				//					}
	//				//					else
	//				//					{
	//				//						alRtn.add(stextlineTrimmed);
	//				//						//iCntSkipOther++;
	//				//						 //O.o "skip line other [" + +stextlineTrimmed+  "]"
	//				//					}
	//				//
	//				//				}
	//			}
	//
	//			O.o("INFO: done file read count [" + alRtn.size() + "] file [" + filename+ "] ms [" + UtilPerf.msSince(ms) + "]."+
	//					O.fmt("skip blank",iCntSkipBlank) + O.fmt("skip comments",iCntSkipComment) + O.fmt("skip other",iCntSkipOther));
	//			bufReader.close();
	//
	//			O.o ("");
	//			return alRtn;
	//		}
	//		catch ( Exception e )
	//		{
	//			e.printStackTrace();
	//			O.or("error in UtilFile:" + filename, e);
	//		}
	//		return null;
	//
	//	}
	//
	//	// convert string array to int number array
	//	// allows us to filter secIDs in EOD assignment or calc loop on the fly using
	//	// if ((new File(sDebugFileName)).exists())
	//	//     CalcTrace.convertStrArrayToIntArr((CalcTrace.getFileAsStrArray(sDebugFileName));
	//	// and then only calcing or assigning if the secID matches
	//	public static long[] cvtSArrToLArr (String[] sArrNums)
	//	{
	//		long[] iArr = new long[sArrNums.length];
	//		for (int i = 0; i < sArrNums.length; i++)
	//			iArr[i] = Long.parseLong(sArrNums[i]);
	//		return iArr;
	//	}
	//
	//	public static HashSet cvtLarrToHs(long[] lArr)
	//	{
	//		HashSet hs = new HashSet();
	//		for (int i = 0; i < lArr.length; i++)
	//		{
	//			Long L = new Long(lArr[i]);
	//			hs.add(L);
	//		}
	//		return hs;
	//	}
	//
	//	//	public static HashSet getFileAsLongHashSet(String filename)
	//	//	{
	//	//		return cvtLarrToHs(cvtSArrToLArr(CalcTrace.getFileAsStrArray(filename)));
	//	//	}
	//
	//
	//	public static String arrayToString(String[] a, String separator) {
	//		StringBuffer result = new StringBuffer();
	//		if (a.length > 0) {
	//			result.append(a[0]);
	//			for (int i=1; i<a.length; i++) {
	//				result.append(separator);
	//				result.append(a[i]);
	//			}
	//		}
	//		return result.toString();
	//	}
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//	public static void createNewFile(String fn) throws IOException
	//	{
	//		//BufferedReader xx = new BufferedReader(new InputStreamReader(System.in));
	//		File f = new File(fn);
	//		f.createNewFile(fn);
	//	}
	//
	//
	//
	//
	//
	//
	//
	//	public static void trunc(String fn) throws IOException
	//	{
	//		//BufferedReader xx = new BufferedReader(new InputStreamReader(System.in));
	//		File f = new File(fn);
	//		f.setText("");
	//	}
	//
	//	public static void touchThoNoDateUpdateIfAlreadyThere(String fn)
	//	{
	//		//o("pre pre pre :" + fqFileName_xxxxxxxxxxxx);
	//		if ( !(new File(fn).exists()))
	//		{
	//			O.o("create new file/for new user:" + fn);
	//			File f = new File(fn);
	//			f.write("");
	//		}
	//		else {
	//			//o("NOT create new file/for new user:" + fqFileName_xxxxxxxxxxxx);//
	//		}
	//	}
	//	// CONVENIENCE
	//	public static void append(String fn, String line)
	//	{
	//		addLineToFileAppend(fn, line)
	//	}
	//	public static void writeAppend(String fn, String line)
	//	{
	//		addLineToFileAppend(fn, line)
	//	}
	//	public static void deleteFileOrFDir(String fn, boolean errorIfNotExists)
	//	{
	//		UtilFileDelete.deleteFileOrFDir(fn, errorIfNotExists);
	//		touchThoNoDateUpdateIfAlreadyThere(fn);
	//	}
	//	public static void createEmptyFileWithDateDeleteIfNeeded(String fn)
	//	{
	//
	//		UtilFileDelete.deleteFileOrFDir(fn, false);
	//		touchThoNoDateUpdateIfAlreadyThere(fn);
	//		addLineToFileAppend(fn, "created:" + new java.util.Date()+"\r\n");
	//	}
	//	// END CONVENIENCE
	//
	//	//	public static void addLineToFileTrunc(String fn, String line)
	//	//	{
	//	//		trunc(fn);
	//	//		write (fn, line, 0);
	//	//	}
	//	//	public static void addLineToFileAppend(String fn, String line)
	//	//	{
	//	//		write (fn, line, 0);
	//	//	}
	//
	//	//	public static void write(String fn, String s, int logmaxfilesz) throws IOException
	//	//	{
	//	//		//BufferedReader xx = new BufferedReader(new InputStreamReader(System.in));
	//	//		File f = new File(fn);
	//	//		if (logmaxfilesz > 0 && f.size() > logmaxfilesz)
	//	//		{
	//	//            // to do
	//	//			System.out.println ()"rolling over log file [${fn}] - trunc trunc_ZeroForNo [$logmaxfilesz{}]");
	//	//			f.set("trunc");
	//	//		}
	//	//		//		if (!exist)
	//	//		//		{
	//	//		//			System.out.println("File already exists.");
	//	//		//			System.exit(0);
	//	//		//		}
	//	//		//		else
	//	//		//		{
	//	//		FileWriter fstream = new FileWriter(fn, true); // true means append
	//	//		BufferedWriter out = new BufferedWriter(fstream);
	//	//		//out.write(xx.readLine());
	//	//		out.write(s);
	//	//		out.close();
	//	//		//System.out.println("File created successfully.");
	//	//	}
	//
	//	//	public static void truncIfLargerThan(String fn, int i) throws IOException
	//	//	{
	//	//		//BufferedReader xx = new BufferedReader(new InputStreamReader(System.in));
	//	//		File f = new File(fn);
	//	//		f.setText ("");
	//	//		System.out.println("File truncated:" + fn);
	//	//	}
	//
	//
	//
	//
	//
	//
	//
	//
	//	// another type?
	//	//	public static void writeToFile(String s, String qfilename) throws IOException
	//	//	{
	//	//		BufferedWriter bw = null;
	//	//
	//	//		try {
	//	//			bw = new BufferedWriter(new FileWriter("checkbook.dat", true));
	//	//			bw.write("400:08311998:Inprise Corporation:249.95");
	//	//			bw.newLine();
	//	//			bw.flush();
	//	//		} catch (IOException ioe) {
	//	//			ioe.printStackTrace();
	//	//		} finally {                       // always close the file
	//	//			if (bw != null) try {
	//	//				bw.close();
	//	//			} catch (IOException ioe2) {
	//	//				// just ignore it
	//	//			}
	//	//		} // end try/catch/finally
	//	//	}
	//
	//
	//
	//
	//
	//
	//
	//	public static boolean exists(String fn)
	//	{
	//		File f = new File(fn);
	//		return f.exists();
	//
	//	}
	//	
	//	public static boolean existsAndReadable(String fn)
	//	{
	//		File f = new File(fn);
	//		return f.exists();
	//
	//	}
	//
	//
	//
	//	//    public static void loadFile ()
	//	//    {
	//	//        String propFile = "c:/props.txt";
	//	//        def propMap = [:];
	//	//        def propList = [];
	//	//
	//	//        new File(propFile).eachLine { line ->
	//	//            def (key,value) = line.split(':').collect { it.trim() }
	//	//            propMap."$key" = "$value"
	//	//
	//	//            if(propMap) {
	//	//                propList << propMap
	//	//                propMap = [:]
	//	//            }
	//	//        }
	//	//    }

	
	private static String sFqFileLog = "/tmp/utdlog.txt";
	private static int callcount = 0;
	public static void writeToFileCreateIfNeeded (String s)
	{
		callcount++;
		PrintWriter writer = null;
		try {
			writer = new PrintWriter (new FileOutputStream( new File(sFqFileLog), true));
			s = callcount + ". " + new java.util.Date() + ":" + s;
			writer.println(s);
			writer.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null)
			{
				writer.close();	
			}
		}
			
	}
	
	
}

