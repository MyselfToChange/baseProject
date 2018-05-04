package com.gcx.api.common.util;

import javax.servlet.http.HttpServletRequest;

import com.gcx.api.common.model.User;
import com.gcx.api.common.redis.UserRedisUtils;
import com.gcx.api.common.spring.SpringContextHolder;
/**
 *<p>Title:UserUtils</p>
 *<p>Description:用户工具类</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2017年6月21日</p>
 */
public class UserUtils {
//	private static CacheUtil cacheUtil = SpringContextHolder.getBean(CacheUtil.class);
	private static UserRedisUtils cacheUtil = SpringContextHolder.getBean(UserRedisUtils.class);
	public static User getUser(HttpServletRequest request){
//		  User user=null;
//		  String gcxId="";
//		  Cookie[] cookie=request.getCookies();
//		  if(cookie!=null){
//			  for (Cookie cookie2 : cookie) {
//				  if(cookie2.getName().equals("GCXID"))
//					  gcxId=cookie2.getValue();
//			  }
//		  }
		User user = cacheUtil.getUser(request);
		return user;
	}
}
