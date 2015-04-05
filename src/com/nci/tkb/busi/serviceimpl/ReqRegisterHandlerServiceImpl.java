package com.nci.tkb.busi.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import com.nci.tkb.busi.exception.UseException;
import com.nci.tkb.busi.redis.RedisUtils;
import com.nci.tkb.busi.service.ReqRegisterHandlerService;
import com.nci.tkb.busi.utils.CmdnoUtils;
import com.nci.tkb.busi.utils.EmailUtils;
import com.nci.tkb.busi.utils.LogonRoleUtils;
import com.nci.tkb.busi.utils.RetCode;
import com.nci.tkb.busi.utils.ShareFieldUtils;
import com.nci.tkb.busi.utils.StaticMethod;
import com.npass.pay.memshare.client.BytesUtils;

public class ReqRegisterHandlerServiceImpl extends HandlerUtil implements ReqRegisterHandlerService
{
	@Override
	public Map<String, byte[]> register(Map<String, String> map)
	{

		// 方法头日志
		StaticMethod.printMethodStartLog();
		String userName = map.get(ShareFieldUtils.USERNAME);

		Map<String, String> queryMap;
		Map<String, byte[]> reqMap = null;
		try
		{
			String appMode = map.get(ShareFieldUtils.APP_MODE);
			this.stringIsEmpty(appMode, RetCode.APP_MODE_ERROR);
			String appModeId = LogonRoleUtils.valueOf(appMode).id;
			this.stringIsEmpty(appMode, RetCode.APP_MODE_ERROR);
			this.stringIsEmpty(userName, RetCode.USERNAME_IS_ERROR);
			// 判断手机号码是否合法
			this.checkValidUserName(userName);
			// 验证用户名是否存在或使用
			queryMap = this.getUserInfo(userName);
			map.put(ShareFieldUtils.APP_MODE, appModeId);
			if (queryMap == null)
			{
				// 存数据库
				this.insertLogonUser(map);
			}
			else
			{
				// 查看STATE，0为已注册，1为已验证
				String state = queryMap.get(ShareFieldUtils.STATE);
				if (state != null)
				{
					// 查看状态码，如果已注册，则返回返回码
					// 未注册，添加验证码，有效期，存内存
					if ("0".equals(state))
					{
						// 更新数据库
						String newPassWord = map.get(ShareFieldUtils.PASSWORD);
						queryMap.put(ShareFieldUtils.APP_MODE, appModeId);
						queryMap.put(ShareFieldUtils.PASSWORD, newPassWord);
						this.updateLogonUser(queryMap);
					}
					if ("1".equals(state))
					{
						log.error("USERNAME:" + queryMap.get(ShareFieldUtils.USERNAME) + "HAD REGISTER");
						throw new UseException(RetCode.USERNAME_EXIST);
					}
				}

			}
			// TODO生成校验码,发送给用户
			int captcha = random.nextInt(randomLength);
			System.out.println(captcha);
			Map<String, Object> record = new HashMap<String, Object>();
			record.put("captcha", captcha);
			EmailUtils.sendSimpleMail("手机注册验证码", "1316842603@qq.com", "vms/captchaTemplate.vm", record);
			// int captcha = 123456;
			// 存内存
			String tempKey = "REG_CAP_" + userName;
			Map<String, String> redisTemp = new HashMap<String, String>();
			redisTemp.put(ShareFieldUtils.CAPTCHA, String.valueOf(captcha));
			RedisUtils.set(tempKey, BytesUtils.ObjectToByte(redisTemp), maxLive);
			reqMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.REG_INPUT_MOBILE_RETURN, map);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************Register error:" + e);
		}
		return reqMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, byte[]> validateRegister(Map<String, String> map)
	{
		Map<String, byte[]> reqMap = null;
		try
		{
			String userName = map.get(ShareFieldUtils.USERNAME);
			String capicha = map.get(ShareFieldUtils.CAPTCHA);
			String tempKey = "REG_CAP_" + userName;
			byte[] objectByte = RedisUtils.get(tempKey);
			if (objectByte == null)
			{
				log.error("USERNAME:" + userName + "REGISTER OVERTIME");
				throw new UseException(RetCode.CAPTCHA_OVERTIME);
			}
			else
			{
				Map<String, String> mapObject = (Map<String, String>) BytesUtils.byteToObject(objectByte);
				String tempCapicha = mapObject.get(ShareFieldUtils.CAPTCHA);
				if (tempCapicha != null && tempCapicha.equals(capicha))
				{
					RedisUtils.del(tempKey);
					this.registerSuccess(map);
				}
				else
				{
					log.error("USERNAME:" + userName + " REGISTER_APTCHA ERROR");
					throw new UseException(RetCode.CAPTCHA_ERROR);
				}
			}
			reqMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.REG_CHECK_CODE_RETURN, map);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************Register error:" + e);
		}
		return reqMap;
	}

	@Override
	public Map<String, byte[]> completeUserInfo(Map<String, String> map)
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		Map<String, byte[]> reqMap = null;
		try
		{
			String userName = map.get(ShareFieldUtils.USERNAME);
			this.stringIsEmpty(userName, RetCode.USERNAME_IS_ERROR);
			Map<String, String> queryMap = this.getRegisterUserInfo(userName);
			this.mapIsNull(queryMap, RetCode.USERNAME_IS_ERROR);
			String logon_user_id = queryMap.get(ShareFieldUtils.ID);
			Map<String, String> roleUserMap = this.getRoleUser(logon_user_id);
			this.mapIsNull(roleUserMap, RetCode.USERNAME_IS_ERROR);
			this.compareRole(LogonRoleUtils.U_1.id, roleUserMap.get(ShareFieldUtils.ROLE_ID));
			this.updateUserInfo(map);
			reqMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.REG_ADD_USERINFO_RETURN, map);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************completeUserInfo error:" + e);
		}
		StaticMethod.printMethodStartLog();
		return reqMap;
	}

	@Override
	public Map<String, byte[]> completeMerchantInfo(Map<String, String> map)
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		Map<String, byte[]> reqMap = null;
		try
		{
			// 用户名
			String userName = map.get(ShareFieldUtils.USERNAME);
			// 判断用户名是否为空
			this.stringIsEmpty(userName, RetCode.USERNAME_IS_ERROR);
			Map<String, String> queryMap = this.getRegisterUserInfo(userName);
			// 判断是否为空
			this.mapIsNull(queryMap, RetCode.USERNAME_IS_ERROR);
			String logon_user_id = queryMap.get(ShareFieldUtils.ID);
			// 获得用户角色
			Map<String, String> roleUserMap = this.getRoleUser(logon_user_id);
			this.mapIsNull(queryMap, RetCode.USERNAME_IS_ERROR);
			this.compareRole(LogonRoleUtils.M_1.id, roleUserMap.get(ShareFieldUtils.ROLE_ID));
			this.updateMerchantInfo(map);
			reqMap = ResponseHandlerUtils.getResponseToByte(CmdnoUtils.REG_ADD_MERINFO_RETURN, map);
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************completeMerchantInfo error:" + e);
		}
		StaticMethod.printMethodStartLog();
		return reqMap;
	}
}
