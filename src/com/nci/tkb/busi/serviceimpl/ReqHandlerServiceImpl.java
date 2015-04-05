package com.nci.tkb.busi.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.nci.tkb.busi.exception.DAOException;
import com.nci.tkb.busi.exception.UseException;
import com.nci.tkb.busi.redis.RedisUtils;
import com.nci.tkb.busi.service.ReqHandlerService;
import com.nci.tkb.busi.utils.CmdnoUtils;
import com.nci.tkb.busi.utils.LogonRoleUtils;
import com.nci.tkb.busi.utils.RetCode;
import com.nci.tkb.busi.utils.ShareFieldUtils;
import com.nci.tkb.busi.utils.StaticMethod;
import com.nci.tkb.busi.utils.TableCollection;
import com.nci.tkb.busi.utils.TableColumnsUtils;
import com.npass.pay.memshare.client.BytesUtils;

/**
 * Description 业务处理（请求）
 * 
 * @author LYP
 * @version 1.0
 * @Date 2014-02-20
 */
public class ReqHandlerServiceImpl extends HandlerUtil implements ReqHandlerService
{
	static Logger log = Logger.getLogger(ReqHandlerServiceImpl.class.getName());
	private static final String USERNAME = "1";
	private static final String POSID = "2";

	/**
	 * function 充值第一步
	 * 
	 * @author LYP
	 * @version 1.0
	 * @Date 2014-02-20
	 * @input param:Map<String, String> reqMap
	 * @return void
	 */
	public Map<String, byte[]> RechargeFirst(Map<String, String> reqMap)
	{
		// TODO Auto-generated method stub
		// 方法头日志
		StaticMethod.printMethodStartLog();
		// 传入参数日志
		StaticMethod.printParamLog(reqMap);
		String cerSn = reqMap.get(ShareFieldUtils.CER_SN);

		try
		{
			// 1.判断证书序列号是否绑定POS机
			Map<String, String> cerSnMap = getCerSnToPosInfo(cerSn);

			// 2.证书验签是否通过

			// 3.判断充值金额是否合法
			valChargeLimit(reqMap.get(ShareFieldUtils.PAY_MONEY));

			// 4.判断商户是否合法
			getMerInfo(reqMap.get(ShareFieldUtils.MERCHANT_CODE));

			// 5.判断POS机是否合法
			getPosByMerInfo(reqMap.get(ShareFieldUtils.POS_ID), reqMap.get(ShareFieldUtils.MERCHANT_CODE));

			// 6.判断操作员是否合法
			getOptInfo(reqMap.get(ShareFieldUtils.OPERATOR), reqMap.get(ShareFieldUtils.MERCHANT_CODE));

			// 7.判断运营商是否合法

			// 8.保存业务流水，交易状态记录为1

			// 同步数据到redis内存
			/*
			 * Map<String, String> maps = new HashMap<String, String>();
			 * maps.put("MSG_TYPE", "1"); boolean uptflag =
			 * StaticMethod.memProcess(maps); if (uptflag) {
			 * System.out.println("数据同步到redis内存成功！"); } else {
			 * System.out.println("数据同步到redis内存失败！"); }
			 */

		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************recharge step first error:" + e);
		}
		return null;
	}

	/**
	 * function 充值第二步
	 * 
	 * @author LYP
	 * @version 1.0
	 * @Date 2014-02-20
	 * @input param:Map<String, String> reqMap
	 * @return void
	 */
	public Map<String, byte[]> RechargeSecond(Map<String, String> reqMap)
	{
		// TODO Auto-generated method stub
		// 方法头日志
		StaticMethod.printMethodStartLog();
		// 传入参数日志
		StaticMethod.printParamLog(reqMap);

		try
		{
			// 1.判断证书序列号是否绑定POS机
			// 2.证书验签是否通过
			// 3.判断业务流水是否已经存在
			// 4.更新业务流水交易状态
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************recharge step second error:" + e);
		}
		return null;
	}

	/**
	 * function 消费第一步
	 * 
	 * @author LYP
	 * @version 1.0
	 * @Date 2014-02-20
	 * @input param:Map<String, String> reqMap
	 * @return void
	 */
	public Map<String, byte[]> ConsumeFirst(Map<String, String> reqMap)
	{
		// TODO Auto-generated method stub
		// 方法头日志
		StaticMethod.printMethodStartLog();
		// 传入参数日志
		StaticMethod.printParamLog(reqMap);

		try
		{
			// 1.判断证书序列号是否绑定POS机
			// 2.证书验签是否通过
			// 3.判断消费金额是否合法
			// 4.判断商户是否合法
			// 5.判断POS机是否
			// 6.判断操作员是否合法
			// 7.判断运营商是否合法
			// 8.保存业务流水，交易状态记录为1
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************consume step first error:" + e);
		}
		return null;
	}

