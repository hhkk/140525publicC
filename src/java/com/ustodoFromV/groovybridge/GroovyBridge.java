package com.ustodoFromV.groovybridge;

//import groovy.lang.Script;

public class GroovyBridge {
	
    //return new com.mongodb.Mongo(getMongoIp(), mongoport);
	public static void test()
	{
		// Main.java
		//		$ groovyc Script.groovy
		//		$ javac -classpath .:$GROOVY_HOME/embeddable/groovy-all-1.7.5.jar Main.java
		//		$ java -classpath .:$GROOVY_HOME/embeddable/groovy-all-1.7.5.jar Main
		//		Hello, World!		
	}
	
	public static void main(String[] args) 
	{
        // com.ustodo.groovybridge.Script script = new com.ustodo.groovybridge.Script();
        //script.hello_world();
		
	}
}



// from http://stackoverflow.com/questions/3989592/calling-a-groovy-function-from-java 

//The simplest way is to compile the script into a java class file and just call it directly. Example:
//
//	// Script.groovy
//	def hello_world() {
//	    println "Hello, World!"
//	}
//
//	// Main.java
//	public class Main {
//	    public static void main(String[] args) {
//	        Script script = new Script();
//	        script.hello_world();
//	    }
//	}
//
//	$ groovyc Script.groovy
//	$ javac -classpath .:$GROOVY_HOME/embeddable/groovy-all-1.7.5.jar Main.java
//	$ java -classpath .:$GROOVY_HOME/embeddable/groovy-all-1.7.5.jar Main
//	Hello, World!