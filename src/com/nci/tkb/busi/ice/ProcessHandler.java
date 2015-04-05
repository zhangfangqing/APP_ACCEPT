package com.nci.tkb.busi.ice;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import Ice.Current;

import com.nci.tkb.busi.exception.UseException;
import com.nci.tkb.busi.manager.OperateHandler;
import com.nci.tkb.callor.ProcessI;

/**
 * 服务器实现类
 * @author yxb
 *
 */
public class ProcessHandler extends ProcessI
{
	OperateHandler handler = new OperateHandler();
	
	//异步调用
	public Map<String, byte[]> callMapBt(Map<String, byte[]> map, Current current)
	{
		Map<String, byte[]> respMap = new HashMap<String, byte[]>(); 

		
/*		String key = new String(map.get("key"));
		System.out.println("asynCall_接收数据key: " + key);
		String value = "BBBBBBBBBBBBBBBBBBBBBBBB";
		
		respMap.put("key", map.get("key"));
		respMap.put("value", value.getBytes());*/
		try 
		{
			respMap = handler.process(map);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return respMap;
	}
	
	//异步调用
	public Map<String, String> CallMapstr(Map<String, String> map, Current current)
	{
		Map<String, String> respMap = new HashMap<String, String>(); 

		
		String key = new String(map.get("key"));
		System.out.println("asynCallAdd_接收数据key: " + key);
		String value = "BBBBBBBBBBBBBBBBBBBBBBBB";
		
		respMap.put("key", map.get("key"));
		respMap.put("value", value);
		
		return respMap;
	}
}