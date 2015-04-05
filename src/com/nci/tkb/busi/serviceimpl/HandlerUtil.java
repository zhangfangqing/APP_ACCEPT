package com.nci.tkb.busi.serviceimpl;

/**
 * File: HandlerUtil.java
 * Description: 基础功能函数
 * @author: ZYB
 * @version: 1.0
 * @Date: 2014-03-03
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.nci.tkb.busi.dao.DbDao;
import com.nci.tkb.busi.daoimpl.DbDaoImpl;
import com.nci.tkb.busi.exception.BSVException;
import com.nci.tkb.busi.exception.DAOException;
import com.nci.tkb.busi.exception.UseException;
import com.nci.tkb.busi.redis.RedisUtils;
import com.nci.tkb.busi.utils.RetCode;
import com.nci.tkb.busi.utils.ShareFieldUtils;
import com.nci.tkb.busi.utils.SqlArrayUtils;
import com.nci.tkb.busi.utils.SqlMothed;
import com.nci.tkb.busi.utils.SqlUtils;
import com.nci.tkb.busi.utils.StaticMethod;
import com.nci.tkb.busi.utils.StaticUtils;
import com.nci.tkb.busi.utils.TableCollection;
import com.nci.tkb.busi.utils.TableColumnsUtils;
import com.npass.pay.memshare.client.BytesUtils;
import com.npass.pay.memshare.client.RedisUtil;
import com.npass.pay.memshare.client.SendHandler;

/**
 * Description: 基础功能函数
 * 
 * @author: ZYB
 * @version: 1.0
 * @Date: 2014-03-03
 */
public class HandlerUtil
{
	// log4j
	static Logger log = Logger.getLogger(HandlerUtil.class.getName());
	public static final int maxLive = 2 * 60;
	public static final Random random = new Random();
	public static final int randomLength = 1000000;
	// 获取数据库操作类
	DbDao dbDao = new DbDaoImpl();

