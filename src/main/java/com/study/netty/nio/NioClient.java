package com.study.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NioClient {

    public static void main(String[] args) throws IOException {

        try (SocketChannel socketChannel = SocketChannel.open()) {

            socketChannel.configureBlocking(false);

            if (!socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000))) {
                //连接失败
                System.out.println("连接失败");

                while(!socketChannel.finishConnect()){
                    System.out.println("重试连接");
                }
            }

            //连接成功
            Scanner scanner = new Scanner(System.in);

            while(true){
                String s = scanner.nextLine();
                ByteBuffer wrap = ByteBuffer.wrap(s.getBytes());
                socketChannel.write(wrap);
            }
        }


    }
}
