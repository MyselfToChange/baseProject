package com.gcx.api.common.redis;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.gcx.api.common.model.User;
import com.gcx.api.common.util.CookieUtils;
import com.gcx.api.common.util.MD5;


/** 
 * @author	yang 
 * @version 创建时间：2017年4月18日 下午4:40:14 
 * 类说明:	
 */
@Component
public class UserRedisUtils {
	
	
//	@Value("${gcxId}")
//	String gcxId;
	String gcxId = "GCXID";
	
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    public UserRedisUtils(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    } 

	//返回null 表示已过期或者未登录
	public User getUser(HttpServletRequest request) {
		String authId = CookieUtils.getCookieValue(request, gcxId);
		String userJSON = redisTemplate.opsForValue().get("user:"+authId);
		if("".equals(userJSON) || userJSON==null){
			return null;
		}
		User user = JSON.parseObject(userJSON, User.class);
		
		return user;
	}
	//返回null 表示已过期或者未登录
	public User getAppUser(HttpServletRequest request) {
		String userName =request.getParameter("userName");
		String userJSON = redisTemplate.opsForValue().get("appUser:"+MD5.MD5ofStr(userName.trim()));
		if("".equals(userJSON) || userJSON==null){
			return null;
		}
		User user = JSON.parseObject(userJSON, User.class);
		
		return user;
	}
	//返回null 表示已过期   图片验证码
	public String getImageCode(HttpServletRequest request) {
		String authId = CookieUtils.getCookieValue(request, gcxId);
		String imageCode = redisTemplate.opsForValue().get("imageCheckCode:"+authId);
		if("".equals(imageCode) || imageCode==null){
			return null;
		}
		return imageCode;
	}
	//返回null 表示已过期  手机验证码
	public String getMsgCode(HttpServletRequest request) {
		String authId = CookieUtils.getCookieValue(request, gcxId);
		String msgCode = redisTemplate.opsForValue().get("msgCheckCode:"+authId);
		if("".equals(msgCode) || msgCode==null){
			return null;
		}
		return msgCode;
	}
	//字符串自增
	public void incrString(String key,Integer value){
		redisTemplate.opsForValue().increment(key, value);
	}
	//==========================Hash操作===============================
	//hashkey自增
	public void incrHash(String key,Object hashKey,Integer delta){
		redisTemplate.opsForHash().increment(key, hashKey, delta);
	}
	//判断hash中是否存在某个hashkey
	public boolean hasKey(String key,Object hashKey){
		return redisTemplate.opsForHash().hasKey(key, hashKey);
	}
    //往hash中插入hashkey
	public void putHash(String key,Object hashKey,Object value){
		redisTemplate.opsForHash().put(key, hashKey, value);
    }
	//设置key的过期时间
	public boolean expireAt(String key,Date date){
		return redisTemplate.expireAt(key, date);
	}
	//获取指定前缀的所有记录
	public Cursor<Entry<Object, Object>>  scan(String key,ScanOptions options){
		return redisTemplate.opsForHash().scan(key, options);
	}
	//获取指定前缀的所有记录
	public Map<Object, Object>  entries(String key){
			return redisTemplate.opsForHash().entries(key);
	}
	public List<Object>  values(String key){
		return redisTemplate.opsForHash().values(key);  
    }
	public Set<String>  keys(String key){
		if(key==null)return null;
		return redisTemplate.keys(key);
    }
	public void deleteKeys(Collection<String> keys){
		redisTemplate.delete(keys);
	}
	public void deleteKey(String key,String hashKeys){
		redisTemplate.opsForHash().delete(key, hashKeys);
	}
	public Object getHashValue(String key,String hashKey){
		return redisTemplate.opsForHash().get(key, hashKey);
	}
} 
