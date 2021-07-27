package com.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2021-06-03 11:11
 **/
public class ThreadLocalDemo {

    public static void main(String[] args) {
        ThreadLocal<Integer> local = new ThreadLocal<>();

        local.set(1);


        ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();
        Integer integer = integerThreadLocal.get();
        System.out.println(integer);

        //创建线程池
        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 1L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1));
        for (int i = 0; i < 10; i++) {
            final int f = i;
            threadPoolExecutor.execute(()->{
                if(f<5){
                    local.set(f);
                }else{
                    System.out.println(local.get()+Thread.currentThread().toString());
                }
            });
        }*/
    }


}
