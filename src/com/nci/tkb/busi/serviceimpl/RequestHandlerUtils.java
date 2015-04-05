package com.nci.tkb.busi.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.nci.tkb.busi.exception.UseException;
import com.nci.tkb.busi.utils.CmdnoUtils;
import com.nci.tkb.busi.utils.RetCode;
import com.nci.tkb.busi.utils.ShareFieldUtils;
import com.nci.tkb.busi.utils.StaticMethod;

public class RequestHandlerUtils
{
	private static Logger log = Logger.getLogger(RequestHandlerUtils.class.getName());
	private static Map<String, String> requestColums = new HashMap<String, String>();
	private static final String MUST = "M";
	private static final String SELECT = "S";
	static
	{

		requestColums.put("defaultPrefixM", "REQ_CODE,ENCRY_FLAG,UUID,USERNAME,ENTITY_OBJ,REQ_CODE,APP_MODE,PROTOCOL_VER,IS_TEST,IS_ENCRYPT,CER_SN,MAC_NO,DEV_VER");
		requestColums.put("defaultPrefixS", "MD5_MAC,SERVICE_DATA,POSID,USERNAME,LAT,LON,DATA");
		// 注册 a. 录入手机号（2000）
		requestColums.put(CmdnoUtils.REG_INPUT_MOBILE_ACCEPT + MUST, "USERNAME,PASSWORD,DESKEY,SIGNATURE");

		// 注册b. 验证码（2010）
		requestColums.put(CmdnoUtils.REG_CHECK_CODE_ACCEPT + MUST, "USERNAME,CAPTCHA");
		requestColums.put(CmdnoUtils.REG_CHECK_CODE_ACCEPT + SELECT, "SIGNATURE");
		// c. 用户信息完善（2020）
		requestColums.put(CmdnoUtils.REG_ADD_USERINFO_ACCEPT + MUST, "USERNAME");
		requestColums.put(CmdnoUtils.REG_ADD_USERINFO_ACCEPT + SELECT, "NAME,NICKNAME,PHONE,IDENTI_PHOTO,IDENTI_NO,SEX,AGE,ADDRESS,EMAIL,PERSON_PHOTO,GRADE,DES,SIGNATURE");
		// d. 商户信息完善（2030）
		requestColums.put(CmdnoUtils.REG_ADD_MERINFO_RETURN + MUST, "USERNAME");
		requestColums
				.put(
						CmdnoUtils.REG_ADD_MERINFO_RETURN + SELECT,
						"MERCODE,MERNAME_SHORT,MERNAME_FULL,LEGAL_PERSON,NICKNAME,BLANK_TYPE,BLANK_ACCOUNT_NO,BLANK_ACCOUNT_NAME,PHONE,FIXEDPHONE,IDENTI_PHOTO,IDENTI_NO,SEX,AGE,ADDRESS,EMAIL,PERSON_PHOTO,OGNZCODE_PHOTO,OGNZCODE_SN,TAXREG_PHOTO,TAXREG_SN,B_LICENSE_PHOTO,B_LICENSE _SN,MER_GRADE,DES");
		// 2. 用户登录（2100）
		requestColums.put(CmdnoUtils.USER_LOGON_ACCEPT + MUST, "USERNAME,PASSWORD,DESKEY,SIGNATURE");

		// 3. 商户登录（2200）
		requestColums.put(CmdnoUtils.MER_LOGON_ACCEPT + MUST, "USERNAME,PASSWORD");

		// 4. 商户交易初始化 初始化（2211）
		requestColums.put(CmdnoUtils.MER_TRAN_INIT_ACCEPT + MUST, "USERNAME,POSID,SIGNATURE");

		// 验证码确认（2212）
		requestColums.put(CmdnoUtils.MER_CHECK_CODE_ACCEPT + MUST, "DEV_VER,CAPTCHA,USERNAME,POSID,SIGNATURE");

		// 5. 子账号登录 a. 已初始化（2300）
		requestColums.put(CmdnoUtils.ACCOUNT_LOGON_ACCEPT + MUST, "USERNAME,PASSWORD,DESKEY,POSID,SIGNATURE");

		// RESP_CODE：登录成功未初始化，且为MAC_NO不存在,返回码为2311
		requestColums.put(CmdnoUtils.ACCOUNT_LOGON_INIT_ACCEPT + MUST, "USERNAME,PASSWORD,SIGNATURE");

		// 子账号绑定pos初始化（选择POSID）（2310）
		requestColums.put(CmdnoUtils.ACCOUNT_LOGON_INIT_ACCEPT + MUST, "USERNAME");

		// 子账号绑定pos验证码确认（2311）
		requestColums.put(CmdnoUtils.ACCOUNT_CHECK_CODE_ACCEPT + MUST, "USERNAME,CAPTCHA");
		requestColums.put(CmdnoUtils.ACCOUNT_CHECK_CODE_ACCEPT + SELECT, "POSID,SIGNATURE");

		// a. 操作员录入（2401）
		requestColums.put(CmdnoUtils.MER_ADD_OPTINFO_ACCEPT + MUST, "CHILD_ROLE,MER_CODE,USERNAME");
		requestColums.put(CmdnoUtils.MER_ADD_OPTINFO_ACCEPT + SELECT, "PASSWORD,POSID,NAME,PHONE,EMAIL");

		// b. POS机信息完善
		requestColums.put(CmdnoUtils.MER_ADD_POSINFO_ACCEPT + MUST, "POSID");
		requestColums.put(CmdnoUtils.MER_ADD_POSINFO_ACCEPT + SELECT, "MERCODE,POS_STATE,DES");

		// 7. 修改密码（2500）
		requestColums.put(CmdnoUtils.EDIT_PASSWORD_ACCEPT + MUST, "USERNAME,PASSWORD,PASSWORD_NEW");

		// 16. 功能记忆下载请求（3200）
		requestColums.put(CmdnoUtils.ZONE_ADD_NODE_ACCEPT + MUST, "TERM_ID,FLAG");

		// 15. 功能记忆绑定请求（3100）
		requestColums.put(CmdnoUtils.TYPE_ADD_NODE_ACCEPT + MUST, "TERM_ID,FLAG,SERVICE_CODE");
		// 18. 找回密码（仅限用户和商户）c. 第一步（2701）
		requestColums.put(CmdnoUtils.FIND_PASSWORD_ACCEPT + MUST, "USERNAME");
		// 18. 找回密码（仅限用户和商户）d. 第二步（2702）
		requestColums.put(CmdnoUtils.CHECK_FIND_PASSWORD_ACCEPT + MUST, "USERNAME,CAPTCHA,PASSWORD");
	}

