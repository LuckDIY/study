package com.study.netty.rpc.server;

// 服务提供者和服务消费者都需要该接口，但只在服务端实现，客户端无需实现
public interface HelloService {
    String hello(String mes);
}
