package com.study.javaConcurrent.ticket;

/**
 * wait notify 练习测试
 * 卖包子模型
 */
public class WaitNotifyDemo {

    public static void main(String[] args) {
        BaoziPu baoziPu = new BaoziPu();

        new Thread(()->{
            while(true){
                baoziPu.chi();
            }
        },"吃包子1").start();

        new Thread(()->{
            while(true){
                baoziPu.chi();
            }
        },"吃包子2").start();

        new Thread(()->{
            while(true){
                baoziPu.chi();
            }
        },"吃包子3").start();


        new Thread(()->{
            while (true){
                baoziPu.zuo();
            }
        },"做包子A").start();

        new Thread(()->{
            while (true){
                baoziPu.zuo();
            }
        },"做包子B").start();
    }

}

class BaoziPu{

    /**
     * 是否存在包子
     */
    private boolean exist;


    public synchronized void chi(){
        while(!exist){
            //当前线程等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"线程吃包子");
        //吃完包子，exist设置为false
        exist=false;
        //没有包子,唤醒另外一个线程做包子
        this.notify();
    }

    public synchronized void zuo(){
        while(exist){
            //当前线程等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"线程做包子");
        //做包子，exist设置为true
        exist=true;
        //有包子,唤醒另外一个线程吃包子
        this.notify();
    }



}
