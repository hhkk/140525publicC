package com.ustodo.utilg


import com.ustodo.utilj.Ozz;

class UtilStr {

    public static void main (String[] args)
    {
        O.o "aa:" + keepAllBeforeLastOfThis ("tester555", " / ")
    }

    public static boolean isNullOrBlank(String s)
	{
		return (s == null) || s.trim().equals("");
	}
	public static boolean isNotNullBlank(String s)
	{
		!isNullOrBlank(s)
	}
	public static String formatList(List l)
	{
		int i = 0;
		StringBuffer sb = new StringBuffer();
		l.each { sb.append
			(i.toString() +					". " +
					it.toString() +
					"\r\n"
					)
		}
		return sb.toString();
	}


	public static String formatList(String[] lxx)
	{
		int i = 0;
		StringBuffer sb = new StringBuffer();
		//
		(1..10).each { it ->
			println "Hello ${it}"
		}
		//		lxx.each {
		//			//sb.append	(i.toString() +					". " +					it.toString() +					"\r\n"					);
		//			//sb.append	(i.toString() +					". " +					it.toString() +					"\r\n"					);
		//		}
		return sb.toString();
	}

	public static String keepAllButLastX(String s, int numToRemove)
	{
		assert (numToRemove > 0)
		//if ((s.length()+1-numToRemove) >= 0)
		s[0..s.length()-(numToRemove+1)]
		//else
		//s
	}

    public static String keepLastX(String s, int numToKeep)
    {
        //if ((s.length()+1-numToRemove) >= 0)
        try {
            s[s.length()-numToKeep..-1]
        } catch (Exception e) {
            e.printStackTrace();
            O.oerr("*********************** keepLastX numToKeep [" + numToKeep + "] of [" + s + "]", e);
            return null;
        }

        //else
        //s
    }

    public static String keepFirstX(String s, int numToKeep)
    {
        if (s.length() <= numToKeep)
            return s;
        s[0..numToKeep-1]
        //else
        //s
    }

    public static String keepAllButFirstX(String s, int numToRemove)
	{
        try {
            s[(numToRemove)..-1]
        } catch ( Throwable t) {
            O.or("failed in keepAllButFirstX s:" + s + ", numToRemove:" + numToRemove, t);
        }
	}



	public static String keepUpToZeroBased(String s, int targetLength)
	{
		int count = Math.min(targetLength-1, s.size()-1)
		s[0..count]
	}

	public static String keepAllBeforeLastOfThis (String s, String delim)
	{
		int i = s.lastIndexOf(delim)
		if (i < 0)
			return s
		return s[0..i]
	}

	public static String keepAllBeforeLastOfThisNewerStripsEndDelim (String s, String delim)
	{
		int i = s.lastIndexOf(delim)
		if (i < 1)
			return s
		return s[0..i-1]
	}

	public static String keepAllBeforeFirstOfThis (String s, String delim)
	{
		int i = s.indexOf(delim)
		return s[0..i]
	}
	public static String keepAllBeforeFirstOfThisNew (String s, String delim)
	{
		int i = s.indexOf(delim)
		return s[0..i-1]
	}

	public static String keepAllAfterLastOfThis (String s, String delim)
	{
		int i = s.lastIndexOf(delim)
		return s[(i+delim.length())..-1]
	}

	public static String keepAllAfterNextOfThis (String s, String delim)
	{
		int i = s.indexOf(delim)
		if (i > 0)
			return s[(i+delim.length())..-1]
	}

	public static String keepAllAfterFirstOfThis (String s, String delim)
	{
		int i = s.indexOf(delim)
		if (i > 0)
			return s[(i+delim.length())..-1]
	}


	public static String join(String[] input, String delimiter)
	{
		StringBuilder sb = new StringBuilder();
		for(String value : input)
		{
			sb.append(value);
			sb.append(delimiter);
		}
		int length = sb.length();
		if(length > 0)
		{
			// Remove the extra delimiter
			sb.setLength(length - delimiter.length());
		}
		return sb.toString();
	}

