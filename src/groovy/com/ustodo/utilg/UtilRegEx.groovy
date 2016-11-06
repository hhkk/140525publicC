package com.ustodo.utilg
//com.ustodo.utilg.UtilRegEx

import groovy.time.TimeCategory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// see http://docs.oracle.com/javase/1.4.2/docs/api/java/util/regex/Pattern.html

class UtilRegEx
{
	public static String inputToRegEx(String input)
	{
		def recursex = {
			//it.each { recurse (it[1..-1]) }
			println (it)
		}

		input.each { recursex ("asd") }
	}

	public static String convertSearchToRegEx(String prompt)
	{
		//String re1 = /.*2007.*/
		//String re2 = /.*mkon.*/
		String input = UtilCons.gets(prompt)
		String inputarray = input.split(" ")
		def regex = ".*" + input + ".*"
		//O.ofmt("regex", regex)
	}

    public static String escapeMongoRequiredRegExChars(s)
    {
//        s = s.replaceAll("\\*", "\\\\*");
//        //s = s.replaceAll("\\*", '\\\u002a');
//        s = s.replaceAll("\\?", "\\\\?");
//        //s = s.replaceAll("\\?", '\\\u003f');
//        //prestarstar = prestarstar.replaceAll('\$', 'x');
 //        //prestarstar = prestarstar.replaceAll("\\!", "\\\\\\\\!");
//        s = s.replaceAll("\\/", "\\\\/");

        s = s.replace("[","QAWSED_RFTGYH");
        s = s.replace("]","POLKJH_HGCXFD");
        //s = s.replace("QAWSEDRFTGYH","]");
        s = s.replace("(","[(]");
        s = s.replace(")","[)]");
        s = s.replace("{","[{]");
        s = s.replace("}","[}]");
        s = s.replace(".","[.]");
        s = s.replace("+","[+]");
        s = s.replace("%","[%]");
        s = s.replace("&","[&]");
        s = s.replace("*","[*]");
        s = s.replace("?","[?]");
        s = s.replace("/","[/]");
        s = s.replace("|","[|]");
        s = s.replace("\$","[\$]");
        s = s.replace("\"","[\"]");
        s = s.replace("\\","\\\\");
        s = s.replace("QAWSED_RFTGYH","\\["); // at end - already put in brackets
        s = s.replace("POLKJH_HGCXFD","\\]"); // at end - already put in brackets
        s = s.replace("^", "\\^");
        //s = s.replace("^","[^]");
        //s = s.replace("\\","\\\\");
        //s = s.replace(")","\\)");
        //s = s.replace(".", "\\.");


        //s = s.replace("\\?", "\\\\?");
        //s = s.replace("\$", "\\\$");

        //s = s.replaceAll("\\\$", "\\\\u0024");
        //s = s.replaceAll('v', '\\\u0077');
        // s = s.replaceAll('\\$', '\\\u0077'); // works for the replace aspect
        //s = s.replaceAll('\\$', "\\\\u0024");
        //s = s.replaceAll("w", 'xxx');
        //s = s.replaceAll("w", new RegExp("\ufffd"));

        // this is not a bad set:  matches httpx=*?/
        // eg printDbosFound ( db, UtilMongoDboCreate.getDboLike("filelineraw", "httpx=*?/", false))
//        s = s.replaceAll("\\*", "\\\\*");
//        s = s.replaceAll("\\?", "\\\\?");
//        s = s.replaceAll("\\/", "\\\\/");



        return s;
    }



	public static boolean matches (String regex, String test)
	{
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(test);
		boolean b = m.matches();
		//O.o "test string [${test}] against regex [${regex}] is b [${b}]"
		b
		//	Pattern p = Pattern.compile("a*b");
		//	Matcher m = p.matcher("aaaaab");
		//	boolean b = m.matches();

	}

