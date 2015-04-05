package com.nci.tkb.busi.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.nci.tkb.busi.exception.BSVException;

public class SqlMothed
{
	// log4j
	static Logger log = Logger.getLogger(SqlMothed.class.getName());

	/**
	 * 业务流水表字段
	 */
	public static Map<String, String> BFC_MAP = getBusiFlowColumns();

	public static Map<String, String> CPM_MAP = getCerPayMentColumns();

	/**
	 * 业务列名数组
	 */
	public static Map<String, String> getBusiFlowColumns()
	{
		Map<String, String> bfcMap = new HashMap<String, String>();

		String[] bf_columns =
		{
				"ID", "CMD_NO", "CARD_ID", "CARD_NO", "PAY_FLOW_ID", "BUSI_CODE", "PSAM_NO", "CARD_TYPE", "MERCHANT_CODE", "STORE_CODE", "STATION_CODE", "BATCH_NO", "TICKET_NO",
				"PAY_MONEY", "STATION_FLOW_ID", "EPOL_FLOW_ID", "COMM_SEQ_ID", "THIRD_BUSI_FLOW_ID", "BEFORE_BALANCE", "AFTER_BALANCE", "LAST_MONEY", "LAST_BUSI_TYPE",
				"STATION_DATE", "STATION_TIME", "OPERATOR_ID", "BUSI_TYPE", "KEY_VER", "ARITHMETIC", "PSEUD_RANDOM", "TAC", "MAC1", "IS_COMPARISON", "COMPARISON_DATE", "TAC_TIME",
				"SYS_TIME", "IS_BATCH_COMPLETE", "PAY_RETCODE", "REVER_FLAG", "BUSI_STATUS", "CARD_SN", "PAY_DATE", "PAY_TIME", "SVR_DATE", "SVR_TIME"
		};

		for (int i = 0; i < bf_columns.length; i++)
		{
			bfcMap.put(bf_columns[i], bf_columns[i]);
		}

		return bfcMap;
	}

	/**
	 * 
	 */
	public static Map<String, String> getCerPayMentColumns()
	{
		Map<String, String> bfcMap = new HashMap<String, String>();

		String[] bf_columns =
		{
				"ID", "CARDNO", "ORDERID", "TRANS_TIME", "POS_COUNT", "PAY_MONEY", "POSID", "MONTHS", "PAYFEE", "CER_SN", "CER_START", "CER_END"
		};

		for (int i = 0; i < bf_columns.length; i++)
		{
			bfcMap.put(bf_columns[i], bf_columns[i]);
		}

		return bfcMap;
	}

	/**
	 * 添加证书纳费信息
	 */
	public static String getAddCerPayMentSql(Map<String, String> map, String table) throws BSVException
	{
		// 列
		StringBuffer columnsbf = new StringBuffer();
		// 值
		StringBuffer valuebf = new StringBuffer();

		// 定义返回sql
		String sql = "INSERT INTO " + table + "(XXSQL0) VALUES(XXSQL1)";
		// map
		if (map != null && !map.isEmpty())
		{
			// 根据传入参数获取要插入的列和值得数据
			for (Map.Entry<String, String> entry : map.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				// 判断key数据是否相等,并且value有效
				if (CPM_MAP.containsKey(key) && null != value && !"".equals(value))
				{
					columnsbf.append(",").append(key);
					valuebf.append(",").append("'").append(value).append("'");
				}
			}
			// 替换列名
			sql = sql.replace("XXSQL0", new String(columnsbf));
			// 替换值
			sql = sql.replace("XXSQL1", new String(valuebf));
			// 替换“(,”字符
			sql = sql.replace("(,", "(");
		}

		return sql;
	}

	/**
	 * 查询POSID是否在该商户下
	 */
	public static String getParamSqlSql(String posid, String merCode)
	{
		// 定义返回SQL
		String sql = SqlUtils.SQL_QUERY_MER_POS;

		// 替换列名
		sql = sql.replace("XXSQL0", posid);
		// 替换值
		sql = sql.replace("XXSQL1", merCode);

		return sql;
	}

