package com.nci.tkb.busi.mail;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.nci.tkb.busi.utils.StaticUtils;

public class MailUtils
{
	/**
	 * ֱ�ӷ����ʼ�
	 * 
	 * @param subject
	 *            �ʼ�����
	 * @param to
	 *            �ռ���
	 * @param content
	 *            ����
	 * @throws EmailException
	 *             ����ʧ��
	 */
	public static void sendSimpleMail(String subject, String to, String content) throws EmailException
	{
		HtmlEmail email = setSimpleMailPro(subject, content);
		email.addTo(to);
		email.send();
	}

	/**
	 * ���������ٷ����ʼ�
	 * 
	 * @param subject
	 *            �ʼ�����
	 * @param to
	 *            �ռ���
	 * @param cc
	 *            �����ˣ�List<String>
	 * @param content
	 *            �ʼ�����
	 * @throws AddressException
	 * @throws EmailException
	 */
	public static void sendSimpleMail(String subject, String to, List<String> cc, String content) throws AddressException, EmailException
	{
		List<InternetAddress> toList = new ArrayList<InternetAddress>();
		HtmlEmail email = setSimpleMailPro(subject, content);
		for (int i = 0; i < cc.size(); i++)
		{
			toList.add(new InternetAddress(cc.get(i)));
		}
		email.addTo(to);
		email.setCc(toList);
		email.send();
	}

	/**
	 * ��������
	 * 
	 * @param subject
	 * @param content
	 * @return
	 * @throws EmailException
	 */
	private static HtmlEmail setSimpleMailPro(String subject, String content) throws EmailException
	{
		HtmlEmail email = new HtmlEmail();
		email.setHostName(StaticUtils.email_hostname);
		email.setAuthentication(StaticUtils.email_address, StaticUtils.email_password);
		email.setFrom(StaticUtils.email_address, StaticUtils.email_mailname);
		email.setSubject(subject);
		email.setMsg(content);
		email.setCharset("UTF-8");
		return email;
	}
}
