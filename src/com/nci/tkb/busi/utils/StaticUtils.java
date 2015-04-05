package com.nci.tkb.busi.utils;

/**
 * Description 静态变量配置类
 * 
 * @author LYP
 * @version 1.0
 * @Date 2014-02-20
 */
public class StaticUtils
{
	/**
	 * 配置
	 */
	public static Configration CONSTANT_CONFIG = new Configration();

	/**
	 * memcache_ip
	 */
	public static String memcache_ip = CONSTANT_CONFIG.getValue("memcache_ip");

	/**
	 * memcache_port
	 */
	public static int memcache_port = Integer.valueOf(CONSTANT_CONFIG.getValue("memcache_port"));

	/**
	 * memcache_timeout
	 */
	public static int memcache_timeout = Integer.valueOf(CONSTANT_CONFIG.getValue("memcache_timeout"));

	/**
	 * memcache_minconn
	 */
	public static int memcache_minconn = Integer.valueOf(CONSTANT_CONFIG.getValue("memcache_minconn"));

	/**
	 * memcache_maxconn
	 */
	public static int memcache_maxconn = Integer.valueOf(CONSTANT_CONFIG.getValue("memcache_maxconn"));

	/**
	 * email_hostname
	 */
	public static String email_hostname = CONSTANT_CONFIG.getValue("email_hostname");

	/**
	 * email_password
	 */
	public static String email_password = CONSTANT_CONFIG.getValue("email_password");
	/**
	 * email_address
	 */
	public static String email_address = CONSTANT_CONFIG.getValue("email_address");
	/**
	 * email_mailname
	 */
	public static String email_mailname = CONSTANT_CONFIG.getValue("email_mailname");
	/**
	 * MSG_TYPE_REQ
	 */
	public static String MSG_TYPE_REQ = "REQ";

	/**
	 * MSG_TYPE_RESP
	 */
	public static String MSG_TYPE_RESP = "RESP";

	/**
	 * 错误参数
	 */
	public static int errorNum = 0;

	/**
	 * 充值金额限制
	 */
	public static final int CHARGE_LIMIT = 100000;

}