	/**
	 * 添加支付流水sql生成
	 */
	public static String getAddBusiFlowSql(Map<String, String> map) throws BSVException
	{
		// 列
		StringBuffer columnsbf = new StringBuffer();
		// 值
		StringBuffer valuebf = new StringBuffer();

		// 定义返回sql
		String sql = "INSERT INTO BUSI_FLOW(XXSQL0,BUSI_STATUS) VALUES(XXSQL1,'10')";
		// map
		if (map != null && !map.isEmpty())
		{
			// 根据传入参数获取要插入的列和值得数据
			for (Map.Entry<String, String> entry : map.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				// 判断key数据是否相等,并且value有效
				if (BFC_MAP.containsKey(key) && null != value && !"".equals(value))
				{
					columnsbf.append(",").append(key);
					valuebf.append(",").append("'").append(value).append("'");
				}
			}
			// 替换列名
			sql = sql.replace("XXSQL0", new String(columnsbf));
			// 替换值
			sql = sql.replace("XXSQL1", new String(valuebf));
			// 替换“(,”字符
			sql = sql.replace("(,", "(");
		}

		return sql;
	}

	/**
	 * 添加支付流水sql生成
	 */
	public static String getAddBusiFlowConsumeSql(Map<String, String> map) throws BSVException
	{
		// 列
		StringBuffer columnsbf = new StringBuffer();
		// 值
		StringBuffer valuebf = new StringBuffer();

		// 定义返回sql
		String sql = "INSERT INTO BUSI_FLOW_CONSUME(XXSQL0,BUSI_STATUS) VALUES(XXSQL1,'10')";
		// map
		if (map != null && !map.isEmpty())
		{
			// 根据传入参数获取要插入的列和值得数据
			for (Map.Entry<String, String> entry : map.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				// 判断key数据是否相等,并且value有效
				if (BFC_MAP.containsKey(key) && null != value && !"".equals(value))
				{
					columnsbf.append(",").append(key);
					valuebf.append(",").append("'").append(value).append("'");
				}
			}
			// 替换列名
			sql = sql.replace("XXSQL0", new String(columnsbf));
			// 替换值
			sql = sql.replace("XXSQL1", new String(valuebf));
			// 替换“(,”字符
			sql = sql.replace("(,", "(");
		}

		return sql;
	}

	/**
	 * 查询消费是否重复
	 */
	public static String getBusiFlowReaptSql(String psamNo, String batchNo, String ticketNo) throws BSVException
	{

		// 定义返回sql
		String sql = "SELECT ID FROM BUSI_FLOW_CONSUME T WHERE t.PSAM_NO='XXSQL0' AND T.BATCH_NO='XXSQL1' AND T.TICKET_NO='XXSQL2' AND T.IS_BATCH_COMPLETE='0' AND (T.BUSI_CODE='91000001' OR T.BUSI_CODE='92000001')";

		// 替换列名
		sql = sql.replace("XXSQL0", psamNo);
		// 替换值
		sql = sql.replace("XXSQL1", batchNo);
		// 替换值
		sql = sql.replace("XXSQL2", ticketNo);

		return sql;
	}

	/**
	 * 更新业务流水
	 */
	public static String updateBusiFlowSql(Map<String, String> map) throws BSVException
	{
		// 列
		StringBuffer columnsbf = new StringBuffer();

		String id = map.get("ID");

		// 定义返回sql
		String sql = "UPDATE BUSI_FLOW SET #XXSQL0,TAC_TIME=sysdate WHERE ID=XXSQL1 ";
		// map
		if (map != null && !map.isEmpty())
		{
			// 根据传入参数获取要插入的列和值得数据
			for (Map.Entry<String, String> entry : map.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				// 判断key数据是否相等,并且value有效
				if (BFC_MAP.containsKey(key) && null != value && !"".equals(value) && !"ID".equals(value))
				{
					columnsbf.append(",").append(key).append("=").append("'" + value + "'");
				}
			}
			// 替换列名
			sql = sql.replace("XXSQL0", new String(columnsbf));
			// 替换“(,”字符
			sql = sql.replace("#,", "");
			// 替换列名
			sql = sql.replace("XXSQL1", id);
		}

		return sql;
	}

	/**
	 * 更新支付流水sql生成,成功状态
	 */
	public static String udtpayFlowToRvsSql(String payFlowId) throws BSVException
	{
		// 定义返回sql
		String sql = null;

		String sqlArray[] = new String[1];
		sqlArray[0] = payFlowId;
		try
		{
			// 获取查询语句
			// sql = SqlUtils.sqlAddParam(SqlUtils.RVS_PAYFLOW_TO_RVS,
			// sqlArray);
		}
		catch (Exception e)
		{
			log.error("BaseService********getUdtpayFlowSql,生成sql失败:", e);
			// throw new
			// BSVException(String.valueOf(RetCode.UDT_PAYFLOW_TO_RVS), e);
		}

		return sql;
	}

