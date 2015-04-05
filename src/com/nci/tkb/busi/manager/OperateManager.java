package com.nci.tkb.busi.manager;

import java.util.Map;

import org.apache.log4j.Logger;

import com.nci.tkb.busi.utils.BusiCodeUtils;
import com.nci.tkb.busi.utils.ShareFieldUtils;
import com.nci.tkb.busi.utils.StaticMethod;
import com.nci.tkb.busi.utils.StaticUtils;
import com.nci.tkb.busi.service.ReqHandlerService;
import com.nci.tkb.busi.service.RespHandlerService;
import com.nci.tkb.busi.serviceimpl.ReqHandlerServiceImpl;
import com.nci.tkb.busi.serviceimpl.RespHandlerServiceImpl;
import com.nci.tkb.busi.utils.CmdnoUtils;

/**
 * Description 业务分发类
 * @author ZYB
 * @version 1.0
 * @Date 2014-03-05
 */
public class OperateManager 
{
	private Logger log = Logger.getLogger(OperateManager.class.getName());
	//请求模块
	private ReqHandlerService reqService = new ReqHandlerServiceImpl();
	//相应模块
	private RespHandlerService respService = new RespHandlerServiceImpl();

	/**
	 * function 根据业务代码和请求码判断业务类型
	 * @author LYP
	 * @Date 2014-02-20
	 * @input @param: Map<String, String> map
     * @return : void
	 */
	public void process(Map<String, String> reqMap) 
	{
		log.info("******************OperateManager.process.start***************");
		//去掉左右边空格和左边0
		Map<String, String> map = StaticMethod.getSpaceTrimMap(reqMap);
		//业务代码
		String busiCode = map.get(ShareFieldUtils.BUSI_CODE);
		//请求码
		String cmdno = map.get(ShareFieldUtils.CMD_NO);
		//请求返回状态
		String msgType = map.get(ShareFieldUtils.MSG_TYPE);
		log.info("process param after: " + map.toString());
		//业务模块（消息类型）
		if (StaticUtils.MSG_TYPE_REQ.equals(msgType))
		{
			log.debug(StaticMethod.locationLog() + "MSG_TYPE=REQ");
			//充值第一步
			if (BusiCodeUtils.BUSICODE_RECHARGE_FIRST.equals(busiCode) && CmdnoUtils.RECHARGE_FIRST_ACCEPT.equals(cmdno))
			{
				reqService.RechargeFirst(map);
			}
			//充值第二步
			else if (BusiCodeUtils.BUSICODE_RECHARGE_SECOND.equals(busiCode) && CmdnoUtils.RECHARGE_SECOND_ACCEPT.equals(cmdno))
			{
				reqService.RechargeSecond(map);
			}
			//消费第一步
			else if (BusiCodeUtils.BUSICODE_CONSUME_FIRST.equals(busiCode) && CmdnoUtils.CONSUME_FIRST_ACCEPT.equals(cmdno))
			{
				reqService.ConsumeFirst(map);
			}
			//消费第二步
			else if (BusiCodeUtils.BUSICODE_CONSUME_SECOND.equals(busiCode) && CmdnoUtils.CONSUME_SECOND_ACCEPT.equals(cmdno))
			{
				reqService.ConsumeSecond(map);
			}
			//冲正
			else if (BusiCodeUtils.BUSICODE_REVERSE.equals(busiCode) && CmdnoUtils.REVERSE_ACCEPT.equals(cmdno))
			{
				reqService.Reverse(map);
			}
			//退款
			else if (BusiCodeUtils.BUSICODE_REFUND.equals(busiCode) && CmdnoUtils.REFUND_ACCEPT.equals(cmdno))
			{
				reqService.Refund(map);
			}
		}
		
		//响应数据
		else if (null != busiCode && !"".equals(busiCode) && StaticUtils.MSG_TYPE_RESP.equals(msgType)) 
		{
			log.debug(StaticMethod.locationLog() + "MSG_TYPE=RESP");
			//充值第一步
			if (BusiCodeUtils.BUSICODE_RECHARGE_FIRST.equals(busiCode) && CmdnoUtils.RECHARGE_FIRST_RETURN.equals(cmdno))
			{
				respService.RechargeFirst(map);
			}
			//充值第二步
			else if (BusiCodeUtils.BUSICODE_RECHARGE_SECOND.equals(busiCode) && CmdnoUtils.RECHARGE_SECOND_RETURN.equals(cmdno)) 
			{
				respService.RechargeSecond(map);
			}
			//消费第一步
			else if (BusiCodeUtils.BUSICODE_CONSUME_FIRST.equals(busiCode) && CmdnoUtils.CONSUME_FIRST_RETURN.equals(cmdno))
			{
				respService.ConsumeFirst(map);
			}
			//消费第二步
			else if (BusiCodeUtils.BUSICODE_CONSUME_SECOND.equals(busiCode) && CmdnoUtils.CONSUME_SECOND_RETURN.equals(cmdno))
			{
				respService.ConsumeSecond(map);
			}
			//冲正
			else if (BusiCodeUtils.BUSICODE_REVERSE.equals(busiCode) && CmdnoUtils.REVERSE_RETURN.equals(cmdno))
			{
				respService.Reverse(map);
			}
			//退款
			else if (BusiCodeUtils.BUSICODE_REFUND.equals(busiCode) && CmdnoUtils.REFUND_RETURN.equals(cmdno))
			{
				respService.Refund(map);
			}
		}
		else 
		{
			++StaticUtils.errorNum;
			log.debug(StaticMethod.locationLog() + "request data is error!!!");
		}
		log.info("^^^^XXXXXXXX^^^^^ICEListener************process end.");
	}
}
