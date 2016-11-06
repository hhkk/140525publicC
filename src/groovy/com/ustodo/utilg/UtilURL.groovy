package com.ustodo.utilg

import java.util.regex.Pattern
import java.util.regex.Matcher
import org.apache.commons.lang.StringUtils;

public class UtilURL
{
	// from http://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html

	public static void main(String[] args) throws Exception
	{
		//String html = getHTML("http://www.oracle.com/")
		//String title = getTitle("http://www.oracle.com/")
		//O.o("html:" + html + "\r\ntitle:" + title)

		//O.oNoFilter("out:" + UtilURL.compileLinksToHREFs(" hi hk1  http://test.com "));
	//	O.oNoFilter("out:" + UtilURL.compileLinksToHREFs(" hi hk1  http://146.115.http://146.115.68.118:8083/ustodo/pics/68.118:8083/ustodo/pics/"));
//		O.o("out:" + UtilURL.compileLinksToHREFs(" hi hk2  www.test.com "));
//		O.o("out:" + UtilURL.compileLinksToHREFs(" hi hk3  ibm.com "));
		//O.o("out:" + UtilURL.compileLinksToHREFs(" hi hk1  http://test1.com  www.test2.com"));
		O.o("out:" + UtilURL.compileLinksToHREFs(" monitor http://localhost:8084/ustodo/ w"));
	}

	public static String getHTML(def desc, String url, boolean errorShow, boolean errorThrow)
	{
 		BufferedReader tt = null;

		StringBuffer sb = new StringBuffer();
		try {


            //O.oNoFilter("hi hk2!! =====================================");
			tt = new BufferedReader(new InputStreamReader((new URL(url)).openStream()));

			String inputLine;

 			while ((inputLine = tt.readLine()) != null)
				sb.append inputLine + "\r\n"

			sb.toString()

		} catch (Exception e){
            if (errorShow)
		    	O.oerrNoStack  ("url read error [${desc}] reading URL [" + url+ "] ", e)
            if (errorThrow)
                throw e;
		} finally {
			if (tt != null)
				tt.close();

		}
		return sb.toString();

	}

	public static String getTitle(def desc, String url, boolean errorShow, boolean errorThrow)
	{
		//O.o "GET TITLE [${desc}] coming from url[" + url + "]"
		String html = getHTML(desc, url, errorShow, errorThrow);
		//O.o("html:" + html)
		def titletag = "<title>"
		int iTitle = html.toLowerCase().indexOf(titletag)
		int iTitleEnd = html.toLowerCase().indexOf("</title>")
		if (iTitle == -1 || iTitleEnd == -1)
			return "no title"
		else
			return html[iTitle+7..iTitleEnd-1].trim()
		//String title = UtilStr.keepAllAfterNextOfThis(.toLowerCase, "<title>")
		//title = UtilStr.keepAllBeforeFirstOfThis(title, "</title>")
		//O.o("title:" + title)
	}

	public static String fixUrl(String url)
	{
		if (url == null)
			return null;

		if (url.startsWith("http"))
			return url
		else
			return "http://" + url;

	}

	public static boolean isUrl(String url, boolean onWayOIntoDB)
	{
        O.oNoFilter("---------- testing url:" + url)
        if (url.indexOf("http")== 0)
        {
            O.oNoFilter("------------------- true for:" + url);
            return true;
        }

        if (url.indexOf(" ") > 0)
            return false;

		if (url.indexOf(".") < 0)
			return false;

		if (url.startsWith("href"))
			return true;
        if (!onWayOIntoDB)
            return;

		if (url.indexOf(".com") > 0)
			return true
		if (url.indexOf(".edu") > 0)
			return true
		if (url.indexOf(".biz") > 0)
			return true
		if (url.indexOf(".org") > 0)
			return true
		if (url.indexOf(".info") > 0)
			return true
		if (url.indexOf(".ly") > 0)
			return true
		if (url.indexOf(".net") > 0)
			return true

		return false;


	}

	public static String buildHtmlHref(String text, String link)
	{
		"<a rel=\"nofollow\" href=\"" + link + "\">" + "ssdd:" + text + "</a> "
	}


