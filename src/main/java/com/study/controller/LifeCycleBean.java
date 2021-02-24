package com.study.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LinkWang
 * @projectName demo
 * @description: TODO
 * @date 2021/1/4 17:33
 */
@Component
public class LifeCycleBean implements BeanNameAware, BeanFactoryAware {


    @Autowired
    private Ab ab;


    @Override
    public void setBeanName(String name) {
        System.out.println("lifeCycle implements BeanNameAware setBeanName=" + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("lifeCycle implements BeanFactoryAware setBeanFactory="+ beanFactory);
    }
}
