package com.ustodo.utilg

import java.util.Vector

import com.google.code.com.sun.mail.imap.IMAPInputStream;
import com.google.code.com.sun.mail.imap.IMAPMessage;
import com.google.code.com.sun.mail.imap.IMAPSSLStore
import com.google.code.javax.mail.Folder
import com.google.code.javax.mail.Store;
import com.google.code.javax.mail.internet.MimeMessage.RecipientType
import com.google.code.javax.mail.search.BodyTerm
import com.google.code.javax.mail.search.ComparisonTerm
import com.google.code.javax.mail.search.FromStringTerm
import com.google.code.javax.mail.search.IntegerComparisonTerm;
import com.google.code.javax.mail.search.MessageNumberTerm;
import com.google.code.javax.mail.search.OrTerm
import com.google.code.javax.mail.search.ReceivedDateTerm
import com.google.code.javax.mail.search.SearchTerm
import com.google.code.javax.mail.search.SubjectTerm
import com.mongodb.BasicDBObject;
import com.ustodo.utilj.Ozz
import grails.web.JSONBuilder;


class UtilJson {
	public static void main (String[] args)
	{
		try {
			test();
			//test2(); //120303
		}
		catch (Throwable t )
		{
			O.or("t", t);
			throw t;
			assert(false);
		} finally {
			assert (true)
		}

	}
	public static void test2() // 120304
	{
		//BasicDBObject dbo = new BasicDBObject();
		//BasicDBObject dbonested = new BasicDBObject();
		//dbonested.put(dbonested)
		
				
		
		 
		
	}
	public static void test() // pre 120304
	{
		//def json = new JSONBuilder()
		//def slurper = new JSONSlurper()
		//def result = slurper.parseText('{"person":{"name":"Guillaume","age":33,"pets":["dog","cat"]}}')
		// (new JSONTest()).runConsole();
		//String json = "{ 'str' : 'asdfasd' , 'long' : 123123123123 , 'int' : 5 , 'float' : 0.4 , 'bool' : false , 'date' : { '$date' : '2011-05-18T18:56:00Z'} , 'pat' : { '$regex' : '.*' , '$options' : ''} , 'oid' : { '$oid' : '4d83ab3ea39562db9c1ae2ae'} , 'ref' : { '$ref' : 'test.test' , '$id' : { '$oid' : '4d83ab59a39562db9c1ae2af'}} , 'code' : { '$code' : 'asdfdsa'} , 'codews' : { '$code' : 'ggggg' , '$scope' : { }} , 'ts' : { '$ts' : 1300474885 , '$inc' : 10} , 'null' :  null, 'uuid' : { '$uuid' : '60f65152-6d4a-4f11-9c9b-590b575da7b5' }}";
		String json = "{ 'str' : 'asdfasd' , 'long' : 123123123123 , 'int' : 5 , 'float' : 0.4 , 'bool' : false , 'date' : { 'date' : '2011-05-18T18:56:00Z'} , 'pat' : { 'regex' : '.*' , 'options' : ''} , 'oid' : { 'oid' : '4d83ab3ea39562db9c1ae2ae'} , 'ref' : { 'ref' : 'test.test' , 'id' : { 'oid' : '4d83ab59a39562db9c1ae2af'}} , 'code' : { 'code' : 'asdfdsa'} , 'codews' : { 'code' : 'ggggg' , 'scope' : { }} , 'ts' : { 'ts' : 1300474885 , 'inc' : 10} , 'null' :  null, 'uuid' : { 'uuid' : '60f65152-6d4a-4f11-9c9b-590b575da7b5' }}";
		BasicDBObject a = (BasicDBObject) com.mongodb.util.JSON.parse(json);
		assert (a.get("str").equals("asdfasd"));
		assert (a.get("str").equals("asdfasd"));
		assert (a.get("int").equals(5));
		assert (a.get("long").equals(123123123123L));
		assert (a.get("float").equals(0.4d));
		assert (a.get("bool").equals(false));
		//		SimpleDateFormat format =
		//				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		//		format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
		//		assert (a.get("date").equals(format.parse("2011-05-18T18:56:00Z")));
		//		Pattern pat = (Pattern) a.get("pat");
		//		Pattern pat2 = Pattern.compile(".*", BSON.regexFlags(""));
		//		assert (pat.pattern().equals(pat2.pattern()));
		//		assert (pat.flags() == (pat2.flags()));
		//		ObjectId oid = (ObjectId) a.get("oid");
		//		assert (oid.equals(new ObjectId("4d83ab3ea39562db9c1ae2ae")));
		//		DBRef ref = (DBRef) a.get("ref");
		//		assert (ref.equals(new DBRef(null, "test.test", new ObjectId("4d83ab59a39562db9c1ae2af"))));
		//		assert (a.get("code").equals(new Code("asdfdsa")));
		//		assert (a.get("codews").equals(new CodeWScope("ggggg", new BasicBSONObject())));
		//		assert (a.get("ts").equals(new BSONTimestamp(1300474885, 10)));
		//		assert (a.get("uuid").equals(UUID.fromString("60f65152-6d4a-4f11-9c9b-590b575da7b5")));
		//		String json2 = JSON.serialize(a);
		//		BasicDBObject b = (BasicDBObject) JSON.parse(json2);
		//		a.equals(b);
		//		assert (a.equals(b));
		O.o "done test"

	}

