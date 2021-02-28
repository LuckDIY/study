package com.study.javaBasis;

/**
 * 测试java基础的东西
 */
public class Demo {


    public void integerTest(){
        int i1 =1;
        int i2 =1;
        System.out.println(i1==i2);
        Integer i3 = 1;
        Integer i4 = 1;
        System.out.println(i3==i4);
        System.out.println(i3==i1);
        /**
         * 包装类型有一个缓存，大小是8位  -128 -- 127
         *
         * 大于此数会new
         * 在包装类型和基础类型比较会自动拆箱
         */
        Integer i5 =128;
        Integer i6 = 128;
        System.out.println(i5==i6);
        System.out.println(i5==128);
    }

    public static void main(String[] args) {
        new Demo().integerTest();
    }
}
