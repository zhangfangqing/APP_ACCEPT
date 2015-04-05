package com.nci.tkb.busi.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONException;
import org.junit.Test;

import com.nci.tkb.busi.utils.ShareFieldUtils;
import com.nci.tkb.busi.utils.StaticUtils;

public class OperateHandlerTest
{
	@Test
	public void operateHandlerTests() throws JSONException
	{
		OperateHandler handler = new OperateHandler();
		Map<String, byte[]> reqMap = new HashMap<String, byte[]>();
		reqMap.put(ShareFieldUtils.UUID, UUID.randomUUID().toString().getBytes());
		//handler.process(reqMap);
	}
}
