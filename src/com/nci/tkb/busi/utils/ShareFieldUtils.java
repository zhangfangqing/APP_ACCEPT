package com.nci.tkb.busi.utils;

/**
 * 业务共享数据类
 * 
 * @author LYP
 * @version 1.0
 * @Date 2014-2-20
 */
public class ShareFieldUtils
{
	/**
	 * 调用模块
	 */
	public static String CALL_MUDLE_KEY = "CALL_MUDLE_KEY";

	/**
	 * UUID
	 */
	public static String UUID = "UUID";

	/**
	 * 用户账户
	 */
	public static String USERNAME = "USERNAME";

	/**
	 * 密码
	 */
	public static String PASSWORD = "PASSWORD";

	/**
	 * 是否加密 加密标识 1:证书加密 2:对称加密
	 */
	public static String ENCRY_FLAG = "ENCRY_FLAG";

	/**
	 * 请求数据（发送到业务模块）
	 */
	public static String REQ_MESSAGE = "REQ_MESSAGE";

	/**
	 * 路由代码
	 */
	public static String ROUTER_KEY = "ROUTER_KEY";

	/**
	 * 通卡数据
	 */
	public static String SERVICE_DATA = "SERVICE_DATA";

	/**
	 * 密钥KEY
	 */
	public static String ENCRY_KEY = "ENCRY_KEY_DES";

	/**
	 * 子JSON数据（用于加密）
	 */
	public static String SUN_JSONOBJ = "SUN_JSONOBJ";

	/**
	 * 路由代码
	 */
	public static String ROUTE_CODE = "ROUTE_CODE";

	/**
	 * 通卡公司
	 */
	public static String CARD_COMPANY = "CARD_COMPANY";

	/**
	 * IP
	 */
	public static String IP = "IP";

	/**
	 * PORT
	 */
	public static String PORT = "PORT";

	/**
	 * 协议长度位标
	 */
	public static String LEN_SIGN = "LEN_SIGN";

	/**
	 * 协议长度字节数
	 */
	public static String LEN_NUM = "LEN_NUM";

	/**
	 * 编码格式(0：ascii，1：bcd，2：hex)
	 */
	public static String FORMAT = "FORMAT";

	/**
	 * 长度包含范围(0：不包含长度，1：包含长度)
	 */
	public static String IS_INCLUDE = "IS_INCLUDE";

	/**
	 * 业务类型
	 */
	public static String BUSI_TYPE = "BUSI_TYPE";

	/**
	 * 请求消息长度
	 */
	public static String REQ_MSG_LENGTH = "REQ_MSG_LENGTH";

	/**
	 * 请求消息内容
	 */
	public static String REQ_MSG = "REQ_MSG";

	/**
	 * 返回消息长度
	 */
	public static String RESP_MSG_LENGTH = "RESP_MSG_LENGTH";

	/**
	 * 返回消息内容
	 */
	public static String RESP_MSG = "RESP_MSG";

	/**
	 * 返回消息代码
	 */
	public static String RESP_MSG_CODE = "RESP_MSG_CODE";

	/**
	 * protocol
	 */
	public static String protocol = "protocol";

	/**
	 * RET_CODE
	 */
	public static String RET_CODE = "RET_CODE";

	/**
	 * STATUS
	 */
	public static String STATUS = "STATUS";

	/**
	 * 业务代码
	 */
	public static String BUSI_CODE = "BUSI_CODE";

	/**
	 * 请求码
	 */
	public static String CMD_NO = "CMD_NO";

	/**
	 * 命令类型
	 */
	public static String MSG_TYPE = "MSG_TYPE";

	/**
	 * 证书序列号
	 */
	public static String CER_SN = "CER_SN";

	/**
	 * ID
	 */
	public static String ID = "ID";

	/**
	 * TABLE
	 */
	public static String TABLE = "TABLE";

	/**
	 * 商户POS表
	 */
	public static String MER_POS = "MER_POS";

	/**
	 * 用户信息表
	 */
	public static String USER_INFO = "USER_INFO";

	/**
	 * 用户登录表
	 */
	public static String LOGON_USER = "LOGON_USER";

	/**
	 * 交易金额,消费总额
	 */
	public static String PAY_MONEY = "PAY_MONEY";