	public static BasicDBObject cvtJsonToDbo(String json)
	{
		BasicDBObject a = (BasicDBObject) com.mongodb.util.JSON.parse(json);
		a

	}
	public static BasicDBObject jToD(String json)
	{
		cvtJsonToDbo(json)
	}

	public static String cvtDboToJson(BasicDBObject dbo)
	{
		dbo.toString()
	}
	public static BasicDBObject dToJ(BasicDBObject dbo)
	{
		cvtJsonToDbo(dbo)
	}

	//
	//
	//
	//		json.state
	//		{
	//			name "Colorado"
	//			statehood 1876
	//			capital "Denver"
	//			majorCities "Denver", "Colorado Springs", "Fort Collins"
	//		}
	//
	//		println json
	//
	//		json.state
	//		{
	//			name "Utah"
	//			statehood 1896
	//			capital "Salt Lake City"
	//			majorCities "Salt Lake City", "Ogden", "Provo", "Logan"
	//		}
	//
	//		println json
	//
	//		//As the example code above indicates, Groovy's JsonBuilder is intentionally similar to the other Groovy builder such as MarkupBuilder. When the above script is run, the output appears as shown in the following screen snapshot.
	//
	//
	//		//There is nothing wrong with the above, but many JSON examples use white space (new lines and indenting) to improve the readability of the JSON. Groovy makes this easy with the JsonOutput.prettyPrint(String) method. This method accepts a String and returns a JSON-ified version of that String. This method is applied to the above code listing and the adapted version is shown in the next code listing.
	//
	//		/**
	//		 * buildJsonState.groovy (adapted for pretty print)
	//		 *
	//		 * Simple Groovy script demonstrating Groovy 1.8 support for Json generation and
	//		 * for providing the JSON output in "pretty print" format (with white space).
	//		 * Requires Groovy 1.8.
	//		 */
	//
	//
	//		def json = new JsonBuilder()
	//
	//		json.state
	//		{
	//			name "Colorado"
	//			statehood 1876
	//			capital "Denver"
	//			majorCities "Denver", "Colorado Springs", "Fort Collins"
	//		}
	//
	//		println JsonOutput.prettyPrint(json.toString())
	//
	//		json.state
	//		{
	//			name "Utah"
	//			statehood 1896
	//			capital "Salt Lake City"
	//			majorCities "Salt Lake City", "Ogden", "Provo", "Logan"
	//		}
	//
	//		println JsonOutput.prettyPrint(json.toString())
	//		//The output from running the script above is the same in terms of substance returned, but the white space additions make the change appear dramatic and make the output more readable. The next screen snapshot demonstrates this.
	//
	//
	//		//The examples above represent valid JSON with name/value pairs. The list of major cities was included to show how JSON represents multiple items in the "value" portion.
	//
	//		//The just-mentioned JsonOutput class does more than support pretty printing a JSON string. It also includes methods for producing JSON Strings appropriate for a provided Java (or Groovy) data structure. These methods are all called toJson and are overloaded based on the provided parameter. The following structures and data types are currently supported: Boolean, Number, String, Closure, Object, and Map.
	//
	//		//The JsonOutput.prettyPrint(String) method was useful for providing the JSON in a more intuitive form. However, I did not really need to use the JsonOutput class at all because JsonBuilder (which I was already using) has its own toPrettyString() method that can be invoked rather than explicitly or implicitly calling its toString() as I did in the examples above. The next code example shows this. Because JsonOutput is no longer needed, I was able to remove its import statement. I also decided to add a few cities as "major cities" while changing the code anyway.
	//
	//		//buildJsonState.groovy with Pretty Print via JsonBuilder
	//
	//		/**
	//		 * buildJsonState.groovy (adapted for pretty print)
	//		 *
	//		 * Simple Groovy script demonstrating Groovy 1.8 support for Json generation and
	//		 * for providing the JSON output in "pretty print" format (with white space).
	//		 * Requires Groovy 1.8.
	//		 */
	//
	//		def json = new JsonBuilder()
	//
	//		json.state
	//		{
	//			name "Colorado"
	//			statehood 1876
	//			capital "Denver"
	//			majorCities "Denver", "Colorado Springs", "Fort Collins", "Boulder", "Grand Junction"
	//		}
	//
	//		println json.toPrettyString()
	//
	//		json.state
	//		{
	//			name "Utah"
	//			statehood 1896
	//			capital "Salt Lake City"
	//			majorCities "Salt Lake City", "Ogden", "Provo", "Logan", "St. George"
	//		}
	//
	//		println json.toPrettyString()
	//		//The output from the above looks the same as before, but with the extra cities.
	//
	//		//I find that Groovy's slurping of XML is more important to me than Groovy's writing of XML. It could turn out that the same will be true for Groovy's JSON support and the reading of JSON may be the most important feature. JsonSlurper works similarly to XmlSlurper as shown in the next code listing which slurps the JSON written by the last example (I piped that examples output to the file states.js and removed all but the part describing Colorado).
	//
	//		// this goes on disk as states.js
	//		//		{
	//		//			"state": {
	//		//				"name": "Colorado",
	//		//				"statehood": 1876,
	//		//				"capital": "Denver",
	//		//				"majorCities": [
	//		//					"Denver",
	//		//					"Colorado Springs",
	//		//					"Fort Collins",
	//		//					"Boulder",
	//		//					"Grand Junction"
	//		//				]
	//		//			}
	//		//		}
	//		//The listing above shows the relevant portion of the output of the above scripts. This output is the input for the next code listing that demonstrates slurping JSON with Groovy.
	//
	//		/**
	//		 * readStatesJson.groovy
	//		 *
	//		 * Simple example of slurping JSON in Groovy 1.8.
	//		 */
	//
	//		def jsonPayload = new File("states.js").text
	//
	//		import groovy.json.JsonSlurper
	//		def slurper = new JsonSlurper()
	//		def states = slurper.parseText(jsonPayload)
	//
	//		states.state.each
	//		{
	//			println it
	//		}
	//
	//	}

}


