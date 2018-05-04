package com.gcx.api.common.spring;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gcx.api.common.base.BaseController;
import com.gcx.api.common.util.Page;

/**
 *<p>Title:PageAop</p>
 *<p>Description:通过切面初始化相同的参数</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2017年9月28日</p>
 */
@Component
@Aspect
public class PageAop extends BaseController {
	
	private final Logger log = LoggerFactory.getLogger(PageAop.class);  
	
	@Before("execution(* com.gcx.api.controller.*Controller.findAllRecords(..))")
	public void initPage(){
		    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
	        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
	        HttpServletRequest request = sra.getRequest();
	        new Page(request);  //初始化分页
	        log.info(">>>>>>>>>>>>>>>>>>>>>初始化分页");
	}
	
}
