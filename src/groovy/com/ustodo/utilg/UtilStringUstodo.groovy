package com.ustodo.utilg

class UtilStringUstodo {

    public static void main (String[] args)
    {
        O.o "aa:" + keepAllBeforeLastOfThis ("tester555", " / ")
    }

    public static String buildCategoryForReadAfterWrite (String flrText)
	{

        def rtn=null;
//        def b = a.split(/[\/|\/\/]\s/)
//        int max = Math.min(2, b.length)
//
//        def lastCategoryElemeToKeep = null;
//        if (max > 0)
//        {
//            String tempToGetLength = UtilStr.keepAllAfterFirstOfThis(b[b.length-1]).length();
//            rtn = flrText[0..(flrText.length() - tempToGetLength.length())];
//        }
//
//        return rtn;
//

        //PArser.(/[\/|\/\/]\s/)


        // keep two cat terms if they exist
        if (flrText.contains(" // "))
        {
            rtn = UtilStr.keepAllBeforeFirstOfThis(flrText, " // ").trim() + " // ";
        }
        else if (flrText.contains(" / "))
        {
            rtn = UtilStr.keepAllBeforeFirstOfThis(flrText, " / ").trim() + " / "
        }
        rtn;
    }


}

