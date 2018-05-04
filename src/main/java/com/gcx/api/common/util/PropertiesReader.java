package com.gcx.api.common.util;

import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *<p>Title:PropertiesReader</p>
 *<p>Description:properties文件操作</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2017年7月5日</p>
 */
public class PropertiesReader
{
	/**
	 * 取得指定properties文件的指定key的value
	 * 
	 * @param file_name
	 *            properties文件的名字（没有扩展名）
	 * @param key
	 *            所指定的key
	 * @return 指定key对应的value值
	 * @throws MissingResourceException
	 *             当没有这个properties文件，或该文件中不存在这个key时
	 */
	public static String getValue(String file_name, String key) throws MissingResourceException
	{
		final ResourceBundle res = ResourceBundle.getBundle(file_name);
		String value = res.getString(key);
		return value.trim();
	}

	
	/**
	 * 将文件中配置信息填充到properties对象中
	 * 
	 * @see
	 * @param file_name
	 * @return
	 */
	public static Properties fillProperties(String file_name) throws MissingResourceException
	{
		Properties properties = new Properties();
		final ResourceBundle res = ResourceBundle.getBundle(file_name);
		Enumeration<String> en = res.getKeys();
		String key = null;
		String value = null;
		while (en.hasMoreElements())
		{
			key = en.nextElement().trim();
			value = res.getString(key);
			properties.setProperty(key, value.trim());
		}
		return properties;
	}

}
