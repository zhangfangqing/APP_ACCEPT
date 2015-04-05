package com.nci.tkb.busi.manager;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.nci.tkb.busi.utils.BusiCodeUtils;
import com.nci.tkb.busi.utils.CmdnoUtils;
import com.nci.tkb.busi.utils.ShareFieldUtils;
import com.nci.tkb.busi.utils.StaticUtils;

public class OperateManagerTest
{
	//测试第一步
	@Test
	public void operateTest() {
		OperateManager operateManager = new OperateManager();
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put(ShareFieldUtils.MSG_TYPE, StaticUtils.MSG_TYPE_REQ);
		reqMap.put(ShareFieldUtils.BUSI_CODE, BusiCodeUtils.BUSICODE_RECHARGE_FIRST);
		reqMap.put(ShareFieldUtils.CMD_NO, CmdnoUtils.RECHARGE_FIRST_ACCEPT);
		reqMap.put(ShareFieldUtils.CER_SN, "1100");
		operateManager.process(reqMap);
	}
}
