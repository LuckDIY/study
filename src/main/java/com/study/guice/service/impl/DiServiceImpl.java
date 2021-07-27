package com.study.guice.service.impl;

import com.study.guice.service.DiService;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2021-05-17 18:10
 **/
public class DiServiceImpl implements DiService {
    @Override
    public void print() {
        System.out.println("我是实现类impl");
    }
}
