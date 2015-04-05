package com.nci.tkb.busi.db;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;






/**
 * 类名：OperationCoreImplements<br>
 * 
 * 作用: 该类实现IOperationCore接口的所有方法<br>
 */
public class OperationCoreImpl extends Thread implements IOperationCore
{
	// 日志记录
	static Logger log = Logger.getLogger(OperationCoreImpl.class);
	
	/*
	 * protected PreparedStatement ps = null; protected ResultSet rs = null;
	 * protected ResultSetMetaData rsmd = null;
	 */
	protected static OperationCoreImpl m_instance = null;
	
	/**
	 * Singleton 即单例(态)模式,用来生成对象唯一实例的方法
	 * 
	 * @return OperationCoreImplements的一个实例
	 * @throws Exception
	 */
	public static OperationCoreImpl createFactory()
	{
		if (m_instance == null)
			m_instance = new OperationCoreImpl();
		return m_instance;
	}
	
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
			InstantiationException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = ConnectionPool.getConnection();
		
		if (conn != null)
		{
			try
			{
				
				Object result = clazz.newInstance();
				
				ps = conn.prepareStatement(sql);
				if (params != null && params.length > 0)
				{
					for (int i = 0; i < params.length; i++)
					{
						ps.setObject(i + 1, params[i]);
					}
				}
				
				Method[] allMethod = clazz.getMethods();
				List<Method> setterMethodList = new ArrayList<Method>();
				for (Method m : allMethod)
				{
					if (m.getName().startsWith("set"))
					{
						setterMethodList.add(m);
					}
				}
				
				rs = ps.executeQuery();
				String columnName = null;
				Class parameterType = null;
				if (rs != null)
				{
					if (rs.next())
					{ // read only one row
					
						for (Method m : setterMethodList)
						{
							columnName = m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4, m.getName().length());
							parameterType = m.getParameterTypes()[0];
							if (parameterType.isPrimitive())
							{
								if (parameterType == Boolean.TYPE)
								{
									m.invoke(result, rs.getBoolean(columnName));
								}
								else if (parameterType == Byte.TYPE)
								{
									m.invoke(result, rs.getByte(columnName));
								}
								else if (parameterType == Short.TYPE)
								{
									m.invoke(result, rs.getShort(columnName));
								}
								else if (parameterType == Character.TYPE)
								{
									m.invoke(result, rs.getString(columnName).charAt(0));
								}
								else if (parameterType == Integer.TYPE)
								{
									m.invoke(result, rs.getInt(columnName));
								}
								else if (parameterType == Double.TYPE)
								{
									m.invoke(result, rs.getDouble(columnName));
								}
								
							}
							else
							{
								String nametype = parameterType.getName();
								if (nametype.equals("java.lang.Long"))
								{
									m.invoke(result, rs.getLong(columnName));
								}
								else if (parameterType == Float.TYPE)
								{
									m.invoke(result, rs.getFloat(columnName));
								}
								else if (nametype.equals("java.lang.Double"))
								{
									m.invoke(result, rs.getDouble(columnName));
								}
								else
								{
									m.invoke(result, rs.getObject(columnName));
								}
							}
						}
						
					}
				}
				
				return result;
				
			}
			finally
			{
				dispose(rs, ps, conn);
			}
		}
		else
		{
			return -1;
		}
	}
	
	// 返回list通用 JDBC直连
	@SuppressWarnings("unchecked")
	public <T> List<T> queryForList(String sql, Class<T> clazz, Object... params) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException,
			ClassNotFoundException
	{
		
		if (clazz == null)
		{
			throw new IllegalArgumentException("clazz is null");
		}
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = ConnectionPool.getConnection();
		
		try
		{
			List<T> resultList = new ArrayList<T>();
			
			ps = conn.prepareStatement(sql);
			if (params != null)
			{
				for (int i = 0; i < params.length; i++)
				{
					ps.setObject(i + 1, params[i]);
				}
			}
			
			rs = ps.executeQuery();
			T t = null;
			Method[] allMethod = clazz.getMethods();
			List<Method> setterMethodList = new ArrayList<Method>();
			for (Method m : allMethod)
			{
				if (m.getName().startsWith("set"))
				{
					setterMethodList.add(m);
				}
			}
			
			String columnName = null;
			Class parameterType = null;
			if (rs != null)
			{
				while (rs.next())
				{
					t = clazz.newInstance();
					for (Method m : setterMethodList)
					{
						columnName = m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4, m.getName().length());
						
						parameterType = m.getParameterTypes()[0];
						
						if (parameterType.isPrimitive())
						//if (1==1)
						{
							if (parameterType == Boolean.TYPE)
							{
								m.invoke(t, rs.getBoolean(columnName));
							}
							else if (parameterType == Byte.TYPE)
							{
								m.invoke(t, rs.getByte(columnName));
							}
							else if (parameterType == Short.TYPE)
							{
								m.invoke(t, rs.getShort(columnName));
							}
							else if (parameterType == Character.TYPE)
							{
								m.invoke(t, rs.getString(columnName).charAt(0));
							}
							else if (parameterType == Integer.TYPE)
							{
								m.invoke(t, rs.getInt(columnName));
							}
							else if (parameterType == Long.TYPE)
							{
								m.invoke(t, rs.getLong(columnName));
							}
							else if (parameterType == Float.TYPE)
							{
								m.invoke(t, rs.getFloat(columnName));
							}
							else if (parameterType == Double.TYPE)
							{
								m.invoke(t, rs.getDouble(columnName));
							}
							
						}
						else
						{
							Object value = rs.getObject(columnName);
							
							if(value != null)
							{
								String type = value.getClass().toString();
								if(type.contains("BigDecimal"))
								{
									BigDecimal bigValueBigDecimal = (BigDecimal)value;
									//private BigDecimal longitude;
									//private BigDecimal latitude;
									if("longitude".equals(columnName) || "latitude".equals(columnName))
									{
										Double doubleValue = bigValueBigDecimal.doubleValue();
										m.invoke(t, doubleValue);
									}
									else
									{
										Long longValue = bigValueBigDecimal.longValue();
										m.invoke(t, longValue);
									}
									
								}
								else
								{
									m.invoke(t, value);
								}
							}

							
						}
					}
					
					resultList.add(t);
				}
			}
			
			return resultList;
			
		}
		finally
		{
			dispose(rs, ps, conn);
		}
	}
	
	/**
	 * 增、删、改操作
	 * 
	 * @param updateString
	 *            数据库更新语句
	 * @return 更新数据库影响行数
	 * @throws SQLException 
	 * 
	 * @exception SQLException
	 */
	public int executeUpdate(String updateString) throws SQLException
	{
		int effectedRows = 0;
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if (conn != null)
		{
			try
			{
				conn.setAutoCommit(false);
				ps = conn.prepareStatement(updateString);
				effectedRows = ps.executeUpdate();
				conn.commit();
			}
			catch (SQLException ex)
			{
				log.error("executeUpdate:" + ex);
				ex.printStackTrace();
			}
			finally
			{
				dispose(rs, ps, conn);
			}
		}
		else
		{
			return -1;
		}
		
		return effectedRows;
	}
	
	/**
	 * 多表查询 返回多条语句时；调用
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Map<String, Object>> queryForList(String sql, Object... params) throws SQLException, ClassNotFoundException
	{
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		Map<String, Object> row = null;
		List<Map<String, Object>> result = null;
		Connection conn = ConnectionPool.getConnection();
		
		try
		{
			
			result = new ArrayList<Map<String, Object>>();
			
			ps = conn.prepareStatement(sql);
			if (params != null && params.length > 0)
			{
				for (int i = 0; i < params.length; i++)
				{
					ps.setObject(i + 1, params[i]);
				}
			}
			
			rs = ps.executeQuery();
			if (rs != null)
			{
				ResultSetMetaData meta = rs.getMetaData();
				int colunmNumber = meta.getColumnCount();
				
				while (rs.next())
				{
					
					row = new HashMap<String, Object>();
					for (int i = 1; i <= colunmNumber; i++)
					{
						row.put(meta.getColumnLabel(i), rs.getObject(i));
					}
					
					result.add(row);
				}
			}
			
			return result;
			
		}
		finally
		{
			row = null;
			dispose(rs, ps, conn);
		}
		
	}
	
	public ResultSet getResultSet(String sql) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = ConnectionPool.getConnection();
		try
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		}
		catch (Exception e)
		{
			log.error("getResultSet:" + e);
		}
		finally
		{
			dispose(null, ps, conn);
		}
		
		return rs;
	}
	
	/**
	 * 返回唯一一条语句
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Map<String, Object> queryForObject(String sql, Object... params) throws SQLException, ClassNotFoundException
	{
		ResultSet rs = null;
		PreparedStatement ps = null;
		Map<String, Object> result = null;
		Connection conn = ConnectionPool.getConnection();
		
		try
		{
			result = new HashMap<String, Object>();
			
			ps = conn.prepareStatement(sql);
			if (params != null && params.length > 0)
			{
				for (int i = 0; i < params.length; i++)
				{
					ps.setObject(i + 1, params[i]);
				}
			}
			
			rs = ps.executeQuery();
			if (rs != null)
			{
				ResultSetMetaData meta = rs.getMetaData();
				int colunmNumber = meta.getColumnCount();
				
				if (rs.next())
				{ // read only one row
				
					result = new HashMap<String, Object>();
					for (int i = 1; i <= colunmNumber; i++)
					{
						result.put(meta.getColumnLabel(i), rs.getObject(i));
					}
					
				}
			}
			
			return result;
			
		}
		finally
		{
			dispose(rs, ps, conn);
		}
	}


	/**
	 * 关闭连接
	 */
	public void dispose(ResultSet rs, Statement stmt, Connection conn)
	{

		try
		{
			if (rs != null)
			{
				rs.close();
				rs = null;
			}
			
			if (stmt != null)
			{
				stmt.close();
				stmt = null;
			}
			
			if (conn != null)
			{
				ConnectionPool.returnConnection(conn);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 释放系统连接资源
	 */
	public void dispose(ResultSet rs, PreparedStatement stmt, Connection conn)
	{
		try
		{
			if (rs != null)
			{
				rs.close();
				rs = null;
			}
			
			if (stmt != null)
			{
				stmt.close();
				stmt = null;
			}
			
			if (conn != null)
			{
				ConnectionPool.returnConnection(conn);
			}
		}
		catch (SQLException e)
		{
			log.error(e);
		}
	}
	
}