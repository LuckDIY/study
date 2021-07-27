package com.study.collection;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: study
 * @description: 测试继承list
 * @author: WangChaoLei
 * @create: 2021-06-10 18:30
 **/
@Data
public class DiyList {


    Integer a = 123;

    Integer b =666;

    private ArrayList list;

    public DiyList(ArrayList list){
        this.list=list;
    }


    @Override
    public String toString(){
        return a+b+"";
    }



}
