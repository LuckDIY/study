package com.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class MyServer {
    public static void main(String[] args) throws Exception {
        //创建两个线程组 boosGroup、workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建服务端的启动对象，设置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //todo 启动工厂生产启动类， 1.绑定 线程租
            //设置两个线程组boosGroup和workerGroup
            bootstrap.group(bossGroup, workerGroup)

                 //todo 2.设置channel类型
                //设置服务端通道实现类型    
                .channel(NioServerSocketChannel.class)

                //todo 3. 设置option，设置的是服务端用于接收进来的连接，也就是boosGroup线程
                //设置线程队列得到连接个数    
                .option(ChannelOption.SO_BACKLOG, 128)

                //todo 4. 设置 optionChild 提供给父管道接收到的连接，也就是workerGroup线程。
                //设置保持活动连接状态    
                .childOption(ChannelOption.SO_KEEPALIVE, true)

                //todo 5.childHandler设置通道对象channel
                //使用匿名内部类的形式初始化通道对象    
                .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //给pipeline管道设置处理器
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new ReadTimeoutHandler(60));
                            pipeline.addLast(new MyServerHandler());
                        }
                    });//给workerGroup的EventLoop对应的管道设置处理器
            System.out.println("java技术爱好者的服务端已经准备就绪...");
            //绑定端口号，启动服务端，同步
            //ChannelFuture channelFuture = bootstrap.bind(6666).sync();

            //异步绑定
            ChannelFuture channelFuture = bootstrap.bind(6666).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("绑定端口成功");
                } else {
                    System.out.println("绑定端口失败");
                }
            });

            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
