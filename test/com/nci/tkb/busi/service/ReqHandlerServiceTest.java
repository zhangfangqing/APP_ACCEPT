package com.nci.tkb.busi.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.nci.tkb.busi.serviceimpl.ReqHandlerServiceImpl;
import com.nci.tkb.busi.utils.ShareFieldUtils;

public class ReqHandlerServiceTest
{
	private ReqHandlerService handlerService = new ReqHandlerServiceImpl();
	private static Map<String, String> map = new HashMap<String, String>();
	static
	{
		map.put(ShareFieldUtils.USERNAME, "15014050230");
		map.put(ShareFieldUtils.PASSWORD, "345");
		map.put(ShareFieldUtils.POSID, "posid");
		map.put(ShareFieldUtils.MAC_OR_IP, "mac_sfadffadsdfaf");
		map.put(ShareFieldUtils.LOGON_TYPE, "1");
		map.put(ShareFieldUtils.DESKEY, "123456789");
		map.put(ShareFieldUtils.ENCRY_FLAG, "1");
		map.put(ShareFieldUtils.UUID, UUID.randomUUID().toString());
		map.put(ShareFieldUtils.IS_ENCRYPT, "0");
		map.put(ShareFieldUtils.MAC_NO, "mac_num_123");
	}

	/**
	 * 商户登录
	 */
	@Test
	public void MerLoginTest()
	{
		handlerService.MerLogin(map);
	}

	/**
	 * 商户交易初始化
	 */
	@Test
	public void MerTransInitTest()
	{
		handlerService.MerTransInit(map);
	}

	@Test
	public void validateMerTransInitTest()
	{
		map.put(ShareFieldUtils.CAPTCHA, "269590");
		handlerService.validateMerTransInit(map);
	}

	@Test
	public void EditPwdTest()
	{
		map.put(ShareFieldUtils.PASSWORD_NEW, "345");
		handlerService.EditPwd(map);
	}

	@Test
	public void merPosManager()
	{
		map.put(ShareFieldUtils.MER_CODE, "12345");
		map.put(ShareFieldUtils.DEV_VER, "nokia");
		handlerService.merPosManager(map);
	}

	@Test
	public void merSubManager()
	{
		map.put(ShareFieldUtils.USERNAME, "1234567890123-123");
		map.put(ShareFieldUtils.MER_CODE, "12345");
		handlerService.merSubManager(map);
	}

	@Test
	public void SubAccountLogin()
	{
		map.put(ShareFieldUtils.USERNAME, "1234567890123-123");
		handlerService.SubAccountLogin(map);
	}

	@Test
	public void MemoryBind()
	{
		map.put(ShareFieldUtils.FLAG, "1");
		map.put(ShareFieldUtils.TERM_ID, "15014050224");
		map.put(ShareFieldUtils.BIND_FLAG, "0");
		map.put(ShareFieldUtils.SERVICE_CODE, "1000");
		handlerService.MemoryBind(map);
	}

	@Test
	public void MemoryDownload()
	{
		handlerService.MemoryDown(map);
	}
}