	/**
	 * \ json类 加密字段集合 将String转换为Json对象，取出各字段
	 */
	public static String ENTITY_OBJ = "ENTITY_OBJ";

	/**
	 * 银行卡号
	 */
	public static String CARD_NO = "CARDNO";

	/**
	 * 银行订单号
	 */
	public static String ORDERID = "ORDERID";

	/**
	 * 交易时间
	 */
	public static String TRANS_TIME = "TRANS_TIME";

	/**
	 * 纳费POS数
	 */
	public static String POS_COUNT = "POS_COUNT";

	/**
	 * 纳费列表
	 */
	public static String PAY_LIST = "PAY_LIST";

	/**
	 * 商户表
	 */
	public static String MERCHANT_INFO = "MERCHANT_INFO";

	/**
	 * 校验码
	 */
	public static String CAPTCHA = "CAPTCHA";

	/**
	 * 是否已注册，并完成验证,状态：0为未验证，1为已验证通过
	 */
	public static String STATE = "STATE";

	/**
	 * 应用类型
	 */
	public static String APP_MODE = "APP_MODE";

	/**
	 * POSID
	 */
	public static String POS_ID = "POSID";

	/**
	 * 月数
	 */
	public static String MONTHS = "MOUTHS";

	/**
	 * 费用
	 */
	public static String PAYFEE = "PAYFEE";

	/**
	 * 开始有效期
	 */
	public static String CER_START = "CER_START";

	/**
	 * 结束日期
	 */
	public static String CER_END = "CER_END";

	/**
	 * 商户编码
	 */
	public static String MERCHANT_CODE = "MERCHANT_CODE";

	/**
	 * 操作员
	 */
	public static String OPERATOR = "OPERATOR";

	/**
	 * 用户id
	 */
	public static String USER_ID = "USER_ID";

	/**
	 * 角色id
	 */
	public static String ROLE_ID = "ROLE_ID";

	/**
	 * 服务方代码
	 */
	public static String SERVICE_CODE = "SERVICE_CODE";

	/**
	 * 证书类别(1：用户,2：运营商,如为1,则运营商编码不填)
	 */
	public static String CER_MODE = "CER_MODE";

	/**
	 * 运营商编码
	 */
	public static String CARRIER_CODE = "CARRIER_CODE";

	/**
	 * 自已数字证书
	 */
	public static String CER_DIGITAL = "CER_DIGITAL";

	/**
	 * 自已证书序列号
	 */
	public static String CER_SN_ME = "CER_SN";

	/**
	 * 最后编辑时间
	 */
	public static String EDIT_TIME = "EDIT_TIME";

	/**
	 * 创建时间
	 */
	public static String CREATE_TIME = "CREATE_TIME";

	/**
	 * 证书信息表
	 */
	public static String CER_INFO = "CER_INFO";

	/**
	 * 证书库表
	 */
	public static String KEYSTORE_INFO = "KEYSTORE_INFO";

	public static final byte[] KEYBYTES =
	{
			0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
			(byte) 0xE2
	};

	/**
	 * 空闲证书
	 */
	public static String FREEL_CER_KEY = "FREEL_CER_KEY";

	/**
	 * 运营商数字证书
	 */
	public static String SERVICE_CER_KEY = "SERVICE_CER_KEY";

	/**
	 * POS数字证书
	 */
	public static String PRIVATE_CER_KEY = "PRIVATE_CER_KEY";

	/**
	 * 证书类别
	 */
	public static String CER_TYPE = "CER_TYPE";

	/**
	 * POSID
	 */
	public static String POSID = "POSID";

	/**
	 * 最近登录时间
	 */
	public static String LAST_LOGONTIME = "LAST_LOGONTIME";

	/**
	 * 对称密钥
	 */
	public static String DESKEY = "DESKEY";

	/**
	 * 商户编号
	 */
	public static String MER_CODE = "MER_CODE";

	/**
	 * 商户ID
	 */
	public static String MER_ID = "MER_ID";

	/**
	 * 新密码
	 */
	public static String PASSWORD_NEW = "PASSWORD_NEW";

	/**
	 * 是否绑定 0:未绑定 1:绑定
	 */
	public static String IS_BIND = "IS_BIND";

	/**
	 * 手机mac地址
	 */
	public static String MAC_NO = "MAC_NO";

