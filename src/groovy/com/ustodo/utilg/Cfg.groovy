package com.ustodo.utilg
//CONFIGGROOVY
/**
 important:
 public static String mongoip = getMongoIp(); // amazon
 public static int mongoport = 27017;
 String ipAmazon = "50.19.78.201";
 String triggerFile = "/tmp/UTDDebug_UseAmazonIPforMongo.txt";
  */

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import grails.plugins.springsecurity.Secured
//import org.example.SecOldUser;

import com.mongodb.DBCollection

class Cfg {
    def springSecurityService; // hbkhbkhbk

    // pwd = current working directory = /Users/hkon/sw/ustodo/110504utd/ustodo112
    public static String env = UtilEnv.getEnv("Cfg.groovy call")


    public static boolean newauth = true;
    public static def debugging = [];
    //public static def debugging = ["com.ustodo.todo.TodoController", "com.ustodo.todo.TodoController"];
    public static String dbname = "livetest";
    public static String collnameFavsCkckck = "favsckckck"
    public static String collnameEmailsSuckedIn = "EmailsSuckedIn"
    //private static String collnameFavsxx = "favsx"w
    //private static String collnameFavsxx = "favsx"
    public static String fnFavsCkckckSmall =
        "/Users/hkon/sw/ustodo/favsckckcksmall.csv";
    public static String fnFavsCkckckExport = "/tmp/tfavsckckckexport.csv";






    // PICK A DB BY IP ADDRESS hbkhere ******************************************************************************************************************************
    public static String mongoip = getMongoIp(); // amazon
    public static int mongoport = 27017;

    private static String getMongoIp()
    {
        // trick to have home server point to amazon but use localhost within Amazon
        String ip = "localhost";
        //String ipAmazon = "50.19.78.201";
        String ipAmazon = "localhost";
        String triggerFile = "/tmp/UTDDebug_UseAmazonIPforMongo.txt";

        if (UtilFile.exists(triggerFile))
            ip = ipAmazon;

        com.ustodo.utilg.O.oNoFilter("******* based on file [" + triggerFile + "] in getMongoIP, returning:" + ip );
        return ip;
    }

//	public static String getCollnameFavsCkckck(
//		grails.plugins.springsecurity.SpringSecurityService springSecurityService)
//	{
//		def user = SecOldUser.get(springSecurityService.principal.id)
//		//O.oc("user:", user) // org.example.SecOldUser
//		//O.oc("user.username:", user.username) // grails.plugins.springsecurity.SpringSecurityService
//		def username = SecOldUser.get(springSecurityService.principal.username)
//		UtilStr.keepAllButLastX(collnameFavsxx,) + username
//	}
//


    public static String getFqFileName(String username)
    {

        return Cfg.getRootFolder() + "/favs" + username + ".csv"
    }


    public static String   getCollNameThisUserFavs(String user)
    {
        return "favs" + user;
    }

    public static String getCollNameThisUserEmail(String user)
    {
        return "Email_" + user;
    }

    public static DBCollection getCollThisUserFavs(String user)
    {
        UtilMongo.getColl(getCollNameThisUserFavs(user))
    }

    public static DBCollection getCollThisUserEmail(String user)
    {
        UtilMongo.getColl(getCollNameThisUserEmail(user))
    }


    public static String getRootFolder()
    {
        return System.getProperty("user.dir")
    }

    public static boolean configSlurpDoIt()
    {
        //String fn = "/Users/hkon/sw/ustodo/110504utd/ustodo-1.1.1/grails-app/conf/Config.groovy";
        // pwd = current working directory = /Users/hkon/sw/ustodo/110504utd/ustodo112
        String fn = getRootFolder() + "/grails-app/conf/Config.groovy";
        //System.out.println("getRootFolder():" + getRootFolder());
        O.oNoFilter("configSlurpDoIt fn:" + fn);
        def fxxx = new File(fn);
        //O.oNoFilter "configSlurpDoIt xxxconfigSlurpDoIt.f.getPath:" + fxxx.getPath();
        //        fxxx.readLines().each {it, ln ->
        //            O.o ln + ". configSlurpDoIt it:" + it
        //        }

        //O.o "UNIT TEST ENV [" + UtilEnv.getEnv() + "]";
        GroovyClassLoader classLoader = new GroovyClassLoader(this.class.classLoader)
        // To Do: top level config hard coded here:
        // O.o "loaded config with slurper:" + fn
        def f = new File(fn);
        //O.o "configSlurpDoIt.f.getPath:" + f.getPath();
        //O.oNoFilter("pre ConfigurationHolder.config ");
        ConfigurationHolder.config = new ConfigSlurper().parse(new File(fn).toURL())
        //O.oNoFilter("post ConfigurationHolder.config ");

        return true;
    }


}