	/**
	 * 更新支付流水sql生成,失败状态
	 */
	public static String getUpdatepFlowFailSql(String payFlowId) throws BSVException
	{
		// 定义返回sql
		String sql = null;

		/*
		 * String sqlArray[] = new String[1]; sqlArray[0] = payFlowId; try {
		 * //获取查询语句 sql = SqlUtil.sqlAddParam(SqlUtil.SQL_PAY_FLOW_UPDATE_Fail,
		 * sqlArray); } catch (Exception e) {
		 * log.error("BaseService********getUpdatepFlowFailSql,生成sql失败:", e);
		 * throw new BSVException(String.valueOf(RetCode.PAYMENT_ERROR), e); }
		 */

		return sql;
	}

	/**
	 * 更新业务流水为批次完成状态
	 */
	public static String getBatchUploadSql(String psamNo, String batchNo)
	{
		// 定义返回sql
		String sql = null;
		// SqlUtil.SQL_UPDATE_BATCHNO;

		// 替换列名
		sql = sql.replace("XXSQL0", psamNo);
		// 替换值
		sql = sql.replace("XXSQL1", batchNo);

		return sql;
	}

	/**
	 * 更新卡的最后绑定充值时间和输入错误密码次数
	 */
	public static String getUdtCardInfoSql(String cardNo, String errorNum)
	{
		// 定义返回sql
		String sql = null;
		// SqlUtil.SQL_UPDATE_CARD_INFO;

		// 替换列名
		sql = sql.replace("XXSQL0", errorNum);
		// 替换值
		sql = sql.replace("XXSQL1", cardNo);

		return sql;
	}

	/**
	 * 更新业务流水为批次完成状态
	 */
	public static String getAllBatchMoneySql(String psamNo, String batchNo)
	{
		// 定义返回sql
		String sql = "select COUNT(T.ID) ALLNUM,SUM(T.PAY_MONEY) ALLMONEY from BUSI_FLOW T WHERE T.PSAM_NO='XXSQL0' AND T.BATCH_NO='XXSQL1' AND T.IS_BATCH_COMPLETE='0' AND T.BUSI_STATUS='10'";

		// 替换列名
		sql = sql.replace("XXSQL0", psamNo);
		// 替换值
		sql = sql.replace("XXSQL1", batchNo);

		return sql;
	}

	/**
	 * 更新卡状态为激活状态
	 */
	public static String getUptCardActivateSql(String cardNo)
	{
		// 定义返回sql
		String sql = null;
		// SqlUtil.sQL_UPDATE_CARD_ACTIVATES;
		// 替换列名
		sql = sql.replace("XXSQL0", cardNo);

		return sql;
	}

