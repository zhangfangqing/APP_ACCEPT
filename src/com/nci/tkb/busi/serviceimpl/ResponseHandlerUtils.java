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

public class ResponseHandlerUtils
{
	private static Logger log = Logger.getLogger(ResponseHandlerUtils.class.getName());
	private static Map<String, String> responseColums = new HashMap<String, String>();
	private static final String MUST = "M";
	private static final String SELECT = "S";
	static
	{
		responseColums.put("defaultPrefixM", "REQ_CODE,RESP_CODE,ENCRY_FLAG,REQ_CODE,RESP_CODE,APP_VER,SYS_PARAM,UUID,IS_ENCRYPT");
		responseColums.put("defaultPrefixS", "RESP_DES,UUID,USERNAME,SERVICE_DATA,MD5_MAC,ENTITY_OBJ,RESP_DES,POSID,DATA");
		responseColums.put(CmdnoUtils.REG_INPUT_MOBILE_RETURN + MUST, "");
		responseColums.put(CmdnoUtils.REG_INPUT_MOBILE_RETURN + SELECT, "");

		responseColums.put(CmdnoUtils.REG_CHECK_CODE_RETURN + MUST, "");
		responseColums.put(CmdnoUtils.REG_CHECK_CODE_RETURN + SELECT, "USERNAME");

		responseColums.put(CmdnoUtils.REG_ADD_USERINFO_RETURN + MUST, "");
		responseColums.put(CmdnoUtils.REG_ADD_USERINFO_RETURN + SELECT, "USERNAME,NAME,NICKNAME,PHONE,IDENTI_PHOTO,IDENTI_NO,SEX,AGE,ADDRESS,EMAIL,PERSON_PHOTO,GRADE,DES");
		responseColums.put(CmdnoUtils.REG_ADD_MERINFO_RETURN + MUST, "");
		responseColums
				.put(
						CmdnoUtils.REG_ADD_MERINFO_RETURN + SELECT,
						"USERNAME,MERCODE,MERNAME_SHORT,MERNAME_FULL,LEGAL_PERSON,NICKNAME,BLANK_TYPE,BLANK_ACCOUNT_NO,BLANK_ACCOUNT_NAME,PHONE,FIXEDPHONE,IDENTI_PHOTO,IDENTI_NO,SEX,AGE,ADDRESS,EMAIL,PERSON_PHOTO,OGNZCODE_PHOTO,OGNZCODE_SN,TAXREG_PHOTO,TAXREG_SN,B_LICENSE_PHOTO,B_LICENSE _SN,MER_GRADE,DES");
		responseColums.put(CmdnoUtils.USER_LOGON_RETURN + MUST, "USER_TYPE");
		responseColums.put(CmdnoUtils.USER_LOGON_RETURN + SELECT, "USERNAME");
		responseColums.put(CmdnoUtils.MER_LOGON_RETURN + MUST, "USER_TYPE,MER_CODE,CER_PERFEE,MER_GRADE,MER_AMOUNT_LIMIT,USERNAME,CHILD_ACCOUNT_LIST,POSID_LIST");
		responseColums.put(CmdnoUtils.MER_LOGON_RETURN + SELECT, "USERNAME,CHILD_ACCOUNT_LIST,POSID_LIST");
		responseColums.put(CmdnoUtils.MER_TRAN_INIT_RETURN + MUST, "USER_TYPE,MER_CODE");
		responseColums.put(CmdnoUtils.MER_TRAN_INIT_RETURN + SELECT, "USERNAME,POSID");

		responseColums.put(CmdnoUtils.MER_CHECK_CODE_RETURN + MUST, "USER_TYPE");
		responseColums.put(CmdnoUtils.MER_CHECK_CODE_RETURN + SELECT, "USERNAME,POSID");

		// 5. 子账号登录
		responseColums.put(CmdnoUtils.ACCOUNT_LOGON_RETURN + MUST, "USER_TYPE,MER_CODE,POSID");
		responseColums.put(CmdnoUtils.ACCOUNT_LOGON_RETURN + SELECT, "USERNAME,NAME,PHONE,EMAIL");
		// 7. 修改密码（2500）
		responseColums.put(CmdnoUtils.EDIT_PASSWORD_RETURN + SELECT, "USERNAME");
		// b. POS机信息完善
		responseColums.put(CmdnoUtils.MER_ADD_POSINFO_RETURN + MUST, "DEV_VER");// 提取了两个,CER_MONTHS,CER_STATE，待加上
		responseColums.put(CmdnoUtils.MER_ADD_POSINFO_RETURN + SELECT, "POSID,CER_START,CER_END,MAC_NO,DES,POS_STATE");
		// a. 操作员录入（2401）
		responseColums.put(CmdnoUtils.MER_ADD_OPTINFO_RETURN + MUST, "USER_TYPE");
		responseColums.put(CmdnoUtils.MER_ADD_OPTINFO_RETURN + SELECT, "CHILD_ROLE,MER_CODE,USERNAME,POSID,NAME,PHONE,EMAIL");

		// a.5. 子账号登录 已初始化（2300）
		responseColums.put(CmdnoUtils.ACCOUNT_LOGON_RETURN + MUST, "USER_TYPE,CHILD_ROLE,MER_CODE,POSID");// CER_LIST
		responseColums.put(CmdnoUtils.ACCOUNT_LOGON_RETURN + SELECT, "USERNAME,NAME,PHONE,EMAIL");

		// RESP_CODE：登录成功未初始化，且为MAC_NO不存在,返回码为2311
		responseColums.put(CmdnoUtils.ACCOUNT_LOGON_INIT_RETURN + MUST, "USER_TYPE,CHILD_ROLE,MER_CODE");
		responseColums.put(CmdnoUtils.ACCOUNT_LOGON_INIT_RETURN + SELECT, "NAME,PHONE,EMAIL,POSID_LIST");

		// 子账号绑定pos初始化（选择POSID）（2310）
		responseColums.put(CmdnoUtils.ACCOUNT_LOGON_INIT_RETURN + SELECT, "MER_CODE,USERNAME,POSID");

		// 子账号绑定pos验证码确认（2311）
		responseColums.put(CmdnoUtils.ACCOUNT_CHECK_CODE_RETURN + SELECT, "USERNAME,POSID");// CER_LIST
		// 16. 功能记忆下载请求（3200）
		responseColums.put(CmdnoUtils.ZONE_ADD_NODE_RETURN + MUST, "SERVICE_CODE");
		responseColums.put(CmdnoUtils.ZONE_ADD_NODE_RETURN + SELECT, "TERM_ID");
		// 14. 参数下载（3000）
		responseColums.put(CmdnoUtils.PARAM_DOWNLOAD_RETURN.toString() + MUST, "ALL_CHARGE_LIMIT,ALL_CONSUME_LIMIT,PER_CHARGE_LIMIT,PER_CONSUME_LIMIT");
		responseColums.put(CmdnoUtils.PARAM_DOWNLOAD_RETURN.toString() + SELECT, "APP_GRADE_LOG,APP_DOWN_URL,SERVICE_TYPE_LIST");
	}

