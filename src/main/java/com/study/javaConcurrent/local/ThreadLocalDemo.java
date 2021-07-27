package com.study.javaConcurrent.local;


public class ThreadLocalDemo {

    public static void main(String[] args) {
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        /**
         *         set方法 t为当天main线程，getMap是从t线程中取出 ThreadLocalMap
         *         ThreadLocalMap定义在threadLocal，使用在thread中。
         *
         *
         *         Thread t = Thread.currentThread();
         *         ThreadLocalMap map = getMap(t);
         *         if (map != null) {
         *             map.set(this, value);
         *         } else {
         *             createMap(t, value);
         *         }
         *
         *
         *         map.set(this, value);
         *
         *         private void set(ThreadLocal<?> key, Object value)
         *         Entry[] tab = table;
         *         int len = tab.length;
         *
         *         //将当前key hash后取模，根据此值往entry数组中set值，即不同的ThreadLocal的i不同，所以对应的数组下标不同
         *         int i = key.threadLocalHashCode & (len-1);
         *
         *             for (Entry e = tab[i];
         *                  e != null;
         *                  e = tab[i = nextIndex(i, len)]) {
         *
         *                 ThreadLocal<?> k = e.get();
         *
         *                 if (k == key) {
         *
         *                 //e即entry的value等于我们要存的值
         *                 e.value = value;
         *                 return;
         *                 }
         *
         *                 if (k == null) {
         *                     replaceStaleEntry(key, value, i);
         *                     return;
         *                 }
         *             }
         *
         *             tab[i] = new Entry(key, value);
         *             int sz = ++size;
         *             if (!cleanSomeSlots(i, sz) && sz >= threshold)
         *                 rehash();
         */
        stringThreadLocal.set("111");

        final ThreadLocal threadLocal = new InheritableThreadLocal();
        threadLocal.set("帅得一匹");
        Thread t = new Thread() {
            @Override
            public void run() {
                //super.run();
                System.out.println( "张三帅么 =" + threadLocal.get());
            }
        };
        t.start();

    }
}
