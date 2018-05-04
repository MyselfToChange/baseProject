package com.gcx.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gcx.api.common.dataSource.DynamicDataSourceRegister;

@SpringBootApplication
@EnableTransactionManagement
@Import({DynamicDataSourceRegister.class})
@MapperScan("com.gcx.api.dao")
@EnableScheduling
public class Application extends SpringBootServletInitializer{
	
//	@Override
//	protected SpringApplicationBuilder configure(
//			SpringApplicationBuilder builder) {
//		 builder.sources(Application.class);
//		return super.configure(builder);
//	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