	/**
	 * function 消费第二步
	 * 
	 * @author LYP
	 * @version 1.0
	 * @Date 2014-02-20
	 * @input param:Map<String, String> reqMap
	 * @return void
	 */
	public Map<String, byte[]> ConsumeSecond(Map<String, String> reqMap)
	{
		// TODO Auto-generated method stub
		// 方法头日志
		StaticMethod.printMethodStartLog();
		// 传入参数日志
		StaticMethod.printParamLog(reqMap);

		try
		{
			// 1.判断证书序列号是否绑定POS机
			// 2.证书验签是否通过
			// 3.判断业务流水是否已经存在
			// 4.更新业务流水交易状态
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************consume step second error:" + e);
		}
		return null;
	}

	/**
	 * function 冲正
	 * 
	 * @author LYP
	 * @version 1.0
	 * @Date 2014-02-20
	 * @input param:Map<String, String> reqMap
	 * @return void
	 */
	public Map<String, byte[]> Reverse(Map<String, String> reqMap)
	{
		// TODO Auto-generated method stub
		// 方法头日志
		StaticMethod.printMethodStartLog();
		// 传入参数日志
		StaticMethod.printParamLog(reqMap);

		try
		{
			// 1.判断证书序列号是否绑定POS机
			// 2.证书验签是否通过
			// 3.判断业务流水是否已经存在并且为失败状态
			// 4.更新业务流水交易状态为冲正状态
			// 5.调用数据透传接口，向通卡公司提交充正请求
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************Reverse error:" + e);
		}
		return null;
	}

	/**
	 * function 退款
	 * 
	 * @author LYP
	 * @version 1.0
	 * @Date 2014-02-20
	 * @input param:Map<String, String> reqMap
	 * @return void
	 */
	public Map<String, byte[]> Refund(Map<String, String> reqMap)
	{
		// TODO Auto-generated method stub
		// 方法头日志
		StaticMethod.printMethodStartLog();
		// 传入参数日志
		StaticMethod.printParamLog(reqMap);

		try
		{
			// 1.判断证书序列号是否绑定POS机
			// 2.证书验签是否通过
			// 3.判断业务流水是否已经存在并且为失败状态
			// 4.更新业务流水交易状态为退款状态
			// 5.调用数据透传接口，向银行提交退款请求
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************Refund error:" + e);
		}
		return null;
	}