	/**
	 * 添加信息（公共方法）
	 * 
	 * @param table
	 * @param tabelColumnMap
	 *            提供Map<String, String>类型<br>
	 *            例如 : tabelColumnMap.put("tablecolumn","tablecolumn") </br>表字段
	 * @param insertMap
	 * @return
	 * @throws BSVException
	 */
	public static String getInsertSql(TableCollection table, Map<String, String> tabelColumnMap, Map<String, String> insertMap) throws BSVException
	{
		// 列
		StringBuffer columnsbf = new StringBuffer();
		// 值
		StringBuffer valuebf = new StringBuffer();

		// 定义返回sql
		String sql = "INSERT INTO " + table + "(XXSQL0) VALUES(XXSQL1)";
		// map
		if (insertMap != null && !insertMap.isEmpty())
		{
			// 根据传入参数获取要插入的列和值得数据
			for (Map.Entry<String, String> entry : insertMap.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				// 判断key数据是否相等,并且value有效
				if (tabelColumnMap != null && tabelColumnMap.containsKey(key))
				{
					columnsbf.append(",").append(key);
					if (ShareFieldUtils.CREATE_TIME.equals(key) || ShareFieldUtils.EDIT_TIME.equals(key) || ShareFieldUtils.ID.equals(key))
					{
						valuebf.append(",").append(value);
					}
					else
					{
						valuebf.append(",").append("'").append(value).append("'");
					}

				}
			}
			if (!insertMap.containsKey(ShareFieldUtils.ID) && tabelColumnMap.containsKey(ShareFieldUtils.ID))
			{
				columnsbf.append(",").append(ShareFieldUtils.ID);
				valuebf.append(",SEQ_" + table + ".Nextval");
			}
			if (!insertMap.containsKey(ShareFieldUtils.CREATE_TIME) && tabelColumnMap.containsKey(ShareFieldUtils.CREATE_TIME))
			{
				columnsbf.append(",").append(ShareFieldUtils.CREATE_TIME);
				valuebf.append(",sysdate");
			}
			if (!insertMap.containsKey(ShareFieldUtils.EDIT_TIME) && tabelColumnMap.containsKey(ShareFieldUtils.EDIT_TIME))
			{
				columnsbf.append(",").append(ShareFieldUtils.EDIT_TIME);
				valuebf.append(",sysdate");
			}
			// 替换列名
			sql = sql.replace("XXSQL0", new String(columnsbf));
			// 替换值
			sql = sql.replace("XXSQL1", new String(valuebf));
			// 替换“(,”字符
			sql = sql.replace("(,", "(");
		}

		return sql;
	}

	/**
	 * 添加信息（公共方法）
	 * 
	 * @param tableName
	 *            更新表格
	 * @param insertMap
	 *            更新字段
	 * @return 返回拼装sql语句
	 * @throws BSVException
	 */

	public static String getInsertSql(TableCollection tableName, Map<String, String> insertMap) throws BSVException
	{
		Map<String, String> tabelColumnMap = TableColumnsUtils.getTableColumnsMap(tableName);
		return getInsertSql(tableName, tabelColumnMap, insertMap);
	}

	/**
	 * 获得更新sql,默认按ID更新
	 * 
	 * @param table
	 *            表名，列表在TableUtils中
	 * @param updateMap
	 *            更新字段
	 * @return
	 * @throws BSVException
	 */

	public static String getUpdateSqlById(TableCollection table, Map<String, String> updateMap) throws BSVException
	{
		Map<String, String> tabelColumnMap = TableColumnsUtils.getTableColumnsMap(table);
		return getUpdateSqlById(table, tabelColumnMap, updateMap);
	}

	/**
	 * 获得更新sql
	 * 
	 * @param table
	 *            表名，列表在TableUtils中
	 * @param tabelColumnMap
	 *            table的字段集合
	 * @param updateMap
	 *            更新字段
	 * @return
	 * @throws BSVException
	 */
	public static String getUpdateSqlById(TableCollection table, Map<String, String> tabelColumnMap, Map<String, String> updateMap) throws BSVException
	{
		// 列
		StringBuffer columnsbf = new StringBuffer();

		String id = updateMap.get("ID");

		// 定义返回sql
		String sql = "UPDATE " + table + " SET #XXSQL0 WHERE ID=XXSQL1 ";
		// map
		if (updateMap != null && !updateMap.isEmpty())
		{
			// 根据传入参数获取要插入的列和值得数据
			for (Map.Entry<String, String> entry : updateMap.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				// 判断key数据是否相等,并且value有效
				if (tabelColumnMap.containsKey(key) && null != value && !"".equals(value) && !"ID".equals(key))
				{
					columnsbf.append(",").append(key).append("=").append("'" + value + "'");
				}
			}
			if (!updateMap.containsKey(ShareFieldUtils.EDIT_TIME) && tabelColumnMap.containsKey(ShareFieldUtils.EDIT_TIME))
			{
				columnsbf.append(",").append(ShareFieldUtils.EDIT_TIME);
				columnsbf.append("=sysdate");
			}
			if (!updateMap.containsKey(ShareFieldUtils.LAST_LOGONTIME) && tabelColumnMap.containsKey(ShareFieldUtils.LAST_LOGONTIME))
			{
				columnsbf.append(",").append(ShareFieldUtils.LAST_LOGONTIME);
				columnsbf.append("=sysdate");
			}
			// 替换列名
			sql = sql.replace("XXSQL0", new String(columnsbf));
			// 替换“(,”字符
			sql = sql.replace("#,", "");
			// 替换列名
			sql = sql.replace("XXSQL1", id);
		}
		return sql;
	}

