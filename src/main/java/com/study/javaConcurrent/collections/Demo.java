package com.study.javaConcurrent.collections;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

/**
 * 集合不安全现象演示
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {

        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.offer("666");
        queue.offer("777");

        System.out.println(queue.poll());

    }
}
