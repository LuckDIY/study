package com.study.javaConcurrent.ticket;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource{

    private Integer count=0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Integer addOne(){
        lock.lock();

        try {
            while(count!=0){
                //不为0，让此线程等待
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"线程，当前值"+count+++"现在值"+count);

            //叫醒等待的减减线程
            condition.signal();
        } finally {
            //解锁
            lock.unlock();
        }

        return count;
    }

    public Integer lessOne(){
        lock.lock();

        try {
            while(count==0){
                //为0，让此线程等待
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"线程，当前值"+count--+"现在值"+count);
            //叫醒等待的减减线程
            condition.signal();
        } finally {

            lock.unlock();
        }

        return count;
    }
}

/**
 * 线程协助
 */
public class TheadWaitNotifyDemo {

    public static void main(String[] args) {
        Resource resource = new Resource();

        new Thread(()->{
            for (int i = 0; i < 2; i++) {
                resource.addOne();
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 2; i++) {
                resource.lessOne();
            }
        }).start();
    }

}
