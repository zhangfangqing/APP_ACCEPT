package com.nci.tkb.busi.server;

import com.nci.tkb.busi.ice.IceMain;
import com.nci.tkb.busi.utils.StaticUtils;
import com.npass.pay.memshare.client.StaticConfig;

/**
 * Description 启动类
 * @author LYP
 * @version 1.0
 * @Date 2014-02-20
 */
public class BusiServer 
{
	
	public static void main(String[] args)
	{
		//启动内存模块
		String ip = StaticUtils.memcache_ip;
		int port = StaticUtils.memcache_port;
		int timeout = StaticUtils.memcache_timeout;
		int minConn = StaticUtils.memcache_minconn;
		int maxConn = StaticUtils.memcache_maxconn;
		//StaticConfig.loadConfig(ip, port, timeout, minConn, maxConn);
		
		//启动ICE
		IceMain.serverHandler();
	}
}
