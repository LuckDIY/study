package com.study.javaBasis.quote;

public class Person {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("我被gc回收啦");
    }
}
