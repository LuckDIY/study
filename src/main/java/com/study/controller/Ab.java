package com.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
@Slf4j
public class Ab {

    String a;


    public  Ab(){
        a="666";
    }

    public static void main(String[] args) {

        consumerTest("消费你",(s)-> System.out.println(s));

        consumerTest3(5,5,(x)-> System.out.println(x));
    }


    //consumer
    public static void consumerTest(String str,Consumer<String> s){
        s.accept(str);
    }

    public static void consumerTest2(Integer i,Integer j,Consumer<Integer> s){
        int t = i+j+666;
        //只负责消费，不负责计算
        s.accept(t);
    }

    public static void consumerTest3(Integer i,Integer j,Consumer<Integer> s){
        int t = 666;
        //只负责消费，不负责计算
        //s.accept(t);
        //先消费当前accept然后消费参数consumer的accept
        s.andThen((a)->{
            int t1 = i+j+777;
            System.out.println("调用的消费者"+t1);
        }).accept(t);
    }


    //Supplier
    public static void supplierTest(Supplier<String> supplier){



    }


    @Cacheable("test")
    public String equals1(String name) {
        log.info("进入方法"+name);
        return "helloword";
    }



}
