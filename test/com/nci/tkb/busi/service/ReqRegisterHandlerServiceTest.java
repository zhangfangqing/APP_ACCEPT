package com.nci.tkb.busi.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONException;
import org.junit.Test;

import com.nci.tkb.busi.serviceimpl.ReqRegisterHandlerServiceImpl;
import com.nci.tkb.busi.utils.CmdnoUtils;
import com.nci.tkb.busi.utils.ShareFieldUtils;

public class ReqRegisterHandlerServiceTest
{
	private ReqRegisterHandlerService reqService = new ReqRegisterHandlerServiceImpl();
	private static String userName = "15014050230";
	private static Map<String, String> map = new HashMap<String, String>();
	static
	{
		map.put(ShareFieldUtils.CMD_NO, CmdnoUtils.REG_INPUT_MOBILE_ACCEPT);
		map.put(ShareFieldUtils.UUID, UUID.randomUUID().toString());
		map.put(ShareFieldUtils.APP_MODE, "M_1");
		map.put(ShareFieldUtils.USERNAME, userName);
		map.put(ShareFieldUtils.PASSWORD, "345");
		map.put(ShareFieldUtils.APP_MODE, "U_1");
		map.put(ShareFieldUtils.DESKEY, "1");
		map.put(ShareFieldUtils.ENCRY_FLAG, "1");
		map.put(ShareFieldUtils.IS_ENCRYPT, "1");
	}

	@Test
	public void RegisterTest() throws JSONException
	{
		System.out.println(map);
		reqService.register(map);
	}

	@Test
	public void ValidateRegister() throws JSONException
	{

		/*
		 * Map<String, String> map = new HashMap<String, String>();
		 * map.put(ShareFieldUtils.CMD_NO, CmdnoUtils.REG_INPUT_MOBILE_ACCEPT);
		 * map.put(ShareFieldUtils.UUID, UUID.randomUUID().toString());
		 * map.put(ShareFieldUtils.USERNAME, userName);
		 * 
		 * map.put(ShareFieldUtils.APP_MODE, "M_1");
		 * map.put(ShareFieldUtils.PASSWORD, "345"); map.put("DESKEY", "1");
		 * 
		 * map.put("SIGNATURE", "2");
		 */
		map.put(ShareFieldUtils.CAPTCHA, "59486");
		reqService.validateRegister(map);
	}

	@Test
	public void completeUserInfo() throws JSONException
	{

		/*
		 * Map<String, String> map = new HashMap<String, String>();
		 * map.put(ShareFieldUtils.CMD_NO, CmdnoUtils.REG_INPUT_MOBILE_ACCEPT);
		 * map.put(ShareFieldUtils.UUID, UUID.randomUUID().toString());
		 * map.put(ShareFieldUtils.USERNAME, userName);
		 * 
		 * map.put(ShareFieldUtils.APP_MODE, "M_1");
		 * map.put(ShareFieldUtils.PASSWORD, "345"); map.put("MER_CODE",
		 * "12345"); map.put("DESKEY", "1"); map.put(ShareFieldUtils.CAPTCHA,
		 * "123456"); map.put("SIGNATURE", "2");
		 */

		reqService.completeUserInfo(map);

	}

	@Test
	public void completeMerchantInfo() throws JSONException
	{

		/*
		 * Map<String, String> map = new HashMap<String, String>();
		 * map.put(ShareFieldUtils.CMD_NO, CmdnoUtils.REG_INPUT_MOBILE_ACCEPT);
		 * map.put(ShareFieldUtils.UUID, UUID.randomUUID().toString());
		 * map.put(ShareFieldUtils.USERNAME, userName);
		 * 
		 * map.put(ShareFieldUtils.APP_MODE, "M_1");
		 * map.put(ShareFieldUtils.PASSWORD, "345"); map.put("MER_CODE",
		 * "12345"); map.put("DESKEY", "1"); map.put(ShareFieldUtils.CAPTCHA,
		 * "123456"); map.put("SIGNATURE", "2");
		 */

		reqService.completeMerchantInfo(map);

	}
}
