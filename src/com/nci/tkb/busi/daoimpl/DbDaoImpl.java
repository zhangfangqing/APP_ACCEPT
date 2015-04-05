package com.nci.tkb.busi.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.nci.tkb.busi.dao.DbDao;
import com.nci.tkb.busi.db.BaseDao;
import com.nci.tkb.busi.db.ConnectionPool;
import com.nci.tkb.busi.exception.DAOException;
import com.nci.tkb.busi.utils.RetCode;
import com.nci.tkb.busi.utils.SqlUtils;
import com.nci.tkb.busi.utils.StaticMethod;

/**
 * 数据库操作类型
 * 
 * @author LYP
 * @version 1.0
 * @Date 2014-02-20
 */
public class DbDaoImpl extends BaseDao implements DbDao
{
	static Logger log = Logger.getLogger(DbDaoImpl.class.getName());

	/**
	 * 查询数据库记录信息
	 * 
	 * @throws SQLException
	 */
	public Map<String, String> queryInfoAllowNo(String sql, String retcode) throws DAOException
	{
		StaticMethod.logmark(0);
		// 如果为空则抛出
		if (null == sql)
		{
			throw new DAOException(String.valueOf(RetCode.BUSI_HANDLER_ERROR), "sql is null", null);
		}
		// 返回操作函数
		Map<String, String> retMap = null;
		String[] columnNames = null;
		int columnsize = 0;
		// 获取连接
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try
		{
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			columnsize = rsmd.getColumnCount();
			columnNames = new String[columnsize];
			// 数组从0开始
			for (int i = 1; i <= columnsize; i++)
			{
				columnNames[i - 1] = rsmd.getColumnLabel(i);
				System.out.print(rsmd.getColumnLabel(i) + ",");
			}
			while (rs.next())
			{
				retMap = new HashMap<String, String>();
				for (int i = 1; i <= columnsize; i++)
				{
					// 获取各字段的值
					String columnValue = rs.getString(columnNames[i - 1]);
					retMap.put(columnNames[i - 1], columnValue);
				}
			}
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("DbDaoImpl************queryInfoAllowNo:" + e);
			log.info("query sql is：" + sql);
			throw new DAOException(retcode, e);
		}
		finally
		{
			dispose(rs, ps, conn);
		}
		StaticMethod.logmark(1);
		return retMap;
	}

	/**
	 * 查询数据库记录信息 retcode为空时抛出返回码
	 * 
	 * @throws SQLException
	 */
	public Map<String, String> queryInfo(String sql, String retcode) throws DAOException
	{
		StaticMethod.logmark(0);
		// 如果为空则抛出
		if (null == sql)
		{
			throw new DAOException(String.valueOf(RetCode.BUSI_HANDLER_ERROR), "查询sql为空", null);
		}
		// 结果标识
		boolean flag = false;
		// 返回操作函数
		Map<String, String> retMap = new HashMap<String, String>();
		String[] columnNames = null;
		int columnsize = 0;
		// 获取连接
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try
		{
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			columnsize = rsmd.getColumnCount();
			columnNames = new String[columnsize];
			// 数组从0开始
			for (int i = 1; i <= columnsize; i++)
			{
				columnNames[i - 1] = rsmd.getColumnLabel(i);
			}
			while (rs.next())
			{
				for (int i = 1; i <= columnsize; i++)
				{
					// 获取各字段的值
					String columnValue = rs.getString(columnNames[i - 1]);
					retMap.put(columnNames[i - 1], columnValue);
				}
				flag = true;
			}
			// 如果查询数据为空
			if (!flag)
			{
				log.info("DbDaoImpl****************query data is null：" + sql);
				throw new DAOException(retcode);
			}
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("DbDaoImpl*************queryInfo:" + e);
			log.info("查询语句：" + sql);
			throw new DAOException(retcode, e);
		}
		finally
		{
			dispose(rs, ps, conn);
		}
		StaticMethod.logmark(1);
		return retMap;
	}