	/**
	 * 用户登录
	 */
	public Map<String, byte[]> UserLogin(Map<String, String> map)
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		Map<String, byte[]> retMap = null;
		try
		{
			String userName = map.get(ShareFieldUtils.USERNAME);
			// 判断用户是否为空
			this.stringIsEmpty(userName, RetCode.USERNAME_IS_ERROR);

			// 获取用户信息
			Map<String, String> userInfo = this.getRegisterUserInfo(userName);

			// 判断是否为空
			this.mapIsNull(userInfo, RetCode.USERNAME_IS_ERROR);

			// 查询角色
			Map<String, String> roleInfo = this.getRoleUser(userInfo.get(ShareFieldUtils.ID));
			// 判断是否为空
			this.mapIsNull(roleInfo, RetCode.USERNAME_IS_ERROR);
			this.compareRole(LogonRoleUtils.U_1.id, roleInfo.get(ShareFieldUtils.ROLE_ID));
			// 验证用户名密码是否正确
			String reqPassword = map.get(ShareFieldUtils.PASSWORD);
			String userPassword = userInfo.get(ShareFieldUtils.PASSWORD);
			// 4.正确则更新用户登录表、保存登录日志表
			this.comparePassword(userPassword, reqPassword);
			this.updateLogonUserAndLog(map);
			Map<String, byte[]> desMap = new HashMap<String, byte[]>();
			desMap.put(userName, map.get(ShareFieldUtils.DESKEY).getBytes());
			RedisUtils.setBHashMap("DES_KEY_MEM", desMap);
			// 5.将数据重新封装加密并返回map
			map.put(ShareFieldUtils.USER_TYPE, "0");
			retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.USER_LOGON_RETURN, map);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************UserLogin error:" + e);
		}
		return retMap;
	}

	/**
	 * 商户登录
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public Map<String, byte[]> MerLogin(Map<String, String> map)
	{

		// 方法头日志
		StaticMethod.printMethodStartLog();
		Map<String, byte[]> retMap = null;
		try
		{
			// (1.解密)
			// (2.判断是否解密成功)
			String userName = map.get(ShareFieldUtils.USERNAME);
			// 判断用户是否为空
			this.stringIsEmpty(userName, RetCode.USERNAME_IS_ERROR);
			// 获取用户信息
			Map<String, String> userInfo = this.getRegisterUserInfo(userName);

			// 判断是否为空
			this.mapIsNull(userInfo, RetCode.USERNAME_IS_ERROR);
			// 3.验证商户名密码是否正确
			String reqPassword = map.get(ShareFieldUtils.PASSWORD);
			String userPassword = userInfo.get(ShareFieldUtils.PASSWORD);
			this.comparePassword(userPassword, reqPassword);
			// 查询角色
			Map<String, String> roleInfo = this.getRoleUser(userInfo.get(ShareFieldUtils.ID));
			// 判断是否为空
			this.mapIsNull(roleInfo, RetCode.USERNAME_IS_ERROR);
			// 用户角色ID是否为3000
			this.compareRole(LogonRoleUtils.M_1.id, roleInfo.get(ShareFieldUtils.ROLE_ID));

			// 4.正确则更新用户登录表、保存登录日志表

			this.updateLogonUserAndLog(map);
			// 将对称密钥加到内存DES_KEY_MEM表
			Map<String, byte[]> desMap = new HashMap<String, byte[]>();

			desMap.put(userName, map.get(ShareFieldUtils.DESKEY).getBytes());
			RedisUtils.setBHashMap("DES_KEY_MEM", desMap);

			// 获取商户信息

			Map<String, String> limitMap = new HashMap<String, String>();
			limitMap.put(ShareFieldUtils.USERNAME, userName);
			Map<String, String> merInfo = this.getDataByRedisOrDatabase(TableCollection.MERCHANT_INFO, userName, limitMap, true);
			this.mapIsNull(merInfo, RetCode.MERCHANT_NOT_EXIST);
			// 5.商户子账户列表查询
			Map<String, String> merLimitMap = new HashMap<String, String>();
			merLimitMap.put(ShareFieldUtils.MER_CODE, merInfo.get(ShareFieldUtils.MER_CODE));

			List<Map<String, String>> subMerInfoList = this.findDataByDatabase(TableCollection.USER_INFO, merLimitMap);

			// 6.验证商户如果绑定POS则获取POS证书数据
			List<Map<String, String>> posList = this.findDataByDatabase(TableCollection.MER_POS, merLimitMap);
			map.put(ShareFieldUtils.CHILD_ACCOUNT_LIST, subMerInfoList.toString());
			map.put(ShareFieldUtils.POSID_LIST, posList.toString());
			// 7.将数据封装加密返回
			map.putAll(merInfo);
			map.put(ShareFieldUtils.USER_TYPE, "1");
			// TODO 证书单价 商户等级 商户额度限制 临时写入值，需改正
			String grade = merInfo.get(ShareFieldUtils.GRADE);
			map.put(ShareFieldUtils.MER_GRADE, StringUtils.isBlank(grade) ? "1" : grade);
			map.put(ShareFieldUtils.CER_PERFEE, "1000000");
			map.put(ShareFieldUtils.MER_AMOUNT_LIMIT, "100000000");

			retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.MER_LOGON_RETURN, map);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************MerLogin error:" + e);
		}
		return retMap;
	}

	/**
	 * 商户交易初始化
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public Map<String, byte[]> MerTransInit(Map<String, String> map)
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		String posId = map.get(ShareFieldUtils.POS_ID);
		Map<String, byte[]> retMap = null;
		try
		{
			// 判断posid是否为空
			this.stringIsEmpty(posId, RetCode.POSID_IS_EMPTY);
			Map<String, String> mer_pos_limit = new HashMap<String, String>();
			// 判断pos机是否存在
			mer_pos_limit.put(ShareFieldUtils.POS_ID, posId);
			Map<String, String> mer_pos = this.getDataByRedisOrDatabase(TableCollection.MER_POS, posId, mer_pos_limit, true, RetCode.POSID_NOT_EXSIT, false);

			String isBind = mer_pos.get(ShareFieldUtils.IS_BIND);

			this.compareEqualsNotNull("1", isBind, RetCode.POS_IS_BIND);

			// TODO生成校验码,发送给用户
			int captcha = random.nextInt(randomLength);
			System.out.println(captcha);
			String mac = map.get(ShareFieldUtils.MAC_NO);
			// 存内存
			String tempKey = "POS_CAP_" + mac;
			Map<String, String> redisTemp = new HashMap<String, String>();
			redisTemp.put(ShareFieldUtils.CAPTCHA, String.valueOf(captcha));
			RedisUtils.set(tempKey, BytesUtils.ObjectToByte(redisTemp), maxLive);

			map.put(ShareFieldUtils.USER_TYPE, "1");
			map.put(ShareFieldUtils.MER_CODE, mer_pos.get(ShareFieldUtils.MER_CODE));
			retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.MER_TRAN_INIT_RETURN, map);
		}
		catch (UseException e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************MerTransInit error:" + e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************MerTransInit error:" + e);
		}
		StaticMethod.getTraceInfo();
		return retMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, byte[]> validateMerTransInit(Map<String, String> map)
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		String posId = map.get(ShareFieldUtils.POS_ID);
		Map<String, byte[]> retMap = null;
		try
		{
			String mac = map.get(ShareFieldUtils.MAC_NO);
			String capicha = map.get(ShareFieldUtils.CAPTCHA);
			String tempKey = "POS_CAP_" + mac;
			byte[] objectByte = RedisUtils.get(tempKey);
			if (objectByte == null)
			{
				log.error("MAC_NO:" + mac + " REGISTER OVERTIME");
				throw new UseException(RetCode.CAPTCHA_OVERTIME);
			}
			else
			{
				Map<String, String> mapObject = (Map<String, String>) BytesUtils.byteToObject(objectByte);
				String tempCapicha = mapObject.get(ShareFieldUtils.CAPTCHA);
				if (tempCapicha != null && tempCapicha.equals(capicha))
				{
					// 判断posid是否为空
					this.stringIsEmpty(posId, RetCode.POSID_IS_EMPTY);
					Map<String, String> mer_pos_limit = new HashMap<String, String>();
					// 判断pos机是否存在
					mer_pos_limit.put(ShareFieldUtils.POS_ID, posId);
					Map<String, String> mer_pos = this.getDataByRedisOrDatabase(TableCollection.MER_POS, posId, mer_pos_limit, true, RetCode.POSID_NOT_EXSIT, false);

					String isBind = mer_pos.get(ShareFieldUtils.IS_BIND);

					this.compareEqualsNotNull("1", isBind, RetCode.POS_IS_BIND);
					RedisUtils.del(tempKey);
					Map<String, String> updateField = new HashMap<String, String>();
					updateField.put(ShareFieldUtils.ID, mer_pos.get(ShareFieldUtils.ID));
					updateField.put(ShareFieldUtils.MAC_NO, mac);
					updateField.put(ShareFieldUtils.IS_BIND, "1");
					this.update(TableCollection.MER_POS, updateField);

					map.put(ShareFieldUtils.USER_TYPE, "1");
					retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.MER_CHECK_CODE_RETURN, map);
				}
				else
				{
					log.error("MAC_NO:" + mac + "REGISTER_APTCHA ERROR");
					throw new UseException(RetCode.CAPTCHA_ERROR);
				}
			}
		}
		catch (UseException e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************MerTransInit error:" + e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************MerTransInit error:" + e);
		}
		StaticMethod.getTraceInfo();
		return retMap;
	}

	/**
	 * 子帐号登录
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> SubAccountLogin(Map<String, String> map)
	{
		String subUserName = map.get(ShareFieldUtils.USERNAME);
		String mac_no = map.get(ShareFieldUtils.MAC_NO);
		Map<String, byte[]> retMap = null;
		String posId = "";
		List<Map<String, String>> noBindList = new ArrayList<Map<String, String>>();
		;
		try
		{
			this.stringIsEmpty(subUserName, RetCode.USERNAME_IS_ERROR);
			this.stringIsEmpty(mac_no, RetCode.USERNAME_IS_ERROR);
			Map<String, String> limitMap = new HashMap<String, String>();
			limitMap.put(ShareFieldUtils.USERNAME, subUserName);
			Map<String, String> userInfo = this.getDataByRedisOrDatabase(TableCollection.USER_INFO, subUserName, limitMap, true, RetCode.USERNAME_IS_ERROR, false);
			String mercode = userInfo.get(ShareFieldUtils.MER_CODE);
			this.stringIsEmpty(mercode, RetCode.USERNAME_IS_ERROR);

			Map<String, String> limitpos = new HashMap<String, String>();
			limitpos.put(ShareFieldUtils.MER_CODE, mercode);

			List<Map<String, String>> posList = this.findDataByDatabase(TableCollection.MER_POS, limitpos);
			map.putAll(userInfo);
			for (Map<String, String> pos : posList)
			{
				String detailMacNo = pos.get(ShareFieldUtils.MAC_NO);
				if (StringUtils.isBlank(detailMacNo))
				{

					noBindList.add(pos);
				}
				else if (mac_no.equals(detailMacNo))
				{
					posId = pos.get(ShareFieldUtils.POS_ID);
				}
			}
			map.put(ShareFieldUtils.USER_TYPE, "2");
			map.put("CHILD_ROLE", "1");
			// 已绑定
			if (StringUtils.isNotBlank(posId))
			{
				map.put(ShareFieldUtils.POSID, posId);
				retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.ACCOUNT_LOGON_RETURN, map);
			}
			else
			{
				map.put(ShareFieldUtils.POSID_LIST, noBindList.toString());
				retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.ACCOUNT_LOGON_RETURN, map);
			}

		}
		catch (UseException e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************SubAccountLogin error:" + e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************SubAccountLogin error:" + e);
		}

		return retMap;
	}

	/**
	 * 商户管理
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> MerManager(Map<String, byte[]> map)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 修改密码
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> EditPwd(Map<String, String> map)
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		Map<String, byte[]> retMap = null;
		try
		{
			// 用户名
			String userName = map.get(ShareFieldUtils.USERNAME);
			// 判断用户名是否为空
			this.stringIsEmpty(userName, RetCode.USERNAME_IS_ERROR);
			Map<String, String> logonUser = this.getRegisterUserInfo(userName);
			// 判断是否为空
			this.mapIsNull(logonUser, RetCode.USERNAME_IS_ERROR);
			String sysPassword = logonUser.get(ShareFieldUtils.PASSWORD);
			String oldPassword = map.get(ShareFieldUtils.PASSWORD);
			String newPassword = map.get(ShareFieldUtils.PASSWORD_NEW);
			this.comparePassword(sysPassword, oldPassword);
			Map<String, String> updateMap = new HashMap<String, String>();
			updateMap.put(ShareFieldUtils.PASSWORD, newPassword);
			updateMap.put(ShareFieldUtils.ID, logonUser.get(ShareFieldUtils.ID));
			this.update(TableCollection.LOGON_USER, updateMap);
			retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.EDIT_PASSWORD_RETURN, map);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************EditPwd error:" + e);
		}
		log.info(StaticMethod.locationLog());
		return retMap;
	}

	/**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> QueryData(Map<String, byte[]> map)
	{
		// TODO Auto-generated method stub
		// 方法头日志
		StaticMethod.printMethodStartLog();
		// 传入参数日志
		StaticMethod.printParamLogs(map);

		try
		{
			//
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************DownloadParam error:" + e);
		}
		return null;
	}

	/**
	 * 参数下载
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, byte[]> DownloadParam(Map<String, String> map)
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		Map<String, byte[]> retMap = null;
		try
		{
			Map<String, String> response = new HashMap<String, String>();
			response.put(ShareFieldUtils.APP_GRADE_LOG, "APP_GRADE_LOG");
			response.put(ShareFieldUtils.APP_DOWN_URL, "APP_DOWN_URL");
			response.put(ShareFieldUtils.ALL_CHARGE_LIMIT, "ALL_CHARGE_LIMIT");
			response.put(ShareFieldUtils.ALL_CONSUME_LIMIT, "ALL_CONSUME_LIMIT");
			response.put(ShareFieldUtils.PER_CHARGE_LIMIT, "PER_CHARGE_LIMIT");
			response.put(ShareFieldUtils.PER_CONSUME_LIMIT, "PER_CONSUME_LIMIT");
			response.put(ShareFieldUtils.SERVICE_TYPE_LIST, "SERVICE_TYPE_LIST");
			retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.PARAM_DOWNLOAD_RETURN, response);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************DownloadParam error:" + e);
		}
		return retMap;
	}

	@Override
	public Map<String, byte[]> merPosManager(Map<String, String> map)
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		String posId = map.get(ShareFieldUtils.POS_ID);
		String merCode = map.get(ShareFieldUtils.MER_CODE);
		Map<String, String> mer_pos = new HashMap<String, String>();
		Map<String, byte[]> retMap = null;
		try
		{
			// 判断posid是否为空
			this.stringIsEmpty(posId, RetCode.POSID_IS_EMPTY);
			// 判断商户编码是否为空
			this.stringIsEmpty(merCode, RetCode.MER_CODE_IS_EMPTY);
			Map<String, String> limitMap = new HashMap<String, String>();
			limitMap.put(ShareFieldUtils.MER_CODE, merCode);
			// 判断是否有该商户编码
			this.getDataByDatabase(TableCollection.MERCHANT_INFO, limitMap, RetCode.MER_CODE_NOT_EXSIT, false);

			Map<String, String> mer_pos_limit = new HashMap<String, String>();
			mer_pos_limit.put(ShareFieldUtils.POS_ID, posId);
			mer_pos = this.getDataByRedisOrDatabase(TableCollection.MER_POS, posId, mer_pos_limit, true);

			mer_pos.put(ShareFieldUtils.POSID, posId);
			mer_pos.put(ShareFieldUtils.MER_CODE, merCode);
			mer_pos.put(ShareFieldUtils.DEV_VER, map.get(ShareFieldUtils.DEV_VER));
			String isBind = map.get(ShareFieldUtils.POS_STATE);
			// 绑定判断
			if (!"1".equals(isBind))
			{
				isBind = "0";
			}
			mer_pos.put(ShareFieldUtils.IS_BIND, isBind);
			// TODO默认试用
			// mer_pos.put(ShareFieldUtils.CER_STATE, "0");

			Map<String, String> insertMap = TableColumnsUtils.copyProperties(TableCollection.MER_POS, mer_pos);
			if (StringUtils.isBlank(mer_pos.get(ShareFieldUtils.ID)))
			{

				this.insert(TableCollection.MER_POS, insertMap);
			}
			else
			{
				this.update(TableCollection.MER_POS, insertMap);
			}
			map.putAll(insertMap);

			retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.MER_ADD_POSINFO_RETURN, map);
		}
		catch (UseException e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************merPosManager error:" + e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************merPosManager error:" + e);
		}
		log.info(StaticMethod.getTraceInfo());
		return retMap;
	}

	@Override
	public Map<String, byte[]> merSubManager(Map<String, String> map)
	{
		StaticMethod.printMethodStartLog();
		String mer_code = map.get(ShareFieldUtils.MER_CODE);
		String subUserName = map.get(ShareFieldUtils.USERNAME);
		Map<String, byte[]> retMap = null;
		try
		{
			this.stringIsEmpty(mer_code, RetCode.MER_CODE_IS_EMPTY);
			this.stringIsEmpty(subUserName, RetCode.USERNAME_IS_ERROR);
			this.checkValidSubUserName(subUserName);

			Map<String, String> limitMap = new HashMap<String, String>();
			limitMap.put(ShareFieldUtils.MER_CODE, mer_code);
			// 判断是否有该商户
			this.getDataByDatabase(TableCollection.MERCHANT_INFO, limitMap, RetCode.MERCHANT_NOT_EXIST, false);
			// 查询是否有该账号
			Map<String, String> logonUser = this.getRegisterUserInfo(subUserName);

			// 判断是否为null，为空，则为未注册子账号，添加。否则更新
			if (logonUser == null)
			{
				this.saveLogonUserAndRoleAndUserInfo(map);
			}
			else
			{
				Map<String, String> userinfo = this.getDataByDatabase(TableCollection.USER_INFO, limitMap, RetCode.SUB_USERNAME_EXSIT, false);
				map.put(ShareFieldUtils.ID, userinfo.get(ShareFieldUtils.ID));
				this.update(TableCollection.USER_INFO, map);
			}
			map.put(ShareFieldUtils.USER_TYPE, "2");
			retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.MER_ADD_OPTINFO_RETURN, map);
		}
		catch (UseException e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************merSubManager error:" + e);
		}
		catch (DAOException e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************merSubManager error:" + e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************merSubManager error:" + e);
		}

		return retMap;
	}

	@Override
	public Map<String, byte[]> BindSubUser(Map<String, String> map)
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		String posId = map.get(ShareFieldUtils.POS_ID);
		String subUserName = map.get(ShareFieldUtils.USERNAME);
		Map<String, byte[]> retMap = null;
		try
		{
			// 判断posid是否为空
			this.stringIsEmpty(posId, RetCode.POSID_IS_EMPTY);
			this.stringIsEmpty(subUserName, RetCode.USERNAME_IS_ERROR);
			this.checkValidSubUserName(subUserName);
			this.getRegisterUserInfo(subUserName);

			Map<String, String> mer_pos_limit = new HashMap<String, String>();
			// 判断pos机是否存在
			mer_pos_limit.put(ShareFieldUtils.POS_ID, posId);
			Map<String, String> mer_pos = this.getDataByRedisOrDatabase(TableCollection.MER_POS, posId, mer_pos_limit, true, RetCode.POSID_NOT_EXSIT, false);
			String isBind = mer_pos.get(ShareFieldUtils.IS_BIND);

			this.compareEqualsNotNull("1", isBind, RetCode.POS_IS_BIND);

			// TODO生成校验码,发送给用户
			int captcha = random.nextInt(randomLength);
			System.out.println(captcha);
			String mac = map.get(ShareFieldUtils.MAC_NO);
			// 存内存
			String tempKey = "SUB_POS_CAP_" + mac;
			Map<String, String> redisTemp = new HashMap<String, String>();
			redisTemp.put(ShareFieldUtils.CAPTCHA, String.valueOf(captcha));
			RedisUtils.set(tempKey, BytesUtils.ObjectToByte(redisTemp), maxLive);

			map.put(ShareFieldUtils.USER_TYPE, "1");
			map.put(ShareFieldUtils.MER_CODE, mer_pos.get(ShareFieldUtils.MER_CODE));
			retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.ACCOUNT_LOGON_INIT_RETURN, map);
		}
		catch (UseException e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************BindSubUser error:" + e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************BindSubUser error:" + e);
		}
		StaticMethod.getTraceInfo();
		return retMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, byte[]> validateBindSubUser(Map<String, String> map)
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		String posId = map.get(ShareFieldUtils.POS_ID);
		Map<String, byte[]> retMap = null;
		try
		{
			String mac = map.get(ShareFieldUtils.MAC_NO);
			String capicha = map.get(ShareFieldUtils.CAPTCHA);
			String tempKey = "SUB_POS_CAP_" + mac;
			byte[] objectByte = RedisUtils.get(tempKey);
			if (objectByte == null)
			{
				log.error("MAC_NO:" + mac + " REGISTER OVERTIME");
				throw new UseException(RetCode.CAPTCHA_OVERTIME);
			}
			else
			{
				Map<String, String> mapObject = (Map<String, String>) BytesUtils.byteToObject(objectByte);
				String tempCapicha = mapObject.get(ShareFieldUtils.CAPTCHA);
				if (tempCapicha != null && tempCapicha.equals(capicha))
				{
					// 判断posid是否为空
					this.stringIsEmpty(posId, RetCode.POSID_IS_EMPTY);
					Map<String, String> mer_pos_limit = new HashMap<String, String>();
					// 判断pos机是否存在
					mer_pos_limit.put(ShareFieldUtils.POS_ID, posId);
					Map<String, String> mer_pos = this.getDataByRedisOrDatabase(TableCollection.MER_POS, posId, mer_pos_limit, true, RetCode.POSID_NOT_EXSIT, false);

					String isBind = mer_pos.get(ShareFieldUtils.IS_BIND);

					this.compareEqualsNotNull("1", isBind, RetCode.POS_IS_BIND);
					RedisUtils.del(tempKey);
					Map<String, String> updateField = new HashMap<String, String>();
					updateField.put(ShareFieldUtils.ID, mer_pos.get(ShareFieldUtils.ID));
					updateField.put(ShareFieldUtils.MAC_NO, mac);
					updateField.put(ShareFieldUtils.IS_BIND, "1");
					this.update(TableCollection.MER_POS, updateField);

					map.put(ShareFieldUtils.USER_TYPE, "1");
					retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.ACCOUNT_CHECK_CODE_RETURN, map);
				}
				else
				{
					log.error("MAC_NO:" + mac + "REGISTER_APTCHA ERROR");
					throw new UseException(RetCode.CAPTCHA_ERROR);
				}
			}
		}
		catch (UseException e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************validateBindSubUser error:" + e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************validateBindSubUser error:" + e);
		}
		StaticMethod.getTraceInfo();
		return retMap;
	}

	@Override
	public Map<String, byte[]> MemoryBind(Map<String, String> map)
	{
		String flag = map.get(ShareFieldUtils.FLAG);
		String term_id = map.get(ShareFieldUtils.TERM_ID);
		String bind_flag = map.get(ShareFieldUtils.BIND_FLAG);
		if (StringUtils.isBlank(bind_flag) || !"1".equals(bind_flag))
		{
			bind_flag = "0";
		}
		// 注意：目前考虑的是单个服务方绑定
		String service_code = map.get(ShareFieldUtils.SERVICE_CODE);

		try
		{
			Map<String, String> limitMap = new HashMap<String, String>();
			Map<String, String> limitServiceMap = new HashMap<String, String>();
			limitMap.put(ShareFieldUtils.USERNAME, term_id);
			// 判断term_id是否存在
			if (USERNAME.equals(flag))
			{
				this.getDataByRedisOrDatabase(TableCollection.LOGON_USER, term_id, limitMap, true, RetCode.USERNAME_IS_ERROR, false);
			}
			else if (POSID.equals(flag))
			{
				this.getDataByRedisOrDatabase(TableCollection.MER_POS, term_id, limitMap, true, RetCode.POSID_NOT_EXSIT, false);
			}

			// 判断服务方代码是否存在
			this.stringIsEmpty(service_code, RetCode.SERVICE_TYPE_CODE_NOT_EXIT);
			limitServiceMap.put(ShareFieldUtils.S_TYPE_CODE, service_code);
			this.getDataByRedisOrDatabase(TableCollection.SERVICE_TYPE, service_code, limitServiceMap, true, RetCode.SERVICE_TYPE_CODE_NOT_EXIT, false);

			// 判断记忆表中是否存在记录
			limitMap.put(ShareFieldUtils.TERM_ID, term_id);
			limitMap.put(ShareFieldUtils.SERVICE_CODE, service_code);
			Map<String, String> mer = this.getDataByDatabase(TableCollection.USER_REMEMBER_INFO, limitMap, "", true);
			Map<String, String> insertMap = new HashMap<String, String>();
			insertMap.put(ShareFieldUtils.FLAG, flag);
			insertMap.put(ShareFieldUtils.TERM_ID, term_id);
			insertMap.put(ShareFieldUtils.SERVICE_CODE, service_code);
			insertMap.put(ShareFieldUtils.BIND_FLAG, bind_flag);
			if (mer == null)
			{
				this.insert(TableCollection.USER_REMEMBER_INFO, insertMap);
			}
			else
			{
				mer.put(ShareFieldUtils.BIND_FLAG, bind_flag);
				this.update(TableCollection.USER_REMEMBER_INFO, mer);
			}
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************MemoryBind error:" + e);
		}
		return null;
	}

	@Override
	public Map<String, byte[]> MemoryDown(Map<String, String> map)
	{
		String flag = map.get(ShareFieldUtils.FLAG);
		String term_id = map.get(ShareFieldUtils.TERM_ID);
		Map<String, String> limitMap = new HashMap<String, String>();
		limitMap.put(ShareFieldUtils.TERM_ID, term_id);
		limitMap.put(ShareFieldUtils.FLAG, flag);
		Map<String, byte[]> retMap = null;
		try
		{
			List<Map<String, String>> getMer = this.findDataByDatabase(TableCollection.USER_REMEMBER_INFO, limitMap);
			StringBuffer service_code = null;
			for (Map<String, String> map2 : getMer)
			{
				String code = map2.get(ShareFieldUtils.SERVICE_CODE);
				if (StringUtils.isNotBlank(code))
				{
					if (service_code == null)
					{
						service_code = new StringBuffer();
						service_code.append(code);
					}
					else
					{
						service_code.append("," + code);
					}
				}
			}
			if (service_code != null)
			{
				String[] codes = service_code.toString().split(",");
				map.put(ShareFieldUtils.SERVICE_CODE, codes.toString());
			}
			retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.ZONE_ADD_NODE_RETURN, map);
		}
		catch (DAOException e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************MemoryDown error:" + e);
		}
		catch (UseException e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************MemoryDown error:" + e);
		}
		return retMap;
	}

	@Override
	public Map<String, byte[]> FindPassword(Map<String, String> map)
	{
		String username = map.get(ShareFieldUtils.USERNAME);
		try
		{
			this.stringIsEmpty(username, RetCode.USERNAME_IS_ERROR);
			Map<String, String> limitMap = new HashMap<String, String>();
			limitMap.put(ShareFieldUtils.USERNAME, username);
			this.getDataByRedisOrDatabase(TableCollection.LOGON_USER, username, limitMap, true, RetCode.USERNAME_IS_ERROR, false);
			// TODO生成校验码,发送给用户
			int captcha = random.nextInt(randomLength);
			System.out.println(captcha);
			String mac = map.get(ShareFieldUtils.MAC_NO);
			// 存内存
			String tempKey = "FIND_PASSWORD_CAP_" + mac;
			Map<String, String> redisTemp = new HashMap<String, String>();
			redisTemp.put(ShareFieldUtils.CAPTCHA, String.valueOf(captcha));
			RedisUtils.set(tempKey, BytesUtils.ObjectToByte(redisTemp), maxLive);
		}
		catch (UseException e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************FindPassword error:" + e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************FindPassword error:" + e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, byte[]> validateFindPassword(Map<String, String> map)
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		String username = map.get(ShareFieldUtils.USERNAME);
		Map<String, byte[]> retMap = null;
		try
		{
			String capicha = map.get(ShareFieldUtils.CAPTCHA);
			String tempKey = "FIND_PASSWORD_CAP_" + username;
			byte[] objectByte = RedisUtils.get(tempKey);
			if (objectByte == null)
			{
				log.error("USERNAME:" + username + " REGISTER OVERTIME");
				throw new UseException(RetCode.CAPTCHA_OVERTIME);
			}
			else
			{
				Map<String, String> mapObject = (Map<String, String>) BytesUtils.byteToObject(objectByte);
				String tempCapicha = mapObject.get(ShareFieldUtils.CAPTCHA);
				if (tempCapicha != null && tempCapicha.equals(capicha))
				{
					// 判断posid是否为空
					this.stringIsEmpty(username, RetCode.POSID_IS_EMPTY);
					
					Map<String, String> limitLogonUser = new HashMap<String, String>();
					// 判断pos机是否存在
					limitLogonUser.put(ShareFieldUtils.USERNAME, username);
					
					Map<String, String> logonUser = this.getDataByRedisOrDatabase(TableCollection.LOGON_USER, username, limitLogonUser, true, RetCode.USERNAME_NOT_EXIST, false);

					String password = logonUser.get(ShareFieldUtils.PASSWORD);

					this.stringIsEmpty(password, RetCode.PASSWORD_IS_ERROR);
					map.put(ShareFieldUtils.ID, logonUser.get(ShareFieldUtils.ID));
					sendRedisUdt(TableCollection.LOGON_USER.toString(), username);
					this.update(TableCollection.LOGON_USER, map);
					RedisUtils.del(tempKey);
					retMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.MER_CHECK_CODE_RETURN, map);
				}
				else
				{
					log.error("USERNAME:" + username + "REGISTER_APTCHA ERROR");
					throw new UseException(RetCode.CAPTCHA_ERROR);
				}
			}
		}
		catch (UseException e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************MerTransInit error:" + e);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************MerTransInit error:" + e);
		}
		StaticMethod.getTraceInfo();
		return retMap;
	}

}
