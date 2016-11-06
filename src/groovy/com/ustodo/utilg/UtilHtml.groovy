package com.ustodo.utilg

/**
 * Created with IntelliJ IDEA.
 * SecUser: hkon
 * Date: 4/26/12
 * Time: 3:30 AM
 * To change this template use File | Settings | File Templates.
 */
class UtilHtml {
    public static String formatForHtmlDisplay(String s)
    {
        //s = s.replaceAll("<", "&lt;");
        //s = s.replaceAll(">", "&gt;");

        //        str = str.Replace("&lt;", "<")
        //        str = str.Replace("&LT;", "<")
        //
        //        str = str.Replace("&gt;", ">")
        //        str = str.Replace("&GT;", ">")
        return s;
    }

    public static String convertHTMLtoRawText(String html)
    {
        html.replaceAll("\\<.*?\\>", "");
    }
}
