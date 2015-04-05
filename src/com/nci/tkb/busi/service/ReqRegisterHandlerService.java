package com.nci.tkb.busi.service;

import java.util.Map;

/**
 * 注册类信息
 * @author Administrator
 *
 */
public interface ReqRegisterHandlerService
{
	/**
	 * 注册
	 */
	public Map<String, byte[]> register(Map<String, String> map);

	/**
	 * 验证
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> validateRegister(Map<String, String> map);
	
	/**
	 * 用户信息完善
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> completeUserInfo(Map<String, String> map);
	
	/**
	 * 商户信息完善
	 * @param map
	 * @return
	 */
	public Map<String,byte[]> completeMerchantInfo(Map<String, String> map);
}
