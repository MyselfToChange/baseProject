package com.gcx.api.common.dataSource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
/**
 *<p>Title:DynamicDataSourceRegister</p>
 *<p>Description:动态数据源注册</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2018年3月27日</p>
 */
public class DynamicDataSourceRegister implements
		ImportBeanDefinitionRegistrar, EnvironmentAware {
	
    //如配置文件中未指定数据源类型，使用该默认值

    private static final Object DATASOURCE_TYPE_DEFAULT = "com.alibaba.druid.pool.DruidDataSource";

    private ConversionService conversionService = new DefaultConversionService();

    private PropertyValues dataSourcePropertyValues;
    
    // 默认数据源
    private DataSource defaultDataSource;

    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();

	public DynamicDataSourceRegister() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setEnvironment(Environment environment) {
		  initDefaultDataSource(environment);
	      initCustomDataSources(environment);
	}

	private void initCustomDataSources(Environment env) {
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.datasource.");

        String dsPrefixs = propertyResolver.getProperty("names");
        String dsn="";
        for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
        	dsn=dsPrefix+".";
            Map<String, Object> dsMap = new HashMap<String, Object>();
            dsMap.put("type", propertyResolver.getProperty(dsn+"type"));
            dsMap.put("driverClassName", propertyResolver.getProperty(dsn+"driverClassName"));
            dsMap.put("url", propertyResolver.getProperty(dsn+"url"));
            dsMap.put("username", propertyResolver.getProperty(dsn+"username"));
            dsMap.put("password", propertyResolver.getProperty(dsn+"password"));
            
            DataSource ds = buildDataSource(dsMap);

            customDataSources.put(dsPrefix, ds);

            dataBinder(ds, env);

        }
		
	}
    /**
     * 加载主数据源配置
     * @param env
     */
	private void initDefaultDataSource(Environment env) {
		//读取主数据源
		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
		Map<String, Object> dsMap = new HashMap<String, Object>();
	    dsMap.put("type", propertyResolver.getProperty("type"));
        dsMap.put("driverClassName", propertyResolver.getProperty("driverClassName"));
        dsMap.put("url", propertyResolver.getProperty("url"));
        dsMap.put("username", propertyResolver.getProperty("username"));
        dsMap.put("password", propertyResolver.getProperty("password"));
        
       //创建数据源;
        defaultDataSource = buildDataSource(dsMap);
        dataBinder(defaultDataSource, env);
	}

	private void dataBinder(DataSource dataSource, Environment env) {
	        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);

	        dataBinder.setConversionService(conversionService);

	        dataBinder.setIgnoreNestedProperties(false);//false

	        dataBinder.setIgnoreInvalidFields(false);//false

	        dataBinder.setIgnoreUnknownFields(true);//true

	        if(dataSourcePropertyValues == null){

	            Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");

	            Map<String, Object> values = new HashMap<>(rpr);

	            // 排除已经设置的属性
	            values.remove("type");

	            values.remove("driverClassName");

	            values.remove("url");

	            values.remove("username");

	            values.remove("password");

	            dataSourcePropertyValues = new MutablePropertyValues(values);

	        }

	        dataBinder.bind(dataSourcePropertyValues);
		
	}

	@SuppressWarnings("unchecked")
	private DataSource buildDataSource(Map<String, Object> dsMap) {
		
		Object type = dsMap.get("type");
        if (type == null){ //默认数据源
            type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
        }

        Class<? extends DataSource> dataSourceType;
        
        try {

            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);

             String driverClassName = dsMap.get("driverClassName").toString();

             String url = dsMap.get("url").toString();

             String username = dsMap.get("username").toString();

             String password = dsMap.get("password").toString();

             DataSourceBuilder factory =   DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username).password(password).type(dataSourceType);

             return factory.build();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }
		return null;
	}

	@Override
	public void registerBeanDefinitions(
			AnnotationMetadata importingClassMetadata,
			BeanDefinitionRegistry registry) {
		
	        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
	        // 将主数据源添加到更多数据源中
	        targetDataSources.put("dataSource", defaultDataSource);
	        // 添加更多数据源
	        targetDataSources.putAll(customDataSources);
	        // 创建DynamicDataSource
	        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();

	        beanDefinition.setBeanClass(DynamicDataSource.class);

	        beanDefinition.setSynthetic(true);

	        MutablePropertyValues mpv = beanDefinition.getPropertyValues();

	        //添加属性：AbstractRoutingDataSource.defaultTargetDataSource
	        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);

	        mpv.addPropertyValue("targetDataSources", targetDataSources);

	        registry.registerBeanDefinition("dataSource", beanDefinition);

	}

}
