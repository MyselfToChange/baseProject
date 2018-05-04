package com.gcx.api.common.util;

import java.util.UUID;

/** 
 * @author	yang 
 * @version 创建时间：2017年4月18日 上午9:38:57 
 * 类说明:	
 */
public class UUIDUtils {

	public static String createUuid(){
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
}
