package com.nci.tkb.busi.redis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.nci.tkb.busi.utils.Configration;
import com.nci.tkb.busi.utils.StaticUtils;

/**
 * 
 * @author ajun
 * 
 */
public class RedisUtils 
{

	private static JedisPool pool;
	private static int DBIndex;
	private static String host;
	private static int port = 6379;
	private static int timeout = 60 * 1000;

	static
	{
		//获取配置文件
		Configration keyconfig = StaticUtils.CONSTANT_CONFIG;
		//redis连接数
		DBIndex = Integer.parseInt(keyconfig.getValue("redis_dbindex"));
		//redis主机
		host = keyconfig.getValue("redis_host");
		
//		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxActive(100);
//		config.setMaxIdle(20);
//		config.setMaxWait((long) 1000);
//		config.setTestOnBorrow(false);
//		// 线程数量限制，IP地址，端口，超时时间
//		pool = new JedisPool(config, host, port, timeout);

	}

	/**
	 * 清空Redis全部数据
	 */
	public static void clear()
	{
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.connect();
			jedis.select(DBIndex);
			jedis.flushAll();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}

	/**
	 * 在list中添加key
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public static void addItemToList(String key, byte[] value)
	{
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.connect();
			jedis.select(DBIndex);
			jedis.lpush(key.getBytes(), value);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}

	/**
	 * 取出并删除list中key的值
	 * 
	 * @param key
	 * @return
	 */
	public static byte[] delItemFromList(String key)
	{
		Jedis jedis = null;
		byte[] value = null;
		try
		{
			jedis = pool.getResource();
			jedis.connect();
			jedis.select(DBIndex);
			value = jedis.lpop(key.getBytes());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
		return value;
	}

	/**
	 * 使用redis内置的map 添加key 如果已经存在key，则新值覆盖旧值，修改时可以使用这个
	 * seconds（秒）表过期超时删除数据，0表示永远不删除
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public static void set(String key, byte[] value, int seconds)
	{
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			jedis.set(key.getBytes(), value);
			if (seconds > 0)
			{
				jedis.expire(key.getBytes(), seconds);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}

	/**
	 * 使用redis内置的map 添加key 如果已经存在key，则不添加 seconds（秒）表过期超时删除数据，0表示永远不删除
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public static void setnx(String key, byte[] value, int seconds)
	{
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			jedis.setnx(key.getBytes(), value);
			if (seconds > 0)
			{
				jedis.expire(key.getBytes(), seconds);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}

	/**
	 * 使用redis内置的map 不熟悉redis不建议用这个 将值value关联到key，并将key的生存时间设为seconds(以秒为单位)。
	 * 如果key 已经存在，SETEX命令将覆写旧值。 seconds（秒）表过期超时删除数据，0表示永远不删除
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public static void setex(String key, byte[] value, int seconds)
	{
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			jedis.setex(key.getBytes(), seconds, value);
			if (seconds > 0)
			{
				jedis.expire(key.getBytes(), seconds);
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}

	/**
	 * 使用redis内置的map 获取key
	 * 
	 * @param key
	 * @return
	 */
	public static byte[] get(String key)
	{
		Jedis jedis = null;
		byte[] s = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			s = jedis.get(key.getBytes());
			return s;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return s;
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);

		}

	}

	/**
	 * 使用redis内置的map 删除key
	 * 
	 * @param key
	 */
	public static void del(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			jedis.del(key.getBytes());

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}

	}

	/**
	 * Redis自增序列
	 * 
	 * @param key
	 * @return
	 */
	public static long getIncrement()
	{
		String key = "REDIS";
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			return jedis.incr(key);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0L;
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}

	/**
	 * 在自己定义的map中存储<key，value>
	 * 
	 * @param key
	 * @param map
	 */
	public static void setBHashMap(String key, Map<String, byte[]> map)
	{
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			if (map != null && !map.isEmpty())
			{
				for (Map.Entry<String, byte[]> entry : map.entrySet())
				{
					jedis.hset(key.getBytes(), entry.getKey().getBytes(), entry.getValue());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}
	
	
	/**
	 * 在自己定义的map中存储<key，value>
	 * seconds为超时时间
	 * 
	 * @param key
	 * @param map
	 */
	public static void setBMapTimeOut(String key, Map<String, byte[]> map, int seconds)
	{
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			if (map != null && !map.isEmpty())
			{
				for (Map.Entry<String, byte[]> entry : map.entrySet())
				{
					jedis.hset(key.getBytes(), entry.getKey().getBytes(), entry.getValue());
				}
				if(seconds > 0)
				{
					jedis.expire(key, seconds);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}

	/**
	 * 在自己定义的map中通过key获取值
	 * 
	 * @param key
	 * @param map
	 */
	public static byte[] getBHashMap(String key, String field)
	{
		byte[] value = null;
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			value = jedis.hget(key.getBytes(), field.getBytes());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
		return value;
	}

	/**
	 * 自己定义的map中获取全部的key值
	 * 
	 * @param key
	 * @param map
	 */
	public static Map<byte[], byte[]> getAllBHashMap(String key)
	{
		Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			map = jedis.hgetAll(key.getBytes());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
		return map;

	}

	/**
	 * 设置map 存储用户信息
	 * 
	 * @param key
	 * @param map
	 */
	public static void setSHashMap(String key, HashMap<String, String> map)
	{
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			if (map != null && !map.isEmpty())
			{
				for (Map.Entry<String, String> entry : map.entrySet())
				{
					jedis.hset(key, entry.getKey(), entry.getValue());
				}

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}

	}

	/**
	 * 获取hashmap
	 * @param key
	 * @return
	 */
	public static Map<String, String> getSHashMap(String key)
	{
		Map<String, String> map = null;
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			map = jedis.hgetAll(key);
			if(map != null && map.size()==0)
			{
				map = null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
		return map;
	}
	
	/**
	 * 删除HashMap中域的值
	 * @param key
	 * @return
	 */
	public static void hdel(String key, String field)
	{
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			jedis.hdel(key.getBytes(), field.getBytes());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}

	/**
	 * 添加set
	 * 
	 * @param key
	 * @param set
	 */
	public static void addSet(String key, Set<String> set)
	{
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			if (set != null && !set.isEmpty())
			{
				for (String value : set)
				{
					/*
					 * for ( Iterator<String> memberItr =
					 * jedis.smembers(str).iterator();//返回key对应set的所有元素，结果是无序的
					 * memberItr.hasNext();){ final String member =
					 * memberItr.next(); if (!jedis.sismember(str, member)){
					 * jedis.srem(str, member); } }
					 */
					jedis.sadd(key, value);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}

	public static Set<String> getSet(String key)
	{
		Set<String> sets = new HashSet<String>();
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			jedis.select(DBIndex);
			sets = jedis.smembers(key);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (jedis != null)
				pool.returnResource(jedis);
		}

		return sets;
	}
}
