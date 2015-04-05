package com.nci.tkb.busi.utils;

import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.velocity.Template;

import com.nci.tkb.busi.mail.MailUtils;
import com.nci.tkb.busi.mail.VelocityEngineUtile;

public class EmailUtils
{

	/**
	 * 发送邮件（发送单人）
	 * 
	 * @param subject
	 *            邮件标题
	 * @param to
	 *            收件人
	 * @param template
	 *            收件内容模板路径，默认在vms包下
	 * @param record
	 *            模板参数（如模板中有需传入参数，需赋值）
	 * @throws EmailException
	 */
	public static void sendSimpleMail(String subject, String to, String template, Map<String, Object> record) throws EmailException
	{
		Template temp = VelocityEngineUtile.getInstance().getTemplate(template);
		String content = VelocityEngineUtile.getInstance().getWriter(record, temp).toString();
		MailUtils.sendSimpleMail(subject, to, content);
	}
}
