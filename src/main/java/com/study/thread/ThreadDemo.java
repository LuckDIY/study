package com.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo {

    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger();
        final PrintAB print = new PrintAB();

        new Thread(new Runnable() {
            public void run(){
                for(int i=0;i<5;i++) {
                    print.printA();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for(int i=0;i<5;i++) {
                    print.printB(); }
            }
        }).start();
    }
}

class PrintAB{
    private boolean flag = true;

    public synchronized void printA () {
        while(!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(1);
        flag = false;
        this.notify();
    }

    public synchronized void printB () {
        while(flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(2);
        flag = true;
        this.notify();
    }

}
