package com.study.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.study.guice.service.DiService;
import com.study.guice.service.impl.DiServiceImpl;

/**
 * @program: study
 * @description: guice基础配置
 * @author: WangChaoLei
 * @create: 2021-05-17 18:04
 **/
public class GuiceBasicModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(DiService.class).to(DiServiceImpl.class);
    }

    public static void main(String[] args) {
        //创建di，使用module配置
        Injector injector = Guice.createInjector(new GuiceBasicModule());
        //获取实例
        DiService instance = injector.getInstance(DiService.class);
        //使用实例
        instance.print();
    }
}