	/**
	 * 更新数据库（插入、更新）
	 * 
	 * @throws SQLException
	 */
	public int update(String[] sqlarray) throws DAOException, SQLException
	{
		StaticMethod.logmark(0);
		// 如果为空则抛出
		if (null == sqlarray)
		{
			throw new DAOException(String.valueOf(RetCode.BUSI_HANDLER_ERROR), "更新sql为空", null);
		}
		int udtFlag = 0;
		// 获取连接
		Connection conn = null;
		Savepoint point1 = null;
		Statement stmt = null;
		try
		{
			// 获取连接
			conn = ConnectionPool.getConnection();
			// 标识着新事务的开始
			conn.setAutoCommit(false);
			// 在两个操作间可以插入一个命名的存储点作为标记
			point1 = conn.setSavepoint("point1");
			stmt = conn.createStatement();
			// 循环插入sql
			for (int i = 0; i < sqlarray.length; i++)
			{
				// 获取sql，并更新
				String sql = sqlarray[i];
				if (null != sql && !"".equals(sql))
				{
					stmt.executeUpdate(sql);
				}
			}
			// 提交
			conn.commit();
		}
		catch (SQLIntegrityConstraintViolationException e)
		{
			// 将事务回滚到那个标记
			conn.rollback(point1);
			log.info(StaticMethod.getTraceInfo());
			log.error("DbDaoImpl**********SQLIntegrityConstraintViolationException:" + e);
			for (int i = 0; i < sqlarray.length; i++)
			{
				log.info("sql语句" + i + ":" + sqlarray[i]);
			}
			throw new DAOException(RetCode.RETCODE_SUCC, "update database error.", e);
		}
		catch (Exception e)
		{
			// 将事务回滚到那个标记
			conn.rollback(point1);
			log.info(StaticMethod.getTraceInfo());
			log.error("DbDaoImpl************update:" + e);
			for (int i = 0; i < sqlarray.length; i++)
			{
				log.info("sql语句" + i + ":" + sqlarray[i]);
			}
			throw new DAOException(RetCode.RC_706, "数据库更新失败", e);
		}
		finally
		{
			dispose(null, stmt, conn);
		}
		StaticMethod.logmark(1);
		// 返回状态
		return udtFlag;
	}

	/**
	 * 参数配置存入redis，将数据存入redis中
	 * 
	 * @throws SQLException
	 */
	public Map<String, String> setCfg2Memery() throws DAOException
	{
		StaticMethod.logmark(0);
		// 列表名数组
		String[] columnNames = null;
		// 列表长度
		int columnsize = 0;
		// 获取连接
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		// 初始化fieldmap
		Map<String, String> fieldMap = new HashMap<String, String>();
		String sql = SqlUtils.SYS_CONFIG_INFO;
		String configkey = SqlUtils.CONFIG_KEY;
		String configvalue = SqlUtils.CONFIG_VALUE;
		try
		{
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			columnsize = rsmd.getColumnCount();
			columnNames = new String[columnsize];
			// 数组从0开始
			for (int i = 1; i <= columnsize; i++)
			{
				columnNames[i - 1] = rsmd.getColumnLabel(i);
			}
			while (rs.next())
			{
				// 获取field,value
				String cfgkey = rs.getString(configkey);
				String cfgvalue = rs.getString(configvalue);
				fieldMap.put(cfgkey, cfgvalue);
			}
			// 如果为0，则返回空
			if (columnsize == 0)
			{
				throw new DAOException(RetCode.RC_703);
			}
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("DbDaoImpl************setCfg2Memery:" + e);
			throw new DAOException(RetCode.RC_702, e);
		}
		finally
		{
			dispose(rs, ps, conn);
		}
		StaticMethod.logmark(1);
		return fieldMap;
	}

	@Override
	public List<Map<String, String>> find(String sql) throws DAOException
	{
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		StaticMethod.logmark(0);
		// 如果为空则抛出
		if (null == sql)
		{
			throw new DAOException(String.valueOf(RetCode.BUSI_HANDLER_ERROR), "sql is null", null);
		}
		// 返回操作函数
		Map<String, String> retMap = null;
		String[] columnNames = null;
		int columnsize = 0;
		// 获取连接
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try
		{
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			columnsize = rsmd.getColumnCount();
			columnNames = new String[columnsize];
			// 数组从0开始
			for (int i = 1; i <= columnsize; i++)
			{
				columnNames[i - 1] = rsmd.getColumnLabel(i);
			}
			while (rs.next())
			{
				retMap = new HashMap<String, String>();
				if (list == null)
				{
					list = new ArrayList<Map<String, String>>();
				}
				for (int i = 1; i <= columnsize; i++)
				{
					// 获取各字段的值
					String columnValue = rs.getString(columnNames[i - 1]);
					retMap.put(columnNames[i - 1], columnValue);
				}
				list.add(retMap);
			}
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("DbDaoImpl************queryInfoAllowNo:" + e);
			log.info("query sql is：" + sql);
			throw new DAOException(RetCode.RC_706, e);
		}
		finally
		{
			dispose(rs, ps, conn);
		}
		StaticMethod.logmark(1);
		return list;
	}
}
