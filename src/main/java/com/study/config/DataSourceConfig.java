package com.study.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据库配置
 */
@Slf4j
@Configuration
@MapperScan(basePackages = DataSourceConfig.MAPPER_PACKAGE, sqlSessionFactoryRef = DataSourceConfig.SESSION_FACTORY)
public class DataSourceConfig {


    static final String SESSION_FACTORY = "dbSqlSessionFactory";

    private static final String DATASOURCE_NAME = "dbDataSource";

    /**
     * mapper类的包路径
     */
    static final String MAPPER_PACKAGE = "com.study.generatorMybatis.mapper";

    static final String MODEL_PACKAGE = "com.example.model";

    /**
     * 自定义mapper的xml文件路径
     */
    private static final String MAPPER_XML_PATH = "classpath*:com.example.mapper/*Mapper.xml";

    /**
     * 数据源配置的前缀，必须与application.properties中配置的对应数据源的前缀一致
     */
    private static final String BUSINESS_DATASOURCE_PREFIX = "spring.datasource.business";

    private static final String QUARTZ_DATASOURCE_PREFIX = "spring.datasource.quartz";

    @Primary
    @Bean(name = DATASOURCE_NAME)
    @ConfigurationProperties(prefix = BUSINESS_DATASOURCE_PREFIX)
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置Mybatis环境
     */
    @Primary
    @Bean(name = SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        log.info("配置SqlSessionFactory开始");
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(druidDataSource());
        return sessionFactory.getObject();
    }

    /**
     * @QuartzDataSource 注解则是配置Quartz独立数据源的配置
     */
    /*@Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = QUARTZ_DATASOURCE_PREFIX)
    public DataSource quartzDataSource(){
        return new DruidDataSource();
    }*/
}

