package com.study.designPatterns.alert;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2022-01-26 20:37
 **/
public class Notification {


    public void notify(String level,String msg){
        System.out.println(level+msg);
    }
}
