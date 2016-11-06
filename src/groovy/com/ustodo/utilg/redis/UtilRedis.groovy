
package com.ustodo.utilg.redis

//import com.ustodo.utilg.O;
//import redis.clients.jedis.Jedis;
//


class UtilRedis {
	
//	def public static synchronized set(String name, String value)
//	{
//		Jedis jedis = new Jedis("localhost");
//		jedis.set(name, value);
//		jedis.set
//		//String value = jedis.get(name);
//		O.o "jedis set name [" + name + "] value [" + value + "]"
//	}
//
//	def  synchronized get (String name)
//	{
//		try {
//		//O.o "attempting jedis [" + counter + "] get name [" + name + "]"
//		Jedis jedis = new Jedis("localhost");
//		String value = jedis.get(name);
//		O.o "jedis get name [" + name + "] value [" + value + "]"
//		return value;
//		} finally { 
//			//O.o "in finally"+counter
//		}
//	}	
//	
//	def synchronized addToSet(String setID, String value)
//	{
//		Jedis jedis = new Jedis("localhost");
//		long ms1 = java.lang.System.currentTimeMillis();
//		String ms = "" + ms1;
//		int size = 100;
//		// WRITE TO DB 
//		for (int i = 0; i < size; i++)
//		{
//			jedis.sadd(ms, ""+i );
//			if (i % 10000 == 0)
//				O.o ("to redis key[" + ms + "]");
//			//jedis.sadd(ms, ""+i + ":" + ms + ms + ms + ms+ ms + ms + ms + ms+ ms + ms + ms + ms+ ms + ms + ms + ms+ ms + ms + ms + ms+ ms + ms + ms + ms+ ms + ms + ms + ms);
//		}
//		long ms2 = java.lang.System.currentTimeMillis()
//		O.o ("test4 jedis insert [" + size + "] strings to a list: time(ms):" + (ms2-ms1) )
//		def a = jedis.smembers(ms);
//		def o2 = { if (Integer.parseInt(it) % 10000 == 0) println "HK:" + it}
//		a.any(o2);
//		O.o ("a.size():" + a.size())
//		long ms3 = java.lang.System.currentTimeMillis()
//		O.o ("test4 jedis retrieve [" + size + "] strings from a list: time(ms):" + (ms3-ms2) )
//	}
//	
//	def  synchronized getListFromSet (String setID)
//	{
//		try {
//		//O.o "attempting jedis [" + counter + "] get name [" + name + "]"
//		Jedis jedis = new Jedis("localhost");
//		String value = jedis.get(setID);
//		O.o "jedis get name [" + value + "] oc [" + O.oc(value)+ "] value [" + value + "]"
//		return value;
//		} finally {
//			//O.o "in finally"+counter
//		}
//	}
		
}
