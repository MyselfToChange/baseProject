package com.gcx.api.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.gcx.api.common.spring.CommonInterceptor;
/**
 *<p>Description:配置springMVC拦截器</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2018年4月2日</p>
 */
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(new CommonInterceptor());
    }
}
