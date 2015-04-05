package com.nci.tkb.busi.exception;

/**
 * File: BSVException.java
 * Description: DAO抛出异常基类
 * Copyright (c)  2009深圳北控信息
 * All right reserved
 * @author:  yuanxbo
 * @version: 1.0
 * @Date: 2008-12-02
 */
import java.rmi.UnexpectedException;

/**
 * Description: DAO抛出异常基类 
 * 
 * @author: LYP
 * @version: 1.0
 * @Date: 2014-02-20
 */
@SuppressWarnings("serial")
public class UseException extends BaseException
{
	
	/**
	 * Create an exception with a message but no root cause throwable.
	 * 
	 * This is equivalent to <code>BaseException(message,null)</code>.
	 * 
	 * @see #BaseException(String, Throwable)
	 * @see UnexpectedException
	 */
	public UseException(String message)
	{
		super(message);
	}
	
	/**
	 * Create an exception with a message but no root cause throwable.
	 * 
	 * This is equivalent to <code>BaseException(message,null)</code>.
	 * 
	 * @see #BaseException(String, Throwable)
	 * @see UnexpectedException
	 */
	public UseException(String message, Throwable throwable)
	{
		super(message, throwable);
		
	}
	
	/**
	 * 新加说明
	 * @param message
	 * @param errorDes
	 * @param t
	 */
	public UseException(String message, String errorDes, Throwable t)
	{
		super(message ,t);
		
		errorMsg = errorDes;
	}
	
	/**
	 * 新加说明
	 * @param message
	 * @param errorDes
	 * @param t
	 */
	public UseException(String message, String errorDes)
	{
		super(message);
		
		errorMsg = errorDes;
	}
	
}
