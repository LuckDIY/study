package com.study.javaConcurrent.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 演示线程池
 */
public class Demo {

    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,1, TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
//
//        //固定线程数
 //       Executors.newFixedThreadPool(5);
//        Executors.newSingleThreadExecutor();
//        Executors.newCachedThreadPool();
        //处理器核心数
        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2,
                1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                //线程和队列都不够用时，抛RejectedExecutionException
                //new ThreadPoolExecutor.AbortPolicy()
                //线程不够用队列满了，将run方法交给上层执行，当前调用的是main方法，所以是main线程执行，会堵塞主线程
                //拒绝任务的处理程序，它直接在execute方法的调用线程中运行拒绝任务
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //直接丢弃
                //new ThreadPoolExecutor.DiscardPolicy()
                //丢弃队列中第一条，将新的放入队列尾部
                new ThreadPoolExecutor.DiscardOldestPolicy()
                );

        for (int i = 0; i < 20; i++) {
            final int a = i;
            executor.execute(()->{
                System.out.println(Thread.currentThread().getName()+"  "+a);
            });
        }
        executor.shutdown();
    }
}