	private static final Map<String, String> getResponseColums(String requestCode)
	{
		Map<String, String> _rep = new HashMap<String, String>();
		String m = responseColums.get("defaultPrefixM");
		String _m = responseColums.get(requestCode + MUST);
		if (StringUtils.isNotBlank(_m))
		{
			m = m + "," + _m;
		}
		String s = responseColums.get("defaultPrefixS");
		String _s = responseColums.get(requestCode + SELECT);
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
		Map<String, String> _maps = getResponseColums(requestCode);
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

	public static final Map<String, String> getResponseValue(String requestCode, Map<String, String> response) throws UseException
	{
		// TODO还需要分解
		if (!response.containsKey(ShareFieldUtils.REQ_CODE))
		{
			response.put(ShareFieldUtils.REQ_CODE, "1");
		}
		if (!response.containsKey(ShareFieldUtils.RESP_CODE))
		{
			response.put(ShareFieldUtils.RESP_CODE, requestCode);
		}
		response.put(ShareFieldUtils.APP_VER, "1.0");
		response.put(ShareFieldUtils.SYS_PARAM, "1.0");
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
			if (StringUtils.isNotBlank(value))
			{
				retMap.put(_key, value);
			}
		}
		return retMap;
	}

	public static final Map<String, byte[]> getResponseToByte(String requestCode, Map<String, String> response) throws UseException
	{
		Map<String, byte[]> ret = new HashMap<String, byte[]>();

		Map<String, String> retMap = getResponseValue(requestCode, response);
		for (Map.Entry<String, String> entry : retMap.entrySet())
		{
			ret.put(entry.getKey(), entry.getValue().getBytes());
		}
		return ret;
	}

}
