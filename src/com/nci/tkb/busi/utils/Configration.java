package com.nci.tkb.busi.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.npass.xmlframework.loadxml.XMLFramework;

public class Configration 
{
	private Properties properties;
	private FileInputStream inputFile;
	
	public Configration()
	{
		loadXml("config_nci.xml");
	}
	
	/**
	 * 初始化Configration类
	 * @param fileName
	 * 要读取的配置文件的路径+名称
	 */
	public Configration(String fileName)
	{
		properties = new Properties();
		try
		{
			inputFile = new FileInputStream("./src/"+fileName);
			properties.load(inputFile);
			inputFile.close();
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("读取属性文件--->失败！" + ex);
		}
		catch (IOException ex)
		{
			System.out.println("装载文件--->失败！" + ex);
		}
	}
	
	/**
	 * 重载函数，得到key的值
	 * @param key取得其值的键
	 * @return key的值
	 */
	public String getValue(String key)
	{
		if (XMLFramework.getXmlMap().containsKey(key))
		{
			String value = XMLFramework.getXmlMap().get(key);
			return value;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * 重载函数，得到key的值
	 * @param key取得其值的键
	 * @return key的值
	 */
	public String getValue2(String key)
	{
		if (properties.containsKey(key))
		{
			String value = properties.getProperty(key);
			return value;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * load XML
	 * @param args
	 */
	public static void loadXml(String filepath)
	{
		XMLFramework.loadConfigXML(filepath);
	}

}
