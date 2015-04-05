package com.nci.tkb.busi.service;

import java.util.Map;

public interface RespHandlerService 
{
	/**
	 * 充值第一步
	 */
	public void RechargeFirst(Map<String, String> map);
	
	/**
	 * 充值第二步
	 */
	public void RechargeSecond(Map<String, String> map);
	
	/**
	 * 消费第一步
	 */
	public void ConsumeFirst(Map<String, String> map);
	
	/**
	 * 消费第二步
	 */
	public void ConsumeSecond(Map<String, String> map);
	
	/**
	 * 冲正
	 */
	public void Reverse(Map<String, String> map);
	
	/**
	 * 退款
	 */
	public void Refund(Map<String, String> map);

}
