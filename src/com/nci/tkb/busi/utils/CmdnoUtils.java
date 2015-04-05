package com.nci.tkb.busi.utils;

/**
 * 请求码和返回码类
 * 
 * @author LYP
 * @version 1.0
 * @Date 2014-02-20
 */
public class CmdnoUtils
{
	/**
	 * 充值第一步 ACCEPT 请求 RETURN 返回
	 */
	public static final String RECHARGE_FIRST_ACCEPT = "1110";
	public static final String RECHARGE_FIRST_RETURN = "1111";

	/**
	 * 充值第二步 ACCEPT 请求 RETURN 返回
	 */
	public static final String RECHARGE_SECOND_ACCEPT = "1120";
	public static final String RECHARGE_SECOND_RETURN = "1121";

	/**
	 * 消费第一步 ACCEPT 请求 RETURN 返回
	 */
	public static final String CONSUME_FIRST_ACCEPT = "1210";
	public static final String CONSUME_FIRST_RETURN = "1211";

	/**
	 * 消费第二步 ACCEPT 请求 RETURN 返回
	 */
	public static final String CONSUME_SECOND_ACCEPT = "1220";
	public static final String CONSUME_SECOND_RETURN = "1221";

	/**
	 * 冲正
	 */
	public static final String REVERSE_ACCEPT = "1310";
	public static final String REVERSE_RETURN = "1311";

	/**
	 * 退款
	 */
	public static final String REFUND_ACCEPT = "1410";
	public static final String REFUND_RETURN = "1411";

	/**
	 * 查询
	 */
	public static final String QUERY_ACCEPT = "1000";
	public static final String QUERY_RETURN = "1001";

	/**
	 * 注册--录入手机号
	 */
	public static final String REG_INPUT_MOBILE_ACCEPT = "2000";
	public static final String REG_INPUT_MOBILE_RETURN = "2001";

	/**
	 * 注册--验证码
	 */
	public static final String REG_CHECK_CODE_ACCEPT = "2010";
	public static final String REG_CHECK_CODE_RETURN = "2011";

	/**
	 * 注册--用户信息完善
	 */
	public static final String REG_ADD_USERINFO_ACCEPT = "2020";
	public static final String REG_ADD_USERINFO_RETURN = "2021";

	/**
	 * 注册--商户信息完善
	 */
	public static final String REG_ADD_MERINFO_ACCEPT = "2030";
	public static final String REG_ADD_MERINFO_RETURN = "2031";

	/**
	 * 用户登录
	 */
	public static final String USER_LOGON_ACCEPT = "2100";
	public static final String USER_LOGON_RETURN = "2101";

	/**
	 * 商户登录
	 */
	public static final String MER_LOGON_ACCEPT = "2200";
	public static final String MER_LOGON_RETURN = "2201";

	/**
	 * 商户交易初始化--初始化
	 */
	public static final String MER_TRAN_INIT_ACCEPT = "2210";
	public static final String MER_TRAN_INIT_RETURN = "2211";

	/**
	 * 商户交易初始化--验证码确认
	 */
	public static final String MER_CHECK_CODE_ACCEPT = "2212";
	public static final String MER_CHECK_CODE_RETURN = "2212";

	/**
	 * 子账号登录（已初始化）
	 */
	public static final String ACCOUNT_LOGON_ACCEPT = "2300";
	public static final String ACCOUNT_LOGON_RETURN = "2301";

	/**
	 * 子账号登录初始化（未初始化）
	 */
	public static final String ACCOUNT_LOGON_INIT_ACCEPT = "2310";
	public static final String ACCOUNT_LOGON_INIT_RETURN = "2311";

	/**
	 * 子账号登录验证码确认（未初始化）
	 */
	public static final String ACCOUNT_CHECK_CODE_ACCEPT = "2320";
	public static final String ACCOUNT_CHECK_CODE_RETURN = "2321";

	/**
	 * 商户管理--操作员信息完善
	 */
	public static final String MER_ADD_OPTINFO_ACCEPT = "2401";
	public static final String MER_ADD_OPTINFO_RETURN = "2401";

	/**
	 * 商户管理--POS机信息完善
	 */
	public static final String MER_ADD_POSINFO_ACCEPT = "2402";
	public static final String MER_ADD_POSINFO_RETURN = "2403";

	/**
	 * 修改密码
	 */
	public static final String EDIT_PASSWORD_ACCEPT = "2500";
	public static final String EDIT_PASSWORD_RETURN = "2501";

	/**
	 * 证书纳费
	 */
	public static final String CERT_PAYMENT_ACCEPT = "2600";
	public static final String CERT_PAYMENT_RETURN = "2601";

	/**
	 * 找回密码
	 */
	public static final String FIND_PASSWORD_ACCEPT = "2700";
	public static final String FIND_PASSWORD_RETURN = "2701";

	/**
	 * 找回密码修改密码
	 */
	public static final String CHECK_FIND_PASSWORD_ACCEPT = "2702";
	public static final String CHECK_FIND_PASSWORD_RETURN = "2703";
	/**
	 * 参数下载
	 */
	public static final String PARAM_DOWNLOAD_ACCEPT = "3000";
	public static final String PARAM_DOWNLOAD_RETURN = "3001";

	/**
	 * 服务类别子节点请求
	 */
	public static final String TYPE_ADD_NODE_ACCEPT = "3100";
	public static final String TYPE_ADD_NODE_RETURN = "3101";

	/**
	 * 区域子节点请求
	 */
	public static final String ZONE_ADD_NODE_ACCEPT = "3200";
	public static final String ZONE_ADD_NODE_RETURN = "3201";

	/**
	 * 证书请求
	 */
	public static final String CERT_ACCEPT = "3300";
	public static final String CERT_RETURN = "3301";

}
