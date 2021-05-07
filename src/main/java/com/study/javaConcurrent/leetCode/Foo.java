package com.study.javaConcurrent.leetCode;



import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Foo {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private Condition condition2 = lock.newCondition();

    private volatile int a = 1;

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        lock.lock();
        printFirst.run();
        a=2;
        condition.signal();
        lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        if(a!=2){
            condition.await();
        }

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        a=3;
        condition2.signal();
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        if(a!=3){
            condition2.await();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        lock.unlock();
    }


}

