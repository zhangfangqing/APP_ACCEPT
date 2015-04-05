package com.nci.tkb.busi.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * dao父类，集成数据库操作函数
 * @author yuanxiaobo
 *
 */
public class BaseDao
{
	private static IOperationCore objIOperationCore = OperationCoreImpl.createFactory();
	
	/**
	 * 获取表映射object
	 * 
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	public Object queryForObject(String sql, Class clazz, Object... params) throws SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			InstantiationException
	{
		return objIOperationCore.queryForObject(sql, clazz, params);
	}
	
	/**
	 * 返回List
	 * @param <T>
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryForList(String sql, Class<T> clazz, Object... params) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException,
			ClassNotFoundException
	{
		return objIOperationCore.queryForList(sql, clazz, params);
	}
	

	/**
	 * 更新操作
	 * @param updateString
	 * @return
	 * @throws SQLException
	 */
	public int executeUpdate(String updateString) throws SQLException
	{
		return objIOperationCore.executeUpdate(updateString);
	}
	
	/**
	 * 删除操作
	 * @param deleteString
	 * @return
	 * @throws SQLException
	 */
	public int executeDelete(String deleteString) throws SQLException
	{
		return objIOperationCore.executeUpdate(deleteString);
	}
	
	/**
	 * 插入sql语句操作
	 * @param insertString
	 * @return
	 * @throws SQLException
	 */
	public int executeInsert(String insertString) throws SQLException
	{
		return objIOperationCore.executeUpdate(insertString);
	}
	
	/**
	 * 释放资源
	 * @param rs
	 * @param stmt
	 * @param conn
	 * @throws SQLException
	 */
	public static void  dispose(ResultSet rs, Statement stmt, Connection conn)
	{
		objIOperationCore.dispose(rs, stmt, conn);
	}
	
	/**
	 * 释放资源
	 * @param rs
	 * @param stmt
	 * @param conn
	 * @throws SQLException
	 */
	public static void  dispose(ResultSet rs, PreparedStatement stmt, Connection conn)
	{
		objIOperationCore.dispose(rs, stmt, conn);
	}
	
	/**
	 * 查询map
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Map<String, Object> queryForObject(String sql, Object... params) throws SQLException, ClassNotFoundException
	{
		return objIOperationCore.queryForObject(sql, params);
	}
	
	
	/**
	 * 查询list（map）
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Map<String, Object>> queryForList(String sql, Object... params) throws SQLException, ClassNotFoundException
	{
		return objIOperationCore.queryForList(sql, params);
	}
	
	public ResultSet getResultSet(String sql) throws SQLException
	{
		return objIOperationCore.getResultSet(sql);
	}
	
}
