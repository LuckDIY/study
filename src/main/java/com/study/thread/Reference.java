package com.study.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2021-06-03 11:48
 **/
public class Reference {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("1","a");
        ArrayList<Map> arr = new ArrayList<>();
        arr.add(map);

        map=null;
        System.out.println(arr);

    }

}
