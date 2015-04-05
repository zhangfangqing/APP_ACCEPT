package com.nci.tkb.busi.mail;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VelocityEngineUtile
{
	private VelocityEngine velocityEngine;

	private VelocityEngineUtile()
	{
		velocityEngine = VelocityEngineFactory.createVelocityEngine();
	};

	private static VelocityEngineUtile instance = new VelocityEngineUtile();

	public static VelocityEngineUtile getInstance()
	{
		return instance;
	}

	/**
	 * 获得模板
	 * 
	 * @param url
	 * @return
	 */
	public Template getTemplate(String url)
	{
		return velocityEngine.getTemplate(url, "utf-8");
	}

	/**
	 * 将模板内容转换成字符串，默认模板vms/emailDefalutTemplate.vm
	 * 
	 * @param record
	 * @return
	 */
	public Writer getWriter(Map<String, Object> record)
	{
		Template template = getTemplate("vms/emailDefalutTemplate.vm");
		Writer writer = getWriter(record, template);
		return writer;
	}

	/**
	 * 获得邮件内容
	 * 
	 * @param record
	 *            模板参数
	 * @param template
	 *            模板
	 * @return
	 */
	public Writer getWriter(Map<String, Object> record, Template template)
	{
		Writer writer = new StringWriter();
		VelocityContext context = new VelocityContext();
		context.put("map", record);
		template.merge(context, writer);
		return writer;
	}

	/**
	 * 无参数模板
	 * 
	 * @param template
	 * @return
	 */
	public Writer getWriter(Template template)
	{
		Writer writer = new StringWriter();
		template.merge(new VelocityContext(), writer);
		return writer;
	}

	/**
	 * 无参数默认路径模板
	 * 
	 * @return
	 */
	public Writer getWriter()
	{
		Template template = getTemplate("vms/emailDefalutTemplate.vm");
		return getWriter(template);
	}
}