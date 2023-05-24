package com.study.netty.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {


    public static void main(String[] args) throws IOException {


        try (
                //create channel
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                //create selector
                Selector selector = Selector.open();) {

            //create serverSocket bind 8000
            serverSocketChannel.socket().bind(new InetSocketAddress(8000));
            //非堵塞
            serverSocketChannel.configureBlocking(false);

            //注册到selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while(true){
                if (selector.select(6000)==0) {
                    System.out.println("等待无连接");
                    continue;
                }

                //有事件发生
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();


                    //是连接请求
                    if (selectionKey.isAcceptable()) {
                        System.out.println("有人来连接我");
                        //获取客户端连接通道
                        SocketChannel socketChannel = serverSocketChannel.accept();

                        socketChannel.configureBlocking(false);
                        //把socketChannel也注册到selector
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }

                    //是读请求
                    if(selectionKey.isReadable()){
                        SocketChannel channel = (SocketChannel)selectionKey.channel();

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //ByteBuffer byteBuffer = (ByteBuffer)selectionKey.attachment();
                        int read = channel.read(byteBuffer);
                        if(read!=-1){
                            System.out.println("读到:"+read);
                            System.out.println("来自客户端:"+ new String(byteBuffer.array()));
                        }else {
                            System.out.println("连接已经关闭了");
                            channel.close();
                        }
                    }

                    iterator.remove();
                }


            }
        }


    }
}