	public static boolean fileLineRawHasProperDateOnly  (String flrs)
	{
		// 1234567890123456789
		// 1998-05-06 10:00:00	c:\temp\pctinstall	putnam directories	work
		UtilRegEx.matches ("^\\d\\d\\d\\d-\\d\\d-\\d\\d.*", flrs)

		//UtilRegEx.matches ("^####-##-## ##:##:##", flrs)
		//	Pattern p = Pattern.compile("a*b");
		//	Matcher m = p.matcher("aaaaab");
		//	boolean b = m.matches();

	}

	public static boolean fileLineRawHasProperDateAndTime (String flrs)
	{
		// 1234567890123456789
		// 1998-05-06 10:00:00	c:\temp\pctinstall	putnam directories	work





















         UtilRegEx.matches ("^\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d.*", flrs)

		//UtilRegEx.matches ("^####-##-## ##:##:##", flrs)
		//	Pattern p = Pattern.compile("a*b");
		//	Matcher m = p.matcher("aaaaab");
		//	boolean b = m.matches();

	}


	/*
	 * Uses split to break up a string of input separated by
	 * commas and/or whitespace.
	 */	//	public class Splitter {
	//		public static void main(String[] args) throws Exception {
	//			// Create a pattern to match breaks
	//			Pattern p = Pattern.compile("[,\\s]+");
	//			// Split input with the pattern
	//			String[] result =
	//					 p.split("one,two, three   four ,  five");
	//			for (int i=0; i<result.length; i++)
	//				System.out.println(result[i]);
	//		}
	//	}


