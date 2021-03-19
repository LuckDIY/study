package com.study.javaConcurrent.impl;

import com.google.common.util.concurrent.ListenableFutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class RunableDeom implements Runnable{
    @Override
    public void run() {
        System.out.println(111);
    }
}

class ThreadDemo extends Thread{
    @Override
    public void run() {
        System.out.println(222);
    }
}

class CallableDemo implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 1024;
    }
}



/**
 * 多线程的多种实现
 */
public class Demo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Runable实现
        new Thread(new RunableDeom()).start();

        //继承实现
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();

        //通过callable实现
        CallableDemo callableDemo = new CallableDemo();
        ListenableFutureTask<Integer> task = ListenableFutureTask.create(callableDemo);

        FutureTask<Integer> futureTask = new FutureTask<>(callableDemo);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());


    }

}
