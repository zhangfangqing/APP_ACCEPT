package com.nci.tkb.busi.utils;

import java.util.List;

public class SqlArrayUtils
{
	/**
	 * 将字符串组装成数组
	 * 
	 * @param sqls
	 * @return
	 */
	public static String[] StringsToArray(String... strings)
	{
		int size = strings.length;
		String sqlArray[] = new String[size];
		for (int i = 0; i < size; i++)
		{
			sqlArray[i] = strings[i];
		}
		return sqlArray;
	}

	/**
	 * 将List组装成数组
	 * @param strings
	 * @return
	 */
	public static String[] ListToArray(List<String> strings)
	{
		int size = strings.size();
		String sqlArray[] = new String[strings.size()];
		for (int i = 0; i < size; i++)
		{
			sqlArray[i] = strings.get(i);
		}
		return sqlArray;
	}
}
