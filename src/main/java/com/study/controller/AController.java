package com.study.controller;

import com.alibaba.fastjson.JSON;
import com.study.aop.WriteLog;
import com.study.generatorMybatis.mapper.UserInfoMapper;
import com.sun.org.apache.bcel.internal.generic.INEG;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@RestController
public class AController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Autowired
    private UserInfoMapper userInfoMapper;


    @Autowired
    private Ab ab;


    //@WriteLog
    @GetMapping("ok")
    public Object get(Person person, HttpServletRequest request){

        log.info("进入方法");

        return userInfoMapper.selectAll();
    }


    public static void a(String s){
        s=s+"123";
    }


}

class Person{
    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

