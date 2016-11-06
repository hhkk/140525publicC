package com.ustodoFromV;

import com.ustodoFromV.util.O;

public class Cfg {

    //private static String mongoip = "50.19.78.201";
    private static String mongoip = "localhost";
    private static int mongoport = 27017;
    //public static String dbname = "TestLiveV5";
    //private static String dummyDbname = "dummyDbnameLivetest3";
    private static String dummyDbname = "livetest";
	private static String dummyCollname = "dummyCollnameLivetest3";
    private static String dummyUserName = "dummyUsernameLivetest3";
    public static String getDummyDbname() {return dummyDbname;}
	public static String getDummyCollname() {return dummyCollname;}
	public static String getDummyUserName() {return dummyUserName;}

    private static boolean outputport = false;
    public static int getMongoPort() {
    	if (!outputport)
    	{
    		O.o ("Cfg:mongoport" + mongoport);
    		outputport = true;
    	}
    	return mongoport;
    }
    
    private static boolean outputip;
    public static String getMongoip()
    {
    	if (!outputip)
    	{
    		O.o ("Cfg:mongoip" + mongoip);
    		outputip = true;
    	}
    	return mongoip; 
	}

    private static boolean outputdbname = false;
	public static String getDbname()
    {
    	if (!outputdbname)
    	{
    		O.o ("Cfg:dummyDbname" + dummyDbname);
    		outputdbname = true;
    	}
    	return dummyDbname;
    }
    public static String getCollNameThisUserFavs(String username)
    {
    	return "favs";
    }
    
    
    
    
}
