package com.study.javaBasis.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 堵塞队列演示
 *
 */
public class Demo {

    public static void main(String[] args) {
        //演示数组阻塞队列
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        System.out.println(queue.element());
        /**
         * Inserts the specified element at the tail of this queue if it is possible to do so immediately
         * without exceeding the queue's capacity, returning true upon success
         * and
         * throwing an IllegalStateException if this queue is full.
         *
         * 如果满了抛异常 IllegalStateException if this queue is full.
         */
        queue.add(5);
        /**
         * 1。 add 队列未满true，满了抛异常  remove 队列为空抛错
         * 2....
         *
         */





    }
}
