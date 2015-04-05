package com.nci.tkb.busi.exception;

/**
 * File: BaseException.java
 * Description: 抛出异常基类
 * Copyright (c)  2009深圳北控信息
 * All right reserved
 * @author:  yuanxbo
 * @version: 1.0
 * @Date: 2008-12-02
 */
import java.rmi.UnexpectedException;

/**
 * Description: 抛出异常基类
 * 
 * @author: LYP
 * @version: 1.0
 * @Date: 2014-02-20
 */
@SuppressWarnings("serial")
public class BaseException extends Exception
{
	private final Throwable mParentThrowable;
	
	String errorMsg;
	
	/**
	 * 构造函数
	 */
	public BaseException()
	{
		this(null, null);
	}
	
	/**
	 * Create an exception with a message but no root cause throwable.
	 * 
	 * This is equivalent to <code>BaseException(message,null)</code>.
	 * 
	 * @see #BaseException(String, Throwable)
	 * @see UnexpectedException
	 */
	
	public BaseException(String message)
	{
		this(message, null);
	}
	
	public BaseException(String message, Throwable t)
	{
		super(message);
		
		mParentThrowable = t;
	}
	
	/**
	 * 新加说明
	 * @param message
	 * @param errorDes
	 * @param t
	 */
	public BaseException(String message, String errorDes, Throwable t)
	{
		super(message);
		
		errorMsg = errorDes;
		
		mParentThrowable = t;
	}
	
	/**
	 * Create an exception with a root cause throwable but but no message.
	 * 
	 * This is equivalent to <code>BaseException(null,t)</code>.
	 * 
	 * @see #BaseException(String, Throwable)
	 * @see UnexpectedException
	 */
	
	public BaseException(Throwable t)
	{
		this(null, t);
	}
	
	/**
	 * Return the root cause of the trouble.
	 * 
	 * If there was no root cause, i.e. if this is not a nested exception, then
	 * return <code>null</code>
	 * 
	 * @return the root cause of the trouble, or null if there was none.
	 */
	public Throwable getParentThrowable()
	{
		return mParentThrowable;
	}
	
	/**
	 * Return a pretty-printed version of the exception.
	 * 
	 * This will include details of the parent throwable if ther is one.
	 * 
	 * @return a pretty-print output of this exception
	 */
	public String toString()
	{
		String msg = super.toString();
		if (mParentThrowable != null)
		{
			msg += "<Parent Throwable: " + mParentThrowable.toString() + ">";
		}
		return msg;
	}

	public String getErrorMsg()
	{
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}
}
