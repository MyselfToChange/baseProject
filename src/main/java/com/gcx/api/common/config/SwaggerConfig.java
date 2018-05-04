package com.gcx.api.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 *<p>Title:SwaggerConfig</p>
 *<p>Description:Swagger文档  访问地址:http://localhost:端口号/项目名/swagger-ui.html</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2018年2月1日</p>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gcx.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("济南市政务诚信  APIs")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }


}