	/**
	 * 请求返回码 协议体括号中 M M
	 */
	public static String REQ_CODE = "REQ_CODE";
	/**
	 * 响应结果 M
	 */

	public static String RESP_CODE = "RESP_CODE";
	/**
	 * 响应码说明 返回码为0不填 S
	 */
	public static String RESP_DES = "RESP_DES";
	/**
	 * String MD5摘要 ENTITY_OBJ字符串的前5与后5字节的MD5摘要值 S S
	 */
	public static String MD5_MAC = "MD5_MAC";

	/**
	 * String 协议版本号 M
	 */
	public static String PROTOCOL_VER = "PROTOCOL_VER";
	/**
	 * String 应用版本 最新应用版本 M
	 */
	public static String APP_VER = "APP_VER";
	/**
	 * String 参数版本 公共参数版本 M
	 */
	public static String SYS_PARAM = "SYS_PARAM";
	/**
	 * Int 测试环境 0：测试环境 1：正式环境 M
	 */
	public static String IS_TEST = "IS_TEST";
	/**
	 * String 加密 0：不加密 1：加密 M M
	 */
	public static String IS_ENCRYPT = "IS_ENCRYPT";
	/**
	 * String 机型版本 手机型号OS系统 M
	 */
	public static String DEV_VER = "DEV_VER";
	/**
	 * String 经度 获取不到不填 S
	 */
	public static String LAT = "LAT";
	/**
	 * String 纬度 获取不到不填 S
	 */
	public static String LON = "LON";

	/**
	 * String 数据 根据数据类型定数据 S S
	 */
	public static String DATA = "DATA";

	/**
	 * 用户类型
	 */
	public static String USER_TYPE = "USER_TYPE";

	/**
	 * 子账户列表
	 */
	public static String CHILD_ACCOUNT_LIST = "CHILD_ACCOUNT_LIST";

	/**
	 * POSID列表
	 */
	public static String POSID_LIST = "POSID_LIST";

	/**
	 * MAC地址（IP地址）
	 */
	public static String MAC_OR_IP = "MAC_OR_IP";

	/**
	 * 登录类别 0:手机 1:PC
	 */
	public static String LOGON_TYPE = "LOGON_TYPE";

	/**
	 * 等级
	 */
	public static String GRADE = "GRADE";

	/**
	 * 商户等级
	 */
	public static String MER_GRADE = "MER_GRADE";

	/**
	 * 证书单价
	 */
	public static String CER_PERFEE = "CER_PERFEE";

	/**
	 * 商户额度限制
	 */
	public static String MER_AMOUNT_LIMIT = "MER_AMOUNT_LIMIT";

	/**
	 * POS状态
	 */
	public static String POS_STATE = "POS_STATE";

	/**
	 * 证书状态
	 */
	public static String CER_STATE = "CER_STATE";

	/**
	 * 类别 1:用户名 2:POSID
	 */
	public static String FLAG = "FLAG";

	/**
	 * 终端用户 用户或者pos机
	 */
	public static String TERM_ID = "TERM_ID";

	/**
	 * 绑定标识 0:取消1：绑定
	 */
	public static String BIND_FLAG = "BIND_FLAG";

	/**
	 * 服务方类别代码
	 */
	public static String S_TYPE_CODE = "S_TYPE_CODE";

	/**
	 * APP升级日志
	 */
	public static String APP_GRADE_LOG = "APP_GRADE_LOG";
	/**
	 * String APP下载地址
	 */
	public static String APP_DOWN_URL = "APP_DOWN_URL";
	/**
	 * Int 累计充值限额
	 */
	public static String ALL_CHARGE_LIMIT = "ALL_CHARGE_LIMIT";
	/**
	 * Int 累计消费额度
	 */
	public static String ALL_CONSUME_LIMIT = "ALL_CONSUME_LIMIT";
	/**
	 * Int 单次充值限额
	 */
	public static String PER_CHARGE_LIMIT = "PER_CHARGE_LIMIT";
	/**
	 * Int 单次消费额度
	 */
	public static String PER_CONSUME_LIMIT = "PER_CONSUME_LIMIT";

	/**
	 * 服务类别列表
	 */
	public static String SERVICE_TYPE_LIST = "SERVICE_TYPE_LIST";

}
