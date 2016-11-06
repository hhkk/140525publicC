package com.ustodo.utilg;

import java.util.ArrayList
import com.ustodo.utilg.O;

public class UtilSearch {

	public static ArrayList<String> getAutoCompleteLinesOut(List<String> fileLines, String autocomp, long countwanted, String fqFileName)
	{
		long lstart = System.currentTimeMillis();
		long fileLen = UtilFile.fileLen (fqFileName);
		long lineCountToStartKeepAt = (Math.max(0,fileLen - countwanted));

		fileLines = fileLines.reverse ();
				//String[] sarr = (String[] ) fileLines.toArray(new String[0] a);
		ArrayList alLinesReturn = new ArrayList();
		if (autocomp.length() > 2)
		{
			fileLines.each {
				String fileLine = it;
				if (fileLine.length() > 20 && UtilSearch.match ( fileLine,  autocomp)) {
					alLinesReturn.add  UtilDate.renderAgeAsLetterFromNowToFileDateStr(fileLine [0..18]) + " &nbsp;" + fileLine [20..-1]
					//O.o ("EEEE adding line [" + fileLine + "]")
				}
			}

			//O.o ("generated [" + alTagsRtn.size() + "] tags from [" + fileLines.size()+ "] filelines")
		}
		O.o ("getAutoCompleteLinesOut completed in ms:" + O.elapsed(lstart));
		return alLinesReturn;
	}

	public static boolean match (String candidateLine, String searchCriteria) {
		try {
			candidateLine = candidateLine.toLowerCase()
			searchCriteria=searchCriteria.toLowerCase()
			//
			boolean hitRemove = false;
			// FOR EACH SRCH WORD MATCH MATCH MATCH MATCH MATCH
			// OPTIMIZATION?: consider permutations like: (str1.*str2.*str3|str3.*str1.*str2|str2.*str1.*str3|str1.*str3.*str2)'
			(searchCriteria.split(" ")).eachWithIndex
			{ srchWrd, ii ->
				//O.o("in here xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				if (!srchWrd.equals("/"))
				{
					srchWrd = srchWrd.trim();
					if (!hitRemove)
					{
						if (srchWrd.startsWith("-")) // subtractive search
						{
							//O.o ((new Date()).toString() + "********* in match sub testing neg on [" + srchWrd + "] vs [" + candidateLine + "[")
							if (srchWrd.length() > 1 && candidateLine.contains(srchWrd[1..-1])) // / ignoreif there is a "-" alone
							{
								hitRemove = true;
								//O.o ((new Date()).toString() + "hit remove ");
							}
						}
						else // positive search
						{
							if (!candidateLine.contains(srchWrd))
							{
								hitRemove = true;
								//O.o ((new Date()).toString() + "hit remove: searchword [" + srchWrd + "] line [" + candidateLine+ "]");
							} else
							{
								//O.o ((new Date()).toString() + "found match: searchword [" + srchWrd + "] line [" + candidateLine+ "]");
							}
						}
					}
				}
			}

			return !hitRemove;
		} catch ( Throwable t)
		{
			O.o ("in throwable");
			t.printStackTrace();
		}
		return false; //assume no match if error
	}



	public static String colorTags (String descCaller, String uncoloredMatchedLine, String searchedFor, String scolor)
	{
		String sav = uncoloredMatchedLine;
		try {
            O.o "in colorTags sav [" + sav + "]"
			if (searchedFor.equals("*") || searchedFor.equals("*2") ) // MODE_SEARCH_STAR
				return wrapInCode(uncoloredMatchedLine)

			def mystuff_settings_hiliteSearch = org.codehaus.groovy.grails.commons.ConfigurationHolder.getFlatConfig().get('mystuff.settings.hiliteSearch');

			if (!"yes".equalsIgnoreCase (mystuff_settings_hiliteSearch))  // if in special debug no auth mode
				return uncoloredMatchedLine;


			if (searchedFor == null || searchedFor.contains("(") || searchedFor.contains(")"))
			{
				return uncoloredMatchedLine;
			}
			if (uncoloredMatchedLine == null )
			{
				return uncoloredMatchedLine;
			}
			else if (uncoloredMatchedLine.indexOf("http") == -1)  //  no fear of mangling a url with coloring
			{
				//O.o "in here 3333333333 " + descCaller;
				searchedFor.split(" ").each {
					String searchedForElement = it;
					if (!searchedForElement.startsWith("-"))
					{
						uncoloredMatchedLine = uncoloredMatchedLine.replaceAll (searchedForElement,"hbkkbhhbkkbh" + searchedForElement + "lrbbrllrbbrl");
					}
				}
				uncoloredMatchedLine = uncoloredMatchedLine.replaceAll ("hbkkbhhbkkbh","<font color=" + scolor + ">");
				uncoloredMatchedLine = uncoloredMatchedLine.replaceAll ("lrbbrllrbbrl","</font>");
				//O.o "done in here 3333333333";
				return uncoloredMatchedLine; // now colored
			}
			else if (uncoloredMatchedLine.lastIndexOf(" / ") > 0) // if there are tags
			{
				//O.o "in here 444444444444444444444444" + descCaller;
				String tagsOnly = uncoloredMatchedLine.substring (0, uncoloredMatchedLine.lastIndexOf(" / "));
				String theRest = uncoloredMatchedLine.substring (uncoloredMatchedLine.lastIndexOf(" / "));
				searchedFor.split(" ").each {
					String searchedForElement = it;
					if (!searchedForElement.startsWith("-"))
					{
						tagsOnly = tagsOnly.replaceAll (searchedForElement, "hbkkbhhbkkbh" + searchedForElement + "lrbbrllrbbrl");
					}
				}
				tagsOnly = tagsOnly.replaceAll ("hbkkbhhbkkbh","<font color=" + scolor + ">");
				tagsOnly = tagsOnly.replaceAll ("lrbbrllrbbrl","</font>");
				//O.o "done in here 444444444444444444444444";
				return tagsOnly + theRest;
			}

			else
			{
				//O.o "in here 6666 on [" + uncoloredMatchedLine + "]";
				return uncoloredMatchedLine;
			}

		} catch (Exception e) {
			return sav;
		}
	}
}