	public static String buildHtmlHrefUtdLink(String text, boolean newwin, int trimOrZeroForAll)
	{
        O.oNoFilter("in buildHtmlHrefUtdLink");
		//http://146.115.68.118:8084/ustodo/todo?q=test+time
		String link = "?q=" + UtilStr.join(text.split(" "), "+")
        String display = (trimOrZeroForAll == 0 || text.length() < trimOrZeroForAll ) ? text :
            text.substring(0, trimOrZeroForAll) + "...";
		if (newwin)
			return "<a target=\"_blank\" rel=\"nofollow\" href=\"" + link + "\">"  + text + "</a> "
		else
			return "<a rel=\"nofollow\" href=\"" + link + "\">" + text + "</a> "
	}

	public static String convertHrefedLinkToRaw(String hrefedLink)
	{
        O.o "hrefedLink:" + hrefedLink
		hrefedLink = UtilStr.keepAllAfterFirstOfThis(hrefedLink, ">");
		hrefedLink = UtilStr.keepAllBeforeFirstOfThisNew(hrefedLink, "<");
		hrefedLink
	}





    public static String compileLinksToHREFs(String record) {
        O.oNoFilter("============ pre html compile [" + record + "]");
        //String[] parts = record.split("\\s");
        String[] parts = record.split(/\s/);
        //String[] parts = record.split(/\s/);
        String rtn = record;
        //O.oNoFilter("------------- in compileLinksToHREFs record [" + record + "]")
        int i = 0;
        int countHits = 0;
        for (String item : parts)
        {
            i++;
            if (!item.trim().equals(""))
            {
                try {
                    // adjustment based one of the answers
                    //O.oNoFilter("item i [$i] ------- processing item [$item]")
                    Pattern p = Pattern.compile("((mailto\\:|(news|(ht|f)tp(s?))\\://){1}\\S+)");
                    Matcher m = p.matcher(item);
                    if( m.matches() ){
                        //O.oNoFilter("matched");

                        item = m.group(1);
                    }

                    //O.oNoFilter("test item:" + item);
                    URL url = new URL(item);
                    countHits++;
                    //O.o "130120 item2 [$item]"
                    String link = url.getProtocol() + "://" + url.getHost()  + (url.getPath() == null ? "" : url.getPath()) + (url.getQuery() == null ? "" : "?" + url.getQuery());
                    //O.o "130120 item2.5 url.toString() ["+ url.toString() +"]"
                    url.getPath();
                    //O.o "130120 link [$link]"
                    //O.o "130120 item3 [$item]"
                    //O.o("link [" + link +  "]");
                    String urlDisplay = UtilStr.keepUpToZeroBased(link, 1000);
                    //O.o "130120 item4 [$item]"
                    if (link.length() > urlDisplay.length())
                        urlDisplay = urlDisplay + "..."

                    i++;
                    rtn = StringUtils.replace(rtn, item, "<a target=\"_blank\" rel=\"nofollow\" href=\"" + url.toString() + "\">"  + url.toString() + "</a> ");
                    //O.oNoFilter("item i [$i] record [$record] item [$item] rtn [$rtn]");
                } catch (MalformedURLException ignore) {
                    //if (item.contains("http"))
                    //O.oNoFilter ("item i [$i] ignoring MalformedURLException for item [$item]");
                }

            }
        } // for
        O.oNoFilter("================= 130210 FINAL compileLinksToHREFs items [$i] hits [$countHits], so converted [$record] to [$rtn]r\n\r\n");
        return rtn;
    }

    public static String detectAndConvertURLs2_NotWorkItSeems(String text) {
        //Regex pattern (unescaped), matches any Internet URL:
        //((mailto\:|(news|(ht|f)tp(s?))\://){1}\S+)
        Pattern p = Pattern.compile( "((mailto\\:|(news|(ht|f)tp(s?))\\://){1}\\S+)" );
        Matcher m = p.matcher( text );
        if( m.matches() ){
            return m.group(1);
        }else return null;
    }




}
