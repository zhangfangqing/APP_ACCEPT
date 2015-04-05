package com.nci.tkb.busi.ice;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.nci.tkb.Caller.handler.CallHandlerPrx;
import com.nci.tkb.callor.CallerAdpater;
import com.nci.tkb.callor.ProcessI;


/**
 * 入口类,启动应用程序
 * @author yxb
 * @version 1.0
 * @Date 2013-03-19
 *
 */
public class IceMain 
{
	//日志记录
	private static Logger log = Logger.getLogger(IceMain.class.getName());
	
	private static int status = 0;
	
	private static CallHandlerPrx callHandlerPrx = null;

	/**
	 * 入口函数
	 * @author lyp
	 * @version 1.0
	 * @Date 2013-03-19
	 */
	/*public static void main(String[] args) 
	{
		IceMain iceMain = new IceMain();
		//iceMain.serverHandler();
		iceMain.clientHandler();
	}*/
	
	//客户端
	public void clientHandler()
	{
		//Ice.Communicator ic = null;
		//ConfigUtils.getLogicPrxClient(args);
		String value = "AAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		String url = "ice_proxy:tcp -h 192.168.1.114 -p 10000:tcp -h 192.168.1.202 -p 10000:tcp -h 192.168.1.202 -p 10002:tcp -h 127.0.0.1 -p 10000";
		try
		{
			//获取代理(客户端)
			callHandlerPrx = CallerAdpater.getPrxClient(url);
			
			if (null == callHandlerPrx)
			{
				log.error("Invalid proxy");
			}
			
			
			//接口共两种,第1种值为byte数组
			Map<String, byte[]> map = new HashMap<String, byte[]>();
			map.put("value", value.getBytes());
			
			
			//long inittime = System.currentTimeMillis();
			for(int i=1; i<=100; i++)
			{
				map.put("key", ("A"+i).getBytes());
				log.debug("send: A_"+i);

				callHandlerPrx.asynCall_async(new CallBack1(), map);
				
				if(i%10==0)
				{
					Thread.sleep(1);
				}
			}
			
			
			
/*			//接口共两种,第2种值为字符串
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("value", value);
			for(int i=1; i<=100; i++)
			{
				map2.put("key", "A"+i);
				System.out.println("send: A_"+i);

				callHandlerPrx.asynCallAdd_async(new CallBack2(), map2);
				
				if(i%10==0)
				{
					Thread.sleep(1);
				}
			}*/
			
/*			long overtime = System.currentTimeMillis();
			long usetime = overtime - inittime;
			System.out.println("使用时间为："+usetime);*/
		}
		catch (Ice.LocalException e)
		{
			e.printStackTrace();
			status = 1;
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			status = 1;
		}
/*		finally
		{
			if (ic != null)
				ic.destroy();
		}*/
		System.exit(status);
	}
	
	//服务器
	public static void serverHandler()
	{
		//初始化
		CallerAdpater caller  = new CallerAdpater(ConfigUtils.ICE_ADAPTER, ConfigUtils.ICE_PROXY, ConfigUtils.ICE_SERVER_PORT);
		//处理类
		ProcessI handlerI = new ProcessHandler();
		try 
		{
			//适配
			caller.setHandler(handlerI);
		}
		catch (Exception e)
		{
			log.error("启动服务器失败： "+e);
		}
	}

}
