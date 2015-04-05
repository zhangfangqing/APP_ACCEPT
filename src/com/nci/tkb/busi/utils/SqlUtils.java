package com.nci.tkb.busi.utils;

/**
 * sql语句类
 * @author LYP
 * @version 1.0
 * @Date 2014-02-20
 */
public class SqlUtils 
{
	/**
	 * 系统配置信息
	 */
	public static String SYS_CONFIG_INFO = "SELECT T.CONFIG_KEY,T.CONFIG_VALUE FROM SYS_CONFIG_INFO T";
	
	/**
	 * 系统表键
	 */
	public static String CONFIG_KEY = "CONFIG_KEY" ;
	
	/**
	 * 系统表值
	 */
	public static String CONFIG_VALUE = "CONFIG_VALUE" ;	
	
	/**
	 * 根据证书序列号查询商户POS
	 */
	public static String SQL_MERPOS_CERSN_VALIDATE = "SELECT * FROM MER_POS";
	
	/**
	 * 根据商户ID查询商户是否合法
	 */
	public static String SQL_MERCHANT_VALIDATE = "SELECT * FROM MERCHANT_INFO M WHERE M.STATUS = 'Y' AND M.MER_CODE = 'XXSQL0'";
	
	/**
	 * 根据用户名查询是否存在该用户
	 */
	public static String SQL_QUERY_LOGON_USER="SELECT * FROM LOGON_USER WHERE USERNAME='XXSQL0'";
	
	
	
	/**
	 * 根据证书序列号查询证书信息
	 */
	public static String SQL_QUERY_CER_INFO = "SELECT * FROM CER_INFO WHERE CER_SN = 'XXSQL0'";
	
	/**
	 * 根据证书库名查询证书库信息
	 */
	public static String SQL_QUERY_KEYSTORE_INFO = "SELECT * FROM KEYSTORE_INFO WHERE STORE_NAME = 'XXSQL0'";
	
	/**
	 * 原始查询语句中添加参数
	 */
	public static String sqlAddParam(String sql, String param[])
	{
		String retSql = null;
		
		if(null != sql && null != param)
		{
			for(int i=param.length-1; i>=0; i--)
			{
				sql = sql.replace("XXSQL"+i, param[i]);
			}
			retSql = sql;
		}
		
		return retSql;
	}
	
	/**
	 * 查询POS是否绑定商户
	 */
	public static String SQL_QUERY_MER_POS = "SELECT * FROM MER_POS M WHERE M.POSID = 'XXSQL0' and M.MER_CODE = 'XXSQL1'";
	
	/**
	 * 查询POS是否绑定证书
	 */
	public static String SQL_QUERY_CERT_POS = "SELECT * FROM MER_POS M WHERE M.POSID = 'XXSQL0' and M.CER_SN = 'XXSQL1'";
	
	/**
	 * 更新POS结束有效期
	 */
	public static String SQL_UPDATE_MER_POS_ENDDATE = "UPDATE MER_POS M SET M.END_DATE='XXSQL0' WHERE M.MER_CODE ='XXSQL1' M.STATUS='Y'";
	
	/**
	 * 根据服务方代码查询证书
	 */
	public static String SQL_QUERY_CER_INFO_SERVICE_CODE = "SELECT * FROM CER_INFO C WHERE C.SERVICE_CODE = 'XXSQL0' C.STATUS = 'Y'";
	
	/**
	* 根据用户名和商户编码查询操作员是否合法
	*/
	public static String SQL_OPERATOR_VALIDATE = "SELECT * FROM USER_INFO U WHERE U.STATUS = 'Y' AND U.MER_CODE = 'XXSQL1', AND U.USERNAME = 'XXSQL0'";
	
}
