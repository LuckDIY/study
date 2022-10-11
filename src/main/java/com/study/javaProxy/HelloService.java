package com.study.javaProxy;

import java.lang.reflect.Proxy;

public class HelloService {
 
    private HelloDao helloDao;
 
    public HelloService(HelloDao helloDao) {
        this.helloDao = helloDao;
    }
 
    public void test() {
        System.out.println(helloDao.sayHello("小明同学"));
    }
 
    public static void main(String[] args) {
        HelloService helloService = new HelloService(
                (HelloDao) Proxy.newProxyInstance(HelloDao.class.getClassLoader()
                        ,new Class[]{HelloDao.class},new MapperHandler(HelloDao.class))
        );
        helloService.test();
    }



}