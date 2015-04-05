package com.nci.tkb.busi.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.nci.tkb.busi.exception.DAOException;

public interface DbDao 
{
	/**
	 * 允许空值
	 * 查询数据库记录信息
	 * retcode为空时抛出返回码
	 * @throws SQLException
	 */
	public Map<String, String> queryInfoAllowNo(String sql, String retcode) throws DAOException;
	
	/**
	 * 查询数据库记录信息
	 * retcode为空时抛出返回码
	 * @throws SQLException
	 */
	public abstract Map<String, String> queryInfo(String sql, String retcode) throws DAOException;
	
	/**
	 * 更新数据库（插入、更新）
	 * @throws SQLException
	 */
	public int update(String[] sql) throws DAOException, SQLException;
	
	/**
	 * 参数配置存入redis，将数据存入redis中
	 * @throws SQLException
	 */
	public Map<String, String> setCfg2Memery() throws DAOException;
	
	/**
	 * 查询数据库表记录信息
	 * @param sql
	 * @return
	 * @throws DAOException
	 */
	public List<Map<String, String>> find(String sql) throws DAOException;
	
}
