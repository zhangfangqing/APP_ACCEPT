/**
 * 数据库常用操作封装
 */
package com.nci.tkb.busi.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * 类名：IOperationCore<br>
 * 
 * 作用: 该接口封装了数据库操作的大部分方法<br>
 * 
 */
public interface IOperationCore
{
	
	/**
	 * sql更新语句
	 * 
	 * @param updateString
	 *            数据库更新语句
	 * @return 更新数据库影响行数
	 * 
	 * @exception SQLException
	 */
	int executeUpdate(String updateString) throws SQLException;
	
	public ResultSet getResultSet(String sql) throws SQLException;
	
	/**
	 * 返回Object对象
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
			InstantiationException;
	
	@SuppressWarnings("unchecked")
	public <T> List<T> queryForList(String sql, Class<T> clazz, Object... params) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException,
			ClassNotFoundException;
	
	/**
	 * 释放系统连接资源
	 * 
	 * @exception SQLException
	 *                如果关闭失败将抛出<code>SQLException</code>
	 */
	void dispose(ResultSet rs, Statement stmt, Connection conn);
	
	
	/**
	 * 返回唯一一条语句
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Map<String, Object> queryForObject(String sql, Object... params) throws SQLException, ClassNotFoundException;
	
	/**
	 * 多表查询 返回多条语句时；调用
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Map<String, Object>> queryForList(String sql, Object... params) throws SQLException, ClassNotFoundException;
	
}