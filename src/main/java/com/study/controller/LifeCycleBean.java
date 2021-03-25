package com.study.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author LinkWang
 * @projectName demo
 * @description: TODO
 * @date 2021/1/4 17:33
 */
@Component
public class LifeCycleBean implements BeanPostProcessor {


    @Autowired
    private Ab ab;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("beanPostProcessor class de method"+beanName);
        return null;
    }
}
