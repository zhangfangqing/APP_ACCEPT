package com.nci.tkb.busi.ice;

import java.util.Map;

import com.nci.tkb.Caller.handler.AMI_CallHandler_asynCallAdd;

/**
 * 客户端回调类(异步调用)
 * @author yxb
 *
 */
public class CallBack2 extends AMI_CallHandler_asynCallAdd
{
	public void ice_exception(Ice.LocalException ex)
	{
		System.out.println(ex.toString());
	}
	
	@Override
	public void ice_response(Map<String, String> _ret)
	{
		System.out.println("getResult: " + _ret.get("key")+"_"+_ret.get("value")+"__num=");

	}
}