	/**
	 * 根据证书序列号获取POS信息
	 * 
	 * @param map
	 * @return
	 * @throws UseException
	 */
	public Map<String, String> getCerSnToPosInfo(String CerSn) throws UseException
	{
		StaticMethod.logmark(0);
		log.debug(StaticMethod.locationLog() + "get getCerSnToPosInfo function.");
		// 返回map
		Map<String, String> retMap = null;

		try
		{
			// 定义参数数组
			String sqlArray[] = new String[1];
			sqlArray[0] = CerSn;
			// 获取查询语句
			String sql = SqlUtils.sqlAddParam(SqlUtils.SQL_MERPOS_CERSN_VALIDATE, sqlArray);
			retMap = getDataInfo(ShareFieldUtils.MER_POS, CerSn, sql, RetCode.MERPOS_NOT_EXIST);
		}
		catch (UseException e)
		{
			log.info(StaticMethod.getTraceInfo());
			throw new UseException(e.getMessage(), e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("getCerSnToPosInfo error:" + e);
			throw new UseException(RetCode.MERPOS_NOT_EXIST, e);
		}
		StaticMethod.logmark(1);
		return retMap;
	}

	/**
	 * 充值金额验证
	 * 
	 * @param paramMap
	 * @param cmdMap
	 * @return
	 */
	public void valChargeLimit(String paymoney) throws UseException
	{
		StaticMethod.logmark(0);
		int money = 0;
		try
		{
			money = Integer.valueOf(paymoney);

			if (money > StaticUtils.CHARGE_LIMIT)
			{
				log.error("recharge is out range:" + paymoney);
				throw new UseException(RetCode.ERROR_CHARGE_LIMIT, "recharge is out range");
			}
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("menoy format is error:" + e);
			throw new UseException(RetCode.BUSI_HANDLER_ERROR, e);
		}
		StaticMethod.logmark(1);
	}

	/**
	 * 获取REDIS或数据库相关信息
	 * 
	 * @param map
	 * @return
	 * @throws UseException
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getDataInfo(String table, String id, String sqlUtil, String ret) throws UseException
	{
		StaticMethod.logmark(0);
		// 返回map
		Map<String, String> retMap = null;

		try
		{
			// 判断取值,不为空
			if (null != id && !"".equals(id))
			{
				log.debug("********************getDataInfo:1");
				byte[] merbt = RedisUtil.getBHashMap(table, id);
				log.debug("********************getDataInfo:2");
				// 有数据则解析，无则从数据库取值
				if (merbt != null)
				{
					retMap = (Map<String, String>) BytesUtils.byteToObject(merbt);
				}
				// REDIS中没有，从数据库中取
				else
				{
					// 定义参数数组
					String sqlArray[] = new String[1];
					sqlArray[0] = id;
					// 获取查询语句
					String sql = SqlUtils.sqlAddParam(sqlUtil, sqlArray);
					// 查询数据
					retMap = dbDao.queryInfo(sql, ret);
					// 将数据通过MQ发送到redis
					sendRedisUdt(table, id);
				}
				log.debug("********************getDataInfo:3");
			}
			else
			{
				// 数据不合法
				log.info(StaticMethod.getTraceInfo());
				throw new UseException(ret);
			}
		}
		catch (DAOException e)
		{
			log.info(StaticMethod.getTraceInfo());
			throw new UseException(ret, e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("getDataInfo query data error:" + e);
			throw new UseException(ret, e);
		}

		StaticMethod.logmark(1);
		return retMap;
	}

	/**
	 * 将table，Id发送到内存管理模块， 让内存管理模块将数据加载进来 0：删除 1：添加 2：更新 3：全部重新加载 4：某表重新加载
	 * 
	 * @param table
	 * @param Id
	 */
	public void sendRedisUdt(String table, String Id)
	{
		StaticMethod.logmark(0);
		Map<String, String> redisMap = new HashMap<String, String>();
		redisMap.put(ShareFieldUtils.TABLE, table);
		redisMap.put(ShareFieldUtils.ID, Id);
		// 增加
		redisMap.put(ShareFieldUtils.MSG_TYPE, "1");
		try
		{
			SendHandler.sendHandler(redisMap, 0);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("update memcache error:" + e);
			log.info(" error data:" + redisMap.toString());
		}

		StaticMethod.logmark(1);
	}

	/**
	 * 验证用户名是否有效
	 * 
	 * @param userName
	 */
	public void checkValidUserName(String userName) throws UseException
	{
		StaticMethod.logmark(0);
		boolean flag = this.isValidMobile(userName);
		if (!flag)
		{
			log.error("username is not valid:" + userName);
			throw new UseException(RetCode.USERNAME_ERROR, "username is not valid");
		}
		StaticMethod.logmark(0);

	}

	/**
	 * 验证子账号是否有效
	 * 
	 * @param subUserName
	 * @throws UseException
	 */
	public void checkValidSubUserName(String subUserName) throws UseException
	{
		StaticMethod.logmark(0);
		boolean flag = this.isValidSubAccount(subUserName);
		if (!flag)
		{
			log.error("username is not valid:" + subUserName);
			throw new UseException(RetCode.USERNAME_IS_ERROR, "username is not valid");
		}
		StaticMethod.logmark(0);

	}

	/**
	 * 公共方法，判断用户名是否为空
	 * 
	 * @param string
	 * @param retCode
	 *            为空时候的返回值
	 * @throws UseException
	 */
	public void stringIsEmpty(String string, String retCode) throws UseException
	{
		if (StringUtils.isBlank(string))
		{
			log.error(string + " IS EMPTY Code: " + retCode);
			throw new UseException(retCode);
		}
	}

	/**
	 * 验证是否存在
	 * 
	 * @param map
	 *            验证内容
	 * @param retCode
	 *            返回码
	 * @throws UseException
	 */
	public void mapIsNull(Map<String, String> map, String retCode) throws UseException
	{

		if (map == null)
		{
			throw new UseException(retCode);
		}

	}

	/**
	 * 验证角色是否正确
	 * 
	 * @param rightRoleID
	 * @param currentID
	 * @throws UseException
	 */
	public void compareRole(String rightRoleID, String currentID) throws UseException
	{
		compareEquals(rightRoleID, currentID, RetCode.USERNAME_IS_ERROR);
	}

	/**
	 * 验证两字符串是否相等
	 * 
	 * @param sys
	 *            系统数据
	 * @param current
	 *            用户数据
	 * @param retCode
	 *            不相等时返回码
	 * @throws UseException
	 */
	public void compareEquals(String sys, String current, String retCode) throws UseException
	{
		if (!sys.equals(current))
		{
			log.error(sys + " NOT EQUAL WITH " + current);
			throw new UseException(retCode);
		}
	}

	public void compareEqualsNotNull(String sys, String current, String retCode) throws UseException
	{
		if (StringUtils.isNotBlank(current))
		{
			compareEquals(sys, current, retCode);
		}
	}

	/**
	 * 验证密码是否正确
	 * 
	 * @param sysPassword
	 * @param currentPassword
	 * @throws UseException
	 */
	public void comparePassword(String sysPassword, String currentPassword) throws UseException
	{
		compareEquals(sysPassword, currentPassword, RetCode.PASSWORD_IS_ERROR);
	}

	/**
	 * 手机号码验证
	 * 
	 * @param userName
	 * @return
	 */
	public boolean isValidMobile(String userName)
	{
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(userName);
		return m.matches();
	}

	/**
	 * 子账号验证 规则：前13位数字+“-” + 1-4位不等数字
	 * 
	 * @param subUserName
	 * @return
	 */
	public boolean isValidSubAccount(String subUserName)
	{
		Pattern p = Pattern.compile("^([1-9])\\d{12}\\-\\d{1,4}");
		Matcher m = p.matcher(subUserName);
		System.out.println(m.matches());
		return m.matches();
	}

	/**
	 * 获取用户登录表信息 <br>
	 * 仅在注册时候使用该方法 </br>
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getUserInfo(String userName) throws Exception
	{
		StaticMethod.logmark(1);
		Map<String, String> retMap = null;
		// 判断取值,不为空
		if (StringUtils.isNotBlank(userName))
		{
			Map<String, String> limitMap = new HashMap<String, String>();
			limitMap.put(ShareFieldUtils.USERNAME, userName);
			retMap = this.getDataByRedisOrDatabase(TableCollection.LOGON_USER, userName, limitMap, true);
		}
		StaticMethod.logmark(1);
		return retMap;
	}

	/**
	 * 获取已注册用户信息
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getRegisterUserInfo(String userName) throws Exception
	{

		StaticMethod.logmark(1);
		// 判断取值,不为空
		Map<String, String> limitMap = new HashMap<String, String>();
		limitMap.put(ShareFieldUtils.USERNAME, userName);
		limitMap.put(ShareFieldUtils.STATE, "1");
		Map<String, String> retMap = this.getDataByRedisOrDatabase(TableCollection.LOGON_USER, userName, limitMap, true);
		StaticMethod.logmark(1);
		return retMap;

	}

	/**
	 * 获取用户角色
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getRoleUser(String userId) throws Exception
	{
		StaticMethod.logmark(1);
		Map<String, String> retMap = null;
		// 判断取值,不为空
		if (StringUtils.isNotEmpty(userId))
		{
			Map<String, String> limitMap = new HashMap<String, String>();
			limitMap.put(ShareFieldUtils.USER_ID, userId);
			limitMap.put(ShareFieldUtils.STATUS, "0");
			retMap = this.getDataByRedisOrDatabase(TableCollection.ROLE_USER, userId, limitMap, true);
		}
		StaticMethod.logmark(1);
		return retMap;
	}

	/**
	 * 查询商户是否合法
	 * 
	 * @param merCode
	 * @return
	 * @throws UseException
	 */
	public Map<String, String> getMerInfo(String merCode) throws UseException
	{
		StaticMethod.logmark(0);
		log.debug(StaticMethod.locationLog() + "get getMerInfo function.");
		// 返回Map
		Map<String, String> retMap = null;

		try
		{
			String sqlArray[] = new String[1];
			sqlArray[0] = merCode;
			// 获取查询语句
			String sql = SqlUtils.sqlAddParam(SqlUtils.SQL_MERCHANT_VALIDATE, sqlArray);
			// 获取商户信息
			retMap = getDataInfo(ShareFieldUtils.MERCHANT_INFO, merCode, sql, RetCode.MERCHANT_NOT_EXIST);

		}
		catch (UseException e)
		{
			log.error(StaticMethod.getTraceInfo());
			log.error("getMerInfo************query merInfo error:" + e);
			throw new UseException(e.getMessage(), e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("getMerInfo************query merInfo error:" + e);
			throw new UseException(RetCode.MERCHANT_WRONG, e);
		}
		StaticMethod.logmark(1);
		return retMap;
	}

	/**
	 * 查询操作员是否合法
	 * 
	 * @param merCode
	 * @return
	 * @throws UseException
	 */
	public Map<String, String> getOptInfo(String operator, String merCode) throws UseException
	{
		StaticMethod.logmark(0);
		log.debug(StaticMethod.locationLog() + "get getOptInfo function.");
		// 返回Map
		Map<String, String> retMap = null;

		try
		{
			String sqlArray[] = new String[1];
			sqlArray[0] = operator;
			sqlArray[1] = merCode;
			// 获取查询语句
			String sql = SqlUtils.sqlAddParam(SqlUtils.SQL_OPERATOR_VALIDATE, sqlArray);
			// 获取操作员信息
			retMap = getDataInfo(ShareFieldUtils.USER_INFO, operator, sql, RetCode.OPERATOR_NOT_EXIST);

		}
		catch (UseException e)
		{
			log.error(StaticMethod.getTraceInfo());
			log.error("getOptInfo************query optInfo error:" + e);
			throw new UseException(e.getMessage(), e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("getOptInfo************query optInfo error:" + e);
			throw new UseException(RetCode.OPERATOR_WRONG, e);
		}
		StaticMethod.logmark(1);
		return retMap;
	}

	/**
	 * 查询POS是否绑定商户
	 * 
	 * @param posId
	 * @param merCode
	 * @return
	 * @throws UseException
	 */
	public boolean getPosByMerInfo(String posId, String merCode) throws UseException
	{
		StaticMethod.logmark(0);
		log.debug(StaticMethod.locationLog() + "get getPosByUserInfo function.");
		// 返回Map
		Map<String, String> retMap = null;
		boolean result = false;
		try
		{
			String sqlArray[] = new String[1];
			sqlArray[0] = posId;
			sqlArray[1] = merCode;
			// 获取查询语句
			String sqlUtil = SqlUtils.sqlAddParam(SqlUtils.SQL_QUERY_MER_POS, sqlArray);
			// 获取商户信息
			String sql = SqlUtils.sqlAddParam(sqlUtil, sqlArray);
			// 查询数据
			retMap = dbDao.queryInfo(sql, RetCode.DB_QUERY_ERROR);
			if (!retMap.isEmpty())
			{
				result = true;
			}
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("getPosByMerInfo************query error:" + e);
			throw new UseException(RetCode.MERCHANT_WRONG, e);
		}
		StaticMethod.logmark(1);
		return result;
	}

	/**
	 * 注册信息存入数据库
	 * 
	 * @param map
	 * @throws DAOException
	 * @throws BSVException
	 * @throws SQLException
	 */
	public void insertLogonUser(Map<String, String> map) throws DAOException, BSVException, SQLException
	{
		String seq = getNextSeq("SEQ_" + TableCollection.LOGON_USER);
		Map<String, String> insertLogonUserMap = new HashMap<String, String>();
		{
			// 存LogonUser
			insertLogonUserMap = TableColumnsUtils.copyProperties(TableCollection.LOGON_USER, map);
			insertLogonUserMap.put(ShareFieldUtils.ID, seq);
			// 注册的时候状态state为0
			insertLogonUserMap.put(ShareFieldUtils.STATE, "0");
		}
		String sqlLogonUser = SqlMothed.getInsertSql(TableCollection.LOGON_USER, insertLogonUserMap);
		// 存RoleUser

		Map<String, String> insertRoleUser = new HashMap<String, String>();
		{
			insertRoleUser.put(ShareFieldUtils.USER_ID, seq);
			insertRoleUser.put(ShareFieldUtils.ROLE_ID, map.get(ShareFieldUtils.APP_MODE));
		}
		String sqlRoleUser = SqlMothed.getInsertSql(TableCollection.ROLE_USER, insertRoleUser);
		dbDao.update(SqlArrayUtils.StringsToArray(sqlLogonUser, sqlRoleUser));

	}

	/**
	 * 更新注册信息
	 * 
	 * @param map
	 * @throws DAOException
	 * @throws SQLException
	 * @throws BSVException
	 */
	public void updateLogonUser(Map<String, String> map) throws DAOException, SQLException, BSVException
	{
		// 更新密码，最后修改时间

		Map<String, String> updateLogonUserMap = new HashMap<String, String>();
		{
			updateLogonUserMap.put(ShareFieldUtils.PASSWORD, map.get(ShareFieldUtils.PASSWORD));
			updateLogonUserMap.put(ShareFieldUtils.ID, map.get(ShareFieldUtils.ID));
		}
		String updateLogonUser = SqlMothed.getUpdateSqlById(TableCollection.LOGON_USER, updateLogonUserMap);
		Map<String, String> updateRoleUserMap = new HashMap<String, String>();
		// 软删除
		updateRoleUserMap.put(ShareFieldUtils.STATUS, "1");
		Map<String, String> limitMap = new HashMap<String, String>();
		limitMap.put(ShareFieldUtils.USER_ID, map.get(ShareFieldUtils.ID));
		String updateRoleUser = SqlMothed.getUpdateSql(TableCollection.ROLE_USER, updateRoleUserMap, limitMap);

		Map<String, String> insertRoleUserMap = new HashMap<String, String>();
		insertRoleUserMap.put(ShareFieldUtils.USER_ID, map.get(ShareFieldUtils.ID));
		insertRoleUserMap.put(ShareFieldUtils.ROLE_ID, map.get(ShareFieldUtils.APP_MODE));
		insertRoleUserMap.put(ShareFieldUtils.STATUS, "0");
		String insertRoleUser = SqlMothed.getInsertSql(TableCollection.ROLE_USER, insertRoleUserMap);

		dbDao.update(SqlArrayUtils.StringsToArray(updateLogonUser, updateRoleUser, insertRoleUser));
	}

	/**
	 * 更新单个表
	 * 
	 * @param tableName
	 *            表名
	 * @param updateField
	 *            更新字段
	 * @param condition
	 *            更新限制条件
	 * @throws BSVException
	 * @throws DAOException
	 * @throws SQLException
	 */
	public void update(TableCollection tableName, Map<String, String> updateField, Map<String, String> condition) throws BSVException, DAOException, SQLException
	{
		String sql = SqlMothed.getUpdateSql(tableName, updateField, condition);
		dbDao.update(SqlArrayUtils.StringsToArray(sql));
	}

	/**
	 * 更新单个表
	 * 
	 * @param tableName
	 *            表名
	 * @param updateField
	 *            更新字段，默认ID更新，需有ID
	 * @throws BSVException
	 * @throws DAOException
	 * @throws SQLException
	 */
	public void update(TableCollection tableName, Map<String, String> updateField) throws BSVException, DAOException, SQLException
	{
		String sql = SqlMothed.getUpdateSqlById(tableName, updateField);
		dbDao.update(SqlArrayUtils.StringsToArray(sql));
	}

	/**
	 * 新增数据
	 * 
	 * @param tableName
	 *            表名
	 * @param insertMap
	 *            新增字段
	 * @throws BSVException
	 * @throws DAOException
	 * @throws SQLException
	 */
	public void insert(TableCollection tableName, Map<String, String> insertMap) throws BSVException, DAOException, SQLException
	{
		String sql = SqlMothed.getInsertSql(tableName, insertMap);
		dbDao.update(SqlArrayUtils.StringsToArray(sql));
	}

	/**
	 * 新增数据,多条
	 * 
	 * @param tableName
	 *            表名
	 * @param insertList
	 *            List<Map<String, String>>类型
	 * @throws BSVException
	 * @throws DAOException
	 * @throws SQLException
	 */
	public void insert(TableCollection tableName, List<Map<String, String>> insertList) throws BSVException, DAOException, SQLException
	{
		List<String> _list = new ArrayList<String>();
		for (Map<String, String> insertMap : insertList)
		{
			String sql = SqlMothed.getInsertSql(tableName, insertMap);
			_list.add(sql);
		}
		dbDao.update(SqlArrayUtils.ListToArray(_list));
	}

	/**
	 * 更新用户登录表、保存登录日志表
	 * 
	 * @throws BSVException
	 * @throws SQLException
	 * @throws DAOException
	 */
	public void updateLogonUserAndLog(Map<String, String> map) throws BSVException, DAOException, SQLException
	{
		Map<String, String> logonUser = new HashMap<String, String>();
		logonUser.put(ShareFieldUtils.USERNAME, "");
		Map<String, String> limitLogonUser = new HashMap<String, String>();
		limitLogonUser.put(ShareFieldUtils.STATE, "1");
		limitLogonUser.put(ShareFieldUtils.USERNAME, map.get(ShareFieldUtils.USERNAME));
		String updateLogonUser = SqlMothed.getUpdateSql(TableCollection.LOGON_USER, logonUser, limitLogonUser);

		Map<String, String> log = TableColumnsUtils.copyProperties(TableCollection.LOGON_USER_LOG, map);

		String insertLog = SqlMothed.getInsertSql(TableCollection.LOGON_USER_LOG, log);

		dbDao.update(SqlArrayUtils.StringsToArray(updateLogonUser, insertLog));
	}

	/**
	 * 子账号注册
	 * 
	 * @param map
	 * @throws DAOException
	 * @throws BSVException
	 * @throws SQLException
	 */
	public void saveLogonUserAndRoleAndUserInfo(Map<String, String> map) throws DAOException, BSVException, SQLException
	{
		String id = this.getNextSeq("SEQ_" + ShareFieldUtils.LOGON_USER);
		String password = map.get(ShareFieldUtils.PASSWORD);
		if (StringUtils.isBlank(password))
		{
			password = "123456";
		}
		// 保存到LOGONUSER中
		Map<String, String> logonUser = new HashMap<String, String>();
		logonUser.put(ShareFieldUtils.USERNAME, map.get(ShareFieldUtils.USERNAME));
		logonUser.put(ShareFieldUtils.ID, id);
		logonUser.put(ShareFieldUtils.PASSWORD, password);
		logonUser.put(ShareFieldUtils.STATE, "1");
		String saveLogonUser = SqlMothed.getInsertSql(TableCollection.LOGON_USER, logonUser);

		// 保存到ROLE_USER中
		Map<String, String> role = new HashMap<String, String>();
		role.put(ShareFieldUtils.USER_ID, id);
		role.put(ShareFieldUtils.ROLE_ID, "3100");
		role.put(ShareFieldUtils.STATE, "1");
		String saveRoleUser = SqlMothed.getInsertSql(TableCollection.ROLE_USER, role);

		String userInfo = SqlMothed.getInsertSql(TableCollection.USER_INFO, map);

		dbDao.update(SqlArrayUtils.StringsToArray(saveLogonUser, saveRoleUser, userInfo));

	}

	/**
	 * 注册成功
	 * 
	 * @param map
	 * @throws BSVException
	 * @throws DAOException
	 * @throws SQLException
	 */
	public void registerSuccess(Map<String, String> map) throws BSVException, DAOException, SQLException
	{
		Map<String, String> updateLoginUser = new HashMap<String, String>();
		updateLoginUser.put(ShareFieldUtils.STATE, "1");
		Map<String, String> limit = new HashMap<String, String>();
		limit.put(ShareFieldUtils.USERNAME, map.get(ShareFieldUtils.USERNAME));
		String updateSQL = SqlMothed.getUpdateSql(TableCollection.LOGON_USER, updateLoginUser, limit);
		dbDao.update(SqlArrayUtils.StringsToArray(updateSQL));
	}

	/**
	 * 查询seq
	 * 
	 * @return 返回下一个seq
	 * @throws DAOException
	 */
	public String getNextSeq(String seq) throws DAOException
	{
		String querySeqSql = "SELECT " + seq + ".Nextval FROM DUAL";
		Map<String, String> map = dbDao.queryInfo(querySeqSql, "0");
		return map.get("NEXTVAL");
	}

	/**
	 * 查询POS是否绑定证书
	 * 
	 * @param posId
	 * @param cerSn
	 * @return
	 * @throws UseException
	 */
	public boolean getPosByCertInfo(String posId, String cerSn) throws UseException
	{
		StaticMethod.logmark(0);
		log.debug(StaticMethod.locationLog() + "get getPosByCertInfo function.");
		// 返回Map
		Map<String, String> retMap = null;
		boolean result = false;
		try
		{
			String sqlArray[] = new String[1];
			sqlArray[0] = posId;
			sqlArray[1] = cerSn;
			// 获取查询语句
			String sqlUtil = SqlUtils.sqlAddParam(SqlUtils.SQL_QUERY_CERT_POS, sqlArray);
			// 获取商户信息
			String sql = SqlUtils.sqlAddParam(sqlUtil, sqlArray);
			// 查询数据
			retMap = dbDao.queryInfo(sql, RetCode.DB_QUERY_ERROR);
			if (!retMap.isEmpty())
			{
				result = true;
			}

		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("getPosByMerInfo************query error:" + e);
			throw new UseException(RetCode.MERCHANT_WRONG, e);
		}
		StaticMethod.logmark(1);
		return result;
	}

	/**
	 * 查询POS是否绑定证书
	 * 
	 * @param posId
	 * @param cerSn
	 * @return
	 * @throws UseException
	 */
	public boolean updateMerPos(String merCode, String entTime) throws UseException
	{
		StaticMethod.logmark(0);
		log.debug(StaticMethod.locationLog() + "get updateMerPos function.");
		// 返回Map
		Map<String, String> retMap = null;
		boolean result = false;
		String[] sqlArrays = new String[1];
		try
		{
			String sqlArray[] = new String[1];
			sqlArray[0] = merCode;
			sqlArray[1] = entTime;
			// 获取查询语句
			String sqlUtil = SqlUtils.sqlAddParam(SqlUtils.SQL_UPDATE_MER_POS_ENDDATE, sqlArray);
			// 获取商户信息
			String sql = SqlUtils.sqlAddParam(sqlUtil, sqlArray);
			// 查询数据
			sqlArrays[0] = sql;
			dbDao.update(sqlArrays);

		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("getPosByMerInfo************query error:" + e);
			throw new UseException(RetCode.MERCHANT_WRONG, e);
		}
		StaticMethod.logmark(1);
		return result;
	}

	/**
	 * 对用户信息完善
	 * 
	 * @param reqMap
	 * @throws Exception
	 */
	public void updateUserInfo(Map<String, String> reqMap) throws Exception
	{
		StaticMethod.logmark(1);
		log.debug(StaticMethod.locationLog() + "updateUserInfo.");
		this.updateMerchantInfoOrUserInfo(TableCollection.USER_INFO, reqMap);
		StaticMethod.logmark(1);
	}

	/**
	 * 对商户信息完善
	 * 
	 * @param reqMap
	 * @throws Exception
	 */
	public void updateMerchantInfo(Map<String, String> reqMap) throws Exception
	{
		StaticMethod.logmark(1);
		log.debug(StaticMethod.locationLog() + "updateMerchantInfo.");
		this.updateMerchantInfoOrUserInfo(TableCollection.MERCHANT_INFO, reqMap);
		StaticMethod.logmark(1);
	}

	public void updateMerchantInfoOrUserInfo(TableCollection tableName, Map<String, String> reqMap) throws Exception
	{
		Map<String, String> updateMap = new HashMap<String, String>();
		String userName = reqMap.get(ShareFieldUtils.USERNAME);
		Map<String, String> _limitMap = new HashMap<String, String>();
		_limitMap.put(ShareFieldUtils.USERNAME, userName);

		Map<String, String> retMap = this.getDataByRedisOrDatabase(tableName, userName, _limitMap);
		updateMap = TableColumnsUtils.copyProperties(tableName, reqMap);
		// 判断如果数据库有数据，则insert，否则update
		if (retMap == null)
		{
			insert(tableName, updateMap);
		}
		else
		{
			update(tableName, updateMap, _limitMap);
		}
		// 方法内存模块中
		sendRedisUdt(tableName.toString(), userName);
	}

	/**
	 * 获得内存数据，并转换成Map<String, String>类型
	 * 
	 * @param tableName
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getDataByRedisToMap(TableCollection tableName, String field) throws Exception
	{
		Map<String, String> map = null;
		byte[] merbt = null;
		if (StringUtils.isNotBlank(field))
		{
			merbt = RedisUtils.getBHashMap(tableName.toString(), field);
		}
		if (merbt != null)
		{
			map = (Map<String, String>) BytesUtils.byteToObject(merbt);
		}
		return map;
	}

	/**
	 * 获得内存或数据库中数据
	 * 
	 * @param table
	 * @param field
	 * @param limitMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getDataByRedisOrDatabase(TableCollection table, String field, Map<String, String> limitMap) throws Exception
	{
		Map<String, String> redisMap = getDataByRedisOrDatabase(table, field, limitMap, false);
		return redisMap;
	}

	/**
	 * 获取内存或数据库中数据,默认查询数据为空
	 * 
	 * @param tableName
	 * @param field
	 * @param limitMap
	 * @param isUpdate
	 *            如果从数据库中查询到，是否需要加载到内存模块中
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getDataByRedisOrDatabase(TableCollection tableName, String field, Map<String, String> limitMap, boolean isUpdate) throws Exception
	{
		Map<String, String> redisMap = getDataByRedisOrDatabase(tableName, field, limitMap, isUpdate, RetCode.BUSI_HANDLER_ERROR, true);
		return redisMap;
	}

	/**
	 * 获取内存或数据库中数据
	 * 
	 * @param tableName
	 * @param field
	 * @param limitMap
	 * @param isUpdate
	 *            如果从数据库中查询到，是否需要加载到内存模块中
	 * @param retCode
	 *            返回码
	 * @param allowNull
	 *            不允许为空的时候，没有数据则返回返回码
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getDataByRedisOrDatabase(TableCollection tableName, String field, Map<String, String> limitMap, boolean isUpdate, String retCode, boolean allowNull)
			throws Exception
	{
		Map<String, String> redisMap = getDataByRedisToMap(tableName, field);
		if (redisMap == null)
		{
			redisMap = getDataByDatabase(tableName, limitMap, retCode, allowNull);
			if (isUpdate && redisMap != null && !redisMap.isEmpty())
			{
				if (StringUtils.isNotBlank(field))
				{
					sendRedisUdt(tableName.toString(), field);
				}
			}
		}
		return redisMap;
	}

	/**
	 * 查询数据库
	 * 
	 * @param tableName
	 *            表名
	 * @param limitMap
	 *            条件
	 * @param retCode
	 *            不允许为空的时候的返回码
	 * @param allowNull
	 *            是否允许为空
	 * @return
	 * @throws DAOException
	 */
	public Map<String, String> getDataByDatabase(TableCollection tableName, Map<String, String> limitMap, String retCode, boolean allowNull) throws DAOException
	{
		String selectSQL = SqlMothed.getSelectSql(tableName, limitMap);
		if (StringUtils.isBlank(retCode))
		{
			retCode = RetCode.DEFAULT_RETCODE;
		}
		if (allowNull)
		{
			// dbDao.queryInfoAllowNo("select * from LOGON_USER_LOG", "0");
			return dbDao.queryInfoAllowNo(selectSQL, retCode);
		}
		else
		{
			return dbDao.queryInfo(selectSQL, retCode);
		}
	}

	/**
	 * 查询LIST表
	 * 
	 * @param tableName
	 * @param limitMap
	 * @return
	 * @throws DAOException
	 */
	public List<Map<String, String>> findDataByDatabase(TableCollection tableName, Map<String, String> limitMap) throws DAOException
	{
		String selectSQL = SqlMothed.getSelectSql(tableName, limitMap);
		return dbDao.find(selectSQL);
	}

	/**
	 * 根据请求证书类别查询
	 * 
	 * @param cerType
	 *            证书类别
	 * @param param
	 *            (POS、服务方)
	 * @return
	 * @throws DAOException
	 */
	public Map<String, String> reqCerList(String cerType, String param) throws DAOException
	{
		StaticMethod.logmark(0);
		log.debug(StaticMethod.locationLog() + "reqCerList.");
		Map<String, String> retMap = null;
		byte[] merbt = RedisUtils.getBHashMap(cerType, param);
		Map<String, String> _limitMap = new HashMap<String, String>();
		_limitMap.put(ShareFieldUtils.USERNAME, param);
		if (merbt != null)
		{
			retMap = (Map<String, String>) BytesUtils.byteToObject(merbt);
		}
		else
		{
			String selectSQL = SqlMothed.getSelectSql(TableCollection.CER_INFO, _limitMap);
			retMap = dbDao.queryInfoAllowNo(selectSQL, RetCode.BUSI_HANDLER_ERROR);
		}
		StaticMethod.logmark(1);
		return retMap;
	}
}
