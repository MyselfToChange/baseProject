package com.gcx.api.common.spring;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gcx.api.common.redis.UserRedisUtils;
/**
 *<p>Title:Scheduler</p>
 *<p>Description:定时任务</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2018年4月23日</p>
 */
@Component
public class Scheduler {
	
	    private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	    @Resource
	    public  UserRedisUtils userRedisUtils;
	      
	    @Scheduled(cron="0 0 0 * * ?") //每晚凌晨执行一次  
	    public void redisScheduler() {    
	    	//获取所有当天统计的hashkey
	    	Set<String> keys=userRedisUtils.keys("statistics:*");
	    	//删除当天统计的key
	    	for (String string : keys) {
	    		userRedisUtils.deleteKey(string,"todayCount");
			}
	    	logger.info("redis 统计删除成功");
	    }   
     
}
