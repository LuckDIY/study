package com.study.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: study
 * @description: 通过注解打印请求log
 * @author: WangChaoLei
 * @create: 2021-05-31 09:30
 **/
@Aspect
@Configuration
@Slf4j
public class RequestLog {




    @Around(value = "@annotation(com.study.aop.WriteLog)")
    public Object beforeLog(ProceedingJoinPoint joinPoint){
        //获得前端请求的request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求方式
        String method = request.getMethod();

        //类名
        String clazzName = joinPoint.getTarget().getClass().getName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //方法名
        String methodName = methodSignature.getName();
        //参数名数组
        String[] parameters =  methodSignature.getParameterNames();
        //参数值
        Object[] args = joinPoint.getArgs();

        String logStr = "\r\n\t\t\tmethod: %s \r\n" +
                "\t\t\targs:\r\n" +
                "%s" +
                "\t\t\tresult: %s";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            String param = parameters[i];
            String arg = JSON.toJSONString(args[i]);
            builder.append("\t\t\t\t").append(i).append(":").append(param).append(":").append(arg).append("\r\n");
        }
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logStr = String.format(logStr, methodSignature, builder,JSON.toJSONString(proceed, SerializerFeature.WriteNullStringAsEmpty));


        log.info(logStr);
        return proceed;
    }
}
