/*package com.gcx.api.common.config;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class ValidatorConfiguration {
	
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        *//**设置validator模式为快速失败返回*//*
        postProcessor.setValidator(validator());
        return postProcessor;
    }
    @Bean
    public Validator validator(){
    	ValidatorFactory validatorFactory =  Validation.byProvider(HibernateValidator.class)
    			.configure()
    			.addProperty( "hibernate.validator.fail_fast", "true")
    			.buildValidatorFactory();
    	Validator validator = validatorFactory.getValidator();
    	return validator;
    }
}*/