	public static String join(java.util.RandomAccessSubList input, String delimiter)
	{
		StringBuilder sb = new StringBuilder();
		for(String value : input)
		{
			sb.append(value);
			sb.append(delimiter);
		}

		int length = sb.length();
		if(length > 0)
		{
			// Remove the extra delimiter
			sb.setLength(length - delimiter.length());
		}
		return sb.toString();
	}
	public static String join(java.util.ArrayList input, String delimiter)
	{
		StringBuilder sb = new StringBuilder();
		for(String value : input)
		{
			sb.append(value);
			sb.append(delimiter);
		}

		int length = sb.length();
		if(length > 0)
		{
			// Remove the extra delimiter
			sb.setLength(length - delimiter.length());
		}
		return sb.toString();
	}

	// Replaces URLs with html hrefs codes  from http://stackoverflow.com/questions/285619/how-to-detect-the-presence-of-url-in-a-string
	// from https://www.google.com/search?ix=seb&sourceid=chrome&ie=UTF-8&q=java+string+detect+url

	public static boolean getUrlBestGuessIsThisOne__Untested(String s)
	{
		if (s.split("\\s").length > 1) "a str with no spaces split will be length 1"
			return false
		try {
			URL url = new URL(s);
			//System.out.print("<a href=\"" + url + "\">"+ url + "</a> " );
			return url.toString();
		} catch (MalformedURLException e) {
			// If there was an URL that was not it!...
			return false;
		}
	}
	public static String getUrlsMulti_____________Untested(String s)
	{
		// TODO make this a stringbuffer and return multi
		// separate input by spaces ( URLs don't have spaces )
		String [] parts = s.split("\\s");

		// Attempt to convert each item into an URL.
		for( String strArrayElem : parts ) try {
			URL url = new URL(strArrayElem);
			// If possible then replace with anchor...
			//System.out.print("<a href=\"" + url + "\">"+ url + "</a> " );
			return url.toString();
		} catch (MalformedURLException e) {
			// If there was an URL that was not it!...
			//System.out.print( item + " " );
		}
		return null;
	}
	public static boolean matchesGoogleStyle(String s, String userinput, int i)
	{
		//O.o ("TOP matchesGoogleStyle:[" + s + "] userinput? [" + userinput + "]")
		int j = 0
		boolean donenow = false;
		String[] arrUserInputSplit = userinput.split (" ")
		for (int k = 0; k < arrUserInputSplit.length; k++)
		{
			if (!s.contains(arrUserInputSplit[k]))
				return false;
			j++
		}
//		userinput.split (" ").each {
//			//O.o (i++ + ". matchesGoogleStyle:[" + s + "] contains? [" + it + "]")
//			if (!s.contains(it))
//			{
//				//O.o(j + "." + i +  ". FINAL STYLE 1 matchesGoogleStyle return false s [" + s + "] userinput [" + userinput + "]")
//				donenow = true;
//				return;
//			}
//			j++;
//		}


//			return false;
		if (j == 0)
		{
			if (s.contains(userinput))
			{
				//O.o(j + "." + i +  ". FINAL STYLE 2 matchesGoogleStyle return true s [" + s + "] userinput [" + userinput + "]")
				return true
			}
			else
			{
				return false
			}
		}

		//O.o(j + "." + i +  ". FINAL STYLE 3 matchesGoogleStyle return true s [" + s + "] userinput [" + userinput + "]")
		return true;
	}

    public static String replaceAllTrailingInstancesOfWithInterimTrim(String s, String trailing, String replacement)
    {
        String sOri = s;
        while (true)
        {
            boolean keepLooping = false;
            if (s.endsWith(trailing))
            {
                s = keepAllButLastX(s, trailing.length()).trim()
                keepLooping = true; //stop only when not found
            }
            if (!keepLooping)
                break;
        }
        String sreturn = s.trim();
        O.oNoFilter("replaceAllTrailingInstancesOfWithInterimTrim [" + sOri + "] to ["+sreturn+"]")
         return sreturn;

    }


}