	private static final Map<String, String> getRequestColums(String requestCode)
	{
		Map<String, String> _rep = new HashMap<String, String>();
		String m = requestColums.get("defaultPrefixM");
		String _m = requestColums.get(requestCode + MUST);
		if (StringUtils.isNotBlank(_m))
		{
			m = m + "," + _m;
		}
		String s = requestColums.get("defaultPrefixS");
		String _s = requestColums.get(requestCode + SELECT);
		if (StringUtils.isNotBlank(_s))
		{
			s = s + "," + _s;
		}
		_rep.put(MUST, m);
		_rep.put(SELECT, s);
		return _rep;
	}

	private static final Map<String, String> dispache(String requestCode)
	{
		Map<String, String> bfcMap = new HashMap<String, String>();
		Map<String, String> _maps = getRequestColums(requestCode);
		if (_maps != null)
		{

			String[] s_columns = _maps.get(SELECT).split(",");
			for (int i = 0; i < s_columns.length; i++)
			{
				bfcMap.put(s_columns[i], SELECT);
			}

			String[] m_columns = _maps.get(MUST).split(",");
			for (int i = 0; i < m_columns.length; i++)
			{
				bfcMap.put(m_columns[i], MUST);
			}
		}
		return bfcMap;
	}

	public static final Map<String, String> getRequestValue(String requestCode, Map<String, String> response) throws UseException
	{
		Map<String, String> retMap = new HashMap<String, String>();
		Map<String, String> _maps = dispache(requestCode);
		for (Map.Entry<String, String> entry : _maps.entrySet())
		{
			String _key = entry.getKey();
			String _value = entry.getValue();
			String value = response.get(_key);
			if (MUST.equals(_value) && StringUtils.isBlank(value))
			{
				log.info(StaticMethod.getTraceInfo());
				log.error("****************MISSING MUST CODE:" + _key);
				throw new UseException(RetCode.RETCODE_MISSING, "MISSING MUST CODE" + _key);
			}
			else
			{
				retMap.put(_key, value);
			}
		}
		return retMap;
	}
}
