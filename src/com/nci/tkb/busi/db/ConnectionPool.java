package com.nci.tkb.busi.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.nci.tkb.busi.utils.StaticUtils;


public class ConnectionPool
{
	 // 数据库驱动
	private static String jdbcDriver = StaticUtils.CONSTANT_CONFIG.getValue("jdbcDriver");
	// 数据 URL
	private static String dbUrl = StaticUtils.CONSTANT_CONFIG.getValue("dbUrl"); 
	// 数据库用户名
	private static String dbUsername = StaticUtils.CONSTANT_CONFIG.getValue("dbUsername"); 
	// 数据库用户密码
	private static String dbPassword = StaticUtils.CONSTANT_CONFIG.getValue("dbPassword"); 
	// 连接池的初始大小
	private static int initialConnections = Integer.valueOf(StaticUtils.CONSTANT_CONFIG.getValue("initialConnections")); 
	// 连接池自动增加的大小
	private static int incrementalConnections = Integer.valueOf(StaticUtils.CONSTANT_CONFIG.getValue("incrementalConnections"));
	// 连接池最大的大小
	private static int maxConnections = Integer.valueOf(StaticUtils.CONSTANT_CONFIG.getValue("maxConnections"));
	//
	private static int connPartitionCount = Integer.valueOf(StaticUtils.CONSTANT_CONFIG.getValue("connPartitionCount"));
	
	//private static BoneCP bone;
	private static BoneCP connectionPool = null;
	Connection connection = null;
	static
	{
		//Properties props = new Properties();
		try
		{
			// 加载JDBC驱动
			Class.forName(jdbcDriver);
			// 设置连接池配置信息
			BoneCPConfig config = new BoneCPConfig();
			// 数据库的JDBC URL
			config.setJdbcUrl(dbUrl);
			// 数据库用户名
			config.setUsername(dbUsername);
			// 数据库用户密码
			config.setPassword(dbPassword);
			//检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0
			//config.setIdleConnectionTestPeriod(1);
			//分区数 ，默认值2，最小1，推荐3-4，视应用而定
			config.setPartitionCount(3);
			// 数据库连接池的最小连接数
			config.setMinConnectionsPerPartition(initialConnections);
			// 数据库连接池的最大连接数
			config.setMaxConnectionsPerPartition(maxConnections);
			//连接池中未使用的链接最大存活时间，单位是分，默认值：60，如果要永远存活设置为0
			//config.setIdleMaxAge(60);
			//每次去拿数据库连接的时候一次性要拿几个,默认值：2
			config.setAcquireIncrement(incrementalConnections);
			//缓存prepared statements的大小，默认值：0
			//config.setStatementCacheSize(0);
			//每个分区释放链接助理进程的数量，默认值：3，除非你的一个数据库连接的时间内做了很多工作，不然过多的助理进程会影响你的性能
			config.setReleaseHelperThreads(connPartitionCount);
			// 设置数据库连接池
			connectionPool = new BoneCP(config);

			// int idleMaxAge =
			// Integer.parseInt(props.getProperty("idleMaxAge"));
			// int acquireIncrement =
			// Integer.parseInt(props.getProperty("acquireIncrement"));
			// int releaseHelperThreads =
			// Integer.parseInt(props.getProperty("releaseHelperThreads"));
			// int maxConnectionsPerPartition=
			// Integer.parseInt(props.getProperty("maxConnectionsPerPartition"));
			// int minConnectionsPerPartition=
			// Integer.parseInt(props.getProperty("minConnectionsPerPartition"));
			// int idleConnectionTestPeriod=
			// Integer.parseInt(props.getProperty("idleConnectionTestPeriod"));

			//BoneCPConfig config = new BoneCPConfig();


			// config.setAcquireIncrement (acquireIncrement);
			// config.setReleaseHelperThreads (releaseHelperThreads);
			// config.setMaxConnectionsPerPartition
			// (maxConnectionsPerPartition);
			// config.setMinConnectionsPerPartition
			// (minConnectionsPerPartition);
			// config.setIdleConnectionTestPeriod (idleConnectionTestPeriod);

			//connectionPool = new BoneCP(config);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 获取一个连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			conn = connectionPool.getConnection();
		}
		catch (Exception e)
		{
			System.out.println("getConnection:"+e);
			if(conn == null)
			{
				System.out.println("conn == null");
			}
			else 
			{
				System.out.println("conn不为空");
			}
		}
		return conn;
	}

	/**
	 * 放回连接池
	 * @param conn
	 * @throws SQLException
	 */
	public static void returnConnection(Connection conn) throws SQLException
	{
		if (conn != null)
		{
			conn.close();
			conn = null;
		}
	}
	
	/**
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
	 * @throws SQLExceptio
	 */
	public static void  dispose(ResultSet rs, Statement stmt, Connection conn) throws SQLException
	{
		if(rs != null)
		{
			rs.close();
			rs = null;
		}
		if(stmt != null)
		{
			stmt.close();
			stmt = null;
		}
		if(conn != null)
		{
			conn.close();
			conn = null;
		}
	}

	public static void main(String[] args)
	{

		/*
		 * try { System.out.println(DbUtil.getConnection());
		 * DbUtil.closeConnection(); } catch (SQLException e) {
		 * e.printStackTrace(); }
		 */
	}
}
