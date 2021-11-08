package com.study.javaConcurrent.pool;

import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2021-09-27 14:58
 **/
public class FutureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                //睡眠
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 123;
        });

        future.thenAccept(result-> System.out.println("得到结果后的回调"+result));

        future.exceptionally(e->{
            e.printStackTrace();
            return null;
        });

        //Thread.sleep(2000);

        Integer integer = future.get();
        System.out.println(integer);

    }
}
