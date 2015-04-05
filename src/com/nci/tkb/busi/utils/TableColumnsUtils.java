package com.nci.tkb.busi.utils;

import java.util.HashMap;
import java.util.Map;

public class TableColumnsUtils
{
	private static Map<String, String> tableColums = new HashMap<String, String>();
	static
	{
		tableColums.put(TableCollection.LOGON_USER.toString(), "ID,USERNAME,PASSWORD,LAST_LOGONTIME,CREATE_TIME,STATUS,STATE");
		tableColums.put(TableCollection.ROLE_USER.toString(), "ID,USER_ID,ROLE_ID,CREATE_TIME,EDIT_TIME,STATUS");
		tableColums.put(TableCollection.USER_INFO.toString(),
				"ID,MER_CODE,USERNAME,NICKNAME,NAME,PHONE,IDENTI_NO,SEX,AGE,ADDRESS,EMAIL,PHOTO_NAME,PHOTO_PATH,REG_TIME,LAST_LOGON,LONGITUDE,LATITUDE,GRADE,STATUS");
		tableColums
				.put(
						TableCollection.MERCHANT_INFO.toString(),
						"ID,USERNAME,MER_CODE,INDUSTRY_CODE,ZONE_CODE,SHORT_NAME,FULL_NAME,LEGAL_PERSON,LINKMAN,PHONE,FAX,EMAIL,MOBILE,OTHER_LINKMAN,ADDRESS,POST_CODE,BANK_NAME,BANK_NO,BANK_CODE,B_LICENSE_SN,B_LICENSE_PHOTO,B_LICENSE _NAME,TAXREG_SN,TAXREG_PHOTO,TAXREG_NAME,OGNZCODE_PHOTO,OGNZCODE_SN,OGNZCODE_NAME,SYS_TIME,GRADE,STATUS");
		tableColums.put(TableCollection.LOGON_USER_LOG.toString(), "ID,USERNAME,POSID,MAC_OR_IP,CREATE_TIME,LOGON_TYPE,STATUS");
		tableColums
				.put(TableCollection.MER_POS.toString(),
						"ID,POSID,CER_SN,MER_CODE,BUSI_FLAG,BUSI_TYPE,CER_ALIAS,START_DATE,END_DATE,CER_STATE,USERID,CONSUME_LIMIT,CHARGE_LIMIT,MAC_NO,DEV_VER,LAT,LON,IS_BIND,CER_MONTHS,STATUS");

		tableColums.put(TableCollection.USER_REMEMBER_INFO.toString(), "ID,TERM_ID,FLAG,SERVICE_CODE,CREATE_TIME,CREATE_USER,EDIT_USER,EDIT_TIME,MEMO,BIND_FLAG,STATUS");
		tableColums.put(TableCollection.SERVICE_TYPE.toString(), "ID,FID,S_TYPE_CODE,S_TYPE_NAME,CREATE_TIME,CREATE_USER,EDIT_USER,EDIT_TIME,IS_STATE,MEMO,STATUS");
	}

	public static final String getTableColums(String tableName)
	{
		return tableColums.get(tableName);
	}

	/**
	 * 获取到table column 并封装成map
	 * 
	 * @param tableName
	 * @return
	 */
	public static Map<String, String> getTableColumnsMap(TableCollection tableName)
	{
		Map<String, String> bfcMap = new HashMap<String, String>();
		String _colums = getTableColums(tableName.toString());
		if (_colums != null)
		{
			String[] bf_columns = _colums.split(",");
			for (int i = 0; i < bf_columns.length; i++)
			{
				bfcMap.put(bf_columns[i], bf_columns[i]);
			}
		}
		return bfcMap;
	}

	/**
	 * 复制属性值
	 * 
	 * @param tableName
	 * @param source
	 * @return
	 */
	public static Map<String, String> copyProperties(TableCollection tableName, Map<String, String> source)
	{
		Map<String, String> target = new HashMap<String, String>();
		Map<String, String> tableColumns = getTableColumnsMap(tableName);
		for (Map.Entry<String, String> entry : tableColumns.entrySet())
		{
			if (source.containsKey(entry.getKey()))
			{
				target.put(entry.getKey(), source.get(entry.getKey()));
			}
		}
		return target;
	}

}
