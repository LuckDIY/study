package com.study.generatorMybatis.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;

/**
 * @program: study
 * @description: mybatis 拦截器
 *
 * intercepts
 *   signature
 *     type 拦截的类名
 *     method 拦截的方法名
 *     args 拦截的方法类型
 *
 * @author: WangChaoLei
 * @create: 2021-08-04 18:16
 **/
@Intercepts(
        {
                @Signature(type = Executor.class,
                        method = "query",
                        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        }
)
@Slf4j
public class InterceptorDemo implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //实现分页插件

        //获取拦截方法的参数
        Object[] args = invocation.getArgs();

        //0.映射语句
        MappedStatement mappedStatements = (MappedStatement) args[0];

        //1. obj
        Object obj = args[1];

        // 2. rowRounds 行边界
        RowBounds rowBounds = (RowBounds)args[2];

        // 3. 结果处理
        ResultHandler resultHandler = (ResultHandler) args[3];



        //拦截的目标对象
        Executor executor = (Executor)invocation.getTarget();

        BoundSql boundSql = mappedStatements.getBoundSql(obj);


        return null;
    }

    @Override
    public Object plugin(Object target) {
       return Plugin.wrap(target, this);
    }

    /**
     * 传递插件参数
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

        log.info(JSON.toJSONString(properties));

    }
}
