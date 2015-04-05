package com.nci.tkb.busi.service;

import java.util.Map;

import com.nci.tkb.busi.exception.UseException;

public interface ReqCertHandlerService 
{
	/**
	 * 证书纳费
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> CerPayMent(Map<String, String> map);
	
	/**
	 * 证书请求
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> CerRequest(Map<String, String> map);
	
	/**
	 * 根据序列号返回证书信息
	 * @param cerSn
	 * @return
	 * @throws UseException
	 */
	public Map<String, String> getByCerSnInfo(String cerSn)  throws UseException;
	
	/**
	 * 根据证书库名查询证书库信息
	 * @param storeName
	 * @return
	 * @throws UseException
	 */
	public Map<String, String> getKeystoneByName(String storeName) throws UseException;
	
}
