package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class AController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Ab ab;


    @GetMapping("ok")
    public String get(){
        System.out.println(ab.a);
        return "ok";
    }

    public static void main(String[] args) {
        String s = "abc";
        a(s);
        System.out.println(s);
    }

    public static void a(String s){
        s=s+"123";
    }


}

