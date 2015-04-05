package com.nci.tkb.busi.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nci.tkb.busi.exception.UseException;
import com.nci.tkb.busi.service.ReqHandlerService;
import com.nci.tkb.busi.service.ReqRegisterHandlerService;
import com.nci.tkb.busi.service.RespHandlerService;
import com.nci.tkb.busi.serviceimpl.HandlerUtil;
import com.nci.tkb.busi.serviceimpl.ReqCertHandlerServiceImpl;
import com.nci.tkb.busi.serviceimpl.ReqHandlerServiceImpl;
import com.nci.tkb.busi.serviceimpl.ReqRegisterHandlerServiceImpl;
import com.nci.tkb.busi.serviceimpl.RequestHandlerUtils;
import com.nci.tkb.busi.serviceimpl.RespHandlerServiceImpl;
import com.nci.tkb.busi.utils.BusiCodeUtils;
import com.nci.tkb.busi.utils.CertificateUtils;
import com.nci.tkb.busi.utils.CmdnoUtils;
import com.nci.tkb.busi.utils.ShareFieldUtils;
import com.nci.tkb.busi.utils.StaticMethod;
import com.nci.tkb.busi.utils.StaticUtils;
import com.nci.tkb.busi.utils.ThreeDES;
import com.npass.pay.memshare.client.ByteUtil;
import com.npass.pay.memshare.client.BytesUtils;

/**
 * Description 业务分发类
 * 
 * @author ZYB
 * @version 1.0
 * @Date 2014-03-05
 */
public class OperateHandler
{
	private Logger log = Logger.getLogger(OperateHandler.class.getName());
	// 请求模块
	private ReqHandlerService reqService = new ReqHandlerServiceImpl();
	private ReqCertHandlerServiceImpl reqCertService = new ReqCertHandlerServiceImpl();
	// 相应模块
	private RespHandlerService respService = new RespHandlerServiceImpl();

	private ReqRegisterHandlerService registerHandlerService = new ReqRegisterHandlerServiceImpl();

	/**
	 * function 根据业务代码和请求码判断业务类型
	 * 
	 * @author ZYB
	 * @Date 2014-03-05
	 * @input @param: Map<String, byte[]> reqMap
	 * @return : map
	 * @throws JSONException
	 * @throws UseException
	 */
	public Map<String, byte[]> process(Map<String, byte[]> reqMap) throws JSONException, UseException
	{
		log.info("******************OperateHandler.process.start***************");

		Map<String, byte[]> resMap = new HashMap<String, byte[]>();
		// 去掉左右边空格和左边0
		Map<String, String> map = retMap(reqMap);

		// 业务代码
		String busiCode = new String(reqMap.get(ShareFieldUtils.BUSI_CODE));
		// 请求码
		String cmdno = new String(reqMap.get(ShareFieldUtils.CMD_NO));
		// 请求返回状态
		String msgType = new String(reqMap.get(ShareFieldUtils.MSG_TYPE));
		log.info("process param after: " + reqMap.toString());
		Map<String, String> req = RequestHandlerUtils.getRequestValue(cmdno, map);
		// 业务模块（消息类型）
		if (StaticUtils.MSG_TYPE_REQ.equals(msgType))
		{
			log.debug(StaticMethod.locationLog() + "MSG_TYPE=REQ");
			// 充值第一步
			if (BusiCodeUtils.BUSICODE_RECHARGE_FIRST.equals(busiCode) && CmdnoUtils.RECHARGE_FIRST_ACCEPT.equals(cmdno))
			{
				reqService.RechargeFirst(map);
			}
			// 充值第二步
			else if (BusiCodeUtils.BUSICODE_RECHARGE_SECOND.equals(busiCode) && CmdnoUtils.RECHARGE_SECOND_ACCEPT.equals(cmdno))
			{
				// reqService.RechargeSecond(map);
			}
			// 消费第一步
			else if (BusiCodeUtils.BUSICODE_CONSUME_FIRST.equals(busiCode) && CmdnoUtils.CONSUME_FIRST_ACCEPT.equals(cmdno))
			{
				// reqService.ConsumeFirst(map);
			}
			// 消费第二步
			else if (BusiCodeUtils.BUSICODE_CONSUME_SECOND.equals(busiCode) && CmdnoUtils.CONSUME_SECOND_ACCEPT.equals(cmdno))
			{
				// reqService.ConsumeSecond(map);
			}
			// 冲正
			else if (BusiCodeUtils.BUSICODE_REVERSE.equals(busiCode) && CmdnoUtils.REVERSE_ACCEPT.equals(cmdno))
			{
				// reqService.Reverse(map);
			}
			// 退款
			else if (BusiCodeUtils.BUSICODE_REFUND.equals(busiCode) && CmdnoUtils.REFUND_ACCEPT.equals(cmdno))
			{
				// reqService.Refund(map);
			}
			// 录入手机号
			else if (CmdnoUtils.REG_INPUT_MOBILE_ACCEPT.equals(cmdno))
			{
				Map<String, byte[]> maps = new HashMap<String, byte[]>();
				return maps;
			}
			// 证书纳费
			else if (CmdnoUtils.CERT_PAYMENT_ACCEPT.equals(cmdno))
			{
				resMap = reqCertService.CerPayMent(map);
			}
			// 证书请求
			else if (CmdnoUtils.CERT_ACCEPT.equals(cmdno))
			{
				resMap = reqCertService.CerRequest(map);
			}
			// 用户注册
			else if (CmdnoUtils.REG_INPUT_MOBILE_ACCEPT.equals(cmdno))
			{
				resMap = registerHandlerService.register(req);
			}
			// 用户注册验证
			else if (CmdnoUtils.REG_CHECK_CODE_ACCEPT.equals(cmdno))
			{
				resMap = registerHandlerService.validateRegister(req);
			}
			// 注册--用户信息完善

			else if (CmdnoUtils.REG_ADD_USERINFO_ACCEPT.equals(cmdno))
			{
				resMap = registerHandlerService.completeUserInfo(req);
			}

			// 注册--商户信息完善

			else if (CmdnoUtils.REG_ADD_MERINFO_ACCEPT.equals(cmdno))
			{
				resMap = registerHandlerService.completeMerchantInfo(req);
			}

			// 用户登录

			else if (CmdnoUtils.USER_LOGON_ACCEPT.equals(cmdno))
			{
				resMap = reqService.UserLogin(req);
			}
			// 商户登录

			else if (CmdnoUtils.MER_LOGON_ACCEPT.equals(cmdno))
			{
				resMap = reqService.MerLogin(req);
			}
			// 商户交易初始化--初始化

			else if (CmdnoUtils.MER_TRAN_INIT_ACCEPT.equals(cmdno))
			{
				resMap = reqService.MerTransInit(req);
			}
			// 商户交易初始化--验证码确认

			else if (CmdnoUtils.MER_CHECK_CODE_ACCEPT.equals(cmdno))
			{
				resMap = reqService.validateMerTransInit(req);
			}
			
			else if(CmdnoUtils.ACCOUNT_LOGON_ACCEPT.equals(cmdno)) {
				resMap = reqService.SubAccountLogin(req);
			}
		}

		else
		{
			++StaticUtils.errorNum;
			log.debug(StaticMethod.locationLog() + "request data is error!!!");
		}
		log.info("^^^^XXXXXXXX^^^^^ICEListener************process end.");
		return resMap;
	}

