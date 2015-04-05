package com.nci.tkb.busi.service;

import com.nci.tkb.busi.redis.RedisUtils;












public class Test
{
	public static void main(String args[]) throws Exception
	{
		String key = "key";
		String value = "123456";
		
		RedisUtils.set(key, value.getBytes(), 100000);
		
		byte[] value2 = RedisUtils.get(key);
		
		String va = new String(value2);
		
		if(va.equals(value))
		{
			System.out.println("1");
		}
		
		
		
		
	}
	
	public void get()
	{/*

		JSONObject obj = new JSONObject();

		try
		{
			obj.put("key", "1232323");
			
			byte[] xx = BytesUtils.ObjectToByte(obj.toString());
			
			System.out.println(new String(xx));
			
			String obj2 = (String) BytesUtils.byteToObject(xx);
			
			JSONObject objs = new JSONObject(obj2);
			
			System.out.println(objs.toString());
			
			//JSONObject obj3 = (JSONObject) obj2;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	*/}
}
