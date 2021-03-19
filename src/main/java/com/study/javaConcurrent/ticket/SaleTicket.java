package com.study.javaConcurrent.ticket;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{

    private int number = 30;
    private Lock lock = new ReentrantLock();

    public synchronized void saleTicket1(){
        try {
            //演示wait会释放锁，而sleep不会释放锁    锁和操作系统cpu调度没关系
            if(Thread.currentThread().getName().equals("卖票元1")){
                wait();
                //Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(number>0){
            try {
                Thread.sleep(111);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"卖出"+number--+"张票，还剩"+number);
        }

    }

    public void saleTicket(){
        lock.lock();
        try {
            if(number>0){
                try {
                    Thread.sleep(111);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"卖出"+number--+"张票，还剩"+number);
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean isNotNull(){
        return number>0;
    }
}

/**
 * 三个售票员 卖出 30张票
 */
public class SaleTicket {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        new Thread(()->{
            while(ticket.isNotNull()){
                ticket.saleTicket();
            }
        },"卖票元1").start();



        new Thread(()->{
            while(ticket.isNotNull()){
                ticket.saleTicket();
            }
        },"卖票元2").start();

        new Thread(()->{
            while(ticket.isNotNull()){
                ticket.saleTicket();
            }
        },"卖票元3").start();
    }


}
