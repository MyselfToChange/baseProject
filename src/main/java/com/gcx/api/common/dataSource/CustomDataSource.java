package com.gcx.api.common.dataSource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *<p>Title:CustomDataSource</p>
 *<p>Description:自定义选择数据源注解(在Service实现层的类或方法上使用该注解切换数据源，方法优先于类)</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2017年10月16日</p>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomDataSource {
	String value();
}
