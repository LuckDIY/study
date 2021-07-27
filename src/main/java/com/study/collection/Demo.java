package com.study.collection;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2021-06-10 18:32
 **/
public class Demo {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(999);
        objects.add(111);
        DiyList diyList = new DiyList(objects);

        System.out.println(JSON.toJSONString(diyList));

        int price = 10000;
        float f = 0.04f;
        int i=0;
        while(20000>(price=(int)(price*(1+f)))){
            i++;
        }
        System.out.println(i);
    }

}
