package com.nci.tkb.busi.ice;

import java.util.concurrent.atomic.AtomicInteger;

import com.nci.tkb.Caller.handler.CallHandlerPrx;
import com.nci.tkb.Caller.handler.CallHandlerPrxHelper;

/**
 * 配置类
 * @author yxb
 *
 */
public class ConfigUtils
{
	public static AtomicInteger RECV_NUM = new AtomicInteger(1);
	//ICE客户端
	public static CallHandlerPrx PRX_ICE_CLIENT = null;
	
	//代理
	public static String ICE_PROXY = "ice_proxy";
	
	//适配器
	public static String ICE_ADAPTER = "ice_adpter";
	
	//适配器
	public static int ICE_SERVER_PORT = 10000;
	
	//
	//public static String SERVER_IP = "suibianjiao:tcp -h 192.168.1.202 -p 10001:tcp -h 192.168.1.202 -p 10000:tcp -h 192.168.1.202 -p 10002:tcp -h 127.0.0.1 -p 10000";
	public static String SERVER_IP = ICE_PROXY+":tcp -h 192.168.1.202 -p 10001:tcp -h 192.168.1.202 -p 10000:tcp -h 192.168.1.202 -p 10002:tcp -h 127.0.0.1 -p 10000";
 
}