	/**
	 * 解密数据
	 * 
	 * @param rep
	 * @return
	 */
	public String decData(Map<String, String> rep)
	{
		String ret = null;
		try
		{
			String encryFlag = rep.get(ShareFieldUtils.ENCRY_FLAG);
			String userId = rep.get(ShareFieldUtils.USER_ID);
			String cerSn = rep.get(ShareFieldUtils.CER_SN);
			byte[] psMessage = rep.get(ShareFieldUtils.ENTITY_OBJ).getBytes();
			rep.put(ShareFieldUtils.RESP_MSG_CODE, "0");
			rep.put(ShareFieldUtils.USER_ID, userId);
			// 证书解密
			if (encryFlag == "1")
			{
				// 根据序列号获取证书信息
				rep = reqCertService.getByCerSnInfo(cerSn);
				// 证书别名
				String alias = rep.get("ALIAS");
				// 证书密码
				String cerPass = rep.get("CER_PWD");
				// 证书库名
				String storeName = rep.get("STORE_NAME");
				// 根据证书库名称获取证书库信息
				rep = reqCertService.getKeystoneByName(storeName);
				// 获取证书库地址
				String keyStorePath = rep.get("STORE_FLODER");
				// 用私钥解密
				byte[] decrypts = CertificateUtils.decryptByPrivateKey(psMessage, keyStorePath, alias, cerPass);
				JSONObject jsObject = new JSONObject(new String(decrypts));
				ret = jsObject.toString();

			}
			else if (encryFlag == "2")
			{
				// 对证加密
				byte[] srcBytes = ThreeDES.decryptMode(ShareFieldUtils.KEYBYTES, psMessage);
				JSONObject jsObject = new JSONObject(new String(srcBytes));
				ret = jsObject.toString();
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return ret;
	}

	/**
	 * 解析JSON
	 * 
	 * @param json
	 * @param retMap
	 * @return
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> decodeJSONObject(JSONObject json, Map<String, String> retMap) throws JSONException
	{
		Iterator<String> keys = json.keys();
		String value = null;
		String key;
		while (keys.hasNext())
		{
			key = keys.next();
			value = (String) json.get(key);
			retMap.put(key, value);
		}
		return retMap;
	}

	/**
	 * 返回MAP
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, String> retMap(Map<String, byte[]> map)
	{
		Map<String, String> rep = new HashMap<String, String>();
		try
		{
			for (String key : map.keySet())
			{
				System.out.println("key= " + key + ",key.value=" + map.get(key));
				byte[] p1 = map.get(key);
				if (p1 != null)
				{
					rep.put(key, new String(p1));
				}
			}
			if (map.containsKey(ShareFieldUtils.REQ_MESSAGE))
			{
				byte[] pb = map.get(ShareFieldUtils.REQ_MESSAGE);
				String p2 = new String(pb);
				JSONObject js = new JSONObject(p2);
				if (js != null)
				{
					rep = decodeJSONObject(js, rep);
				}
				String entityObj = rep.get(ShareFieldUtils.ENTITY_OBJ);
				if (null != entityObj)
				{
					JSONObject j2 = new JSONObject(entityObj);
					rep = decodeJSONObject(j2, rep);
				}
			}

		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return rep;
	}
}
