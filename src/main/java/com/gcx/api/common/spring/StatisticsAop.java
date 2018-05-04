package com.gcx.api.common.spring;


import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ScanOptions.ScanOptionsBuilder;
import org.springframework.stereotype.Component;

import com.gcx.api.common.dataSource.CustomDataSource;
import com.gcx.api.common.note.ModelDescription;
import com.gcx.api.common.redis.UserRedisUtils;
import com.gcx.api.common.spring.SpringContextHolder;


/**
 *<p>Title:StatisticsAop</p>
 *<p>Description:通过切面进行数据统计</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2018年4月19日</p>
 */
@Aspect
@Component
public class StatisticsAop {
	
   public static UserRedisUtils redisTemplate = SpringContextHolder.getBean(UserRedisUtils.class);
   
   
   @Before("execution(* com.gcx.api.service.impl.*.addRecord(..))")
   public  void  dataCount(JoinPoint pjp) throws Throwable{
		Signature signature = pjp.getSignature();    
		MethodSignature methodSignature = (MethodSignature)signature;  
		Method targetMethod = methodSignature.getMethod();
		Method realMethod = pjp.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());
		    //首先判断方法级别
		    ModelDescription cds=realMethod.getAnnotation(ModelDescription.class);
	    	if(cds==null){ //判断类级别
	    		 cds= AnnotationUtils.findAnnotation(realMethod.getDeclaringClass(), ModelDescription.class);
	    	}
	    	if(cds==null){
	    		return;
	    	}
	    	String modelDescription=cds.value();
	    	String skey="statistics:"+cds.key();
	        //加入总数
	        redisTemplate.putHash(skey, "name", modelDescription);
	    	redisTemplate.putHash(skey, "key", cds.key());
	        redisTemplate.incrHash(skey,"count", 1);
	        redisTemplate.incrHash(skey,"todayCount", 1);
   }
 
}
