package com.study.thread.training;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: study
 * @description: 交替打印demo
 * 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5 然后是线程2打印6,7,8,9,10 然后是线程3打印11,12,13,14,15.接着再由线程1打印16,17,18,19,20....依次类推, 直到打印到60。
 * @author: WangChaoLei
 * @create: 2021-08-27 16:29
 **/
public class JiaotiDemo {

    public static void main(String[] args) throws InterruptedException {

        ///new Thread(new A(0)).start();
        //new Thread(new A(1)).start();
        //new Thread(new A(2)).start();

        ReentrantLock reentrantLock = new ReentrantLock(true);
        reentrantLock.lock();
        reentrantLock.unlock();

        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire(2);
        System.out.println(semaphore.getQueueLength());
        System.out.println(semaphore.availablePermits());
        semaphore.release();
        System.out.println(semaphore.availablePermits());

    }
}

class A implements Runnable {

    //共享资源
    static int num = 0;

    //线程顺序
    private int i;

    public A(int i) {
        this.i = i;
    }


    @SneakyThrows
    @Override
    public void run() {

        synchronized (A.class) {
            while (num < 1000) {
                if (num % 15 != i * 5) {
                    //如果不该自己执行自己便等待
                    A.class.wait();
                } else {
                    //该自己执行
                    for (int j = 0; j < 5; j++) {
                        System.out.println("i:" + i + "------" + (++num));
                    }
                    A.class.notifyAll();
                }


            }

        }

    }
}
