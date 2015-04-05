package com.nci.tkb.busi.serviceimpl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nci.tkb.busi.exception.DAOException;
import com.nci.tkb.busi.exception.UseException;
import com.nci.tkb.busi.redis.RedisUtils;
import com.nci.tkb.busi.service.ReqCertHandlerService;
import com.nci.tkb.busi.utils.RetCode;
import com.nci.tkb.busi.utils.ShareFieldUtils;
import com.nci.tkb.busi.utils.SqlMothed;
import com.nci.tkb.busi.utils.SqlUtils;
import com.nci.tkb.busi.utils.StaticMethod;
import com.npass.pay.memshare.client.BytesUtils;

/**
 * Description 证书业务处理（请求）
 * 
 * @author ZYB
 * @version 1.0
 * @Date 2014-03-07
 */
public class ReqCertHandlerServiceImpl extends HandlerUtil implements ReqCertHandlerService
{
	static Logger log = Logger.getLogger(ReqCertHandlerServiceImpl.class.getName());
	
	/**
	 * 证书纳费
	 * @param map
	 * @return
	 * @throws JSONException
	 */
	public Map<String, byte[]> CerPayMent(Map<String,String> map)
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		// 传入参数日志
		StaticMethod.printParamLog(map);
		// sqlArray
		String[] sqlArray = null;
		Map<String, byte[]> resMap = new HashMap<String, byte[]>();
		try
		{
			String json = map.get(ShareFieldUtils.REQ_MESSAGE);
			JSONObject reqJson = new JSONObject(json);
			// 交易商户
			String userId = map.get(ShareFieldUtils.USERNAME);
			//商户编码
			String merCode = map.get(ShareFieldUtils.MERCHANT_CODE);
			// 银行卡号
			String cardNo = map.get(ShareFieldUtils.CARD_NO);
			// 银行订单号
			String orderid = map.get(ShareFieldUtils.ORDERID);
			// 交易时间
			String transTime = map.get(ShareFieldUtils.TRANS_TIME);
			// 纳费POS数/证书数量
			String posCount = map.get(ShareFieldUtils.POS_COUNT);
			// 交易总金额
			String payMoney = map.get(ShareFieldUtils.PAY_MONEY);
			// 纳费列表
			JSONArray jsa = reqJson.getJSONArray(new String(map.get(ShareFieldUtils.PAY_LIST)));
			String sql = null;

			// 1.验证商户是否合法
			Map<String, String> ruMap = getMerInfo(merCode);
			if (ruMap.size() > 1)
			{
				// 2.将数据插入证书纳费表
				ruMap.put(ShareFieldUtils.CARD_NO, cardNo);
				ruMap.put(ShareFieldUtils.ORDERID, orderid);
				ruMap.put(ShareFieldUtils.TRANS_TIME, transTime);
				ruMap.put(ShareFieldUtils.POS_COUNT, posCount);
				ruMap.put(ShareFieldUtils.PAY_MONEY, payMoney);
				sql = SqlMothed.getAddCerPayMentSql(ruMap, "CERT_PAYMENT");
				sqlArray[0] = sql;
				dbDao.update(sqlArray);
			}
			//3.遍历明细数据
			for (int i = 0; i < jsa.length(); i++) 
			{
				String posId = jsa.getJSONObject(i).getString(ShareFieldUtils.POS_ID);
				String cerSn = jsa.getJSONObject(i).getString(ShareFieldUtils.CER_SN);
				int months = jsa.getJSONObject(i).getInt(ShareFieldUtils.MONTHS);
				ruMap.put(ShareFieldUtils.POS_ID, jsa.getJSONObject(i).getString(ShareFieldUtils.POS_ID));
				ruMap.put(ShareFieldUtils.MONTHS, jsa.getJSONObject(i).getString(ShareFieldUtils.MONTHS));
				ruMap.put(ShareFieldUtils.PAYFEE, jsa.getJSONObject(i).getString(ShareFieldUtils.PAYFEE));
				ruMap.put(ShareFieldUtils.CER_SN, jsa.getJSONObject(i).getString(ShareFieldUtils.CER_SN));
				ruMap.put(ShareFieldUtils.CER_START, jsa.getJSONObject(i).getString(ShareFieldUtils.CER_START));
				ruMap.put(ShareFieldUtils.CER_END, jsa.getJSONObject(i).getString(ShareFieldUtils.CER_END));
				sql = SqlMothed.getAddCerPayMentSql(ruMap, "CERT_PAY_ADDLIST");
				sqlArray[0] = sql;
				//4.将数据添回至证书纳费附表
				dbDao.update(sqlArray);
				
				//5.查询POS是否在该商户下
				boolean result = getPosByMerInfo(posId,merCode);
				if(result == true)
				{
					//6.查询POS是否绑定证书
					boolean results = getPosByCertInfo(posId,cerSn);
					if(results == true)
					{
						//将缴费时间累加到POS结束有效期
						String endDate = ruMap.get("END_DATE");
						
						Calendar  cc = Calendar.getInstance();
						cc.setTime(new Date(endDate));
						cc.set(Calendar.DAY_OF_MONTH, cc.get(Calendar.DAY_OF_MONTH) + months);
						
						String newDate  = cc.getTime().toString();
						//更新结束有效期
						updateMerPos(merCode,newDate);
						
					}
					else
					{
						//6.1.如果没有绑定证书,则查询内存是否有空闲证书
						byte[] merbt = RedisUtils.getBHashMap(ShareFieldUtils.FREEL_CER_KEY, ShareFieldUtils.CER_SN);
					}
				}
				else
				{
					continue;
				}
			}
		}
		catch (Exception e)
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************CerPayMent error:" + e);
		}
		return resMap;
	}

	/**
	 * 证书请求
	 */
	public Map<String, byte[]> CerRequest(Map<String, String> map) 
	{
		// 方法头日志
		StaticMethod.printMethodStartLog();
		// 传入参数日志
		StaticMethod.printParamLog(map);
		Map<String,String> cerMap = new HashMap<String, String>();
		try 
		{
			//证书类别
			String cerType = new String(map.get(ShareFieldUtils.CER_TYPE));
			if(cerType == "0")
			{
				cerMap = reqCerList(ShareFieldUtils.PRIVATE_CER_KEY, map.get(ShareFieldUtils.POSID).toString());
			}
			else
			{
				cerMap = reqCerList(ShareFieldUtils.SERVICE_CER_KEY, map.get(ShareFieldUtils.SERVICE_CODE).toString());
			}
		} 
		catch (Exception e) 
		{
			log.info(StaticMethod.getTraceInfo());
			log.error("****************CerRequest error:" + e);
		}
		return null;
	}
	
	
	/**
	 * 根据序列号查询证书信息
	 * @param cerSn
	 * @return
	 * @throws UseException
	 */
	public Map<String, String> getByCerSnInfo(String cerSn) throws UseException
	{
		Map<String, String> retMap = null;
		// 判断取值,不为空
		if (null != cerSn && !"".equals(cerSn))
		{
			byte[] merbt = RedisUtils.getBHashMap(ShareFieldUtils.CER_INFO, cerSn);

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
				sqlArray[0] = cerSn;
				String sqlUtil = SqlUtils.sqlAddParam(SqlUtils.SQL_QUERY_CER_INFO, sqlArray);
				// 获取查询语句
				String sql = SqlUtils.sqlAddParam(sqlUtil, sqlArray);
				// 查询数据
				try
				{
					retMap = dbDao.queryInfoAllowNo(sql, RetCode.BUSI_HANDLER_ERROR);
					if (retMap != null)
					{
						// 将数据通过MQ发送到redis'
						sendRedisUdt(ShareFieldUtils.CER_INFO, retMap.get(ShareFieldUtils.ID));
					}
				}
				catch (DAOException e)
				{
					log.info(StaticMethod.getTraceInfo());
					log.error("getKeystoneByCerSnInfo************query error:" + e);
					throw new UseException(RetCode.BUSI_HANDLER_ERROR, e);
				}
			}
			log.debug("********************getDataInfo:3");
		}

		StaticMethod.logmark(1);
		return retMap;
	}
	
	/**
	 * 根据证书库名查询证书库
	 * @param cerSn
	 * @return
	 * @throws UseException
	 */
	public Map<String, String> getKeystoneByName(String storeName) throws UseException
	{
		Map<String, String> retMap = null;
		// 判断取值,不为空
		if (null != storeName && !"".equals(storeName))
		{
			byte[] merbt = RedisUtils.getBHashMap(ShareFieldUtils.KEYSTORE_INFO, storeName);

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
				sqlArray[0] = storeName;
				String sqlUtil = SqlUtils.sqlAddParam(SqlUtils.SQL_QUERY_KEYSTORE_INFO, sqlArray);
				// 获取查询语句
				String sql = SqlUtils.sqlAddParam(sqlUtil, sqlArray);
				// 查询数据
				try
				{
					retMap = dbDao.queryInfoAllowNo(sql, RetCode.BUSI_HANDLER_ERROR);
					if (retMap != null)
					{
						// 将数据通过MQ发送到redis'
						sendRedisUdt(ShareFieldUtils.KEYSTORE_INFO, retMap.get(ShareFieldUtils.ID));
					}
				}
				catch (DAOException e)
				{
					log.info(StaticMethod.getTraceInfo());
					log.error("getKeystoneByName************query error:" + e);
					throw new UseException(RetCode.BUSI_HANDLER_ERROR, e);
				}
			}
			log.debug("********************getDataInfo:3");
		}

		StaticMethod.logmark(1);
		return retMap;
	}
	
}
