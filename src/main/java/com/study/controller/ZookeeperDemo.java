package com.study.controller;

import com.study.config.ZkLock;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring boot链接zk
 */
@RestController
public class ZookeeperDemo {

    @Autowired
    private ZooKeeper zkClient;

    @Autowired
    private ZkLock zkLock;

    @GetMapping("/zk/get")
    public String get() throws KeeperException, InterruptedException {
        byte[] data = zkClient.getData("/demo", true, new Stat());
        System.out.println(new String(data));
        return new String(data);
    }

    @GetMapping("/zk/test")
    public String test() throws KeeperException, InterruptedException {
        zkLock.lock();
        return "ok";
    }

}
