package com.study.javaBasis.quote;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 演示强软弱虚引用
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {
        //软引用
        //soft();

        //弱引用
        weak();



    }

    /**
     * 软引用
     * 如果内存空间足够，垃圾回收器就不会回收它，如果内存空间不足了，就会回收这些对象的内存
     */
    public static void soft() throws InterruptedException {
        //new byte[] 为软引用
        SoftReference<byte[]> softReference = new SoftReference<byte[]>(new byte[1024*1024*10]);
        System.out.println(softReference.get());
        System.gc();
        Thread.sleep(500);

        //变为强引就会Java heap space
        //byte[] bytes = softReference.get();
        System.out.println(softReference.get());

        //将堆大小设置成-xmx20m，  上面软引用生成了一个10m的数组，下面强引用new了一个12兆的空间导致堆空间不足，触发垃圾回收将软引用清除调
        byte[] b = new byte[1024 * 1024 * 12];
        System.out.println(softReference.get());
    }

    /**
     * 弱引用
     * 只要发生gc必被回收
     * @throws InterruptedException
     */
    public static void weak() throws InterruptedException {
        //弱引用person
        WeakReference<Person> softReference = new WeakReference<>(new Person());
        System.out.println(softReference.get());

        System.gc();
        Thread.sleep(500);
        ThreadLocal<Person> personThreadLocal = new ThreadLocal<>();
        personThreadLocal.get();

        System.out.println(softReference.get());
    }


}
