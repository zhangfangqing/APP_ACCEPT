package com.nci.tkb.busi.utils;

public enum LogonRoleUtils
{

	/**
	 * 用户角色，id为2000
	 */
	U_1("2000"),
	/**
	 * 商户角色，id为3000
	 */
	M_1("3000");
	public String id;

	private LogonRoleUtils(String id)
	{
		this.id = id;
	}

}
