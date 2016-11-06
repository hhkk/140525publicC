
package com.ustodo.utilg;

import java.util.Calendar;
import java.sql.Date;
import com.ustodo.utilg.O;


public class UtilGroovyMetaObject {
	int testInt = -99;

	public UtilGroovyMetaObject()
	{
		//O.o ("idx:" + i + ". FileLine: date [" + date + "] line [" + line + "]");
	}

	public static void testMethod(int x)
	{
		UtilGroovyMetaObject f21 = new UtilGroovyMetaObject ()
		emitObj (f21)
		def f22 = f21.metaClass
		listMethsProps(f22);
		//listAllMethods(f22);

	}

    public static void emitObj(Object o)
    {
        listMethsProps(o);
    }

    // UtilGroovyMetaObject.  getClass
    public static void getClass(Object o)
    {
        listMethsProps(o);
    }



    static def done = 0;
	public static void listMethsProps(Object oInput)
	{
		O.o "TOP listMethsProps"
		O.oc "listAllVars o", oInput // com.ustodo.utilg.UtilGroovyReflection
		O.o "oInput.getClass().toString():" + oInput.getClass().toString()
		O.o "oInput.getClass().getName():" +  oInput.getClass().getName()
		//assert oInput.getClass().getName().toString().equals("com.ustodo.utilg.UtilGroovyReflection")
		org.codehaus.groovy.runtime.HandleMetaClass o1 = oInput.metaClass
		//O.oc "listAllVars oa", oa //

		// 		Eval.me("def o1p3a = o.metaClass.methods")
		// LIST METHODS NO STAR
		def o1p3 = oInput.metaClass.methods  // j	ava.util.ArrayList of org.codehaus.groovy.reflection.CachedMethod
		def o1p4 = oInput.metaClass.methods*.toString()
		//def o1p5 = o.metaClass.methods*.name
		O.oc("a oInput.metaClass", oInput.metaClass) // org.codehaus.groovy.runtime.HandleMetaClass  but in shell? example ?? groovy.lang.ExpandoMetaClass

		org.codehaus.groovy.runtime.HandleMetaClass mcInput = oInput.metaClass;

		// metaclass methods and example first
		                    O.oc("\r\n-----------\r\nA oInput.metaClass.methods", 
			com.ustodo.utilg.O.formatList("A methods", oInput.metaClass.methods))
		O.oc("A mcInput.methods[0]", mcInput.methods[0])

		// metaclass metamethods and example first
		O.oc("\r\n-----------\r\nB oInput.metaClass.metaMethods*.name.sort().unique()", 
			com.ustodo.utilg.O.formatList("B metaMethods", oInput.metaClass.metaMethods*.name.sort().unique()))
		O.oc("B mcInput.metaMethods[0]", mcInput.metaMethods[0])

		// metaclass properties and example first
		                     O.oc("\r\n-----------\r\nC mcInput.properties*.name.sort().unique()", 
         com.ustodo.utilg.O.formatList("C properties",  mcInput.properties*.name.sort().unique()))
		O.oc("C mcInput.properties[0]", mcInput.properties[0])

		// test 0'th property (not meta) (not value)
		O.oc("\r\n-----------\r\nD test 0'th property (not meta) (not value) mcInput.getProperty(mcInput.properties*.name.sort().unique()[0])", 
		                               	                                     mcInput.getProperty(mcInput.properties*.name.sort().unique()[0]))

		// test 0'th property (not meta) value
		O.oc("\r\n-----------\r\nE test 0'th property (not meta) value mcInput.getProperty(mcInput.properties*.name.sort().unique()[0])", 
			                                                           mcInput.getProperty(mcInput.properties*.name.sort().unique()[0]))

		// test 0'th meta property (not value)
		O.oc("\r\n-----------\r\nF test 0'th meta property (not value) mcInput.getMetaProperty(mcInput.properties*.name.sort().unique()[0])", 
 			                                                           mcInput.getMetaProperty(mcInput.properties*.name.sort().unique()[0]))

		O.o("donehbk")
		
		
		//	O.oc("folder.metaClass", folder.metaClass)
		//	O.ofmt("folder.metaClass.methods*.name", folder.metaClass.methods*.name.sort().unique())
		//	O.ofmt("folder.metaClass.metaMethods*.name", folder.metaClass.metaMethods*.name.sort().unique())
				//org.codehaus.groovy.runtime.HandleMetaClass y = x.metaClass
				//O.oc "x:", x
				//O.oc "y:", y // org.codehaus.groovy.runtime.HandleMetaClass
				//O.ofmt("y.methods*.name:", y.methods*.name.sort().unique())  // [[__$swapInit, equals, getClass, getMetaClass, getProperty, hashCode, invokeMethod, notify, notifyAll, searchEmailOptionalSave, setMetaClass, setProperty, toString, wait]]
				//O.ofmt("y.getMetaMethods:", y.getMetaMethods())  //
				
				//	O.ofmt("folder.metaClass.metaMethods*.name", folder.metaClass.metaMethods*.name.sort().unique())
							// METAOBJECT TEST
							//if (i > Math.max(esx.numEmitReq, esx.numToDBReq))
							//	throw new Exception("hbkloopexit") // how to get out of a loop
							//else
							//{
							//					if (i < esx.getNumEmitReq()) {
							//						if (i< 10)
							//						{
							//							UtilMail.emitMsg(imapMsg, true);
							//							UtilGroovyMetaObject.emitObj(imapMsg)
							//						}
							//						else
							//							UtilMail.emitMsg(imapMsg, false);
							//
							//						esx.esr.numEmitted++
							//					}
	
		
		
		
		

	}



	public static void main (String[] args )
	{
		testMethod(1);
	}

}