package com.nci.tkb.busi.mail;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.velocity.Template;
import org.junit.Test;

public class emailTest
{
	/**
	 * 自选模板
	 * 
	 * @throws EmailException
	 */
	@Test
	public void mailtest() throws EmailException
	{
		Map<String, Object> record = new HashMap<String, Object>();
		record.put("url", "www.baidu.com");
		Template template = VelocityEngineUtile.getInstance().getTemplate("vms/emailDefalutTemplate.vm");
		String content = VelocityEngineUtile.getInstance().getWriter(record, template).toString();
		MailUtils.sendSimpleMail("hello friend", "253058194@qq.com", content);
	}

	/**
	 * 默认模板，无参数
	 * 
	 * @throws EmailException
	 */
	@Test
	public void mailDefaultTest() throws EmailException
	{
		Template template = VelocityEngineUtile.getInstance().getTemplate("vms/emailDefalutTemplate.vm");
		String content = VelocityEngineUtile.getInstance().getWriter(template).toString();
		MailUtils.sendSimpleMail("test hello", "253058194@qq.com", content);
	}
}
