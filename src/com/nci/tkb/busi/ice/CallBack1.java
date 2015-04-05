package com.nci.tkb.busi.ice;

import java.util.Map;

import com.nci.tkb.Caller.handler.AMI_CallHandler_asynCall;

/**
 * 客户端回调类(异步调用)
 * @author yxb
 *
 */
public class CallBack1 extends AMI_CallHandler_asynCall
{
	public void ice_exception(Ice.LocalException ex)
	{
		System.out.println(ex.toString());
	}

	@Override
	public void ice_response(Map<String, byte[]> _ret)
	{
		System.out.println("getResult: " + new String(_ret.get("key"))+"_"+new String(_ret.get("value"))+"__num="+ConfigUtils.RECV_NUM);

	}
}