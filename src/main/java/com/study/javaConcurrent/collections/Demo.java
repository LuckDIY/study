package com.study.javaConcurrent.collections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

/**
 * 集合不安全现象演示
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {

        CopyOnWriteArrayList<Integer> list1 = new CopyOnWriteArrayList<>();
        list1.add(1);
        HashMap<String, String> map = new HashMap<>();
        System.out.println(map.computeIfAbsent("2", x->x+1));

       /* ArrayList list = new ArrayList();
        for (int i = 0; i < 10; i++) {

            int a = i;
            new Thread(() -> {

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                list.add(a);


                System.out.println(list);

            }).start();
        }

        Thread.sleep(10000);
        System.out.println("11" + list);*/

    }
}
