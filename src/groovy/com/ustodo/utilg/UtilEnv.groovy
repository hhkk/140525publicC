package com.ustodo.utilg;

import com.ustodo.utilg.O;

public class UtilEnv {

    public static String getEnv()
    {
        O.dumpstack();
        return getEnv("No callerID");
    }
    public static String getEnv(String callerId)
    {
        int i = 1;
        StringBuffer sb = new StringBuffer();
        sb.append(i++ + ". " + callerId + ":" + getEnvVar("os.name"));
        sb.append(i++ + ". " + callerId + ":" + getEnvVar("CLASSPATH"));
        sb.append(i++ + ". " + callerId + ":" + getEnvVar("java.class.path"));
        sb.append(i++ + ". " + callerId + ":" + getEnvVar("HOME"));
        sb.append(i++ + ". " + callerId + ":" + getEnvVar("JAVA_HOME"));
        sb.append(i++ + ". " + callerId + ":" + getEnvVar("GRAILS_HOME"));
        sb.append(i++ + ". " + callerId + ":" + getEnvVar("GROOVY_HOME"));
        sb.append(i++ + ". " + callerId + ":" + getEnvVar("SHELL"));
        sb.append(i++ + ". " + callerId + ":" + getEnvVar("PATH"));
        sb.append(i++ + ". " + callerId + ":" + getEnvVar("user.dir")); //System.getenv("user.dir")

        return sb.toString();
    }

    public static String getEnvVar(String varName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("System.getenv('" + varName + "') [" + System.getenv(varName) + "]\r\n") ;
        sb.append("System.getProperty('" + varName + "') [" + System.getProperty(varName) + "]\r\n");
        return sb.toString();
    }

}
