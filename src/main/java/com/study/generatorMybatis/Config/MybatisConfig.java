package com.study.generatorMybatis.Config;

import com.study.generatorMybatis.interceptor.InterceptorDemo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @program: study
 * @description: mybatis配置
 * @author: WangChaoLei
 * @create: 2021-08-04 18:42
 **/
@Configuration
public class MybatisConfig {


    @Bean
    public InterceptorDemo interceptorDemo(){
        InterceptorDemo interceptorDemo = new InterceptorDemo();

        Properties properties = new Properties();
        properties.setProperty("测试","123");
        interceptorDemo.setProperties(properties);

        return interceptorDemo;
    }
}
