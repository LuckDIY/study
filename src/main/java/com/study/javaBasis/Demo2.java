package com.study.javaBasis;

public class Demo2 {

    public static void main(String[] args) {
        B b = new B();
    }
}

class A{
    public A(){
        System.out.print("A");
    }
}
class B extends A{
    public B(){
        System.out.print("B");
    }
}
