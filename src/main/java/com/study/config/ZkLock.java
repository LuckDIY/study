package com.study.config;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现zk分布式锁
 */
@Component
@Slf4j
public class ZkLock implements Lock {

    @Autowired
    private ZooKeeper zkClient;


    @SneakyThrows
    @Override
    public void lock() {
        //如果加锁成功则返回
        if(tryLock()){
            return ;
        }
        //不成功阻塞
        waitForLock();

        //尝试重新加锁
        lock();

    }

    private void waitForLock() throws KeeperException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        //监听/lock是否为空
        Stat exists = zkClient.exists("/lock", (event -> {
            log.info("/lock发生了变化：{}", JSON.toJSONString(event));
            countDownLatch.countDown();
        }));
        log.info("exists:{}",JSON.toJSONString(exists));
        //不为空的话在这堵塞，监听到为空则唤醒
        countDownLatch.await();

    }

    @Override
    public boolean tryLock() {
        try {
            String s = zkClient.create("/lock", "是个锁".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }



    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @SneakyThrows
    @Override
    public void unlock() {

        zkClient.delete("/lock",-1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
