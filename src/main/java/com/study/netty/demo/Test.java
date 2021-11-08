package com.study.netty.demo;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2021-09-22 20:52
 **/
public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {

        //创建buffer,数组长度3，limit结尾末尾3
        IntBuffer intBuffer = IntBuffer.allocate(3);

        //capacity容量，缓冲区容量
        for (int i = 0; i < intBuffer.capacity(); i++) {

            intBuffer.put(i+10);
        }

        //切换为读模式
        intBuffer.flip();

        //position 下一条hb中位置  limit 最大索引+1
        //是否有剩余,position < limit
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }


        String msg = "测试";
        //创建一个固定大小的buffer(返回的是HeapByteBuffer)
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] bytes = msg.getBytes("UTF-8");
        System.out.println(Arrays.toString(bytes));


    }
}
