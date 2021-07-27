package com.study.caffeine;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import sun.awt.SunHints;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @program: study
 * @description: caffeine缓存测试
 * @author: WangChaoLei
 * @create: 2021-05-14 09:11
 **/
public class Demo {

    public static void main(String[] args) {
        Cache<Object, Object> build = Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
        String key = "name";
        // 根据key查询一个缓存，如果没有返回NULL
        Object value = build.getIfPresent(key);

        System.out.println("打印"+value);
        // 根据Key查询一个缓存，如果缓存不存在，调用createExpensiveGraph方法，并将返回值保存到缓存。
        // 如果该方法返回Null则build.get返回null，如果该方法抛出异常则manualCache.get抛出异常
        value = build.get(key, k -> k+"1");

        System.out.println("打印"+value);
        System.out.println("打印缓存里"+build.getIfPresent(key));

        // 将一个值放入缓存，如果以前有值就覆盖以前的值
        build.put(key, value+"1");
        System.out.println("打印缓存里"+build.getIfPresent(key));

        // 删除一个缓存
        build.invalidate(key);
        System.out.println("打印缓存里"+build.getIfPresent(key));

        ConcurrentMap<Object, Object> map = build.asMap();
        System.out.println(map);
        build.invalidate(key);

    }
}