	/**
	 * 获得更新sql
	 * 
	 * @param table
	 *            列表在TableUtils中
	 * @param tabelColumnMap
	 *            table的字段集合
	 * @param updateMap
	 *            更新字段
	 * @param limitMap
	 *            更新中的限制条件,并联
	 * @return
	 * @throws BSVException
	 */
	public static String getUpdateSql(TableCollection table, Map<String, String> tabelColumnMap, Map<String, String> updateMap, Map<String, String> limitMap) throws BSVException
	{
		// 列
		StringBuffer columnsbflimit = new StringBuffer();
		// 列
		StringBuffer columnsbf = new StringBuffer();

		// 定义返回sql
		String sql = "UPDATE " + table + " SET #XXSQL0 WHERE #XXSQL1 ";
		if (limitMap == null || limitMap.isEmpty())
		{
			log.error("limitMap is empty");
			throw new BSVException("limitMap is empty");
		}
		else
		{
			// 根据传入参数获取要插入的列和值得数据
			for (Map.Entry<String, String> entry : limitMap.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				// 判断key数据是否相等,并且value有效
				if (tabelColumnMap.containsKey(key) && null != value && !"".equals(value))
				{
					columnsbflimit.append(" and ").append(key).append("=").append("'" + value + "'");
				}
			}

			// 替换列名
			sql = sql.replace("XXSQL1", new String(columnsbflimit));
			sql = sql.replace("# and", "");
		}

		// map
		if (updateMap != null && !updateMap.isEmpty())
		{
			// 根据传入参数获取要插入的列和值得数据
			for (Map.Entry<String, String> entry : updateMap.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				// 判断key数据是否相等,并且value有效
				if (tabelColumnMap.containsKey(key) && null != value && !"".equals(value) && !"ID".equals(key))
				{
					columnsbf.append(",").append(key).append("=").append("'" + value + "'");
				}
			}
			if (tabelColumnMap.containsKey(ShareFieldUtils.EDIT_TIME))
			{
				columnsbf.append(",").append(ShareFieldUtils.EDIT_TIME);
				columnsbf.append("=sysdate");
			}
			if (tabelColumnMap.containsKey(ShareFieldUtils.LAST_LOGONTIME))
			{
				columnsbf.append(",").append(ShareFieldUtils.LAST_LOGONTIME);
				columnsbf.append("=sysdate");
			}
			// 替换列名
			sql = sql.replace("XXSQL0", new String(columnsbf));
			// 替换“(,”字符
			sql = sql.replace("#,", "");
		}
		return sql;
	}

	/**
	 * 
	 * @param table
	 *            列表在TableUtils中
	 * @param updateMap
	 *            table的字段集合
	 * @param limitMap
	 *            更新中的限制条件,并联
	 * @return
	 * @throws BSVException
	 */
	public static String getUpdateSql(TableCollection table, Map<String, String> updateMap, Map<String, String> limitMap) throws BSVException
	{
		Map<String, String> tabelColumnMap = TableColumnsUtils.getTableColumnsMap(table);
		return getUpdateSql(table, tabelColumnMap, updateMap, limitMap);
	}

	public static String getSelectSql(TableCollection tableName, Map<String, String> limit)
	{
		Map<String, String> tabelColumnMap = TableColumnsUtils.getTableColumnsMap(tableName);
		return getSelectSql(tableName, limit, tabelColumnMap);
	}

	public static String getSelectSql(TableCollection tableName, Map<String, String> limit, Map<String, String> tabelColumnMap)
	{
		// 定义返回sql
		String sql = "SELECT * FROM " + tableName;
		StringBuffer _limit = new StringBuffer();
		if (!limit.isEmpty())
		{
			for (Map.Entry<String, String> entry : tabelColumnMap.entrySet())
			{
				String _key = entry.getKey();
				if (limit.containsKey(_key))
				{
					String _value = limit.get(_key);
					if (_limit.length() == 0)
					{
						_limit.append(" where ");
					}
					_limit.append(_key);
					_limit.append("='");
					_limit.append(_value);
					_limit.append("' and ");
				}
			}
			// _limit.subSequence(0, _limit.lastIndexOf(" and "));
		}
		sql = sql + _limit.substring(0, _limit.lastIndexOf(" and "));
		return sql;
	}
}
