package com.study.javaConcurrent.collections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合不安全现象演示
 */
public class Demo {

    public static void main(String[] args) {

        CopyOnWriteArrayList<Integer> list1 = new CopyOnWriteArrayList<>();
        list1.add(1);

        HashMap<String, String> map = new HashMap<>();
        map.put("1","2");
        ArrayList list = new ArrayList();
        for (int i = 0; i < 3; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    list.add(1);
                    System.out.println(list);
                }
            }).start();
        }
        System.out.println("11" + list);

    }
}
