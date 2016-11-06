package com.ustodo.utilg

import java.util.HashMap;
//import redis.clients.jedis.Jedis;
import com.ustodo.utilg.O;



class UtilJedis {
	
//	// ============================================================
//	// ========== http://redis.io/commands#list
//	// ============================================================
//	// list LSET 
//	public static void lpush(String desc, String key, value)
//	{
//		
//		//O.o ("--> UTILJEDIS [" + desc + "] lpush " + O.fmt("key", key) + O.fmt("value", value));
//		conn().lpush (key, value);
//		//O.o ("Done lpush " + O.fmt("key", key) + O.fmt("value", value));
//	}
//	
//	// list LRANGE 
//	public static String lrange (String desc, String key, int start, int stop)
//	{
//		//O.o ("--> UTILJEDIS [" + desc + "] lrange " + O.fmt("key", key) + O.fmt("start", start)+ O.fmt("stop", stop));
//		conn().lrange (key, start, stop);
//	}
//
//	// list LTRIM 
//	public static String ltrim (String desc, String key, int start, int stop)
//	{
//		//O.o ("--> UTILJEDIS [" + desc + "] sltrim " + O.fmt("key", key) + O.fmt("start", start)+ O.fmt("stop", stop));
//		conn().ltrim (key, start, stop);
//	}
//
//	// list LLEN 
//	public static int llen (String desc, String key)
//	{
//		int len = conn().llen (key);
//		//O.o ("--> UTILJEDIS llen, " + O.fmt("desc", desc) + O.fmt("key", key) + O.fmt("len", len));
//		return len;
//	}
//
//	public static void ocl (String desc, String key)
//	{
//		O.o ("--> UTILJEDIS data struct out LIST " + 
//			O.fmt ("desc", desc) + 
//			O.fmt ("key", key) + 
//			O.fmt ("llen", ""+ conn().llen (key)) +
//			O.fmt ("lrange", conn().lrange (key, 0, -1))	); 
//	} 
//	
//	
//	
//	
//	
//	
//	// ============================================================
//	// ========== http://redis.io/commands#sorted_set
//	// ============================================================
//
//	//  sorted set ZADD 	
//	public static void zadd(String desc, String key, String score, String member)
//	{
//		//O.o ("--> UTILJEDIS [" + desc + "] zadd " + O.fmt("key", key) + O.fmt("score", score) + O.fmt("member", member));
//		conn().zadd (key, score, member);
//	}
//	
//	// sorted set ZRANGE get 
//	public static String zrange (String desc, String key, int start, int stop)
//	{
//		//O.o ("--> UTILJEDIS [" + desc + "] zrange " + O.fmt("key", key) + O.fmt("start", start)+ O.fmt("stop", stop));
//		return conn().zrange (key, start, stop).toString();
//	}
//
//	// sorted set ZRANGE getall 
//	public static String zrangeGetAll (String desc, String key)
//	{
//		//O.o ("--> UTILJEDIS [" + desc + "] zrange " + O.fmt("key", key));
//		return conn().zrange (key, 0, -1).toString();
//	}
//
//	// sorted set ZINCRBY get
//	public static String zincrby (String desc, String key, Integer increment, String member)
//	{
//		//O.o ("--> UTILJEDIS [" + desc + "] utilredis sorted set zincrby " + O.fmt("key", key) + O.fmt("increment", increment)+ O.fmt("member", member));
//		return conn().zincrby (key, increment, member).toString();
//	}
//
//	public static void ocss (String desc, String key)
//	{
//		O.o ("--> UTILJEDIS data struct out SORTED SET " +
//			O.fmt ("desc", desc) +
//			O.fmt ("type (key)", conn().type (key)) +
//			O.fmt ("key", key) +
//			O.fmt ("zcard (key)", ""+conn().zcard (key)) +
//			O.fmt ("allLrange", conn().zrange (key, 0, -1).toString()));
//	}
//
//	
//	// ============================================================
//	// ========== Jedis general 
//	// ============================================================
//
//	
//
//	// ============================================================
//	// ========== Jedis connection pool 
//	// ============================================================
//
//	private static boolean firstTime = true;
//	private static HashMap hmJedisPerThread = new HashMap(0);
//	public static Jedis conn()
//	{
//		
//		def mystuff_settings_redishost = 
//		  org.codehaus.groovy.grails.commons.ConfigurationHolder.getFlatConfig().get('mystuff.settings.redishost');
//		def mystuff_settings_redisport = 
//		  org.codehaus.groovy.grails.commons.ConfigurationHolder.getFlatConfig().get('mystuff.settings.redisport');
//		def mystuff_settings_redispass = 
//		  org.codehaus.groovy.grails.commons.ConfigurationHolder.getFlatConfig().get('mystuff.settings.redispass');
//		if (firstTime)
//		{
//			O.o (O.fmt("new jedis mystuff_settings_redishost", mystuff_settings_redishost));
//			firstTime = false;
//		}
//		Jedis jedis = hmJedisPerThread.get(Thread.currentThread().getId());
//		if (jedis != null)
//		{
//			return jedis;
//		}
//		//jedis = new Jedis(mystuff_settings_redishost, 6379, 10000000);
//		jedis = new Jedis(mystuff_settings_redishost, Integer.parseInt(mystuff_settings_redisport), 10000000);
//		jedis.auth(mystuff_settings_redispass);
//		hmJedisPerThread.put Thread.currentThread().getId(), jedis
//		return jedis;
//	}
//
//	// remove ref to the object	
//	public static void connOut()
//	{
//		hmJedisPerThread.remove(Thread.currentThread().getId());
//	}
//	//O.fmt ("DB_NAME", DB_NAME) +
//	//O.fmt ("COLL_NAME", COLL_NAME) );
//	//}
//
//	//	jedis.rpush(rediskeylist, filelineraw);
//	//	jedis.zadd(rediskeyset, (double) java.lang.System.currentTimeMillis(), filelineraw);
//	// EACH FILE LINE ===========

	
	// WRITE TO DB TOO - JEDIS =========================
	//	if (false)
	//	{
	//		Jedis jedis = UtilJeds.conn();
	//		long ms1 = java.lang.System.currentTimeMillis();
	//		String ms = "" + ms1;
	//		String redistimestamp = UtilDate.getDateForFile()
	//		redistimestamp = "123123"
	//		jedis.sadd(redistimestamp, writeStr );
	//		O.o "wrote under key [" + redistimestamp + "] to jedis [" + writeStr + "]"
	//	}
	// END WRITE TO DB TOO - JEDIS =========================

	
	
}