	private static void testTimeAgoParser()
	{
		try
		{
			// Extract the text between the two title elements
			def pattern = "[0-9][a-zA-Z]";
			//[a-zA-Z]

			// from http://stackoverflow.com/questions/5732035/regex-to-find-words-with-letters-and-numbers-separated-or-not-by-symbols
			String s = "A35, 35A, B503X, 1ABC5 " +
					"AB-10, 10-AB, A10-BA, BA-A10, etc... " +
					"10-10, open-office, etc.";
			Pattern regex = Pattern.compile(
					"# Match special word having one letter and one digit (min).\n" +
					"\\b                       # Match first word having\n" +
					"(?=[-/\\\\A-Za-z]*[0-9])  # at least one number and\n" +
					"(?=[-/\\\\0-9]*[A-Za-z])  # at least one letter.\n" +
					"[A-Za-z0-9]+              # Match first part of word.\n" +
					"(?:                       # Optional extra word parts\n" +
					"  [-/\\\\]                # separated by -, / or //\n" +
					"  [A-Za-z0-9]+            # Match extra word part.\n" +
					")*                        # Zero or more extra word parts.\n" +
					"\\b                       # Start and end on a word boundary",
					Pattern.COMMENTS);
			Matcher regexMatcher = regex.matcher(s);
			while (regexMatcher.find()) {
				O.o("dfdfdf:" + regexMatcher.group() + ", ");
			}


			//		pattern = "(?i)(<title.*?>)(.+?)(</title>)";
			//		String updated = EXAMPLE_TEST.replaceAll(pattern, "$2");

			//O.o("getDateLikeFileFormatFromUtilDate(dtmin):", getDateLikeFileFormatFromUtilDate(dtmin))
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


	}

	private static void testTimeAgoParser2()
	{
		try
		{
			// Extract the text between the two title elements
			//def pattern = "([0-9])[a-zA-Z]";
			def pattern = "(a[0-9])";
			//[a-zA-Z]

			// from http://stackoverflow.com/questions/5732035/regex-to-find-words-with-letters-and-numbers-separated-or-not-by-symbols
			String s = "A35, 35A, B503X, 1ABC5 AB-10, 10-AB, A10-BA, BA-A10, etc... 10-10, open-office, etc.";
			Pattern regex = Pattern.compile(
					"# Match special word having one letter and one digit (min).\n" +
					"\\b                       # Match first word having\n" +
					"(?=[-/\\\\A-Za-z]*[0-9])  # at least one number and\n" +
					"(?=[-/\\\\0-9]*[A-Za-z])  # at least one letter.\n" +
					"[A-Za-z0-9]+              # Match first part of word.\n" +
					"(?:                       # Optional extra word parts\n" +
					"  [-/\\\\]                # separated by -, / or //\n" +
					"  [A-Za-z0-9]+            # Match extra word part.\n" +
					")*                        # Zero or more extra word parts.\n" +
					"\\b                       # Start and end on a word boundary",
					Pattern.COMMENTS);
			Matcher regexMatcher = regex.matcher(s);
			while (regexMatcher.find()) {
				O.o("sdsdsd:" + regexMatcher.group() + ", ");
			}


			//		pattern = "(?i)(<title.*?>)(.+?)(</title>)";
			//		String updated = EXAMPLE_TEST.replaceAll(pattern, "$2");

			//O.o("getDateLikeFileFormatFromUtilDate(dtmin):", getDateLikeFileFormatFromUtilDate(dtmin))
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


	}


	private static void testTimeAgoParser3()
	{
		try
		{
			//CharSequence inputStr = "abbabcd";
			//	String patternStr = "(a(b*))+(c*)";
			//CharSequence[] inputStr = ["-tm 1m","-t 11m", "-t 1m -d 12"]
			//CharSequence[] inputStr = ["-t=1m -d=12"]
			// works CharSequence[] inputStr = ["-t=1m "] first set "-([a-zA-Z0-9].*)=([a-zA-Z0-9].*)"
			CharSequence[] inputStr = ["-t=m -d=w"]
			Pattern pattern = null;
			try {
				String patternStr = "((-([a-zA-Z0-9].*)=([a-z].*) ).*)";

				// Compile and use regular expression
				pattern = Pattern.compile(patternStr);
			} catch (Throwable t )
			{
				O.or("t", t);
				throw t;
				assert(false);
			} finally {
				O.o "done part 1"
				assert (true)
			}

			for (int j = 0; j < inputStr.length; j++)
			{
				//	O.o "processing:" + inputStr[i]
				Matcher matcher = pattern.matcher(inputStr[j]);
				boolean matchFound = matcher.find();

				O.o(j + ". processing " + inputStr[j])
				if (matchFound)
				{
					// Get all groups for this match
					for (int i=0; i<=matcher.groupCount(); i++) {
						String groupStr = matcher.group(i);
						O.o(j + "." + i + ". aaaa:" + groupStr)
					}
				}

			}
		}
		catch (Throwable t )
		{
			O.or("t", t);
			throw t;
			assert(false);
		} finally {
			O.o "done part 2"
			assert (true)
		}


	}



    public static String getTest()
    {
        // http://stackoverflow.com/questions/399078/what-special-characters-must-be-escaped-in-regular-expressions
        // "httpx=*?/".replaceAll("=\\*\\?/", "a");  works



        //        s = s.replaceAll("\\.", "\\\\\\\\*");
        //        s = s.replaceAll("\\^", "\\\\\\\\*");
        //        s = s.replaceAll("\\*", "\\\\\\\\*");
        //        s = s.replaceAll("\\?", "\\\\\\\\?");
        //        s = s.replaceAll("\\-", "\\\\\\\\-");
        //        s = s.replaceAll("\\+", "\\\\\\\\+");

        //s = s.replaceAll("\\$", "\\\\\\\\$");
        // .^$*+?()[{\|
        return "hi mom";

    }





    public static void main (String[] args)
	{
		try
		{
			//O.o("getDateLikeFileFormatFromUtilDate(dtmin):", getDateLikeFileFormatFromUtilDate(dtmin))
			//testTimeAgoParser();
			//testTimeAgoParser2();
			//testTimeAgoParser3();
			//testTimeAgoParser4("-t=1m -t2=2m ");
			parseTimeAgoLike1m("1m");
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

}


