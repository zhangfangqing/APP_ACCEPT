package com.nci.tkb.busi.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.nci.tkb.busi.exception.BSVException;
import com.npass.pay.memshare.client.SendHandler;

/**
 * 静态方法类
 * @author LYP
 * @version 1.0
 * @Date 2014-02-20
 */
public class StaticMethod 
{
	private static Logger log = Logger.getLogger(StaticMethod.class.getName());
	
	/**
	 * 去掉左边空格Map
	 * @param args
	 */
	public static Map<String, String> getSpaceTrimMap(Map<String, String> map)
	{
		Map<String, String> retMap = new HashMap<String, String>();
		if (map != null && !map.isEmpty())
		{
			for (Map.Entry<String, String> entry:map.entrySet())
			{
				String key = entry.getKey();
				String mapvalue = trim(entry.getValue());
				String value = null;
				
				value = mapvalue;
				retMap.put(key, value);
			}
		}
		return retMap;
	}
	
	/**
	 * 左右空格都去掉
	 * @param str
	 * @return
	 */
	public static String trim(String str)
	{
		if (str == null || str.equals(""))
		{
			return str;
		}
		else 
		{
			return str.replaceAll("^[　 ]+|[　 ]+$", "");
		}
	}
	
	/**
	 * 设置日志
	 * @return
	 */
	public static String locationLog()
	{
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		sb.append("##########").append(stacks[1].getClassName()).append("************").append(stacks[1].getMethodName()).append(":");
		
		return sb.toString();
	}
	
	/**
	 * 打印方法日志头
	 * @return
	 */
	public static void printMethodStartLog()
	{
		//如果为info模式
		if (log.isDebugEnabled())
		{
			StringBuffer sb = new StringBuffer();
			StackTraceElement[] stacks = new Throwable().getStackTrace();
			sb.append("##########").append(stacks[1].getClassName()).append("************").append(stacks[1].getMethodName()).append("start:");
			log.debug(sb.toString());
		}
	}
	
	/**
	 * 传入参数日志打印
	 * @param args
	 */
	public static void printParamLog(Map<String, String> map)
	{
		//如果为debug模式
		if (log.isDebugEnabled())
		{
			if (map != null && !map.isEmpty())
			{
				for (Map.Entry<String, String> entry:map.entrySet())
				{
					log.debug("$$$$$$$$$$$$$$$$$$$$$***"+entry.getKey()+"="+entry.getValue());
				}
			}
		}
	}
	
	/**
	 * 传入参数日志打印
	 * @param args
	 */
	public static void printParamLogs(Map<String, byte[]> map)
	{
		//如果为debug模式
		if (log.isDebugEnabled())
		{
			if (map != null && !map.isEmpty())
			{
				for (Map.Entry<String, byte[]> entry:map.entrySet())
				{
					log.debug("$$$$$$$$$$$$$$$$$$$$$***"+entry.getKey()+"="+entry.getValue());
				}
			}
		}
	}
	
	/**
	 * 获取调用代码的类名，方法名，代码行数
	 * @return
	 */
	public static String getTraceInfo()
	{
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		sb.append("class: ").append(stacks[1].getClassName()).append("; method: ").append(stacks[1].getMethodName()).append("; number: ").append(stacks[1].getLineNumber());

		return sb.toString();
	}
	
	/**
	 * 获取调用代码的类名，方法名，代码行数
	 * @return
	 */
	public static String logmark(int mark)
	{
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		if (mark == 0 && log.isDebugEnabled())
		{
			log.debug(sb.append("class: ").append(stacks[1].getClassName()).append("; method: ").append(stacks[1].getMethodName()).append(": start>>").toString());
		}
		else 
		{
			log.debug(sb.append("class: ").append(stacks[1].getClassName()).append("; method: ").append(stacks[1].getMethodName()).append(": end<<").toString());
		}
		return sb.toString();
	}
	
	/**
	 * 更新redis内存
	 * @param map
	 * @return
	 * @throws BSVException
	 */
	public static boolean memProcess(Map<String, String> map) throws BSVException
	{
		boolean flag = false;
		Map<String, String> respMap = null;
		try 
		{
			respMap = SendHandler.sendHandler(map, 0);
		} 
		catch (Exception e) 
		{
			log.error("update redis error:" + e);
			throw new BSVException("update redis error:", e);
		}
		if (respMap != null && "0".equals(respMap.get("RET_CODE")))
		{
			flag = true;
		}
		return flag;
	}

}
