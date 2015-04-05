package com.nci.tkb.busi.service;

import java.util.Map;

public interface ReqHandlerService
{
	/**
	 * 充值第一步
	 */
	public Map<String, byte[]> RechargeFirst(Map<String, String> map);

	/**
	 * 充值第二步
	 */
	public Map<String, byte[]> RechargeSecond(Map<String, String> map);

	/**
	 * 消费第一步
	 */
	public Map<String, byte[]> ConsumeFirst(Map<String, String> map);

	/**
	 * 消费第二步
	 */
	public Map<String, byte[]> ConsumeSecond(Map<String, String> map);

	/**
	 * 冲正
	 */
	public Map<String, byte[]> Reverse(Map<String, String> map);

	/**
	 * 退款
	 */
	public Map<String, byte[]> Refund(Map<String, String> map);

	/**
	 * 用户登录
	 */
	public Map<String, byte[]> UserLogin(Map<String, String> map);

	/**
	 * 商户登录
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> MerLogin(Map<String, String> map);

	/**
	 * 商户交易初始化
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> MerTransInit(Map<String, String> map);

	/**
	 * 商户交易初始化验证
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> validateMerTransInit(Map<String, String> map);

	/**
	 * 子帐号登录
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> SubAccountLogin(Map<String, String> map);

	/**
	 * 商户管理
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> MerManager(Map<String, byte[]> map);

	/**
	 * 商户pos机管理
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> merPosManager(Map<String, String> map);

	/**
	 * 商户子账号管理
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> merSubManager(Map<String, String> map);

	/**
	 * 修改密码
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> EditPwd(Map<String, String> map);

	/**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> QueryData(Map<String, byte[]> map);

	/**
	 * 参数下载
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> DownloadParam(Map<String, String> map);

	/**
	 * 子账号选择pos机绑定
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> BindSubUser(Map<String, String> map);

	/**
	 * 子账号选择pos机绑定验证
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> validateBindSubUser(Map<String, String> map);

	/**
	 * 用户记忆功能绑定
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> MemoryBind(Map<String, String> map);

	/**
	 * 用户记忆功能下载
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> MemoryDown(Map<String, String> map);

	/**
	 * 找回密码
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> FindPassword(Map<String, String> map);

	/**
	 * 验证密码
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> validateFindPassword(Map<String, String> map);
}
