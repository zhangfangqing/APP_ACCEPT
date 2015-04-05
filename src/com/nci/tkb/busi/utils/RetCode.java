package com.nci.tkb.busi.utils;

/**
 * 返回码
 * 
 * @author LYP
 * @version 1.0
 * @Date 2014-02-20
 */
public class RetCode
{
	/**
	 * 返回成功
	 */
	public static String RETCODE_SUCC = "0";

	/**
	 * 业务模块程序异常
	 */
	public static String BUSI_HANDLER_ERROR = "200";

	/**
	 * 数据库更新异常
	 */
	public static String RC_706 = "706";

	/**
	 * 系统配置表无数据
	 */
	public static String RC_703 = "703";

	/**
	 * 查询系统配置表异常
	 */
	public static String RC_702 = "702";

	/**
	 * 商户POS不存在
	 */
	public static String MERPOS_NOT_EXIST = "231";

	/**
	 * 充值金额超限
	 */
	public static String ERROR_CHARGE_LIMIT = "292";

	/**
	 * 注册用户名不是手机号
	 */
	public static String USERNAME_ERROR = "201";

	/**
	 * 注册用户名不存在,可注册
	 */
	public static String USERNAME_NOT_EXIST = "202";

	/**
	 * 用户名已存在
	 */
	public static String USERNAME_EXIST = "203";

	/**
	 * 商户不合法
	 */
	public static String MERCHANT_WRONG = "223";

	/**
	 * 操作员不合法
	 */
	public static String OPERATOR_WRONG = "224";
	
	/**
	 * 操作员不存在
	 */
	public static String OPERATOR_NOT_EXIST = "225";
	
	/**
	 * 商户不存在
	 */
	public static String MERCHANT_NOT_EXIST = "231";

	/**
	 * 商户不存在
	 */
	public static String CER_NOT_EXIST = "232";

	/**
	 * 数据库查询异常
	 */
	public static String DB_QUERY_ERROR = "250";

	/**
	 * 验证码错误
	 */
	public static String CAPTCHA_ERROR = "204";

	/**
	 * 验证码失效
	 */
	public static String CAPTCHA_OVERTIME = "205";

	/**
	 * 用户名错误
	 */
	public static String USERNAME_IS_ERROR = "206";

	/**
	 * 密码错误
	 */
	public static String PASSWORD_IS_ERROR = "207";

	/**
	 * POSID为空
	 */
	public static String POSID_IS_EMPTY = "208";
	
	/**
	 * 商户编码为空
	 */
	public static String MER_CODE_IS_EMPTY = "209";
	
	/**
	 * 默认返回码，提交数据有异常
	 */
	public static String DEFAULT_RETCODE = "210";
	
	/**
	 * 商户编码不存在
	 */
	public static String MER_CODE_NOT_EXSIT = "211";
	
	/**
	 * pos机不存在
	 */
	public static String POSID_NOT_EXSIT = "212";
	
	/**
	 * POS机已绑定
	 */
	public static String POS_IS_BIND = "213";
	
	/**
	 * 返回字段缺失 缺少必须字段
	 */
	public static String RETCODE_MISSING = "214";
	
	/**
	 * 请求码错误
	 */
	public static String CMD_CODE_ERROR = "215";
	
	/**
	 * 应用类型错误
	 */
	public static String APP_MODE_ERROR = "216";
	
	/**
	 * 子账号已存在
	 */
	public static String SUB_USERNAME_EXSIT= "217";
	
	/**
	 * 服务方代码不存在
	 */
	public static String SERVICE_TYPE_CODE_NOT_EXIT = "218";
}
