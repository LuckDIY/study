package com.study.jvm.nativeTest;

/**
 * @program: study
 * @description: 测试native
 * @author: WangChaoLei
 * @create: 2022-03-31 16:43
 **/
public class NativeDemo {

    static{
        //加载c动态库
        System.load("/Users/chaoleiwang/CLionProjects/nativeTest/test.dylib");
    }

    public static void main(String[] args) {
        System.out.println(sum(7,7));
    }

    // /Library/Java/JavaVirtualMachines/jdk1.8.0_281.jdk/Contents/Home
    // /Users/chaoleiwang/Library/Java/JavaVirtualMachines/openjdk-17.0.2/Contents/Home
    public static native int sum(int a,int b);
}
