package com.study.controller;


import com.study.generatorMybatis.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
public class AController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConfigurableApplicationContext context;



    @Autowired
    private UserInfoMapper userInfoMapper;


    @Autowired
    private Ab ab;


    //@WriteLog
    @GetMapping("ok")
    public Object get(HttpServletRequest request){

        log.info("进入方法");

        return ab.a;
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

