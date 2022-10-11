package com.study.javaProxy;

import org.apache.ibatis.annotations.Select;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class MapperHandler implements InvocationHandler {
 
    private Class<?> clazz;
 
    public MapperHandler(Class<?> helloDao) {
        this.clazz = helloDao;
    }
 
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(args != null) {
            for (Object arg : args) {
                System.out.println("通过代理拿到了参数：" + arg);
            }
        }

        System.out.println("拿到方法名字:"+method.getName());

        for (Parameter parameter : method.getParameters()) {
            System.out.println("通过反射拿到了参数以及类型等; 拿到参数:"+parameter.getName()+"参数类型:"+ parameter.getType());
        }
        for (Annotation annotation : method.getAnnotations()) {
            if(annotation instanceof Select) {
                Select select = (Select) annotation;
                String[] value = select.value();
                System.out.println("拿到了sql: " + Arrays.toString(value));

                //拼接sql todo
                //value[0]
            }
        }

        return "执行结果";
    }
}