/**
 * 
 */
package com.nci.tkb.busi.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.nci.tkb.busi.redis.RedisUtils;
import com.npass.pay.memshare.client.BytesUtils;
import com.npass.pay.memshare.client.RedisUtil;

/**
 * @author YiBo
 * 
 */
public class test {

	public static void main(String[] args) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("key", "1232323");
		Iterator<String> keys = json.keys();
		JSONObject jo = null;
		Object o;
		String key;
		while (keys.hasNext()) {
			key = keys.next();
			o = json.get(key);
			System.out.println(o);
		}
		/*
		 * Map<String,byte[]> maps = new HashMap<String, byte[]>();
		 * maps.put("1", "test1".getBytes()); maps.put("2", "test2".getBytes());
		 * maps.put("3", "test3".getBytes());
		 * 
		 * for (String key : maps.keySet()) { System.out.println("key= "+ key +
		 * " and value= " + new String(maps.get(key))); }
		 * 
		 * Map<String, String> map = new HashMap<String, String>(); map.put("1",
		 * "value1"); map.put("2", "value2"); map.put("3", "value3");
		 * 
		 * //第一种：普遍使用，二次取值 System.out.println("通过Map.keySet遍历key和value："); for
		 * (String key : map.keySet()) { System.out.println("key= "+ key +
		 * " and value= " + map.get(key)); }
		 * 
		 * //第二种 System.out.println("通过Map.entrySet使用iterator遍历key和value：");
		 * Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		 * while (it.hasNext()) { Map.Entry<String, String> entry = it.next();
		 * System.out.println("key= " + entry.getKey() + " and value= " +
		 * entry.getValue()); }
		 * 
		 * //第三种：推荐，尤其是容量大时 System.out.println("通过Map.entrySet遍历key和value"); for
		 * (Map.Entry<String, String> entry : map.entrySet()) {
		 * System.out.println("key= " + entry.getKey() + " and value= " +
		 * entry.getValue()); }
		 * 
		 * //第四种 System.out.println("通过Map.values()遍历所有的value，但不能遍历key"); for
		 * (String v : map.values()) { System.out.println("value= " + v); }
		 */}

	public void get() {

		JSONObject obj = new JSONObject();

		try {
			obj.put("key", "1232323");
			byte[] xx = BytesUtils.ObjectToByte(obj.toString());
			System.out.println(new String(xx));
			String obj2 = (String) BytesUtils.byteToObject(xx);
			JSONObject objs = new JSONObject(obj2);
			System.out.println(objs.get("key"));

			Map<String, String> map = new HashMap<String, String>();
			map.put("obj", objs.toString());
			System.out.println(map.get("obj"));

			// JSONObject obj3 = (JSONObject) obj2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