//
//
//
///**
// *      Copyright (C) 2008 10gen Inc.
// *
// *   Licensed under the Apache License, Version 2.0 (the "License");
// *   you may not use this file except in compliance with the License.
// *   You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// *   Unless required by applicable law or agreed to in writing, software
// *   distributed under the License is distributed on an "AS IS" BASIS,
// *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *   See the License for the specific language governing permissions and
// *   limitations under the License.
// */
//
//package com.ustodo.utilg
//
//import java.text.ParseException
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.GregorianCalendar
//import java.util.SimpleTimeZone
//import java.util.UUID
//import java.util.regex.Pattern
//
//import org.bson.BSON
//import org.bson.BasicBSONObject
//import org.bson.types.BSONTimestamp
//import org.bson.types.Code
//import org.bson.types.CodeWScope
//import org.bson.types.ObjectId
//
//import com.mongodb.BasicDBObject
//import com.mongodb.BasicDBObjectBuilder
//import com.mongodb.DBObject
//import com.mongodb.DBRef
//import com.mongodb.util.JSON
//import com.mongodb.util.JSONParseException
//
//public class JSONTest extends com.mongodb.util.TestCase {
////	public class JSONTest extends com.mongodb.util.TestCase {
//	//https://github.com/mongodb/mongo-java-driver/blob/master/src/test/com/mongodb/util/JSONTest.java
////    // @org.testng.annotations.Test(groups = {"basic"})
//	public void testNumbers(){
//		assertEquals(JSON.serialize(JSON.parse("{'x' : 5 }")), "{ \"x\" : 5}");
//		assertEquals(JSON.serialize(JSON.parse("{'x' : 5.0 }")), "{ \"x\" : 5.0}");
//		assertEquals(JSON.serialize(JSON.parse("{'x' : 0 }")), "{ \"x\" : 0}");
//		assertEquals(JSON.serialize(JSON.parse("{'x' : 0.0 }")), "{ \"x\" : 0.0}");
//		assertEquals(JSON.serialize(JSON.parse("{'x' : 500 }")), "{ \"x\" : 500}");
//		assertEquals(JSON.serialize(JSON.parse("{'x' : 500.0 }")), "{ \"x\" : 500.0}");
//		assertEquals(JSON.serialize(JSON.parse("{'x' : 0.500 }")), "{ \"x\" : 0.5}");
//		assertEquals(JSON.serialize(JSON.parse("{'x' : 5. }")), "{ \"x\" : 5.0}");
//		assertEquals(JSON.serialize(JSON.parse("{'x' : 5.0e+1 }")), "{ \"x\" : 50.0}");
//		assertEquals(JSON.serialize(JSON.parse("{'x' : 5.0E-1 }")), "{ \"x\" : 0.5}");
//	}
//
//  //  // @org.testng.annotations.Test(groups = {"basic"})
//	public void testLongValues() {
//		Long val = Integer.MAX_VALUE + 1L;
//		String test = String.format("{ \"x\" : %d}", val);
//		assertEquals(JSON.serialize(JSON.parse(test)), test);
//	}
//
//	//// @org.testng.annotations.Test(groups = {"basic"})
//	public void testSimple() {
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : true}")), "{ \"csdf\" : true}");
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : false}")), "{ \"csdf\" : false}");
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : null}")), "{ \"csdf\" :  null }");
//	}
//	
//	//// @org.testng.annotations.Test(groups = {"basic"})
//	public void testString() {
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : \"foo\"}")), "{ \"csdf\" : \"foo\"}") ;
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : \'foo\'}")), "{ \"csdf\" : \"foo\"}") ;
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : \"a\\\"b\"}")), "{ \"csdf\" : \"a\\\"b\"}");
//		assertEquals(JSON.serialize(JSON.parse("{\n\t\"id\":\"1689c12eb234c54a84ebd100\",\n}")),
//					 "{ \"id\" : \"1689c12eb234c54a84ebd100\"}");
//	}
//	
//	//// @org.testng.annotations.Test(groups = {"basic"})
//	public void testArray() {
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : [\"foo\"]}")), "{ \"csdf\" : [ \"foo\"]}") ;
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : [3, 5, \'foo\', null]}")), "{ \"csdf\" : [ 3 , 5 , \"foo\" ,  null ]}") ;
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : [3.0, 5.0, \'foo\', null]}")), "{ \"csdf\" : [ 3.0 , 5.0 , \"foo\" ,  null ]}") ;
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : [[],[[]],false]}")), "{ \"csdf\" : [ [ ] , [ [ ]] , false]}");
//	}
//
//	//// @org.testng.annotations.Test(groups = {"basic"})
//	public void testObject() {
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : {}}")), "{ \"csdf\" : { }}") ;
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : {\"foo\":\"bar\"}}")), "{ \"csdf\" : { \"foo\" : \"bar\"}}") ;
//		assertEquals(JSON.serialize(JSON.parse("{'csdf' : {\'hi\':{\'hi\':[{}]}}}")), "{ \"csdf\" : { \"hi\" : { \"hi\" : [ { }]}}}");
//	}
//
//	// @org.testng.annotations.Test(groups = {"basic"})
//	public void testMulti() {
//		assertEquals(JSON.serialize(JSON.parse("{\'\' : \"\", \"34\" : -52.5}")), "{ \"\" : \"\" , \"34\" : -52.5}") ;
//	}
//
//	// @org.testng.annotations.Test(groups = {"basic"})
//	public void testUnicode() {
//		assertEquals(JSON.serialize(JSON.parse("{'x' : \"hi\\u0020\"}")),"{ \"x\" : \"hi \"}") ;
//		assertEquals(JSON.serialize(JSON.parse("{ \"x\" : \"\\u0E01\\u2702\\uF900\"}")), "{ \"x\" : \"\u0E01\u2702\uF900\"}");
//		assertEquals(JSON.serialize(JSON.parse("{ \"x\" : \"foo\\u0020bar\"}")), "{ \"x\" : \"foo bar\"}");
//	}
//
//	// @org.testng.annotations.Test(groups = {"basic"})
//	public void testBin() {
//	   // byte b[] = {'a', 'b', 0, 'd'};
//		DBObject obj = BasicDBObjectBuilder.start().add("b", b).get();
//		//assertEquals(JSON.serialize(obj), "{ \"b\" : <Binary Data>}");
//	}
//
//
//	// @org.testng.annotations.Test(groups = {"basic"})
//	public void testErrors(){
//		boolean threw = false;
//		try {
//			JSON.parse("{\"x\" : \"");
//		}
//		catch(JSONParseException e) {
//			threw = true;
//		}
//		assertEquals(threw, true);
//		threw = false;
//		try {
//			JSON.parse("{\"x\" : \"\\");
//		}
//		catch(JSONParseException e) {
//			threw = true;
//		}
//		assertEquals(threw, true);
//		threw = false;
//		try {
//			JSON.parse("{\"x\" : 5.2");
//		}
//		catch(JSONParseException e) {
//			threw = true;
//		}
//		assertEquals(threw, true);
//		threw = false;
//		try {
//			JSON.parse("{\"x\" : 5");
//		}
//		catch(JSONParseException e) {
//			threw = true;
//		}
//		assertEquals(threw, true);
//		threw = false;
//		try {
//			JSON.parse("{\"x\" : 5,");
//		}
//		catch(JSONParseException e) {
//			threw = true;
//		}
//		assertEquals(threw, true);
//		threw = false;
//	}
//
//	// @org.testng.annotations.Test(groups = {"basic"})
//	public void testBasic(){
//		assertEquals( JSON.serialize(JSON.parse("{}")), "{ }");
//		assertEquals( JSON.parse(""), null );
//		assertEquals( JSON.parse("     "), null);
//		assertEquals( JSON.parse(null), null);
//
//		boolean threw = false;
//		try {
//			JSON.parse("{");
//		}
//		catch(JSONParseException e) {
//			threw = true;
//		}
//		assertEquals(threw, true);
//		threw = false;
//
//		try {
//			JSON.parse("}");
//		}
//		catch(JSONParseException e) {
//			threw = true;
//		}
//		assertEquals(threw, true);
//		threw = false;
//
//		try {
//			JSON.parse("{{}");
//		}
//		catch(JSONParseException e) {
//			threw = true;
//		}
//		assertEquals(threw, true);
//		threw = false;
//
//		try {
//			JSON.parse("4");
//		}
//		catch(JSONParseException e) {
//			threw = true;
//		}
//		assertEquals(threw, false);
//		threw = false;
//		
//		assertEquals( 4 , JSON.parse( "4" ) );
//	}
//
//	// @org.testng.annotations.Test
//	public void testNumbers2(){
//		DBObject x = new BasicDBObject( "x" , 123 );
//		assertEquals( x , JSON.parse( x.toString() ) );
//
//		x = new BasicDBObject( "x" , 123123123123L );
//		assertEquals( x , JSON.parse( x.toString() ) );
//
//		x = new BasicDBObject( "x" , 123123123 );
//		assertEquals( x , JSON.parse( x.toString() ) );
//	}
//
//	void _escapeChar( String s ){
//		String thingy = "va" + s + "lue";
//		DBObject x = new BasicDBObject( "name" , thingy );
//		x = (DBObject)JSON.parse( x.toString() );
//		assertEquals( thingy , x.get( "name" ) );
//
//		thingy = "va" + s + s + s + "lue" + s;
//		x = new BasicDBObject( "name" , thingy );
//		x = (DBObject)JSON.parse( x.toString() );
//		assertEquals( thingy , x.get( "name" ) );
//	}
//
//
//
//	// @org.testng.annotations.Test
//	public void testEscape1(){
//		String raw = "a\nb";
//
//		DBObject x = new BasicDBObject( "x" , raw );
//		assertEquals( "\"a\\nb\"" , JSON.serialize( raw ) );
//		assertEquals( x , JSON.parse( x.toString() ) );
//		assertEquals( raw , ((DBObject)JSON.parse( x.toString() ) ).get( "x" ) );
//
//		x = new BasicDBObject( "x" , "a\nb\bc\td\re" );
//		assertEquals( x , JSON.parse( x.toString() ) );
//
//		
//		String thingy = "va\"lue";
//		x = new BasicDBObject( "name" , thingy );
//		x = (DBObject)JSON.parse( x.toString() );
//		assertEquals( thingy , x.get( "name" ) );
//
//		thingy = "va\\lue";
//		x = new BasicDBObject( "name" , thingy );
//		x = (DBObject)JSON.parse( x.toString() );
//		assertEquals( thingy , x.get( "name" ) );
//
//		assertEquals( "va/lue" , (String)JSON.parse("\"va\\/lue\"") );
//		assertEquals( "value" , (String)JSON.parse("\"va\\lue\"") );
//		assertEquals( "va\\lue" , (String)JSON.parse("\"va\\\\lue\"") );
//
//		_escapeChar( "\t" );
//		_escapeChar( "\b" );
//		_escapeChar( "\n" );
//		_escapeChar( "\r" );
//		_escapeChar( "\'" );
//		_escapeChar( "\"" );
//		_escapeChar( "\\" );
//	}
//
//   // @org.testng.annotations.Test
////   public void testPattern() {
////       String x = "^Hello$";
////       String serializedPattern =
////	   "{ \"$regex\" : \"" + x + "\" , \"$options\" : \"" + "i\"}";
////
////       Pattern pattern = Pattern.compile( x , Pattern.CASE_INSENSITIVE);
////       assertEquals( serializedPattern, JSON.serialize(pattern));
////
////       BasicDBObject a = new BasicDBObject( "x" , pattern );
////       assertEquals( "{ \"x\" : " + serializedPattern + "}" , a.toString() );
////
////       DBObject b = (DBObject)JSON.parse( a.toString() );
////       assertEquals( b.get("x").getClass(), Pattern.class );
////       assertEquals( a.toString() , b.toString() );
////   }
//
////   // @org.testng.annotations.Test
////   public void testRegexNoOptions() {
////       String x = "^Hello$";
////       String serializedPattern =
////       "{ \"$regex\" : \"" + x + "\"}";
////
////       Pattern pattern = Pattern.compile( x );
////       assertEquals( serializedPattern, JSON.serialize(pattern));
////
////       BasicDBObject a = new BasicDBObject( "x" , pattern );
////       assertEquals( "{ \"x\" : " + serializedPattern + "}" , a.toString() );
////
////       DBObject b = (DBObject)JSON.parse( a.toString() );
////       assertEquals( b.get("x").getClass(), Pattern.class );
////       assertEquals( a.toString() , b.toString() );
////   }
//
//   // @org.testng.annotations.Test
//   public void testObjectId() {
//	   ObjectId oid = new ObjectId(new Date());
//
//	   String serialized = JSON.serialize(oid);
//	   assertEquals("{ \"$oid\" : \"" + oid + "\"}", serialized);
//	   
//	   ObjectId oid2 = (ObjectId)JSON.parse(serialized);
//	   assertEquals(oid, oid2);
//   }
//
//   // @org.testng.annotations.Test
//   public void testDate() {
//	   Date d = new Date();
//	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//	   format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
//	   String formattedDate = format.format(d);
//
//	   String serialized = JSON.serialize(d);
//	   assertEquals("{ \"$date\" : \"" + formattedDate + "\"}", serialized);
//
//	   Date d2 = (Date)JSON.parse(serialized);
//	   assertEquals(d.toString(), d2.toString());
//	   assertTrue(d.equals(d2));
//   }
//
//	// @org.testng.annotations.Test
//	public void testJSONEncoding() throws ParseException {
//		String json = "{ 'str' : 'asdfasd' , 'long' : 123123123123 , 'int' : 5 , 'float' : 0.4 , 'bool' : false , 'date' : { '$date' : '2011-05-18T18:56:00Z'} , 'pat' : { '$regex' : '.*' , '$options' : ''} , 'oid' : { '$oid' : '4d83ab3ea39562db9c1ae2ae'} , 'ref' : { '$ref' : 'test.test' , '$id' : { '$oid' : '4d83ab59a39562db9c1ae2af'}} , 'code' : { '$code' : 'asdfdsa'} , 'codews' : { '$code' : 'ggggg' , '$scope' : { }} , 'ts' : { '$ts' : 1300474885 , '$inc' : 10} , 'null' :  null, 'uuid' : { '$uuid' : '60f65152-6d4a-4f11-9c9b-590b575da7b5' }}";
//		BasicDBObject a = (BasicDBObject) JSON.parse(json);
//		assert (a.get("str").equals("asdfasd"));
//		assert (a.get("int").equals(5));
//		assert (a.get("long").equals(123123123123L));
//		assert (a.get("float").equals(0.4d));
//		assert (a.get("bool").equals(false));
//		SimpleDateFormat format =
//				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//		format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
//		assert (a.get("date").equals(format.parse("2011-05-18T18:56:00Z")));
//		Pattern pat = (Pattern) a.get("pat");
//		Pattern pat2 = Pattern.compile(".*", BSON.regexFlags(""));
//		assert (pat.pattern().equals(pat2.pattern()));
//		assert (pat.flags() == (pat2.flags()));
//		ObjectId oid = (ObjectId) a.get("oid");
//		assert (oid.equals(new ObjectId("4d83ab3ea39562db9c1ae2ae")));
//		DBRef ref = (DBRef) a.get("ref");
//		assert (ref.equals(new DBRef(null, "test.test", new ObjectId("4d83ab59a39562db9c1ae2af"))));
//		assert (a.get("code").equals(new Code("asdfdsa")));
//		assert (a.get("codews").equals(new CodeWScope("ggggg", new BasicBSONObject())));
//		assert (a.get("ts").equals(new BSONTimestamp(1300474885, 10)));
//		assert (a.get("uuid").equals(UUID.fromString("60f65152-6d4a-4f11-9c9b-590b575da7b5")));
//		String json2 = JSON.serialize(a);
//		BasicDBObject b = (BasicDBObject) JSON.parse(json2);
//		a.equals(b);
//		assert (a.equals(b));
//	}
//
//	public static void main( String[] args )
//	{
//		// UtilJson. hbkhkhbk
//	   
//	}
//
